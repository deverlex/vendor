package vn.needy.vendor.data.source;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import vn.needy.vendor.data.source.remote.api.response.BaseResponse;

/**
 * Created by lion on 09/11/2017.
 */

public class ProductRepository {

    private ProductDataSource.LocalDataSource mProductLocalDataSource;
    private ProductDataSource.RemoteDataSource mProductRemoteDataSource;

    public ProductRepository(ProductDataSource.LocalDataSource productLocalDataSource,
                             ProductDataSource.RemoteDataSource productRemoteDataSource) {
        mProductLocalDataSource = productLocalDataSource;
        mProductRemoteDataSource = productRemoteDataSource;
    }

    public Observable<BaseResponse> uploadImage(long productId, MultipartBody.Part image) {
        return mProductRemoteDataSource.uploadImage(productId, image);
    }
}
