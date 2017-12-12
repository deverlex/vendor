package vn.needy.vendor.repository;

import io.reactivex.Observable;
import vn.needy.vendor.repository.remote.attribute.response.AttributeInfoResp;

/**
 * Created by lion on 10/12/2017.
 */

public interface AttributeData {

    interface Remote {
        Observable<AttributeInfoResp> getAttributesCategory(String category);
    }

    interface Local {

    }
}
