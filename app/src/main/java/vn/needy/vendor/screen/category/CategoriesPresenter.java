package vn.needy.vendor.screen.category;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import vn.needy.vendor.model.wrapper.CategoryWrapper;
import vn.needy.vendor.port.api.VendorApi;
import vn.needy.vendor.port.error.BaseException;
import vn.needy.vendor.port.error.SafetyError;
import vn.needy.vendor.port.service.VendorServiceClient;
import vn.needy.vendor.repository.CategoryRepository;
import vn.needy.vendor.repository.CompanyRepository;
import vn.needy.vendor.repository.local.CompanyDataLocal;
import vn.needy.vendor.repository.remote.category.CategoryDataRemote;
import vn.needy.vendor.repository.remote.category.response.CategoriesResp;
import vn.needy.vendor.repository.remote.company.CompanyRemoteData;
import vn.needy.vendor.utils.Constant;

/**
 * Created by lion on 03/11/2017.
 */

public class CategoriesPresenter implements CategoriesContract.Presenter {

    private final static String TAG = CategoriesPresenter.class.getName();

    private final CategoriesContract.ViewModel mViewModel;

    private final CategoryRepository mCategoryRepository;
    private final CompanyRepository mCompanyRepository;
    private final String mCompanyId;

    public CategoriesPresenter(CategoriesContract.ViewModel viewModel, VendorApi vendorApi) {
        mViewModel = viewModel;
        mCategoryRepository = new CategoryRepository(
                new CategoryDataRemote(VendorServiceClient.getInstance())
        );
        mCompanyRepository = new CompanyRepository(
                new CompanyRemoteData(vendorApi),
                new CompanyDataLocal()
        );
        // get company ID
        mCompanyId = mCompanyRepository.getCompanyIdSync();
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {
    }

    @Override
    public void getCompanyCategoryPriceNow() {
        mCategoryRepository.getCompanyCategories(Constant.PRICE_NOW, mCompanyId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Consumer<CategoriesResp>() {
                @Override
                public void accept(CategoriesResp resp) throws Exception {
                    List<CategoryWrapper> categories = resp.getCategories();
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
    }

    @Override
    public void getCompanyCategoryPriceLater() {
        mCategoryRepository.getCompanyCategories(Constant.PRICE_LATER, mCompanyId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Consumer<CategoriesResp>() {
                @Override
                public void accept(CategoriesResp resp) throws Exception {
                    List<CategoryWrapper> categories = resp.getCategories();
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
    }

    @Override
    public void getCategoryPriceNow() {
        mCategoryRepository.getCategories(Constant.PRICE_NOW)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Consumer<CategoriesResp>() {
                @Override
                public void accept(CategoriesResp resp) throws Exception {
                    List<CategoryWrapper> categories = resp.getCategories();
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
    }

    @Override
    public void getCategoryPriceLater() {

    }

    @Override
    public void getCompanyCategories(String category) {
        mCategoryRepository.getCompanyCategories(category, mCompanyId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Consumer<CategoriesResp>() {
                @Override
                public void accept(CategoriesResp resp) throws Exception {
                    List<CategoryWrapper> categories = resp.getCategories();
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
    }

    @Override
    public void getCategories(String category) {
        mCategoryRepository.getCategories(category)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Consumer<CategoriesResp>() {
                @Override
                public void accept(CategoriesResp resp) throws Exception {
                    List<CategoryWrapper> categories = resp.getCategories();
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
    }

}
