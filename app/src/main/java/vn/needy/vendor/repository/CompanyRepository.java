package vn.needy.vendor.repository;

import io.reactivex.Observable;
import vn.needy.vendor.model.Company;
import vn.needy.vendor.repository.remote.company.request.UpdateCompanyInfoRequest;
import vn.needy.vendor.repository.remote.company.request.RegisterCompanyRequest;
import vn.needy.vendor.port.message.BaseResponse;
import vn.needy.vendor.repository.remote.company.response.CompanyInfoResponse;

/**
 * Created by lion on 07/10/2017.
 */

public class CompanyRepository {

    private CompanyData.Remote mRemote;
    private CompanyData.Local mLocal;

    public CompanyRepository(CompanyData.Remote remote, CompanyData.Local local) {
        mRemote = remote;
        mLocal = local;
    }

    public Observable<CompanyInfoResponse> getCompanyInformation(String companyId) {
        return mRemote.getCompanyInformation(companyId);
    }

    public Observable<CompanyInfoResponse> registerCompany(RegisterCompanyRequest request) {
        return mRemote.registerCompany(request);
    }

    public Observable<BaseResponse> updateStaffFcmToken(String companyId, String fcmToken) {
        return mRemote.updateStaffFcmToken(companyId, fcmToken);
    }

    public Observable<BaseResponse> updateCompanyInformation(String companyId, UpdateCompanyInfoRequest infoRequest) {
        return mRemote.updateCompanyInformation(companyId, infoRequest);
    }

    public Observable<Void> saveCompany(Company company) {
        return mLocal.saveCompany(company);
    }

    public String getCompanyId() {
        return mLocal.getCompanyId();
    }
}
