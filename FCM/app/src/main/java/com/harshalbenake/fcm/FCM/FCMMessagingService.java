package com.harshalbenake.fcm.FCM;


import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class FCMMessagingService extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseMsgService";

    /**
     * Called when message is received.
     *
     * @param remoteMessage Object representing the message received from Firebase Cloud Messaging.
     */
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.d(TAG, "From: " + remoteMessage.getFrom());

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }

        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
        System.out.println("sendNotification");
        displayNotification("Message Notification Body");
    }

    /**
     * display Notification
     *
     */
    private void displayNotification(String strMessage) {
        try {
            NotificationCompat.Builder mBuilder =
                    new NotificationCompat.Builder(FCMMessagingService.this, "1000")
                            .setSmallIcon(android.R.drawable.btn_plus)
                            .setContentTitle(strMessage)
                            .setContentText(strMessage)
                            .setAutoCancel(true)
                            .setDefaults(Notification.DEFAULT_SOUND)
                            .setPriority(Notification.PRIORITY_HIGH);
                mBuilder.setStyle(new NotificationCompat.BigTextStyle()
                        .bigText(strMessage));

                NotificationManager mNotificationManager =
                        (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                // allows you to update the displayNotification later on.
                mNotificationManager.notify(1, mBuilder.build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}