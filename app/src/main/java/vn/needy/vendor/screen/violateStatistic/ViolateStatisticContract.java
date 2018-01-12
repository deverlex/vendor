package vn.needy.vendor.screen.violateStatistic;

import vn.needy.vendor.screen.BasePresenter;
import vn.needy.vendor.screen.BaseViewModel;

/**
 * Created by lion on 11/01/2018.
 */

public interface ViolateStatisticContract {

    interface ViewModel extends BaseViewModel<Presenter>{

        void onBackPressed();
    }

    interface Presenter extends BasePresenter{

    }
}
