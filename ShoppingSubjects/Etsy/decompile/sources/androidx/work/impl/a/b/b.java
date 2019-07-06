package androidx.work.impl.a.b;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import androidx.work.e;
import com.etsy.android.lib.models.ResponseConstants;

@RestrictTo({Scope.LIBRARY_GROUP})
/* compiled from: BatteryNotLowTracker */
public class b extends c<Boolean> {
    public b(Context context) {
        super(context);
    }

    /* renamed from: a */
    public Boolean c() {
        Intent registerReceiver = this.a.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        if (registerReceiver == null) {
            e.e("BatteryNotLowTracker", "getInitialState - null intent received", new Throwable[0]);
            return null;
        }
        int intExtra = registerReceiver.getIntExtra("plugged", 0);
        int intExtra2 = registerReceiver.getIntExtra("status", -1);
        float intExtra3 = ((float) registerReceiver.getIntExtra("level", -1)) / ((float) registerReceiver.getIntExtra(ResponseConstants.SCALE, -1));
        boolean z = true;
        if (intExtra == 0 && intExtra2 != 1 && intExtra3 <= 0.15f) {
            z = false;
        }
        return Boolean.valueOf(z);
    }

    public IntentFilter b() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.BATTERY_OKAY");
        intentFilter.addAction("android.intent.action.BATTERY_LOW");
        return intentFilter;
    }

    public void a(Context context, @NonNull Intent intent) {
        if (intent.getAction() != null) {
            e.b("BatteryNotLowTracker", String.format("Received %s", new Object[]{intent.getAction()}), new Throwable[0]);
            String action = intent.getAction();
            char c = 65535;
            int hashCode = action.hashCode();
            if (hashCode != -1980154005) {
                if (hashCode == 490310653 && action.equals("android.intent.action.BATTERY_LOW")) {
                    c = 1;
                }
            } else if (action.equals("android.intent.action.BATTERY_OKAY")) {
                c = 0;
            }
            switch (c) {
                case 0:
                    a(Boolean.valueOf(true));
                    break;
                case 1:
                    a(Boolean.valueOf(false));
                    break;
            }
        }
    }
}
