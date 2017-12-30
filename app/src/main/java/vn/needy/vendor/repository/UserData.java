package vn.needy.vendor.repository;

import io.reactivex.Observable;
import retrofit2.Response;
import vn.needy.vendor.domain.User;
import vn.needy.vendor.port.message.ResponseWrapper;
import vn.needy.vendor.repository.remote.user.request.LoginReq;
import vn.needy.vendor.repository.remote.user.response.BusinessInfoResp;
import vn.needy.vendor.repository.remote.user.response.LoginResp;
import vn.needy.vendor.repository.remote.user.response.TokenResponse;
import vn.needy.vendor.repository.remote.user.request.RegisterUserReq;
import vn.needy.vendor.repository.remote.user.request.ResetAccountReq;
import vn.needy.vendor.repository.remote.user.request.UpdateUserInfoRequest;
import vn.needy.vendor.repository.remote.user.response.UserInfoResponse;

/**
 * Created by lion on 10/12/2017.
 */

public interface UserData {

    interface Remote {
        Observable<ResponseWrapper<LoginResp>> login(LoginReq request);

        Observable<ResponseWrapper<TokenResponse>> refresh();

        Observable<Response<Void>> logout();

        Observable<ResponseWrapper<TokenResponse>> registerUser(RegisterUserReq request);

        Observable<ResponseWrapper> findUserExist(String phoneNumber);

        Observable<ResponseWrapper<TokenResponse>> resetPassword(String phoneNumber, ResetAccountReq request);

        Observable<ResponseWrapper<UserInfoResponse>> getUserInformation();

        Observable<ResponseWrapper> updateUserInformation(UpdateUserInfoRequest request);

        Observable<ResponseWrapper<BusinessInfoResp>> getBusinessInformation();
    }

    interface Local {

        void saveUserSync(User user);

        void saveTokenSync(String token);

        void clearToken();

        void clearAll();
    }
}
