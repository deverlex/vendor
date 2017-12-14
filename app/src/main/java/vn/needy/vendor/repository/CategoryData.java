package vn.needy.vendor.repository;

import io.reactivex.Observable;
import vn.needy.vendor.port.message.ResponseWrapper;
import vn.needy.vendor.repository.remote.category.response.CategoriesResp;

/**
 * Created by lion on 10/12/2017.
 */

public interface CategoryData {

    interface Remote {
        Observable<ResponseWrapper<CategoriesResp>> getCategories(String category);

        Observable<ResponseWrapper<CategoriesResp>> getCompanyCategories(String category, String companyId);
    }

    interface Local {

    }
}
