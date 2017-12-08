package vn.needy.vendor.api.v1.product;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import vn.needy.vendor.api.base.BaseDataSource;
import vn.needy.vendor.api.base.BaseResponse;
import vn.needy.vendor.service.VendorApi;

/**
 * Created by lion on 09/11/2017.
 */

public class ProductRemoteDataSource extends BaseDataSource
        implements ProductDataSource.RemoteDataSource {

    public ProductRemoteDataSource(VendorApi vendorApi) {
        super(vendorApi);
    }

    @Override
    public Observable<BaseResponse> uploadImage(long productId, MultipartBody.Part image) {
        return mVendorApi.uploadImageProduct(productId, image);
    }
}
