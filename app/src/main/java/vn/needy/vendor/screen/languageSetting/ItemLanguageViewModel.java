package vn.needy.vendor.screen.languageSetting;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import vn.needy.vendor.model.Language;
import vn.needy.vendor.screen.BaseRecyclerViewAdapter;

/**
 * Created by minh_dai on 26/12/2017.
 */

public class ItemLanguageViewModel extends BaseObservable {

    private BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object> mItemClickListener;
    private Language mLanguage;
    private boolean isChecked;

    public ItemLanguageViewModel(Language mLanguage, BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object> mItemClickListener , boolean isChecked) {
        this.mLanguage = mLanguage;
        this.mItemClickListener = mItemClickListener;
        this.isChecked = isChecked;
    }

    public void onItemClicked() {
        if (mItemClickListener == null) {
            return;
        }
        mItemClickListener.onItemRecyclerViewClick(mLanguage);
    }


    @Bindable
    public Language getLanguage() {
        return mLanguage;
    }

    @Bindable
    public boolean isChecked() {
        return isChecked;
    }
}
