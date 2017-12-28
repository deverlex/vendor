package vn.needy.vendor.screen.createProduct;

import android.os.AsyncTask;
import android.util.Log;

import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import vn.needy.vendor.model.Image;
import vn.needy.vendor.port.error.BaseException;
import vn.needy.vendor.port.error.SafetyError;
import vn.needy.vendor.port.message.BaseCode;
import vn.needy.vendor.port.message.ResponseWrapper;
import vn.needy.vendor.port.service.VendorServiceClient;
import vn.needy.vendor.repository.CompanyRepository;
import vn.needy.vendor.repository.ProductRepository;
import vn.needy.vendor.repository.StoreRepository;
import vn.needy.vendor.repository.local.CompanyDataLocal;
import vn.needy.vendor.repository.local.ProductDataLocal;
import vn.needy.vendor.repository.local.StoreDataLocal;
import vn.needy.vendor.repository.remote.company.CompanyRemoteData;
import vn.needy.vendor.repository.remote.product.ProductDataRemote;
import vn.needy.vendor.repository.remote.product.request.AddProductPlReq;
import vn.needy.vendor.repository.remote.store.StoreDataRemote;
import vn.needy.vendor.utils.Constant;

/**
 * Created by lion on 28/11/2017.
 */

public class CreateProductPlPresenter implements CreateProductPlContract.Presenter{

    private CreateProductPlContract.ViewModel mViewModel;

    private final ProductRepository mProductRepository;
    private final CompanyRepository mCompanyRepository;
    private final StoreRepository mStoreRepository;

    public CreateProductPlPresenter(CreateProductPlContract.ViewModel viewModel) {
        this.mViewModel = viewModel;

        mProductRepository = new ProductRepository(
                new ProductDataRemote(VendorServiceClient.getInstance()),
                new ProductDataLocal()
        );
        mCompanyRepository = new CompanyRepository(
                new CompanyRemoteData(VendorServiceClient.getInstance()),
                new CompanyDataLocal()
        );
        mStoreRepository = new StoreRepository(
                new StoreDataRemote(VendorServiceClient.getInstance()),
                new StoreDataLocal()
        );
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void uploadProduct(final AddProductPlReq request, final List<Image> images) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                if (validateDataInput(request)) {
                    // convert images file to base64 string
                    List<String> base64Images = new LinkedList<>();
                    for (Image image : images) {
                        try {
                            base64Images.add(image.getBase64());
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                    // set content to request message
                    request.setImages(base64Images);

                    String companyId = mCompanyRepository.getCompanyIdSync();
                    String storeId = mStoreRepository.getOurStoreIdSync();
                    mProductRepository.addProduct(Constant.PRICE_LATER, companyId, storeId, request)
                            .subscribeOn(Schedulers.io())
                            .subscribe(new Consumer<ResponseWrapper>() {
                                @Override
                                public void accept(ResponseWrapper responseWrapper) throws Exception {
                                    if (responseWrapper.getCode() == BaseCode.OK) {
                                        mViewModel.onBackPressed();
                                    }
                                }
                            }, new SafetyError() {
                                @Override
                                public void onSafetyError(BaseException error) {

                                }
                            });
                } else {
                    // validateDataInput False
                }
            }
        });
    }

    @Override
    public boolean validateDataInput(AddProductPlReq request) {
        return true;
    }
}
