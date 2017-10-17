package vn.needy.vendor.data.source.remote;

import io.reactivex.Observable;
import vn.needy.vendor.data.source.UserDataSource;
import vn.needy.vendor.data.source.remote.api.request.RegisterUserRequest;
import vn.needy.vendor.data.source.remote.api.request.ResetPasswordRequest;
import vn.needy.vendor.data.source.remote.api.response.BaseResponse;
import vn.needy.vendor.data.source.remote.api.response.CertificationResponse;
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
    public Observable<CertificationResponse> registerUser(RegisterUserRequest registerUserRequest) {
        return mVendorApi.registerUser(registerUserRequest);
    }

    @Override
    public Observable<BaseResponse> findUserExist(String phoneNumber) {
        return mVendorApi.findUserExist(phoneNumber);
    }

    @Override
    public Observable<CertificationResponse> resetPassword(String phoneNumber, ResetPasswordRequest resetPasswordRequest) {
        phoneNumber = phoneNumber.replaceAll("\\+", "%2B");
        return mVendorApi.resetPassword(phoneNumber, resetPasswordRequest);
    }

//    @Override
//    public Observable<String> login(final String phoneNumber, String password) {
//        LoginRequest loginRequest = new LoginRequest();
//        loginRequest.setPhoneNumber(phoneNumber);
//        loginRequest.setPassWord(password);
//        return null;
//        return mVendorApi.login(loginRequest).map(new Function<String, String>() {
//            @Override
//            public String apply(@NonNull String s) throws Exception {
//                return s;
//            }
//        });
//            .map(new Function<LoginResponse, User>() {
//                @Override
//                public User apply(@NonNull LoginResponse loginResponse) throws Exception {
//                    // save auth into SharedPreference
//                    SharedPrefsApi prefsApi = SharedPrefsImpl.getInstance();
//                    String authContext = new Gson().toJson(loginResponse.getAuth());
//                    prefsApi.put(SharedPrefsKey.TOKEN_KEY, authContext);
//                    return loginResponse.getUser();
//                }
//            });
//    }

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
