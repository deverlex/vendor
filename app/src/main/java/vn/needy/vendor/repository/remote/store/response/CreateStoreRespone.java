package vn.needy.vendor.repository.remote.store.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by truongpq on 16/01/2018.
 */

public class CreateStoreRespone {
    @Expose
    @SerializedName("store_id")
    private long mStoreId;

    public long getStoreId() {
        return mStoreId;
    }

    public void setStoreId(long storeId) {
        mStoreId = storeId;
    }
}
