package vn.needy.vendor.screen.addProduct.addAttribute;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import vn.needy.vendor.database.model.Attribute;
import vn.needy.vendor.screen.BaseRecyclerViewAdapter;

/**
 * Created by lion on 04/12/2017.
 */

public class ItemAttributeViewModel extends BaseObservable {

    private Attribute mAttribute;

    public ItemAttributeViewModel(Attribute attribute) {
        mAttribute = attribute;
    }

    @Bindable
    public String getAttributeTitle() {
        if (mAttribute != null) {
            return mAttribute.getName();
        }
        return "";
    }

    public void setAttributeValue(Object value) {
        if (mAttribute != null) {
            mAttribute.setValue(value);
        }
    }

    @Bindable
    public Object getAttributeValue() {
        if (mAttribute != null) {
            return mAttribute.getValue();
        }
        return new Object();
    }
}
