package vn.needy.vendor.model;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.provider.MediaStore;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.FileNotFoundException;
import java.io.InputStream;

import vn.needy.vendor.utils.Utils;

/**
 * Created by lion on 08/11/2017.
 */

public class Image implements Parcelable {

    public static final Creator<Image> CREATOR = new Creator<Image>() {
        @Override
        public Image createFromParcel(Parcel in) {
            return new Image(in);
        }

        @Override
        public Image[] newArray(int size) {
            return new Image[size];
        }
    };

    @Expose
    @SerializedName("uri")
    private String mUri;

    private Context mContext;

    public Image() {
        super();
    }

    public Image(Context context, String uri) {
        mUri = uri;
        mContext = context;
    }

    protected Image(Parcel in) {
        mUri = in.readString();
    }

    public String getUri() {
        return mUri;
    }

    public void setUri(String uri) {
        mUri = uri;
    }

    // This function using when load image from local
    public String getPath() {
        Cursor cursor = mContext.getContentResolver().query(Uri.parse(mUri),
                null, null, null, null);
        cursor.moveToFirst();
        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        return cursor.getString(idx);
    }

    // This function using when load image from local
    public InputStream getInputStream() throws FileNotFoundException {
        return mContext.getContentResolver().openInputStream(Uri.parse(mUri));
    }

    // This function using when load image from local
    public String getBase64() throws FileNotFoundException {
        return Utils.ImageUtils.convertImageToBase64(getInputStream());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mUri);
    }
}
