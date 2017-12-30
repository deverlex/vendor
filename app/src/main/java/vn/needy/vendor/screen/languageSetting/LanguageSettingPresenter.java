package vn.needy.vendor.screen.languageSetting;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import vn.needy.vendor.R;
import vn.needy.vendor.model.Language;

/**
 * Created by lion on 25/12/2017.
 */

public class LanguageSettingPresenter implements LanguageSettingContract.Presenter{

    private LanguageSettingContract.ViewModel mViewModel;
    private Context mContext;
    private int mPositionEnglish = 0;
    private List<Language> mLanguageList;

    public LanguageSettingPresenter(LanguageSettingContract.ViewModel viewModel , Context context) {
        this.mViewModel = viewModel;
        this.mContext = context;

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void loadSupportLanguage() {

    }

    @Override
    public void getLanguageList() {
        String[] keys = mContext.getResources().getStringArray(R.array.language_entries);
        String[] values = mContext.getResources().getStringArray(R.array.language_values);
        mLanguageList = new ArrayList<>();
        for(int i=0; i<keys.length; ++i){
            Language language = new Language(keys[i] , values[i]);
            if (values[i].equals("en"))
                mPositionEnglish = i;
            mLanguageList.add(language);
        }
        mViewModel.setLanguageList(mLanguageList);
    }

    @Override
    public void getDefaulLanguage() {
        // get defaul language
        Language mLanguage = null;
        String mLanguageCodeDefaul = Locale.getDefault().getLanguage();

        for (int i=0; i<mLanguageList.size() ; ++i)
            if (mLanguageList.get(i).getLanguageCode().equals(mLanguageCodeDefaul)) {
                mLanguage = new Language(mLanguageList.get(i).getLanguageName(), mLanguageCodeDefaul);
                break;
            }
        if (mLanguage == null){
            mLanguage = new Language(mLanguageList.get(mPositionEnglish).getLanguageName(), mLanguageList.get(mPositionEnglish).getLanguageCode());
        }
        mViewModel.setDefaulLanguageLanguage(mLanguage);
    }

}
