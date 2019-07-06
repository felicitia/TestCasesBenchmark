package androidx.work.impl.background.systemalarm;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.annotation.WorkerThread;
import androidx.work.e;
import androidx.work.impl.a.d;
import androidx.work.impl.b.j;
import java.util.ArrayList;
import java.util.List;

@RestrictTo({Scope.LIBRARY_GROUP})
/* compiled from: ConstraintsCommandHandler */
class c {
    private final Context a;
    private final int b;
    private final e c;
    private final List<j> d = new ArrayList();
    private final d e = new d(this.a, null);

    c(@NonNull Context context, int i, @NonNull e eVar) {
        this.a = context;
        this.b = i;
        this.c = eVar;
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public void a() {
        List<j> a2 = this.c.d().d().workSpecDao().a(this.c.d().e().e());
        ArrayList<j> arrayList = new ArrayList<>(a2.size());
        for (j jVar : a2) {
            if (jVar.p != -1) {
                arrayList.add(jVar);
            }
        }
        ConstraintProxy.updateAll(this.a, arrayList);
        this.e.a((List<j>) arrayList);
        for (j jVar2 : arrayList) {
            String str = jVar2.a;
            if (!jVar2.d() || this.e.a(str)) {
                this.d.add(jVar2);
            }
        }
        for (j jVar3 : this.d) {
            String str2 = jVar3.a;
            Intent b2 = b.b(this.a, str2);
            e.b("ConstraintsCmdHandler", String.format("Creating a delay_met command for workSpec with id (%s)", new Object[]{str2}), new Throwable[0]);
            this.c.a((Runnable) new a(this.c, b2, this.b));
        }
        this.e.a();
    }
}
