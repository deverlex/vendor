package vn.needy.vendor.repository;

import io.reactivex.Observable;
import vn.needy.vendor.port.message.ResponseWrapper;
import vn.needy.vendor.repository.remote.attribute.response.AttributeInfoResponse;

/**
 * Created by lion on 10/12/2017.
 */

public interface AttributeData {

    interface Remote {
        Observable<ResponseWrapper<AttributeInfoResponse>> getAttributesCategory(String category);
    }

    interface Local {

    }
}
