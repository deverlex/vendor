package vn.needy.vendor.screen.storeProfile;

/**
 * Created by lion on 12/12/2017.
 */

public class StoreProfilePresenter implements StoreProfileContract.Presenter {

    private StoreProfileContract.ViewModel mViewModel;

    public StoreProfilePresenter(StoreProfileContract.ViewModel viewModel) {
        mViewModel = viewModel;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }
}
