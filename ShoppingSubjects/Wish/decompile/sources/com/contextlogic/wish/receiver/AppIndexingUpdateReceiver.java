package com.contextlogic.wish.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.contextlogic.wish.api.service.AppIndexingUpdateService;

public class AppIndexingUpdateReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        if (intent != null && "com.google.firebase.appindexing.UPDATE_INDEX".equals(intent.getAction())) {
            AppIndexingUpdateService.enqueueWork(context);
        }
    }
}
