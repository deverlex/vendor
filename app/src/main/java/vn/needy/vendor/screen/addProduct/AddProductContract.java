package vn.needy.vendor.screen.addProduct;

import java.util.List;

import vn.needy.vendor.data.model.Image;
import vn.needy.vendor.screen.BasePresenter;
import vn.needy.vendor.screen.BaseViewModel;

/**
 * Created by lion on 08/11/2017.
 */

public interface AddProductContract {

    interface ViewModel extends BaseViewModel<Presenter> {
        void onClickAddImage();

        void onClickCreate();

        void onClickAddAttribute();

        void onClickAddPromotion();

        void onSelectedListImages(List<Image> images);
    }

    interface Presenter extends BasePresenter {

    }
}
