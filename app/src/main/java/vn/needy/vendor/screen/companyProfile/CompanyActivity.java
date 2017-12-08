package vn.needy.vendor.screen.companyProfile;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.widget.ScrollView;

import vn.needy.vendor.R;
import vn.needy.vendor.database.sharedprf.SharedPrefsApi;
import vn.needy.vendor.database.sharedprf.SharedPrefsImpl;
import vn.needy.vendor.databinding.ActivityCompanyProfileBinding;
import vn.needy.vendor.screen.BaseActivity;
import vn.needy.vendor.widget.WorkaroundMapFragment;

/**
 * Created by truongpq on 04/12/2017.
 */

public class CompanyActivity extends BaseActivity {
    private CompanyContract.ViewModel mViewModel;
    private SharedPrefsApi mPrefsApi;

    private ScrollView mScrollView;

    @Override
    protected void onCreateActivity(Bundle savedInstanceState) {
        super.onCreateActivity(savedInstanceState);
        ActivityCompanyProfileBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_company_profile);

        mPrefsApi = SharedPrefsImpl.getInstance();

        mScrollView = (ScrollView) findViewById(R.id.sv_container);

        WorkaroundMapFragment mapFragment = (WorkaroundMapFragment) getFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.setListener(new WorkaroundMapFragment.OnTouchListener() {
            @Override
            public void onTouch() {
                mScrollView.requestDisallowInterceptTouchEvent(true);
            }
        });


        mViewModel = new CompanyViewModel(this, mapFragment);

        CompanyContract.Presenter presenter= new CompanyPresenter(mViewModel, mPrefsApi);
        mViewModel.setPresenter(presenter);

        mViewModel.onStart();

        binding.setViewModel((CompanyViewModel) mViewModel);
    }
}
