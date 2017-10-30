package vn.needy.vendor.data.source;

import io.reactivex.Observable;
import vn.needy.vendor.data.model.User;
import vn.needy.vendor.data.source.remote.api.request.RegisterUserRequest;
import vn.needy.vendor.data.source.remote.api.request.ResetPasswordRequest;
import vn.needy.vendor.data.source.remote.api.response.BaseResponse;
import vn.needy.vendor.data.source.remote.api.response.CertificationResponse;
import vn.needy.vendor.data.source.remote.api.response.UserResponse;

/**
 * Created by lion on 04/10/2017.
 */

public interface UserDataSource {
    interface LocalDataSource {
        void saveUser(User user);

        User getUser();

        void clearData();

        void saveToken(String token);
    }

    interface RemoteDataSource {
        Observable<CertificationResponse> registerUser(RegisterUserRequest registerUserRequest);

        Observable<BaseResponse> findUserExist(String phoneNumber);

        Observable<CertificationResponse> resetPassword(String phoneNumber, ResetPasswordRequest resetPasswordRequest);

        Observable<UserResponse> getUserInformation();
    }
}
