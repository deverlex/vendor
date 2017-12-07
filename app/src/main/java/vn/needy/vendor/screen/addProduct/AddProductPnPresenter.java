package vn.needy.vendor.screen.addProduct;

import android.content.Context;
import android.os.AsyncTask;
import android.text.TextUtils;

import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import vn.needy.vendor.database.model.Image;
import vn.needy.vendor.api.v1.product.ProductRepository;
import vn.needy.vendor.api.v1.product.ProductLocalDataSource;
import vn.needy.vendor.api.v1.product.ProductRemoteDataSource;
import vn.needy.vendor.api.v1.product.request.AddProductPnRequest;
import vn.needy.vendor.service.VendorServiceClient;

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
    public void uploadProduct(final AddProductPnRequest request, final List<Image> images) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
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
            }
        });
    }

    @Override
    public boolean validateDataInput(AddProductPnRequest request) {
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
