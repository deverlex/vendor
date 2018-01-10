package vn.needy.vendor.screen.budget.incomeHistory;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import vn.needy.vendor.screen.budget.BudgetActivity;

/**
 * Created by minh_dai on 02/01/2018.
 */

public class BudgetHistoryIncomePresenter implements BudgetHistoryIncomeContract.Presenter {

    private Context mContext;
    private BudgetHistoryIncomeContract.ViewModel mViewModel;

    public BudgetHistoryIncomePresenter(Context mContext, BudgetHistoryIncomeContract.ViewModel mViewModel) {
        this.mContext = mContext;
        this.mViewModel = mViewModel;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void setListCoin(){

        List<BudgetActivity.Coin> mList;
        BudgetActivity.Coin cargo = new BudgetActivity.Coin("Set 10 đôi tất Uni cổ thấp loại đẹp"
                , "Đã hoàn thành" ,"30-12-2018 14:02"
                ,  "1.300.000 VNĐ");

        mList = new ArrayList<>();
        mList.add(cargo);
        mList.add(cargo);
        mList.add(cargo);
        mList.add(cargo);
        mList.add(cargo);
        mList.add(cargo);

        mViewModel.getListCoin(mList);
    }
}
