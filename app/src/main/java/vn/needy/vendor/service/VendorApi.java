package vn.needy.vendor.service;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import vn.needy.vendor.api.v1.attrs.response.AttributeResponse;
import vn.needy.vendor.api.v1.auth.request.CredentialsRequest;
import vn.needy.vendor.api.v1.company.request.RegisterCompanyRequest;
import vn.needy.vendor.api.v1.company.request.UpdateCompanyInfoRequest;
import vn.needy.vendor.api.v1.user.request.RegisterUserRequest;
import vn.needy.vendor.api.v1.user.request.ResetPasswordRequest;
import vn.needy.vendor.api.base.BaseResponse;
import vn.needy.vendor.api.v1.category.response.CategoryResponse;
import vn.needy.vendor.api.v1.company.response.CompanyResponse;
import vn.needy.vendor.api.v1.auth.response.CertificationResponse;
import vn.needy.vendor.api.v1.user.request.UpdateUserInfoRequest;
import vn.needy.vendor.api.v1.user.response.UserResponse;
import vn.needy.vendor.database.model.Attribute;

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

    @PUT("v1/users")
    Observable<BaseResponse> updateUserInformation(@Body UpdateUserInfoRequest request);

    @GET("v1/companies")
    Observable<CompanyResponse> getCompanyInformation();

    @PUT("v1/companies/{company_id}")
    Observable<BaseResponse> updateCompanyInformation(@Path(value = "company_id") String companyId, @Body UpdateCompanyInfoRequest infoRequest);

    @POST("v1/companies")
    Observable<CompanyResponse> registerCompany(@Body RegisterCompanyRequest request);

    @PUT("v1/companies/{company_id}/staffs/fcm_tokens")
    Observable<BaseResponse> updateStaffFcmToken(@Path("company_id") String companyId,
                                                      @Query("token") String token);

    @GET("v1/categories/{category}")
    Observable<CategoryResponse> getLinkCategories(@Path("category") String category);

    @GET("v1/categories/{category}")
    Observable<CategoryResponse> getCompanyLinkCategories(@Path("category") String category,
                                                          @Query("company_id") String companyId);

    @GET("v1/attributes")
    Observable<AttributeResponse> getAttributeCategory(@Query("category_name") String category);

    // POST new images
    @Multipart
    @POST("v1/pn/products/{product_id}/images")
    Observable<BaseResponse> uploadImageProduct(@Path("product_id") long productId,
                                                @Part MultipartBody.Part image);
}
