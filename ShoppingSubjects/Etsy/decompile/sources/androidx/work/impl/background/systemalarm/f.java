package androidx.work.impl.background.systemalarm;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import androidx.work.e;
import androidx.work.impl.b.j;
import androidx.work.impl.c;

@RestrictTo({Scope.LIBRARY_GROUP})
/* compiled from: SystemAlarmScheduler */
public class f implements c {
    private final Context a;

    public f(@NonNull Context context) {
        this.a = context.getApplicationContext();
    }

    public void a(j... jVarArr) {
        for (j a2 : jVarArr) {
            a(a2);
        }
    }

    public void a(@NonNull String str) {
        this.a.startService(b.c(this.a, str));
    }

    private void a(@NonNull j jVar) {
        e.b("SystemAlarmScheduler", String.format("Scheduling work with workSpecId %s", new Object[]{jVar.a}), new Throwable[0]);
        this.a.startService(b.a(this.a, jVar.a));
    }
}
