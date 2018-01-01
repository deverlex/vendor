package vn.needy.vendor.repository.remote.attribute;

import io.reactivex.Observable;
import vn.needy.vendor.port.message.ResponseWrapper;
import vn.needy.vendor.repository.remote.attribute.response.AttributeInfoResponse;
import vn.needy.vendor.port.api.VendorApi;
import vn.needy.vendor.repository.AttributeData;
import vn.needy.vendor.repository.remote.BaseDataRemote;

/**
 * Created by lion on 10/12/2017.
 */

public class AttributeDataRemote extends BaseDataRemote<VendorApi> implements AttributeData.Remote {

    public AttributeDataRemote(VendorApi api) {
        super(api);
    }

    @Override
    public Observable<ResponseWrapper<AttributeInfoResponse>> getAttributesCategory(String category) {
        return mApi.getAttributesCategory(category);
    }
}
