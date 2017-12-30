package vn.needy.vendor.screen.languageSetting;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import vn.needy.vendor.R;
import vn.needy.vendor.databinding.ItemLanguageBinding;
import vn.needy.vendor.model.Language;
import vn.needy.vendor.screen.BaseRecyclerViewAdapter;

/**
 * Created by minh_dai on 26/12/2017.
 */

public class LanguageSettingAdapter extends BaseRecyclerViewAdapter<LanguageSettingAdapter.ItemViewHoder> {

    private int mPosition = -1;
    private List<Language> mLanguage;
    private Context mContext;
    private BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object> mItemClickListener;

    protected LanguageSettingAdapter(@NonNull Context context , List<Language> map) {
        super(context);
        this.mContext = context;
        this.mLanguage = map;
    }

    @Override
    public ItemViewHoder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemLanguageBinding binding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_language, parent, false);
        return new LanguageSettingAdapter.ItemViewHoder(binding, mItemClickListener);
    }

    @Override
    public void onBindViewHolder(ItemViewHoder holder, int position) {
        holder.bind(mLanguage.get(position), position == mPosition);
    }

    @Override
    public int getItemCount() {
        return mLanguage.size();
    }

    public void setItemClickListener(OnRecyclerViewItemClickListener<Object> itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    public void updateData(List<Language> languages) {
        if (languages == null) {
            return;
        }
        mLanguage.clear();
        mLanguage.addAll(languages);
        notifyDataSetChanged();
    }

    public int getPosition(Language language){
        return mLanguage.indexOf(language);
    }

    public void setPosition(int position){
        this.mPosition = position;
        notifyDataSetChanged();
    }

    static class ItemViewHoder extends RecyclerView.ViewHolder {

        private ItemLanguageBinding mBinding;
        private BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object>
                mItemClickListener;

        public ItemViewHoder(ItemLanguageBinding binding, OnRecyclerViewItemClickListener<Object> mItemClickListener) {
            super(binding.getRoot());
            this.mBinding = binding;
            this.mItemClickListener = mItemClickListener;
        }

        void bind(Language language , boolean isChecked) {
            mBinding.setViewModel(new ItemLanguageViewModel(language , mItemClickListener, isChecked));
            mBinding.executePendingBindings();
        }
    }
}
