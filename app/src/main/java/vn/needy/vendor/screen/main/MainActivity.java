package vn.needy.vendor.screen.main;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import io.realm.RealmChangeListener;
import io.realm.RealmResults;
import vn.needy.vendor.R;
import vn.needy.vendor.database.realm.RealmApi;
import vn.needy.vendor.database.sharedprf.SharedPrefsApi;
import vn.needy.vendor.database.sharedprf.SharedPrefsImpl;
import vn.needy.vendor.databinding.ActivityMainBinding;
import vn.needy.vendor.domain.Notification;
import vn.needy.vendor.port.api.VendorApi;
import vn.needy.vendor.port.service.VendorServiceClient;
import vn.needy.vendor.screen.BaseActivity;
import vn.needy.vendor.screen.category.CategoriesActivity;
import vn.needy.vendor.screen.listorder.ListOrderFragment;
import vn.needy.vendor.screen.mainPage.MainPageFragment;
import vn.needy.vendor.screen.notification.NotificationFragment;
import vn.needy.vendor.screen.personal.PersonalFragment;
import vn.needy.vendor.utils.ViewUtil;

public class MainActivity extends BaseActivity {

    private MainContract.Presenter mPresenter;
    private VendorApi mVendorApi;
    private SharedPrefsApi mPrefsApi;

    private BottomBar mBottomBar;
    private MainContract.ViewModel mViewModel;

    @Override
    protected void onCreateActivity(Bundle savedInstanceState) {
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        mVendorApi = VendorServiceClient.getInstance();
        mPrefsApi = SharedPrefsImpl.getInstance();

        mViewModel = new MainViewModel(this);
        mPresenter = new MainPresenter(mViewModel, mVendorApi, mPrefsApi);
        mViewModel.setPresenter(mPresenter);
        initializeBottomBar();

        mViewModel.onStart();
        binding.setViewModel((MainViewModel) mViewModel);

        Log.e(getClass().getName(), FirebaseInstanceId.getInstance().getToken());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == MainPageFragment.RC_CHOOSE_CATEGORY) {
            if (resultCode == CategoriesActivity.RC_OK) {
                // we will reload main fragment
                initFragment(R.id.contentContainer, MainPageFragment.getInstance(),
                        null, data.getExtras());
            }
        }
    }

    private void initializeBottomBar() {
        mBottomBar = ViewUtil.findById(this, R.id.bottomBar);
        mBottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                switch (tabId) {
                    case R.id.store:
                        initFragment(R.id.contentContainer, MainPageFragment.getInstance());
                        break;
                    case R.id.order:
                        initFragment(R.id.contentContainer, ListOrderFragment.getInstance());
                        break;
                    case R.id.notification:
                        initFragment(R.id.contentContainer, NotificationFragment.getInstance());

                        // View all notification when open notification list
                        mPresenter.viewAllNotification();
                        break;
                    case R.id.personal:
                        initFragment(R.id.contentContainer, PersonalFragment.getInstance());
                        break;
                    default:
                }
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
    }

}
