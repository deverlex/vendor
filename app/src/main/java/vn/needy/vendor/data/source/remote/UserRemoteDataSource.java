package vn.needy.vendor.data.source.remote;

import com.google.gson.Gson;

import java.util.LinkedHashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import vn.needy.vendor.data.model.Company;
import vn.needy.vendor.data.model.User;
import vn.needy.vendor.data.source.UserDataSource;
import vn.needy.vendor.data.source.local.sharedprf.SharedPrefsApi;
import vn.needy.vendor.data.source.local.sharedprf.SharedPrefsImpl;
import vn.needy.vendor.data.source.local.sharedprf.SharedPrefsKey;
import vn.needy.vendor.data.source.remote.api.request.LoginRequest;
import vn.needy.vendor.data.source.remote.api.response.CompanyResponse;
import vn.needy.vendor.data.source.remote.api.response.LoginResponse;
import vn.needy.vendor.data.source.remote.api.service.VendorApi;

/**
 * Created by lion on 23/09/2017.
 */

public class UserRemoteDataSource extends BaseRemoteDataSource
        implements UserDataSource.RemoteDataSource {

    private static final String TAG = UserRemoteDataSource.class.getName();

    public UserRemoteDataSource(VendorApi vendorApi) {
        super(vendorApi);
    }

    @Override
    public Observable<User> login(final String phoneNumber, String passWord, String deviceToken) {
        Map<String, String> header = new LinkedHashMap<>();
        header.put("X-User-Application", "Vendor");
        header.put("X-User-PhoneNumber", phoneNumber);
        header.put("X-User-Password", passWord);

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setFcmToken(deviceToken);

        return mVendorApi.login(header, loginRequest)
            .map(new Function<LoginResponse, User>() {
                @Override
                public User apply(@NonNull LoginResponse loginResponse) throws Exception {
                    // save auth into SharedPreference
                    SharedPrefsApi prefsApi = SharedPrefsImpl.getInstance();
                    String authContext = new Gson().toJson(loginResponse.getAuth());
                    prefsApi.put(SharedPrefsKey.KEY_AUTH, authContext);
                    return loginResponse.getUser();
                }
            });
    }

//    @Override
//    public Observable<UpdateProfileResponse> updateProfile(
//            UpdateProfileRequest updateProfileRequest) {
//        return mVendorApi.updateUserInformation(updateProfileRequest)
//                .map(new Function<UpdateProfileResponse, UpdateProfileResponse>() {
//                    @Override
//                    public UpdateProfileResponse apply(@NonNull UpdateProfileResponse updateProfileResponse) throws Exception {
//                        return updateProfileResponse;
//                    }
//                });
//    }
}
