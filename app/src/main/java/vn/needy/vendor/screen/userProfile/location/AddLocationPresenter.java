package vn.needy.vendor.screen.userProfile.location;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.text.TextUtils;

import java.io.IOException;
import java.util.List;

import vn.needy.vendor.R;
import vn.needy.vendor.repository.remote.user.context.UserLocationContext;

/**
 * Created by truongpq on 19/01/2018.
 */

public class AddLocationPresenter implements AddLocationConstant.Presenter{

    private AddLocationConstant.ViewModel mViewModel;
    private Context mContext;

    private Geocoder mGeocoder;

    public AddLocationPresenter(Context context, AddLocationConstant.ViewModel viewModel) {
        mContext = context;
        mViewModel = viewModel;
        mGeocoder = new Geocoder(context);
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public boolean validate(UserLocationContext location) {
        boolean isValidate = true;
        if (TextUtils.isEmpty(location.getTitle())) {
            isValidate = false;
            mViewModel.onInputNameError(R.string.address_name_empty);
        } else if (TextUtils.isEmpty(location.getDescription())) {
            isValidate = false;
            mViewModel.onInputAddressError(R.string.address_empty);
        }
        return isValidate;
    }

    @Override
    public void getLatLng(String address) {
        try {
            List<Address> addresses = mGeocoder.getFromLocationName(address, 1);
            if (addresses != null && addresses.size() > 0) {
                mViewModel.onGetLatLngCompleted(addresses.get(0).getLatitude(), addresses.get(0).getLongitude());
            }
        } catch (IOException e) {
            e.printStackTrace();
            mViewModel.onGetLatLngFailed();
        }
    }
}
