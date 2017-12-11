package vn.needy.vendor.repository.remote.user;

import io.reactivex.Observable;
import retrofit2.Response;
import vn.needy.vendor.port.message.BaseResponse;
import vn.needy.vendor.repository.remote.user.request.LoginReq;
import vn.needy.vendor.repository.remote.user.response.LoginResp;
import vn.needy.vendor.repository.remote.user.response.TokenResponse;
import vn.needy.vendor.repository.UserData;
import vn.needy.vendor.port.api.VendorApi;
import vn.needy.vendor.repository.remote.user.request.RegisterUserReq;
import vn.needy.vendor.repository.remote.user.request.ResetPasswordRequest;
import vn.needy.vendor.repository.remote.user.request.UpdateUserInfoRequest;
import vn.needy.vendor.repository.remote.user.response.CompanyResp;
import vn.needy.vendor.repository.remote.user.response.UserInfoResponse;
import vn.needy.vendor.repository.remote.BaseDataRemote;

/**
 * Created by lion on 10/12/2017.
 */

public class UserDataRemote extends BaseDataRemote<VendorApi> implements UserData.Remote {

    public UserDataRemote(VendorApi vendorApi) {
        super(vendorApi);
    }

    @Override
    public Observable<LoginResp> login(LoginReq request) {
        return mApi.login(request);
    }

    @Override
    public Observable<TokenResponse> refresh() {
        return null;
    }

    @Override
    public Observable<Response<Void>> logout() {
        return null;
    }

    @Override
    public Observable<TokenResponse> registerUser(RegisterUserReq request) {
        return mApi.registerUser(request);
    }

    @Override
    public Observable<BaseResponse> findUserExist(String phoneNumber) {
        return mApi.findUserExist(phoneNumber);
    }

    @Override
    public Observable<TokenResponse> resetPassword(String phoneNumber, ResetPasswordRequest request) {
        return mApi.resetPassword(phoneNumber, request);
    }

    @Override
    public Observable<UserInfoResponse> getUserInformation() {
        return mApi.getUserInformation();
    }

    @Override
    public Observable<BaseResponse> updateUserInformation(UpdateUserInfoRequest request) {
        return mApi.updateUserInformation(request);
    }
}
