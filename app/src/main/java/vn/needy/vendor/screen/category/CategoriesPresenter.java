package vn.needy.vendor.screen.category;

import android.util.Log;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import vn.needy.vendor.data.model.Category;
import vn.needy.vendor.data.model.Company;
import vn.needy.vendor.data.source.CategoryRepository;
import vn.needy.vendor.data.source.CompanyRepository;
import vn.needy.vendor.data.source.local.CompanyLocalDataSource;
import vn.needy.vendor.data.source.local.realm.RealmApi;
import vn.needy.vendor.data.source.remote.CategoryRemoteDataSource;
import vn.needy.vendor.data.source.remote.CompanyRemoteDataSource;
import vn.needy.vendor.data.source.remote.api.error.BaseException;
import vn.needy.vendor.data.source.remote.api.error.SafetyError;
import vn.needy.vendor.data.source.remote.api.service.VendorServiceClient;

/**
 * Created by lion on 03/11/2017.
 */

public class CategoriesPresenter implements CategoriesContract.Presenter {

    private final static String TAG = CategoriesPresenter.class.getName();

    private final CategoriesContract.ViewModel mViewModel;

    private final CompositeDisposable mCompositeDisposable;

    private final CategoryRepository mCategoryRepository;
    private final CompanyRepository mCompanyRepository;

    private String mCompanyId;

    public CategoriesPresenter(CategoriesContract.ViewModel viewModel, RealmApi realmApi) {
        mViewModel = viewModel;
        mCategoryRepository = new CategoryRepository(
                new CategoryRemoteDataSource(VendorServiceClient.getInstance())
        );
        mCompanyRepository = new CompanyRepository(
                new CompanyRemoteDataSource(VendorServiceClient.getInstance()),
                new CompanyLocalDataSource(realmApi));
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void onStart() {
        Disposable disposable = mCompanyRepository.getCompany()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Company>() {
                    @Override
                    public void accept(Company company) throws Exception {
                        mCompanyId = company.getId();
                        getListCategory();
                    }
                });
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void onStop() {

    }

    @Override
    public void getListCategory() {
        Disposable disposable = mCategoryRepository.getCategories(mCompanyId)
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
        Disposable disposable = mCategoryRepository.getSubCategories(category, mCompanyId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Category>>() {
                    @Override
                    public void accept(List<Category> categories) throws Exception {
                        if (categories.size() == 0) {
                            mViewModel.onGetProductOfCompany();
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
