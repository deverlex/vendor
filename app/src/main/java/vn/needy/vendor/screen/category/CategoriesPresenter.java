package vn.needy.vendor.screen.category;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import vn.needy.vendor.api.v1.category.CategoryRepository;
import vn.needy.vendor.api.v1.company.CompanyRepository;
import vn.needy.vendor.database.model.Category;
import vn.needy.vendor.api.v1.category.CategoryRepositoryImpl;
import vn.needy.vendor.api.v1.company.CompanyRepositoryImpl;
import vn.needy.vendor.database.sharedprf.SharedPrefsApi;
import vn.needy.vendor.database.sharedprf.SharedPrefsImpl;
import vn.needy.vendor.error.BaseException;
import vn.needy.vendor.error.SafetyError;
import vn.needy.vendor.service.VendorServiceClient;
import vn.needy.vendor.utils.Constant;

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

    public CategoriesPresenter(CategoriesContract.ViewModel viewModel, SharedPrefsApi prefsApi) {
        mPrefsApi = prefsApi;
        mViewModel = viewModel;
        mCategoryRepository = new CategoryRepositoryImpl(
                VendorServiceClient.getInstance(),
                SharedPrefsImpl.getInstance()
        );
        mCompanyRepository = new CompanyRepositoryImpl(
                VendorServiceClient.getInstance(),
                SharedPrefsImpl.getInstance()
        );

        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {
        mCompositeDisposable.dispose();
    }

    @Override
    public void getCompanyLinkCategoryPriceNow() {
        Disposable disposable = mCategoryRepository.getCompanyLinkCategories(Constant.PRICE_NOW)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Category>>() {
                    @Override
                    public void accept(List<Category> categories) throws Exception {
                        if (categories != null && categories.size() > 0) {
                            mViewModel.onUpdateListCategory(categories);
                        } else if (categories == null) {
                            mViewModel.backActivity();
                        }
                    }
                }, new SafetyError() {
                    @Override
                    public void onSafetyError(BaseException error) {
                        mViewModel.onUpdateListCategoryError(error);
                    }
                });
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void getCompanyLinkCategoryPriceLater() {
        Disposable disposable = mCategoryRepository.getCompanyLinkCategories(Constant.PRICE_LATER)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Category>>() {
                    @Override
                    public void accept(List<Category> categories) throws Exception {
                        if (categories != null && categories.size() > 0) {
                            mViewModel.onUpdateListCategory(categories);
                        } else if (categories == null) {
                            mViewModel.backActivity();
                        }
                    }
                }, new SafetyError() {
                    @Override
                    public void onSafetyError(BaseException error) {
                        mViewModel.onUpdateListCategoryError(error);
                    }
                });
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void getLinkCategoryPriceNow() {
        Disposable disposable = mCategoryRepository.getLinkCategories(Constant.PRICE_NOW)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Category>>() {
                    @Override
                    public void accept(List<Category> categories) throws Exception {
                        if (categories != null && categories.size() > 0) {
                            mViewModel.onUpdateListCategory(categories);
                        } else if (categories == null) {
                            mViewModel.backActivity();
                        }
                    }
                }, new SafetyError() {
                    @Override
                    public void onSafetyError(BaseException error) {
                        mViewModel.onUpdateListCategoryError(error);
                    }
                });
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void getLinkCategoryPriceLater() {

    }

    @Override
    public void getCompanyLinkCategories(String category) {
        Disposable disposable = mCategoryRepository.getCompanyLinkCategories(category)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Category>>() {
                    @Override
                    public void accept(List<Category> categories) throws Exception {
                        if (categories != null && categories.size() > 0) {
                            mViewModel.onUpdateListCategory(categories);
                        } else if (categories == null || categories.size() == 0) {
                            mViewModel.backActivity();
                        }
                    }
                }, new SafetyError() {
                    @Override
                    public void onSafetyError(BaseException error) {
                        mViewModel.onUpdateListCategoryError(error);
                    }
                });
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void getLinkCategories(String category) {
        Disposable disposable = mCategoryRepository.getLinkCategories(category)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Category>>() {
                    @Override
                    public void accept(List<Category> categories) throws Exception {
                        if (categories != null && categories.size() > 0) {
                            mViewModel.onUpdateListCategory(categories);
                        } else if (categories == null || categories.size() == 0) {
                            mViewModel.backActivity();
                        }
                    }
                }, new SafetyError() {
                    @Override
                    public void onSafetyError(BaseException error) {
                        mViewModel.onUpdateListCategoryError(error);
                    }
                });
        mCompositeDisposable.add(disposable);
    }

}
