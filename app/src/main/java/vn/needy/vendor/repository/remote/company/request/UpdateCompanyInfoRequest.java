package vn.needy.vendor.repository.remote.company.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import vn.needy.vendor.domain.Company;
import vn.needy.vendor.domain.FeeTransport;
import vn.needy.vendor.port.wrapper.FeeTransportWrapper;

/**
 * Created by truongpq on 08/12/2017.
 */

public class UpdateCompanyInfoRequest {
    @Expose
    @SerializedName("name")
    private String mName;
    @Expose
    @SerializedName("address")
    private String mAddress;
    @Expose
    @SerializedName("description")
    private String mDescription;
    @Expose
    @SerializedName("siteURL")
    private String mSiteURL;
    @Expose
    @SerializedName("email")
    private String mEmail;
    @Expose
    @SerializedName("foundedDate")
    private String mFoundedDate;
    @Expose
    @SerializedName("openingTime")
    private String mOpeningTime;
    @Expose
    @SerializedName("closingTime")
    private String mClosingTime;
    @Expose
    @SerializedName("lat")
    private float mLat;
    @Expose
    @SerializedName("lng")
    private float mLng;
    @Expose
    @SerializedName("feeTransport")
    private List<FeeTransportWrapper> mFeeTransport;
    @Expose
    @SerializedName("removeFeeTransportId")
    private List<Long> mRemoveFeeTransportIds;

    public UpdateCompanyInfoRequest() {
        super();
    }

    public UpdateCompanyInfoRequest(Company company) {
        mName = company.getName();
        mAddress = company.getAddress();
        mDescription = company.getDescription();
        mSiteURL = company.getSiteUrl();
        mFoundedDate = company.getFoundedDate();
        mOpeningTime = company.getOpeningTime();
        mClosingTime = company.getClosingTime();
        mLat = company.getLat();
        mLng = company.getLng();

        mFeeTransport = new ArrayList<>();
        for (FeeTransport ft : company.getFeeTransports()) {
            mFeeTransport.add(new FeeTransportWrapper(ft));
        }
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        mAddress = address;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getSiteURL() {
        return mSiteURL;
    }

    public void setSiteURL(String siteURL) {
        mSiteURL = siteURL;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public String getFoundedDate() {
        return mFoundedDate;
    }

    public void setFoundedDate(String foundedDate) {
        mFoundedDate = foundedDate;
    }

    public String getOpeningTime() {
        return mOpeningTime;
    }

    public void setOpeningTime(String openingTime) {
        mOpeningTime = openingTime;
    }

    public String getClosingTime() {
        return mClosingTime;
    }

    public void setClosingTime(String closingTime) {
        mClosingTime = closingTime;
    }

    public float getLat() {
        return mLat;
    }

    public void setLat(float lat) {
        mLat = lat;
    }

    public float getLng() {
        return mLng;
    }

    public void setLng(float lng) {
        mLng = lng;
    }

    public List<Long> getmRemoveFeeTransportIds() {
        return mRemoveFeeTransportIds;
    }

    public void setmRemoveFeeTransportIds(List<Long> mRemoveFeeTransportIds) {
        this.mRemoveFeeTransportIds = mRemoveFeeTransportIds;
    }
}
