package vn.needy.vendor.screen.notificationSetting;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vn.needy.vendor.R;
import vn.needy.vendor.databinding.FragmentNotificationSettingBinding;


/**
 * Created by lion on 25/12/2017.
 */

public class NotificationSettingFragment extends Fragment{

    public static NotificationSettingFragment getInstance()
    {
        return new NotificationSettingFragment();
    }

    private NotificationSettingContract.ViewModel mViewModel;
    private NotificationSettingContract.Presenter mPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mViewModel = new NotificationSettingViewModel(getActivity());
        mPresenter = new NotificationSettingPresenter(mViewModel , getContext());

        mViewModel.setPresenter(mPresenter);
        mViewModel.onStart();

        FragmentNotificationSettingBinding binding = DataBindingUtil.inflate(inflater , R.layout.fragment_notification_setting , container , false);
        binding.setViewModel((NotificationSettingViewModel) mViewModel);

        return binding.getRoot();
    }
}
