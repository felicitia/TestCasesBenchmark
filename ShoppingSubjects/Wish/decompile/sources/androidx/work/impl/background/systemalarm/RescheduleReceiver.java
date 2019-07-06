package androidx.work.impl.background.systemalarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class RescheduleReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        context.startService(CommandHandler.createRescheduleIntent(context));
    }
}
