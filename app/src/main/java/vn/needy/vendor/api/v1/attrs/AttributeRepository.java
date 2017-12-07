package vn.needy.vendor.api.v1.attrs;

import io.reactivex.Observable;
import vn.needy.vendor.api.v1.attrs.response.AttributeResponse;

/**
 * Created by lion on 06/12/2017.
 */

public class AttributeRepository {
    AttributeDataSource.RemoteDataSource mAttributeRemoteDataSource;

    public AttributeRepository(AttributeDataSource.RemoteDataSource attributeRemoteDataSource) {
        mAttributeRemoteDataSource = attributeRemoteDataSource;
    }

    public Observable<AttributeResponse> getAttributeCategory(String category) {
        return mAttributeRemoteDataSource.getListAttributeCategory(category);
    }
}
