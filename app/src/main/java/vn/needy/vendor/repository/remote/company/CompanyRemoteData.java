package vn.needy.vendor.repository.remote.company;

import io.reactivex.Observable;
import vn.needy.vendor.port.message.RequestWrapper;
import vn.needy.vendor.port.message.ResponseWrapper;
import vn.needy.vendor.port.api.VendorApi;
import vn.needy.vendor.repository.CompanyData;
import vn.needy.vendor.repository.remote.BaseDataRemote;
import vn.needy.vendor.repository.remote.company.request.RegisterCompanyRequest;
import vn.needy.vendor.repository.remote.company.request.UpdateCompanyInfoRequest;
import vn.needy.vendor.repository.remote.company.response.CompanyInfoResponse;
import vn.needy.vendor.repository.remote.company.response.RegisterComapnyRespone;
import vn.needy.vendor.repository.remote.user.response.CompanyResponse;

/**
 * Created by lion on 10/12/2017.
 */

public class CompanyRemoteData extends BaseDataRemote<VendorApi> implements CompanyData.Remote {

    public CompanyRemoteData(VendorApi api) {
        super(api);
    }

    @Override
    public Observable<ResponseWrapper<CompanyResponse>> findOurCompany() {
        return mApi.findOurCompany();
    }

    @Override
    public Observable<ResponseWrapper<CompanyInfoResponse>> getCompanyInformation(String companyId) {
        return mApi.getCompanyInformation(companyId);
    }

    @Override
    public Observable<ResponseWrapper<RegisterComapnyRespone>> registerCompany(RegisterCompanyRequest request) {
        return mApi.registerCompany(new RequestWrapper<RegisterCompanyRequest>().setData(request));
    }

    @Override
    public Observable<ResponseWrapper> updateCompanyInformation(String companyId, UpdateCompanyInfoRequest request) {
        return mApi.updateCompanyInformation(companyId, new RequestWrapper<UpdateCompanyInfoRequest>().setData(request));
    }

    @Override
    public Observable<ResponseWrapper> updateStaffFcmToken(String companyId, String fcmToken) {
        return mApi.updateStaffFcmToken(companyId, fcmToken);
    }
}
