package vn.needy.vendor.repository.remote.product;

import io.reactivex.Observable;
import vn.needy.vendor.port.api.VendorApi;
import vn.needy.vendor.port.message.RequestWrapper;
import vn.needy.vendor.port.message.ResponseWrapper;
import vn.needy.vendor.repository.ProductData;
import vn.needy.vendor.repository.remote.BaseDataRemote;
import vn.needy.vendor.repository.remote.product.request.AddProductPnReq;
import vn.needy.vendor.repository.remote.product.respone.ProductPnInfoResp;

/**
 * Created by lion on 10/12/2017.
 */

public class ProductDataRemote extends BaseDataRemote<VendorApi> implements ProductData.Remote{
    public ProductDataRemote(VendorApi api) {
        super(api);
    }

    @Override
    public Observable<ResponseWrapper> addProductPn(String companyId, String storeId, AddProductPnReq request) {
        return mApi.addProductPn(companyId, storeId, new RequestWrapper<AddProductPnReq>().setData(request));
    }

    @Override
    public Observable<ResponseWrapper<ProductPnInfoResp>> getProductsPnOfCompany(String companyId, String category) {
        return mApi.getAllProductsPnOfCompany(companyId, category);
    }
}
