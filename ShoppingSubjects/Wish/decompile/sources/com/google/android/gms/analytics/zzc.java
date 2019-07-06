package com.google.android.gms.analytics;

import android.content.BroadcastReceiver.PendingResult;

final class zzc implements Runnable {
    private final /* synthetic */ PendingResult zzqu;

    zzc(CampaignTrackingReceiver campaignTrackingReceiver, PendingResult pendingResult) {
        this.zzqu = pendingResult;
    }

    public final void run() {
        if (this.zzqu != null) {
            this.zzqu.finish();
        }
    }
}
