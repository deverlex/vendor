package vn.needy.vendor.screen.createProduct.attribute;

import java.util.List;

import vn.needy.vendor.model.wrapper.AttributeWrapper;
import vn.needy.vendor.model.wrapper.CategoryWrapper;
import vn.needy.vendor.screen.BasePresenter;
import vn.needy.vendor.screen.BaseViewModel;

/**
 * Created by lion on 04/12/2017.
 */

interface AttributeContract {

    interface ViewModel extends BaseViewModel<Presenter> {
        void onBackClicked();

        void onDoneClicked();

        void onListAttributeLoaded(List<AttributeWrapper> attributes);
    }

    interface Presenter extends BasePresenter {
        void onGetListAttributes(CategoryWrapper category);

        boolean onValidateDataInput(List<Object> attrs);
    }
}
