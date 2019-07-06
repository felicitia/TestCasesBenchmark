package com.salesforce.marketingcloud.a;

import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.annotation.Size;
import android.support.annotation.VisibleForTesting;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import com.salesforce.marketingcloud.MCReceiver;
import com.salesforce.marketingcloud.a.a.C0160a;
import com.salesforce.marketingcloud.b.c;
import com.salesforce.marketingcloud.d.h;
import com.salesforce.marketingcloud.e.f;
import com.salesforce.marketingcloud.e.g;
import com.salesforce.marketingcloud.i;
import com.salesforce.marketingcloud.j;
import java.util.Date;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

@RestrictTo({Scope.LIBRARY})
public class b extends i implements com.salesforce.marketingcloud.b.b {
    /* access modifiers changed from: private */
    public static final String b = j.a(b.class);
    @VisibleForTesting
    BroadcastReceiver a;
    private final Map<C0160a, a> c = new HashMap();
    private final c d;
    private Application e;
    private h f;
    private SharedPreferences g;

    /* renamed from: com.salesforce.marketingcloud.a.b$1 reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a = new int[com.salesforce.marketingcloud.b.a.values().length];

        static {
            try {
                a[com.salesforce.marketingcloud.b.a.BEHAVIOR_DEVICE_BOOT_COMPLETE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    public interface a {
        void a(@NonNull C0160a aVar);
    }

    /* renamed from: com.salesforce.marketingcloud.a.b$b reason: collision with other inner class name */
    class C0161b extends BroadcastReceiver {
        C0161b() {
        }

        public void onReceive(Context context, Intent intent) {
            C0160a[] values;
            String a2;
            String str;
            long currentTimeMillis = System.currentTimeMillis();
            if (intent == null) {
                a2 = b.b;
                str = "Received null intent";
            } else {
                String action = intent.getAction();
                if (action == null) {
                    a2 = b.b;
                    str = "Received null action";
                } else {
                    Bundle extras = intent.getExtras();
                    if (extras == null) {
                        a2 = b.b;
                        str = "Intent had no extras";
                    } else {
                        char c = 65535;
                        if (action.hashCode() == -1436687111 && action.equals("com.salesforce.marketingcloud.ACTION_ALARM_WAKE_EVENT")) {
                            c = 0;
                        }
                        if (c != 0) {
                            j.b(b.b, "Received unknown action: %s", action);
                            return;
                        }
                        String string = extras.getString(MCReceiver.a, null);
                        if (string != null) {
                            j.a(b.b, "ACTION_ALARM_WAKE_EVENT had extra: %s", string);
                            try {
                                C0160a valueOf = C0160a.valueOf(string);
                                b.this.b(valueOf);
                                if (valueOf.b().a()) {
                                    for (C0160a aVar : C0160a.values()) {
                                        if (aVar.b().a() && b.this.a(aVar, currentTimeMillis)) {
                                            b.this.c(aVar);
                                            b.this.b(aVar);
                                        }
                                    }
                                }
                            } catch (IllegalArgumentException unused) {
                                j.d(b.b, "Woke for an unknown alarm: %s", string);
                            }
                        }
                        return;
                    }
                }
            }
            j.a(a2, str, new Object[0]);
        }
    }

    @RestrictTo({Scope.LIBRARY})
    public b(@NonNull Application application, @NonNull h hVar, @NonNull c cVar) {
        this.e = application;
        this.f = hVar;
        this.d = (c) f.a(cVar, "BehaviorManager is null");
        this.g = hVar.e();
    }

    @NonNull
    private static PendingIntent a(@NonNull Context context, @Nullable String str, @NonNull Integer num) {
        return PendingIntent.getBroadcast(context, num.intValue(), MCReceiver.a(context, str), 134217728);
    }

    private void a(long j) {
        C0160a[] values;
        for (C0160a aVar : C0160a.values()) {
            a b2 = aVar.b();
            long j2 = this.g.getLong(b2.f(), 0);
            if (j2 > 0) {
                if (a(aVar, j)) {
                    a(this.e, aVar, this.g.getLong(b2.b(), b2.c()), j2);
                } else {
                    b(aVar);
                }
            }
        }
    }

    private void a(@NonNull C0160a aVar, @IntRange(from = 0) long j, @IntRange(from = 0, to = 86400000) long j2) {
        j.b(b, "Setting the %s Alarm Flag ...", aVar.name());
        this.g.edit().putLong(aVar.b().f(), j).putLong(aVar.b().b(), j2).apply();
    }

