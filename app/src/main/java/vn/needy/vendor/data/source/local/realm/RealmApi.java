package vn.needy.vendor.data.source.local.realm;

import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.BiConsumer;
import io.realm.Realm;

/**
 * Created by lion on 25/09/2017.
 */

public class RealmApi {

    private static final String TAG = RealmApi.class.getName();

    private Realm mRealm;

    public RealmApi() {
        mRealm = Realm.getDefaultInstance();
    }

    public <T> Observable<T> realmTransactionAsync(final BiConsumer<ObservableEmitter<? super T>, Realm> action) {
        Observable<T> observable = Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(@NonNull final ObservableEmitter<T> observable) {
                mRealm.executeTransactionAsync(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        try {
                            action.accept(observable, realm);
                        } catch (Exception e) {
                            observable.onError(e);
                        }
                    }
                }, new Realm.Transaction.OnSuccess() {
                    @Override
                    public void onSuccess() {
                        observable.onComplete();
                    }
                }, new Realm.Transaction.OnError() {
                    @Override
                    public void onError(Throwable error) {
                        observable.onError(error);
                    }
                });
            }
        });
        //observable.subscribe();
        return observable;
    }

    /**
     * USE THIS METHOD FOR GET
     */
    public <T> Observable<T> realmGet(final BiConsumer<ObservableEmitter<? super T>, Realm> action) {
        return Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<T> observable) throws Exception {
                action.accept(observable, mRealm);
                observable.onComplete();
            }
        });
    }

    public void closeRealmOnMainThread() {
        if (mRealm != null && !mRealm.isClosed()) {
            mRealm.close();
            mRealm = null;
        }
    }
}
