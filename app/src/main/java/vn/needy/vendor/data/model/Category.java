package vn.needy.vendor.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by lion on 03/11/2017.
 */

public class Category implements Parcelable {

    @Expose
    @SerializedName("category")
    private String mCategory;
    @Expose
    @SerializedName("title")
    private String mTitle;
    @Expose
    @SerializedName("coverPicture")
    private String mCoverPicture;

    public Category() {
        super();
    }

    protected Category(Parcel in) {
        mCategory = in.readString();
        mTitle = in.readString();
        mCoverPicture = in.readString();
    }

    public static final Creator<Category> CREATOR = new Creator<Category>() {
        @Override
        public Category createFromParcel(Parcel in) {
            return new Category(in);
        }

        @Override
        public Category[] newArray(int size) {
            return new Category[size];
        }
    };

    public String getCategory() {
        return mCategory;
    }

    public void setCategory(String category) {
        mCategory = category;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getCoverPicture() {
        return mCoverPicture;
    }

    public void setCoverPicture(String coverPicture) {
        mCoverPicture = coverPicture;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mCategory);
        dest.writeString(mTitle);
        dest.writeString(mCoverPicture);
    }
}
