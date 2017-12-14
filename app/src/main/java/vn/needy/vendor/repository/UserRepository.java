package vn.needy.vendor.repository;

import io.reactivex.Observable;
import retrofit2.Response;
import vn.needy.vendor.model.User;
import vn.needy.vendor.repository.remote.user.request.LoginReq;
import vn.needy.vendor.repository.remote.user.request.UpdateUserInfoRequest;
import vn.needy.vendor.repository.remote.user.request.RegisterUserReq;
import vn.needy.vendor.repository.remote.user.request.ResetAccountReq;
import vn.needy.vendor.port.message.ResponseWrapper;
import vn.needy.vendor.repository.remote.user.response.BusinessInfoResp;
import vn.needy.vendor.repository.remote.user.response.LoginResp;
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

    public Observable<ResponseWrapper<LoginResp>> login(LoginReq request) {
        return mRemote.login(request);
    }

    public Observable<ResponseWrapper<TokenResponse>> refresh() {
        return mRemote.refresh();
    }

    public Observable<Response<Void>> logout() {
        return mRemote.logout();
    }

    public Observable<ResponseWrapper<TokenResponse>> registerUser(RegisterUserReq request) {
        return mRemote.registerUser(request);
    }

    public Observable<ResponseWrapper> findUserExist(String phoneNumber) {
        return mRemote.findUserExist(phoneNumber);
    }

    public Observable<ResponseWrapper<TokenResponse>> resetPassword(String phoneNumber, ResetAccountReq request) {
        return mRemote.resetPassword(phoneNumber, request);
    }

    public Observable<ResponseWrapper<UserInfoResponse>> getUserInformation() {
        return mRemote.getUserInformation();
    }

    public Observable<ResponseWrapper> updateUserInformation(UpdateUserInfoRequest request) {
        return mRemote.updateUserInformation(request);
    }

    public Observable<ResponseWrapper<BusinessInfoResp>> getBusinessInformation() {
        return mRemote.getBusinessInformation();
    }

    public void saveUserSync(User user) {
        mLocal.saveUserSync(user);
    }

    public void saveTokenSync(String token) {
        mLocal.saveTokenSync(token);
    }
}
