package vn.needy.vendor.screen.listorder;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;

import java.util.List;

import vn.needy.vendor.model.Order;
import vn.needy.vendor.screen.BaseRecyclerViewAdapter;

/**
 * Created by lion on 21/10/2017.
 */

public class ListOrderViewModel extends BaseObservable implements ListOrderContract.ViewModel,
        BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object>{

    private ListOrderContract.Presenter mPresenter;

    private Context mContext;
    private ListOrderAdapter mAdapter;
    private boolean mIsTransportingList;

    public ListOrderViewModel(Context mContext, ListOrderAdapter adapter) {
        this.mContext = mContext;
        mAdapter = adapter;
        mAdapter.setItemClickListener(this);
    }

    @Override
    public void onStart() {
        mPresenter.getReceivingOrders();
    }

    @Override
    public void onStop() {

    }

    @Override
    public void setPresenter(ListOrderContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Bindable
    public ListOrderAdapter getAdapter() {
        return mAdapter;
    }

    @Override
    public void setOrders(List<Order> orders) {
        mAdapter.setData(orders);
    }

    @Override
    public void onClickChangeList() {
        mIsTransportingList = !mIsTransportingList;

        if (mIsTransportingList) {
            mPresenter.getTransportingOrders();
        } else {
            mPresenter.getReceivingOrders();
        }
    }

    @Override
    public void onItemRecyclerViewClick(Object item) {

    }
}
