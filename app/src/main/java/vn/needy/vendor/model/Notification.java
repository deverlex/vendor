package vn.needy.vendor.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by truongpq on 19/12/2017.
 */

public class Notification extends RealmObject {
    @PrimaryKey
    private long mId;
    private String mSenderId;
    private String mTitle;
    private String mHtmlContent;
    private String mCreateTime;
    private String mReferenceGUI;
    private boolean mIsReaded;
    private boolean mIsViewed;

    public Notification() {
        super();
    }

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        this.mId = id;
    }

    public String getSenderId() {
        return mSenderId;
    }

    public void setSenderId(String senderId) {
        this.mSenderId = senderId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public String getHtmlContent() {
        return mHtmlContent;
    }

    public void setHtmlContent(String htmlContent) {
        this.mHtmlContent = htmlContent;
    }

    public String getCreateTime() {
        return mCreateTime;
    }

    public void setCreateTime(String createTime) {
        this.mCreateTime = createTime;
    }

    public String getReferenceGUI() {
        return mReferenceGUI;
    }

    public void setReferenceGUI(String referenceGUI) {
        this.mReferenceGUI = referenceGUI;
    }

    public boolean isReaded() {
        return mIsReaded;
    }

    public void setIsReaded(boolean isReaded) {
        this.mIsReaded = isReaded;
    }

    public boolean isViewed() {
        return mIsViewed;
    }

    public void setIsViewed(boolean isViewed) {
        this.mIsViewed = isViewed;
    }
}
