package vn.needy.vendor.api.v1.category;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import vn.needy.vendor.api.base.BaseDataSource;
import vn.needy.vendor.database.model.Category;
import vn.needy.vendor.api.v1.category.response.CategoryResponse;
import vn.needy.vendor.service.VendorApi;

/**
 * Created by lion on 06/11/2017.
 */

public class CategoryRemoteDataSource extends BaseDataSource
        implements CategoryDataSource.RemoteDataSource {

    private static final String TAG = CategoryRemoteDataSource.class.getName();

    public CategoryRemoteDataSource(VendorApi vendorApi) {
        super(vendorApi);
    }

    @Override
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
    public Observable<List<Category>> getCompanyLinkCategories(String category, String companyId) {
        return mVendorApi.getCompanyLinkCategories(category, companyId)
                .map(new Function<CategoryResponse, List<Category>>() {
            @Override
            public List<Category> apply(CategoryResponse categoryResponse) throws Exception {
                return categoryResponse.getCategories();
            }
        });
    }
}
