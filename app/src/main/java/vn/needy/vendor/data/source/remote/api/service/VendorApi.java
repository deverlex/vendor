package vn.needy.vendor.data.source.remote.api.service;


import io.reactivex.Completable;
import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import vn.needy.vendor.data.source.remote.api.request.LoginRequest;
import vn.needy.vendor.data.source.remote.api.request.RegisterCompanyRequest;
import vn.needy.vendor.data.source.remote.api.response.CompanyResponse;

/**
 * Created by lion on 04/10/2017.
 */

public interface VendorApi {

    @POST("api/login")
    Observable<Response<Void>> login(@Body LoginRequest loginRequest);

    @GET("api/company")
    Observable<CompanyResponse> findCompanyInherent();

    @POST("api/v1/companies/register")
    Observable<CompanyResponse> registerCompany(@Body RegisterCompanyRequest registerCompanyRequest);
}
