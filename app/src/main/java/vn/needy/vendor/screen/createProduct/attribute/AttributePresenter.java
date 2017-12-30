package vn.needy.vendor.screen.createProduct.attribute;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import vn.needy.vendor.model.wrapper.AttributeWrapper;
import vn.needy.vendor.model.wrapper.CategoryWrapper;
import vn.needy.vendor.port.message.ResponseWrapper;
import vn.needy.vendor.port.service.VendorServiceClient;
import vn.needy.vendor.repository.AttributeRepository;
import vn.needy.vendor.repository.remote.attribute.AttributeDataRemote;
import vn.needy.vendor.repository.remote.attribute.response.AttributeInfoResp;

/**
 * Created by lion on 04/12/2017.
 */

public class AttributePresenter implements AttributeContract.Presenter {

    private static final String TAG = AttributePresenter.class.getName();

    private AttributeContract.ViewModel mViewModel;
    private AttributeRepository mAttributeRepository;
    private CompositeDisposable mCompositeDisposable;

    public AttributePresenter(AttributeContract.ViewModel viewModel) {
        mViewModel = viewModel;
        mAttributeRepository = new AttributeRepository(
            new AttributeDataRemote(VendorServiceClient.getInstance())
        );
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {
        mCompositeDisposable.dispose();
    }

    @Override
    public void onGetListAttributes(CategoryWrapper category) {
        Disposable disposable = mAttributeRepository
                .getAttributeCategory(category.getName())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseWrapper<AttributeInfoResp>>() {
                    @Override
                    public void accept(ResponseWrapper<AttributeInfoResp> resp) throws Exception {
                        AttributeInfoResp data = resp.getData();
                        if (data != null) {
                            List<AttributeWrapper> attributes = data.getAttributes();
                            if (attributes != null) {
                                mViewModel.onListAttributeLoaded(attributes);
                            }
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
        mCompositeDisposable.add(disposable);
    }

    @Override
    public boolean onValidateDataInput(List<Object> attrs) {
        return false;
    }
}
