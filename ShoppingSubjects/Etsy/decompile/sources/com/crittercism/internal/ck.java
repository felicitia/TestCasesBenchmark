package com.crittercism.internal;

import android.app.Application;
import com.crittercism.internal.cj.d;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public final class ck {
    public final HashMap<String, cj> a = new HashMap<>();
    public final ScheduledExecutorService b;
    final ay<cj> c;
    public ap d;
    public boolean e = "true".equals(System.getProperty("com.crittercism.appLoadUserflowIsDisabled", "false"));
    private ScheduledFuture f;
    private a g;

    class a extends br {
        public a(Application application) {
            super(application);
            a();
        }

        public final void b() {
            long currentTimeMillis = System.currentTimeMillis();
            synchronized (ck.this.a) {
                for (cj cjVar : ck.this.a.values()) {
                    if (cjVar.f == d.b) {
                        if (currentTimeMillis >= cjVar.d) {
                            cjVar.i.add(new b(c.b, currentTimeMillis));
                        }
                    }
                }
            }
        }

        public final void c() {
            long currentTimeMillis = System.currentTimeMillis();
            synchronized (ck.this.a) {
                for (cj cjVar : ck.this.a.values()) {
                    if (cjVar.f == d.b) {
                        if (currentTimeMillis >= cjVar.d) {
                            cjVar.i.add(new b(c.a, currentTimeMillis));
                        }
                    }
                }
            }
        }
    }

    class b implements Runnable {
        private b() {
        }

        /* synthetic */ b(ck ckVar, byte b) {
            this();
        }

        public final void run() {
            HashMap hashMap = new HashMap();
            synchronized (ck.this.a) {
                for (Entry entry : ck.this.a.entrySet()) {
                    String str = (String) entry.getKey();
                    cj cjVar = (cj) entry.getValue();
                    if (cjVar.a() >= cjVar.b) {
                        hashMap.put(str, cjVar);
                    }
                }
                for (Entry entry2 : hashMap.entrySet()) {
                    String str2 = (String) entry2.getKey();
                    cj cjVar2 = (cj) entry2.getValue();
                    ck.this.a.remove(str2);
                    cjVar2.a(d.f, System.currentTimeMillis());
                    if (((Boolean) ck.this.d.a(ap.ah)).booleanValue()) {
                        cjVar2.j = ((Float) ck.this.d.a(ap.al)).floatValue();
                        ck.this.c.a(cjVar2);
                    }
                }
            }
        }
    }

    public ck(Application application, ScheduledExecutorService scheduledExecutorService, ay<cj> ayVar, ap apVar) {
        this.b = scheduledExecutorService;
        this.c = ayVar;
        this.d = apVar;
        TimeUnit timeUnit = TimeUnit.SECONDS;
        if (this.f != null) {
            this.f.cancel(false);
        }
        if (this.f == null || this.f.isDone()) {
            this.f = this.b.scheduleAtFixedRate(new b(this, 0), 10, 10, timeUnit);
        }
        this.g = new a(application);
    }

    public final void a(String str, long j) {
        synchronized (this.a) {
            final cj cjVar = (cj) this.a.remove(str);
            if (cjVar == null) {
                StringBuilder sb = new StringBuilder("endUserflow(");
                sb.append(str);
                sb.append("): no such userflow");
                cm.b(sb.toString());
                return;
            }
            cjVar.a(d.c, j);
            this.b.submit(new Runnable() {
                public final void run() {
                    if (((Boolean) ck.this.d.a(ap.ah)).booleanValue()) {
                        cjVar.j = ((Float) ck.this.d.a(ap.al)).floatValue();
                        ck.this.c.a(cjVar);
                    }
                }
            });
        }
    }

    public final int a(String str) {
        synchronized (this.a) {
            cj cjVar = (cj) this.a.get(str);
            if (cjVar == null) {
                StringBuilder sb = new StringBuilder("getUserflowValue(");
                sb.append(str);
                sb.append("): no such userflow");
                cm.b(sb.toString());
                return -1;
            }
            int i = cjVar.c;
            return i;
        }
    }

    public final Collection<cj> a() {
        long currentTimeMillis = System.currentTimeMillis();
        synchronized (this.a) {
            LinkedList linkedList = new LinkedList(this.a.values());
            this.a.clear();
            if (!((Boolean) this.d.a(ap.ah)).booleanValue()) {
                List list = Collections.EMPTY_LIST;
                return list;
            }
            Iterator it = linkedList.iterator();
            while (it.hasNext()) {
                ((cj) it.next()).a(d.g, currentTimeMillis);
            }
            return linkedList;
        }
    }
}
