package androidx.work.impl.background.a;

import android.content.Context;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import androidx.work.State;
import androidx.work.e;
import androidx.work.impl.a.c;
import androidx.work.impl.a.d;
import androidx.work.impl.b.j;
import androidx.work.impl.f;
import java.util.ArrayList;
import java.util.List;

@RestrictTo({Scope.LIBRARY_GROUP})
/* compiled from: GreedyScheduler */
public class a implements androidx.work.impl.a, c, androidx.work.impl.c {
    private f a;
    private d b;
    private List<j> c = new ArrayList();
    private boolean d;

    public a(Context context, f fVar) {
        this.a = fVar;
        this.b = new d(context, this);
    }

    public synchronized void a(j... jVarArr) {
        a();
        int size = this.c.size();
        for (j jVar : jVarArr) {
            if (jVar.b == State.ENQUEUED && !jVar.a() && jVar.g == 0) {
                if (!jVar.d()) {
                    this.a.b(jVar.a);
                } else if (VERSION.SDK_INT < 24 || !jVar.j.g()) {
                    e.b("GreedyScheduler", String.format("Starting tracking for %s", new Object[]{jVar.a}), new Throwable[0]);
                    this.c.add(jVar);
                }
            }
        }
        if (size != this.c.size()) {
            this.b.a(this.c);
        }
    }

    public synchronized void a(@NonNull String str) {
        a();
        e.b("GreedyScheduler", String.format("Cancelling work ID %s", new Object[]{str}), new Throwable[0]);
        this.a.c(str);
        b(str);
    }

    public synchronized void a(@NonNull List<String> list) {
        for (String str : list) {
            e.b("GreedyScheduler", String.format("Constraints met: Scheduling work ID %s", new Object[]{str}), new Throwable[0]);
            this.a.b(str);
        }
    }

    public synchronized void b(@NonNull List<String> list) {
        for (String str : list) {
            e.b("GreedyScheduler", String.format("Constraints not met: Cancelling work ID %s", new Object[]{str}), new Throwable[0]);
            this.a.c(str);
        }
    }

    public synchronized void onExecuted(@NonNull String str, boolean z, boolean z2) {
        b(str);
    }

    private synchronized void b(@NonNull String str) {
        int size = this.c.size();
        int i = 0;
        while (true) {
            if (i >= size) {
                break;
            } else if (((j) this.c.get(i)).a.equals(str)) {
                e.b("GreedyScheduler", String.format("Stopping tracking for %s", new Object[]{str}), new Throwable[0]);
                this.c.remove(i);
                this.b.a(this.c);
                break;
            } else {
                i++;
            }
        }
    }

    private void a() {
        if (!this.d) {
            this.a.g().a((androidx.work.impl.a) this);
            this.d = true;
        }
    }
}
