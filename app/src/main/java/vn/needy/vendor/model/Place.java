package vn.needy.vendor.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by truongpq on 13/01/2018.
 */

public class Place implements Parcelable {
    private String mId;
    private String mName;
    private String mAddress;
    private double mLat;
    private double mLng;

    protected Place(Parcel in) {
        mId = in.readString();
        mName = in.readString();
        mAddress = in.readString();
        mLat = in.readDouble();
        mLng = in.readDouble();
    }

    public Place() {
    }

    public static final Creator<Place> CREATOR = new Creator<Place>() {
        @Override
        public Place createFromParcel(Parcel in) {
            return new Place(in);
        }

        @Override
        public Place[] newArray(int size) {
            return new Place[size];
        }
    };

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
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

    public double getLat() {
        return mLat;
    }

    public void setLat(double lat) {
        mLat = lat;
    }

    public double getLng() {
        return mLng;
    }

    public void setLng(double lng) {
        mLng = lng;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mId);
        parcel.writeString(mName);
        parcel.writeString(mAddress);
        parcel.writeDouble(mLat);
        parcel.writeDouble(mLng);
    }
}
