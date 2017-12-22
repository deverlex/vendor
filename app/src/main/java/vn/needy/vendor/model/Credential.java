package vn.needy.vendor.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by lion on 14/10/2017.
 */

public class Credential implements Parcelable {

    @Expose
    @SerializedName("phoneNumber")
    private String mPhoneNumber;
    @Expose
    @SerializedName("passWord")
    private String mPassWord;

    public Credential() {
        super();
    }

    public Credential(String phoneNumber, String passWord) {
        mPhoneNumber = phoneNumber;
        mPassWord = passWord;
    }

    protected Credential(Parcel in) {
        mPhoneNumber = in.readString();
        mPassWord = in.readString();
    }

    public static final Creator<Credential> CREATOR = new Creator<Credential>() {
        @Override
        public Credential createFromParcel(Parcel in) {
            return new Credential(in);
        }

        @Override
        public Credential[] newArray(int size) {
            return new Credential[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mPhoneNumber);
        dest.writeString(mPassWord);
    }
}
