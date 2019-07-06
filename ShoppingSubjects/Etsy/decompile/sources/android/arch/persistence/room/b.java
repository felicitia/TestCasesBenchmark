package android.arch.persistence.room;

import android.arch.core.executor.ArchTaskExecutor;
import android.arch.persistence.db.SupportSQLiteStatement;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.annotation.VisibleForTesting;
import android.support.v4.util.ArrayMap;
import android.support.v4.util.ArraySet;
import android.util.Log;
import com.etsy.android.lib.core.http.request.BaseHttpRequest;
import java.util.Arrays;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;

/* compiled from: InvalidationTracker */
public class b {
    private static final String[] f = {"UPDATE", BaseHttpRequest.DELETE, "INSERT"};
    @VisibleForTesting
    @NonNull
    ArrayMap<String, Integer> a;
    @VisibleForTesting
    @NonNull
    long[] b;
    AtomicBoolean c;
    @VisibleForTesting
    final android.arch.core.internal.a<C0003b, c> d;
    @VisibleForTesting
    Runnable e;
    private String[] g;
    /* access modifiers changed from: private */
    public Object[] h = new Object[1];
    /* access modifiers changed from: private */
    public long i = 0;
    /* access modifiers changed from: private */
    public final RoomDatabase j;
    private volatile boolean k;
    /* access modifiers changed from: private */
    public volatile SupportSQLiteStatement l;
    private a m;

    /* compiled from: InvalidationTracker */
    static class a {
        final long[] a;
        final boolean[] b;
        final int[] c;
        boolean d;
        boolean e;

        a(int i) {
            this.a = new long[i];
            this.b = new boolean[i];
            this.c = new int[i];
            Arrays.fill(this.a, 0);
            Arrays.fill(this.b, false);
        }

        /* access modifiers changed from: 0000 */
        @Nullable
        public int[] a() {
            synchronized (this) {
                if (this.d) {
                    if (!this.e) {
                        int length = this.a.length;
                        int i = 0;
                        while (true) {
                            int i2 = 1;
                            if (i < length) {
                                boolean z = this.a[i] > 0;
                                if (z != this.b[i]) {
                                    int[] iArr = this.c;
                                    if (!z) {
                                        i2 = 2;
                                    }
                                    iArr[i] = i2;
                                } else {
                                    this.c[i] = 0;
                                }
                                this.b[i] = z;
                                i++;
                            } else {
                                this.e = true;
                                this.d = false;
                                int[] iArr2 = this.c;
                                return iArr2;
                            }
                        }
                    }
                }
                return null;
            }
        }

        /* access modifiers changed from: 0000 */
        public void b() {
            synchronized (this) {
                this.e = false;
            }
        }
    }

    /* renamed from: android.arch.persistence.room.b$b reason: collision with other inner class name */
    /* compiled from: InvalidationTracker */
    public static abstract class C0003b {
        public abstract void a(@NonNull Set<String> set);
    }

    /* compiled from: InvalidationTracker */
    static class c {
        final int[] a;
        final C0003b b;
        private final String[] c;
        private final long[] d;
        private final Set<String> e;

