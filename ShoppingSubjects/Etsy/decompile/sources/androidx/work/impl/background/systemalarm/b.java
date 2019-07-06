package androidx.work.impl.background.systemalarm;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.annotation.WorkerThread;
import androidx.work.e;
import androidx.work.impl.a;
import androidx.work.impl.b.j;
import java.util.HashMap;
import java.util.Map;

@RestrictTo({Scope.LIBRARY_GROUP})
/* compiled from: CommandHandler */
public class b implements a {
    private final Context a;
    private final Map<String, a> b = new HashMap();
    private final Object c = new Object();

    static Intent a(@NonNull Context context, @NonNull String str) {
        Intent intent = new Intent(context, SystemAlarmService.class);
        intent.setAction("ACTION_SCHEDULE_WORK");
        intent.putExtra("KEY_WORKSPEC_ID", str);
        return intent;
    }

    static Intent b(@NonNull Context context, @NonNull String str) {
        Intent intent = new Intent(context, SystemAlarmService.class);
        intent.setAction("ACTION_DELAY_MET");
        intent.putExtra("KEY_WORKSPEC_ID", str);
        return intent;
    }

    static Intent c(@NonNull Context context, @NonNull String str) {
        Intent intent = new Intent(context, SystemAlarmService.class);
        intent.setAction("ACTION_STOP_WORK");
        intent.putExtra("KEY_WORKSPEC_ID", str);
        return intent;
    }

    static Intent a(@NonNull Context context) {
        Intent intent = new Intent(context, SystemAlarmService.class);
        intent.setAction("ACTION_CONSTRAINTS_CHANGED");
        return intent;
    }

    static Intent b(@NonNull Context context) {
        Intent intent = new Intent(context, SystemAlarmService.class);
        intent.setAction("ACTION_RESCHEDULE");
        return intent;
    }

    static Intent a(@NonNull Context context, @NonNull String str, boolean z, boolean z2) {
        Intent intent = new Intent(context, SystemAlarmService.class);
        intent.setAction("ACTION_EXECUTION_COMPLETED");
        intent.putExtra("KEY_WORKSPEC_ID", str);
        intent.putExtra("KEY_IS_SUCCESSFUL", z);
        intent.putExtra("KEY_NEEDS_RESCHEDULE", z2);
        return intent;
    }

    b(@NonNull Context context) {
        this.a = context;
    }

    public void onExecuted(@NonNull String str, boolean z, boolean z2) {
        synchronized (this.c) {
            a aVar = (a) this.b.remove(str);
            if (aVar != null) {
                aVar.onExecuted(str, z, z2);
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean a() {
        boolean z;
        synchronized (this.c) {
            z = !this.b.isEmpty();
        }
        return z;
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public void a(@NonNull Intent intent, int i, @NonNull e eVar) {
        String action = intent.getAction();
        if ("ACTION_CONSTRAINTS_CHANGED".equals(action)) {
            e(intent, i, eVar);
        } else if ("ACTION_RESCHEDULE".equals(action)) {
            f(intent, i, eVar);
        } else {
            if (!a(intent.getExtras(), "KEY_WORKSPEC_ID")) {
                e.e("CommandHandler", String.format("Invalid request for %s, requires %s.", new Object[]{action, "KEY_WORKSPEC_ID"}), new Throwable[0]);
            } else if ("ACTION_SCHEDULE_WORK".equals(action)) {
                b(intent, i, eVar);
            } else if ("ACTION_DELAY_MET".equals(action)) {
                c(intent, i, eVar);
            } else if ("ACTION_STOP_WORK".equals(action)) {
                d(intent, i, eVar);
            } else if ("ACTION_EXECUTION_COMPLETED".equals(action)) {
                g(intent, i, eVar);
            } else {
                e.d("CommandHandler", String.format("Ignoring intent %s", new Object[]{intent}), new Throwable[0]);
            }
        }
    }

    private void b(@NonNull Intent intent, int i, @NonNull e eVar) {
        String string = intent.getExtras().getString("KEY_WORKSPEC_ID");
        e.b("CommandHandler", String.format("Handling schedule work for %s", new Object[]{string}), new Throwable[0]);
        j b2 = eVar.d().d().workSpecDao().b(string);
        long c2 = b2.c();
        if (!b2.d()) {
            e.b("CommandHandler", String.format("Setting up Alarms for %s", new Object[]{string}), new Throwable[0]);
            a.a(this.a, eVar.d(), string, c2);
            return;
        }
        e.b("CommandHandler", String.format("Opportunistically setting an alarm for %s", new Object[]{string}), new Throwable[0]);
        a.a(this.a, eVar.d(), string, c2);
        eVar.a((Runnable) new a(eVar, a(this.a), i));
    }

    private void c(@NonNull Intent intent, int i, @NonNull e eVar) {
        Bundle extras = intent.getExtras();
        synchronized (this.c) {
            String string = extras.getString("KEY_WORKSPEC_ID");
            e.b("CommandHandler", String.format("Handing delay met for %s", new Object[]{string}), new Throwable[0]);
            d dVar = new d(this.a, i, string, eVar);
            this.b.put(string, dVar);
            dVar.a();
        }
    }

    private void d(@NonNull Intent intent, int i, @NonNull e eVar) {
        String string = intent.getExtras().getString("KEY_WORKSPEC_ID");
        e.b("CommandHandler", String.format("Handing stopWork work for %s", new Object[]{string}), new Throwable[0]);
        eVar.d().c(string);
        a.a(this.a, eVar.d(), string);
        eVar.onExecuted(string, false, false);
    }

    private void e(@NonNull Intent intent, int i, @NonNull e eVar) {
        e.b("CommandHandler", String.format("Handling constraints changed %s", new Object[]{intent}), new Throwable[0]);
        new c(this.a, i, eVar).a();
    }

    private void f(@NonNull Intent intent, int i, @NonNull e eVar) {
        e.b("CommandHandler", String.format("Handling reschedule %s, %s", new Object[]{intent, Integer.valueOf(i)}), new Throwable[0]);
        eVar.d().j();
    }

    private void g(@NonNull Intent intent, int i, @NonNull e eVar) {
        Bundle extras = intent.getExtras();
        String string = extras.getString("KEY_WORKSPEC_ID");
        boolean z = extras.getBoolean("KEY_IS_SUCCESSFUL");
        boolean z2 = extras.getBoolean("KEY_NEEDS_RESCHEDULE");
        e.b("CommandHandler", String.format("Handling onExecutionCompleted %s, %s", new Object[]{intent, Integer.valueOf(i)}), new Throwable[0]);
        onExecuted(string, z, z2);
        eVar.a((Runnable) new b(eVar));
    }

    private static boolean a(@Nullable Bundle bundle, @NonNull String... strArr) {
        if (bundle == null || bundle.isEmpty()) {
            return false;
        }
        for (String str : strArr) {
            if (!bundle.containsKey(str) || bundle.get(str) == null) {
                return false;
            }
        }
        return true;
    }
}
