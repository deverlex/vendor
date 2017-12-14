package vn.needy.vendor.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import vn.needy.vendor.model.wrapper.StoreWrapper;

/**
 * Created by lion on 12/12/2017.
 */

public class Store extends RealmObject {

    @PrimaryKey
    private String mId;
    private int mState;
    private String mUnlockTime;
    private String mName;
    private String mAddress;
    private String mDescription;
    private String mEmail;
    private float mLat;
    private float mLng;
    private String mOpeningTime;
    private String mClosingTime;
    private String mCreatedTime;
    private String mLastUpdatedTime;
    private String mLastUpdatedBy;

    public Store() {
        super();
    }

    public Store(StoreWrapper wrapper) {
        super();
        this.mId = wrapper.getId();
        this.mState = wrapper.getState();
        this.mUnlockTime = wrapper.getUnlockTime();
        this.mName = wrapper.getName();
        this.mAddress = wrapper.getAddress();
        this.mDescription = wrapper.getDescription();
        this.mEmail = wrapper.getEmail();
        this.mLat = wrapper.getLat();
        this.mLng = wrapper.getLng();
        this.mOpeningTime = wrapper.getOpeningTime();
        this.mClosingTime = wrapper.getClosingTime();
        this.mCreatedTime = wrapper.getCreatedTime();
        this.mLastUpdatedTime = wrapper.getLastUpdatedTime();
        this.mLastUpdatedBy = wrapper.getLastUpdatedBy();
    }

    public String getId() {
        return mId;
    }

    public void setmId(String id) {
        mId = id;
    }

    public int getState() {
        return mState;
    }

    public void setState(int state) {
        mState = state;
    }

    public String getUnlockTime() {
        return mUnlockTime;
    }

    public void setUnlockTime(String unlockTime) {
        mUnlockTime = unlockTime;
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

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
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

    public String getCreatedTime() {
        return mCreatedTime;
    }

    public void setCreatedTime(String createdTime) {
        mCreatedTime = createdTime;
    }

    public String getLastUpdatedTime() {
        return mLastUpdatedTime;
    }

    public void setLastUpdatedTime(String lastUpdatedTime) {
        mLastUpdatedTime = lastUpdatedTime;
    }

    public String getLastUpdatedBy() {
        return mLastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        mLastUpdatedBy = lastUpdatedBy;
    }
}
