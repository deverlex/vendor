package vn.needy.vendor.data.source;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import vn.needy.vendor.data.source.remote.api.response.BaseResponse;

/**
 * Created by lion on 09/11/2017.
 */

public interface ProductDataSource {

    interface LocalDataSource {

    }

    interface RemoteDataSource {

        Observable<BaseResponse> uploadImage(long productId, MultipartBody.Part image);
    }
}
