package vn.needy.vendor.screen.createProduct;

import android.content.Context;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;

import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import vn.needy.vendor.R;
import vn.needy.vendor.model.Image;
import vn.needy.vendor.port.api.VendorApi;
import vn.needy.vendor.port.error.BaseException;
import vn.needy.vendor.port.error.SafetyError;
import vn.needy.vendor.port.message.BaseCode;
import vn.needy.vendor.port.message.ResponseWrapper;
import vn.needy.vendor.repository.CompanyRepository;
import vn.needy.vendor.repository.ProductRepository;
import vn.needy.vendor.repository.StoreRepository;
import vn.needy.vendor.repository.local.CompanyDataLocal;
import vn.needy.vendor.repository.local.ProductDataLocal;
import vn.needy.vendor.repository.local.StoreDataLocal;
import vn.needy.vendor.repository.remote.company.CompanyRemoteData;
import vn.needy.vendor.repository.remote.product.ProductDataRemote;
import vn.needy.vendor.repository.remote.product.request.AddProductPnReq;
import vn.needy.vendor.repository.remote.store.StoreDataRemote;

/**
 * Created by lion on 08/11/2017.
 */

public class CreateProductPnPresenter implements CreateProductPnContract.Presenter {

    private static final String TAG = CreateProductPnPresenter.class.getName();

    private final Context mContext;
    private final CreateProductPnContract.ViewModel mViewModel;

    private final CompositeDisposable mCompositeDisposable;

    private final ProductRepository mProductRepository;
    private final CompanyRepository mCompanyRepository;
    private final StoreRepository mStoreRepository;

    public CreateProductPnPresenter(Context context, CreateProductPnContract.ViewModel viewModel, VendorApi vendorApi) {
        mContext = context;
        mViewModel = viewModel;

        mCompositeDisposable = new CompositeDisposable();

        mProductRepository = new ProductRepository(
                new ProductDataRemote(vendorApi),
                new ProductDataLocal()
        );
        mCompanyRepository = new CompanyRepository(
                new CompanyRemoteData(vendorApi),
                new CompanyDataLocal()
        );
        mStoreRepository = new StoreRepository(
                new StoreDataRemote(vendorApi),
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
    public void uploadProduct(final AddProductPnReq request, final List<Image> images) {
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

                    mProductRepository.addProductPn(companyId, storeId, request)
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
    public boolean validateDataInput(AddProductPnReq request) {
        boolean isValidate = true;
        if (TextUtils.isEmpty(request.getName())) {
            mViewModel.onInputNameError(R.string.name_product_empty);
            isValidate = false;
        } else if (TextUtils.isEmpty(request.getDescription())
                || request.getDescription().length() < 60) {
            mViewModel.onInputDescriptionError(R.string.description_product_higher_60);
            isValidate = false;
        } else if (request.getCategory() == null) {
            mViewModel.onInputCategoryError(R.string.product_category_empty);
            isValidate = false;
        } else if (request.getPrice() <= 0f) {
            mViewModel.onInputPriceError(R.string.price_product_empty);
            isValidate = false;
        } else if (request.getQuantity() < 1) {
            mViewModel.onInputQuantityError(R.string.quantity_prduct_empty);
            isValidate = false;
        } else if (request.getAttributes().size() == 0) {
            mViewModel.onInputAttributesError(R.string.product_attributes_empty);
            isValidate = false;
        }
        return isValidate;
    }
}
