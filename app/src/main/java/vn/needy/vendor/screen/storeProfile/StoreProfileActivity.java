package vn.needy.vendor.screen.storeProfile;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.widget.ScrollView;

import vn.needy.vendor.R;
import vn.needy.vendor.databinding.ActivityStoreProfileBinding;
import vn.needy.vendor.port.api.VendorApi;
import vn.needy.vendor.port.service.VendorServiceClient;
import vn.needy.vendor.screen.BaseActivity;
import vn.needy.vendor.widget.WorkaroundMapFragment;

/**
 * Created by lion on 12/12/2017.
 */

public class StoreProfileActivity extends BaseActivity {

    private StoreProfileContract.ViewModel mViewModel;

    private VendorApi mVendorApi;

    @Override
    protected void onCreateActivity(Bundle savedInstanceState) {
        super.onCreateActivity(savedInstanceState);
        ActivityStoreProfileBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_store_profile);

        mVendorApi = VendorServiceClient.getInstance();

        mViewModel = new StoreProfileViewModel(this);
        StoreProfilePresenter presenter = new StoreProfilePresenter(mViewModel, mVendorApi);
        mViewModel.setPresenter(presenter);

        mViewModel.onStart();
        binding.setViewModel((StoreProfileViewModel) mViewModel);
    }


}
