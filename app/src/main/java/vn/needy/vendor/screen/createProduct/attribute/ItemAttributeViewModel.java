package vn.needy.vendor.screen.createProduct.attribute;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.widget.Spinner;

import java.util.List;

import vn.needy.vendor.port.wrapper.AttributeWrapper;
import vn.needy.vendor.port.wrapper.AttributeWrapper.DataType;

/**
 * Created by lion on 04/12/2017.
 */

public class ItemAttributeViewModel extends BaseObservable {

    private static final String TAG = ItemAttributeViewModel.class.getName();

    private AttributeWrapper mAttribute;

    public ItemAttributeViewModel(AttributeWrapper attribute) {
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

    @Bindable
    public int getInputType() {
        if (mAttribute != null) {
            return mAttribute.getDataType();
        }
        return DataType.STRING;
    }

    @Bindable
    public List<Object> getValues() {
        return mAttribute.getValues();
    }

    public void onItemSelected(Spinner spinner) {
        mAttribute.setValue(mAttribute.getValues().get(spinner.getSelectedItemPosition()));
    }
}
