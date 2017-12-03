package vn.needy.vendor.api.v1.category;

import java.util.List;

import io.reactivex.Observable;
import vn.needy.vendor.database.model.Category;

/**
 * Created by lion on 06/11/2017.
 */

public interface CategoryDataSource {

    interface RemoteDataSource {
        Observable<List<Category>> getCategories(String companyId);

        Observable<List<Category>> getSubCategories(String category, String companyId);
    }
}