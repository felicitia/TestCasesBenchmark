package androidx.work.impl.background.systemalarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import androidx.work.NetworkType;
import androidx.work.b;
import androidx.work.e;
import androidx.work.impl.b.j;
import java.util.List;

abstract class ConstraintProxy extends BroadcastReceiver {
    private static final String TAG = "ConstraintProxy";

    public static class BatteryChargingProxy extends ConstraintProxy {
        public /* bridge */ /* synthetic */ void onReceive(Context context, Intent intent) {
            ConstraintProxy.super.onReceive(context, intent);
        }
    }

    public static class BatteryNotLowProxy extends ConstraintProxy {
        public /* bridge */ /* synthetic */ void onReceive(Context context, Intent intent) {
            ConstraintProxy.super.onReceive(context, intent);
        }
    }

    public static class NetworkStateProxy extends ConstraintProxy {
        public /* bridge */ /* synthetic */ void onReceive(Context context, Intent intent) {
            ConstraintProxy.super.onReceive(context, intent);
        }
    }

    public static class StorageNotLowProxy extends ConstraintProxy {
        public /* bridge */ /* synthetic */ void onReceive(Context context, Intent intent) {
            ConstraintProxy.super.onReceive(context, intent);
        }
    }

    ConstraintProxy() {
    }

    public void onReceive(Context context, Intent intent) {
        e.b(TAG, String.format("onReceive : %s", new Object[]{intent}), new Throwable[0]);
        context.startService(b.a(context));
    }

    static void updateAll(Context context, List<j> list) {
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        boolean z4 = false;
        for (j jVar : list) {
            b bVar = jVar.j;
            z |= bVar.d();
            z2 |= bVar.b();
            z3 |= bVar.e();
            z4 |= bVar.a() != NetworkType.NOT_REQUIRED;
            if (z && z2 && z3 && z4) {
                break;
            }
        }
        context.sendBroadcast(ConstraintProxyUpdateReceiver.newConstraintProxyUpdateIntent(z, z2, z3, z4));
    }
}
