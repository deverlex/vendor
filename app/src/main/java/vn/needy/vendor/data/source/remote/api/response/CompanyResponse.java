package vn.needy.vendor.data.source.remote.api.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import vn.needy.vendor.data.model.Company;

/**
 * Created by lion on 07/10/2017.
 */

public class CompanyResponse implements Parcelable {

    @Expose
    @SerializedName("company")
    private Company mCompany;
    @Expose
    @SerializedName("status")
    private int mStatus;
    @Expose
    @SerializedName("message")
    private String mMessage;

    protected CompanyResponse(Parcel in) {
        mCompany = in.readParcelable(Company.class.getClassLoader());
        mStatus = in.readInt();
        mMessage = in.readString();
    }

    public static final Creator<CompanyResponse> CREATOR = new Creator<CompanyResponse>() {
        @Override
        public CompanyResponse createFromParcel(Parcel in) {
            return new CompanyResponse(in);
        }

        @Override
        public CompanyResponse[] newArray(int size) {
            return new CompanyResponse[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mStatus);
        dest.writeString(mMessage);
        dest.writeParcelable(mCompany, flags);
    }

    public Company getCompany() {
        return mCompany;
    }

    public void setCompany(Company mCompany) {
        this.mCompany = mCompany;
    }

    public int getStatus() {
        return mStatus;
    }

    public void setStatus(int mStatus) {
        this.mStatus = mStatus;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String mMessage) {
        this.mMessage = mMessage;
    }
}
