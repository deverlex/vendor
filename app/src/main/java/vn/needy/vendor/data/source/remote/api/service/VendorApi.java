package vn.needy.vendor.data.source.remote.api.service;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import vn.needy.vendor.data.source.remote.api.request.LoginRequest;
import vn.needy.vendor.data.source.remote.api.request.RegisterCompanyRequest;
import vn.needy.vendor.data.source.remote.api.response.CompanyResponse;
import vn.needy.vendor.data.source.remote.api.response.LoginResponse;

/**
 * Created by lion on 04/10/2017.
 */

public interface VendorApi {

    @POST("api/v1/authenticate")
    Observable<LoginResponse> login(@HeaderMap Map<String, String> header, @Body LoginRequest loginRequest);

    @GET("api/v1/companies")
    Observable<CompanyResponse> getCompany();

    @POST("api/v1/companies/register")
    Observable<CompanyResponse> registerCompany(@Body RegisterCompanyRequest registerCompanyRequest);
}
