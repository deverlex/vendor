package vn.needy.vendor.repository.remote.store;

import io.reactivex.Observable;
import vn.needy.vendor.port.api.VendorApi;
import vn.needy.vendor.port.message.RequestWrapper;
import vn.needy.vendor.port.message.ResponseWrapper;
import vn.needy.vendor.repository.StoreData;
import vn.needy.vendor.repository.remote.BaseDataRemote;
import vn.needy.vendor.repository.remote.store.request.CreateStoreRequest;
import vn.needy.vendor.repository.remote.store.request.UpdateStoreInfoRequest;
import vn.needy.vendor.repository.remote.store.response.CreateStoreRespone;
import vn.needy.vendor.repository.remote.store.response.StoreInfoResponse;

/**
 * Created by lion on 12/12/2017.
 */

public class StoreDataRemote extends BaseDataRemote<VendorApi> implements StoreData.Remote {

    public StoreDataRemote(VendorApi api) {
        super(api);
    }

    @Override
    public Observable<ResponseWrapper<StoreInfoResponse>> getStoreInfo(String storeId) {
        return mApi.getStoreInfo(storeId);
    }

    @Override
    public Observable<ResponseWrapper> updateStoreInfo(String storeId, UpdateStoreInfoRequest infoReq) {
        return mApi.updateStoreInfo(storeId, new RequestWrapper<UpdateStoreInfoRequest>().setData(infoReq));
    }

    @Override
    public Observable<ResponseWrapper<CreateStoreRespone>> createStore(long companyId, CreateStoreRequest request) {
        return mApi.createStore(companyId, new RequestWrapper<CreateStoreRequest>().setData(request));
    }
}
