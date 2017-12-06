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
 * Created by lion on 05/10/2017.
 */

public class UserRepository {

    private UserDataSource.RemoteDataSource mUserRemoteDataSource;
    private UserDataSource.LocalDataSource mUserLocalDataSource;

    public UserRepository(UserDataSource.RemoteDataSource userRemoteDataSource,
                          UserDataSource.LocalDataSource userLocalDataSource) {
        mUserRemoteDataSource = userRemoteDataSource;
        mUserLocalDataSource = userLocalDataSource;
    }

    public Observable<CertificationResponse> registerUser(RegisterUserRequest registerUserRequest) {
        return mUserRemoteDataSource.registerUser(registerUserRequest);
    }

    public Observable<BaseResponse> findUserExist(String phoneNumber) {
        return mUserRemoteDataSource.findUserExist(phoneNumber);
    }

    public Observable<CertificationResponse> resetPassword(String phoneNumber, ResetPasswordRequest resetPasswordRequest) {
        return mUserRemoteDataSource.resetPassword(phoneNumber, resetPasswordRequest);
    }

    public Observable<UserResponse> getUserInformation() {
        return mUserRemoteDataSource.getUserInformation();
    }

    public Observable<BaseResponse> updateUserInformation(UpdateUserInfoRequest request) {
        return mUserRemoteDataSource.updateUserInformation(request);
    }

    public void saveToken(String token) {
        mUserLocalDataSource.saveToken(token);
    }

    public void saveUser(User user) {
        mUserLocalDataSource.saveUser(user);
    }

    public User getUser() {
        return mUserLocalDataSource.getUser();
    }

    public void clearData() {
        mUserLocalDataSource.clearData();
    }
}
