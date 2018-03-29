package vn.needy.vendor.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import java.net.URL;

/**
 * Created by minh_dai on 29/12/2017.
 */

public class BlockUser extends BaseObservable{

    private String nameUser;
    private String numberUser;
    private URL avataUser;

    public BlockUser(String nameUser, String numberUser) {
        this.nameUser = nameUser;
        this.numberUser = numberUser;
    }

    @Bindable
    public String getNameUser() {
        return nameUser;
    }

    @Bindable
    public String getNumberUser() {
        return numberUser;
    }

    @Bindable
    public URL getAvataUser() {
        return avataUser;
    }
}
