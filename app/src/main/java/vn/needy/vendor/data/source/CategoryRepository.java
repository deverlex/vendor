package vn.needy.vendor.data.source;

import java.util.List;

import io.reactivex.Observable;
import vn.needy.vendor.data.model.Category;

/**
 * Created by lion on 06/11/2017.
 */

public class CategoryRepository {

    private CategoryDataSource.RemoteDataSource mCategoryRemoteDataSource;

    public CategoryRepository(CategoryDataSource.RemoteDataSource categoryRemoteDataSource) {
        mCategoryRemoteDataSource = categoryRemoteDataSource;
    }

    public Observable<List<Category>> getCategories(String companyId) {
        return mCategoryRemoteDataSource.getCategories(companyId);
    }

    public Observable<List<Category>> getSubCategories(String category, String companyId) {
        return mCategoryRemoteDataSource.getSubCategories(category, companyId);
    }
}
