package vn.needy.vendor.screen.category;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import vn.needy.vendor.datasource.category.CategoryDataSource;
import vn.needy.vendor.datasource.company.CompanyDataSource;
import vn.needy.vendor.model.Category;
import vn.needy.vendor.datasource.category.CategoryDataSourceImpl;
import vn.needy.vendor.datasource.company.CompanyDataSourceImpl;
import vn.needy.vendor.service.sharedprf.SharedPrefsApi;
import vn.needy.vendor.error.BaseException;
import vn.needy.vendor.error.SafetyError;
import vn.needy.vendor.utils.Constant;

/**
 * Created by lion on 03/11/2017.
 */

public class CategoriesPresenter implements CategoriesContract.Presenter {

    private final static String TAG = CategoriesPresenter.class.getName();

    private final CategoriesContract.ViewModel mViewModel;

    private final CompositeDisposable mCompositeDisposable;

    private final CategoryDataSource mCategoryDataSource;
    private final CompanyDataSource mCompanyDataSource;

    private SharedPrefsApi mPrefsApi;

    public CategoriesPresenter(CategoriesContract.ViewModel viewModel, SharedPrefsApi prefsApi) {
        mPrefsApi = prefsApi;
        mViewModel = viewModel;
        mCategoryDataSource = new CategoryDataSourceImpl();
        mCompanyDataSource = new CompanyDataSourceImpl();

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
        Disposable disposable = mCategoryDataSource.getCompanyCategories(Constant.PRICE_NOW)
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
        Disposable disposable = mCategoryDataSource.getCompanyCategories(Constant.PRICE_LATER)
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
        Disposable disposable = mCategoryDataSource.getCategories(Constant.PRICE_NOW)
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
        Disposable disposable = mCategoryDataSource.getCompanyCategories(category)
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
        Disposable disposable = mCategoryDataSource.getCategories(category)
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
