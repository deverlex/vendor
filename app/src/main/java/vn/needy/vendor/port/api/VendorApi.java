package vn.needy.vendor.port.api;

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
import vn.needy.vendor.port.message.BaseResponse;
import vn.needy.vendor.repository.remote.attribute.response.AttributeInfoResp;
import vn.needy.vendor.repository.remote.attribute.response.AttributesResp;
import vn.needy.vendor.repository.remote.user.request.LoginReq;
import vn.needy.vendor.repository.remote.user.response.LoginResp;
import vn.needy.vendor.repository.remote.user.response.TokenResponse;
import vn.needy.vendor.repository.remote.category.response.CategoriesResp;
import vn.needy.vendor.repository.remote.company.request.RegisterCompanyRequest;
import vn.needy.vendor.repository.remote.company.request.UpdateCompanyInfoRequest;
import vn.needy.vendor.repository.remote.company.response.CompanyInfoResponse;
import vn.needy.vendor.repository.remote.user.request.RegisterUserReq;
import vn.needy.vendor.repository.remote.user.request.ResetAccountReq;
import vn.needy.vendor.repository.remote.user.request.UpdateUserInfoRequest;
import vn.needy.vendor.repository.remote.user.response.CompanyResp;
import vn.needy.vendor.repository.remote.user.response.UserInfoResponse;

/**
 * Created by lion on 04/10/2017.
 */

public interface VendorApi {

    @POST("v1/authentications")
    Observable<LoginResp> login(@Body LoginReq request);

    @POST("v1/users")
    Observable<TokenResponse> registerUser(@Body RegisterUserReq request);

    @GET("v1/users/finds")
    Observable<BaseResponse> findUserExist(@Query("username") String phoneNumber);

    @POST("v1/users/resets")
    Observable<TokenResponse> resetPassword(@Query("username") String phoneNumber,
                                            @Body ResetAccountReq request);

    @GET("v1/users/informations/details")
    Observable<UserInfoResponse> getUserInformation();

    @PUT("v1/users/informations/details")
    Observable<BaseResponse> updateUserInformation(@Body UpdateUserInfoRequest request);

    @GET("v1/companies/users")
    Observable<CompanyResp> findOurCompany();

    @GET("v1/companies/{company_id}/informations/details")
    Observable<CompanyInfoResponse> getCompanyInformation(@Path(value = "company_id") String companyId);

    @PUT("v1/companies/{company_id}/informations/details")
    Observable<BaseResponse> updateCompanyInformation(@Path(value = "company_id") String companyId,
                                                      @Body UpdateCompanyInfoRequest infoRequest);

    @POST("v1/companies")
    Observable<CompanyInfoResponse> registerCompany(@Body RegisterCompanyRequest request);

    /**************************************************************************************/

    @GET("v1/categories/{category}/childs")
    Observable<CategoriesResp> getCategories(@Path("category") String category);

    @GET("v1/categories/{category}/childs")
    Observable<CategoriesResp> getCompanyCategories(@Path("category") String category,
                                                    @Query("company_id") String companyId);

    @GET("v1/attributes/lists")
    Observable<AttributeInfoResp> getAttributesCategory(@Query("category_name") String category);


    @PUT("v1/companies/{company_id}/staffs/fcm_tokens")
    Observable<BaseResponse> updateStaffFcmToken(@Path("company_id") String companyId,
                                                 @Query("token") String token);

    // POST new images
    @Multipart
    @POST("v1/pn/products/{product_id}/images")
    Observable<BaseResponse> uploadImageProduct(@Path("product_id") long productId,
                                                @Part MultipartBody.Part image);
}
