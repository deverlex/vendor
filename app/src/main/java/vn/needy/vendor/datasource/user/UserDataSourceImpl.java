package vn.needy.vendor.datasource.user;

import io.reactivex.Observable;
import vn.needy.vendor.datasource.BaseDataSource;
import vn.needy.vendor.datasource.user.request.UpdateUserInfoRequest;
import vn.needy.vendor.datasource.user.request.RegisterUserRequest;
import vn.needy.vendor.datasource.user.request.ResetPasswordRequest;
import vn.needy.vendor.datasource.BaseResponse;
import vn.needy.vendor.datasource.authentication.response.TokenResponse;
import vn.needy.vendor.datasource.user.response.UserInfoResponse;
import vn.needy.vendor.service.sharedprf.SharedPrefsApi;
import vn.needy.vendor.service.sharedprf.SharedPrefsKey;

/**
 * Created by lion on 05/10/2017.
 */

public class UserDataSourceImpl extends BaseDataSource implements UserDataSource {

    private SharedPrefsApi mPrefsApi;

    public UserDataSourceImpl(SharedPrefsApi prefsApi) {
        super();
        mPrefsApi = prefsApi;
    }

    public Observable<TokenResponse> registerUser(RegisterUserRequest request) {
        return mVendorApi.registerUser(request);
    }

    public Observable<BaseResponse> findUserExist(String phoneNumber) {
        return mVendorApi.findUserExist(phoneNumber);
    }

    public Observable<TokenResponse> resetPassword(String phoneNumber, ResetPasswordRequest request) {
        return mVendorApi.resetPassword(phoneNumber, request);
    }

    public Observable<UserInfoResponse> getUserInformation() {
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

    public void clearToken() {
        mPrefsApi.remove(SharedPrefsKey.TOKEN_KEY);
    }

    public void clear() {
        mPrefsApi.clear();
    }
}
