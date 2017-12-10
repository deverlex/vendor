package vn.needy.vendor.screen.addProduct;

import java.util.List;

import vn.needy.vendor.model.Attribute;
import vn.needy.vendor.model.Category;
import vn.needy.vendor.model.Image;
import vn.needy.vendor.repository.remote.product.request.AddProductPnRequest;
import vn.needy.vendor.screen.BasePresenter;
import vn.needy.vendor.screen.BaseViewModel;

/**
 * Created by lion on 08/11/2017.
 */

interface AddProductPnContract {

    interface ViewModel extends BaseViewModel<Presenter> {

        void onInputNameError(int msg);

        void onInputDescriptionError(int msg);

        void onInputQuantityError(int msg);

        void onInputPriceError(int msg);

        void onChooseCategory();

        void onClickAddImage();

        void onClickCreate();

        void onClickAddAttribute();

        void onClickAddPromotion();

        void onSelectedListImages(List<Image> images);

        void updateCategory(Category category);

        void onSelectedListAttribute(List<Attribute> attributes);
    }

    interface Presenter extends BasePresenter {

        void uploadProduct(AddProductPnRequest request, List<Image> images);

        boolean validateDataInput(AddProductPnRequest request);
    }
}
