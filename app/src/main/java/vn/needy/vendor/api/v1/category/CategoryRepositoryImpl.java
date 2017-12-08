package vn.needy.vendor.api.v1.category;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import vn.needy.vendor.api.base.BaseRepository;
import vn.needy.vendor.api.v1.category.response.CategoryResponse;
import vn.needy.vendor.database.model.Category;
import vn.needy.vendor.database.model.Company;
import vn.needy.vendor.database.sharedprf.SharedPrefsApi;
import vn.needy.vendor.database.sharedprf.SharedPrefsImpl;
import vn.needy.vendor.database.sharedprf.SharedPrefsKey;
import vn.needy.vendor.service.VendorApi;

/**
 * Created by lion on 06/11/2017.
 */

public class CategoryRepositoryImpl extends BaseRepository implements CategoryRepository {

    private SharedPrefsApi mPrefsApi;

    public CategoryRepositoryImpl(VendorApi vendorApi, SharedPrefsApi prefsApi) {
        super(vendorApi);
        mPrefsApi = prefsApi;
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
        String companyId = mPrefsApi.get(SharedPrefsKey.COMPANY, String.class);
        return mVendorApi.getCompanyLinkCategories(category, companyId)
                .map(new Function<CategoryResponse, List<Category>>() {
                    @Override
                    public List<Category> apply(CategoryResponse categoryResponse) throws Exception {
                        return categoryResponse.getCategories();
                    }
                });
    }
}
