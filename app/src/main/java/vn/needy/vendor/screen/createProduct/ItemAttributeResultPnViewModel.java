package vn.needy.vendor.screen.createProduct;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.widget.Spinner;

import java.util.List;

import vn.needy.vendor.model.wrapper.AttributeWrapper;
import vn.needy.vendor.model.wrapper.AttributeWrapper.DataType;

/**
 * Created by lion on 04/12/2017.
 */

public class ItemAttributeResultPnViewModel extends BaseObservable {

    private AttributeWrapper mAttribute;

    public ItemAttributeResultPnViewModel(AttributeWrapper attribute) {
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
