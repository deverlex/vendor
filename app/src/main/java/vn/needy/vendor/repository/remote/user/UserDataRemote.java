package vn.needy.vendor.repository.remote.user;

import io.reactivex.Observable;
import retrofit2.Response;
import vn.needy.vendor.port.message.ResponseWrapper;
import vn.needy.vendor.repository.remote.user.request.LoginReq;
import vn.needy.vendor.repository.remote.user.response.BusinessInfoResp;
import vn.needy.vendor.repository.remote.user.response.LoginResp;
import vn.needy.vendor.repository.remote.user.response.TokenResponse;
import vn.needy.vendor.repository.UserData;
import vn.needy.vendor.port.api.VendorApi;
import vn.needy.vendor.repository.remote.user.request.RegisterUserReq;
import vn.needy.vendor.repository.remote.user.request.ResetAccountReq;
import vn.needy.vendor.repository.remote.user.request.UpdateUserInfoRequest;
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
    public Observable<ResponseWrapper<LoginResp>> login(LoginReq request) {
        return mApi.login(request);
    }

    @Override
    public Observable<ResponseWrapper<TokenResponse>> refresh() {
        return null;
    }

    @Override
    public Observable<Response<Void>> logout() {
        return null;
    }

    @Override
    public Observable<ResponseWrapper<TokenResponse>> registerUser(RegisterUserReq request) {
        return mApi.registerUser(request);
    }

    @Override
    public Observable<ResponseWrapper> findUserExist(String phoneNumber) {
        return mApi.findUserExist(phoneNumber);
    }

    @Override
    public Observable<ResponseWrapper<TokenResponse>> resetPassword(String phoneNumber, ResetAccountReq request) {
        return mApi.resetPassword(phoneNumber, request);
    }

    @Override
    public Observable<ResponseWrapper<UserInfoResponse>> getUserInformation() {
        return mApi.getUserInformation();
    }

    @Override
    public Observable<ResponseWrapper> updateUserInformation(UpdateUserInfoRequest request) {
        return mApi.updateUserInformation(request);
    }

    @Override
    public Observable<ResponseWrapper<BusinessInfoResp>> getBusinessInformation() {
        return mApi.getBusinessInformation();
    }
}
