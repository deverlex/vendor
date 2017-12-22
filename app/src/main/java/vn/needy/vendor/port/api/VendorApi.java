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
import vn.needy.vendor.port.message.RequestWrapper;
import vn.needy.vendor.port.message.ResponseWrapper;
import vn.needy.vendor.repository.remote.attribute.response.AttributeInfoResp;
import vn.needy.vendor.repository.remote.user.request.LoginReq;
import vn.needy.vendor.repository.remote.user.response.BusinessInfoResp;
import vn.needy.vendor.repository.remote.user.response.LoginResp;
import vn.needy.vendor.repository.remote.user.response.TokenResponse;
import vn.needy.vendor.repository.remote.category.response.CategoriesResp;
import vn.needy.vendor.repository.remote.company.request.RegisterCompanyRequest;
import vn.needy.vendor.repository.remote.company.request.UpdateCompanyInfoReq;
import vn.needy.vendor.repository.remote.company.response.CompanyInfoResp;
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
    Observable<ResponseWrapper<LoginResp>> login(@Body RequestWrapper<LoginReq> request);

    @POST("v1/users")
    Observable<ResponseWrapper<TokenResponse>> registerUser(@Body RequestWrapper<RegisterUserReq> request);

    @GET("v1/users/finds")
    Observable<ResponseWrapper> findUserExist(@Query("username") String phoneNumber);

    @POST("v1/users/resets")
    Observable<ResponseWrapper<TokenResponse>> resetPassword(@Query("username") String phoneNumber,
                                                             @Body RequestWrapper<ResetAccountReq> request);

    @GET("v1/users/informations/details")
    Observable<ResponseWrapper<UserInfoResponse>> getUserInformation();

    @PUT("v1/users/informations/details")
    Observable<ResponseWrapper> updateUserInformation(@Body RequestWrapper<UpdateUserInfoRequest> request);

    @GET("v1/users/businesses/informations")
    Observable<ResponseWrapper<BusinessInfoResp>> getBusinessInformation();

    @GET("v1/companies/users")
    Observable<ResponseWrapper<CompanyResp>> findOurCompany();

    @GET("v1/companies/{company_id}/informations/details")
    Observable<ResponseWrapper<CompanyInfoResp>> getCompanyInformation(@Path(value = "company_id") String companyId);

    @PUT("v1/companies/{company_id}/informations/details")
    Observable<ResponseWrapper> updateCompanyInformation(@Path(value = "company_id") String companyId,
                                                         @Body RequestWrapper<UpdateCompanyInfoReq> infoRequest);

    @POST("v1/companies")
    Observable<ResponseWrapper<CompanyInfoResp>> registerCompany(@Body RequestWrapper<RegisterCompanyRequest> request);

    @GET("v1/categories/{category}/childs")
    Observable<ResponseWrapper<CategoriesResp>> getCategories(@Path("category") String category);

    @GET("v1/categories/{category}/childs")
    Observable<ResponseWrapper<CategoriesResp>> getCompanyCategories(@Path("category") String category,
                                                                     @Query("company_id") String companyId);

    @GET("v1/attributes/lists")
    Observable<ResponseWrapper<AttributeInfoResp>> getAttributesCategory(@Query("category_name") String category);

    /**************************************************************************************/

    @PUT("v1/companies/{company_id}/staffs/fcm_tokens")
    Observable<ResponseWrapper> updateStaffFcmToken(@Path("company_id") String companyId,
                                                    @Query("token") String token);

    // POST new images
    @Multipart
    @POST("v1/pn/products/{product_id}/images")
    Observable<ResponseWrapper> uploadImageProduct(@Path("product_id") long productId,
                                                   @Part MultipartBody.Part image);
}
