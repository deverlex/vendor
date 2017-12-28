package vn.needy.vendor.screen.createProduct.childProduct;

/**
 * Created by lion on 08/12/2017.
 */

public class ChildProductPresenter implements ChildProductContract.Presenter {

    private ChildProductContract.ViewModel mViewModel;

    public ChildProductPresenter(ChildProductContract.ViewModel viewModel) {
        mViewModel = viewModel;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }
}
