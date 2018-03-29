package vn.needy.vendor.repository;

import io.reactivex.Observable;
import vn.needy.vendor.port.message.ResponseWrapper;
import vn.needy.vendor.repository.remote.attribute.response.AttributeInfoResponse;

/**
 * Created by lion on 10/12/2017.
 */

public class AttributeRepository {

    private AttributeData.Remote mRemote;

    public AttributeRepository(AttributeData.Remote remote) {
        mRemote = remote;
    }

    public Observable<ResponseWrapper<AttributeInfoResponse>> getAttributeCategory(String category) {
        return mRemote.getAttributesCategory(category);
    }
}
