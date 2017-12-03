package vn.needy.vendor.screen.addProduct;

import android.content.Context;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;

import java.io.File;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import vn.needy.vendor.database.model.Image;
import vn.needy.vendor.api.v1.product.ProductRepository;
import vn.needy.vendor.api.v1.product.ProductLocalDataSource;
import vn.needy.vendor.api.v1.product.ProductRemoteDataSource;
import vn.needy.vendor.error.BaseException;
import vn.needy.vendor.error.SafetyError;
import vn.needy.vendor.api.v1.product.request.AddProductRequest;
import vn.needy.vendor.api.base.BaseResponse;
import vn.needy.vendor.service.VendorServiceClient;
import vn.needy.vendor.utils.image.CompressImage;

/**
 * Created by lion on 08/11/2017.
 */

public class AddProductPnPresenter implements AddProductPnContract.Presenter {

    private static final String TAG = AddProductPnPresenter.class.getName();

    private final Context mContext;
    private final AddProductPnContract.ViewModel mViewModel;

    private final CompositeDisposable mCompositeDisposable;
    private final ProductRepository mProductRepository;

    public AddProductPnPresenter(Context context, AddProductPnContract.ViewModel viewModel) {
        mContext = context;
        mViewModel = viewModel;

        mCompositeDisposable = new CompositeDisposable();
        mProductRepository = new ProductRepository(new ProductLocalDataSource(),
                new ProductRemoteDataSource(VendorServiceClient.getInstance()));
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void uploadProduct(AddProductRequest request) {

    }

    @Override
    public void uploadImage(List<Image> images) {
        if (images.size() > 0) {
            Image image = images.get(0);
            File file = new File(CompressImage.compressImage(image.getUrl()));
            RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), file);
            final MultipartBody.Part part = MultipartBody.Part.createFormData("multiparty", file.getName(), reqFile);
            final long productId = 11223122132131L;
            AsyncTask.execute(new Runnable() {
                @Override
                public void run() {
                    Disposable disposable = mProductRepository.uploadImage(productId, part)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Consumer<BaseResponse>() {
                                @Override
                                public void accept(BaseResponse baseResponse) throws Exception {
                                    Log.w(TAG, "accept? " + baseResponse.getMessage());
                                }
                            }, new SafetyError() {
                                @Override
                                public void onSafetyError(BaseException error) {
                                    Log.w(TAG, "onSafetyError? " + error.getMessage());
                                }
                            });
                    mCompositeDisposable.add(disposable);
                }
            });
        }

    }

    @Override
    public boolean validateDataInput(AddProductRequest request) {
        boolean isValidate = true;
        if (TextUtils.isEmpty(request.getName())) {

            isValidate = false;
        } else if (TextUtils.isEmpty(request.getDescription())
                && request.getDescription().length() < 120) {

            isValidate = false;
        } else if (request.getQuantity() < 1) {

            isValidate = false;
        } else if (request.getPrice() <= 0f) {

            isValidate = false;
        }
        return isValidate;
    }
}