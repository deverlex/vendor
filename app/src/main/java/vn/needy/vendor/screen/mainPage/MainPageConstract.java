package vn.needy.vendor.screen.mainPage;

import android.widget.RadioGroup;

import vn.needy.vendor.screen.BasePresenter;
import vn.needy.vendor.screen.BaseViewModel;

/**
 * Created by lion on 21/10/2017.
 */

public interface MainPageConstract {

    interface ViewModel extends BaseViewModel<Presenter> {

        void onCheckedChanged(RadioGroup radioGroup, int id);

        void onClickAddProduct();
    }

    interface Presenter extends BasePresenter {

    }
}
