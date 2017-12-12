package vn.needy.vendor.model.wrapper;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by lion on 12/12/2017.
 */

public class FeeTransportWrapper {

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
}
