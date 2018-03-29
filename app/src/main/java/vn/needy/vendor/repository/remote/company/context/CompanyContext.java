package vn.needy.vendor.repository.remote.company.context;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CompanyContext {

    @Expose
    @SerializedName(value = "company_id")
    private long id;

    @Expose
    @SerializedName("state")
    private int state;

    @Expose
    @SerializedName("level")
    private int level;

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
    @SerializedName(value = "site_url")
    private String siteUrl;

    @Expose
    @SerializedName(value = "founded_date")
    private String foundedDate;

    @Expose
    @SerializedName("lat")
    private float lat;

    @Expose
    @SerializedName("lng")
    private float lng;

    @Expose
    @SerializedName("email")
    private String email;

    @Expose
    @SerializedName(value = "opening_time")
    private String openingTime;

    @Expose
    @SerializedName(value = "closing_time")
    private String closingTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
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

    public String getSiteUrl() {
        return siteUrl;
    }

    public void setSiteUrl(String siteUrl) {
        this.siteUrl = siteUrl;
    }

    public String getFoundedDate() {
        return foundedDate;
    }

    public void setFoundedDate(String foundedDate) {
        this.foundedDate = foundedDate;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
}
