package vn.needy.vendor.repository;

import io.reactivex.Observable;
import retrofit2.Response;
import vn.needy.vendor.domain.User;
import vn.needy.vendor.port.message.ResponseWrapper;
import vn.needy.vendor.repository.remote.user.request.LoginRequest;
import vn.needy.vendor.repository.remote.user.response.BusinessInfoResponse;
import vn.needy.vendor.repository.remote.user.response.LoginResponse;
import vn.needy.vendor.repository.remote.user.response.TokenResponse;
import vn.needy.vendor.repository.remote.user.request.RegisterUserRequest;
import vn.needy.vendor.repository.remote.user.request.ResetAccountRequest;
import vn.needy.vendor.repository.remote.user.request.UpdateUserInfoRequest;
import vn.needy.vendor.repository.remote.user.response.UserInfoResponse;

/**
 * Created by lion on 10/12/2017.
 */

public interface UserData {

    interface Remote {
        Observable<ResponseWrapper<TokenResponse>> login(LoginRequest request);

        Observable<ResponseWrapper<TokenResponse>> refresh();

        Observable<Response<Void>> logout();

        Observable<ResponseWrapper<TokenResponse>> registerUser(RegisterUserRequest request);

        Observable<ResponseWrapper> checkOwnCompanyExist();

        Observable<ResponseWrapper> findUserExist(String phoneNumber);

        Observable<ResponseWrapper<TokenResponse>> resetPassword(String phoneNumber, ResetAccountRequest request);

        Observable<ResponseWrapper<UserInfoResponse>> getUserInformation();

        Observable<ResponseWrapper> updateUserInformation(UpdateUserInfoRequest request);

        Observable<ResponseWrapper<BusinessInfoResponse>> getBusinessInformation();
    }

    interface Local {

        void saveUserSync(User user);

        void saveAccessTokenSync(String token);

        void saveRefreshTokenSync(String token);

        void saveExpiresIn(long expiresIn);

        void clearToken();

        void clearAll();
    }
}
