package vn.needy.vendor.repository;

import io.reactivex.Observable;
import retrofit2.Response;
import vn.needy.vendor.port.message.BaseResponse;
import vn.needy.vendor.repository.remote.user.request.LoginReq;
import vn.needy.vendor.repository.remote.user.response.TokenResponse;
import vn.needy.vendor.repository.remote.user.request.RegisterUserReq;
import vn.needy.vendor.repository.remote.user.request.ResetPasswordRequest;
import vn.needy.vendor.repository.remote.user.request.UpdateUserInfoRequest;
import vn.needy.vendor.repository.remote.user.response.BusinessIdResponse;
import vn.needy.vendor.repository.remote.user.response.UserInfoResponse;

/**
 * Created by lion on 10/12/2017.
 */

public interface UserData {

    interface Remote {
        Observable<TokenResponse> login(LoginReq request);

        Observable<TokenResponse> refresh();

        Observable<Response<Void>> logout();

        Observable<TokenResponse> registerUser(RegisterUserReq request);

        Observable<BaseResponse> findUserExist(String phoneNumber);

        Observable<TokenResponse> resetPassword(String phoneNumber, ResetPasswordRequest request);

        Observable<UserInfoResponse> getUserInformation();

        Observable<BaseResponse> updateUserInformation(UpdateUserInfoRequest request);

        Observable<BusinessIdResponse> findUserBusinessId();
    }

    interface Local {

        void saveToken(String token);

        void clearToken();

        void clearAll();
    }
}
