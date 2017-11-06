package vn.needy.vendor.screen.category;

/**
 * Created by lion on 03/11/2017.
 */

public class CategoriesPresenter implements CategoriesContract.Presenter {

    private final CategoriesContract.ViewModel mViewModel;

    public CategoriesPresenter(CategoriesContract.ViewModel viewModel) {
        mViewModel = viewModel;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }
}
