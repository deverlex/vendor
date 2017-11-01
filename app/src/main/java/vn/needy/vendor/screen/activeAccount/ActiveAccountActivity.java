package vn.needy.vendor.screen.activeAccount;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.widget.ScrollView;

import vn.needy.vendor.R;
import vn.needy.vendor.data.source.local.realm.RealmApi;
import vn.needy.vendor.databinding.ActivityActiveAccountBinding;
import vn.needy.vendor.screen.BaseActivity;
import vn.needy.vendor.utils.dialog.DialogManager;
import vn.needy.vendor.utils.navigator.Navigator;
import vn.needy.vendor.widget.WorkaroundMapFragment;

/**
 * Created by lion on 31/10/2017.
 */

public class ActiveAccountActivity extends BaseActivity {

    private ActiveAccountContract.ViewModel mViewModel;

    private ScrollView mScrollView;
    private DialogManager mDialogManager;
    private RealmApi mRealmApi;

    @Override
    protected void onCreateActivity(Bundle savedInstanceState) {
        ActivityActiveAccountBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_active_account);

        mRealmApi = new RealmApi();
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

        mViewModel = new ActiveAccountViewModel(this, navigator, mDialogManager, mapFragment);
        ActiveAccountContract.Presenter presenter = new ActiveAccountPresenter(mViewModel, mRealmApi);
        mViewModel.setPresenter(presenter);

        binding.setViewModel((ActiveAccountViewModel) mViewModel);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mViewModel.onStart();
    }

    @Override
    protected void onStop() {
        mViewModel.onStop();
        mRealmApi.closeRealmOnMainThread();
        super.onStop();
    }
}
