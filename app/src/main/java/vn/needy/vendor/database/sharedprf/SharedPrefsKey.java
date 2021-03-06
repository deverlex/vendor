package vn.needy.vendor.database.sharedprf;

/**
 * Created by lion on 23/09/2017.
 */

public final class SharedPrefsKey {
    public static final String ACCESS_TOKEN = "access_token";
    public static final String REFRESH_TOKEN = "refresh_token";
    public static final String EXPIRES_TOKEN_IN = "expires_in";

    public static final String CURRENT_CATEGORY = "current_category";
    public static final String PRODUCT_TYPE_CHOOSE = "product_type_choose";

    public static final String COMPANY_ID = "company_id";
    public static final String STORE_ID = "store_id";

    public static final String USER_INFO = "user_info";

    private SharedPrefsKey() {
        // No-op
    }
}
