package vn.needy.vendor.repository;

import io.reactivex.Observable;
import vn.needy.vendor.port.message.BaseResponse;
import vn.needy.vendor.repository.remote.attribute.response.AttributeInfoResp;

/**
 * Created by lion on 10/12/2017.
 */

public class AttributeRepository {

    private AttributeData.Remote mRemote;

    public AttributeRepository(AttributeData.Remote remote) {
        mRemote = remote;
    }

    public Observable<BaseResponse<AttributeInfoResp>> getAttributeCategory(String category) {
        return mRemote.getAttributesCategory(category);
    }
}
