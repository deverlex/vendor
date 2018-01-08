package vn.needy.vendor.screen.review;

import vn.needy.vendor.screen.BasePresenter;
import vn.needy.vendor.screen.BaseViewModel;

/**
 * Created by lion on 18/12/2017.
 */

interface ReviewContract {

    interface ViewModel extends BaseViewModel<Presenter> {

        void onBackPressed();


    }

    interface Presenter extends BasePresenter {

    }

}
