package vn.needy.vendor.screen.addProduct.addAttribute;

import android.content.Context;
import android.databinding.BaseObservable;

/**
 * Created by lion on 04/12/2017.
 */

public class AddAttributeViewModel extends BaseObservable implements AddAttributeContract.ViewModel {

    private Context mContext;
    private AttributeAdapter mAttributeAdapter;

    public AddAttributeViewModel(Context context, AttributeAdapter attributeAdapter) {
        mContext = context;
        mAttributeAdapter = attributeAdapter;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void setPresenter(AddAttributeContract.Presenter presenter) {

    }

    @Override
    public void onBackClicked() {

    }

    @Override
    public void onDoneClicked() {

    }


}
