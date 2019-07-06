package com.google.android.gms.internal.measurement;

import android.content.BroadcastReceiver.PendingResult;
import android.content.Context;
import android.content.Intent;

public interface bo {
    PendingResult doGoAsync();

    void doStartService(Context context, Intent intent);
}
