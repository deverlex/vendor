package vn.needy.vendor.screen.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import vn.needy.vendor.R;
import vn.needy.vendor.datasource.sharedprf.SharedPrefsApi;
import vn.needy.vendor.datasource.sharedprf.SharedPrefsImpl;
import vn.needy.vendor.screen.BaseActivity;
import vn.needy.vendor.screen.category.CategoriesActivity;
import vn.needy.vendor.screen.listorder.ListOrderFragment;
import vn.needy.vendor.screen.mainPage.MainPageFragment;
import vn.needy.vendor.screen.notification.NotificationFragment;
import vn.needy.vendor.screen.personal.PersonalFragment;
import vn.needy.vendor.utils.ViewUtil;

public class MainActivity extends BaseActivity {

    private MainContract.Presenter mPresenter;
    private SharedPrefsApi mPrefsApi;

    private BottomBar mBottomBar;

    @Override
    protected void onCreateActivity(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);

        mPrefsApi = SharedPrefsImpl.getInstance();

        mPresenter = new MainPresenter();
        initializeBottomBar();
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
