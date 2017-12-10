package vn.needy.vendor.repository.remote.company.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by truongpq on 08/12/2017.
 */

public class UpdateCompanyInfoRequest {
    @Expose
    @SerializedName("name")
    private String name;
    @Expose
    @SerializedName("address")
    private String address;
    @Expose
    @SerializedName("description")
    private String description;
    @Expose
    @SerializedName("siteURL")
    private String siteURL;
    @Expose
    @SerializedName("email")
    private String email;
    @Expose
    @SerializedName("foundedDate")
    private String foundedDate;
    @Expose
    @SerializedName("openingTime")
    private String openingTime;
    @Expose
    @SerializedName("closingTime")
    private String closingTime;
    @Expose
    @SerializedName("lat")
    private float lat;
    @Expose
    @SerializedName("lng")
    private float lng;

    public UpdateCompanyInfoRequest() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSiteURL() {
        return siteURL;
    }

    public void setSiteURL(String siteURL) {
        this.siteURL = siteURL;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFoundedDate() {
        return foundedDate;
    }

    public void setFoundedDate(String foundedDate) {
        this.foundedDate = foundedDate;
    }

    public String getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(String openingTime) {
        this.openingTime = openingTime;
    }

    public String getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(String closingTime) {
        this.closingTime = closingTime;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLng() {
        return lng;
    }

    public void setLng(float lng) {
        this.lng = lng;
    }
}
