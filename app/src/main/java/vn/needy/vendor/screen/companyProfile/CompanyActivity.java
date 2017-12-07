package vn.needy.vendor.screen.companyProfile;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import vn.needy.vendor.R;
import vn.needy.vendor.databinding.ActivityCompanyProfileBinding;
import vn.needy.vendor.screen.BaseActivity;

/**
 * Created by truongpq on 04/12/2017.
 */

public class CompanyActivity extends BaseActivity {
    private CompanyContract.ViewModel mViewModel;

    @Override
    protected void onCreateActivity(Bundle savedInstanceState) {
        super.onCreateActivity(savedInstanceState);
        ActivityCompanyProfileBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_company_profile);

        mViewModel = new CompanyViewModel();

        CompanyContract.Presenter presenter= new CompanyPresenter(mViewModel);
        mViewModel.setPresenter(presenter);

        mViewModel.onStart();

        binding.setViewModel((CompanyViewModel) mViewModel);
    }
}
