package vn.needy.vendor.screen.productProfile.attribute;

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
import vn.needy.vendor.model.wrapper.AttributeWrapper;
import vn.needy.vendor.model.wrapper.CategoryWrapper;
import vn.needy.vendor.databinding.FragmentAddAttributesBinding;
import vn.needy.vendor.screen.category.CategoriesActivity;

/**
 * Created by lion on 04/12/2017.
 */

public class AttributeFragment extends Fragment {

    private static final String TAG = AttributeFragment.class.getName();

    public static final AttributeFragment getInstance() {
        return new AttributeFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        CategoryWrapper category;
        Bundle extras = getArguments();
        if (extras != null) {
            category = extras.getParcelable(CategoriesActivity.CATEGORY);
        } else {
            category = new CategoryWrapper();
        }

        List<AttributeWrapper> attributes = new ArrayList<>();
        AttributeAdapter attributeAdapter = new AttributeAdapter(getContext(), attributes);
        AttributeContract.ViewModel viewModel =
                new AttributeViewModel(getContext(), attributeAdapter, category);

        AttributeContract.Presenter presenter = new AttributePresenter(viewModel);
        viewModel.setPresenter(presenter);
        viewModel.onStart();

        // data binding
        FragmentAddAttributesBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_add_attributes, container, false);
        binding.setViewModel((AttributeViewModel) viewModel);
        return binding.getRoot();
    }

    public interface OnCallbackReceived {
        void onUpdateListAttribute(List<AttributeWrapper> attributes);
    }
}
