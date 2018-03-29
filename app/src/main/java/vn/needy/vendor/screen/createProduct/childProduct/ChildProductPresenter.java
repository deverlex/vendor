package vn.needy.vendor.screen.createProduct.childProduct;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import vn.needy.vendor.port.api.VendorApi;
import vn.needy.vendor.port.error.BaseException;
import vn.needy.vendor.port.error.SafetyError;
import vn.needy.vendor.port.message.ResponseWrapper;
import vn.needy.vendor.repository.CompanyRepository;
import vn.needy.vendor.repository.ProductRepository;
import vn.needy.vendor.repository.local.CompanyDataLocal;
import vn.needy.vendor.repository.local.ProductDataLocal;
import vn.needy.vendor.repository.remote.company.CompanyRemoteData;
import vn.needy.vendor.repository.remote.product.ProductDataRemote;
import vn.needy.vendor.repository.remote.product.respone.ProductPnInfoResponse;
import vn.needy.vendor.utils.Constant;

/**
 * Created by lion on 08/12/2017.
 */

public class ChildProductPresenter implements ChildProductContract.Presenter {

    private ChildProductContract.ViewModel mViewModel;
    private ProductRepository mProductRepository;
    private CompanyRepository mCompanyRepository;

    public ChildProductPresenter(ChildProductContract.ViewModel viewModel, VendorApi vendorApi) {
        mViewModel = viewModel;
        mProductRepository = new ProductRepository(
                new ProductDataRemote(vendorApi),
                new ProductDataLocal()
        );
        mCompanyRepository = new CompanyRepository(
                new CompanyRemoteData(vendorApi),
                new CompanyDataLocal()
        );
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void getProducts() {
        // Get All Product of company
        String companyId = mCompanyRepository.getCompanyIdSync();
        mProductRepository.getProductsPnOfCompany(companyId, Constant.PRICE_NOW)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseWrapper<ProductPnInfoResponse>>() {
                    @Override
                    public void accept(ResponseWrapper<ProductPnInfoResponse> productPnInfoRespResponseWrapper) throws Exception {
                        mViewModel.onUpdateProducts(productPnInfoRespResponseWrapper.getData().getProducts());
                    }
                }, new SafetyError() {
                    @Override
                    public void onSafetyError(BaseException error) {

                    }
                });
    }

    @Override
    public void getProductByCategory(String category) {
        String companyId = mCompanyRepository.getCompanyIdSync();
        mProductRepository.getProductsPnOfCompany(companyId, category)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseWrapper<ProductPnInfoResponse>>() {
                    @Override
                    public void accept(ResponseWrapper<ProductPnInfoResponse> productPnInfoRespResponseWrapper) throws Exception {
                        mViewModel.onUpdateProducts(productPnInfoRespResponseWrapper.getData().getProducts());
                    }
                }, new SafetyError() {
                    @Override
                    public void onSafetyError(BaseException error) {

                    }
                });
    }
}
