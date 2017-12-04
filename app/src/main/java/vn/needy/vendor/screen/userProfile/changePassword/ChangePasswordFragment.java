package vn.needy.vendor.screen.userProfile.changePassword;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vn.needy.vendor.R;
import vn.needy.vendor.databinding.FragmentChangePasswordBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChangePasswordFragment extends Fragment {

    public static ChangePasswordFragment newInstance() {
        
        Bundle args = new Bundle();
        
        ChangePasswordFragment fragment = new ChangePasswordFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private ChangePasswordConstract.ViewModel mViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mViewModel = new ChangePasswordViewModel(getActivity());

        FragmentChangePasswordBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_change_password, container, false);
        binding.setViewModel((ChangePasswordViewModel) mViewModel);
        // Inflate the layout for this fragment
        return binding.getRoot();
    }

}
