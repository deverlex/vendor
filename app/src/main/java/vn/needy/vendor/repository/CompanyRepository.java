package vn.needy.vendor.repository;

import io.reactivex.Observable;
import vn.needy.vendor.model.Company;
import vn.needy.vendor.repository.remote.company.request.UpdateCompanyInfoReq;
import vn.needy.vendor.repository.remote.company.request.RegisterCompanyRequest;
import vn.needy.vendor.port.message.ResponseWrapper;
import vn.needy.vendor.repository.remote.company.response.CompanyInfoResp;
import vn.needy.vendor.repository.remote.user.response.CompanyResp;

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

    public Observable<ResponseWrapper<CompanyResp>> findOurCompany() {
        return mRemote.findOurCompany();
    }

    public Observable<ResponseWrapper<CompanyInfoResp>> getCompanyInformation(String companyId) {
        return mRemote.getCompanyInformation(companyId);
    }

    public Observable<ResponseWrapper<CompanyInfoResp>> registerCompany(RegisterCompanyRequest request) {
        return mRemote.registerCompany(request);
    }

    public Observable<ResponseWrapper> updateStaffFcmToken(String companyId, String fcmToken) {
        return mRemote.updateStaffFcmToken(companyId, fcmToken);
    }

    public Observable<ResponseWrapper> updateCompanyInformation(String companyId, UpdateCompanyInfoReq infoRequest) {
        return mRemote.updateCompanyInformation(companyId, infoRequest);
    }

    /** LOCAL */
    public Observable<Company> getOurCompanyAsync() {
        return mLocal.getOurCompanyAsync();
    }

    public void saveCompanySync(Company company) {
        mLocal.saveCompanySync(company);
    }

    public String getCompanyIdSync() {
        return mLocal.getOurCompanyIdSync();
    }
}
