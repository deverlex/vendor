package vn.needy.vendor.repository.remote.product.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import vn.needy.vendor.port.wrapper.FeeTransportWrapper;

/**
 * Created by truongpq on 28/12/2017.
 */

public class AddProductPlReq {
    @Expose
    @SerializedName("category")
    private String mCategory;

    @Expose
    @SerializedName("name")
    private String mName;

    @Expose
    @SerializedName("promotion")
    private String mPromotion;

    @Expose
    @SerializedName("description")
    private String mDescription;

    @Expose
    @SerializedName("feeTransport")
    private List<FeeTransportWrapper> mFeeTransport;

    @Expose
    @SerializedName("hashtag")
    private List<String> mHashtag;

    @Expose
    @SerializedName("products")
    private List<Long> mProducts;

    @Expose
    @SerializedName("images")
    private List<String> images;

    public String getCategory() {
        return mCategory;
    }

    public void setCategory(String category) {
        this.mCategory = category;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getPromotion() {
        return mPromotion;
    }

    public void setPromotion(String promotion) {
        this.mPromotion = promotion;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        this.mDescription = description;
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
        this.mHashtag = hashtag;
    }

    public List<Long> getProducts() {
        return mProducts;
    }

    public void setProducts(List<Long> products) {
        this.mProducts = products;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
