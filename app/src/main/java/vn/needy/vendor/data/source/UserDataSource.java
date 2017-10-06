package vn.needy.vendor.data.source;

import io.reactivex.Observable;
import vn.needy.vendor.data.model.User;
import vn.needy.vendor.data.source.remote.api.response.LoginResponse;

/**
 * Created by lion on 04/10/2017.
 */

public interface UserDataSource {
    interface LocalDataSource {
        void saveUser(User user);

        User getUser();

        void clearData();
    }

    interface RemoteDataSource {
        Observable<LoginResponse> login(String userName, String passWord, String deviceToken);

//        Observable<UpdateProfileResponse> updateProfile(UpdateProfileRequest updateProfileRequest);
    }
}