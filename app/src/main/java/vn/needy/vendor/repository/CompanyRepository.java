package vn.needy.vendor.repository;

import io.reactivex.Observable;
import vn.needy.vendor.domain.Company;
import vn.needy.vendor.repository.remote.company.request.UpdateCompanyInfoRequest;
import vn.needy.vendor.repository.remote.company.request.RegisterCompanyRequest;
import vn.needy.vendor.port.message.ResponseWrapper;
import vn.needy.vendor.repository.remote.company.response.CompanyInfoResponse;
import vn.needy.vendor.repository.remote.company.response.RegisterComapnyRespone;
import vn.needy.vendor.repository.remote.user.response.CompanyResponse;

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

    public Observable<ResponseWrapper<CompanyResponse>> findOurCompany() {
        return mRemote.findOurCompany();
    }

    public Observable<ResponseWrapper<CompanyInfoResponse>> getCompanyInformation(String companyId) {
        return mRemote.getCompanyInformation(companyId);
    }

    public Observable<ResponseWrapper<CompanyInfoResponse>> getCompanyInfo(long companyId) {
        return mRemote.getCompanyInfo(companyId);
    }

    public Observable<ResponseWrapper<RegisterComapnyRespone>> registerCompany(RegisterCompanyRequest request) {
        return mRemote.registerCompany(request);
    }

    public Observable<ResponseWrapper> updateStaffFcmToken(String companyId, String fcmToken) {
        return mRemote.updateStaffFcmToken(companyId, fcmToken);
    }

    public Observable<ResponseWrapper> updateCompanyInfo(long companyId, UpdateCompanyInfoRequest infoRequest) {
        return mRemote.updateCompanyInfo(companyId, infoRequest);
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

    public void saveCompanyId(long companyId) {
        mLocal.saveCompanyId(companyId);
    }

    public long getCompanyId() {
        return mLocal.getCompanyId();
    }
}
