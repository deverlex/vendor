package vn.needy.vendor.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.text.Html;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONException;
import org.json.JSONObject;

import vn.needy.vendor.R;

/**
 * Created by lion on 05/01/2018.
 */

public class VendorService extends FirebaseMessagingService {

    private static final String GCM_NOTIFICATION_TITLE = "google.c.a.c_l";
    private static final String GCM_NOTIFICATION_BODY = "gcm.notification.body";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Log.e(getClass().getName(), "onMessageReceived");
    }

    @Override
    public void handleIntent(Intent intent) {
        vn.needy.vendor.domain.Notification notification =new vn.needy.vendor.domain.Notification();
        try {
            JSONObject notificationInfoJson = new JSONObject(intent.getStringExtra(GCM_NOTIFICATION_BODY));
            notification.setTitle(notificationInfoJson.getString("title"));
            notification.setHtmlContent(notificationInfoJson.getString("html_content"));
            notification.setReferenceGUI(notificationInfoJson.getString("refecence_gui"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        assert notificationManager != null;
        notificationManager.notify(0 /* ID of notification */, createNotification(notification));
    }

    private Notification createNotification(vn.needy.vendor.domain.Notification notification) {
        Intent notiIntent;
        PendingIntent pendingIntent = null;
        try {
            notiIntent = new Intent(this, Class.forName(getPackageName() + "." + notification.getReferenceGUI()));
            notiIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, notiIntent,
                    PendingIntent.FLAG_ONE_SHOT);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setAutoCancel(true)
                .setContentTitle(notification.getTitle())
                .setContentText(Html.fromHtml(notification.getHtmlContent()))
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setContentIntent(pendingIntent)
                .setPriority(NotificationCompat.PRIORITY_MAX);

        NotificationCompat.BigTextStyle bigTextStyle = new NotificationCompat.BigTextStyle();
        bigTextStyle.setBigContentTitle(notification.getTitle());
        bigTextStyle.bigText(Html.fromHtml(notification.getHtmlContent()));

        notificationBuilder.setStyle(bigTextStyle);

        return notificationBuilder.build();
    }
}
