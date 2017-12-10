package vn.needy.vendor.datasource.user;

import io.reactivex.Observable;
import vn.needy.vendor.datasource.user.request.UpdateUserInfoRequest;
import vn.needy.vendor.datasource.user.request.RegisterUserRequest;
import vn.needy.vendor.datasource.user.request.ResetPasswordRequest;
import vn.needy.vendor.datasource.BaseResponse;
import vn.needy.vendor.datasource.authentication.response.TokenResponse;
import vn.needy.vendor.datasource.user.response.BusinessIdResponse;
import vn.needy.vendor.datasource.user.response.UserInfoResponse;

/**
 * Created by lion on 04/10/2017.
 */

public interface UserDataSource {

    Observable<TokenResponse> registerUser(RegisterUserRequest request);

    Observable<BaseResponse> findUserExist(String phoneNumber);

    Observable<TokenResponse> resetPassword(String phoneNumber, ResetPasswordRequest request);

    Observable<UserInfoResponse> getUserInformation();

    Observable<BaseResponse> updateUserInformation(UpdateUserInfoRequest request);

    Observable<BusinessIdResponse> findUserBusinessId();

    void saveToken(String token);

    void saveCompanyId(String companyId);

    void saveStoreId(String storeId);

    void clearToken();

    void clear();
}
