package vn.needy.vendor.model;

import android.os.Parcel;
import android.os.Parcelable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by lion on 08/12/2017.
 */

public class ProductPn extends RealmObject implements Parcelable {

    @PrimaryKey
    private long mId;
    private String mName;
    private String mCategory;
    private float mPrice;

    public ProductPn() {
    }

    protected ProductPn(Parcel in) {
        mId = in.readLong();
        mName = in.readString();
        mCategory = in.readString();
        mPrice = in.readFloat();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(mId);
        dest.writeString(mName);
        dest.writeString(mCategory);
        dest.writeFloat(mPrice);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ProductPn> CREATOR = new Creator<ProductPn>() {
        @Override
        public ProductPn createFromParcel(Parcel in) {
            return new ProductPn(in);
        }

        @Override
        public ProductPn[] newArray(int size) {
            return new ProductPn[size];
        }
    };

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        this.mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getCategory() {
        return mCategory;
    }

    public void setCategory(String category) {
        this.mCategory = category;
    }

    public float getPrice() {
        return mPrice;
    }

    public void setPrice(float price) {
        this.mPrice = price;
    }
}
