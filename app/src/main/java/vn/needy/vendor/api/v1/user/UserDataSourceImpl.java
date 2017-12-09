package vn.needy.vendor.api.v1.user;

import io.reactivex.Observable;
import vn.needy.vendor.api.base.BaseRepository;
import vn.needy.vendor.api.v1.user.request.UpdateUserInfoRequest;
import vn.needy.vendor.api.v1.user.request.RegisterUserRequest;
import vn.needy.vendor.api.v1.user.request.ResetPasswordRequest;
import vn.needy.vendor.api.base.BaseResponse;
import vn.needy.vendor.api.v1.auth.response.CertificationResponse;
import vn.needy.vendor.api.v1.user.response.UserResponse;
import vn.needy.vendor.datasource.sharedprf.SharedPrefsApi;
import vn.needy.vendor.datasource.sharedprf.SharedPrefsKey;

/**
 * Created by lion on 05/10/2017.
 */

public class UserDataSourceImpl extends BaseRepository implements UserDataSource {

    private SharedPrefsApi mPrefsApi;

    public UserDataSourceImpl(SharedPrefsApi prefsApi) {
        super();
        mPrefsApi = prefsApi;
    }

    public Observable<CertificationResponse> registerUser(RegisterUserRequest request) {
        return mVendorApi.registerUser(request);
    }

    public Observable<BaseResponse> findUserExist(String phoneNumber) {
        return mVendorApi.findUserExist(phoneNumber);
    }

    public Observable<CertificationResponse> resetPassword(String phoneNumber, ResetPasswordRequest request) {
        return mVendorApi.resetPassword(phoneNumber, request);
    }

    public Observable<UserResponse> getUserInformation() {
        return mVendorApi.getUserInformation();
    }

    public Observable<BaseResponse> updateUserInformation(UpdateUserInfoRequest request) {
        return mVendorApi.updateUserInformation(request);
    }

    @Override
    public Observable<BaseResponse> findUserCompany() {
        return mVendorApi.findUserCompany();
    }

    public void saveToken(String token) {
        mPrefsApi.put(SharedPrefsKey.TOKEN_KEY, token);
    }

    public void clearData() {
        mPrefsApi.clear();
    }
}
