package vn.needy.vendor.screen.listorder;

import java.util.ArrayList;
import java.util.List;

import vn.needy.vendor.model.Order;

/**
 * Created by lion on 21/10/2017.
 */

public class ListOrderPresenter implements ListOrderContract.Presenter{

    private ListOrderContract.ViewModel mViewModel;

    public ListOrderPresenter(ListOrderContract.ViewModel viewModel) {
        this.mViewModel = viewModel;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void getReceivingOrders() {
        List<Order> orders = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            Order order = new Order();
            order.setStatus(0);
            order.setReceiveFrom("11:00 19-12-2017");
            order.setReceiveTo("11:00 20-12-2017");
            order.setTransportFrom("12:00 19-12-2017");
            order.setTransportTo("12:00 20-12-2017");
            orders.add(order);
        }
        mViewModel.setOrders(orders);
    }

    @Override
    public void getTransportingOrders() {
        List<Order> orders = new ArrayList<>();
        for (int i = 1; i <= 15; i++) {
            Order order = new Order();
            order.setStatus(1);
            order.setReceiveFrom("11:00 19-12-2017");
            order.setReceiveTo("11:00 20-12-2017");
            order.setTransportFrom("12:00 19-12-2017");
            order.setTransportTo("12:00 20-12-2017");
            orders.add(order);
        }
        mViewModel.setOrders(orders);
    }
}
