package vn.needy.vendor.screen.budget.subtractHistory;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import vn.needy.vendor.screen.budget.BudgetActivity;


/**
 * Created by minh_dai on 02/01/2018.
 */

public class BudgetHistorySubtractPresenter implements BudgetHistorySubtractContract.Presenter {

    private Context mContext;
    private BudgetHistorySubtractContract.ViewModel mViewModel;

    public BudgetHistorySubtractPresenter(Context mContext, BudgetHistorySubtractContract.ViewModel viewModel) {
        this.mContext = mContext;
        this.mViewModel = viewModel;
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
                ,  "+11");

        mList = new ArrayList<>();

        mViewModel.getListCoin(mList);
    }
}
