package vn.needy.vendor.screen.saleIndex;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import vn.needy.vendor.R;
import vn.needy.vendor.databinding.ActivitySaleIndexBinding;
import vn.needy.vendor.screen.BaseActivity;

/**
 * Created by lion on 11/01/2018.
 */

public class SaleIndexActivity extends BaseActivity {

    private SaleIndexContract.ViewModel mViewModel;
    private SaleIndexContract.Presenter mPresenter;

    @Override
    protected void onCreateActivity(Bundle savedInstanceState) {

        mViewModel = new SaleIndexViewModel(this);
        mPresenter = new SaleIndexPresenter(mViewModel);

        mViewModel.setPresenter(mPresenter);
        mViewModel.onStart();

        ActivitySaleIndexBinding binding = DataBindingUtil.setContentView(this , R.layout.activity_sale_index);
        binding.setViewModel((SaleIndexViewModel) mViewModel);
    }
}
