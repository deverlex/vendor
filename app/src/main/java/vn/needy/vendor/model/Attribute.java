package vn.needy.vendor.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.IntDef;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by lion on 04/12/2017.
 */

public class Attribute implements Parcelable {

    @Expose
    @SerializedName("name")
    private String mName;
    @Expose
    @SerializedName("title")
    private String mTitle;
    @Expose
    @SerializedName("dataType")
    private int mDataType;
    private Object mValue;

    @Override
    public int describeContents() {
        return 0;
    }

    protected Attribute(Parcel in) {
        mName = in.readString();
        mTitle = in.readString();
        mValue = in.readValue(getClass().getClassLoader());
        mDataType = in.readInt();
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public Object getValue() {
        return mValue;
    }

    public void setValue(Object value) {
        mValue = value;
    }

    public int getDataType() {
        return mDataType;
    }

    public void setDataType(int dataType) {
        mDataType = dataType;
    }

    public static final Creator<Attribute> CREATOR = new Creator<Attribute>() {
        @Override
        public Attribute createFromParcel(Parcel in) {
            return new Attribute(in);
        }

        @Override
        public Attribute[] newArray(int size) {
            return new Attribute[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mName);
        dest.writeString(mTitle);
        dest.writeValue(mValue);
        dest.writeInt(mDataType);
    }

    @IntDef({DataType.NUMBER, DataType.STRING})
    public @interface DataType {
        int NUMBER = 0;
        int STRING  = 1;
    }
}
