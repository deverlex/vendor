package vn.needy.vendor.screen.registerCompany;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;

import vn.needy.vendor.R;
import vn.needy.vendor.databinding.ActivityRegisterCompanyBinding;
import vn.needy.vendor.model.Place;
import vn.needy.vendor.port.api.VendorApi;
import vn.needy.vendor.port.service.VendorServiceClient;
import vn.needy.vendor.screen.BaseActivity;
import vn.needy.vendor.screen.place.PlaceActivity;
import vn.needy.vendor.utils.dialog.DialogManager;
import vn.needy.vendor.utils.navigator.Navigator;

public class RegisterCompanyActivity extends BaseActivity {

    private static final String TAG = RegisterCompanyActivity.class.getName();

    protected static final int COMPANY_ADDRESS = 1;
    protected static final int STORE_ADDRESS = 2;

    private RegisterCompanyContract.ViewModel mViewModel;
    private DialogManager mDialogManager;
    private VendorApi mVendorApi;

    @Override
    protected void onCreateActivity(Bundle savedInstanceState) {
        ActivityRegisterCompanyBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_register_company);

        Navigator navigator = new Navigator(this);
        mDialogManager = new DialogManager(this);

        mVendorApi = VendorServiceClient.getInstance();

        mViewModel = new RegisterCompanyViewModel(this, navigator, mDialogManager);
        RegisterCompanyContract.Presenter presenter =
                new RegisterCompanyPresenter(mViewModel, mVendorApi);

        mViewModel.setPresenter(presenter);

        binding.setViewModel((RegisterCompanyViewModel) mViewModel);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mViewModel.onStart();
    }

    @Override
    protected void onStop() {
        mViewModel.onStop();
        super.onStop();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == COMPANY_ADDRESS && resultCode == PlaceActivity.RC_OK) {
            Place place = data.getExtras().getParcelable(PlaceActivity.PLACE);
            mViewModel.updateCompanyAddress(place);
        } else if (requestCode == STORE_ADDRESS && resultCode == PlaceActivity.RC_OK) {
            Place place = data.getExtras().getParcelable(PlaceActivity.PLACE);
            mViewModel.updateStoreAddress(place);
        }
    }
}
