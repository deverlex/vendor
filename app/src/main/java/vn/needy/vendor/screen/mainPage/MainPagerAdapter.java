package vn.needy.vendor.screen.mainPage;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import vn.needy.vendor.screen.mainPage.priceLater.MainPagePlFragment;
import vn.needy.vendor.screen.mainPage.priceNow.MainPagePnFragment;

/**
 * Created by truongpq on 02/01/2018.
 */

public class MainPagerAdapter extends FragmentPagerAdapter {
    private static final int NUM_PAGES = 2;

    public static final int PRICE_NOW_POSITION = 0;
    public static final int PRICE_LATER_POSITION = 1;

    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: return MainPagePnFragment.newInstance();
            case 1: return MainPagePlFragment.newInstance();
            default: return null;
        }
    }

    @Override
    public int getCount() {
        return NUM_PAGES;
    }
}
