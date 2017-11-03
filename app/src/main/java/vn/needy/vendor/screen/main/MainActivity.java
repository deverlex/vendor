package vn.needy.vendor.screen.main;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.text.TextUtils;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import vn.needy.vendor.R;
import vn.needy.vendor.data.model.UserState;
import vn.needy.vendor.data.source.local.realm.RealmApi;
import vn.needy.vendor.data.source.local.sharedprf.SharedPrefsApi;
import vn.needy.vendor.data.source.local.sharedprf.SharedPrefsImpl;
import vn.needy.vendor.data.source.local.sharedprf.SharedPrefsKey;
import vn.needy.vendor.screen.BaseActivity;
import vn.needy.vendor.screen.listorder.ListOrderFragment;
import vn.needy.vendor.screen.mainPage.MainPageFragment;
import vn.needy.vendor.screen.notification.NotificationFragment;
import vn.needy.vendor.screen.personal.PersonalFragment;
import vn.needy.vendor.utils.ViewUtil;

public class MainActivity extends BaseActivity {

    private static final String TAG = MainActivity.class.getName();

    private MainContract.Presenter mPresenter;
    private RealmApi mRealmApi;
    private SharedPrefsApi mPrefsApi;

    private BottomBar mBottomBar;

    @Override
    protected void onCreateActivity(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);

        mRealmApi = new RealmApi();
        mPrefsApi = SharedPrefsImpl.getInstance();

        mPresenter = new MainPresenter(mRealmApi);
        initializeBottomBar();

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
