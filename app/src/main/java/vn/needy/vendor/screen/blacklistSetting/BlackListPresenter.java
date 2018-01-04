package vn.needy.vendor.screen.blacklistSetting;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import vn.needy.vendor.model.BlockUser;

/**
 * Created by lion on 25/12/2017.
 */

public class BlackListPresenter implements BlacklistSettingContract.Presenter{

    private BlacklistSettingContract.ViewModel mViewModel;
    private List<BlockUser> mListUser;
    private Context mContext;
    private int mPosotion;

    public BlackListPresenter(BlacklistSettingContract.ViewModel mViewModel, Context mContext) {
        this.mViewModel = mViewModel;
        this.mContext = mContext;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void getUserList() {
        mListUser = new ArrayList<>();
        mListUser.add(new BlockUser("minh " , "012345665" ));
        mListUser.add(new BlockUser("minh " , "012345665" ));
        mListUser.add(new BlockUser("minh " , "012345665" ));
        mListUser.add(new BlockUser("minh " , "012345665" ));
        mListUser.add(new BlockUser("minh " , "012345665" ));
        mListUser.add(new BlockUser("minh " , "012345665" ));
        mListUser.add(new BlockUser("minh " , "012345665" ));
        mViewModel.setListBlockUser(mListUser);
    }
}