        /* access modifiers changed from: 0000 */
        public void a(long[] jArr) {
            Set set = null;
            int length = this.a.length;
            for (int i = 0; i < length; i++) {
                long j = jArr[this.a[i]];
                if (this.d[i] < j) {
                    this.d[i] = j;
                    if (length == 1) {
                        set = this.e;
                    } else {
                        if (set == null) {
                            set = new ArraySet(length);
                        }
                        set.add(this.c[i]);
                    }
                }
            }
            if (set != null) {
                this.b.a(set);
            }
        }
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public b(RoomDatabase roomDatabase, String... strArr) {
        this.c = new AtomicBoolean(false);
        this.k = false;
        this.d = new android.arch.core.internal.a<>();
        this.e = new Runnable() {
            /* JADX WARNING: Removed duplicated region for block: B:43:0x009c  */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void run() {
                /*
                    r6 = this;
                    android.arch.persistence.room.b r0 = android.arch.persistence.room.b.this
                    android.arch.persistence.room.RoomDatabase r0 = r0.j
                    java.util.concurrent.locks.Lock r0 = r0.getCloseLock()
                    r1 = 0
                    r0.lock()     // Catch:{ SQLiteException | IllegalStateException -> 0x008e }
                    android.arch.persistence.room.b r2 = android.arch.persistence.room.b.this     // Catch:{ SQLiteException | IllegalStateException -> 0x008e }
                    boolean r2 = r2.b()     // Catch:{ SQLiteException | IllegalStateException -> 0x008e }
                    if (r2 != 0) goto L_0x001a
                    r0.unlock()
                    return
                L_0x001a:
                    android.arch.persistence.room.b r2 = android.arch.persistence.room.b.this     // Catch:{ SQLiteException | IllegalStateException -> 0x008e }
                    java.util.concurrent.atomic.AtomicBoolean r2 = r2.c     // Catch:{ SQLiteException | IllegalStateException -> 0x008e }
                    r3 = 1
                    boolean r2 = r2.compareAndSet(r3, r1)     // Catch:{ SQLiteException | IllegalStateException -> 0x008e }
                    if (r2 != 0) goto L_0x0029
                    r0.unlock()
                    return
                L_0x0029:
                    android.arch.persistence.room.b r2 = android.arch.persistence.room.b.this     // Catch:{ SQLiteException | IllegalStateException -> 0x008e }
                    android.arch.persistence.room.RoomDatabase r2 = r2.j     // Catch:{ SQLiteException | IllegalStateException -> 0x008e }
                    boolean r2 = r2.inTransaction()     // Catch:{ SQLiteException | IllegalStateException -> 0x008e }
                    if (r2 == 0) goto L_0x0039
                    r0.unlock()
                    return
                L_0x0039:
                    android.arch.persistence.room.b r2 = android.arch.persistence.room.b.this     // Catch:{ SQLiteException | IllegalStateException -> 0x008e }
                    android.arch.persistence.db.SupportSQLiteStatement r2 = r2.l     // Catch:{ SQLiteException | IllegalStateException -> 0x008e }
                    r2.executeUpdateDelete()     // Catch:{ SQLiteException | IllegalStateException -> 0x008e }
                    android.arch.persistence.room.b r2 = android.arch.persistence.room.b.this     // Catch:{ SQLiteException | IllegalStateException -> 0x008e }
                    java.lang.Object[] r2 = r2.h     // Catch:{ SQLiteException | IllegalStateException -> 0x008e }
                    android.arch.persistence.room.b r3 = android.arch.persistence.room.b.this     // Catch:{ SQLiteException | IllegalStateException -> 0x008e }
                    long r3 = r3.i     // Catch:{ SQLiteException | IllegalStateException -> 0x008e }
                    java.lang.Long r3 = java.lang.Long.valueOf(r3)     // Catch:{ SQLiteException | IllegalStateException -> 0x008e }
                    r2[r1] = r3     // Catch:{ SQLiteException | IllegalStateException -> 0x008e }
                    android.arch.persistence.room.b r2 = android.arch.persistence.room.b.this     // Catch:{ SQLiteException | IllegalStateException -> 0x008e }
                    android.arch.persistence.room.RoomDatabase r2 = r2.j     // Catch:{ SQLiteException | IllegalStateException -> 0x008e }
                    boolean r2 = r2.mWriteAheadLoggingEnabled     // Catch:{ SQLiteException | IllegalStateException -> 0x008e }
                    if (r2 == 0) goto L_0x0087
                    android.arch.persistence.room.b r2 = android.arch.persistence.room.b.this     // Catch:{ SQLiteException | IllegalStateException -> 0x008e }
                    android.arch.persistence.room.RoomDatabase r2 = r2.j     // Catch:{ SQLiteException | IllegalStateException -> 0x008e }
                    android.arch.persistence.db.b r2 = r2.getOpenHelper()     // Catch:{ SQLiteException | IllegalStateException -> 0x008e }
                    android.arch.persistence.db.a r2 = r2.a()     // Catch:{ SQLiteException | IllegalStateException -> 0x008e }
                    r2.a()     // Catch:{ all -> 0x0082 }
                    boolean r3 = r6.a()     // Catch:{ all -> 0x0082 }
                    r2.c()     // Catch:{ all -> 0x007d }
                    r2.b()     // Catch:{ SQLiteException | IllegalStateException -> 0x007a }
                    goto L_0x0097
                L_0x007a:
                    r1 = move-exception
                    r2 = r1
                    goto L_0x0090
                L_0x007d:
                    r1 = move-exception
                    r5 = r3
                    r3 = r1
                    r1 = r5
                    goto L_0x0083
                L_0x0082:
                    r3 = move-exception
                L_0x0083:
                    r2.b()     // Catch:{ SQLiteException | IllegalStateException -> 0x008e }
                    throw r3     // Catch:{ SQLiteException | IllegalStateException -> 0x008e }
                L_0x0087:
                    boolean r3 = r6.a()     // Catch:{ SQLiteException | IllegalStateException -> 0x008e }
                    goto L_0x0097
                L_0x008c:
                    r1 = move-exception
                    goto L_0x00c9
                L_0x008e:
                    r2 = move-exception
                    r3 = r1
                L_0x0090:
                    java.lang.String r1 = "ROOM"
                    java.lang.String r4 = "Cannot run invalidation tracker. Is the db closed?"
                    android.util.Log.e(r1, r4, r2)     // Catch:{ all -> 0x008c }
                L_0x0097:
                    r0.unlock()
                    if (r3 == 0) goto L_0x00c8
                    android.arch.persistence.room.b r0 = android.arch.persistence.room.b.this
                    android.arch.core.internal.a<android.arch.persistence.room.b$b, android.arch.persistence.room.b$c> r0 = r0.d
                    monitor-enter(r0)
                    android.arch.persistence.room.b r1 = android.arch.persistence.room.b.this     // Catch:{ all -> 0x00c5 }
                    android.arch.core.internal.a<android.arch.persistence.room.b$b, android.arch.persistence.room.b$c> r1 = r1.d     // Catch:{ all -> 0x00c5 }
                    java.util.Iterator r1 = r1.iterator()     // Catch:{ all -> 0x00c5 }
                L_0x00a9:
                    boolean r2 = r1.hasNext()     // Catch:{ all -> 0x00c5 }
                    if (r2 == 0) goto L_0x00c3
                    java.lang.Object r2 = r1.next()     // Catch:{ all -> 0x00c5 }
                    java.util.Map$Entry r2 = (java.util.Map.Entry) r2     // Catch:{ all -> 0x00c5 }
                    java.lang.Object r2 = r2.getValue()     // Catch:{ all -> 0x00c5 }
                    android.arch.persistence.room.b$c r2 = (android.arch.persistence.room.b.c) r2     // Catch:{ all -> 0x00c5 }
                    android.arch.persistence.room.b r3 = android.arch.persistence.room.b.this     // Catch:{ all -> 0x00c5 }
                    long[] r3 = r3.b     // Catch:{ all -> 0x00c5 }
                    r2.a(r3)     // Catch:{ all -> 0x00c5 }
                    goto L_0x00a9
                L_0x00c3:
                    monitor-exit(r0)     // Catch:{ all -> 0x00c5 }
                    goto L_0x00c8
                L_0x00c5:
                    r1 = move-exception
                    monitor-exit(r0)     // Catch:{ all -> 0x00c5 }
                    throw r1
                L_0x00c8:
                    return
                L_0x00c9:
                    r0.unlock()
                    throw r1
                */
                throw new UnsupportedOperationException("Method not decompiled: android.arch.persistence.room.b.AnonymousClass1.run():void");
            }

            private boolean a() {
                Cursor query = b.this.j.query("SELECT * FROM room_table_modification_log WHERE version  > ? ORDER BY version ASC;", b.this.h);
                boolean z = false;
                while (query.moveToNext()) {
                    try {
                        long j = query.getLong(0);
                        b.this.b[query.getInt(1)] = j;
                        b.this.i = j;
                        z = true;
                    } finally {
                        query.close();
                    }
                }
                return z;
            }
        };
        this.j = roomDatabase;
        this.m = new a(strArr.length);
        this.a = new ArrayMap<>();
        int length = strArr.length;
        this.g = new String[length];
        for (int i2 = 0; i2 < length; i2++) {
            String lowerCase = strArr[i2].toLowerCase(Locale.US);
            this.a.put(lowerCase, Integer.valueOf(i2));
            this.g[i2] = lowerCase;
        }
        this.b = new long[strArr.length];
        Arrays.fill(this.b, 0);
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: 0000 */
    public void a(android.arch.persistence.db.a aVar) {
        synchronized (this) {
            if (this.k) {
                Log.e("ROOM", "Invalidation tracker is initialized twice :/.");
                return;
            }
            aVar.a();
            try {
                aVar.c("PRAGMA temp_store = MEMORY;");
                aVar.c("PRAGMA recursive_triggers='ON';");
                aVar.c("CREATE TEMP TABLE room_table_modification_log(version INTEGER PRIMARY KEY AUTOINCREMENT, table_id INTEGER)");
                aVar.c();
                aVar.b();
                b(aVar);
                this.l = aVar.a("DELETE FROM room_table_modification_log WHERE version NOT IN( SELECT MAX(version) FROM room_table_modification_log GROUP BY table_id)");
                this.k = true;
            } catch (Throwable th) {
                aVar.b();
                throw th;
            }
        }
    }

    private static void a(StringBuilder sb, String str, String str2) {
        sb.append("`");
        sb.append("room_table_modification_trigger_");
        sb.append(str);
        sb.append("_");
        sb.append(str2);
        sb.append("`");
    }

    private void a(android.arch.persistence.db.a aVar, int i2) {
        String[] strArr;
        String str = this.g[i2];
        StringBuilder sb = new StringBuilder();
        for (String str2 : f) {
            sb.setLength(0);
            sb.append("DROP TRIGGER IF EXISTS ");
            a(sb, str, str2);
            aVar.c(sb.toString());
        }
    }

    private void b(android.arch.persistence.db.a aVar, int i2) {
        String[] strArr;
        String str = this.g[i2];
        StringBuilder sb = new StringBuilder();
        for (String str2 : f) {
            sb.setLength(0);
            sb.append("CREATE TEMP TRIGGER IF NOT EXISTS ");
            a(sb, str, str2);
            sb.append(" AFTER ");
            sb.append(str2);
            sb.append(" ON `");
            sb.append(str);
            sb.append("` BEGIN INSERT OR REPLACE INTO ");
            sb.append("room_table_modification_log");
            sb.append(" VALUES(null, ");
            sb.append(i2);
            sb.append("); END");
            aVar.c(sb.toString());
        }
    }

    /* access modifiers changed from: private */
    public boolean b() {
        if (!this.j.isOpen()) {
            return false;
        }
        if (!this.k) {
            this.j.getOpenHelper().a();
        }
        if (this.k) {
            return true;
        }
        Log.e("ROOM", "database is not initialized even though it is open");
        return false;
    }

    public void a() {
        if (this.c.compareAndSet(false, true)) {
            ArchTaskExecutor.getInstance().executeOnDiskIO(this.e);
        }
    }

    /* access modifiers changed from: 0000 */
    public void b(android.arch.persistence.db.a aVar) {
        if (!aVar.d()) {
            while (true) {
                try {
                    Lock closeLock = this.j.getCloseLock();
                    closeLock.lock();
                    try {
                        int[] a2 = this.m.a();
                        if (a2 == null) {
                            closeLock.unlock();
                            return;
                        }
                        int length = a2.length;
                        aVar.a();
                        for (int i2 = 0; i2 < length; i2++) {
                            switch (a2[i2]) {
                                case 1:
                                    b(aVar, i2);
                                    break;
                                case 2:
                                    a(aVar, i2);
                                    break;
                            }
                        }
                        aVar.c();
                        aVar.b();
                        this.m.b();
                        closeLock.unlock();
                    } catch (Throwable th) {
                        closeLock.unlock();
                        throw th;
                    }
                } catch (SQLiteException | IllegalStateException e2) {
                    Log.e("ROOM", "Cannot run invalidation tracker. Is the db closed?", e2);
                    return;
                }
            }
        }
    }
}
