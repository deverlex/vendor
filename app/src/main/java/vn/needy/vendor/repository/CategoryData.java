package vn.needy.vendor.repository;

import io.reactivex.Observable;
import vn.needy.vendor.repository.remote.category.response.CategoriesResponse;

/**
 * Created by lion on 10/12/2017.
 */

public interface CategoryData {

    interface Remote {
        Observable<CategoriesResponse> getCategories(String category);

        Observable<CategoriesResponse> getCompanyCategories(String category, String companyId);
    }

    interface Local {

    }
}
