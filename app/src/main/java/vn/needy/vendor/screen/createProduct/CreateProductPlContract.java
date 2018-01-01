package vn.needy.vendor.screen.createProduct;

import java.util.List;

import vn.needy.vendor.model.Image;
import vn.needy.vendor.port.wrapper.CategoryWrapper;
import vn.needy.vendor.port.wrapper.ProductPnWrapper;
import vn.needy.vendor.repository.remote.product.request.AddProductPlRequest;
import vn.needy.vendor.screen.BasePresenter;
import vn.needy.vendor.screen.BaseViewModel;

/**
 * Created by lion on 28/11/2017.
 */

public interface CreateProductPlContract {

    interface ViewModel extends BaseViewModel<Presenter> {
        void onBackPressed();

        void onChooseCategory();

        void updateCategory(CategoryWrapper category);

        void onClickAddImage();

        void onSelectedListImages(List<Image> images);

        void onClickAddChildProduct();

        void onUpdateListChildProduct(List<ProductPnWrapper> productPns);

        void addFeeTransport();

        void onClickCreate();

        void onInputNameError(int msg);

        void onInputDescriptionError(int msg);

        void onInputCategoryError(int msg);
    }

    interface Presenter extends BasePresenter {
        void uploadProduct(AddProductPlRequest request, List<Image> images);

        boolean validateDataInput(AddProductPlRequest request);
    }
}
