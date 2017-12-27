package vn.needy.vendor.screen.createProduct;

import java.util.List;

import vn.needy.vendor.model.Image;
import vn.needy.vendor.repository.remote.product.request.AddProductPnReq;

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

    @Override
    public void uploadProduct(AddProductPnReq request, List<Image> images) {

    }

    @Override
    public boolean validateDataInput(AddProductPnReq request) {
        return false;
    }
}
