package androidx.work.impl.a.b;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import androidx.work.e;

@RestrictTo({Scope.LIBRARY_GROUP})
/* compiled from: StorageNotLowTracker */
public class f extends c<Boolean> {
    public f(Context context) {
        super(context);
    }

    /* renamed from: a */
    public Boolean c() {
        Intent registerReceiver = this.a.registerReceiver(null, b());
        if (registerReceiver == null || registerReceiver.getAction() == null) {
            return Boolean.valueOf(true);
        }
        String action = registerReceiver.getAction();
        char c = 65535;
        int hashCode = action.hashCode();
        if (hashCode != -1181163412) {
            if (hashCode == -730838620 && action.equals("android.intent.action.DEVICE_STORAGE_OK")) {
                c = 0;
            }
        } else if (action.equals("android.intent.action.DEVICE_STORAGE_LOW")) {
            c = 1;
        }
        switch (c) {
            case 0:
                return Boolean.valueOf(true);
            case 1:
                return Boolean.valueOf(false);
            default:
                return null;
        }
    }

    public IntentFilter b() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.DEVICE_STORAGE_OK");
        intentFilter.addAction("android.intent.action.DEVICE_STORAGE_LOW");
        return intentFilter;
    }

    public void a(Context context, @NonNull Intent intent) {
        if (intent.getAction() != null) {
            e.b("StorageNotLowTracker", String.format("Received %s", new Object[]{intent.getAction()}), new Throwable[0]);
            String action = intent.getAction();
            char c = 65535;
            int hashCode = action.hashCode();
            if (hashCode != -1181163412) {
                if (hashCode == -730838620 && action.equals("android.intent.action.DEVICE_STORAGE_OK")) {
                    c = 0;
                }
            } else if (action.equals("android.intent.action.DEVICE_STORAGE_LOW")) {
                c = 1;
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
