package vn.needy.vendor.screen.mainPage.priceLater;


import java.util.ArrayList;
import java.util.List;

import vn.needy.vendor.port.wrapper.ProductPlWrapper;

/**
 * Created by truongpq on 02/01/2018.
 */

public class MainPagePlPresenter implements MainPagePlContract.Presenter{
    private MainPagePlContract.ViewModel mViewModel;

    public MainPagePlPresenter(MainPagePlContract.ViewModel viewModel) {
        this.mViewModel = viewModel;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void getProductByCategory(String category) {
        List<ProductPlWrapper> productPlWrappers = new ArrayList<>();
        for (int i = 0 ; i < 10; i++) {
            productPlWrappers.add(new ProductPlWrapper());
        }

        mViewModel.onUpdateProducts(productPlWrappers);
    }
}
