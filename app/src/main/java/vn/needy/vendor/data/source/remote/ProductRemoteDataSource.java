package vn.needy.vendor.data.source.remote;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import vn.needy.vendor.data.source.ProductDataSource;
import vn.needy.vendor.data.source.remote.api.response.BaseResponse;
import vn.needy.vendor.data.source.remote.api.service.VendorApi;

/**
 * Created by lion on 09/11/2017.
 */

public class ProductRemoteDataSource extends BaseRemoteDataSource
        implements ProductDataSource.RemoteDataSource {

    public ProductRemoteDataSource(VendorApi vendorApi) {
        super(vendorApi);
    }

    @Override
    public Observable<BaseResponse> uploadImage(long productId, MultipartBody.Part image) {
        return mVendorApi.uploadImageProduct(productId, image);
    }
}
