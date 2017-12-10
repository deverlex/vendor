package vn.needy.vendor.api;

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
import vn.needy.vendor.datasource.BaseResponse;
import vn.needy.vendor.datasource.attribute.response.AttributesResponse;
import vn.needy.vendor.datasource.authentication.request.LoginRequest;
import vn.needy.vendor.datasource.authentication.response.TokenResponse;
import vn.needy.vendor.datasource.category.response.CategoriesResponse;
import vn.needy.vendor.datasource.company.request.RegisterCompanyRequest;
import vn.needy.vendor.datasource.company.request.UpdateCompanyInfoRequest;
import vn.needy.vendor.datasource.company.response.CompanyInfoResponse;
import vn.needy.vendor.datasource.user.request.RegisterUserRequest;
import vn.needy.vendor.datasource.user.request.ResetPasswordRequest;
import vn.needy.vendor.datasource.user.request.UpdateUserInfoRequest;
import vn.needy.vendor.datasource.user.response.BusinessIdResponse;
import vn.needy.vendor.datasource.user.response.UserInfoResponse;

/**
 * Created by lion on 04/10/2017.
 */

public interface VendorApi {

    @POST("v1/users/news")
    Observable<TokenResponse> registerUser(@Body RegisterUserRequest request);

    @GET("v1/users/find")
    Observable<BaseResponse> findUserExist(@Query("username") String phoneNumber);

    @POST("v1/users/reset")
    Observable<TokenResponse> resetPassword(@Query("username") String phoneNumber,
                                            @Body ResetPasswordRequest request);

    @GET("v1/users")
    Observable<UserInfoResponse> getUserInformation();

    @PUT("v1/users")
    Observable<BaseResponse> updateUserInformation(@Body UpdateUserInfoRequest request);

    @GET("v1/users/companies")
    Observable<BusinessIdResponse> findUserBusinessId();

    @GET("v1/companies/{company_id}")
    Observable<CompanyInfoResponse> getCompanyInformation(@Path(value = "company_id") String companyId);

    @PUT("v1/companies/{company_id}")
    Observable<BaseResponse> updateCompanyInformation(@Path(value = "company_id") String companyId,
                                                      @Body UpdateCompanyInfoRequest infoRequest);

    @POST("v1/companies")
    Observable<CompanyInfoResponse> registerCompany(@Body RegisterCompanyRequest request);

    @PUT("v1/companies/{company_id}/staffs/fcm_tokens")
    Observable<BaseResponse> updateStaffFcmToken(@Path("company_id") String companyId,
                                                 @Query("token") String token);

    @GET("v1/categories/{category}")
    Observable<CategoriesResponse> getCategories(@Path("category") String category);

    @GET("v1/categories/{category}")
    Observable<CategoriesResponse> getCompanyCategories(@Path("category") String category,
                                                        @Query("company_id") String companyId);

    @POST("v1/authentications")
    Observable<TokenResponse> login(@Body LoginRequest request);

    @GET("v1/attributes")
    Observable<AttributesResponse> getAttributeCategory(@Query("category_name") String category);


    // POST new images
    @Multipart
    @POST("v1/pn/products/{product_id}/images")
    Observable<BaseResponse> uploadImageProduct(@Path("product_id") long productId,
                                                @Part MultipartBody.Part image);
}
