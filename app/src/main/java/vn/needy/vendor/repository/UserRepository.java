package vn.needy.vendor.repository;

import io.reactivex.Observable;
import retrofit2.Response;
import vn.needy.vendor.domain.User;
import vn.needy.vendor.repository.remote.user.request.LoginRequest;
import vn.needy.vendor.repository.remote.user.request.UpdateUserInfoRequest;
import vn.needy.vendor.repository.remote.user.request.RegisterUserRequest;
import vn.needy.vendor.repository.remote.user.request.ResetAccountRequest;
import vn.needy.vendor.port.message.ResponseWrapper;
import vn.needy.vendor.repository.remote.user.response.BusinessInfoResponse;
import vn.needy.vendor.repository.remote.user.response.CheckOwnCompanyExistRespone;
import vn.needy.vendor.repository.remote.user.response.TokenResponse;
import vn.needy.vendor.repository.remote.user.response.UserInfoResponse;

/**
 * Created by lion on 05/10/2017.
 */

public class UserRepository {

    private UserData.Local mLocal;
    private UserData.Remote mRemote;

    public UserRepository(UserData.Remote remote, UserData.Local local) {
        mRemote = remote;
        mLocal = local;
    }

    public Observable<ResponseWrapper<TokenResponse>> login(LoginRequest request) {
        return mRemote.login(request);
    }

    public Observable<ResponseWrapper> logout(String refreshToken) {
        return mRemote.logout(refreshToken);
    }

    public Observable<ResponseWrapper<TokenResponse>> registerUser(RegisterUserRequest request) {
        return mRemote.registerUser(request);
    }

    public Observable<ResponseWrapper> findUserExist(String phoneNumber) {
        return mRemote.findUserExist(phoneNumber);
    }

    public Observable<ResponseWrapper<TokenResponse>> resetPassword(String phoneNumber, ResetAccountRequest request) {
        return mRemote.resetPassword(phoneNumber, request);
    }

    public Observable<ResponseWrapper<UserInfoResponse>> getUserInformation() {
        return mRemote.getUserInfo();
    }

    public Observable<ResponseWrapper> updateUserInfo(UpdateUserInfoRequest request) {
        return mRemote.updateUserInfo(request);
    }

    public Observable<ResponseWrapper<BusinessInfoResponse>> getBusinessInformation() {
        return mRemote.getBusinessInformation();
    }

    public Observable<ResponseWrapper<CheckOwnCompanyExistRespone>> checkOwnCompanyExist() {
        return mRemote.checkOwnCompanyExist();
    }

    public Observable<ResponseWrapper<TokenResponse>> refreshToken(String refreshToken) {
        return mRemote.refreshToken(refreshToken);
    }

    public void saveUserSync(User user) {
        mLocal.saveUserSync(user);
    }

    public void saveAccessTokenSync(String token) {
        mLocal.saveAccessTokenSync(token);
    }

    public void saveRefreshTokenSync(String token) {
        mLocal.saveRefreshTokenSync(token);
    }

    public void saveExpiresIn(long expiresIn) {
        mLocal.saveExpiresIn(expiresIn);
    }
}
