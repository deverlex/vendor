package vn.needy.vendor.screen.addProduct.addAttribute;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;

import java.util.List;

import vn.needy.vendor.database.model.Attribute;
import vn.needy.vendor.database.model.Category;

/**
 * Created by lion on 04/12/2017.
 */

public class AddAttributeViewModel extends BaseObservable implements AddAttributeContract.ViewModel {

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

    }

    @Override
    public void onDoneClicked() {

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
