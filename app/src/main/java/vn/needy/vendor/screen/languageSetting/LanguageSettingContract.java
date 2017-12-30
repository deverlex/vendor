package vn.needy.vendor.screen.languageSetting;

import java.util.List;

import vn.needy.vendor.model.Language;
import vn.needy.vendor.screen.BasePresenter;
import vn.needy.vendor.screen.BaseViewModel;


/**
 * Created by lion on 25/12/2017.
 */

interface LanguageSettingContract {

    interface ViewModel extends BaseViewModel<Presenter> {

        void onBackPressed();

        void setLanguageList(List<Language> languageList);

        void setDefaulLanguageLanguage(Language defaulLanguage);

        void onLanguageSave();

    }

    interface Presenter extends BasePresenter {

        void loadSupportLanguage();

        void getLanguageList();

        void getDefaulLanguage();

    }
}
