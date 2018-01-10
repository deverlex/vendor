package vn.needy.vendor.screen.businessManage;

/**
 * Created by lion on 10/01/2018.
 */

public class BusinessManagePresenter implements BusinessManageContract.Presenter {

    private BusinessManageContract.ViewModel mViewModel;

    public BusinessManagePresenter(BusinessManageContract.ViewModel mViewModel) {
        this.mViewModel = mViewModel;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }
}