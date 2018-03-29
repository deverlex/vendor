package vn.needy.vendor.repository.remote.store.context;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StoreContext {

    @Expose
    @SerializedName(value = "store_id")
    private long id;

    @Expose
    @SerializedName("state")
    private int state;

    @Expose
    @SerializedName("status")
    private int status;

    @Expose
    @SerializedName(value = "unlock_time")
    private String unlockTime;

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
    @SerializedName("email")
    private String email;

    @Expose
    @SerializedName(value = "support_mobile")
    private boolean supportMobile;

    @Expose
    @SerializedName("lat")
    private Double lat;

    @Expose
    @SerializedName("lng")
    private Double lng;

    @Expose
    @SerializedName("opening_time")
    private String openingTime;

    @Expose
    @SerializedName("closing_time")
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isSupportMobile() {
        return supportMobile;
    }

    public void setSupportMobile(boolean supportMobile) {
        this.supportMobile = supportMobile;
    }

    public String getUnlockTime() {
        return unlockTime;
    }

    public void setUnlockTime(String unlockTime) {
        this.unlockTime = unlockTime;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
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
