package vn.needy.vendor.datasource;

import java.util.List;

import io.reactivex.Observable;
import vn.needy.vendor.datasource.model.Category;

/**
 * Created by lion on 06/11/2017.
 */

public interface CategoryDataSource {

    Observable<List<Category>> getLinkCategories(String category);

    Observable<List<Category>> getCompanyLinkCategories(String category);
}
