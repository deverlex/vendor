package vn.needy.vendor.repository;

import io.reactivex.Observable;
import vn.needy.vendor.port.message.ResponseWrapper;
import vn.needy.vendor.repository.remote.product.request.AddProductPnReq;
import vn.needy.vendor.repository.remote.product.respone.ProductPnInfoResp;

/**
 * Created by lion on 10/12/2017.
 */

public class ProductRepository {
    private ProductData.Remote mRemote;
    private ProductData.Local mLocal;

    public ProductRepository(ProductData.Remote mRemote, ProductData.Local mLocal) {
        this.mRemote = mRemote;
        this.mLocal = mLocal;
    }

    public Observable<ResponseWrapper> addProductPn(String companyId, String storeId, AddProductPnReq request) {
        return mRemote.addProductPn(companyId, storeId, request);
    }

    public Observable<ResponseWrapper<ProductPnInfoResp>> getAllProductsPnOfCompany(String companyId) {
        return mRemote.getAllProductsPnOfCompany(companyId);
    }
}
