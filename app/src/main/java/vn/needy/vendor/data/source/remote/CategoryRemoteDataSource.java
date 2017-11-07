package vn.needy.vendor.data.source.remote;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import vn.needy.vendor.data.model.Category;
import vn.needy.vendor.data.source.CategoryDataSource;
import vn.needy.vendor.data.source.remote.api.response.CategoryResponse;
import vn.needy.vendor.data.source.remote.api.service.VendorApi;

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
