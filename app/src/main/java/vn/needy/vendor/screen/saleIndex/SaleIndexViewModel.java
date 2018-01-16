package vn.needy.vendor.screen.saleIndex;

import android.app.Activity;
import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;

import vn.needy.vendor.screen.main.MainActivity;
import vn.needy.vendor.screen.violateStatistic.ViolateStatisticFragment;

/**
 * Created by lion on 11/01/2018.
 */

public class SaleIndexViewModel extends BaseObservable implements SaleIndexContract.ViewModel{

    private Context mContext;
    private String mRatio;
    private float mIndexWeigh;
    private String mVoteSaleIndex;

    public SaleIndexViewModel(Context mContext) {
        this.mContext = mContext;

        mRatio = "57%";
        mIndexWeigh = 0;
        mVoteSaleIndex = "5.0/5.0";
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void setPresenter(SaleIndexContract.Presenter presenter) {

    }

    @Override
    public void onBackPressed(){
        ((Activity) mContext).onBackPressed();
    }

    @Bindable
    public String getRatio() {
        return mRatio;
    }

    @Bindable
    public String getIndexWeigh() {
        return String.format("%s", mIndexWeigh);
    }

    @Bindable
    public String getVoteSaleIndex() {
        return mVoteSaleIndex;
    }

    @Override
    public void onClickViolateStatistic(){
        ((SaleIndexActivity) mContext).initFragment(android.R.id.content , ViolateStatisticFragment.getInstance());
    }
}
