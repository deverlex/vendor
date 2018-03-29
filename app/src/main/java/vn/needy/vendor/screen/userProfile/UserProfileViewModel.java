package vn.needy.vendor.screen.userProfile;

import android.app.DatePickerDialog;
import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Bundle;
import android.util.Log;
import android.widget.DatePicker;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import ss.com.bannerslider.banners.Banner;
import vn.needy.vendor.BR;
import vn.needy.vendor.R;
import vn.needy.vendor.model.Place;
import vn.needy.vendor.repository.remote.user.context.UpdateUserContext;
import vn.needy.vendor.repository.remote.user.context.UserContext;
import vn.needy.vendor.repository.remote.user.context.UserLocationContext;
import vn.needy.vendor.repository.remote.user.request.UpdateUserInfoRequest;
import vn.needy.vendor.screen.BaseRecyclerViewAdapter;
import vn.needy.vendor.screen.place.PlaceActivity;
import vn.needy.vendor.screen.userProfile.changePassword.ChangePasswordFragment;
import vn.needy.vendor.screen.userProfile.location.AddLocationActivity;
import vn.needy.vendor.utils.Constant;
import vn.needy.vendor.utils.navigator.Navigator;

/**
 * Created by lion on 09/11/2017.
 */

public class UserProfileViewModel extends BaseObservable implements UserProfileContract.ViewModel,
        BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener{

    private UserProfileContract.Presenter mPresenter;
    private Navigator mNavigator;
    private UserContext mUser;
    private List<Banner> mBanners;
    private boolean mEnable;
    private int mDrawableEdit;
    private Context mContext;
    private String mAvatar;
    private List<String> mGenderTitle;
    private UserLocationAdapter mUserLocationAdapter;
    private List<UserLocationContext> mDeletedLocation;
    private boolean mIsExpandLocation;
    private int mDrawableExpandLocation;

    private boolean isSpinnerSelection;

    public UserProfileViewModel(Context context, Navigator navigator, UserLocationAdapter locationAdapter) {
        this.mContext = context;
        mNavigator = navigator;
        mDrawableEdit = R.drawable.ic_edits_white;
        mGenderTitle = Arrays.asList(mContext.getResources().getStringArray(R.array.gender_title));
        mAvatar = Constant.API_END_POINT_URL + "v1/images/products/1";
        mUserLocationAdapter = locationAdapter;
        mUserLocationAdapter.setItemClickListener(this);
        mDeletedLocation = new ArrayList<>();
        mDrawableExpandLocation = R.drawable.ic_next_right;
    }

    @Override
    public void onStart() {
        mPresenter.getUserInfo();
    }

    @Override
    public void onStop() {

    }

    @Override
    public void setPresenter(UserProfileContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onClickEdit() {
        if (mEnable) {
            UpdateUserContext userContext = new UpdateUserContext();
            userContext.setFirstName(mUser.getFirstName());
            userContext.setLastName(mUser.getLastName());
            userContext.setBirthday(mUser.getBirthday());
            userContext.setEmail(mUser.getEmail());
            userContext.setGender(mUser.getGender());
            userContext.setAddress(mUser.getAddress());

            UpdateUserInfoRequest request = new UpdateUserInfoRequest();
            request.setUser(userContext);
            request.setLocations(mUserLocationAdapter.getLocations());
            request.setDeletedlocations(mDeletedLocation);
            mPresenter.updateUserInformation(request);
        }

        mEnable = !mEnable;
        notifyPropertyChanged(BR.enable);
        mDrawableEdit = mEnable ? R.drawable.ic_check : R.drawable.ic_edits_white;
        notifyPropertyChanged(BR.drawableEdit);
    }

    @Bindable
    public List<Banner> getBanners() {
        return mBanners;
    }

    @Override
    public void setBanners(List<Banner> banners) {
        mBanners = banners;
        notifyPropertyChanged(BR.banners);
    }

    @Override
    public void onPositionClick() {

    }

    @Override
    public void onChangePassword() {
        ((UserProfileActivity) mContext).initFragment(android.R.id.content,
                ChangePasswordFragment.newInstance());
    }

    @Override
    public void onBackPressed() {
        ((UserProfileActivity) mContext).onBackPressed();
    }

    @Override
    public void onClickBirthday() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(mContext, R.style.DatePickerDialogTheme, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Calendar calendar = new GregorianCalendar(year, month, dayOfMonth);
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                mUser.setBirthday(dateFormat.format(calendar.getTime()));
                notifyPropertyChanged(BR.user);
            }
        }, 2017, 11, 6);
        datePickerDialog.getDatePicker().setSpinnersShown(true);
        datePickerDialog.getDatePicker().setCalendarViewShown(false);
        datePickerDialog.show();
    }

    @Override
    public void setUserInfo(UserContext user) {
        mUser = user;
        notifyPropertyChanged(BR.user);
    }

    @Override
    public void setUserLocations(List<UserLocationContext> userLocations) {
        mUserLocationAdapter.setData(userLocations);
    }

    @Override
    public void onClickExpandLocation() {
        mIsExpandLocation = !mIsExpandLocation;
        notifyPropertyChanged(BR.expandLocation);

        if (mIsExpandLocation) {
            mDrawableExpandLocation = R.drawable.ic_expand;
        } else {
            mDrawableExpandLocation = R.drawable.ic_next_right;
        }
        notifyPropertyChanged(BR.drawableExpandLocation);
    }

    @Override
    public void onSpinnerItemSelected(int position) {
        assert mUser != null;
        // check is initialization view because Spinner onItemSelect alway call on first time
        if (isSpinnerSelection) {
            switch (position) {
                case 0:
                    mUser.setGender("male");
                    break;
                case 1:
                    mUser.setGender("female");
                    break;
                case 2:
                    mUser.setGender("other");
                    break;
                default:
                    mUser.setGender("other");
            }
        } else {
            isSpinnerSelection = true;
        }
    }

    @Override
    public void onClickAddress() {
        mNavigator.startActivityForResult(PlaceActivity.class, UserProfileActivity.ADDRESS);
    }

    @Override
    public void updateCompanyAddress(Place place) {
        mUser.setAddress(place.getAddress());
        notifyPropertyChanged(BR.user);
    }

    @Override
    public void onClickAddLocation() {
        mNavigator.startActivityForResult(AddLocationActivity.class, UserProfileActivity.ADD_LOCATION);
    }

    @Override
    public void addLocation(UserLocationContext location) {
        mUserLocationAdapter.addLocation(location);
    }

    @Override
    public void updateLocation(int position, UserLocationContext location) {
        if (position != -1) {
            mUserLocationAdapter.editLocation(position, location);
        }
    }

    @Override
    public void onRemoveLocation(int position) {
        if (position != -1) {
            mDeletedLocation.add(mUserLocationAdapter.getLocation(position));
            mUserLocationAdapter.removeLocation(position);
        }
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
    public UserContext getUser() {
        return mUser;
    }

    @Bindable
    public String getAvatar() {
        return mAvatar;
    }

    @Bindable
    public List<String> getGenderTitle() {
        return mGenderTitle;
    }

    @Bindable
    public UserLocationAdapter getUserLocationAdapter() {
        return mUserLocationAdapter;
    }

    @Bindable
    public boolean isExpandLocation() {
        return mIsExpandLocation;
    }

    @Bindable
    public int getDrawableExpandLocation() {
        return mDrawableExpandLocation;
    }

    @Override
    public void onItemRecyclerViewClick(Object item) {
        if (item instanceof UserLocationContext) {
            Bundle args = new Bundle();
            UserLocationContext location = (UserLocationContext) item;
            args.putParcelable(AddLocationActivity.LOCATION, location);
            args.putInt(AddLocationActivity.LOCATION_POSITION, mUserLocationAdapter.indexOf(location));
            mNavigator.startActivityForResult(AddLocationActivity.class, args, UserProfileActivity.ADD_LOCATION);
        }
    }
}
