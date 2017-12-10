package vn.needy.vendor.repository;

import io.reactivex.Observable;
import retrofit2.Response;
import vn.needy.vendor.repository.remote.user.request.LoginRequest;
import vn.needy.vendor.repository.remote.user.request.UpdateUserInfoRequest;
import vn.needy.vendor.repository.remote.user.request.RegisterUserRequest;
import vn.needy.vendor.repository.remote.user.request.ResetPasswordRequest;
import vn.needy.vendor.port.message.BaseResponse;
import vn.needy.vendor.repository.remote.user.response.TokenResponse;
import vn.needy.vendor.repository.remote.user.response.BusinessIdResponse;
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

    public Observable<TokenResponse> login(LoginRequest request) {
        return mRemote.login(request);
    }

    public Observable<TokenResponse> refresh() {
        return mRemote.refresh();
    }

    public Observable<Response<Void>> logout() {
        return mRemote.logout();
    }

    public Observable<TokenResponse> registerUser(RegisterUserRequest request) {
        return mRemote.registerUser(request);
    }

    public Observable<BaseResponse> findUserExist(String phoneNumber) {
        return mRemote.findUserExist(phoneNumber);
    }

    public Observable<TokenResponse> resetPassword(String phoneNumber, ResetPasswordRequest request) {
        return mRemote.resetPassword(phoneNumber, request);
    }

    public Observable<UserInfoResponse> getUserInformation() {
        return mRemote.getUserInformation();
    }

    public Observable<BaseResponse> updateUserInformation(UpdateUserInfoRequest request) {
        return mRemote.updateUserInformation(request);
    }

    public Observable<BusinessIdResponse> findUserBusinessId() {
        return mRemote.findUserBusinessId();
    }

    public void saveToken(String token) {
        mLocal.saveToken(token);
    }
}
