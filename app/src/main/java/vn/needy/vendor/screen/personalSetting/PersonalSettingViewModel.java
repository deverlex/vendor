package vn.needy.vendor.screen.personalSetting;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.android.databinding.library.baseAdapters.BR;

import vn.needy.vendor.R;
import vn.needy.vendor.utils.dialog.DialogManager;

/**
 * Created by lion on 25/12/2017.
 */

public class PersonalSettingViewModel extends BaseObservable implements PersonalSettingContract.ViewModel{
    private PersonalSettingContract.Presenter mPresenter;
    private Activity mContext;
    private DialogManager mDialogManager;
    private boolean checkBirthday;
    private boolean checkEmail;
    private boolean checkSex;

    public PersonalSettingViewModel( Activity mContext) {
        this.mContext = mContext;
        mDialogManager = new DialogManager(mContext);
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void setPresenter(PersonalSettingContract.Presenter presenter) {

    }

    @Override
    public void onBackPressed() {
        mContext.onBackPressed();
    }


    @Override
    public void onCheckedEmail() {
        checkEmail = ! checkEmail;
        notifyPropertyChanged(BR.checkEmail);
        if (!checkEmail){
            //mDialogManager.dialogWithNoTitleTwoButton(R.string.personal_setting_email , );
            AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
            builder.setMessage(R.string.personal_setting_email);
            builder.setPositiveButton(R.string.button_ok, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.dismiss();
                }
            });
            builder.setNegativeButton(R.string.cancel_open_location, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.dismiss();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }

    }

    @Override
    public void onCheckedBirthday() {

        checkBirthday = ! checkBirthday;
        notifyPropertyChanged(BR.checkBirthday);

        if (!checkBirthday){
            AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
            builder.setMessage(R.string.personal_setting_birthday);
            builder.setPositiveButton(R.string.button_ok, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.dismiss();
                }
            });
            builder.setNegativeButton(R.string.cancel_open_location, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.dismiss();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }

    @Override
    public void onCheckedSex() {

        checkSex = ! checkSex;
        notifyPropertyChanged(BR.checkSex);

        if (!checkSex){
            AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
            builder.setMessage(R.string.personal_setting_sex);
            builder.setPositiveButton(R.string.button_ok, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.dismiss();
                }
            });
            builder.setNegativeButton(R.string.cancel_open_location, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.dismiss();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }

    @Bindable
    public boolean isCheckBirthday() {
        return checkBirthday;
    }
    @Bindable
    public boolean isCheckEmail() {
        return checkEmail;
    }
    @Bindable
    public boolean isCheckSex() {
        return checkSex;
    }
}
