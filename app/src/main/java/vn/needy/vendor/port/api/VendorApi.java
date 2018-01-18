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
import vn.needy.vendor.repository.remote.attribute.response.AttributeInfoResponse;
import vn.needy.vendor.repository.remote.product.respone.ProductPnInfoResponse;
import vn.needy.vendor.repository.remote.store.request.UpdateStoreInfoRequest;
import vn.needy.vendor.repository.remote.store.response.StoreInfoResponse;
import vn.needy.vendor.repository.remote.user.request.LoginRequest;
import vn.needy.vendor.repository.remote.user.response.BusinessInfoResponse;
import vn.needy.vendor.repository.remote.user.response.TokenResponse;
import vn.needy.vendor.repository.remote.category.response.CategoriesResponse;
import vn.needy.vendor.repository.remote.company.request.RegisterCompanyRequest;
import vn.needy.vendor.repository.remote.company.request.UpdateCompanyInfoRequest;
import vn.needy.vendor.repository.remote.company.response.CompanyInfoResponse;
import vn.needy.vendor.repository.remote.user.request.RegisterUserRequest;
import vn.needy.vendor.repository.remote.user.request.ResetAccountRequest;
import vn.needy.vendor.repository.remote.user.request.UpdateUserInfoRequest;
import vn.needy.vendor.repository.remote.user.response.CompanyResponse;
import vn.needy.vendor.repository.remote.user.response.UserInfoResponse;

/**
 * Created by lion on 04/10/2017.
 */

public interface VendorApi {

    @POST("v1/users/tokens")
    Observable<ResponseWrapper<TokenResponse>> login(@Body RequestWrapper<LoginRequest> request);

    @POST("v1/users")
    Observable<ResponseWrapper<TokenResponse>> registerUser(@Body RequestWrapper<RegisterUserRequest> request);

    @GET("v1/users/tokens/refresh")
    Observable<ResponseWrapper<TokenResponse>> refreshToken(@Query("refresh_token") String refreshToken);

    @GET("v1/users/{username}/exist")
    Observable<ResponseWrapper> findUserExist(@Path("username") String phoneNumber);

    @POST("v1/users/{username}/password")
    Observable<ResponseWrapper<TokenResponse>> resetPassword(@Path("username") String phoneNumber,
                                                             @Body RequestWrapper<ResetAccountRequest> request);

    @GET("v1/users/informations/details")
    Observable<ResponseWrapper<UserInfoResponse>> getUserInformation();

    @PUT("v1/users/informations/details")
    Observable<ResponseWrapper> updateUserInformation(@Body RequestWrapper<UpdateUserInfoRequest> request);

    @GET("v1/companies/employees/own")
    Observable<ResponseWrapper> checkOwnCompanyExist();

    @GET("v1/users/businesses/informations")
    Observable<ResponseWrapper<BusinessInfoResponse>> getBusinessInformation();

    @GET("v1/companies/users")
    Observable<ResponseWrapper<CompanyResponse>> findOurCompany();

    @GET("v1/companies/{company_id}/informations/details")
    Observable<ResponseWrapper<CompanyInfoResponse>> getCompanyInformation(@Path(value = "company_id") String companyId);

    @PUT("v1/companies/{company_id}/informations/details")
    Observable<ResponseWrapper> updateCompanyInformation(@Path(value = "company_id") String companyId,
                                                         @Body RequestWrapper<UpdateCompanyInfoRequest> infoRequest);

    @POST("v1/companies")
    Observable<ResponseWrapper<CompanyInfoResponse>> registerCompany(@Body RequestWrapper<RegisterCompanyRequest> request);

    @GET("v1/categories/{category}/childs")
    Observable<ResponseWrapper<CategoriesResponse>> getCategories(@Path("category") String category);

    @GET("v1/categories/{category}/childs")
    Observable<ResponseWrapper<CategoriesResponse>> getCompanyCategories(@Path("category") String category,
                                                                         @Query("company_id") String companyId);

    @GET("v1/attributes/lists")
    Observable<ResponseWrapper<AttributeInfoResponse>> getAttributesCategory(@Query("category_name") String category);

    @GET("v1/stores/{store_id}/infomations/details")
    Observable<ResponseWrapper<StoreInfoResponse>> getStoreInfo(@Path(value = "store_id") String storeId);

    @PUT("v1/stores/{store_id}/infomations/details")
    Observable<ResponseWrapper> updateStoreInfo(@Path("store_id") String storeId,
                                                @Body RequestWrapper<UpdateStoreInfoRequest> infoReq);
    /**************************************************************************************/

    @PUT("v1/companies/{company_id}/staffs/fcm_tokens")
    Observable<ResponseWrapper> updateStaffFcmToken(@Path("company_id") String companyId,
                                                    @Query("token") String token);

    // Product
    /**************************************************************************************/

    @POST("v1/pn/products")
    Observable<ResponseWrapper> addProductPn(
            @Query("product_type") String productType,
            @Query("company_id") String companyId,
            @Query("store_id") String storeId,
            @Body RequestWrapper<Object> request);

    @GET("v1/pn/products")
    Observable<ResponseWrapper<ProductPnInfoResponse>> getAllProductsPnOfCompany(
            @Query("company_id") String companyId,
            @Query("category") String category);


    // POST new images
    @Multipart
    @POST("v1/pn/products/{product_id}/images")
    Observable<ResponseWrapper> uploadImageProduct(@Path("product_id") long productId,
                                                   @Part MultipartBody.Part image);
}
