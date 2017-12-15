package vn.needy.vendor.screen.productProfile;

import android.content.Context;
import android.os.AsyncTask;
import android.text.TextUtils;

import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import vn.needy.vendor.model.Image;
import vn.needy.vendor.repository.remote.product.request.AddProductPnReq;

/**
 * Created by lion on 08/11/2017.
 */

public class AddProductPnPresenter implements AddProductPnContract.Presenter {

    private static final String TAG = AddProductPnPresenter.class.getName();

    private final Context mContext;
    private final AddProductPnContract.ViewModel mViewModel;

    private final CompositeDisposable mCompositeDisposable;

    public AddProductPnPresenter(Context context, AddProductPnContract.ViewModel viewModel) {
        mContext = context;
        mViewModel = viewModel;

        mCompositeDisposable = new CompositeDisposable();
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
    public boolean validateDataInput(AddProductPnReq request) {
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
