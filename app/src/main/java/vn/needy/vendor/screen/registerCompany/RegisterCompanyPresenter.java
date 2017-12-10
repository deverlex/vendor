package vn.needy.vendor.screen.registerCompany;

import android.text.TextUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import vn.needy.vendor.R;
import vn.needy.vendor.database.realm.RealmApi;
import vn.needy.vendor.port.api.VendorApi;
import vn.needy.vendor.repository.CompanyRepository;
import vn.needy.vendor.port.error.BaseException;
import vn.needy.vendor.port.error.SafetyError;
import vn.needy.vendor.repository.local.CompanyDataLocal;
import vn.needy.vendor.repository.remote.company.CompanyRemoteData;
import vn.needy.vendor.repository.remote.company.request.RegisterCompanyRequest;
import vn.needy.vendor.repository.remote.company.response.CompanyInfoResponse;
import vn.needy.vendor.database.sharedprf.SharedPrefsImpl;

/**
 * Created by lion on 07/10/2017.
 */

public class RegisterCompanyPresenter implements RegisterCompanyContract.Presenter {

    private RegisterCompanyContract.ViewModel mViewModel;
    private CompanyRepository mCompanyRepository;

    public RegisterCompanyPresenter(RegisterCompanyContract.ViewModel viewModel,
                                    VendorApi vendorApi, RealmApi realmApi) {
        mViewModel = viewModel;
        mCompanyRepository = new CompanyRepository(
                new CompanyRemoteData(vendorApi),
                new CompanyDataLocal(realmApi)
        );
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void registerCompany(RegisterCompanyRequest request) {
        if (!validateDataInput(request)) return;
        mCompanyRepository.registerCompany(request)
            .subscribeOn(Schedulers.io())
            .doOnSubscribe(new Consumer<Disposable>() {
                @Override
                public void accept(Disposable disposable) throws Exception {
                    mViewModel.onShowProgressBar();
                }
            })
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Consumer<CompanyInfoResponse>() {
                @Override
                public void accept(CompanyInfoResponse companyResponse) throws Exception {
                    if (companyResponse.getCompany() != null) {
                        mViewModel.onRegisterSuccess();
                        mViewModel.onHideProgressBar();
                    } else {
                        mViewModel.onRegisterError(companyResponse.getMessage());
                    }
                }
            }, new SafetyError() {
                @Override
                public void onSafetyError(BaseException error) {
                    mViewModel.onHideProgressBar();
                    mViewModel.onRegisterError(R.string.error_service);
                }
            });
    }

    @Override
    public boolean validateDataInput(RegisterCompanyRequest request) {
        boolean isValidate = true;
        if (TextUtils.isEmpty(request.getCompanyName())) {
            isValidate = false;
            mViewModel.onInputCompanyError(R.string.company_name_empty);
        } else if (TextUtils.isEmpty(request.getOfficeAddress())) {
            isValidate = false;
            mViewModel.onInputOfficeAddressError(R.string.office_address_empty);
        } else if (TextUtils.isEmpty(request.getStoreName())) {
            isValidate = false;
            mViewModel.onInputStoreNameError(R.string.store_name_empty);
        } else if (TextUtils.isEmpty(request.getStoreAddress())) {
            isValidate = false;
            mViewModel.onInputStoreAddressError(R.string.store_address_empty);
        }
        return isValidate;
    }
}
