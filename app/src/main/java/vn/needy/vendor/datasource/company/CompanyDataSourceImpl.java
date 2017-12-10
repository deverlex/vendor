package vn.needy.vendor.datasource.company;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import vn.needy.vendor.datasource.BaseDataSource;
import vn.needy.vendor.datasource.company.request.UpdateCompanyInfoRequest;
import vn.needy.vendor.datasource.company.request.RegisterCompanyRequest;
import vn.needy.vendor.datasource.BaseResponse;
import vn.needy.vendor.datasource.company.response.CompanyInfoResponse;
import vn.needy.vendor.model.Company;
import vn.needy.vendor.service.sharedprf.SharedPrefsApi;
import vn.needy.vendor.service.sharedprf.SharedPrefsKey;

/**
 * Created by lion on 07/10/2017.
 */

public class CompanyDataSourceImpl extends BaseDataSource implements CompanyDataSource {

    private SharedPrefsApi mPrefsApi;

    public CompanyDataSourceImpl(SharedPrefsApi prefsApi) {
        super();
        mPrefsApi = prefsApi;
    }

    public Observable<CompanyInfoResponse> getCompanyInformation() {
        return mVendorApi.getCompanyInformation(getCompanyId()).map(new Function<CompanyInfoResponse, CompanyInfoResponse>() {
            @Override
            public CompanyInfoResponse apply(CompanyInfoResponse response) throws Exception {
                // save company id
                Company company = response.getCompany();
                if (company != null) {
                    String companyId = response.getCompany().getId();
                    saveCompanyId(companyId);
                }
                return response;
            }
        });
    }

    public Observable<CompanyInfoResponse> registerCompany(RegisterCompanyRequest request) {
        return mVendorApi.registerCompany(request);
    }

    public Observable<BaseResponse> updateStaffFcmToken(String companyId, String fcmToken) {
        return mVendorApi.updateStaffFcmToken(companyId, fcmToken);
    }

    public Observable<BaseResponse> updateCompanyInformation(String companyId, UpdateCompanyInfoRequest infoRequest) {
        return mVendorApi.updateCompanyInformation(companyId, infoRequest);
    }

    @Override
    public void saveCompanyId(String companyId) {
        mPrefsApi.put(SharedPrefsKey.COMPANY_ID, companyId);
    }

    @Override
    public String getCompanyId() {
        return mPrefsApi.get(SharedPrefsKey.COMPANY_ID, String.class);
    }
}
