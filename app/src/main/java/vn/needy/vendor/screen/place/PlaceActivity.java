package vn.needy.vendor.screen.place;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

import java.util.ArrayList;
import java.util.List;

import vn.needy.vendor.R;
import vn.needy.vendor.databinding.ActivityPlaceBinding;
import vn.needy.vendor.model.Place;
import vn.needy.vendor.screen.BaseActivity;
import vn.needy.vendor.utils.navigator.Navigator;

/**
 * Created by truongpq on 12/01/2018.
 */

public class PlaceActivity extends BaseActivity {

    public static final int RC_OK = 1101;
    public static final int RC_NOT_OK = 1102;

    public static final String PLACE = "_place";

    private PlaceConstant.ViewModel mViewModel;

    private GeoDataClient mGeoDataClient;

    @Override
    protected void onCreateActivity(Bundle savedInstanceState) {
        super.onCreateActivity(savedInstanceState);
        ActivityPlaceBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_place);
        List<Place> places = new ArrayList<>();
        mGeoDataClient = Places.getGeoDataClient(this, null);

        LatLngBounds BOUNDS_VN = new LatLngBounds(
                new LatLng(8.383333, 102.216667), new LatLng(23.666667, 109.466667));

        AutocompleteFilter filter = new AutocompleteFilter.Builder()
                .setCountry("VN") //  This should be a ISO 3166-1 Alpha-2 country code
                .build();

        PlacesAdapter placesAdapter = new PlacesAdapter(this, places, mGeoDataClient, BOUNDS_VN, filter);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        Navigator navigator = new Navigator(this);

        mViewModel = new PlaceViewModel(this, navigator, placesAdapter, mapFragment);
        PlaceConstant.Presenter presenter = new PlacePresenter(this, mViewModel);
        mViewModel.setPresenter(presenter);

        mViewModel.onStart();

        binding.setViewModel((PlaceViewModel) mViewModel);
    }

    @Override
    public void onBackPressed() {
        setResult(RC_NOT_OK);
        finish();
    }
}
