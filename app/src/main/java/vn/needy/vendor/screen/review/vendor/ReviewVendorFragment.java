package vn.needy.vendor.screen.review.vendor;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vn.needy.vendor.R;
import vn.needy.vendor.databinding.FragmentReviewVendorBinding;
import vn.needy.vendor.screen.review.ReviewViewModel;
import vn.needy.vendor.utils.navigator.Navigator;

/**
 * Created by lion on 03/01/2018.
 */

public class ReviewVendorFragment extends Fragment {

    public static ReviewVendorFragment getInstance()
    {
        return new ReviewVendorFragment();
    }

    private ReviewVendorContract.Presenter mPresenter;
    private ReviewVendorContract.ViewModel mViewModel;
    private Navigator mNavigator;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mPresenter = new ReviewVendorPresenter();
        mNavigator = new Navigator(this);

        mViewModel = new ReviewVendorViewModel(getActivity() , mNavigator);

        mViewModel.setPresenter(mPresenter);
        mViewModel.onStart();
        FragmentReviewVendorBinding binding = DataBindingUtil.inflate(inflater , R.layout.fragment_review_vendor
                , container , false);

        binding.setViewModel((ReviewVendorViewModel) mViewModel);
        return binding.getRoot();
    }
}

