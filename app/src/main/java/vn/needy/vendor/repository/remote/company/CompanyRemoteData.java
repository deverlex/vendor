package vn.needy.vendor.repository.remote.company;

import io.reactivex.Observable;
import vn.needy.vendor.port.message.ResponseWrapper;
import vn.needy.vendor.port.api.VendorApi;
import vn.needy.vendor.repository.CompanyData;
import vn.needy.vendor.repository.remote.BaseDataRemote;
import vn.needy.vendor.repository.remote.company.request.RegisterCompanyRequest;
import vn.needy.vendor.repository.remote.company.request.UpdateCompanyInfoReq;
import vn.needy.vendor.repository.remote.company.response.CompanyInfoResp;
import vn.needy.vendor.repository.remote.user.response.CompanyResp;

/**
 * Created by lion on 10/12/2017.
 */

public class CompanyRemoteData extends BaseDataRemote<VendorApi> implements CompanyData.Remote {

    public CompanyRemoteData(VendorApi api) {
        super(api);
    }

    @Override
    public Observable<ResponseWrapper<CompanyResp>> findOurCompany() {
        return mApi.findOurCompany();
    }

    @Override
    public Observable<ResponseWrapper<CompanyInfoResp>> getCompanyInformation(String companyId) {
        return mApi.getCompanyInformation(companyId);
    }

    @Override
    public Observable<ResponseWrapper<CompanyInfoResp>> registerCompany(RegisterCompanyRequest request) {
        return mApi.registerCompany(request);
    }

    @Override
    public Observable<ResponseWrapper> updateCompanyInformation(String companyId, UpdateCompanyInfoReq request) {
        return mApi.updateCompanyInformation(companyId, request);
    }

    @Override
    public Observable<ResponseWrapper> updateStaffFcmToken(String companyId, String fcmToken) {
        return mApi.updateStaffFcmToken(companyId, fcmToken);
    }
}
