package vn.needy.vendor.api.v1.category.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import vn.needy.vendor.api.base.BaseResponse;
import vn.needy.vendor.datasource.model.Category;

/**
 * Created by lion on 07/11/2017.
 */

public class CategoryResponse extends BaseResponse {
    @Expose
    @SerializedName("categories")
    private List<Category> mCategories;

    public List<Category> getCategories() {
        return mCategories;
    }

    public void setCategories(List<Category> categories) {
        mCategories = categories;
    }
}
