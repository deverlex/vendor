package vn.needy.vendor.screen.addProduct.addAttribute;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import vn.needy.vendor.R;
import vn.needy.vendor.database.model.Attribute;
import vn.needy.vendor.database.model.Category;
import vn.needy.vendor.databinding.FragmentAddAttributesBinding;
import vn.needy.vendor.screen.addProduct.AddProductPnActivity;
import vn.needy.vendor.screen.category.CategoriesActivity;

/**
 * Created by lion on 04/12/2017.
 */

public class AddAttributeFragment extends Fragment {

    private static final String TAG = AddAttributeFragment.class.getName();

    public static final AddAttributeFragment getInstance() {
        return new AddAttributeFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        Category category;
        Bundle extras = getArguments();
        if (extras != null) {
            category = extras.getParcelable(CategoriesActivity.CATEGORY);
        } else {
            category = new Category();
        }

        List<Attribute> attributes = new ArrayList<>();
        AttributeAdapter attributeAdapter = new AttributeAdapter(getContext(), attributes);
        AddAttributeContract.ViewModel viewModel =
                new AddAttributeViewModel(getContext(), attributeAdapter, category);

        AddAttributeContract.Presenter presenter = new AddAttributePresenter(viewModel);
        viewModel.setPresenter(presenter);
        viewModel.onStart();

        // data binding
        FragmentAddAttributesBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_add_attributes, container, false);
        binding.setViewModel((AddAttributeViewModel) viewModel);
        return binding.getRoot();
    }

    public interface OnCallbackReceived {
        void onUpdateListAttribute(List<Attribute> attributes);
    }
}
