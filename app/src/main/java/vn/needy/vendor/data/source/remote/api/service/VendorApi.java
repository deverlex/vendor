package vn.needy.vendor.data.source.remote.api.service;


import java.util.List;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import vn.needy.vendor.data.model.Category;
import vn.needy.vendor.data.source.remote.api.request.ActiveAccountRequest;
import vn.needy.vendor.data.source.remote.api.request.CredentialsRequest;
import vn.needy.vendor.data.source.remote.api.request.RegisterCompanyRequest;
import vn.needy.vendor.data.source.remote.api.request.RegisterUserRequest;
import vn.needy.vendor.data.source.remote.api.request.ResetPasswordRequest;
import vn.needy.vendor.data.source.remote.api.response.BaseResponse;
import vn.needy.vendor.data.source.remote.api.response.CategoryResponse;
import vn.needy.vendor.data.source.remote.api.response.CompanyResponse;
import vn.needy.vendor.data.source.remote.api.response.CertificationResponse;
import vn.needy.vendor.data.source.remote.api.response.UserResponse;

/**
 * Created by lion on 04/10/2017.
 */

public interface VendorApi {

    @POST("v1/authentications")
    Observable<CertificationResponse> login(@Body CredentialsRequest request);

    @POST("v1/users/news")
    Observable<CertificationResponse> registerUser(@Body RegisterUserRequest request);

    @GET("v1/users/find")
    Observable<BaseResponse> findUserExist(@Query("username") String phoneNumber);

    @POST("v1/users/reset")
    Observable<CertificationResponse> resetPassword(@Query("username") String phoneNumber,
                                                @Body ResetPasswordRequest request);

    @GET("v1/users")
    Observable<UserResponse> getUserInformation();

    @POST("v1/users/activeness")
    Observable<BaseResponse> activeAccount(@Body ActiveAccountRequest request);

    @GET("v1/companies")
    Observable<CompanyResponse> getCompanyInformation();

    @GET("v1/companies/dependencies")
    Observable<CompanyResponse> findCompanyInformation();

    @POST("v1/companies")
    Observable<CompanyResponse> registerCompany(@Body RegisterCompanyRequest request);

    @PUT("v1/companies/{company_id}/staffs/fcm_tokens")
    Observable<BaseResponse> updateStaffFcmToken(@Path("company_id") String companyId,
                                                      @Query("token") String token);

    @GET("v1/pn/categories")
    Observable<CategoryResponse> getCategories(@Query("company_id") String companyId);

    @GET("v1/pn/categories/{category}")
    Observable<CategoryResponse> getSubCategories(@Path("category") String category,
                                                  @Query("company_id") String companyId);
}
