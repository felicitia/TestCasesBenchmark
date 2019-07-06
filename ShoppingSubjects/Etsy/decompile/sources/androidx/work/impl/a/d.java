package androidx.work.impl.a;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import androidx.work.impl.a.a.b;
import androidx.work.impl.a.a.c;
import androidx.work.impl.a.a.c.a;
import androidx.work.impl.a.a.e;
import androidx.work.impl.a.a.f;
import androidx.work.impl.a.a.g;
import androidx.work.impl.a.a.h;
import androidx.work.impl.b.j;
import java.util.ArrayList;
import java.util.List;

/* compiled from: WorkConstraintsTracker */
public class d implements a {
    @Nullable
    private final c a;
    private final c[] b;
    private final Object c = new Object();

    public d(Context context, @Nullable c cVar) {
        Context applicationContext = context.getApplicationContext();
        this.a = cVar;
        this.b = new c[]{new androidx.work.impl.a.a.a(applicationContext), new b(applicationContext), new h(applicationContext), new androidx.work.impl.a.a.d(applicationContext), new g(applicationContext), new f(applicationContext), new e(applicationContext)};
    }

    public void a(@NonNull List<j> list) {
        synchronized (this.c) {
            for (c a2 : this.b) {
                a2.a((a) null);
            }
            for (c a3 : this.b) {
                a3.a(list);
            }
            for (c a4 : this.b) {
                a4.a((a) this);
            }
        }
    }

    public void a() {
        synchronized (this.c) {
            for (c a2 : this.b) {
                a2.a();
            }
        }
    }

    public boolean a(@NonNull String str) {
        c[] cVarArr;
        synchronized (this.c) {
            for (c cVar : this.b) {
                if (cVar.a(str)) {
                    androidx.work.e.b("WorkConstraintsTracker", String.format("Work %s constrained by %s", new Object[]{str, cVar.getClass().getSimpleName()}), new Throwable[0]);
                    return false;
                }
            }
            return true;
        }
    }

    public void b(@NonNull List<String> list) {
        synchronized (this.c) {
            ArrayList arrayList = new ArrayList();
            for (String str : list) {
                if (a(str)) {
                    androidx.work.e.b("WorkConstraintsTracker", String.format("Constraints met for %s", new Object[]{str}), new Throwable[0]);
                    arrayList.add(str);
                }
            }
            if (this.a != null) {
                this.a.a(arrayList);
            }
        }
    }

    public void c(@NonNull List<String> list) {
        synchronized (this.c) {
            if (this.a != null) {
                this.a.b(list);
            }
        }
    }
}
