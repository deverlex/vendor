package vn.needy.vendor.screen.registerCompany;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;

import vn.needy.vendor.R;
import vn.needy.vendor.databinding.ActivityRegisterCompanyBinding;
import vn.needy.vendor.screen.BaseActivity;
import vn.needy.vendor.utils.dialog.DialogManager;
import vn.needy.vendor.utils.navigator.Navigator;

public class RegisterCompanyActivity extends BaseActivity {

    private static final String TAG = RegisterCompanyActivity.class.getName();

    private RegisterCompanyContract.ViewModel mViewModel;
    private DialogManager mDialogManager;

    @Override
    protected void onCreateActivity(Bundle savedInstanceState) {
        getWindow().setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.background_login));

        Navigator navigator = new Navigator(this);
        mDialogManager = new DialogManager(this);

        mViewModel = new RegisterCompanyViewModel(this, navigator, mDialogManager);

        RegisterCompanyContract.Presenter presenter = new RegisterCompanyPresenter(mViewModel);
        mViewModel.setPresenter(presenter);

        ActivityRegisterCompanyBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_register_company);
        binding.setViewModel((RegisterCompanyViewModel) mViewModel);
    }

//    private void showAlert() {
//        final Dialog dialog = new AlertDialog.Builder(this)
//            .setTitle(R.string.enable_location)
//            .setMessage(R.string.alert_location)
//            .setPositiveButton(R.string.goto_location_setting, new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    Intent settingLocation = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
//                    startActivity(settingLocation);
//                }
//            })
//            .setNegativeButton(R.string.cancel_open_location, new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    finish();
//                }
//            }).show();
//        dialog.setCanceledOnTouchOutside(false);
//    }
}
