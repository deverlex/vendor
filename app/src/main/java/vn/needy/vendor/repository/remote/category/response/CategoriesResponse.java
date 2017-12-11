package vn.needy.vendor.repository.remote.category.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import vn.needy.vendor.port.message.BaseResponse;
import vn.needy.vendor.model.wrapper.CategoryWrapper;

/**
 * Created by lion on 07/11/2017.
 */

public class CategoriesResponse extends BaseResponse {
    @Expose
    @SerializedName("categories")
    private List<CategoryWrapper> mCategories;

    public List<CategoryWrapper> getCategories() {
        return mCategories;
    }

    public void setCategories(List<CategoryWrapper> categories) {
        mCategories = categories;
    }
}
