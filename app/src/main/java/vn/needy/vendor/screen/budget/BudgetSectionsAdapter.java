package vn.needy.vendor.screen.budget;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import vn.needy.vendor.R;
import vn.needy.vendor.screen.budget.historyIncome.BudgetHistoryIncomeFragment;
import vn.needy.vendor.screen.budget.historyRecharge.BudgetHistoryRechargeFragment;
import vn.needy.vendor.screen.budget.historySubtract.BudgetHistorySubtractFragment;

/**
 * Created by minh_dai on 02/01/2018.
 */

public class BudgetSectionsAdapter extends FragmentPagerAdapter{

        private static final int CONTACTS = 0;
        private static final int CALLS = 1;
        private static final int CHATS = 2;

        private final int[] TABS = new int[]{CONTACTS, CALLS ,CHATS};

        private Context mContext;

        public BudgetSectionsAdapter(final Context context, final FragmentManager fm) {
            super(fm);
            mContext = context.getApplicationContext();
        }

        @Override
        public Fragment getItem(int position) {
            switch (TABS[position]) {

                case CONTACTS:
                    return BudgetHistoryIncomeFragment.getInstance();
                case CALLS:
                    return BudgetHistoryRechargeFragment.getInstance();
                case CHATS:
                    return BudgetHistorySubtractFragment.getInstance();
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
                case CONTACTS:
                    return mContext.getResources().getString(R.string.personal_history_all);
                case CALLS:
                    return mContext.getResources().getString(R.string.personal_recharge);
                case CHATS:
                    return mContext.getResources().getString(R.string.personal_coin_subtract);
            }
            return null;
        }

}
