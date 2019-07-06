package com.crittercism.internal;

import android.support.annotation.NonNull;
import com.crittercism.internal.at.b;
import com.crittercism.internal.b.c;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;

public final class d {
    final ay<at> a;
    final ay<b> b;
    final ap c;
    private final List<String> d;
    private final List<String> e;
    private final Executor f;

    public static class a {
        public Executor a;
        public List<String> b = new LinkedList();
        public List<String> c = new LinkedList();
        public ay<b> d = new bg();
        public ay<at> e = new bg();
        public ap f;
    }

    public /* synthetic */ d(Executor executor, List list, List list2, ay ayVar, ay ayVar2, ap apVar, byte b2) {
        this(executor, list, list2, ayVar, ayVar2, apVar);
    }

    private d(@NonNull Executor executor, @NonNull List<String> list, @NonNull List<String> list2, @NonNull ay<b> ayVar, @NonNull ay<at> ayVar2, @NonNull ap apVar) {
        this.f = executor;
        this.b = ayVar;
        this.a = ayVar2;
        this.d = new LinkedList(list);
        this.d.remove(null);
        this.e = new LinkedList(list2);
        this.e.remove(null);
        this.c = apVar;
    }

    @Deprecated
    public final void a(b bVar) {
        a(bVar, c.LEGACY_JAVANET);
    }

    private boolean b(b bVar) {
        String a2 = bVar.a();
        synchronized (this.d) {
            for (String contains : this.d) {
                if (a2.contains(contains)) {
                    return true;
                }
            }
            return false;
        }
    }

    private boolean a(String str) {
        synchronized (this.e) {
            for (String contains : this.e) {
                if (str.contains(contains)) {
                    return false;
                }
            }
            return true;
        }
    }

    public final void a(final b bVar, c cVar) {
        if (!bVar.c) {
            bVar.c = true;
            bVar.f = cVar;
            if (!b(bVar)) {
                String a2 = bVar.a();
                if (a(a2)) {
                    int indexOf = a2.indexOf("?");
                    if (indexOf != -1) {
                        bVar.a(a2.substring(0, indexOf));
                    }
                }
                try {
                    this.f.execute(new Runnable() {
                        public final void run() {
                            if (((Boolean) d.this.c.a(ap.c)).booleanValue()) {
                                bVar.e = ((Float) d.this.c.a(ap.g)).floatValue();
                                if (((Boolean) d.this.c.a(ap.ap)).booleanValue()) {
                                    d.this.a.a(new at(b.c, bVar.g()));
                                }
                                d.this.b.a(bVar);
                            }
                        }
                    });
                } catch (RejectedExecutionException unused) {
                }
            }
        }
    }
}
