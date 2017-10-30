package vn.needy.vendor.data.source.remote.api.service;


import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import vn.needy.vendor.data.source.remote.api.request.CredentialsRequest;
import vn.needy.vendor.data.source.remote.api.request.RegisterCompanyRequest;
import vn.needy.vendor.data.source.remote.api.request.RegisterUserRequest;
import vn.needy.vendor.data.source.remote.api.request.ResetPasswordRequest;
import vn.needy.vendor.data.source.remote.api.response.BaseResponse;
import vn.needy.vendor.data.source.remote.api.response.CompanyResponse;
import vn.needy.vendor.data.source.remote.api.response.CertificationResponse;
import vn.needy.vendor.data.source.remote.api.response.UserResponse;

/**
 * Created by lion on 04/10/2017.
 */

public interface VendorApi {

    @POST("v1/authentications")
    Observable<CertificationResponse> login(@Body CredentialsRequest credentialsRequest);

    @POST("v1/users/registers")
    Observable<CertificationResponse> registerUser(@Body RegisterUserRequest registerUserRequest);

    @GET("v1/users/existences")
    Observable<BaseResponse> findUserExist(@Query("username") String phoneNumber);

    @POST("v1/users/reset")
    Observable<CertificationResponse> resetPassword(@Query("username") String phoneNumber,
                                                @Body ResetPasswordRequest resetPasswordRequest);

    @GET("v1/users")
    Observable<UserResponse> getUserInformation();

    @GET("v1/companies")
    Observable<CompanyResponse> getCompanyInformation();

    @GET("v1/companies/dependencies")
    Observable<CompanyResponse> findCompanyInformation();

    @POST("v1/companies/registers")
    Observable<CompanyResponse> registerCompany(@Body RegisterCompanyRequest registerCompanyRequest);

    @PUT("v1/companies/{company_id}/staffs/fcm_tokens")
    Observable<BaseResponse> updateStaffFcmToken(@Path("company_id") String companyId,
                                                      @Query("token") String token);
}
