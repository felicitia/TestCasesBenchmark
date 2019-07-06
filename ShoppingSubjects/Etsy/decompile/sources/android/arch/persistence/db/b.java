package android.arch.persistence.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.Log;
import java.io.File;

/* compiled from: SupportSQLiteOpenHelper */
public interface b {

    /* compiled from: SupportSQLiteOpenHelper */
    public static abstract class a {
        private static final String TAG = "SupportSQLite";
        public final int version;

        public void onConfigure(a aVar) {
        }

        public abstract void onCreate(a aVar);

        public void onOpen(a aVar) {
        }

        public abstract void onUpgrade(a aVar, int i, int i2);

        public a(int i) {
            this.version = i;
        }

        public void onDowngrade(a aVar, int i, int i2) {
            StringBuilder sb = new StringBuilder();
            sb.append("Can't downgrade database from version ");
            sb.append(i);
            sb.append(" to ");
            sb.append(i2);
            throw new SQLiteException(sb.toString());
        }

        /* JADX WARNING: Code restructure failed: missing block: B:11:0x0035, code lost:
            if (r0 != null) goto L_0x0037;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:12:0x0037, code lost:
            r4 = r0.iterator();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:14:0x003f, code lost:
            if (r4.hasNext() != false) goto L_0x0041;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:15:0x0041, code lost:
            deleteDatabaseFile((java.lang.String) ((android.util.Pair) r4.next()).second);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:16:0x004f, code lost:
            deleteDatabaseFile(r4.f());
         */
        /* JADX WARNING: Code restructure failed: missing block: B:17:0x0056, code lost:
            throw r1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:8:0x002f, code lost:
            r1 = move-exception;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0031 */
        /* JADX WARNING: Removed duplicated region for block: B:20:0x0059  */
        /* JADX WARNING: Removed duplicated region for block: B:24:0x0071  */
        /* JADX WARNING: Removed duplicated region for block: B:8:0x002f A[ExcHandler: all (r1v2 'th' java.lang.Throwable A[CUSTOM_DECLARE]), PHI: r0 
          PHI: (r0v9 java.util.List) = (r0v2 java.util.List), (r0v3 java.util.List), (r0v3 java.util.List) binds: [B:5:0x0029, B:9:0x0031, B:10:?] A[DONT_GENERATE, DONT_INLINE], Splitter:B:5:0x0029] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void onCorruption(android.arch.persistence.db.a r4) {
            /*
                r3 = this;
                java.lang.String r0 = "SupportSQLite"
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                r1.<init>()
                java.lang.String r2 = "Corruption reported by sqlite on database: "
                r1.append(r2)
                java.lang.String r2 = r4.f()
                r1.append(r2)
                java.lang.String r1 = r1.toString()
                android.util.Log.e(r0, r1)
                boolean r0 = r4.e()
                if (r0 != 0) goto L_0x0028
                java.lang.String r4 = r4.f()
                r3.deleteDatabaseFile(r4)
                return
            L_0x0028:
                r0 = 0
                java.util.List r1 = r4.g()     // Catch:{ SQLiteException -> 0x0031, all -> 0x002f }
                r0 = r1
                goto L_0x0031
            L_0x002f:
                r1 = move-exception
                goto L_0x0035
            L_0x0031:
                r4.close()     // Catch:{ IOException -> 0x0057, all -> 0x002f }
                goto L_0x0057
            L_0x0035:
                if (r0 == 0) goto L_0x004f
                java.util.Iterator r4 = r0.iterator()
            L_0x003b:
                boolean r0 = r4.hasNext()
                if (r0 == 0) goto L_0x0056
                java.lang.Object r0 = r4.next()
                android.util.Pair r0 = (android.util.Pair) r0
                java.lang.Object r0 = r0.second
                java.lang.String r0 = (java.lang.String) r0
                r3.deleteDatabaseFile(r0)
                goto L_0x003b
            L_0x004f:
                java.lang.String r4 = r4.f()
                r3.deleteDatabaseFile(r4)
            L_0x0056:
                throw r1
            L_0x0057:
                if (r0 == 0) goto L_0x0071
                java.util.Iterator r4 = r0.iterator()
            L_0x005d:
                boolean r0 = r4.hasNext()
                if (r0 == 0) goto L_0x0078
                java.lang.Object r0 = r4.next()
                android.util.Pair r0 = (android.util.Pair) r0
                java.lang.Object r0 = r0.second
                java.lang.String r0 = (java.lang.String) r0
                r3.deleteDatabaseFile(r0)
                goto L_0x005d
            L_0x0071:
                java.lang.String r4 = r4.f()
                r3.deleteDatabaseFile(r4)
            L_0x0078:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: android.arch.persistence.db.b.a.onCorruption(android.arch.persistence.db.a):void");
        }

        private void deleteDatabaseFile(String str) {
            if (!str.equalsIgnoreCase(":memory:") && str.trim().length() != 0) {
                String str2 = TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("deleting the database file: ");
                sb.append(str);
                Log.w(str2, sb.toString());
                try {
                    if (VERSION.SDK_INT >= 16) {
                        SQLiteDatabase.deleteDatabase(new File(str));
                    } else {
                        try {
                            if (!new File(str).delete()) {
                                String str3 = TAG;
                                StringBuilder sb2 = new StringBuilder();
                                sb2.append("Could not delete the database file ");
                                sb2.append(str);
                                Log.e(str3, sb2.toString());
                            }
                        } catch (Exception e) {
                            Log.e(TAG, "error while deleting corrupted database file", e);
                        }
                    }
                } catch (Exception e2) {
                    Log.w(TAG, "delete failed: ", e2);
                }
            }
        }
    }

    /* renamed from: android.arch.persistence.db.b$b reason: collision with other inner class name */
    /* compiled from: SupportSQLiteOpenHelper */
    public static class C0002b {
        @NonNull
        public final Context a;
        @Nullable
        public final String b;
        @NonNull
        public final a c;

        /* renamed from: android.arch.persistence.db.b$b$a */
        /* compiled from: SupportSQLiteOpenHelper */
        public static class a {
            Context a;
            String b;
            a c;

            public C0002b a() {
                if (this.c == null) {
                    throw new IllegalArgumentException("Must set a callback to create the configuration.");
                } else if (this.a != null) {
                    return new C0002b(this.a, this.b, this.c);
                } else {
                    throw new IllegalArgumentException("Must set a non-null context to create the configuration.");
                }
            }

            a(@NonNull Context context) {
                this.a = context;
            }

            public a a(@Nullable String str) {
                this.b = str;
                return this;
            }

            public a a(@NonNull a aVar) {
                this.c = aVar;
                return this;
            }
        }

        C0002b(@NonNull Context context, @Nullable String str, @NonNull a aVar) {
            this.a = context;
            this.b = str;
            this.c = aVar;
        }

        public static a a(Context context) {
            return new a(context);
        }
    }

    /* compiled from: SupportSQLiteOpenHelper */
    public interface c {
        b create(C0002b bVar);
    }

    a a();

    @RequiresApi(api = 16)
    void a(boolean z);

    void b();
}
