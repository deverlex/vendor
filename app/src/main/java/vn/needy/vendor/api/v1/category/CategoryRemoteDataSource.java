package vn.needy.vendor.api.v1.category;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import vn.needy.vendor.api.base.BaseRemoteDataSource;
import vn.needy.vendor.database.model.Category;
import vn.needy.vendor.api.v1.category.response.CategoryResponse;
import vn.needy.vendor.service.VendorApi;

/**
 * Created by lion on 06/11/2017.
 */

public class CategoryRemoteDataSource extends BaseRemoteDataSource
        implements CategoryDataSource.RemoteDataSource {

    private static final String TAG = CategoryRemoteDataSource.class.getName();

    public CategoryRemoteDataSource(VendorApi vendorApi) {
        super(vendorApi);
    }

    @Override
    public Observable<List<Category>> getCategories(String companyId) {
        return mVendorApi.getCategories(companyId).map(new Function<CategoryResponse, List<Category>>() {
            @Override
            public List<Category> apply(CategoryResponse categoryResponse) throws Exception {
                return categoryResponse.getCategories();
            }
        });
    }

    @Override
    public Observable<List<Category>> getSubCategories(String category, String companyId) {
        return mVendorApi.getSubCategories(category, companyId).map(new Function<CategoryResponse, List<Category>>() {
            @Override
            public List<Category> apply(CategoryResponse categoryResponse) throws Exception {
                return categoryResponse.getCategories();
            }
        });
    }
}
