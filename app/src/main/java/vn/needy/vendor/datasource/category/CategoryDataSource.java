package vn.needy.vendor.datasource.category;

import java.util.List;

import io.reactivex.Observable;
import vn.needy.vendor.model.Category;

/**
 * Created by lion on 06/11/2017.
 */

public interface CategoryDataSource {

    Observable<List<Category>> getCategories(String category);

    Observable<List<Category>> getCompanyCategories(String category);
}
