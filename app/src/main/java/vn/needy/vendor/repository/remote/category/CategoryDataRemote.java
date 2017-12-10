package vn.needy.vendor.repository.remote.category;

import io.reactivex.Observable;
import vn.needy.vendor.repository.remote.category.response.CategoriesResponse;
import vn.needy.vendor.port.api.VendorApi;
import vn.needy.vendor.repository.CategoryData;
import vn.needy.vendor.repository.remote.BaseDataRemote;

/**
 * Created by lion on 10/12/2017.
 */

public class CategoryDataRemote extends BaseDataRemote<VendorApi> implements CategoryData.Remote {

    public CategoryDataRemote(VendorApi api) {
        super(api);
    }

    @Override
    public Observable<CategoriesResponse> getCategories(String category) {
        return mApi.getCategories(category);
    }

    @Override
    public Observable<CategoriesResponse> getCompanyCategories(String category, String companyId) {
        return mApi.getCompanyCategories(category, companyId);
    }
}
