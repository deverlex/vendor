package vn.needy.vendor.screen.registerCompany;

import android.app.TimePickerDialog;
import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.annotation.NonNull;
import android.widget.TimePicker;

import com.android.databinding.library.baseAdapters.BR;
import com.google.android.gms.location.places.PlaceDetectionClient;
import com.google.android.gms.location.places.PlaceLikelihoodBufferResponse;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import vn.needy.vendor.R;
import vn.needy.vendor.model.Place;
import vn.needy.vendor.repository.remote.company.request.RegisterCompanyRequest;
import vn.needy.vendor.repository.remote.store.request.CreateStoreRequest;
import vn.needy.vendor.screen.main.MainActivity;
import vn.needy.vendor.screen.place.PlaceActivity;
import vn.needy.vendor.utils.dialog.DialogManager;
import vn.needy.vendor.utils.navigator.Navigator;

/**
 * Created by lion on 07/10/2017.
 */

public class RegisterCompanyViewModel extends BaseObservable implements RegisterCompanyContract.ViewModel {

    private static final String TAG = RegisterCompanyViewModel.class.getName();

    private final Context mContext;
    private final Navigator mNavigator;
    private final DialogManager mDialogManager;
    private RegisterCompanyContract.Presenter mPresenter;

    private String mCompanyNameError;
    private String mOfficeAddressError;
    private String mOpeningTimeCompanyError;
    private String mClosingTimeCompanyError;

    private String mStoreNameError;
    private String mStoreAddressError;
    private String mOpeningTimeStoreError;
    private String mClosingTimeStoreError;

    private String mCompanyName;
    private String mOfficeAddress;
    private String mStoreName;
    private String mStoreAddress;

    private String mOpeningTimeCompany;
    private String mClosingTimeCompany;

    private String mOpeningTimeStore;
    private String mClosingTimeStore;

    private Place mCompanyPlace;
    private Place mStorePlace;

    private float mLat;
    private float mLng;

    private PlaceDetectionClient mPlaceDetectionClient;

    public RegisterCompanyViewModel(Context context, Navigator navigator,
                                    DialogManager dialogManager) {
        mContext = context;
        mNavigator = navigator;
        mDialogManager = dialogManager;

        mPlaceDetectionClient = Places.getPlaceDetectionClient(mContext, null);
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }

