package vn.needy.vendor.screen.companyProfile;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.widget.ScrollView;

import vn.needy.vendor.R;
import vn.needy.vendor.database.sharedprf.SharedPrefsApi;
import vn.needy.vendor.databinding.ActivityCompanyProfileBinding;
import vn.needy.vendor.port.api.VendorApi;
import vn.needy.vendor.port.service.VendorServiceClient;
import vn.needy.vendor.screen.BaseActivity;
import vn.needy.vendor.widget.WorkaroundMapFragment;

/**
 * Created by truongpq on 04/12/2017.
 */

public class CompanyProfileActivity extends BaseActivity {

    private CompanyProfileContract.ViewModel mViewModel;
    private SharedPrefsApi mPrefsApi;
    private VendorApi mVendorApi;

    private ScrollView mScrollView;

    @Override
    protected void onCreateActivity(Bundle savedInstanceState) {
        super.onCreateActivity(savedInstanceState);
        ActivityCompanyProfileBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_company_profile);

        mVendorApi = VendorServiceClient.getInstance();

        mScrollView = (ScrollView) findViewById(R.id.sv_container);

        WorkaroundMapFragment mapFragment = (WorkaroundMapFragment) getFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.setListener(new WorkaroundMapFragment.OnTouchListener() {
            @Override
            public void onTouch() {
                mScrollView.requestDisallowInterceptTouchEvent(true);
            }
        });


        mViewModel = new CompanyProfileViewModel(this, mapFragment);

        CompanyProfileContract.Presenter presenter= new CompanyProfilePresenter(mViewModel, mVendorApi);
        mViewModel.setPresenter(presenter);

        mViewModel.onStart();

        binding.setViewModel((CompanyProfileViewModel) mViewModel);
    }
}
