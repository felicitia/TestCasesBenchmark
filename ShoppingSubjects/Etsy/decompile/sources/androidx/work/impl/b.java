package androidx.work.impl;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import androidx.work.a;
import androidx.work.e;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executor;

@RestrictTo({Scope.LIBRARY_GROUP})
/* compiled from: Processor */
public class b implements a {
    private Context a;
    private a b;
    private WorkDatabase c;
    private Map<String, g> d = new HashMap();
    private List<c> e;
    private Executor f;
    private Set<String> g;
    private final List<a> h;

    public b(Context context, a aVar, WorkDatabase workDatabase, List<c> list, Executor executor) {
        this.a = context;
        this.b = aVar;
        this.c = workDatabase;
        this.e = list;
        this.f = executor;
        this.g = new HashSet();
        this.h = new ArrayList();
    }

    public synchronized boolean a(String str) {
        return a(str, null);
    }

    public synchronized boolean a(String str, Extras.a aVar) {
        if (this.d.containsKey(str)) {
            e.b("Processor", String.format("Work %s is already enqueued for processing", new Object[]{str}), new Throwable[0]);
            return false;
        }
        g a2 = new g.a(this.a, this.b, this.c, str).a((a) this).a(this.e).a(aVar).a();
        this.d.put(str, a2);
        this.f.execute(a2);
        e.b("Processor", String.format("%s: processing %s", new Object[]{getClass().getSimpleName(), str}), new Throwable[0]);
        return true;
    }

    public synchronized boolean b(String str) {
        e.b("Processor", String.format("Processor stopping %s", new Object[]{str}), new Throwable[0]);
        g gVar = (g) this.d.remove(str);
        if (gVar != null) {
            gVar.a(false);
            e.b("Processor", String.format("WorkerWrapper stopped for %s", new Object[]{str}), new Throwable[0]);
            return true;
        }
        e.b("Processor", String.format("WorkerWrapper could not be found for %s", new Object[]{str}), new Throwable[0]);
        return false;
    }

    public synchronized boolean c(String str) {
        e.b("Processor", String.format("Processor cancelling %s", new Object[]{str}), new Throwable[0]);
        this.g.add(str);
        g gVar = (g) this.d.remove(str);
        if (gVar != null) {
            gVar.a(true);
            e.b("Processor", String.format("WorkerWrapper cancelled for %s", new Object[]{str}), new Throwable[0]);
            return true;
        }
        e.b("Processor", String.format("WorkerWrapper could not be found for %s", new Object[]{str}), new Throwable[0]);
        return false;
    }

    public synchronized boolean d(String str) {
        return this.g.contains(str);
    }

    public synchronized boolean e(@NonNull String str) {
        return this.d.containsKey(str);
    }

    public synchronized void a(a aVar) {
        this.h.add(aVar);
    }

    public synchronized void b(a aVar) {
        this.h.remove(aVar);
    }

    public synchronized void onExecuted(@NonNull String str, boolean z, boolean z2) {
        this.d.remove(str);
        e.b("Processor", String.format("%s %s executed; isSuccessful = %s, reschedule = %s", new Object[]{getClass().getSimpleName(), str, Boolean.valueOf(z), Boolean.valueOf(z2)}), new Throwable[0]);
        for (a onExecuted : this.h) {
            onExecuted.onExecuted(str, z, z2);
        }
    }
}
