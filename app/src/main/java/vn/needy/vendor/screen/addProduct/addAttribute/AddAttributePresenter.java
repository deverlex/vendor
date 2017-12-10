package vn.needy.vendor.screen.addProduct.addAttribute;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import vn.needy.vendor.datasource.attribute.AttributeDataSource;
import vn.needy.vendor.datasource.attribute.AttributeDataSourceImpl;
import vn.needy.vendor.datasource.attribute.response.AttributesResponse;
import vn.needy.vendor.model.Attribute;
import vn.needy.vendor.model.Category;

/**
 * Created by lion on 04/12/2017.
 */

public class AddAttributePresenter implements AddAttributeContract.Presenter {

    private static final String TAG = AddAttributePresenter.class.getName();

    private AddAttributeContract.ViewModel mViewModel;
    private AttributeDataSource mAttributeDataSource;
    private CompositeDisposable mCompositeDisposable;

    public AddAttributePresenter(AddAttributeContract.ViewModel viewModel) {
        mViewModel = viewModel;
        mAttributeDataSource = new AttributeDataSourceImpl();
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
    public void onGetListAttributes(Category category) {
        Disposable disposable = mAttributeDataSource
                .getListAttributeCategory(category.getName())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<AttributesResponse>() {
                    @Override
                    public void accept(AttributesResponse attributeResponse) throws Exception {
                        List<Attribute> attributes = attributeResponse.getAttrs();
                        if (attributes != null) {
                            mViewModel.onListAttributeLoaded(attributes);
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
