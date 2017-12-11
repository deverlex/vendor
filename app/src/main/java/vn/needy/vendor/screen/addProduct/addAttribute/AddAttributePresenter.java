package vn.needy.vendor.screen.addProduct.addAttribute;

import java.util.List;

import vn.needy.vendor.model.wrapper.CategoryWrapper;

/**
 * Created by lion on 04/12/2017.
 */

public class AddAttributePresenter implements AddAttributeContract.Presenter {

    private static final String TAG = AddAttributePresenter.class.getName();

    private AddAttributeContract.ViewModel mViewModel;
//    private AttributeRepository mAttributeRepository;
//    private CompositeDisposable mCompositeDisposable;

    public AddAttributePresenter(AddAttributeContract.ViewModel viewModel) {
        mViewModel = viewModel;
//        mAttributeRepository = new AttributeRepository(
//
//        );
//        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {
//        mCompositeDisposable.dispose();
    }

    @Override
    public void onGetListAttributes(CategoryWrapper category) {
//        Disposable disposable = mAttributeRepository
//                .getAttributeCategory(category.getName())
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<AttributesResponse>() {
//                    @Override
//                    public void accept(AttributesResponse attributeResponse) throws Exception {
//                        List<Attribute> attributes = attributeResponse.getAttrs();
//                        if (attributes != null) {
//                            mViewModel.onListAttributeLoaded(attributes);
//                        }
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(Throwable throwable) throws Exception {
//
//                    }
//                });
//        mCompositeDisposable.add(disposable);
    }

    @Override
    public boolean onValidateDataInput(List<Object> attrs) {
        return false;
    }
}
