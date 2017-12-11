package vn.needy.vendor.screen.category;

import vn.needy.vendor.port.api.VendorApi;

/**
 * Created by lion on 03/11/2017.
 */

public class CategoriesPresenter implements CategoriesContract.Presenter {

    private final static String TAG = CategoriesPresenter.class.getName();

    private final CategoriesContract.ViewModel mViewModel;

//    private final CategoryRepository mCategoryRepository;
//    private final CompanyRepository mCompanyRepository;

    public CategoriesPresenter(CategoriesContract.ViewModel viewModel, VendorApi vendorApi) {
        mViewModel = viewModel;
//        mCategoryRepository = new CategoryRepository();
//        mCompanyRepository = new CompanyRepository(
//                new CompanyRemoteData(vendorApi),
//                new CompanyDataLocal(realmApi)
//        );
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {
    }

    @Override
    public void getCompanyCategoryPriceNow() {
//        mCategoryRepository.getCompanyCategories(Constant.PRICE_NOW)
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(new Consumer<List<Category>>() {
//                @Override
//                public void accept(List<Category> categories) throws Exception {
//                    if (categories != null && categories.size() > 0) {
//                        mViewModel.onUpdateListCategory(categories);
//                    } else if (categories == null) {
//                        mViewModel.backActivity();
//                    }
//                }
//            }, new SafetyError() {
//                @Override
//                public void onSafetyError(BaseException error) {
//                    mViewModel.onUpdateListCategoryError(error);
//                }
//            });
    }

    @Override
    public void getCompanyCategoryPriceLater() {
//        mCategoryRepository.getCompanyCategories(Constant.PRICE_LATER)
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(new Consumer<List<Category>>() {
//                @Override
//                public void accept(List<Category> categories) throws Exception {
//                    if (categories != null && categories.size() > 0) {
//                        mViewModel.onUpdateListCategory(categories);
//                    } else if (categories == null) {
//                        mViewModel.backActivity();
//                    }
//                }
//            }, new SafetyError() {
//                @Override
//                public void onSafetyError(BaseException error) {
//                    mViewModel.onUpdateListCategoryError(error);
//                }
//            });
    }

    @Override
    public void getCategoryPriceNow() {
//        mCategoryRepository.getCategories(Constant.PRICE_NOW)
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(new Consumer<List<Category>>() {
//                @Override
//                public void accept(List<Category> categories) throws Exception {
//                    if (categories != null && categories.size() > 0) {
//                        mViewModel.onUpdateListCategory(categories);
//                    } else if (categories == null) {
//                        mViewModel.backActivity();
//                    }
//                }
//            }, new SafetyError() {
//                @Override
//                public void onSafetyError(BaseException error) {
//                    mViewModel.onUpdateListCategoryError(error);
//                }
//            });
    }

    @Override
    public void getCategoryPriceLater() {

    }

    @Override
    public void getCompanyCategories(String category) {
//        mCategoryRepository.getCompanyCategories(category)
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(new Consumer<List<Category>>() {
//                @Override
//                public void accept(List<Category> categories) throws Exception {
//                    if (categories != null && categories.size() > 0) {
//                        mViewModel.onUpdateListCategory(categories);
//                    } else if (categories == null || categories.size() == 0) {
//                        mViewModel.backActivity();
//                    }
//                }
//            }, new SafetyError() {
//                @Override
//                public void onSafetyError(BaseException error) {
//                    mViewModel.onUpdateListCategoryError(error);
//                }
//            });
    }

    @Override
    public void getCategories(String category) {
//        mCategoryRepository.getCategories(category)
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(new Consumer<List<Category>>() {
//                @Override
//                public void accept(List<Category> categories) throws Exception {
//                    if (categories != null && categories.size() > 0) {
//                        mViewModel.onUpdateListCategory(categories);
//                    } else if (categories == null || categories.size() == 0) {
//                        mViewModel.backActivity();
//                    }
//                }
//            }, new SafetyError() {
//                @Override
//                public void onSafetyError(BaseException error) {
//                    mViewModel.onUpdateListCategoryError(error);
//                }
//            });
    }

}
