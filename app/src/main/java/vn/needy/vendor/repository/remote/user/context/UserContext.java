package vn.needy.vendor.repository.remote.user.context;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by truongpq on 17/01/2018.
 */

public class UserContext {
    @SerializedName(value = "phone_number")
    @Expose
    private String phoneNumber;

    @SerializedName(value = "first_name")
    @Expose
    private String firstName;

    @SerializedName(value = "last_name")
    @Expose
    private String lastName;

    @SerializedName(value = "address")
    @Expose
    private String address;

    @SerializedName(value = "birthday")
    @Expose
    private String birthday;

    @SerializedName(value = "gender")
    @Expose
    private String gender;

    @SerializedName(value = "email")
    @Expose
    private String email;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
