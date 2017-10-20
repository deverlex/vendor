package vn.needy.vendor.data.source.remote.api.service;


import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import vn.needy.vendor.data.source.remote.api.request.CredentialsRequest;
import vn.needy.vendor.data.source.remote.api.request.RegisterCompanyRequest;
import vn.needy.vendor.data.source.remote.api.request.RegisterUserRequest;
import vn.needy.vendor.data.source.remote.api.request.ResetPasswordRequest;
import vn.needy.vendor.data.source.remote.api.response.BaseResponse;
import vn.needy.vendor.data.source.remote.api.response.CompanyResponse;
import vn.needy.vendor.data.source.remote.api.response.CertificationResponse;

/**
 * Created by lion on 04/10/2017.
 */

public interface VendorApi {

    @POST("v1/authentications")
    Observable<CertificationResponse> login(@Body CredentialsRequest credentialsRequest);

    @POST("v1/users/registers")
    Observable<CertificationResponse> newUser(@Body RegisterUserRequest registerUserRequest);

    @GET("v1/users/existences")
    Observable<BaseResponse> findUserExist(@Query("username") String phoneNumber);

    @POST("v1/users/{username}/passwords")
    Observable<CertificationResponse> resetPassword(@Path("username") String phoneNumber,
                                                    @Body ResetPasswordRequest resetPasswordRequest);

    @GET("v1/companies/dependencies")
    Observable<CompanyResponse> findCompanyInherent();

    @POST("v1/companies/registers")
    Observable<CompanyResponse> registerCompany(@Body RegisterCompanyRequest registerCompanyRequest);
}
