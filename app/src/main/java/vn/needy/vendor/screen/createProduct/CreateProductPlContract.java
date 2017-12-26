package vn.needy.vendor.screen.createProduct;

import java.util.List;

import vn.needy.vendor.model.Image;
import vn.needy.vendor.model.wrapper.CategoryWrapper;
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
    }

    interface Presenter extends BasePresenter {
        
    }
}
