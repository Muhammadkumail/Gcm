package net.mk786110.gcm.Service;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;

import net.mk786110.gcm.MainActivity;
import net.mk786110.gcm.Mynotification;
import net.mk786110.gcm.R;


public class GcmService extends IntentService {

    private NotificationManager mNotificationManager;
    NotificationCompat.Builder builder;

    public GcmService() {
        super("GcmService");
    }
    @Override
    protected void onHandleIntent(Intent intent) {

        Bundle mBundle = intent.getExtras();
        String strTtile = mBundle.getString("title");
        String strMessage = mBundle.getString("m");
        String strnotificaton_id = mBundle.getString("notification_id");
        int NOTIFICATION_ID = Integer.parseInt(strnotificaton_id);
        sendNotification(strMessage, strTtile, NOTIFICATION_ID);
    }

    private void sendNotification(String msg, String title, int nofication_id) {


        Mynotification mnotification = new Mynotification();
        mnotification.setMessage(msg);
        mnotification.setTitle(title);

        Intent mintent = new Intent(this, MainActivity.class);
       // mintent.putExtra("mynotification", mnotification);
        mintent.setAction(Long.toString(System.currentTimeMillis()));

        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        mNotificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, mintent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(msg))
                .setSound(uri).setContentText(msg)
                .setAutoCancel(true);
        mBuilder.setContentIntent(contentIntent);
        mNotificationManager.notify(nofication_id, mBuilder.build());

    }
}
