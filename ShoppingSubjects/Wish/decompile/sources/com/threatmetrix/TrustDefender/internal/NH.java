package com.threatmetrix.TrustDefender.internal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.threatmetrix.TrustDefender.TrustDefender;
import java.util.Timer;
import java.util.TimerTask;

public final class NH extends BroadcastReceiver {

    /* renamed from: int reason: not valid java name */
    TrustDefender f458int;

    public NH(TrustDefender trustDefender) {
        this.f458int = trustDefender;
    }

    public final void onReceive(Context context, Intent intent) {
        if ("android.intent.action.SCREEN_OFF".equals(intent.getAction())) {
            TrustDefender trustDefender = this.f458int;
            trustDefender.f23int = false;
            String str = TrustDefender.f16if;
            StringBuilder sb = new StringBuilder("Screen is off, any future profiling will be blocked after ");
            sb.append(trustDefender.f24new);
            sb.append(" milliseconds.");
            TL.m338new(str, sb.toString());
            if (trustDefender.f19do != null) {
                trustDefender.f19do.cancel();
            }
            trustDefender.f19do = new Timer();
            trustDefender.f19do.schedule(new TimerTask() {
                public final void run() {
                    synchronized (this) {
                        if (!TrustDefender.this.f23int) {
                            TrustDefender.this.f647goto = false;
                        }
                    }
                }
            }, (long) trustDefender.f24new);
        } else if ("android.intent.action.SCREEN_ON".equals(intent.getAction())) {
            TrustDefender trustDefender2 = this.f458int;
            synchronized (trustDefender2) {
                trustDefender2.f23int = true;
                trustDefender2.f647goto = true;
                if (trustDefender2.f19do != null) {
                    trustDefender2.f19do.cancel();
                }
                TL.m338new(TrustDefender.f16if, "Screen is on profiling is unblocked.");
            }
        }
    }
}
