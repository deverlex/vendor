package vn.needy.vendor.screen.main;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import vn.needy.vendor.R;
import vn.needy.vendor.screen.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreateActivity(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
    }
}
