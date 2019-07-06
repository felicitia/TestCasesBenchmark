package io.branch.referral;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import io.branch.referral.Branch.f;
import io.branch.referral.Defines.RequestPath;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/* compiled from: ServerRequestQueue */
class u {
    private static u a;
    /* access modifiers changed from: private */
    public static final Object e = new Object();
    private SharedPreferences b;
    /* access modifiers changed from: private */
    public Editor c = this.b.edit();
    /* access modifiers changed from: private */
    public final List<ServerRequest> d;

    public static u a(Context context) {
        if (a == null) {
            synchronized (u.class) {
                if (a == null) {
                    a = new u(context);
                }
            }
        }
        return a;
    }

    @SuppressLint({"CommitPrefEdits"})
    private u(Context context) {
        this.b = context.getSharedPreferences("BNC_Server_Request_Queue", 0);
        this.d = b(context);
    }

    private void i() {
        new Thread(new Runnable() {
            /* JADX WARNING: Can't wrap try/catch for region: R(5:13|23|24|25|26) */
            /* JADX WARNING: Can't wrap try/catch for region: R(6:2|3|(4:6|(2:8|(2:10|33)(1:32))(1:31)|30|4)|(2:11|12)|20|21) */
            /* JADX WARNING: Missing exception handler attribute for start block: B:20:0x0074 */
            /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x0089 */
            /* JADX WARNING: Unknown top exception splitter block from list: {B:20:0x0074=Splitter:B:20:0x0074, B:25:0x0089=Splitter:B:25:0x0089} */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void run() {
                /*
                    r6 = this;
                    java.lang.Object r0 = io.branch.referral.u.e
                    monitor-enter(r0)
                    org.json.JSONArray r1 = new org.json.JSONArray     // Catch:{ all -> 0x008a }
                    r1.<init>()     // Catch:{ all -> 0x008a }
                    io.branch.referral.u r2 = io.branch.referral.u.this     // Catch:{ all -> 0x008a }
                    java.util.List r2 = r2.d     // Catch:{ all -> 0x008a }
                    java.util.Iterator r2 = r2.iterator()     // Catch:{ all -> 0x008a }
                L_0x0014:
                    boolean r3 = r2.hasNext()     // Catch:{ all -> 0x008a }
                    if (r3 == 0) goto L_0x0030
                    java.lang.Object r3 = r2.next()     // Catch:{ all -> 0x008a }
                    io.branch.referral.ServerRequest r3 = (io.branch.referral.ServerRequest) r3     // Catch:{ all -> 0x008a }
                    boolean r4 = r3.d()     // Catch:{ all -> 0x008a }
                    if (r4 == 0) goto L_0x0014
                    org.json.JSONObject r3 = r3.k()     // Catch:{ all -> 0x008a }
                    if (r3 == 0) goto L_0x0014
                    r1.put(r3)     // Catch:{ all -> 0x008a }
                    goto L_0x0014
                L_0x0030:
                    io.branch.referral.u r2 = io.branch.referral.u.this     // Catch:{ Exception -> 0x0046 }
                    android.content.SharedPreferences$Editor r2 = r2.c     // Catch:{ Exception -> 0x0046 }
                    java.lang.String r3 = "BNCServerRequestQueue"
                    java.lang.String r4 = r1.toString()     // Catch:{ Exception -> 0x0046 }
                    android.content.SharedPreferences$Editor r2 = r2.putString(r3, r4)     // Catch:{ Exception -> 0x0046 }
                    r2.commit()     // Catch:{ Exception -> 0x0046 }
                    goto L_0x0074
                L_0x0044:
                    r2 = move-exception
                    goto L_0x0076
                L_0x0046:
                    r2 = move-exception
                    java.lang.String r3 = "Persisting Queue: "
                    java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0044 }
                    r4.<init>()     // Catch:{ all -> 0x0044 }
                    java.lang.String r5 = "Failed to persit queue "
                    r4.append(r5)     // Catch:{ all -> 0x0044 }
                    java.lang.String r2 = r2.getMessage()     // Catch:{ all -> 0x0044 }
                    r4.append(r2)     // Catch:{ all -> 0x0044 }
                    java.lang.String r2 = r4.toString()     // Catch:{ all -> 0x0044 }
                    io.branch.referral.m.b(r3, r2)     // Catch:{ all -> 0x0044 }
                    io.branch.referral.u r2 = io.branch.referral.u.this     // Catch:{ Exception -> 0x0074 }
                    android.content.SharedPreferences$Editor r2 = r2.c     // Catch:{ Exception -> 0x0074 }
                    java.lang.String r3 = "BNCServerRequestQueue"
                    java.lang.String r1 = r1.toString()     // Catch:{ Exception -> 0x0074 }
                    android.content.SharedPreferences$Editor r1 = r2.putString(r3, r1)     // Catch:{ Exception -> 0x0074 }
                    r1.commit()     // Catch:{ Exception -> 0x0074 }
                L_0x0074:
                    monitor-exit(r0)     // Catch:{ all -> 0x008a }
                    return
                L_0x0076:
                    io.branch.referral.u r3 = io.branch.referral.u.this     // Catch:{ Exception -> 0x0089 }
                    android.content.SharedPreferences$Editor r3 = r3.c     // Catch:{ Exception -> 0x0089 }
                    java.lang.String r4 = "BNCServerRequestQueue"
                    java.lang.String r1 = r1.toString()     // Catch:{ Exception -> 0x0089 }
                    android.content.SharedPreferences$Editor r1 = r3.putString(r4, r1)     // Catch:{ Exception -> 0x0089 }
                    r1.commit()     // Catch:{ Exception -> 0x0089 }
                L_0x0089:
                    throw r2     // Catch:{ all -> 0x008a }
                L_0x008a:
                    r1 = move-exception
                    monitor-exit(r0)     // Catch:{ all -> 0x008a }
                    throw r1
                */
                throw new UnsupportedOperationException("Method not decompiled: io.branch.referral.u.AnonymousClass1.run():void");
            }
        }).start();
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.util.List<io.branch.referral.ServerRequest> b(android.content.Context r7) {
        /*
            r6 = this;
            android.content.SharedPreferences r0 = r6.b
            java.lang.String r1 = "BNCServerRequestQueue"
            r2 = 0
            java.lang.String r0 = r0.getString(r1, r2)
            java.util.LinkedList r1 = new java.util.LinkedList
            r1.<init>()
            java.util.List r1 = java.util.Collections.synchronizedList(r1)
            java.lang.Object r2 = e
            monitor-enter(r2)
            if (r0 == 0) goto L_0x003b
            org.json.JSONArray r3 = new org.json.JSONArray     // Catch:{ JSONException -> 0x003b }
            r3.<init>(r0)     // Catch:{ JSONException -> 0x003b }
            r0 = 0
            int r4 = r3.length()     // Catch:{ JSONException -> 0x003b }
            r5 = 25
            int r4 = java.lang.Math.min(r4, r5)     // Catch:{ JSONException -> 0x003b }
        L_0x0027:
            if (r0 >= r4) goto L_0x003b
            org.json.JSONObject r5 = r3.getJSONObject(r0)     // Catch:{ JSONException -> 0x003b }
            io.branch.referral.ServerRequest r5 = io.branch.referral.ServerRequest.a(r5, r7)     // Catch:{ JSONException -> 0x003b }
            if (r5 == 0) goto L_0x0036
            r1.add(r5)     // Catch:{ JSONException -> 0x003b }
        L_0x0036:
            int r0 = r0 + 1
            goto L_0x0027
        L_0x0039:
            r7 = move-exception
            goto L_0x003d
        L_0x003b:
            monitor-exit(r2)     // Catch:{ all -> 0x0039 }
            return r1
        L_0x003d:
            monitor-exit(r2)     // Catch:{ all -> 0x0039 }
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: io.branch.referral.u.b(android.content.Context):java.util.List");
    }

    public int a() {
        int size;
        synchronized (e) {
            size = this.d.size();
        }
        return size;
    }

    /* access modifiers changed from: 0000 */
    public void a(ServerRequest serverRequest) {
        synchronized (e) {
            if (serverRequest != null) {
                try {
                    this.d.add(serverRequest);
                    if (a() >= 25) {
                        this.d.remove(1);
                    }
                    i();
                } finally {
                }
            }
        }
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:10:0x0014 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public io.branch.referral.ServerRequest b() {
        /*
            r4 = this;
            java.lang.Object r0 = e
            monitor-enter(r0)
            r1 = 0
            java.util.List<io.branch.referral.ServerRequest> r2 = r4.d     // Catch:{ IndexOutOfBoundsException | NoSuchElementException -> 0x0013 }
            r3 = 0
            java.lang.Object r2 = r2.remove(r3)     // Catch:{ IndexOutOfBoundsException | NoSuchElementException -> 0x0013 }
            io.branch.referral.ServerRequest r2 = (io.branch.referral.ServerRequest) r2     // Catch:{ IndexOutOfBoundsException | NoSuchElementException -> 0x0013 }
            r4.i()     // Catch:{ IndexOutOfBoundsException | NoSuchElementException -> 0x0014 }
            goto L_0x0014
        L_0x0011:
            r1 = move-exception
            goto L_0x0016
        L_0x0013:
            r2 = r1
        L_0x0014:
            monitor-exit(r0)     // Catch:{ all -> 0x0011 }
            return r2
        L_0x0016:
            monitor-exit(r0)     // Catch:{ all -> 0x0011 }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: io.branch.referral.u.b():io.branch.referral.ServerRequest");
    }

    /* access modifiers changed from: 0000 */
    public ServerRequest c() {
        ServerRequest serverRequest;
        synchronized (e) {
            try {
                serverRequest = (ServerRequest) this.d.get(0);
            } catch (IndexOutOfBoundsException | NoSuchElementException unused) {
                serverRequest = null;
            }
        }
        return serverRequest;
    }

    /* access modifiers changed from: 0000 */
    public ServerRequest a(int i) {
        ServerRequest serverRequest;
        synchronized (e) {
            try {
                serverRequest = (ServerRequest) this.d.get(i);
            } catch (IndexOutOfBoundsException | NoSuchElementException unused) {
                serverRequest = null;
            }
        }
        return serverRequest;
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:8:0x001c */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(io.branch.referral.ServerRequest r3, int r4) {
        /*
            r2 = this;
            java.lang.Object r0 = e
            monitor-enter(r0)
            java.util.List<io.branch.referral.ServerRequest> r1 = r2.d     // Catch:{ IndexOutOfBoundsException -> 0x001c }
            int r1 = r1.size()     // Catch:{ IndexOutOfBoundsException -> 0x001c }
            if (r1 >= r4) goto L_0x0011
            java.util.List<io.branch.referral.ServerRequest> r4 = r2.d     // Catch:{ IndexOutOfBoundsException -> 0x001c }
            int r4 = r4.size()     // Catch:{ IndexOutOfBoundsException -> 0x001c }
        L_0x0011:
            java.util.List<io.branch.referral.ServerRequest> r1 = r2.d     // Catch:{ IndexOutOfBoundsException -> 0x001c }
            r1.add(r4, r3)     // Catch:{ IndexOutOfBoundsException -> 0x001c }
            r2.i()     // Catch:{ IndexOutOfBoundsException -> 0x001c }
            goto L_0x001c
        L_0x001a:
            r3 = move-exception
            goto L_0x001e
        L_0x001c:
            monitor-exit(r0)     // Catch:{ all -> 0x001a }
            return
        L_0x001e:
            monitor-exit(r0)     // Catch:{ all -> 0x001a }
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: io.branch.referral.u.a(io.branch.referral.ServerRequest, int):void");
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:10:0x0011 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean b(io.branch.referral.ServerRequest r4) {
        /*
            r3 = this;
            java.lang.Object r0 = e
            monitor-enter(r0)
            r1 = 0
            java.util.List<io.branch.referral.ServerRequest> r2 = r3.d     // Catch:{ UnsupportedOperationException -> 0x0010 }
            boolean r4 = r2.remove(r4)     // Catch:{ UnsupportedOperationException -> 0x0010 }
            r3.i()     // Catch:{ UnsupportedOperationException -> 0x0011 }
            goto L_0x0011
        L_0x000e:
            r4 = move-exception
            goto L_0x0013
        L_0x0010:
            r4 = r1
        L_0x0011:
            monitor-exit(r0)     // Catch:{ all -> 0x000e }
            return r4
        L_0x0013:
            monitor-exit(r0)     // Catch:{ all -> 0x000e }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: io.branch.referral.u.b(io.branch.referral.ServerRequest):boolean");
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x000e */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void d() {
        /*
            r2 = this;
            java.lang.Object r0 = e
            monitor-enter(r0)
            java.util.List<io.branch.referral.ServerRequest> r1 = r2.d     // Catch:{ UnsupportedOperationException -> 0x000e }
            r1.clear()     // Catch:{ UnsupportedOperationException -> 0x000e }
            r2.i()     // Catch:{ UnsupportedOperationException -> 0x000e }
            goto L_0x000e
        L_0x000c:
            r1 = move-exception
            goto L_0x0010
        L_0x000e:
            monitor-exit(r0)     // Catch:{ all -> 0x000c }
            return
        L_0x0010:
            monitor-exit(r0)     // Catch:{ all -> 0x000c }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: io.branch.referral.u.d():void");
    }

    /* access modifiers changed from: 0000 */
    public boolean e() {
        synchronized (e) {
            for (ServerRequest serverRequest : this.d) {
                if (serverRequest != null && serverRequest.f().equals(RequestPath.RegisterClose.getPath())) {
                    return true;
                }
            }
            return false;
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean f() {
        synchronized (e) {
            for (ServerRequest serverRequest : this.d) {
                if (serverRequest != null && ((serverRequest instanceof x) || (serverRequest instanceof y))) {
                    return true;
                }
            }
            return false;
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(ServerRequest serverRequest, int i, f fVar) {
        synchronized (e) {
            Iterator it = this.d.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                ServerRequest serverRequest2 = (ServerRequest) it.next();
                if (serverRequest2 == null || (!(serverRequest2 instanceof x) && !(serverRequest2 instanceof y))) {
                }
            }
            it.remove();
        }
        a(serverRequest, i == 0 ? 0 : 1);
    }

    /* access modifiers changed from: 0000 */
    public void a(f fVar) {
        synchronized (e) {
            for (ServerRequest serverRequest : this.d) {
                if (serverRequest != null) {
                    if (serverRequest instanceof x) {
                        ((x) serverRequest).a(fVar);
                    } else if (serverRequest instanceof y) {
                        ((y) serverRequest).a(fVar);
                    }
                }
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(PROCESS_WAIT_LOCK process_wait_lock) {
        synchronized (e) {
            for (ServerRequest serverRequest : this.d) {
                if (serverRequest != null) {
                    serverRequest.b(process_wait_lock);
                }
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void g() {
        synchronized (e) {
            for (ServerRequest serverRequest : this.d) {
                if (serverRequest != null && (serverRequest instanceof s)) {
                    serverRequest.a(PROCESS_WAIT_LOCK.STRONG_MATCH_PENDING_WAIT_LOCK);
                }
            }
        }
    }
}
