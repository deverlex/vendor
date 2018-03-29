package vn.needy.vendor.screen.storeProfile;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.location.Location;
import android.widget.TimePicker;

import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import ss.com.bannerslider.banners.Banner;
import vn.needy.vendor.BR;
import vn.needy.vendor.R;
import vn.needy.vendor.domain.Store;
import vn.needy.vendor.repository.remote.store.context.StoreContext;

/**
 * Created by lion on 12/12/2017.
 */

public class StoreProfileViewModel extends BaseObservable implements StoreProfileContract.ViewModel {

    private Context mContext;
    private StoreProfileContract.Presenter mPresenter;

    private List<Banner> mBanners;
    private boolean mEnable;
    private int mDrawableEdit;
    private StoreContext mStore;
    private String mAvatar;
    private int mNumberOfEmployee;
    private boolean mVisibleDescription;
    private int mDrawableExpandDescription;
    private String mNameError;
    private String mAddressError;

    StoreProfileViewModel(Context context) {
        mContext = context;
        mDrawableEdit = R.drawable.ic_edits_white;
        mDrawableExpandDescription = R.drawable.ic_next_right;
    }

    @Override
    public void onStart() {
        mPresenter.getStoreInfo();
    }

    @Override
    public void onStop() {

    }

    @Override
    public void setPresenter(StoreProfileContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Bindable
    public List<Banner> getBanners() {
        return mBanners;
    }

    @Bindable
    public int getDrawableEdit() {
        return mDrawableEdit;
    }

    @Bindable
    public StoreContext getStore() {
        return mStore;
    }

    @Bindable
    public boolean isVisibleDescription() {
        return mVisibleDescription;
    }

    @Bindable
    public int getDrawableExpandDescription() {
        return mDrawableExpandDescription;
    }

    @Bindable
    public String getNameError() {
        return mNameError;
    }

    @Bindable
    public String getAddressError() {
        return mAddressError;
    }

    @Bindable
    public boolean isEnable() {
        return mEnable;
    }

    @Bindable
    public String getNumberOfEmployee() {
        return String.valueOf(mNumberOfEmployee);
    }

    @Bindable
    public String getAvatar() {
        return mAvatar;
    }

    @Override
    public void setBanner(List<Banner> banners) {
        mBanners = banners;
        notifyPropertyChanged(BR.banners);
    }

    @Override
    public void onClickEdit() {
//        if (mEnable) {
//            boolean isValidate = mPresenter.validateDataInput(mStore.getName(), mStore.getAddress());
//            if (!isValidate) return;
//            // Update
//            mPresenter.updateStoreInfo(mStore);
//        }
//
//        mEnable = !mEnable;
//        notifyPropertyChanged(BR.enable);
//        mDrawableEdit = mEnable ? R.drawable.ic_check : R.drawable.ic_edits_white;
//        notifyPropertyChanged(BR.drawableEdit);
    }

    @Override
    public void setStoreInfo(StoreContext store, int numberOfEmployee) {
        mStore = store;
        mNumberOfEmployee = numberOfEmployee;
        notifyPropertyChanged(BR.store);
        notifyPropertyChanged(BR.numberOfEmployee);
    }

    @Override
    public void setAvatar(String avatarUrl) {
        mAvatar = avatarUrl;
        notifyPropertyChanged(BR.avatar);
    }

    @Override
    public void onClickDescription() {
        mVisibleDescription = !mVisibleDescription;
        notifyPropertyChanged(com.android.databinding.library.baseAdapters.BR.visibleDescription);

        mDrawableExpandDescription = mVisibleDescription ? R.drawable.ic_expand : R.drawable.ic_next_right;
        notifyPropertyChanged(com.android.databinding.library.baseAdapters.BR.drawableExpandDescription);
    }

    @Override
    public void onBackPressed() {
        ((Activity) mContext).onBackPressed();
    }

    @Override
    public void onClickOpeningTime() {
        TimePickerDialog timePickerDialog = new TimePickerDialog(mContext, R.style.DatePickerDialogTheme, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                Calendar calendar = new GregorianCalendar(0, 0, 0, hourOfDay, minute);
                SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
                mStore.setOpeningTime(dateFormat.format(calendar.getTime()));
                notifyPropertyChanged(BR.store);
            }
        }, 0, 0, true);
        timePickerDialog.show();
    }

    @Override
    public void onClickClosingTime() {
        TimePickerDialog timePickerDialog = new TimePickerDialog(mContext, R.style.DatePickerDialogTheme, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                Calendar calendar = new GregorianCalendar(0, 0, 0, hourOfDay, minute);
                SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
                mStore.setClosingTime(dateFormat.format(calendar.getTime()));
                notifyPropertyChanged(BR.store);
            }
        }, 0, 0, true);
        timePickerDialog.show();
    }

    @Override
    public void onInputNameError(String msg) {
        mNameError = msg;
        notifyPropertyChanged(BR.nameError);
    }

    @Override
    public void onInputAddressError(String msg) {
        mAddressError = msg;
        notifyPropertyChanged(BR.addressError);
    }
}
