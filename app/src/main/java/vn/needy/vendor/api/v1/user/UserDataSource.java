package vn.needy.vendor.api.v1.user;

import io.reactivex.Observable;
import vn.needy.vendor.api.v1.user.request.UpdateUserInfoRequest;
import vn.needy.vendor.database.model.User;
import vn.needy.vendor.api.v1.user.request.RegisterUserRequest;
import vn.needy.vendor.api.v1.user.request.ResetPasswordRequest;
import vn.needy.vendor.api.base.BaseResponse;
import vn.needy.vendor.api.v1.auth.response.CertificationResponse;
import vn.needy.vendor.api.v1.user.response.UserResponse;

/**
 * Created by lion on 04/10/2017.
 */

public interface UserDataSource {
    interface LocalDataSource {
        void clearData();

        void saveToken(String token);
    }

    interface RemoteDataSource {
        Observable<CertificationResponse> registerUser(RegisterUserRequest registerUserRequest);

        Observable<BaseResponse> findUserExist(String phoneNumber);

        Observable<CertificationResponse> resetPassword(String phoneNumber, ResetPasswordRequest resetPasswordRequest);

        Observable<UserResponse> getUserInformation();

        Observable<BaseResponse> updateUserInformation(UpdateUserInfoRequest request);
    }
}
