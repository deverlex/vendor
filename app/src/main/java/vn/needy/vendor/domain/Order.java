package vn.needy.vendor.domain;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by truongpq on 19/12/2017.
 */

public class Order extends RealmObject{
    @PrimaryKey
    private long mId;
    private String mUserId;
    private String mStoreId;
    private int mStatus;
    private boolean mIsPaid;
    private boolean mIsGetTax;
    private int mPaymentMethod;
    private String mDescription;
    private String mReceiveFrom;
    private String mReceiveTo;
    private float mFeeTransport;
    private String mTransportFrom;
    private String mTransportTo;
    private float mLatTo;
    private float mLngTo;
    private String mLastUpdatedTime;

    public Order() {
        super();
    }

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        this.mId = id;
    }

    public String getUserId() {
        return mUserId;
    }

    public void setUserId(String userId) {
        this.mUserId = userId;
    }

    public String getStoreId() {
        return mStoreId;
    }

    public void setStoreId(String storeId) {
        this.mStoreId = storeId;
    }

    public int getStatus() {
        return mStatus;
    }

    public void setStatus(int status) {
        this.mStatus = status;
    }

    public boolean isPaid() {
        return mIsPaid;
    }

    public void setIsPaid(boolean isPaid) {
        this.mIsPaid = isPaid;
    }

    public boolean isGetTax() {
        return mIsGetTax;
    }

    public void setIsGetTax(boolean isGetTax) {
        this.mIsGetTax = isGetTax;
    }

    public int getPaymentMethod() {
        return mPaymentMethod;
    }

    public void setPaymentMethod(int paymentMethod) {
        this.mPaymentMethod = paymentMethod;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        this.mDescription = description;
    }

    public String getReceiveFrom() {
        return mReceiveFrom;
    }

    public void setReceiveFrom(String receiveFrom) {
        this.mReceiveFrom = receiveFrom;
    }

    public String getReceiveTo() {
        return mReceiveTo;
    }

    public void setReceiveTo(String receiveTo) {
        this.mReceiveTo = receiveTo;
    }

    public float getFeeTransport() {
        return mFeeTransport;
    }

    public void setFeeTransport(float feeTransport) {
        this.mFeeTransport = feeTransport;
    }

    public String getTransportFrom() {
        return mTransportFrom;
    }

    public void setTransportFrom(String transportFrom) {
        this.mTransportFrom = transportFrom;
    }

    public String getTransportTo() {
        return mTransportTo;
    }

    public void setTransportTo(String transportTo) {
        this.mTransportTo = transportTo;
    }

    public float getLatTo() {
        return mLatTo;
    }

    public void setLatTo(float latTo) {
        this.mLatTo = latTo;
    }

    public float getLngTo() {
        return mLngTo;
    }

    public void setLngTo(float lngTo) {
        this.mLngTo = lngTo;
    }

    public String getLastUpdatedTime() {
        return mLastUpdatedTime;
    }

    public void setLastUpdatedTime(String lastUpdatedTime) {
        this.mLastUpdatedTime = mLastUpdatedTime;
    }
}