    @Override
    public void setPresenter(RegisterCompanyContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onInputCompanyError(int errorMsg) {
        mCompanyNameError = mContext.getString(errorMsg);
        notifyPropertyChanged(BR.companyNameError);
    }

    @Override
    public void onInputOfficeAddressError(int errorMsg) {
        mOfficeAddressError = mContext.getString(errorMsg);
        notifyPropertyChanged(BR.officeAddressError);
    }

    @Override
    public void onInputOpeningTimeComanyError(int msg) {
        mOpeningTimeCompanyError = mContext.getString(msg);
        notifyPropertyChanged(BR.openingTimeCompanyError);
    }

    @Override
    public void onInputClosingTimeComanyError(int msg) {
        mClosingTimeCompanyError = mContext.getString(msg);
        notifyPropertyChanged(BR.closingTimeCompanyError);
    }

    @Override
    public void onInputOpeningTimeStoreError(int msg) {
        mOpeningTimeStoreError = mContext.getString(msg);
        notifyPropertyChanged(BR.openingTimeStoreError);
    }

    @Override
    public void onInputClosingTimeStoreError(int msg) {
        mClosingTimeStoreError = mContext.getString(msg);
        notifyPropertyChanged(BR.closingTimeStoreError);
    }

    @Override
    public void onInputStoreNameError(int errorMsg) {
        mStoreNameError = mContext.getString(errorMsg);
        notifyPropertyChanged(BR.storeNameError);
    }

    @Override
    public void onInputStoreAddressError(int errorMsg) {
        mStoreAddressError = mContext.getString(errorMsg);
        notifyPropertyChanged(BR.storeAddressError);
    }

    @Override
    public void onRegisterError(int errorMsg) {
        mNavigator.showToastCenterText(mContext.getString(errorMsg));
    }

    @Override
    public void onRegisterError(String message) {
        mNavigator.showToastCenterText(message);
    }

    @Override
    public void onRegisterSuccess() {
        mNavigator.startActivity(MainActivity.class);
        mNavigator.finishActivity();
    }

    @Override
    public void onRegisterClick() {
        RegisterCompanyRequest companyRequest = new RegisterCompanyRequest();
        companyRequest.setCompanyName(mCompanyName);
        companyRequest.setCompanyAddress(mOfficeAddress);
        companyRequest.setOpeningTime(mOpeningTimeCompany);
        companyRequest.setClosingTime(mClosingTimeCompany);

        CreateStoreRequest storeRequest = new CreateStoreRequest();
        storeRequest.setStoreName(mStoreName);
        storeRequest.setAddress(mStoreAddress);
        storeRequest.setOpeningTime(mOpeningTimeStore);
        storeRequest.setClosingTime(mClosingTimeStore);
        mPresenter.registerCompany(companyRequest, storeRequest);
    }

    @Override
    public void onCompanyPositionClick() {
        mPlaceDetectionClient.getCurrentPlace(null).addOnCompleteListener(new OnCompleteListener<PlaceLikelihoodBufferResponse>() {
            @Override
            public void onComplete(@NonNull Task<PlaceLikelihoodBufferResponse> task) {
                PlaceLikelihoodBufferResponse likelyPlaces = task.getResult();
                if (likelyPlaces != null && likelyPlaces.getCount() > 0) {
                    mOfficeAddress = likelyPlaces.get(0).getPlace().getName().toString();
                    notifyPropertyChanged(BR.officeAddress);
                    likelyPlaces.release();
                }
            }
        });
    }

    @Override
    public void onStorePositionClick() {
        mPlaceDetectionClient.getCurrentPlace(null).addOnCompleteListener(new OnCompleteListener<PlaceLikelihoodBufferResponse>() {
            @Override
            public void onComplete(@NonNull Task<PlaceLikelihoodBufferResponse> task) {
                PlaceLikelihoodBufferResponse likelyPlaces = task.getResult();
                if (likelyPlaces != null && likelyPlaces.getCount() > 0) {
                    mStoreAddress = likelyPlaces.get(0).getPlace().getName().toString();
                    notifyPropertyChanged(BR.storeAddress);
                    likelyPlaces.release();
                }
            }
        });
    }

    @Override
    public void onShowProgressBar() {
        mDialogManager.showProgressDialog();
    }

    @Override
    public void onHideProgressBar() {
        mDialogManager.dismissProgressDialog();
    }

    @Override
    public void onBackPressed() {
        mNavigator.onBackPressed();
    }

    @Override
    public void onClickEditCompanyAddress() {
        mNavigator.startActivityForResult(PlaceActivity.class, RegisterCompanyActivity.COMPANY_ADDRESS);
    }

    @Override
    public void onClickEditStoreAddress() {
        mNavigator.startActivityForResult(PlaceActivity.class, RegisterCompanyActivity.STORE_ADDRESS);
    }

    @Override
    public void updateCompanyAddress(Place place) {
        mOfficeAddress = place.getAddress();
        notifyPropertyChanged(BR.officeAddress);
    }

    @Override
    public void updateStoreAddress(Place place) {
        mStoreAddress = place.getAddress();
        notifyPropertyChanged(BR.storeAddress);
    }

    @Override
    public void onClickOpeningTimeCompany() {
        TimePickerDialog timePickerDialog = new TimePickerDialog(mContext, R.style.DatePickerDialogTheme, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                Calendar calendar = new GregorianCalendar(0, 0, 0, hourOfDay, minute);
                SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
                mOpeningTimeCompany = dateFormat.format(calendar.getTime());
                notifyPropertyChanged(BR.openingTimeCompany);
            }
        }, 0, 0, true);
        timePickerDialog.show();
    }

    @Override
    public void onClickClosingTimeCompany() {
        TimePickerDialog timePickerDialog = new TimePickerDialog(mContext, R.style.DatePickerDialogTheme, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                Calendar calendar = new GregorianCalendar(0, 0, 0, hourOfDay, minute);
                SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
                mClosingTimeCompany = dateFormat.format(calendar.getTime());
                notifyPropertyChanged(BR.closingTimeCompany);
            }
        }, 0, 0, true);
        timePickerDialog.show();
    }

    @Override
    public void onClickOpeningTimeStore() {
        TimePickerDialog timePickerDialog = new TimePickerDialog(mContext, R.style.DatePickerDialogTheme, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                Calendar calendar = new GregorianCalendar(0, 0, 0, hourOfDay, minute);
                SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
                mOpeningTimeStore = dateFormat.format(calendar.getTime());
                notifyPropertyChanged(BR.openingTimeStore);
            }
        }, 0, 0, true);
        timePickerDialog.show();
    }

    @Override
    public void onClickClosingTimeStore() {
        TimePickerDialog timePickerDialog = new TimePickerDialog(mContext, R.style.DatePickerDialogTheme, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                Calendar calendar = new GregorianCalendar(0, 0, 0, hourOfDay, minute);
                SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
                mClosingTimeStore = dateFormat.format(calendar.getTime());
                notifyPropertyChanged(BR.closingTimeStore);
            }
        }, 0, 0, true);
        timePickerDialog.show();
    }

    @Bindable
    public String getCompanyNameError() {
        return mCompanyNameError;
    }

    @Bindable
    public String getOfficeAddressError() {
        return mOfficeAddressError;
    }

    @Bindable
    public String getStoreNameError() {
        return mStoreNameError;
    }

    @Bindable
    public String getStoreAddressError() {
        return mStoreAddressError;
    }

    @Bindable
    public String getCompanyName() {
        return mCompanyName;
    }

    public void setCompanyName(String companyName) {
        mCompanyName = companyName;
    }

    @Bindable
    public String getOfficeAddress() {
        return mOfficeAddress;
    }

    public void setOfficeAddress(String officeAddress) {
        mOfficeAddress = officeAddress;
    }

    @Bindable
    public String getStoreName() {
        return mStoreName;
    }

    public void setStoreName(String storeName) {
        mStoreName = storeName;
    }

    @Bindable
    public String getStoreAddress() {
        return mStoreAddress;
    }

    public void setStoreAddress(String storeAddress) {
        mStoreAddress = storeAddress;
    }

    @Bindable
    public String getOpeningTimeCompany() {
        return mOpeningTimeCompany;
    }

    @Bindable
    public String getClosingTimeCompany() {
        return mClosingTimeCompany;
    }

    @Bindable
    public String getOpeningTimeStore() {
        return mOpeningTimeStore;
    }

    @Bindable
    public String getClosingTimeStore() {
        return mClosingTimeStore;
    }

    @Bindable
    public String getOpeningTimeCompanyError() {
        return mOpeningTimeCompanyError;
    }

    @Bindable
    public String getClosingTimeCompanyError() {
        return mClosingTimeCompanyError;
    }

    @Bindable
    public String getOpeningTimeStoreError() {
        return mOpeningTimeStoreError;
    }

    @Bindable
    public String getClosingTimeStoreError() {
        return mClosingTimeStoreError;
    }
}
