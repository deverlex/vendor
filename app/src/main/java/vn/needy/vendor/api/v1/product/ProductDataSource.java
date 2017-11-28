package vn.needy.vendor.api.v1.product;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import vn.needy.vendor.api.base.BaseResponse;

/**
 * Created by lion on 09/11/2017.
 */

public interface ProductDataSource {

    interface LocalDataSource {

    }

    interface RemoteDataSource {

        Observable<BaseResponse> uploadImage(long productId, MultipartBody.Part image);

//        Observable<BaseResponse> addProduct()
    }
}
