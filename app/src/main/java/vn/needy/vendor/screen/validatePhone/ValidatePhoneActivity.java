package vn.needy.vendor.screen.validatePhone;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;


import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

import vn.needy.vendor.R;
import vn.needy.vendor.databinding.ActivityValidatePhoneBinding;
import vn.needy.vendor.screen.BaseActivity;
import vn.needy.vendor.utils.dialog.DialogManager;
import vn.needy.vendor.utils.navigator.Navigator;

/**
 * Created by lion on 03/10/2017.
 */

public class ValidatePhoneActivity extends BaseActivity {

    private static final String TAG = ValidatePhoneActivity.class.getName();

    public static final String KEY_PHONE_NUMBER = "phone_number";
    public static final String KEY_FIREBASE_TOKEN = "firebase_token";
    public static final String KEY_FIREBASE_UID = "firebase_uid";

    private ValidatePhoneContract.ViewModel mViewModel;

    @Override
    protected void onCreateActivity(Bundle savedInstanceState) {
        getWindow().setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.background_login));

        FirebaseAuth.getInstance().signOut();

        Navigator navigator = new Navigator(this);
        DialogManager dialogManager = new DialogManager(this);

        mViewModel = new ValidatePhoneViewModel(this, navigator, dialogManager);
        ValidatePhoneContract.Presenter presenter = new ValidatePhonePresenter(this, mViewModel);

        mViewModel.setPresenter(presenter);
        ActivityValidatePhoneBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_validate_phone);
        binding.setViewModel((ValidatePhoneViewModel) mViewModel);

        if (!isNetworkConnected()) {
            dialogManager.dialogWarning(R.string.error_connection).show();
        }
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }

}
