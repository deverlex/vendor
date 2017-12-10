package vn.needy.vendor.datasource.user.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by truongpq on 06/12/2017.
 */

public class UpdateUserInfoRequest {

    @Expose
    @SerializedName("name")
    private String mName;

    @Expose
    @SerializedName("gender")
    private String mGender;

    @Expose
    @SerializedName("birthday")
    private String mBirthday;

    @Expose
    @SerializedName("email")
    private String mEmail;

    @Expose
    @SerializedName("address")
    private String mAddress;

    @Expose
    @SerializedName("lat")
    private float lat;

    @Expose
    @SerializedName("lng")
    private float lng;

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getGender() {
        return mGender;
    }

    public void setGender(String gender) {
        this.mGender = gender;
    }

    public String getBirthday() {
        return mBirthday;
    }

    public void setBirthday(String birthday) {
        this.mBirthday = birthday;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        this.mEmail = email;
    }

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address){
        this.mAddress = address;
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
