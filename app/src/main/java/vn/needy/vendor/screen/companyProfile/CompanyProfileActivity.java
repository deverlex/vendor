package vn.needy.vendor.screen.companyProfile;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import vn.needy.vendor.R;
import vn.needy.vendor.databinding.ActivityCompanyProfileBinding;
import vn.needy.vendor.model.Place;
import vn.needy.vendor.port.api.VendorApi;
import vn.needy.vendor.port.service.VendorServiceClient;
import vn.needy.vendor.screen.BaseActivity;
import vn.needy.vendor.screen.place.PlaceActivity;
import vn.needy.vendor.utils.navigator.Navigator;

/**
 * Created by truongpq on 04/12/2017.
 */

public class CompanyProfileActivity extends BaseActivity {

    protected static final int ADDRESS = 1;

    private CompanyProfileContract.ViewModel mViewModel;
    private VendorApi mVendorApi;
    private Navigator mNavigator;

    @Override
    protected void onCreateActivity(Bundle savedInstanceState) {
        super.onCreateActivity(savedInstanceState);
        ActivityCompanyProfileBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_company_profile);

        mVendorApi = VendorServiceClient.getInstance();
        mNavigator = new Navigator(this);

        mViewModel = new CompanyProfileViewModel(this, mNavigator);

        CompanyProfileContract.Presenter presenter= new CompanyProfilePresenter(mViewModel, mVendorApi);
        mViewModel.setPresenter(presenter);

        mViewModel.onStart();

        binding.setViewModel((CompanyProfileViewModel) mViewModel);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADDRESS && resultCode == PlaceActivity.RC_OK) {
            Place place = data.getExtras().getParcelable(PlaceActivity.PLACE);
            mViewModel.updateAddress(place);
        }
    }
}
