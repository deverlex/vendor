package vn.needy.vendor.repository;

import io.reactivex.Observable;
import vn.needy.vendor.port.message.ResponseWrapper;
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

    public Observable<ResponseWrapper> addProduct(String productType, String companyId, String storeId, Object request) {
        return mRemote.addProduct(productType, companyId, storeId, request);
    }

    public Observable<ResponseWrapper<ProductPnInfoResp>> getProductsPnOfCompany( String companyId, String category) {
        return mRemote.getProductsPnOfCompany(companyId, category);
    }
}
