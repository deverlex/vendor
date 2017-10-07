package vn.needy.vendor.utils.navigator;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

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

    public void showToastCustom(String message) {
        Toast toast = Toast.makeText(mActivity, message, Toast.LENGTH_SHORT);
        View view = toast.getView();
        toast.setGravity(Gravity.BOTTOM | Gravity.HORIZONTAL_GRAVITY_MASK, 0, 0);
        view.setBackgroundResource(R.drawable.item_custom_toast);
        toast.setView(view);
        toast.show();
    }

}
