package vn.needy.vendor.screen.login;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import vn.needy.vendor.R;
import vn.needy.vendor.data.model.Company;
import vn.needy.vendor.data.source.CompanyRepository;
import vn.needy.vendor.data.source.UserRepository;
import vn.needy.vendor.data.source.local.CompanyLocalDataSource;
import vn.needy.vendor.data.source.local.UserLocalDataSource;
import vn.needy.vendor.data.source.local.realm.RealmApi;
import vn.needy.vendor.data.source.local.sharedprf.SharedPrefsImpl;
import vn.needy.vendor.data.source.remote.CompanyRemoteDataSource;
import vn.needy.vendor.data.source.remote.UserRemoteDataSource;
import vn.needy.vendor.data.source.remote.api.error.BaseException;
import vn.needy.vendor.data.source.remote.api.error.SafetyError;
import vn.needy.vendor.data.source.remote.api.service.VendorServiceClient;
import vn.needy.vendor.databinding.ActivityLoginBinding;
import vn.needy.vendor.screen.BaseActivity;
import vn.needy.vendor.screen.main.MainActivity;
import vn.needy.vendor.screen.registerCompany.RegisterCompanyActivity;
import vn.needy.vendor.utils.dialog.DialogManager;
import vn.needy.vendor.utils.navigator.Navigator;

/**
 * Created by lion on 02/10/2017.
 */

public class LoginActivity extends BaseActivity implements LoginViewModel.Gateway {

    private static final String TAG = LoginActivity.class.getName();

    private LoginContract.ViewModel mViewModel;
    private RealmApi mRealmApi;
    private Navigator mNavigator;

    @Override
    protected void onCreateActivity(Bundle savedInstanceState) {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        getWindow().setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.background_login));

        //Clear data
        SharedPrefsImpl.getInstance().clear();
        mRealmApi =new RealmApi();

        mNavigator = new Navigator(this);

        DialogManager dialogManager = new DialogManager(this);

        mViewModel = new LoginViewModel(this, getApplication(), mNavigator, dialogManager);
        UserRepository userRepository =
                new UserRepository(new UserRemoteDataSource(VendorServiceClient.getInstance()),
                        new UserLocalDataSource(mRealmApi));

        LoginContract.Presenter presenter = new LoginPresenter(mViewModel, userRepository);
        mViewModel.setPresenter(presenter);

        ActivityLoginBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_login);
        binding.setViewModel((LoginViewModel) mViewModel);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mViewModel.onStart();
    }

    @Override
    protected void onStop() {
        mViewModel.onStop();
        super.onStop();
    }


    @Override
    public void onGateway() {
        final CompanyRepository companyRepository = new CompanyRepository(
                new CompanyRemoteDataSource(VendorServiceClient.getInstance()),
                new CompanyLocalDataSource(mRealmApi));
        CompositeDisposable compositeDisposable = new CompositeDisposable();
        Disposable disposable = companyRepository.findCompany()
            .subscribeOn(Schedulers.io())
            .doAfterTerminate(new Action() {
                @Override
                public void run() throws Exception {
                    mViewModel.onHideProgressBar();
                }
            }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Company>() {
                @Override
                public void accept(Company company) throws Exception {
                    ///save company and redirect to main
                    companyRepository.saveCompany(company);
                    main();
                }
            }, new SafetyError() {
                @Override
                public void onSafetyError(BaseException error) {
                    // redirect to register company
                    registerCompany();
                }
            });
        compositeDisposable.add(disposable);
    }

    public void main() {
        mNavigator.startActivity(MainActivity.class);
        mNavigator.finishActivity();
    }

    public void registerCompany() {
        mNavigator.startActivity(RegisterCompanyActivity.class);
    }
}
