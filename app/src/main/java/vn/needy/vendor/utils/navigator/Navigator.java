package vn.needy.vendor.utils.navigator;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
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
    }

    public void startActivity(@NonNull Class<? extends Activity> clazz) {
        startActivity(new Intent(mActivity, clazz));
    }

    public void startActivity(@NonNull Class<? extends Activity> clazz, Bundle args) {
        Intent intent = new Intent(mActivity, clazz);
        intent.putExtras(args);
        startActivity(intent);
    }

    public void finishActivity() {
        if (mActivity != null) {
            mActivity.finish();
        }
        if (mFragment != null) {
            mFragment.getActivity().finish();
        }
    }

    public void showToastButtom(String message) {
//        Toast toast = Toast.makeText(mActivity, message, Toast.LENGTH_SHORT);
//        View view = toast.getView();
//        toast.setGravity(Gravity.BOTTOM | Gravity.HORIZONTAL_GRAVITY_MASK, 0, 0);
//        view.setBackgroundResource(R.drawable.item_custom_toast);
//        toast.setView(view);
//        toast.show();
        Snackbar.make(mActivity.findViewById(android.R.id.content), message,
                Snackbar.LENGTH_SHORT).show();

    }

    public void showToastCenterText(String message) {
        Toast toast = Toast.makeText(mActivity, message, Toast.LENGTH_SHORT);
        TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
        if( v != null) v.setGravity(Gravity.CENTER);
        toast.show();
    }

    public void onBackPressed() {
        mActivity.onBackPressed();
    }
}
