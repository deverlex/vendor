package vn.needy.vendor.screen.languageSetting;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.databinding.BaseObservable;

import java.util.List;

import vn.needy.vendor.R;
import vn.needy.vendor.model.Language;
import vn.needy.vendor.screen.BaseRecyclerViewAdapter;

/**
 * Created by lion on 25/12/2017.
 */

public class LanguageSettingViewModel extends BaseObservable implements LanguageSettingContract.ViewModel,
        BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object> {

    private LanguageSettingAdapter mLanguageSettingAdapter;
    private LanguageSettingContract.Presenter mPresenter;
    private Activity mContext;
    private  BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object>
            mItemClickListener;

    public LanguageSettingViewModel( Activity mContext , LanguageSettingAdapter language) {
        this.mContext = mContext;
        this.mLanguageSettingAdapter = language;
        mLanguageSettingAdapter.setItemClickListener(this);
    }

    @Override
    public void onStart() {
        mPresenter.getLanguageList();
        mPresenter.getDefaultLanguage();
    }

    @Override
    public void onStop() {

    }

    @Override
    public void setPresenter(LanguageSettingContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onBackPressed() {
        mContext.onBackPressed();
    }

    @Override
    public void setLanguageList(List<Language> languageList) {
        mLanguageSettingAdapter.updateData(languageList);
    }

    @Override
    public void setDefaultLanguageLanguage(Language defaultLanguage) {
        int position = mLanguageSettingAdapter.getPosition(defaultLanguage);
        mLanguageSettingAdapter.setPosition(position);
    }

    @Override
    public void onLanguageSave() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setMessage("Đã lưu");
        builder.setPositiveButton(R.string.button_ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.setNegativeButton(R.string.button_cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void onItemRecyclerViewClick(Object item) {
        int position = mLanguageSettingAdapter.getPosition((Language) item);
        mLanguageSettingAdapter.setPosition(position);
    }

    public LanguageSettingAdapter getLanguageSettingAdapter() {
        return mLanguageSettingAdapter;
    }
}
