package vn.needy.vendor.service;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.text.Html;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

import vn.needy.vendor.R;
import vn.needy.vendor.VendorApplication;
import vn.needy.vendor.domain.Notification;
import vn.needy.vendor.repository.NotificationRepository;
import vn.needy.vendor.repository.local.NotificationDataLocal;

/**
 * Created by lion on 05/01/2018.
 */

public class VendorService extends FirebaseMessagingService{
    public static final String ACTION_NEW_NOTIFICATION = "vn.need.vendor.NEW_NOTIFICATION";

    private NotificationRepository mNotificationRepository;

    public VendorService() {
        super();
        mNotificationRepository = new NotificationRepository(new NotificationDataLocal());
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        Notification notification = new Notification();
        Map<String, String> data = remoteMessage.getData();
        notification.setId(Long.valueOf(data.get("id")));
        notification.setTitle(data.get("title"));
        notification.setHtmlContent(data.get("body"));
        notification.setReferenceGUI(data.get("reference_gui"));
        notification.setCreateTime(data.get("create_time"));

        // Save notification to local
        mNotificationRepository.saveNotificationSync(notification);

        // Check Active Activity
        Activity activity = ((VendorApplication)getApplication()).getActiveActivity();
        if (activity != null && activity.getLocalClassName().equals("screen.main.MainActivity")) {
            // App is Foceground and Active Activity is MainActivity

            // Send broadcast new notification to MainActivity
            Intent intent = new Intent();
            intent.setAction(ACTION_NEW_NOTIFICATION);
            sendBroadcast(intent);
        } else {
            // Active Activity is'n MainActivity or App in Background -> show notification
            Log.e(getClass().getName(), "is Background");

            showNotification(notification);
        }
    }

    private void showNotification(Notification notification) {
        Intent notiIntent;
        PendingIntent pendingIntent = null;
        try {
            notiIntent = new Intent(this, Class.forName(getPackageName() + notification.getReferenceGUI()));
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

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        assert notificationManager != null;
        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }
}
