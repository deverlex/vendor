package vn.needy.vendor.repository;

import io.reactivex.Observable;
import vn.needy.vendor.repository.remote.category.response.CategoriesResponse;

/**
 * Created by lion on 10/12/2017.
 */

public class CategoryRepository {

    private CategoryData.Remote mRemote;
    private CategoryData.Local mLocal;

    public CategoryRepository(CategoryData.Remote remote, CategoryData.Local local) {
        mRemote = remote;
        mLocal = local;
    }

    public Observable<CategoriesResponse> getCategories(String category) {
        return mRemote.getCategories(category);
    }

    public Observable<CategoriesResponse> getCompanyCategories(String category, String companyId) {
        return mRemote.getCompanyCategories(category, companyId);
    }
}