    private boolean a(@NonNull C0160a aVar, boolean z) {
        if (!aVar.a(this.f)) {
            j.b(b, "shouldCreateAlarm() for %s Alarm was FALSE.  Aborting alarm creation.", aVar.name());
            return false;
        }
        long currentTimeMillis = System.currentTimeMillis();
        long a2 = a(aVar);
        if (!a(aVar, currentTimeMillis)) {
            j.b(b, "No pending %s Alarm. Creating one ...", aVar.name());
            a(aVar, currentTimeMillis, a2);
            a(this.e, aVar, z ? 1000 : a2, currentTimeMillis);
            return true;
        } else if (z) {
            return false;
        } else {
            j.b(b, "%s Send Pending ... will send at %s", aVar.name(), g.a(new Date(this.f.e().getLong(aVar.b().f(), 0) + a2)));
            return false;
        }
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public final long a(@NonNull C0160a aVar) {
        long j = this.g.getLong(aVar.b().b(), 0);
        long c2 = j == 0 ? aVar.b().c() : (long) (((double) j) * aVar.b().d());
        if (c2 <= aVar.b().e()) {
            return c2;
        }
        long e2 = aVar.b().e();
        j.b(b, "%s MAX INTERVAL exceeded. Setting interval to %s milliseconds.", aVar.name(), Long.valueOf(e2));
        return e2;
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public void a(@NonNull Context context, @NonNull C0160a aVar, @IntRange(from = 0, to = 86400000) long j, @IntRange(from = 0) long j2) {
        PendingIntent a2 = a(context, aVar.name(), Integer.valueOf(aVar.b().g()));
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(NotificationCompat.CATEGORY_ALARM);
        long j3 = j2 + j;
        String a3 = g.a(new Date(j3));
        try {
            if (VERSION.SDK_INT >= 19) {
                alarmManager.setExact(0, j3, a2);
            } else {
                alarmManager.set(0, j3, a2);
            }
            j.a(b, "%s Alarm scheduled to wake at %s.", aVar.name(), a3);
        } catch (Exception e2) {
            j.b(b, e2, "Failed to schedule alarm %s for %s", aVar.name(), a3);
        }
    }

    /* access modifiers changed from: protected */
    public final void a(com.salesforce.marketingcloud.InitializationStatus.a aVar) {
        this.d.a((com.salesforce.marketingcloud.b.b) this, EnumSet.of(com.salesforce.marketingcloud.b.a.BEHAVIOR_DEVICE_BOOT_COMPLETE));
        this.a = new C0161b();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.salesforce.marketingcloud.ACTION_ALARM_WAKE_EVENT");
        LocalBroadcastManager.getInstance(this.e).registerReceiver(this.a, intentFilter);
    }

    @RestrictTo({Scope.LIBRARY})
    public void a(@NonNull a aVar, @NonNull C0160a... aVarArr) {
        synchronized (this.c) {
            for (C0160a put : aVarArr) {
                this.c.put(put, aVar);
            }
        }
    }

    public final void a(com.salesforce.marketingcloud.b.a aVar, @NonNull Bundle bundle) {
        if (AnonymousClass1.a[aVar.ordinal()] == 1) {
            a(bundle.getLong("timestamp"));
        }
    }

    public final void a(boolean z) {
        if (z) {
            c(C0160a.values());
        }
        if (this.e != null) {
            LocalBroadcastManager.getInstance(this.e).unregisterReceiver(this.a);
        }
        this.d.a((com.salesforce.marketingcloud.b.b) this);
    }

    @RestrictTo({Scope.LIBRARY})
    public void a(@NonNull C0160a... aVarArr) {
        synchronized (this.c) {
            for (C0160a remove : aVarArr) {
                this.c.remove(remove);
            }
        }
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public final boolean a(@NonNull C0160a aVar, @IntRange(from = 0) long j) {
        return this.g.getLong(aVar.b().f(), 0) > j - this.g.getLong(aVar.b().b(), 0);
    }

    @NonNull
    public final String b() {
        return "AlarmScheduler";
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public void b(C0160a aVar) {
        e(aVar);
        a aVar2 = (a) this.c.get(aVar);
        if (aVar2 != null) {
            aVar2.a(aVar);
        }
    }

    @RestrictTo({Scope.LIBRARY})
    public void b(@Size(min = 1) @NonNull C0160a... aVarArr) {
        for (C0160a a2 : aVarArr) {
            a(a2, false);
        }
    }

    @RestrictTo({Scope.LIBRARY})
    public void c(@Size(min = 1) @NonNull C0160a... aVarArr) {
        for (C0160a aVar : aVarArr) {
            d(aVar);
            e(aVar);
            try {
                ((AlarmManager) this.e.getSystemService(NotificationCompat.CATEGORY_ALARM)).cancel(a((Context) this.e, aVar.name(), Integer.valueOf(aVar.b().g())));
                j.b(b, "Reset %s alarm.", aVar.name());
            } catch (Exception e2) {
                j.b(b, e2, "Could not cancel %s alarm.", aVar.name());
            }
        }
    }

    @RestrictTo({Scope.LIBRARY})
    public void d(@Size(min = 1) @NonNull C0160a... aVarArr) {
        for (C0160a aVar : aVarArr) {
            j.b(b, "Resetting %s Alarm Interval.", aVar.name());
            this.g.edit().putLong(aVar.b().b(), 0).apply();
        }
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public void e(@Size(min = 1) @NonNull C0160a... aVarArr) {
        for (C0160a aVar : aVarArr) {
            j.b(b, "Resetting %s Alarm Active Flag to FALSE", aVar.name());
            this.g.edit().putLong(aVar.b().f(), 0).apply();
        }
    }
}
