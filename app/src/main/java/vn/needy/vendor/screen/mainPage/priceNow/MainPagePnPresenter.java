package vn.needy.vendor.screen.mainPage.priceNow;

/**
 * Created by truongpq on 02/01/2018.
 */

public class MainPagePnPresenter implements MainPagePnContract.Presenter{
    private MainPagePnContract.ViewModel mViewModel;

    public MainPagePnPresenter(MainPagePnContract.ViewModel viewModel) {
        this.mViewModel = viewModel;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }
}
