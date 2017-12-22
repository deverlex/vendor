package vn.needy.vendor.screen.createProduct.childProduct;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vn.needy.vendor.R;
import vn.needy.vendor.databinding.FragmentChildProductBinding;

/**
 * Created by lion on 08/12/2017.
 */

public class ChildProductFragment extends Fragment {


    private ChildProductContract.ViewModel mViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        mViewModel = new ChildProductViewModel();

        FragmentChildProductBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_child_product, container, false);
        binding.setViewModel((ChildProductViewModel) mViewModel);
        return binding.getRoot();
    }
}
