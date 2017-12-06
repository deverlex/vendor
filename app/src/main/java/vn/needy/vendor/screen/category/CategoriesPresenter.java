package vn.needy.vendor.screen.category;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import vn.needy.vendor.api.v1.company.response.CompanyResponse;
import vn.needy.vendor.database.model.Category;
import vn.needy.vendor.database.model.Company;
import vn.needy.vendor.api.v1.category.CategoryRepository;
import vn.needy.vendor.api.v1.company.CompanyRepository;
import vn.needy.vendor.api.v1.company.CompanyLocalDataSource;
import vn.needy.vendor.api.v1.category.CategoryRemoteDataSource;
import vn.needy.vendor.api.v1.company.CompanyRemoteDataSource;
import vn.needy.vendor.database.sharedprf.SharedPrefsApi;
import vn.needy.vendor.error.BaseException;
import vn.needy.vendor.error.SafetyError;
import vn.needy.vendor.service.VendorServiceClient;

/**
 * Created by lion on 03/11/2017.
 */

public class CategoriesPresenter implements CategoriesContract.Presenter {

    private final static String TAG = CategoriesPresenter.class.getName();

    private final CategoriesContract.ViewModel mViewModel;

    private final CompositeDisposable mCompositeDisposable;

    private final CategoryRepository mCategoryRepository;
    private final CompanyRepository mCompanyRepository;

    private SharedPrefsApi mPrefsApi;
    private String mCompanyId;

    public CategoriesPresenter(CategoriesContract.ViewModel viewModel, SharedPrefsApi prefsApi) {
        mPrefsApi = prefsApi;
        mViewModel = viewModel;
        mCategoryRepository = new CategoryRepository(
                new CategoryRemoteDataSource(VendorServiceClient.getInstance())
        );
        mCompanyRepository = new CompanyRepository(
                new CompanyRemoteDataSource(VendorServiceClient.getInstance()),
                new CompanyLocalDataSource(prefsApi));
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void onStart() {
        mCompanyRepository.getCompanyInformation()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CompanyResponse>() {
                    @Override
                    public void accept(CompanyResponse companyResponse) throws Exception {
                        Company company = companyResponse.getCompany();
                        if (company != null) {
                            mCompanyId = company.getId();

                            // get category of company
                            getCategoryCompany();
                        } else {
                            // notify an error
                        }
                    }
                }, new SafetyError() {
                    @Override
                    public void onSafetyError(BaseException error) {
                        /// notify an error
                    }
                });
    }

    @Override
    public void onStop() {
        mCompositeDisposable.dispose();
    }

    private void getCategoryCompany() {
        // check price now or price later
        Disposable disposable = mCategoryRepository.getLinkCategories(mCompanyId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Category>>() {
                    @Override
                    public void accept(List<Category> categories) throws Exception {
                        mViewModel.onGetListCategorySuccess(categories);
                    }
                }, new SafetyError() {
                    @Override
                    public void onSafetyError(BaseException error) {
                        mViewModel.onGetListCategoryError(error);
                    }
                });
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void getListSubCategories(String category) {
        Disposable disposable = mCategoryRepository.getCompanyLinkCategories(category, mCompanyId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Category>>() {
                    @Override
                    public void accept(List<Category> categories) throws Exception {
                        if (categories.size() == 0) {
                            mViewModel.backActivity();
                        }
                        mViewModel.onGetListCategorySuccess(categories);
                    }
                }, new SafetyError() {
                    @Override
                    public void onSafetyError(BaseException error) {
                        mViewModel.onGetListCategoryError(error);
                    }
                });
        mCompositeDisposable.add(disposable);
    }
}
