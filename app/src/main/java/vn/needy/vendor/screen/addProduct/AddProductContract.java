package vn.needy.vendor.screen.addProduct;

import java.util.List;

import vn.needy.vendor.database.model.Image;
import vn.needy.vendor.api.v1.product.request.AddProductRequest;
import vn.needy.vendor.screen.BasePresenter;
import vn.needy.vendor.screen.BaseViewModel;

/**
 * Created by lion on 08/11/2017.
 */

public interface AddProductContract {

    interface ViewModel extends BaseViewModel<Presenter> {

        void onInputNameError(int msg);

        void onInputDescriptionError(int msg);

        void onInputQuantityError(int msg);

        void onInputPriceError(int msg);

        void onClickAddImage();

        void onClickCreate();

        void onClickAddAttribute();

        void onClickAddPromotion();

        void onSelectedListImages(List<Image> images);
    }

    interface Presenter extends BasePresenter {

        void uploadProduct(AddProductRequest request);

        void uploadImage(List<Image> images);

        boolean validateDataInput(AddProductRequest request);
    }
}
