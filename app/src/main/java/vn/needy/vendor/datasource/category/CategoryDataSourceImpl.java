package vn.needy.vendor.datasource.category;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import vn.needy.vendor.datasource.BaseDataSource;
import vn.needy.vendor.datasource.category.response.CategoriesResponse;
import vn.needy.vendor.model.Category;

/**
 * Created by lion on 06/11/2017.
 */

public class CategoryDataSourceImpl extends BaseDataSource implements CategoryDataSource {

    public CategoryDataSourceImpl() {
        super();
    }

    public Observable<List<Category>> getCategories(String category) {
        return mVendorApi.getCategories(category)
                .map(new Function<CategoriesResponse, List<Category>>() {
                    @Override
                    public List<Category> apply(CategoriesResponse categoryResponse) throws Exception {
                        return categoryResponse.getCategories();
                    }
                });
    }

    @Override
    public Observable<List<Category>> getCompanyCategories(String category) {
        return mVendorApi.getCompanyCategories(category, "1")
                .map(new Function<CategoriesResponse, List<Category>>() {
                    @Override
                    public List<Category> apply(CategoriesResponse categoryResponse) throws Exception {
                        return categoryResponse.getCategories();
                    }
                });
    }
}
