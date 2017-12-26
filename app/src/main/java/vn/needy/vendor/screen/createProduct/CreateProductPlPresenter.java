package vn.needy.vendor.screen.createProduct;

/**
 * Created by lion on 28/11/2017.
 */

public class CreateProductPlPresenter implements CreateProductPlContract.Presenter{

    private CreateProductPlContract.ViewModel mViewModel;

    public CreateProductPlPresenter(CreateProductPlContract.ViewModel viewModel) {
        this.mViewModel = viewModel;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }
}
