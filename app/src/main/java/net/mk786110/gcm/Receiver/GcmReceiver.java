package net.mk786110.gcm.Receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import net.mk786110.gcm.Service.GcmService;


public class GcmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        intent.setClass(context, GcmService.class);
        context.startService(intent);
    }
}
