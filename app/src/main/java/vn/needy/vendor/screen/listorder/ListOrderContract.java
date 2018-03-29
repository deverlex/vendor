package vn.needy.vendor.screen.listorder;

import java.util.List;

import vn.needy.vendor.domain.Order;
import vn.needy.vendor.screen.BasePresenter;
import vn.needy.vendor.screen.BaseViewModel;

/**
 * Created by lion on 21/10/2017.
 */

public class ListOrderContract {

    interface ViewModel extends BaseViewModel<Presenter> {
        void setOrders(List<Order> orders);

        void onClickChangeList();
    }

    interface Presenter extends BasePresenter {
        void getReceivingOrders();

        void getTransportingOrders();
    }
}
