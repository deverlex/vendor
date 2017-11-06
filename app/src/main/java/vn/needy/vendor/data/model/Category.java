package vn.needy.vendor.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by lion on 03/11/2017.
 */

public class Category {

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
}
