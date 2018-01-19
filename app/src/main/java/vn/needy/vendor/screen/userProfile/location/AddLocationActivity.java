package vn.needy.vendor.screen.userProfile.location;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import vn.needy.vendor.R;
import vn.needy.vendor.databinding.ActivityAddLocationBinding;
import vn.needy.vendor.model.Place;
import vn.needy.vendor.repository.remote.user.context.UserLocationContext;
import vn.needy.vendor.screen.BaseActivity;
import vn.needy.vendor.screen.place.PlaceActivity;
import vn.needy.vendor.utils.navigator.Navigator;

/**
 * Created by truongpq on 19/01/2018.
 */

public class AddLocationActivity extends BaseActivity{

    protected static final int ADDRESS = 1;

    public static final String LOCATION = "_location";
    public static final String LOCATION_POSITION = "_location_position";

    public static final int RC_OK = 1101;
    public static final int RC_NOT_OK = 1102;
    public static final int RC_OK_EDIT = 1103;
    public static final int RC_DELETE = 1104;

    private AddLocationConstant.ViewModel mViewModel;
    private Navigator mNavigator;

    @Override
    protected void onCreateActivity(Bundle savedInstanceState) {
        super.onCreateActivity(savedInstanceState);
        ActivityAddLocationBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_add_location);

        mNavigator = new Navigator(this);

        UserLocationContext locationContext = getIntent().getParcelableExtra(LOCATION);
        int position = getIntent().getIntExtra(LOCATION_POSITION, -1);
        if (locationContext == null) {
            locationContext = new UserLocationContext();
            position = -1;
        }

        mViewModel = new AddLocationViewModel(this, mNavigator, locationContext, position);
        AddLocationConstant.Presenter presenter = new AddLocationPresenter(this, mViewModel);
        mViewModel.setPresenter(presenter);
        mViewModel.onStart();

        binding.setViewModel((AddLocationViewModel) mViewModel);
    }

    @Override
    public void onBackPressed() {
        setResult(RC_NOT_OK);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADDRESS && resultCode == PlaceActivity.RC_OK) {
            Place place = data.getExtras().getParcelable(PlaceActivity.PLACE);
            mViewModel.updateAddress(place);
        }
    }
}
