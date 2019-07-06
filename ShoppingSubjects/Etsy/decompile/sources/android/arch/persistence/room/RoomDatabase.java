package android.arch.persistence.room;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.arch.core.executor.ArchTaskExecutor;
import android.arch.persistence.db.SimpleSQLiteQuery;
import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.db.d;
import android.arch.persistence.db.framework.FrameworkSQLiteOpenHelperFactory;
import android.content.Context;
import android.database.Cursor;
import android.os.Build.VERSION;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.annotation.WorkerThread;
import android.support.v4.app.ActivityManagerCompat;
import android.support.v4.util.SparseArrayCompat;
import android.util.Log;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public abstract class RoomDatabase {
    private static final String DB_IMPL_SUFFIX = "_Impl";
    @RestrictTo({Scope.LIBRARY_GROUP})
    public static final int MAX_BIND_PARAMETER_CNT = 999;
    private boolean mAllowMainThreadQueries;
    @Nullable
    protected List<b> mCallbacks;
    private final ReentrantLock mCloseLock = new ReentrantLock();
    protected volatile android.arch.persistence.db.a mDatabase;
    private final b mInvalidationTracker = createInvalidationTracker();
    private android.arch.persistence.db.b mOpenHelper;
    boolean mWriteAheadLoggingEnabled;

    public enum JournalMode {
        AUTOMATIC,
        TRUNCATE,
        WRITE_AHEAD_LOGGING;

        /* access modifiers changed from: 0000 */
        @SuppressLint({"NewApi"})
        public JournalMode resolve(Context context) {
            if (this != AUTOMATIC) {
                return this;
            }
            if (VERSION.SDK_INT >= 16) {
                ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
                if (activityManager != null && !ActivityManagerCompat.isLowRamDevice(activityManager)) {
                    return WRITE_AHEAD_LOGGING;
                }
            }
            return TRUNCATE;
        }
    }

    public static class a<T extends RoomDatabase> {
        private final Class<T> a;
        private final String b;
        private final Context c;
        private ArrayList<b> d;
        private android.arch.persistence.db.b.c e;
        private boolean f;
        private JournalMode g = JournalMode.AUTOMATIC;
        private boolean h = true;
        private final c i = new c();
        private Set<Integer> j;
        private Set<Integer> k;

        a(@NonNull Context context, @NonNull Class<T> cls, @Nullable String str) {
            this.c = context;
            this.a = cls;
            this.b = str;
        }

        @NonNull
        public a<T> a(@NonNull android.arch.persistence.room.a.a... aVarArr) {
            if (this.k == null) {
                this.k = new HashSet();
            }
            for (android.arch.persistence.room.a.a aVar : aVarArr) {
                this.k.add(Integer.valueOf(aVar.startVersion));
                this.k.add(Integer.valueOf(aVar.endVersion));
            }
            this.i.a(aVarArr);
            return this;
        }

        @NonNull
        public a<T> a() {
            this.f = true;
            return this;
        }

        @NonNull
        public a<T> b() {
            this.h = false;
            return this;
        }

        @NonNull
        public a<T> a(@NonNull b bVar) {
            if (this.d == null) {
                this.d = new ArrayList<>();
            }
            this.d.add(bVar);
            return this;
        }

        @NonNull
        public T c() {
            if (this.c == null) {
                throw new IllegalArgumentException("Cannot provide null context for the database.");
            } else if (this.a == null) {
                throw new IllegalArgumentException("Must provide an abstract class that extends RoomDatabase");
            } else {
                if (!(this.k == null || this.j == null)) {
                    for (Integer num : this.k) {
                        if (this.j.contains(num)) {
                            StringBuilder sb = new StringBuilder();
                            sb.append("Inconsistency detected. A Migration was supplied to addMigration(Migration... migrations) that has a start or end version equal to a start version supplied to fallbackToDestructiveMigrationFrom(int... startVersions). Start version: ");
                            sb.append(num);
                            throw new IllegalArgumentException(sb.toString());
                        }
                    }
                }
                if (this.e == null) {
                    this.e = new FrameworkSQLiteOpenHelperFactory();
                }
                a aVar = new a(this.c, this.b, this.e, this.i, this.d, this.f, this.g.resolve(this.c), this.h, this.j);
                T t = (RoomDatabase) c.a(this.a, RoomDatabase.DB_IMPL_SUFFIX);
                t.init(aVar);
                return t;
            }
        }
    }

    public static abstract class b {
        public void a(@NonNull android.arch.persistence.db.a aVar) {
        }

        public void b(@NonNull android.arch.persistence.db.a aVar) {
        }
    }

    public static class c {
        private SparseArrayCompat<SparseArrayCompat<android.arch.persistence.room.a.a>> a = new SparseArrayCompat<>();

        public void a(@NonNull android.arch.persistence.room.a.a... aVarArr) {
            for (android.arch.persistence.room.a.a a2 : aVarArr) {
                a(a2);
            }
        }

        private void a(android.arch.persistence.room.a.a aVar) {
            int i = aVar.startVersion;
            int i2 = aVar.endVersion;
            SparseArrayCompat sparseArrayCompat = (SparseArrayCompat) this.a.get(i);
            if (sparseArrayCompat == null) {
                sparseArrayCompat = new SparseArrayCompat();
                this.a.put(i, sparseArrayCompat);
            }
            android.arch.persistence.room.a.a aVar2 = (android.arch.persistence.room.a.a) sparseArrayCompat.get(i2);
            if (aVar2 != null) {
                StringBuilder sb = new StringBuilder();
                sb.append("Overriding migration ");
                sb.append(aVar2);
                sb.append(" with ");
                sb.append(aVar);
                Log.w("ROOM", sb.toString());
            }
            sparseArrayCompat.append(i2, aVar);
        }

        @Nullable
        public List<android.arch.persistence.room.a.a> a(int i, int i2) {
            if (i == i2) {
                return Collections.emptyList();
            }
            return a(new ArrayList(), i2 > i, i, i2);
        }

        private List<android.arch.persistence.room.a.a> a(List<android.arch.persistence.room.a.a> list, boolean z, int i, int i2) {
            boolean z2;
            int i3;
            int i4;
            int i5 = z ? -1 : 1;
            do {
                if (z) {
                    if (i >= i2) {
                        return list;
                    }
                } else if (i <= i2) {
                    return list;
                }
                SparseArrayCompat sparseArrayCompat = (SparseArrayCompat) this.a.get(i);
                if (sparseArrayCompat != null) {
                    int size = sparseArrayCompat.size();
                    z2 = false;
                    if (z) {
                        i4 = size - 1;
                        i3 = -1;
                    } else {
                        i3 = size;
                        i4 = 0;
                    }
                    while (true) {
                        if (i4 == i3) {
                            break;
                        }
                        int keyAt = sparseArrayCompat.keyAt(i4);
                        if (!z ? !(keyAt < i2 || keyAt >= i) : !(keyAt > i2 || keyAt <= i)) {
                            list.add(sparseArrayCompat.valueAt(i4));
                            z2 = true;
                            i = keyAt;
                            continue;
                            break;
                        }
                        i4 += i5;
                    }
                } else {
                    return null;
                }
            } while (z2);
            return null;
        }
    }

    @WorkerThread
    public abstract void clearAllTables();

    /* access modifiers changed from: protected */
    @NonNull
    public abstract b createInvalidationTracker();

    /* access modifiers changed from: protected */
    @NonNull
    public abstract android.arch.persistence.db.b createOpenHelper(a aVar);

    /* access modifiers changed from: 0000 */
    public Lock getCloseLock() {
        return this.mCloseLock;
    }

    @CallSuper
    public void init(@NonNull a aVar) {
        this.mOpenHelper = createOpenHelper(aVar);
        boolean z = false;
        if (VERSION.SDK_INT >= 16) {
            if (aVar.g == JournalMode.WRITE_AHEAD_LOGGING) {
                z = true;
            }
            this.mOpenHelper.a(z);
        }
        this.mCallbacks = aVar.e;
        this.mAllowMainThreadQueries = aVar.f;
        this.mWriteAheadLoggingEnabled = z;
    }

    @NonNull
    public android.arch.persistence.db.b getOpenHelper() {
        return this.mOpenHelper;
    }

    public boolean isOpen() {
        android.arch.persistence.db.a aVar = this.mDatabase;
        return aVar != null && aVar.e();
    }

    public void close() {
        if (isOpen()) {
            try {
                this.mCloseLock.lock();
                this.mOpenHelper.b();
            } finally {
                this.mCloseLock.unlock();
            }
        }
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public void assertNotMainThread() {
        if (!this.mAllowMainThreadQueries && ArchTaskExecutor.getInstance().isMainThread()) {
            throw new IllegalStateException("Cannot access database on the main thread since it may potentially lock the UI for a long period of time.");
        }
    }

    public Cursor query(String str, @Nullable Object[] objArr) {
        return this.mOpenHelper.a().a((d) new SimpleSQLiteQuery(str, objArr));
    }

    public Cursor query(d dVar) {
        assertNotMainThread();
        return this.mOpenHelper.a().a(dVar);
    }

    public SupportSQLiteStatement compileStatement(@NonNull String str) {
        assertNotMainThread();
        return this.mOpenHelper.a().a(str);
    }

    public void beginTransaction() {
        assertNotMainThread();
        android.arch.persistence.db.a a2 = this.mOpenHelper.a();
        this.mInvalidationTracker.b(a2);
        a2.a();
    }

    public void endTransaction() {
        this.mOpenHelper.a().b();
        if (!inTransaction()) {
            this.mInvalidationTracker.a();
        }
    }

    public void setTransactionSuccessful() {
        this.mOpenHelper.a().c();
    }

    public void runInTransaction(@NonNull Runnable runnable) {
        beginTransaction();
        try {
            runnable.run();
            setTransactionSuccessful();
        } finally {
            endTransaction();
        }
    }

    public <V> V runInTransaction(@NonNull Callable<V> callable) {
        beginTransaction();
        try {
            V call = callable.call();
            setTransactionSuccessful();
            endTransaction();
            return call;
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e2) {
            throw new RuntimeException("Exception in transaction", e2);
        } catch (Throwable th) {
            endTransaction();
            throw th;
        }
    }

    /* access modifiers changed from: protected */
    public void internalInitInvalidationTracker(@NonNull android.arch.persistence.db.a aVar) {
        this.mInvalidationTracker.a(aVar);
    }

    @NonNull
    public b getInvalidationTracker() {
        return this.mInvalidationTracker;
    }

    public boolean inTransaction() {
        return this.mOpenHelper.a().d();
    }
}
