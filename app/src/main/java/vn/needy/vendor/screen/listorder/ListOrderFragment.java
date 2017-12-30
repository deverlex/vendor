package vn.needy.vendor.screen.listorder;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import vn.needy.vendor.R;
import vn.needy.vendor.databinding.FragmentListOrderBinding;
import vn.needy.vendor.domain.Order;

/**
 * Created by lion on 21/10/2017.
 */

public class ListOrderFragment extends Fragment {

    public static ListOrderFragment getInstance() {
        return new ListOrderFragment();
    }

    private ListOrderContract.ViewModel mViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        List<Order> orders = new ArrayList<>();
        ListOrderAdapter adapter = new ListOrderAdapter(getActivity(), orders);

        mViewModel = new ListOrderViewModel(getActivity(), adapter);

        FragmentListOrderBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list_order, container, false);

        ListOrderContract.Presenter presenter = new ListOrderPresenter(mViewModel);
        mViewModel.setPresenter(presenter);

        mViewModel.onStart();

        binding.setViewModel((ListOrderViewModel) mViewModel);
//        binding.setMainPage(this);
        return binding.getRoot();
    }
}
