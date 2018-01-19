package vn.needy.vendor.repository.remote.user.context;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserLocationContext implements Parcelable{

    @Expose
    @SerializedName("id")
    private long id;

    @Expose
    @SerializedName("title")
    private String title;

    @Expose
    @SerializedName("description")
    private String description;

    @Expose
    @SerializedName("lat")
    private Double lat;

    @Expose
    @SerializedName("lng")
    private Double lng;

    public UserLocationContext() {}


    protected UserLocationContext(Parcel in) {
        id = in.readLong();
        title = in.readString();
        description = in.readString();
        if (in.readByte() == 0) {
            lat = null;
        } else {
            lat = in.readDouble();
        }
        if (in.readByte() == 0) {
            lng = null;
        } else {
            lng = in.readDouble();
        }
    }

    public static final Creator<UserLocationContext> CREATOR = new Creator<UserLocationContext>() {
        @Override
        public UserLocationContext createFromParcel(Parcel in) {
            return new UserLocationContext(in);
        }

        @Override
        public UserLocationContext[] newArray(int size) {
            return new UserLocationContext[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(id);
        parcel.writeString(title);
        parcel.writeString(description);
        if (lat == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(lat);
        }
        if (lng == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(lng);
        }
    }
}
