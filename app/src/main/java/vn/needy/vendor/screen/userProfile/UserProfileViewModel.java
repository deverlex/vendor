package vn.needy.vendor.screen.userProfile;

import android.app.DatePickerDialog;
import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.widget.DatePicker;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import ss.com.bannerslider.banners.Banner;
import vn.needy.vendor.BR;
import vn.needy.vendor.R;
import vn.needy.vendor.repository.remote.user.context.UserContext;
import vn.needy.vendor.repository.remote.user.context.UserLocationContext;
import vn.needy.vendor.screen.userProfile.changePassword.ChangePasswordFragment;
import vn.needy.vendor.utils.Constant;

/**
 * Created by lion on 09/11/2017.
 */

public class UserProfileViewModel extends BaseObservable implements UserProfileContract.ViewModel {

    private UserProfileContract.Presenter mPresenter;

    private UserContext mUser;
    private List<Banner> mBanners;
    private boolean mEnable;
    private int mDrawableEdit;
    private Context mContext;
    private String mAvatar;
    private List<String> mGenderTitle;
    private UserLocationAdapter mUserLocationAdapter;
    private boolean mIsExpandLocation;
    private int mDrawableExpandLocation;

    public UserProfileViewModel(Context context, UserLocationAdapter locationAdapter) {
        this.mContext = context;
        mDrawableEdit = R.drawable.ic_edits_white;
        mGenderTitle = Arrays.asList(mContext.getResources().getStringArray(R.array.gender_title));
        mAvatar = Constant.API_END_POINT_URL + "v1/images/products/1";
        mUserLocationAdapter = locationAdapter;
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
//            UpdateUserInfoRequest request = new UpdateUserInfoRequest();
//            request.setAddress(mUser.getAddress());
//            request.setEmail(mUser.getEmail());
//            request.setName(mUser.getFullName());
//            request.setBirthday(mUser.getBirthday());
//            request.setGender(mUser.getGender());
//            request.setLat(mUser.getLat());
//            request.setLng(mUser.getLng());
//            mPresenter.updateUserInformation(request);
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
    public void onClickMale() {
        mUser.setGender("male");
        notifyPropertyChanged(BR.user);
    }

    @Override
    public void onClickFemale() {
        mUser.setGender("female");
        notifyPropertyChanged(BR.user);
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
}
