package vn.needy.vendor.screen.mainPage.priceNow;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import vn.needy.vendor.port.error.BaseException;
import vn.needy.vendor.port.error.SafetyError;
import vn.needy.vendor.port.message.ResponseWrapper;
import vn.needy.vendor.port.service.VendorServiceClient;
import vn.needy.vendor.repository.CompanyRepository;
import vn.needy.vendor.repository.ProductRepository;
import vn.needy.vendor.repository.local.CompanyDataLocal;
import vn.needy.vendor.repository.local.ProductDataLocal;
import vn.needy.vendor.repository.remote.company.CompanyRemoteData;
import vn.needy.vendor.repository.remote.product.ProductDataRemote;
import vn.needy.vendor.repository.remote.product.respone.ProductPnInfoResponse;

/**
 * Created by truongpq on 02/01/2018.
 */

public class MainPagePnPresenter implements MainPagePnContract.Presenter {
    private MainPagePnContract.ViewModel mViewModel;

    private ProductRepository mProductRepository;
    private CompanyRepository mCompanyRepository;

    public MainPagePnPresenter(MainPagePnContract.ViewModel viewModel) {
        this.mViewModel = viewModel;

        mProductRepository = new ProductRepository(
                new ProductDataRemote(VendorServiceClient.getInstance()),
                new ProductDataLocal()
        );
        mCompanyRepository = new CompanyRepository(
                new CompanyRemoteData(VendorServiceClient.getInstance()),
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
