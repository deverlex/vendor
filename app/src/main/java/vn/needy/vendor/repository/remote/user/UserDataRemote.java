package vn.needy.vendor.repository.remote.user;

import io.reactivex.Observable;
import retrofit2.Response;
import vn.needy.vendor.port.message.RequestWrapper;
import vn.needy.vendor.port.message.ResponseWrapper;
import vn.needy.vendor.repository.remote.user.request.LoginRequest;
import vn.needy.vendor.repository.remote.user.response.BusinessInfoResponse;
import vn.needy.vendor.repository.remote.user.response.LoginResponse;
import vn.needy.vendor.repository.remote.user.response.TokenResponse;
import vn.needy.vendor.repository.UserData;
import vn.needy.vendor.port.api.VendorApi;
import vn.needy.vendor.repository.remote.user.request.RegisterUserRequest;
import vn.needy.vendor.repository.remote.user.request.ResetAccountRequest;
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
    public Observable<ResponseWrapper<TokenResponse>> login(LoginRequest request) {
        return mApi.login(new RequestWrapper<LoginRequest>().setData(request));
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
    public Observable<ResponseWrapper<TokenResponse>> registerUser(RegisterUserRequest request) {
        return mApi.registerUser(new RequestWrapper<RegisterUserRequest>().setData(request));
    }

    @Override
    public Observable<ResponseWrapper> checkOwnCompanyExist() {
        return mApi.checkOwnCompanyExist();
    }

    @Override
    public Observable<ResponseWrapper> findUserExist(String phoneNumber) {
        return mApi.findUserExist(phoneNumber);
    }

    @Override
    public Observable<ResponseWrapper<TokenResponse>> resetPassword(String phoneNumber, ResetAccountRequest request) {
        return mApi.resetPassword(phoneNumber, new RequestWrapper<ResetAccountRequest>().setData(request));
    }

    @Override
    public Observable<ResponseWrapper<UserInfoResponse>> getUserInformation() {
        return mApi.getUserInformation();
    }

    @Override
    public Observable<ResponseWrapper> updateUserInformation(UpdateUserInfoRequest request) {
        return mApi.updateUserInformation(new RequestWrapper<UpdateUserInfoRequest>().setData(request));
    }

    @Override
    public Observable<ResponseWrapper<BusinessInfoResponse>> getBusinessInformation() {
        return mApi.getBusinessInformation();
    }
}
