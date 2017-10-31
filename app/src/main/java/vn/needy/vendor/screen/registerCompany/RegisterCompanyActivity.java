package vn.needy.vendor.screen.registerCompany;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.widget.ScrollView;

import vn.needy.vendor.R;
import vn.needy.vendor.databinding.ActivityRegisterCompanyBinding;
import vn.needy.vendor.screen.BaseActivity;
import vn.needy.vendor.utils.dialog.DialogManager;
import vn.needy.vendor.utils.navigator.Navigator;
import vn.needy.vendor.widget.WorkaroundMapFragment;

public class RegisterCompanyActivity extends BaseActivity {

    private static final String TAG = RegisterCompanyActivity.class.getName();

    private ScrollView mScrollView;

    private RegisterCompanyContract.ViewModel mViewModel;
    private DialogManager mDialogManager;

    @Override
    protected void onCreateActivity(Bundle savedInstanceState) {
        getWindow().setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.background_login));

        ActivityRegisterCompanyBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_register_company);

        mScrollView = (ScrollView) findViewById(R.id.sv_container);

        Navigator navigator = new Navigator(this);
        mDialogManager = new DialogManager(this);

        WorkaroundMapFragment mapFragment = (WorkaroundMapFragment) getFragmentManager()
                .findFragmentById(R.id.fragment_map);
        mapFragment.setListener(new WorkaroundMapFragment.OnTouchListener() {
            @Override
            public void onTouch() {
                mScrollView.requestDisallowInterceptTouchEvent(true);
            }
        });

        mViewModel = new RegisterCompanyViewModel(this, navigator, mDialogManager, mapFragment);
        RegisterCompanyContract.Presenter presenter = new RegisterCompanyPresenter(mViewModel);
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
}
