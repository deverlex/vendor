package vn.needy.vendor.screen.notification;

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
import vn.needy.vendor.databinding.FragmentNotificationBinding;
import vn.needy.vendor.model.Notification;

/**
 * Created by lion on 21/10/2017.
 */

public class NotificationFragment extends Fragment {

    public static NotificationFragment getInstance() {
        return new NotificationFragment();
    }

    private NotificationConstract.ViewModel mViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        List<Notification> notifications = new ArrayList<>();
        NotificationAdapter adapter = new NotificationAdapter(getActivity(), notifications);
        mViewModel = new NotificationViewModel(getActivity(), adapter);
        FragmentNotificationBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_notification, container, false);

        NotificationConstract.Presenter presenter = new NotificationPresenter(mViewModel);
        mViewModel.setPresenter(presenter);

        mViewModel.onStart();

        binding.setViewModel((NotificationViewModel) mViewModel);
//        binding.setMainPage(this);
        return binding.getRoot();
    }
}
