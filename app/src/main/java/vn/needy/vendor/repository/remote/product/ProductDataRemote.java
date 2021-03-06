package vn.needy.vendor.repository.remote.product;

import io.reactivex.Observable;
import vn.needy.vendor.port.api.VendorApi;
import vn.needy.vendor.port.message.RequestWrapper;
import vn.needy.vendor.port.message.ResponseWrapper;
import vn.needy.vendor.repository.ProductData;
import vn.needy.vendor.repository.remote.BaseDataRemote;
import vn.needy.vendor.repository.remote.product.respone.ProductPnInfoResponse;

/**
 * Created by lion on 10/12/2017.
 */

public class ProductDataRemote extends BaseDataRemote<VendorApi> implements ProductData.Remote{
    public ProductDataRemote(VendorApi api) {
        super(api);
    }

    @Override
    public Observable<ResponseWrapper> addProduct(String productType, String companyId, String storeId, Object request) {
        return mApi.addProductPn(productType, companyId, storeId, new RequestWrapper<>().setData(request));
    }

    @Override
    public Observable<ResponseWrapper<ProductPnInfoResponse>> getProductsPnOfCompany(String companyId, String category) {
        return mApi.getAllProductsPnOfCompany(companyId, category);
    }
}
