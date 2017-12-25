package vn.needy.vendor.model.wrapper;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import vn.needy.vendor.model.FeeTransport;

/**
 * Created by lion on 12/12/2017.
 */

public class FeeTransportWrapper {

    @SerializedName("id")
    @Expose
    private long id;
    @SerializedName("feeType")
    @Expose
    private int feeType;
    @SerializedName("from")
    @Expose
    private float from;
    @SerializedName("to")
    @Expose
    private float to;
    @SerializedName("fee")
    @Expose
    private float fee;

    public FeeTransportWrapper(FeeTransport feeTransport) {
        id = feeTransport.getId();
        feeType = feeTransport.getFeeType();
        from = feeTransport.getFrom();
        to = feeTransport.getTo();
        fee = feeTransport.getFee();
    }

    public int getFeeType() {
        return feeType;
    }

    public void setFeeType(int feeType) {
        this.feeType = feeType;
    }

    public float getFrom() {
        return from;
    }

    public void setFrom(float from) {
        this.from = from;
    }

    public float getTo() {
        return to;
    }

    public void setTo(float to) {
        this.to = to;
    }

    public float getFee() {
        return fee;
    }

    public void setFee(float fee) {
        this.fee = fee;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
