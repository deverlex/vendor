package vn.needy.vendor.screen.addProduct.addAttribute;

import android.app.Activity;
import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import vn.needy.vendor.database.model.Attribute;
import vn.needy.vendor.database.model.Category;
import vn.needy.vendor.screen.addProduct.AddProductPnActivity;

/**
 * Created by lion on 04/12/2017.
 */

public class AddAttributeViewModel extends BaseObservable implements AddAttributeContract.ViewModel {

    private static final String TAG = AddAttributeViewModel.class.getName();

    private AddAttributeContract.Presenter mPresenter;
    private Context mContext;
    private AttributeAdapter mAttributeAdapter;

    private Category mCategory;

    public AddAttributeViewModel(Context context, AttributeAdapter attributeAdapter, Category category) {
        mContext = context;
        mAttributeAdapter = attributeAdapter;
        mCategory = category;
    }

    @Override
    public void onStart() {
        mPresenter.onGetListAttributes(mCategory);
    }

    @Override
    public void onStop() {

    }

    @Override
    public void setPresenter(AddAttributeContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onBackClicked() {
        ((Activity) mContext).onBackPressed();
    }

    @Override
    public void onDoneClicked() {
        ArrayList<Attribute> attributes =  new ArrayList<>(mAttributeAdapter.getAttributes());
        for (Attribute attribute : attributes) {
            if (attribute.getValue() == null) {
                // show notify
                Log.w(TAG, "All attributes are required complete");
                return;
            }
        }
        Bundle extras = new Bundle();
        extras.putParcelableArrayList(
                AddAttributeFragment.class.getSimpleName(),
                attributes
        );
        ((AddProductPnActivity) mContext).onUpdateListAttribute(attributes);
        ((Activity) mContext).onBackPressed();
    }

    @Override
    public void onListAttributeLoaded(List<Attribute> attributes) {
        mAttributeAdapter.updateData(attributes);
    }

    @Bindable
    public AttributeAdapter getAttributeAdapter() {
        return mAttributeAdapter;
    }

}
