package vn.needy.vendor.screen.storeProfile;

import android.content.Context;
import android.databinding.BaseObservable;

/**
 * Created by lion on 12/12/2017.
 */

public class StoreProfileViewModel extends BaseObservable implements StoreProfileContract.ViewModel {

    private Context mContext;
    private StoreProfileContract.Presenter mPresenter;

    StoreProfileViewModel(Context context) {
        mContext = context;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void setPresenter(StoreProfileContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
