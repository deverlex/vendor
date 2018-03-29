package vn.needy.vendor.model;

/**
 * Created by minh_dai on 27/12/2017.
 */

public class Language {
    private String mLanguageName;
    private String mLanguageCode;


    public Language() {
    }

    public Language(String mLanguageName, String mLanguageCode) {
        this.mLanguageName = mLanguageName;
        this.mLanguageCode = mLanguageCode;
    }

    public String getLanguageName() {
        return mLanguageName;
    }

    public void setLanguageName(String mLanguageName) {
        this.mLanguageName = mLanguageName;
    }

    public String getLanguageCode() {
        return mLanguageCode;
    }

    public void setLanguageCode(String mLanguageCode) {
        this.mLanguageCode = mLanguageCode;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Language)
        {
            if(((Language) obj).mLanguageCode.equals(mLanguageCode))
                return true;
            return false;
        }
        return false;
    }
}
