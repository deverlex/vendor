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
import vn.needy.vendor.repository.remote.user.response.LoginResponse;
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

    public Observable<ResponseWrapper<LoginResponse>> login(LoginRequest request) {
        return mRemote.login(request);
    }

    public Observable<ResponseWrapper<TokenResponse>> refresh() {
        return mRemote.refresh();
    }

    public Observable<Response<Void>> logout() {
        return mRemote.logout();
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
        return mRemote.getUserInformation();
    }

    public Observable<ResponseWrapper> updateUserInformation(UpdateUserInfoRequest request) {
        return mRemote.updateUserInformation(request);
    }

    public Observable<ResponseWrapper<BusinessInfoResponse>> getBusinessInformation() {
        return mRemote.getBusinessInformation();
    }

    public void saveUserSync(User user) {
        mLocal.saveUserSync(user);
    }

    public void saveTokenSync(String token) {
        mLocal.saveTokenSync(token);
    }
}
