package vn.needy.vendor.port.wrapper;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * Created by lion on 03/11/2017.
 */

public class CategoryWrapper implements Parcelable {

    @Expose
    @SerializedName("name")
    private String mName;
    @Expose
    @SerializedName("title")
    private String mTitle;

    public CategoryWrapper() {
        super();
    }

    protected CategoryWrapper(Parcel in) {
        mName = in.readString();
        mTitle = in.readString();
    }

    public static final Creator<CategoryWrapper> CREATOR = new Creator<CategoryWrapper>() {
        @Override
        public CategoryWrapper createFromParcel(Parcel in) {
            return new CategoryWrapper(in);
        }

        @Override
        public CategoryWrapper[] newArray(int size) {
            return new CategoryWrapper[size];
        }
    };

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mName);
        dest.writeString(mTitle);
    }
}
