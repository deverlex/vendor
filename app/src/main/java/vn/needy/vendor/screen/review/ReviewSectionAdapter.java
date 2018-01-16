package vn.needy.vendor.screen.review;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import vn.needy.vendor.R;
import vn.needy.vendor.screen.review.buyer.ReviewBuyerFragment;
import vn.needy.vendor.screen.review.vendor.ReviewVendorFragment;


/**
 * Created by minh_dai on 03/01/2018.
 */

public class ReviewSectionAdapter extends FragmentPagerAdapter {

    private static final int mReviewBuyer = 0;
    private static final int mReviewVendor = 1;

    private final int[] TABS = new int[]{mReviewBuyer, mReviewVendor};

    private Context mContext;

    public ReviewSectionAdapter(final Context context, final FragmentManager fm) {
        super(fm);
        mContext = context.getApplicationContext();
    }

    @Override
    public Fragment getItem(int position) {
        switch (TABS[position]) {
            case mReviewBuyer:
                return ReviewBuyerFragment.getInstance();
            case mReviewVendor:
                return ReviewVendorFragment.getInstance();
        }
        return null;
    }

    @Override
    public int getCount() {
        return TABS.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (TABS[position]) {
            case mReviewBuyer:
                return mContext.getResources().getString(R.string.review_buyer);
            case mReviewVendor:
                return mContext.getResources().getString(R.string.review_vendor);
        }
        return null;
    }
}