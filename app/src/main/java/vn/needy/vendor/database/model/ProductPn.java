package vn.needy.vendor.database.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by lion on 08/12/2017.
 */

public class ProductPn implements Parcelable {

    protected ProductPn(Parcel in) {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
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
}
