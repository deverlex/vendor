package vn.needy.vendor.repository;

import io.reactivex.Observable;
import vn.needy.vendor.repository.remote.attribute.response.AttributesResponse;

/**
 * Created by lion on 10/12/2017.
 */

public class AttributeRepository {

    private AttributeData.Remote mRemote;
    private AttributeData.Local mLocal;

    public AttributeRepository(AttributeData.Remote remote, AttributeData.Local local) {
        mRemote = remote;
        mLocal = local;
    }

    public Observable<AttributesResponse> getAttributeCategory(String category) {
        return mRemote.getAttributesCategory(category);
    }
}
