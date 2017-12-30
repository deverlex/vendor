package vn.needy.vendor.repository.remote.product.respone;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import vn.needy.vendor.port.wrapper.ProductPnWrapper;

/**
 * Created by truongpq on 27/12/2017.
 */

public class ProductPnInfoResp {
    @SerializedName("products")
    @Expose
    private List<ProductPnWrapper> products;

    public List<ProductPnWrapper> getProducts() {
        return products;
    }

    public void setProducts(List<ProductPnWrapper> products) {
        this.products = products;
    }
}
