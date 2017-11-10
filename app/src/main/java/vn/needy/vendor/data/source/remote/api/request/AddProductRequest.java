package vn.needy.vendor.data.source.remote.api.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

/**
 * Created by lion on 10/11/2017.
 */

public class AddProductRequest {

    @Expose
    @SerializedName("category")
    private String mCategory;
    @Expose
    @SerializedName("name")
    private String mName;
    @Expose
    @SerializedName("price")
    private float mPrice;
    @Expose
    @SerializedName("quantity")
    private short mQuantity;
    @Expose
    @SerializedName("promotion")
    private String mPromotion;
    @Expose
    @SerializedName("description")
    private String mDescription;
    @Expose
    @SerializedName("attributes")
    private Map<String, String> mAttributes;
    @Expose
    @SerializedName("hashtag")
    private List<String> mHashtag;

    public String getCategory() {
        return mCategory;
    }

    public void setCategory(String category) {
        mCategory = category;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public float getPrice() {
        return mPrice;
    }

    public void setPrice(float price) {
        mPrice = price;
    }

    public short getQuantity() {
        return mQuantity;
    }

    public void setQuantity(short quantity) {
        mQuantity = quantity;
    }

    public String getPromotion() {
        return mPromotion;
    }

    public void setPromotion(String promotion) {
        mPromotion = promotion;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public Map<String, String> getAttributes() {
        return mAttributes;
    }

    public void setAttributes(Map<String, String> attributes) {
        mAttributes = attributes;
    }

    public List<String> getHashtag() {
        return mHashtag;
    }

    public void setHashtag(List<String> hashtag) {
        mHashtag = hashtag;
    }
}
