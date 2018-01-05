package vn.needy.vendor.screen.mainPage;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.RadioGroup;

import vn.needy.vendor.BR;
import vn.needy.vendor.R;
import vn.needy.vendor.port.wrapper.CategoryWrapper;
import vn.needy.vendor.database.sharedprf.SharedPrefsApi;
import vn.needy.vendor.database.sharedprf.SharedPrefsKey;
import vn.needy.vendor.screen.createProduct.CreateProductPlActivity;
import vn.needy.vendor.screen.createProduct.CreateProductPnActivity;
import vn.needy.vendor.screen.category.CategoriesActivity;
import vn.needy.vendor.utils.navigator.Navigator;

/**
 * Created by lion on 23/10/2017.
 */

public class MainPageViewModel extends BaseObservable implements MainPageConstract.ViewModel {

    private static final String TAG = MainPageViewModel.class.getName();

    private final Context mContext;
    private final Navigator mNavigator;

    private MainPageConstract.Presenter mPresenter;
    private CategoryWrapper mCategory;

    private SharedPrefsApi mPrefsApi;

    private MainPagerAdapter mPagerAdapter;

    private int mProductType;
    private boolean mIsPlChecked;
    private int mCurrentTab;
    private ViewPager.OnPageChangeListener mOnPageChangeListener;

    public MainPageViewModel(Context context, Navigator navigator,
                             final SharedPrefsApi prefsApi, final CategoryWrapper category, MainPagerAdapter viewPagerAdapter) {
        mContext = context;
        mNavigator = navigator;
        mPagerAdapter = viewPagerAdapter;

        // default of product type is pn - because UI set it is checked.
        mPrefsApi = prefsApi;
        int productType = mPrefsApi.get(SharedPrefsKey.PRODUCT_TYPE_CHOOSE, Integer.class);
        mProductType = productType > 0 ? productType : R.id.price_now;
        // save it into db
        prefsApi.put(SharedPrefsKey.PRODUCT_TYPE_CHOOSE, mProductType);

        mIsPlChecked = mProductType == R.id.price_later;
        if (mIsPlChecked) {
            mCurrentTab = MainPagerAdapter.PRICE_LATER_POSITION;
        } else {
            mCurrentTab = MainPagerAdapter.PRICE_NOW_POSITION;
        }

        mOnPageChangeListener = new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mIsPlChecked = position == MainPagerAdapter.PRICE_LATER_POSITION;
                notifyPropertyChanged(BR.plChecked);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        };

        // when getAsync category from result activity
        if (category != null) {
            mCategory = category;
            // save category to db
            prefsApi.putObject(SharedPrefsKey.CURRENT_CATEGORY, category);
        } else {
            // getAsync category from db
            mCategory = prefsApi.getObject(SharedPrefsKey.CURRENT_CATEGORY, CategoryWrapper.class);
        }
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {

    }

    @Override
    public void setPresenter(MainPageConstract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int id) {
        mProductType = id;
        // Navigation ViewPager
        if (mProductType == R.id.price_now) {
            mCurrentTab = MainPagerAdapter.PRICE_NOW_POSITION;
            notifyPropertyChanged(BR.currentTab);
        } else {
            mCurrentTab = MainPagerAdapter.PRICE_LATER_POSITION;
            notifyPropertyChanged(BR.currentTab);
        }

        // save setting product type into db
        mPrefsApi.put(SharedPrefsKey.PRODUCT_TYPE_CHOOSE, mProductType);
    }

    @Override
    public void onClickAddProduct() {
        if (mProductType == R.id.price_now) {
            mNavigator.startActivity(CreateProductPnActivity.class);
        } else {
            mNavigator.startActivity(CreateProductPlActivity.class);
        }
    }

    @Bindable
    public String getCategoryTitle() {
        if (mCategory != null) {
            return mCategory.getTitle();
        }
        return mContext.getString(R.string.all_category);
    }

    @Bindable
    public MainPagerAdapter getPagerAdapter() {
        return mPagerAdapter;
    }

    @Bindable
    public int getCurrentTab() {
        return mCurrentTab;
    }

    @Bindable
    public boolean isPlChecked() {
        return mIsPlChecked;
    }

    @Bindable
    public ViewPager.OnPageChangeListener getOnPageChangeListener() {
        return mOnPageChangeListener;
    }
}
