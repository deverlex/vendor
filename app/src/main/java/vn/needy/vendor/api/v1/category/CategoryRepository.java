package vn.needy.vendor.api.v1.category;

import java.util.List;

import io.reactivex.Observable;
import vn.needy.vendor.database.model.Category;
import vn.needy.vendor.database.model.Company;
import vn.needy.vendor.database.sharedprf.SharedPrefsImpl;
import vn.needy.vendor.database.sharedprf.SharedPrefsKey;

/**
 * Created by lion on 06/11/2017.
 */

public class CategoryRepository {

    private CategoryDataSource.RemoteDataSource mCategoryRemoteDataSource;

    public CategoryRepository(CategoryDataSource.RemoteDataSource categoryRemoteDataSource) {
        mCategoryRemoteDataSource = categoryRemoteDataSource;
    }

    public Observable<List<Category>> getLinkCategories(String category) {
        return mCategoryRemoteDataSource.getLinkCategories(category);
    }

    public Observable<List<Category>> getCompanyLinkCategories(String category) {
        Company company = SharedPrefsImpl.getInstance()
                .getObject(SharedPrefsKey.COMPANY, Company.class);
        if (company != null) {
            return mCategoryRemoteDataSource.getCompanyLinkCategories(category, company.getId());
        } else {
            return mCategoryRemoteDataSource.getLinkCategories(category);
        }
    }
}
