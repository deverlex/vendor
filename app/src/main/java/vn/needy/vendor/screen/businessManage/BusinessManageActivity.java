package vn.needy.vendor.screen.businessManage;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import vn.needy.vendor.R;
import vn.needy.vendor.databinding.ActivityBusinessManageBinding;
import vn.needy.vendor.screen.BaseActivity;
import vn.needy.vendor.utils.navigator.Navigator;

/**
 * Created by lion on 10/01/2018.
 */

public class BusinessManageActivity extends BaseActivity {

    private BusinessManageContract.Presenter mPresenter;
    private BusinessManageContract.ViewModel mViewModel;

    private Navigator mNavigator;

    @Override
    protected void onCreateActivity(Bundle savedInstanceState) {

        mNavigator = new Navigator(this);

        mViewModel = new BusinessManageViewModel(this, mNavigator);
        mPresenter = new BusinessManagePresenter(mViewModel);

        mViewModel.setPresenter(mPresenter);
        mViewModel.onStart();

        ActivityBusinessManageBinding binding = DataBindingUtil.setContentView(this , R.layout.activity_business_manage);
        binding.setViewModel((BusinessManageViewModel) mViewModel);

    }
}