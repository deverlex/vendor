package vn.needy.vendor.repository;

import io.reactivex.Observable;
import vn.needy.vendor.port.message.ResponseWrapper;
import vn.needy.vendor.repository.remote.product.request.AddProductPnReq;
import vn.needy.vendor.repository.remote.product.respone.ProductPnInfoResp;

/**
 * Created by lion on 10/12/2017.
 */

public interface ProductData {
    interface Remote {
        Observable<ResponseWrapper> addProductPn(String companyId, String storeId, AddProductPnReq request);

        Observable<ResponseWrapper<ProductPnInfoResp>> getProductsPnOfCompany(String companyId, String category);
    }

    interface Local {

    }
}
