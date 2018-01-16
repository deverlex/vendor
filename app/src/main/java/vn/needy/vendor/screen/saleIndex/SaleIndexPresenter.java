package vn.needy.vendor.screen.saleIndex;

/**
 * Created by lion on 11/01/2018.
 */

public class SaleIndexPresenter implements SaleIndexContract.Presenter{

    private SaleIndexContract.ViewModel mViewModel;

    public SaleIndexPresenter(SaleIndexContract.ViewModel mViewModel) {
        this.mViewModel = mViewModel;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }
}
