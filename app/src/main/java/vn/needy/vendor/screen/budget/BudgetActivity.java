package vn.needy.vendor.screen.budget;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import vn.needy.vendor.R;
import vn.needy.vendor.databinding.ActivityBudgetBinding;
import vn.needy.vendor.screen.BaseActivity;

/**
 * Created by truongpq on 04/12/2017.
 */

public class BudgetActivity extends BaseActivity{

    private BudgetContract.Presenter mPresenter;
    private BudgetContract.ViewModel mViewModel;
    private BudgetSectionsAdapter mSectionsAdapter;
    /*private BudgetRecyclerViewAdapter mAdapter;
    private List<Coin> mList;*/

    @Override
    protected void onCreateActivity(Bundle savedInstanceState) {

        mSectionsAdapter = new BudgetSectionsAdapter(this , getSupportFragmentManager());

        mPresenter = new BudgetPresenter();
        mViewModel = new BudgetViewModel(this , mSectionsAdapter);

        mViewModel.setPresenter(mPresenter);
        mViewModel.onStart();

        ActivityBudgetBinding binding = DataBindingUtil.setContentView(this , R.layout.activity_budget);
        binding.setViewModel((BudgetViewModel) mViewModel);

    }


    public static class Coin extends BaseObservable{

        private String coinName;
        private String coinStatus;
        private String coidDate;
        private String coin;

        public Coin(String coinName, String coinStatus, String coidDate, String coin) {
            this.coinName = coinName;
            this.coinStatus = coinStatus;
            this.coidDate = coidDate;
            this.coin = coin;
        }

        @Bindable
        public String getCoinName() {
            return coinName;
        }

        @Bindable
        public String getCoinStatus() {
            return coinStatus;
        }


        @Bindable
        public String getCoidDate() {
            return coidDate;
        }

        @Bindable
        public String getCoin() {
            return coin;
        }
    }


}
