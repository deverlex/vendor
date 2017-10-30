package vn.needy.vendor.screen.completeInfo;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import vn.needy.vendor.R;
import vn.needy.vendor.screen.registerCompany.RegisterCompanyActivity;
import vn.needy.vendor.utils.ViewUtil;
import vn.needy.vendor.utils.navigator.Navigator;

/**
 * Created by lion on 30/10/2017.
 */

public class CompleteInfoDialog extends DialogFragment {

    private static final String TAG = CompleteInfoDialog.class.getName();

    private TextView mTextIntroComplete;
    private RelativeLayout mButtonCompleteUserProfile;
    private RelativeLayout mButtonCompleteCompanyProfile;

    private boolean mRqUserProfile;
    private boolean mRqCompanyProfile;

    private Navigator mNavigator;

    public static CompleteInfoDialog getInstance() {
        return new CompleteInfoDialog();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_complete_info, null);
        initializeEvent(view);
        builder.setView(view);

        mNavigator = new Navigator((FragmentActivity) getActivity());

        return builder.create();
    }

    public void setRequireComplete(boolean rqUserProfile, boolean rqCompanyProfile) {
        Log.d(TAG, "" + rqUserProfile + " - " + rqCompanyProfile);
        mRqUserProfile = rqUserProfile;
        mRqCompanyProfile = rqCompanyProfile;
    }

    private void initializeEvent(View view) {
        mTextIntroComplete = ViewUtil.findById(view, R.id.intro_complete);
        mButtonCompleteUserProfile = ViewUtil.findById(view, R.id.button_complete_user_profile);
        mButtonCompleteCompanyProfile = ViewUtil.findById(view, R.id.button_complete_company_profile);

        if (mRqUserProfile && mRqCompanyProfile) {
            mTextIntroComplete.setText(R.string.complete_user_company_info);
            mButtonCompleteUserProfile.setVisibility(View.VISIBLE);
            mButtonCompleteCompanyProfile.setVisibility(View.VISIBLE);
        } else if (mRqCompanyProfile) {
            mTextIntroComplete.setText(R.string.complete_company_info);
            mButtonCompleteUserProfile.setVisibility(View.GONE);
            mButtonCompleteCompanyProfile.setVisibility(View.VISIBLE);
        } else if (mRqUserProfile) {
            mTextIntroComplete.setText(R.string.complete_user_info);
            mButtonCompleteUserProfile.setVisibility(View.VISIBLE);
            mButtonCompleteCompanyProfile.setVisibility(View.GONE);
        }

        mButtonCompleteUserProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mButtonCompleteCompanyProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CompleteInfoDialog.this.dismiss();
                mNavigator.startActivity(RegisterCompanyActivity.class);
            }
        });

    }
}
