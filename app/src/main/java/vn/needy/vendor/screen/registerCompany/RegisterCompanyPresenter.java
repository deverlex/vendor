package vn.needy.vendor.screen.registerCompany;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.text.TextUtils;

import java.io.IOException;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import vn.needy.vendor.R;
import vn.needy.vendor.database.sharedprf.SharedPrefsImpl;
import vn.needy.vendor.port.api.VendorApi;
import vn.needy.vendor.port.message.BaseCode;
import vn.needy.vendor.port.message.ResponseWrapper;
import vn.needy.vendor.repository.CompanyRepository;
import vn.needy.vendor.port.error.BaseException;
import vn.needy.vendor.port.error.SafetyError;
import vn.needy.vendor.repository.StoreRepository;
import vn.needy.vendor.repository.local.CompanyDataLocal;
import vn.needy.vendor.repository.local.StoreDataLocal;
import vn.needy.vendor.repository.remote.company.CompanyRemoteData;
import vn.needy.vendor.repository.remote.company.request.RegisterCompanyRequest;
import vn.needy.vendor.repository.remote.company.response.RegisterComapnyRespone;
import vn.needy.vendor.repository.remote.store.StoreDataRemote;
import vn.needy.vendor.repository.remote.store.request.CreateStoreRequest;
import vn.needy.vendor.repository.remote.store.response.CreateStoreRespone;

/**
 * Created by lion on 07/10/2017.
 */

public class RegisterCompanyPresenter implements RegisterCompanyContract.Presenter {

    private RegisterCompanyContract.ViewModel mViewModel;
    private Context mContext;
    private CompanyRepository mCompanyRepository;
    private StoreRepository mStoreRepository;
    private Geocoder mGeocoder;

    public RegisterCompanyPresenter(Context context, RegisterCompanyContract.ViewModel viewModel,
                                    VendorApi vendorApi) {
        mContext = context;
        mViewModel = viewModel;
        mGeocoder = new Geocoder(mContext);
        mCompanyRepository = new CompanyRepository(
                new CompanyRemoteData(vendorApi),
                new CompanyDataLocal(SharedPrefsImpl.getInstance())
        );

        mStoreRepository = new StoreRepository(
                new StoreDataRemote(vendorApi),
                new StoreDataLocal(SharedPrefsImpl.getInstance())
        );
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void registerCompany(RegisterCompanyRequest companyRequest, final CreateStoreRequest storeRequest) {
        if (!validateDataInput(companyRequest, storeRequest)) return;

        mCompanyRepository.registerCompany(companyRequest)
            .subscribeOn(Schedulers.io())
            .doOnSubscribe(new Consumer<Disposable>() {
                @Override
                public void accept(Disposable disposable) throws Exception {
                    mViewModel.onShowProgressBar();
                }
            })
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Consumer<ResponseWrapper<RegisterComapnyRespone>>() {
                @Override
                public void accept(ResponseWrapper<RegisterComapnyRespone> companyResponse) throws Exception {
                    if (companyResponse.getCode() == BaseCode.OK) {
                        RegisterComapnyRespone data = companyResponse.getData();
                        if (data != null) {
                            long companyId = data.getCompanyId();
                            createStore(companyId, storeRequest);
                        } else {
                            mViewModel.onHideProgressBar();
                            mViewModel.onRegisterError(companyResponse.getMessage());
                        }
                    } else {
                        mViewModel.onHideProgressBar();
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
    public boolean validateDataInput(RegisterCompanyRequest request, CreateStoreRequest storeRequest) {
        boolean isValidate = true;
        if (TextUtils.isEmpty(request.getCompanyName())) {
            isValidate = false;
            mViewModel.onInputCompanyError(R.string.company_name_empty);
        } else if (TextUtils.isEmpty(request.getCompanyAddress())) {
            isValidate = false;
            mViewModel.onInputOfficeAddressError(R.string.office_address_empty);
        } else if (TextUtils.isEmpty(request.getOpeningTime())) {
            isValidate = false;
            mViewModel.onInputOpeningTimeComanyError(R.string.opening_time_empty);
        } else if (TextUtils.isEmpty(request.getClosingTime())) {
            isValidate = false;
            mViewModel.onInputClosingTimeComanyError(R.string.closing_time_empty);
        } else if (TextUtils.isEmpty(storeRequest.getStoreName())) {
            isValidate = false;
            mViewModel.onInputStoreNameError(R.string.store_name_empty);
        } else if (TextUtils.isEmpty(storeRequest.getAddress())) {
            isValidate = false;
            mViewModel.onInputStoreAddressError(R.string.store_address_empty);
        } else if (TextUtils.isEmpty(storeRequest.getOpeningTime())) {
            isValidate = false;
            mViewModel.onInputOpeningTimeStoreError(R.string.opening_time_empty);
        } else if (TextUtils.isEmpty(storeRequest.getClosingTime())) {
            isValidate = false;
            mViewModel.onInputClosingTimeStoreError(R.string.closing_time_empty);
        }
        return isValidate;
    }

    private void createStore(final long companyId, CreateStoreRequest request) {
        // Get lat, lng of store address
        try {
            List<Address> addresses = mGeocoder.getFromLocationName(request.getAddress(), 1);
            if (addresses.size() > 0) {
                Address address = addresses.get(0);
                request.setLat(address.getLatitude());
                request.setLng(address.getLongitude());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        mStoreRepository.createStore(companyId, request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseWrapper<CreateStoreRespone>>() {
                    @Override
                    public void accept(ResponseWrapper<CreateStoreRespone> respone) throws Exception {
                        CreateStoreRespone data = respone.getData();
                        if (data != null) {
                            long storeId = data.getStoreId();
                            mCompanyRepository.saveCompanyId(companyId);
                            mStoreRepository.saveStoreId(storeId);

                            mViewModel.onHideProgressBar();
                            mViewModel.onRegisterSuccess();
                        } else {
                            mViewModel.onHideProgressBar();
                            mViewModel.onRegisterError(respone.getMessage());
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
}
