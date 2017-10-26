package vn.needy.vendor.screen.registerUser;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;


import com.google.firebase.auth.FirebaseAuth;

import vn.needy.vendor.R;
import vn.needy.vendor.data.source.local.realm.RealmApi;
import vn.needy.vendor.databinding.ActivityRegisterUserBinding;
import vn.needy.vendor.screen.BaseActivity;
import vn.needy.vendor.utils.dialog.DialogManager;
import vn.needy.vendor.utils.navigator.Navigator;

/**
 * Created by lion on 03/10/2017.
 */

public class RegisterUserActivity extends BaseActivity {

    private static final String TAG = RegisterUserActivity.class.getName();

    private RegisterUserContract.ViewModel mViewModel;
    private RealmApi mRealmApi;

    @Override
    protected void onCreateActivity(Bundle savedInstanceState) {
        getWindow().setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.background_login));

        FirebaseAuth.getInstance().signOut();

        Navigator navigator = new Navigator(this);
        DialogManager dialogManager = new DialogManager(this);

        mRealmApi = new RealmApi();

        mViewModel = new RegisterUserViewModel(this, getApplication(), navigator, dialogManager);
        RegisterUserContract.Presenter presenter = new RegisterUserPresenter(this, mViewModel, mRealmApi);

        mViewModel.setPresenter(presenter);
        ActivityRegisterUserBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_register_user);
        binding.setViewModel((RegisterUserViewModel) mViewModel);

//        if (!isNetworkConnected()) {
//            dialogManager.dialogWarning(R.string.error_connection).show();
//        }
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }

    @Override
    protected void onPause() {
        super.onPause();
        mRealmApi.closeRealmOnMainThread();
    }

}
