package vn.needy.vendor.repository;

import android.util.Log;

import io.reactivex.Observable;
import vn.needy.vendor.port.message.ResponseWrapper;
import vn.needy.vendor.repository.remote.product.request.AddProductPnReq;

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
}
