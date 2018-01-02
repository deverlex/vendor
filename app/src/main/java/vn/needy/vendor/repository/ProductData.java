package vn.needy.vendor.repository;

import io.reactivex.Observable;
import vn.needy.vendor.port.message.ResponseWrapper;
import vn.needy.vendor.repository.remote.product.respone.ProductPnInfoResponse;

/**
 * Created by lion on 10/12/2017.
 */

public interface ProductData {
    interface Remote {
        Observable<ResponseWrapper> addProduct(String productType, String companyId, String storeId, Object request);

        Observable<ResponseWrapper<ProductPnInfoResponse>> getProductsPnOfCompany(String companyId, String category);
    }

    interface Local {

    }
}
