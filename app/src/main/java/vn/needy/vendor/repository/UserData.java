package vn.needy.vendor.repository;

import io.reactivex.Observable;
import retrofit2.Response;
import vn.needy.vendor.model.User;
import vn.needy.vendor.port.message.BaseResponse;
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
        Observable<BaseResponse<LoginResp>> login(LoginReq request);

        Observable<BaseResponse<TokenResponse>> refresh();

        Observable<Response<Void>> logout();

        Observable<BaseResponse<TokenResponse>> registerUser(RegisterUserReq request);

        Observable<BaseResponse> findUserExist(String phoneNumber);

        Observable<BaseResponse<TokenResponse>> resetPassword(String phoneNumber, ResetAccountReq request);

        Observable<BaseResponse<UserInfoResponse>> getUserInformation();

        Observable<BaseResponse> updateUserInformation(UpdateUserInfoRequest request);

        Observable<BaseResponse<BusinessInfoResp>> getBusinessInformation();
    }

    interface Local {

        void saveUserSync(User user);

        void saveTokenSync(String token);

        void clearToken();

        void clearAll();
    }
}
