package vn.needy.vendor.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by lion on 08/12/2017.
 */

public class ProductPn implements Parcelable {

    private long id;

    public ProductPn() {
    }

    protected ProductPn(Parcel in) {
        id = in.readLong();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
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
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
