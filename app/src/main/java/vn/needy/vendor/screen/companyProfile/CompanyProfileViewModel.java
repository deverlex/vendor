package vn.needy.vendor.screen.companyProfile;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.location.Location;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.android.databinding.library.baseAdapters.BR;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import io.realm.RealmList;
import ss.com.bannerslider.banners.Banner;
import vn.needy.vendor.R;
import vn.needy.vendor.domain.Company;
import vn.needy.vendor.domain.FeeTransport;
import vn.needy.vendor.model.Place;
import vn.needy.vendor.repository.remote.company.context.CompanyContext;
import vn.needy.vendor.screen.BaseRecyclerViewAdapter;
import vn.needy.vendor.screen.place.PlaceActivity;
import vn.needy.vendor.utils.navigator.Navigator;

/**
 * Created by truongpq on 04/12/2017.
 */

public class CompanyProfileViewModel extends BaseObservable implements CompanyProfileContract.ViewModel{
    private CompanyProfileContract.Presenter mPresenter;
    private Navigator mNavigator;
    private Context mContext;
    private List<Banner> mBanners;
    private boolean mEnable;
    private int mDrawableEdit;
    private CompanyContext mCompany;
    private int mNumberOfEmployee;
    private int mNumberOfStore;
    private String mNameError;
    private String mAddressError;
    private boolean mVisibleDescription;
    private int mDrawableExpandDescription;

    public CompanyProfileViewModel(Context context, Navigator navigator) {
        this.mContext = context;
        mNavigator = navigator;
        mDrawableEdit = R.drawable.ic_edits_white;
        mDrawableExpandDescription = R.drawable.ic_next_right;
    }

    @Override
    public void onStart() {
        mPresenter.getCoverPictures();
        mPresenter.getCompanyInfo();
    }

    @Override
    public void onStop() {

    }

    @Override
    public void setPresenter(CompanyProfileContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void setBanners(List<Banner> banners) {
        mBanners = banners;
        notifyPropertyChanged(BR.banners);
    }

    @Override
    public void onClickEdit() {
//        if (mEnable) {
//           boolean isValidate = mPresenter.validateDataInput(mCompany.getName(), mCompany.getAddress());
//           if (!isValidate) return;
//
//           mPresenter.updateCompanyInfo(mCompany, mRemoveFeeTransportIds);
//           mFeeTransportAdapter.notifyDataSetChanged();
//        }

        mEnable = !mEnable;
        notifyPropertyChanged(BR.enable);
        mDrawableEdit = mEnable ? R.drawable.ic_check : R.drawable.ic_edits_white;
        notifyPropertyChanged(BR.drawableEdit);

    }

    @Override
    public void setCompanyInfo(CompanyContext company, int numberOfEmployee, int numberOfStore) {
        mCompany = company;
        mNumberOfEmployee = numberOfEmployee;
        mNumberOfStore = numberOfStore;
        notifyPropertyChanged(BR.company);
        notifyPropertyChanged(BR.numberOfEmployee);
        notifyPropertyChanged(BR.numberOfStore);
    }

    @Override
    public void onInputNameError(String errorMsg) {
        mNameError = errorMsg;
        notifyPropertyChanged(BR.nameError);
    }

    @Override
    public void onInputAddressError(String msg) {
        mAddressError = msg;
        notifyPropertyChanged(BR.addressError);
    }

    @Override
    public void onBackPressed() {
        ((Activity) mContext).onBackPressed();
    }

    @Override
    public void onClickFoundedDate() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(mContext, R.style.DatePickerDialogTheme, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Calendar calendar = new GregorianCalendar(year, month, dayOfMonth);
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
                mCompany.setFoundedDate(dateFormat.format(calendar.getTime()));
                notifyPropertyChanged(BR.company);
            }
        }, 2017, 11, 6);
        datePickerDialog.getDatePicker().setSpinnersShown(true);
        datePickerDialog.getDatePicker().setCalendarViewShown(false);
        datePickerDialog.show();
    }

    @Override
    public void onClickOpeningTime() {
        TimePickerDialog timePickerDialog = new TimePickerDialog(mContext, R.style.DatePickerDialogTheme, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                Calendar calendar = new GregorianCalendar(0, 0, 0, hourOfDay, minute);
                SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
                mCompany.setOpeningTime(dateFormat.format(calendar.getTime()));
                notifyPropertyChanged(BR.company);
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
                mCompany.setClosingTime(dateFormat.format(calendar.getTime()));
                notifyPropertyChanged(BR.company);
            }
        }, 0, 0, true);
        timePickerDialog.show();
    }

    @Override
    public void onClickDescription() {
        mVisibleDescription = !mVisibleDescription;
        notifyPropertyChanged(BR.visibleDescription);

        mDrawableExpandDescription = mVisibleDescription ? R.drawable.ic_expand : R.drawable.ic_next_right;
        notifyPropertyChanged(BR.drawableExpandDescription);
    }

    @Override
    public void updateAddress(Place place) {
        mCompany.setAddress(place.getAddress());
        notifyPropertyChanged(BR.company);
    }

    @Override
    public void onPlaceClick() {
        mNavigator.startActivityForResult(PlaceActivity.class, CompanyProfileActivity.ADDRESS);
    }

    @Bindable
    public List<Banner> getBanners() {
        return mBanners;
    }

    @Bindable
    public boolean getEnable() {
        return mEnable;
    }

    @Bindable
    public int getDrawableEdit() {
        return mDrawableEdit;
    }

    @Bindable
    public CompanyContext getCompany() {
        return mCompany;
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
    public boolean isVisibleDescription() {
        return mVisibleDescription;
    }

    @Bindable
    public int getDrawableExpandDescription() {
        return mDrawableExpandDescription;
    }

    @Bindable
    public String getNumberOfEmployee() {
        return String.valueOf(mNumberOfEmployee);
    }

    @Bindable
    public String getNumberOfStore() {
        return String.valueOf(mNumberOfStore);
    }
}
