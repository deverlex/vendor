package vn.needy.vendor.port.wrapper;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by truongpq on 27/12/2017.
 */

public class ProductPnWrapper implements Parcelable {
    @SerializedName("id")
    @Expose
    private long mId;

    @SerializedName("name")
    @Expose
    private String mName;

    @SerializedName("category")
    @Expose
    private String mCategory;

    @SerializedName("price")
    @Expose
    private float mPrice;

    protected ProductPnWrapper(Parcel in) {
        mId = in.readLong();
        mName = in.readString();
        mCategory = in.readString();
        mPrice = in.readFloat();
    }

    public static final Creator<ProductPnWrapper> CREATOR = new Creator<ProductPnWrapper>() {
        @Override
        public ProductPnWrapper createFromParcel(Parcel in) {
            return new ProductPnWrapper(in);
        }

        @Override
        public ProductPnWrapper[] newArray(int size) {
            return new ProductPnWrapper[size];
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(mId);
        dest.writeString(mName);
        dest.writeString(mCategory);
        dest.writeFloat(mPrice);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ProductPnWrapper && Long.valueOf(mId).equals(((ProductPnWrapper) obj).getId());
    }
}
