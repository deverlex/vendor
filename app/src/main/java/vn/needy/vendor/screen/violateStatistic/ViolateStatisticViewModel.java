package vn.needy.vendor.screen.violateStatistic;

import android.app.Activity;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingBuildInfo;
import android.support.v4.app.Fragment;

import vn.needy.vendor.screen.main.MainActivity;
import vn.needy.vendor.screen.saleIndex.SaleIndexActivity;

/**
 * Created by lion on 11/01/2018.
 */

public class ViolateStatisticViewModel extends BaseObservable implements ViolateStatisticContract.ViewModel{


    private int mViolateStatistic;
    private int mCounterfeiting;
    private int mParrot;
    private int mProductDisallowance;
    private int mRatioProsper;
    private int mRatioLate;
    private int mRatioUnreal;
    private int mProgress;
    private String mViolateDate;

    private Context mContext;
    private ViolateStatisticContract.Presenter mPresenter;

    public ViolateStatisticViewModel(Context mContext) {
        this.mContext = mContext;

        mViolateStatistic = 0;
        mCounterfeiting = 0;
        mParrot = 0;
        mProductDisallowance = 0;
        mRatioProsper = 1;
        mRatioLate = 0;
        mRatioUnreal = 2;
        mProgress = 23;
        mViolateDate = "1 th01 đến 1 th04";
    }

    @Override
    public void onBackPressed(){
        ((Activity) mContext).onBackPressed();
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void setPresenter(ViolateStatisticContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Bindable
    public String getViolateStatistic() {
        return mViolateStatistic + "";
    }

    @Bindable
    public String getCounterfeiting() {
        return mCounterfeiting +"";
    }

    @Bindable
    public String getParrot() {
        return mParrot + "";
    }

    @Bindable
    public String getProductDisallowance() {
        return mProductDisallowance +"";
    }

    @Bindable
    public String getRatioProsper() {
        return mRatioProsper + "";
    }

    @Bindable
    public String getRatioLate() {
        return mRatioLate + "";
    }

    @Bindable
    public String getRatioUnreal() {
        return mRatioUnreal + "";
    }

    @Bindable
    public int getProgress() {
        return mProgress ;
    }

    @Bindable
    public String getViolateDate() {
        return mViolateDate;
    }
}
