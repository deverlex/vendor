package vn.needy.vendor.screen.createProduct;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import vn.needy.vendor.port.wrapper.AttributeWrapper;

/**
 * Created by lion on 04/12/2017.
 */

public class ItemAttributeProductPnViewModel extends BaseObservable {

    private AttributeWrapper mAttribute;

    public ItemAttributeProductPnViewModel(AttributeWrapper attribute) {
        mAttribute = attribute;
    }

    @Bindable
    public String getAttributeTitle() {
        return mAttribute.getName();
    }

    @Bindable
    public Object getAttributeValue() {
        return mAttribute.getValue();
    }
}
