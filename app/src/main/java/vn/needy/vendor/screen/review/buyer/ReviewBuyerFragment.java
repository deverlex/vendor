package vn.needy.vendor.screen.review.buyer;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vn.needy.vendor.R;
import vn.needy.vendor.databinding.FragmentReviewBuyerBinding;

/**
 * Created by lion on 03/01/2018.
 */

public class ReviewBuyerFragment extends Fragment {

    public static ReviewBuyerFragment getInstance() {
        return new ReviewBuyerFragment();
    }

    private ReviewBuyerContract.Presenter mPresenter;
    private ReviewBuyerContract.ViewModel mViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mPresenter = new ReviewBuyerPresenter();
        mViewModel = new ReviewBuyerViewModel(getActivity());

        mViewModel.setPresenter(mPresenter);
        mViewModel.onStart();

        FragmentReviewBuyerBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_review_buyer
                , container, false);

        binding.setViewModel((ReviewBuyerViewModel) mViewModel);

        return binding.getRoot();
    }
}
