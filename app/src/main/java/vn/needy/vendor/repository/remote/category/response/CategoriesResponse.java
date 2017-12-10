package vn.needy.vendor.repository.remote.category.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import vn.needy.vendor.port.message.BaseResponse;
import vn.needy.vendor.model.Category;

/**
 * Created by lion on 07/11/2017.
 */

public class CategoriesResponse extends BaseResponse {
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
