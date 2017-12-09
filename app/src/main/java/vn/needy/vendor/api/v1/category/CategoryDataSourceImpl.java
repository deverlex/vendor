package vn.needy.vendor.api.v1.category;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import vn.needy.vendor.api.base.BaseRepository;
import vn.needy.vendor.api.v1.category.response.CategoryResponse;
import vn.needy.vendor.datasource.model.Category;

/**
 * Created by lion on 06/11/2017.
 */

public class CategoryDataSourceImpl extends BaseRepository implements CategoryDataSource {

    public CategoryDataSourceImpl() {
        super();
    }

    public Observable<List<Category>> getLinkCategories(String category) {
        return mVendorApi.getLinkCategories(category)
                .map(new Function<CategoryResponse, List<Category>>() {
                    @Override
                    public List<Category> apply(CategoryResponse categoryResponse) throws Exception {
                        return categoryResponse.getCategories();
                    }
                });
    }

    @Override
    public Observable<List<Category>> getCompanyLinkCategories(String category) {
        return mVendorApi.getCompanyLinkCategories(category, "1")
                .map(new Function<CategoryResponse, List<Category>>() {
                    @Override
                    public List<Category> apply(CategoryResponse categoryResponse) throws Exception {
                        return categoryResponse.getCategories();
                    }
                });
    }
}
