package vn.needy.vendor.data.source.remote.api.service;


import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import vn.needy.vendor.data.source.remote.api.request.CredentialsRequest;
import vn.needy.vendor.data.source.remote.api.request.RegisterCompanyRequest;
import vn.needy.vendor.data.source.remote.api.request.RegisterUserRequest;
import vn.needy.vendor.data.source.remote.api.response.CompanyResponse;
import vn.needy.vendor.data.source.remote.api.response.CertificationResponse;

/**
 * Created by lion on 04/10/2017.
 */

public interface VendorApi {

    @POST("api/login")
    Observable<CertificationResponse> login(@Body CredentialsRequest credentialsRequest);

    @GET("api/company")
    Observable<CompanyResponse> findCompanyInherent();

    @POST("api/register")
    Observable<CertificationResponse> registerUser(@Body RegisterUserRequest registerUserRequest);




    @POST("api/v1/companies/register")
    Observable<CompanyResponse> registerCompany(@Body RegisterCompanyRequest registerCompanyRequest);
}
