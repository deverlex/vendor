package vn.needy.vendor.screen.mainPage.priceLater;

import vn.needy.vendor.screen.mainPage.priceNow.MainPagePnContract;

/**
 * Created by truongpq on 02/01/2018.
 */

public class MainPagePlPresenter implements MainPagePnContract.Presenter{
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
}
