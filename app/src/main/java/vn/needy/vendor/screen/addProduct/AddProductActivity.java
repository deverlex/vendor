package vn.needy.vendor.screen.addProduct;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.zhihu.matisse.Matisse;

import java.util.ArrayList;
import java.util.List;

import vn.needy.vendor.R;
import vn.needy.vendor.data.model.Image;
import vn.needy.vendor.databinding.ActivityAddProductBinding;
import vn.needy.vendor.screen.BaseActivity;

/**
 * Created by lion on 08/11/2017.
 */

public class AddProductActivity extends BaseActivity {

    private static final String TAG = AddProductActivity.class.getName();

    private AddProductContract.ViewModel mViewModel;
    public static final int REQUEST_CODE_CHOOSE = 2682;
    private List<Image> mImages;

    @Override
    protected void onCreateActivity(Bundle savedInstanceState) {

        mImages = new ArrayList<>();
        ImageAdapter imageAdapter = new ImageAdapter(this, mImages);
        mViewModel = new AddProductViewModel(this, imageAdapter);

        ActivityAddProductBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_add_product);
        binding.setViewModel((AddProductViewModel) mViewModel);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            for (Uri uri : Matisse.obtainResult(data)) {
                mImages.add(new Image(uri.getPath()));
            }
            // Update images view
            mViewModel.onSelectedListImages(mImages);
        }
    }
}
