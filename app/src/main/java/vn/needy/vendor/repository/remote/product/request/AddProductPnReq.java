package vn.needy.vendor.repository.remote.product.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

import vn.needy.vendor.model.wrapper.FeeTransportWrapper;

/**
 * Created by lion on 10/11/2017.
 */

public class AddProductPnReq {

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
    private int mQuantity;
    @Expose
    @SerializedName("promotion")
    private String mPromotion;
    @Expose
    @SerializedName("description")
    private String mDescription;
    @Expose
    @SerializedName("attributes")
    private Map<String, Object> mAttributes;
    @Expose
    @SerializedName("feeTransport")
    private List<FeeTransportWrapper> mFeeTransport;
    @Expose
    @SerializedName("hashtag")
    private List<String> mHashtag;
    @Expose
    @SerializedName("images")
    private List<String> images;

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

    public int getQuantity() {
        return mQuantity;
    }

    public void setQuantity(int quantity) {
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

    public Map<String, Object> getAttributes() {
        return mAttributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        mAttributes = attributes;
    }

    public List<FeeTransportWrapper> getFeeTransport() {
        return mFeeTransport;
    }

    public void setFeeTransport(List<FeeTransportWrapper> feeTransport) {
        this.mFeeTransport = feeTransport;
    }

    public List<String> getHashtag() {
        return mHashtag;
    }

    public void setHashtag(List<String> hashtag) {
        mHashtag = hashtag;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
