package vn.needy.vendor.repository;

import io.reactivex.Observable;
import vn.needy.vendor.port.message.BaseResponse;
import vn.needy.vendor.repository.remote.category.response.CategoriesResp;

/**
 * Created by lion on 10/12/2017.
 */

public class CategoryRepository {

    private CategoryData.Remote mRemote;

    public CategoryRepository(CategoryData.Remote remote) {
        mRemote = remote;
    }

    public Observable<BaseResponse<CategoriesResp>> getCategories(String category) {
        return mRemote.getCategories(category);
    }

    public Observable<BaseResponse<CategoriesResp>> getCompanyCategories(String category, String companyId) {
        return mRemote.getCompanyCategories(category, companyId);
    }
}
