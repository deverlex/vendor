package vn.needy.vendor.repository.remote.company;

import io.reactivex.Observable;
import vn.needy.vendor.port.message.BaseResponse;
import vn.needy.vendor.port.api.VendorApi;
import vn.needy.vendor.repository.CompanyData;
import vn.needy.vendor.repository.remote.BaseDataRemote;
import vn.needy.vendor.repository.remote.company.request.RegisterCompanyRequest;
import vn.needy.vendor.repository.remote.company.request.UpdateCompanyInfoRequest;
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
    public Observable<CompanyResp> findOurCompany() {
        return mApi.findOurCompany();
    }

    @Override
    public Observable<CompanyInfoResp> getCompanyInformation(String companyId) {
        return mApi.getCompanyInformation(companyId);
    }

    @Override
    public Observable<CompanyInfoResp> registerCompany(RegisterCompanyRequest request) {
        return mApi.registerCompany(request);
    }

    @Override
    public Observable<BaseResponse> updateCompanyInformation(String companyId, UpdateCompanyInfoRequest request) {
        return mApi.updateCompanyInformation(companyId, request);
    }

    @Override
    public Observable<BaseResponse> updateStaffFcmToken(String companyId, String fcmToken) {
        return mApi.updateStaffFcmToken(companyId, fcmToken);
    }
}
