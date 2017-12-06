package vn.needy.vendor.api.v1.category;

import java.util.List;

import io.reactivex.Observable;
import vn.needy.vendor.database.model.Category;

/**
 * Created by lion on 06/11/2017.
 */

public class CategoryRepository {

    private CategoryDataSource.RemoteDataSource mCategoryRemoteDataSource;

    public CategoryRepository(CategoryDataSource.RemoteDataSource categoryRemoteDataSource) {
        mCategoryRemoteDataSource = categoryRemoteDataSource;
    }

    public Observable<List<Category>> getCategories(String category) {
        return mCategoryRemoteDataSource.getLinkCategories(category);
    }

    public Observable<List<Category>> getSubCategories(String category, String companyId) {
        return mCategoryRemoteDataSource.getCompanyLinkCategories(category, companyId);
    }
}
