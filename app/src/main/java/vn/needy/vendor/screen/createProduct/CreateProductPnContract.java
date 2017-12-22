package vn.needy.vendor.screen.createProduct;

import java.util.List;

import vn.needy.vendor.model.wrapper.AttributeWrapper;
import vn.needy.vendor.model.wrapper.CategoryWrapper;
import vn.needy.vendor.model.Image;
import vn.needy.vendor.repository.remote.product.request.AddProductPnReq;
import vn.needy.vendor.screen.BasePresenter;
import vn.needy.vendor.screen.BaseViewModel;

/**
 * Created by lion on 08/11/2017.
 */

interface CreateProductPnContract {

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

        void updateCategory(CategoryWrapper category);

        void onSelectedListAttribute(List<AttributeWrapper> attributes);

        void onBackPressed();
    }

    interface Presenter extends BasePresenter {

        void uploadProduct(AddProductPnReq request, List<Image> images);

        boolean validateDataInput(AddProductPnReq request);
    }
}
