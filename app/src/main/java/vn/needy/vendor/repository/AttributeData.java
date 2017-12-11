package vn.needy.vendor.repository;

import io.reactivex.Observable;
import vn.needy.vendor.repository.remote.attribute.response.AttributesResp;

/**
 * Created by lion on 10/12/2017.
 */

public interface AttributeData {

    interface Remote {
        Observable<AttributesResp> getAttributesCategory(String category);
    }

    interface Local {

    }
}
