package vn.needy.vendor.screen.businessManage;

import android.app.Activity;
import android.content.Context;
import android.databinding.BaseObservable;

/**
 * Created by lion on 10/01/2018.
 */

public class BusinessManageViewModel extends BaseObservable implements BusinessManageContract.ViewModel {

    private Context mContext;
    private BusinessManageContract.Presenter mPresenter;

    public BusinessManageViewModel(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void setPresenter(BusinessManageContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onBackPressed() {
        ((Activity) mContext).onBackPressed();
    }
}