package vn.needy.vendor.data.source;

import io.reactivex.Observable;
import vn.needy.vendor.data.model.User;
import vn.needy.vendor.data.source.remote.api.request.RegisterUserRequest;
import vn.needy.vendor.data.source.remote.api.response.RegisterUserResponse;

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

    public Observable<RegisterUserResponse> registerUser(RegisterUserRequest registerUserRequest) {
        return mUserRemoteDataSource.registerUser(registerUserRequest);
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
