package vn.needy.vendor.screen.addProduct;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;

import com.zhihu.matisse.Matisse;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import vn.needy.vendor.R;
import vn.needy.vendor.database.model.Image;
import vn.needy.vendor.databinding.ActivityAddProductPnBinding;
import vn.needy.vendor.screen.BaseActivity;
import vn.needy.vendor.screen.ImageAdapter;

/**
 * Created by lion on 08/11/2017.
 */

public class AddProductPnActivity extends BaseActivity {

    public static final String CLASS = AddProductPnActivity.class.getName();

    private AddProductPnContract.ViewModel mViewModel;
    public static final int REQUEST_CODE_CHOOSE = 2682;

    @Override
    protected void onCreateActivity(Bundle savedInstanceState) {
        List<Image> images = new ArrayList<>();
        ImageAdapter imageAdapter = new ImageAdapter(this, images);

        mViewModel = new AddProductPnViewModel(this, imageAdapter);
        AddProductPnContract.Presenter presenter = new AddProductPnPresenter(this, mViewModel);
        mViewModel.setPresenter(presenter);

        ActivityAddProductPnBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_add_product_pn);
        binding.setViewModel((AddProductPnViewModel) mViewModel);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            List<Image> images = new LinkedList<>();
            for (Uri uri : Matisse.obtainResult(data)) {
                images.add(new Image(this, uri.toString()));
            }
            // Update images view
            mViewModel.onSelectedListImages(images);
        }
    }
}
