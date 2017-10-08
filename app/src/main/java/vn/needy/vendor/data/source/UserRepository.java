package vn.needy.vendor.data.source;

import io.reactivex.Observable;
import vn.needy.vendor.data.model.Auth;
import vn.needy.vendor.data.model.Company;
import vn.needy.vendor.data.model.User;
import vn.needy.vendor.data.source.remote.api.response.LoginResponse;

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

    public Observable<User> login(String userName, String passWord, String deviceToken) {
        return mUserRemoteDataSource.login(userName, passWord, deviceToken);
    }

//    public Observable<UpdateProfileResponse> updateProfile(
//            UpdateProfileRequest updateProfileRequest) {
//        return mUserRemoteDataSource.updateProfile(updateProfileRequest);
//    }

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
