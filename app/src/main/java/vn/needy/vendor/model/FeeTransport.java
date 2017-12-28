package vn.needy.vendor.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import vn.needy.vendor.model.wrapper.FeeTransportWrapper;

/**
 * Created by lion on 12/12/2017.
 */

public class FeeTransport extends RealmObject {

    @PrimaryKey
    private long id;
    private String mCompanyId;
    private int mFeeType;
    private float mFrom;
    private float mTo;
    private float mFee;

    public FeeTransport() {
        super();
    }

    public FeeTransport(String companyId, FeeTransportWrapper wrapper) {
        id = wrapper.getId();
        mCompanyId = companyId;
        mFeeType = wrapper.getFeeType();
        mFrom = wrapper.getFrom();
        mTo = wrapper.getTo();
        mFee = wrapper.getFee();
    }

    public String getCompanyId() {
        return mCompanyId;
    }

    public void setCompanyId(String companyId) {
        mCompanyId = companyId;
    }

    public int getFeeType() {
        return mFeeType;
    }

    public void setFeeType(int feeType) {
        mFeeType = feeType;
    }

    public float getFrom() {
        return mFrom;
    }

    public void setFrom(float from) {
        mFrom = from;
    }

    public float getTo() {
        return mTo;
    }

    public void setTo(float to) {
        mTo = to;
    }

    public float getFee() {
        return mFee;
    }

    public void setFee(float fee) {
        mFee = fee;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
