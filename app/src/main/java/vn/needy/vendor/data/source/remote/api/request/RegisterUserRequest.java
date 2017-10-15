package vn.needy.vendor.data.source.remote.api.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by lion on 14/10/2017.
 */

public class RegisterUserRequest {

    @Expose
    @SerializedName("firebaseUid")
    private String mFirebaseUid;
    @Expose
    @SerializedName("firebaseToken")
    private String mFirebaseToken;
    @Expose
    @SerializedName("username")
    private String mPhoneNumber;
    @Expose
    @SerializedName("password")
    private String mPassword;
    @Expose
    @SerializedName("firstName")
    private String firstName;
    @Expose
    @SerializedName("lastName")
    private String lastName;
    @Expose
    @SerializedName("gender")
    private String gender;
    @Expose
    @SerializedName("address")
    private String address;
    @Expose
    @SerializedName("lat")
    private float lat;
    @Expose
    @SerializedName("lng")
    private float lng;

    public RegisterUserRequest() {
        super();
    }

    public String getFirebaseUid() {
        return mFirebaseUid;
    }

    public void setFirebaseUid(String firebaseUid) {
        mFirebaseUid = firebaseUid;
    }

    public String getFirebaseToken() {
        return mFirebaseToken;
    }

    public void setFirebaseToken(String firebaseToken) {
        mFirebaseToken = firebaseToken;
    }

    public String getPhoneNumber() {
        return mPhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.mPhoneNumber = phoneNumber;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        this.mPassword = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
