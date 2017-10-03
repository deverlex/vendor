package vn.needy.vendor.utils.navigator;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import vn.needy.vendor.R;

public class Navigator {

    @NonNull
    private FragmentActivity mActivity;
    private Fragment mFragment;

    public Navigator(@NonNull FragmentActivity activity) {
        mActivity = activity;
    }

    public Navigator(Fragment fragment) {
        mFragment = fragment;
        mActivity = fragment.getActivity();
    }

    public void startActivity(@NonNull Intent intent) {
        mActivity.startActivity(intent);
        mActivity.overridePendingTransition(R.anim.slide_left_in, R.anim.slide_left_out);
    }

    public void startActivity(@NonNull Class<? extends Activity> clazz) {
        mActivity.startActivity(new Intent(mActivity, clazz));
        mActivity.overridePendingTransition(R.anim.slide_left_in, R.anim.slide_left_out);
    }

    public void finishActivity() {
        if (mActivity != null) {
            mActivity.finish();
        }
        if (mFragment != null) {
            mFragment.getActivity().finish();
        }
    }

}
