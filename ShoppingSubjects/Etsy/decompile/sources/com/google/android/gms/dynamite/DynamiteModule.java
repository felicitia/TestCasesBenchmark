package com.google.android.gms.dynamite;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.DynamiteApi;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamite.IDynamiteLoader.Stub;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public final class DynamiteModule {
    public static final a a = new b();
    public static final a b = new c();
    public static final a c = new d();
    public static final a d = new e();
    public static final a e = new f();
    public static final a f = new g();
    private static Boolean g;
    private static IDynamiteLoader h;
    private static IDynamiteLoaderV2 i;
    private static String j;
    private static final ThreadLocal<b> k = new ThreadLocal<>();
    private static final C0138a l = new a();
    private final Context m;

    @DynamiteApi
    public static class DynamiteLoaderClassLoader {
        public static ClassLoader sClassLoader;
    }

    public static class LoadingException extends Exception {
        private LoadingException(String str) {
            super(str);
        }

        /* synthetic */ LoadingException(String str, a aVar) {
            this(str);
        }

        private LoadingException(String str, Throwable th) {
            super(str, th);
        }

        /* synthetic */ LoadingException(String str, Throwable th, a aVar) {
            this(str, th);
        }
    }

    public interface a {

        /* renamed from: com.google.android.gms.dynamite.DynamiteModule$a$a reason: collision with other inner class name */
        public interface C0138a {
            int a(Context context, String str);

            int a(Context context, String str, boolean z) throws LoadingException;
        }

        public static class b {
            public int a = 0;
            public int b = 0;
            public int c = 0;
        }

        b a(Context context, String str, C0138a aVar) throws LoadingException;
    }

    private static class b {
        public Cursor a;

        private b() {
        }

        /* synthetic */ b(a aVar) {
            this();
        }
    }

    private static class c implements C0138a {
        private final int a;
        private final int b = 0;

        public c(int i, int i2) {
            this.a = i;
        }

        public final int a(Context context, String str) {
            return this.a;
        }

        public final int a(Context context, String str, boolean z) {
            return 0;
        }
    }

    private DynamiteModule(Context context) {
        this.m = (Context) Preconditions.checkNotNull(context);
    }

    public static int a(Context context, String str) {
        try {
            ClassLoader classLoader = context.getApplicationContext().getClassLoader();
            StringBuilder sb = new StringBuilder(61 + String.valueOf(str).length());
            sb.append("com.google.android.gms.dynamite.descriptors.");
            sb.append(str);
            sb.append(".ModuleDescriptor");
            Class loadClass = classLoader.loadClass(sb.toString());
            Field declaredField = loadClass.getDeclaredField("MODULE_ID");
            Field declaredField2 = loadClass.getDeclaredField("MODULE_VERSION");
            if (declaredField.get(null).equals(str)) {
                return declaredField2.getInt(null);
            }
            String valueOf = String.valueOf(declaredField.get(null));
            StringBuilder sb2 = new StringBuilder(51 + String.valueOf(valueOf).length() + String.valueOf(str).length());
            sb2.append("Module descriptor id '");
            sb2.append(valueOf);
            sb2.append("' didn't match expected id '");
            sb2.append(str);
            sb2.append("'");
            Log.e("DynamiteModule", sb2.toString());
            return 0;
        } catch (ClassNotFoundException unused) {
            StringBuilder sb3 = new StringBuilder(45 + String.valueOf(str).length());
            sb3.append("Local module descriptor class for ");
            sb3.append(str);
            sb3.append(" not found.");
            Log.w("DynamiteModule", sb3.toString());
            return 0;
        } catch (Exception e2) {
            String str2 = "DynamiteModule";
            String str3 = "Failed to load module descriptor class: ";
            String valueOf2 = String.valueOf(e2.getMessage());
            Log.e(str2, valueOf2.length() != 0 ? str3.concat(valueOf2) : new String(str3));
            return 0;
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(2:39|40) */
    /* JADX WARNING: Can't wrap try/catch for region: R(4:15|16|17|18) */
    /* JADX WARNING: Code restructure failed: missing block: B:40:?, code lost:
        r2.set(null, java.lang.ClassLoader.getSystemClassLoader());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x0085, code lost:
        r1 = r2;
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0035 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:39:0x007c */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x00bb A[SYNTHETIC, Splitter:B:55:0x00bb] */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x00e2  */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:22:0x0050=Splitter:B:22:0x0050, B:17:0x0035=Splitter:B:17:0x0035, B:34:0x0079=Splitter:B:34:0x0079} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int a(android.content.Context r8, java.lang.String r9, boolean r10) {
        /*
            java.lang.Class<com.google.android.gms.dynamite.DynamiteModule> r0 = com.google.android.gms.dynamite.DynamiteModule.class
            monitor-enter(r0)
            java.lang.Boolean r1 = g     // Catch:{ all -> 0x00e7 }
            if (r1 != 0) goto L_0x00b4
            android.content.Context r1 = r8.getApplicationContext()     // Catch:{ ClassNotFoundException | IllegalAccessException | NoSuchFieldException -> 0x008a }
            java.lang.ClassLoader r1 = r1.getClassLoader()     // Catch:{ ClassNotFoundException | IllegalAccessException | NoSuchFieldException -> 0x008a }
            java.lang.Class<com.google.android.gms.dynamite.DynamiteModule$DynamiteLoaderClassLoader> r2 = com.google.android.gms.dynamite.DynamiteModule.DynamiteLoaderClassLoader.class
            java.lang.String r2 = r2.getName()     // Catch:{ ClassNotFoundException | IllegalAccessException | NoSuchFieldException -> 0x008a }
            java.lang.Class r1 = r1.loadClass(r2)     // Catch:{ ClassNotFoundException | IllegalAccessException | NoSuchFieldException -> 0x008a }
            java.lang.String r2 = "sClassLoader"
            java.lang.reflect.Field r2 = r1.getDeclaredField(r2)     // Catch:{ ClassNotFoundException | IllegalAccessException | NoSuchFieldException -> 0x008a }
            monitor-enter(r1)     // Catch:{ ClassNotFoundException | IllegalAccessException | NoSuchFieldException -> 0x008a }
            r3 = 0
            java.lang.Object r4 = r2.get(r3)     // Catch:{ all -> 0x0087 }
            java.lang.ClassLoader r4 = (java.lang.ClassLoader) r4     // Catch:{ all -> 0x0087 }
            if (r4 == 0) goto L_0x0038
            java.lang.ClassLoader r2 = java.lang.ClassLoader.getSystemClassLoader()     // Catch:{ all -> 0x0087 }
            if (r4 != r2) goto L_0x0032
        L_0x002f:
            java.lang.Boolean r2 = java.lang.Boolean.FALSE     // Catch:{ all -> 0x0087 }
            goto L_0x0084
        L_0x0032:
            a(r4)     // Catch:{ LoadingException -> 0x0035 }
        L_0x0035:
            java.lang.Boolean r2 = java.lang.Boolean.TRUE     // Catch:{ all -> 0x0087 }
            goto L_0x0084
        L_0x0038:
            java.lang.String r4 = "com.google.android.gms"
            android.content.Context r5 = r8.getApplicationContext()     // Catch:{ all -> 0x0087 }
            java.lang.String r5 = r5.getPackageName()     // Catch:{ all -> 0x0087 }
            boolean r4 = r4.equals(r5)     // Catch:{ all -> 0x0087 }
            if (r4 == 0) goto L_0x0050
            java.lang.ClassLoader r4 = java.lang.ClassLoader.getSystemClassLoader()     // Catch:{ all -> 0x0087 }
            r2.set(r3, r4)     // Catch:{ all -> 0x0087 }
            goto L_0x002f
        L_0x0050:
            int r4 = d(r8, r9, r10)     // Catch:{ LoadingException -> 0x007c }
            java.lang.String r5 = j     // Catch:{ LoadingException -> 0x007c }
            if (r5 == 0) goto L_0x0079
            java.lang.String r5 = j     // Catch:{ LoadingException -> 0x007c }
            boolean r5 = r5.isEmpty()     // Catch:{ LoadingException -> 0x007c }
            if (r5 == 0) goto L_0x0061
            goto L_0x0079
        L_0x0061:
            com.google.android.gms.dynamite.h r5 = new com.google.android.gms.dynamite.h     // Catch:{ LoadingException -> 0x007c }
            java.lang.String r6 = j     // Catch:{ LoadingException -> 0x007c }
            java.lang.ClassLoader r7 = java.lang.ClassLoader.getSystemClassLoader()     // Catch:{ LoadingException -> 0x007c }
            r5.<init>(r6, r7)     // Catch:{ LoadingException -> 0x007c }
            a(r5)     // Catch:{ LoadingException -> 0x007c }
            r2.set(r3, r5)     // Catch:{ LoadingException -> 0x007c }
            java.lang.Boolean r5 = java.lang.Boolean.TRUE     // Catch:{ LoadingException -> 0x007c }
            g = r5     // Catch:{ LoadingException -> 0x007c }
            monitor-exit(r1)     // Catch:{ all -> 0x0087 }
            monitor-exit(r0)     // Catch:{ all -> 0x00e7 }
            return r4
        L_0x0079:
            monitor-exit(r1)     // Catch:{ all -> 0x0087 }
            monitor-exit(r0)     // Catch:{ all -> 0x00e7 }
            return r4
        L_0x007c:
            java.lang.ClassLoader r4 = java.lang.ClassLoader.getSystemClassLoader()     // Catch:{ all -> 0x0087 }
            r2.set(r3, r4)     // Catch:{ all -> 0x0087 }
            goto L_0x002f
        L_0x0084:
            monitor-exit(r1)     // Catch:{ all -> 0x0087 }
            r1 = r2
            goto L_0x00b2
        L_0x0087:
            r2 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0087 }
            throw r2     // Catch:{ ClassNotFoundException | IllegalAccessException | NoSuchFieldException -> 0x008a }
        L_0x008a:
            r1 = move-exception
            java.lang.String r2 = "DynamiteModule"
            java.lang.String r1 = java.lang.String.valueOf(r1)     // Catch:{ all -> 0x00e7 }
            r3 = 30
            java.lang.String r4 = java.lang.String.valueOf(r1)     // Catch:{ all -> 0x00e7 }
            int r4 = r4.length()     // Catch:{ all -> 0x00e7 }
            int r3 = r3 + r4
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x00e7 }
            r4.<init>(r3)     // Catch:{ all -> 0x00e7 }
            java.lang.String r3 = "Failed to load module via V2: "
            r4.append(r3)     // Catch:{ all -> 0x00e7 }
            r4.append(r1)     // Catch:{ all -> 0x00e7 }
            java.lang.String r1 = r4.toString()     // Catch:{ all -> 0x00e7 }
            android.util.Log.w(r2, r1)     // Catch:{ all -> 0x00e7 }
            java.lang.Boolean r1 = java.lang.Boolean.FALSE     // Catch:{ all -> 0x00e7 }
        L_0x00b2:
            g = r1     // Catch:{ all -> 0x00e7 }
        L_0x00b4:
            monitor-exit(r0)     // Catch:{ all -> 0x00e7 }
            boolean r0 = r1.booleanValue()
            if (r0 == 0) goto L_0x00e2
            int r8 = d(r8, r9, r10)     // Catch:{ LoadingException -> 0x00c0 }
            return r8
        L_0x00c0:
            r8 = move-exception
            java.lang.String r9 = "DynamiteModule"
            java.lang.String r10 = "Failed to retrieve remote module version: "
            java.lang.String r8 = r8.getMessage()
            java.lang.String r8 = java.lang.String.valueOf(r8)
            int r0 = r8.length()
            if (r0 == 0) goto L_0x00d8
            java.lang.String r8 = r10.concat(r8)
            goto L_0x00dd
        L_0x00d8:
            java.lang.String r8 = new java.lang.String
            r8.<init>(r10)
        L_0x00dd:
            android.util.Log.w(r9, r8)
            r8 = 0
            return r8
        L_0x00e2:
            int r8 = c(r8, r9, r10)
            return r8
        L_0x00e7:
            r8 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x00e7 }
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.dynamite.DynamiteModule.a(android.content.Context, java.lang.String, boolean):int");
    }

    private static Context a(Context context, String str, int i2, Cursor cursor, IDynamiteLoaderV2 iDynamiteLoaderV2) {
        try {
            return (Context) ObjectWrapper.unwrap(iDynamiteLoaderV2.loadModule2(ObjectWrapper.wrap(context), str, i2, ObjectWrapper.wrap(cursor)));
        } catch (Exception e2) {
            String str2 = "DynamiteModule";
            String str3 = "Failed to load DynamiteLoader: ";
            String valueOf = String.valueOf(e2.toString());
            Log.e(str2, valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
            return null;
        }
    }

    public static Uri a(String str, boolean z) {
        String str2 = z ? "api_force_staging" : "api";
        StringBuilder sb = new StringBuilder(42 + String.valueOf(str2).length() + String.valueOf(str).length());
        sb.append("content://com.google.android.gms.chimera/");
        sb.append(str2);
        sb.append("/");
        sb.append(str);
        return Uri.parse(sb.toString());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0081, code lost:
        if (r1.a != null) goto L_0x0083;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00e2, code lost:
        if (r1.a != null) goto L_0x0083;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.android.gms.dynamite.DynamiteModule a(android.content.Context r10, com.google.android.gms.dynamite.DynamiteModule.a r11, java.lang.String r12) throws com.google.android.gms.dynamite.DynamiteModule.LoadingException {
        /*
            java.lang.ThreadLocal<com.google.android.gms.dynamite.DynamiteModule$b> r0 = k
            java.lang.Object r0 = r0.get()
            com.google.android.gms.dynamite.DynamiteModule$b r0 = (com.google.android.gms.dynamite.DynamiteModule.b) r0
            com.google.android.gms.dynamite.DynamiteModule$b r1 = new com.google.android.gms.dynamite.DynamiteModule$b
            r2 = 0
            r1.<init>(r2)
            java.lang.ThreadLocal<com.google.android.gms.dynamite.DynamiteModule$b> r3 = k
            r3.set(r1)
            com.google.android.gms.dynamite.DynamiteModule$a$a r3 = l     // Catch:{ all -> 0x0132 }
            com.google.android.gms.dynamite.DynamiteModule$a$b r3 = r11.a(r10, r12, r3)     // Catch:{ all -> 0x0132 }
            java.lang.String r4 = "DynamiteModule"
            int r5 = r3.a     // Catch:{ all -> 0x0132 }
            int r6 = r3.b     // Catch:{ all -> 0x0132 }
            r7 = 68
            java.lang.String r8 = java.lang.String.valueOf(r12)     // Catch:{ all -> 0x0132 }
            int r8 = r8.length()     // Catch:{ all -> 0x0132 }
            int r7 = r7 + r8
            java.lang.String r8 = java.lang.String.valueOf(r12)     // Catch:{ all -> 0x0132 }
            int r8 = r8.length()     // Catch:{ all -> 0x0132 }
            int r7 = r7 + r8
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ all -> 0x0132 }
            r8.<init>(r7)     // Catch:{ all -> 0x0132 }
            java.lang.String r7 = "Considering local module "
            r8.append(r7)     // Catch:{ all -> 0x0132 }
            r8.append(r12)     // Catch:{ all -> 0x0132 }
            java.lang.String r7 = ":"
            r8.append(r7)     // Catch:{ all -> 0x0132 }
            r8.append(r5)     // Catch:{ all -> 0x0132 }
            java.lang.String r5 = " and remote module "
            r8.append(r5)     // Catch:{ all -> 0x0132 }
            r8.append(r12)     // Catch:{ all -> 0x0132 }
            java.lang.String r5 = ":"
            r8.append(r5)     // Catch:{ all -> 0x0132 }
            r8.append(r6)     // Catch:{ all -> 0x0132 }
            java.lang.String r5 = r8.toString()     // Catch:{ all -> 0x0132 }
            android.util.Log.i(r4, r5)     // Catch:{ all -> 0x0132 }
            int r4 = r3.c     // Catch:{ all -> 0x0132 }
            if (r4 == 0) goto L_0x0108
            int r4 = r3.c     // Catch:{ all -> 0x0132 }
            r5 = -1
            if (r4 != r5) goto L_0x006c
            int r4 = r3.a     // Catch:{ all -> 0x0132 }
            if (r4 == 0) goto L_0x0108
        L_0x006c:
            int r4 = r3.c     // Catch:{ all -> 0x0132 }
            r6 = 1
            if (r4 != r6) goto L_0x0077
            int r4 = r3.b     // Catch:{ all -> 0x0132 }
            if (r4 != 0) goto L_0x0077
            goto L_0x0108
        L_0x0077:
            int r4 = r3.c     // Catch:{ all -> 0x0132 }
            if (r4 != r5) goto L_0x008e
            com.google.android.gms.dynamite.DynamiteModule r10 = c(r10, r12)     // Catch:{ all -> 0x0132 }
            android.database.Cursor r11 = r1.a
            if (r11 == 0) goto L_0x0088
        L_0x0083:
            android.database.Cursor r11 = r1.a
            r11.close()
        L_0x0088:
            java.lang.ThreadLocal<com.google.android.gms.dynamite.DynamiteModule$b> r11 = k
            r11.set(r0)
            return r10
        L_0x008e:
            int r4 = r3.c     // Catch:{ all -> 0x0132 }
            if (r4 != r6) goto L_0x00ed
            int r4 = r3.b     // Catch:{ LoadingException -> 0x00a7 }
            com.google.android.gms.dynamite.DynamiteModule r4 = a(r10, r12, r4)     // Catch:{ LoadingException -> 0x00a7 }
            android.database.Cursor r10 = r1.a
            if (r10 == 0) goto L_0x00a1
            android.database.Cursor r10 = r1.a
            r10.close()
        L_0x00a1:
            java.lang.ThreadLocal<com.google.android.gms.dynamite.DynamiteModule$b> r10 = k
            r10.set(r0)
            return r4
        L_0x00a7:
            r4 = move-exception
            java.lang.String r6 = "DynamiteModule"
            java.lang.String r7 = "Failed to load remote module: "
            java.lang.String r8 = r4.getMessage()     // Catch:{ all -> 0x0132 }
            java.lang.String r8 = java.lang.String.valueOf(r8)     // Catch:{ all -> 0x0132 }
            int r9 = r8.length()     // Catch:{ all -> 0x0132 }
            if (r9 == 0) goto L_0x00bf
            java.lang.String r7 = r7.concat(r8)     // Catch:{ all -> 0x0132 }
            goto L_0x00c5
        L_0x00bf:
            java.lang.String r8 = new java.lang.String     // Catch:{ all -> 0x0132 }
            r8.<init>(r7)     // Catch:{ all -> 0x0132 }
            r7 = r8
        L_0x00c5:
            android.util.Log.w(r6, r7)     // Catch:{ all -> 0x0132 }
            int r6 = r3.a     // Catch:{ all -> 0x0132 }
            if (r6 == 0) goto L_0x00e5
            com.google.android.gms.dynamite.DynamiteModule$c r6 = new com.google.android.gms.dynamite.DynamiteModule$c     // Catch:{ all -> 0x0132 }
            int r3 = r3.a     // Catch:{ all -> 0x0132 }
            r7 = 0
            r6.<init>(r3, r7)     // Catch:{ all -> 0x0132 }
            com.google.android.gms.dynamite.DynamiteModule$a$b r11 = r11.a(r10, r12, r6)     // Catch:{ all -> 0x0132 }
            int r11 = r11.c     // Catch:{ all -> 0x0132 }
            if (r11 != r5) goto L_0x00e5
            com.google.android.gms.dynamite.DynamiteModule r10 = c(r10, r12)     // Catch:{ all -> 0x0132 }
            android.database.Cursor r11 = r1.a
            if (r11 == 0) goto L_0x0088
            goto L_0x0083
        L_0x00e5:
            com.google.android.gms.dynamite.DynamiteModule$LoadingException r10 = new com.google.android.gms.dynamite.DynamiteModule$LoadingException     // Catch:{ all -> 0x0132 }
            java.lang.String r11 = "Remote load failed. No local fallback found."
            r10.<init>(r11, r4, r2)     // Catch:{ all -> 0x0132 }
            throw r10     // Catch:{ all -> 0x0132 }
        L_0x00ed:
            com.google.android.gms.dynamite.DynamiteModule$LoadingException r10 = new com.google.android.gms.dynamite.DynamiteModule$LoadingException     // Catch:{ all -> 0x0132 }
            int r11 = r3.c     // Catch:{ all -> 0x0132 }
            r12 = 47
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0132 }
            r3.<init>(r12)     // Catch:{ all -> 0x0132 }
            java.lang.String r12 = "VersionPolicy returned invalid code:"
            r3.append(r12)     // Catch:{ all -> 0x0132 }
            r3.append(r11)     // Catch:{ all -> 0x0132 }
            java.lang.String r11 = r3.toString()     // Catch:{ all -> 0x0132 }
            r10.<init>(r11, r2)     // Catch:{ all -> 0x0132 }
            throw r10     // Catch:{ all -> 0x0132 }
        L_0x0108:
            com.google.android.gms.dynamite.DynamiteModule$LoadingException r10 = new com.google.android.gms.dynamite.DynamiteModule$LoadingException     // Catch:{ all -> 0x0132 }
            int r11 = r3.a     // Catch:{ all -> 0x0132 }
            int r12 = r3.b     // Catch:{ all -> 0x0132 }
            r3 = 91
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0132 }
            r4.<init>(r3)     // Catch:{ all -> 0x0132 }
            java.lang.String r3 = "No acceptable module found. Local version is "
            r4.append(r3)     // Catch:{ all -> 0x0132 }
            r4.append(r11)     // Catch:{ all -> 0x0132 }
            java.lang.String r11 = " and remote version is "
            r4.append(r11)     // Catch:{ all -> 0x0132 }
            r4.append(r12)     // Catch:{ all -> 0x0132 }
            java.lang.String r11 = "."
            r4.append(r11)     // Catch:{ all -> 0x0132 }
            java.lang.String r11 = r4.toString()     // Catch:{ all -> 0x0132 }
            r10.<init>(r11, r2)     // Catch:{ all -> 0x0132 }
            throw r10     // Catch:{ all -> 0x0132 }
        L_0x0132:
            r10 = move-exception
            android.database.Cursor r11 = r1.a
            if (r11 == 0) goto L_0x013c
            android.database.Cursor r11 = r1.a
            r11.close()
        L_0x013c:
            java.lang.ThreadLocal<com.google.android.gms.dynamite.DynamiteModule$b> r11 = k
            r11.set(r0)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.dynamite.DynamiteModule.a(android.content.Context, com.google.android.gms.dynamite.DynamiteModule$a, java.lang.String):com.google.android.gms.dynamite.DynamiteModule");
    }

    private static DynamiteModule a(Context context, String str, int i2) throws LoadingException {
        Boolean bool;
        synchronized (DynamiteModule.class) {
            bool = g;
        }
        if (bool != null) {
            return bool.booleanValue() ? c(context, str, i2) : b(context, str, i2);
        }
        throw new LoadingException("Failed to determine which loading route to use.", (a) null);
    }

    private static IDynamiteLoader a(Context context) {
        synchronized (DynamiteModule.class) {
            if (h != null) {
                IDynamiteLoader iDynamiteLoader = h;
                return iDynamiteLoader;
            } else if (GoogleApiAvailabilityLight.getInstance().isGooglePlayServicesAvailable(context) != 0) {
                return null;
            } else {
                try {
                    IDynamiteLoader asInterface = Stub.asInterface((IBinder) context.createPackageContext("com.google.android.gms", 3).getClassLoader().loadClass("com.google.android.gms.chimera.container.DynamiteLoaderImpl").newInstance());
                    if (asInterface != null) {
                        h = asInterface;
                        return asInterface;
                    }
                } catch (Exception e2) {
                    String str = "DynamiteModule";
                    String str2 = "Failed to load IDynamiteLoader from GmsCore: ";
                    String valueOf = String.valueOf(e2.getMessage());
                    Log.e(str, valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
                }
            }
        }
        return null;
    }

    private static void a(ClassLoader classLoader) throws LoadingException {
        try {
            i = IDynamiteLoaderV2.Stub.asInterface((IBinder) classLoader.loadClass("com.google.android.gms.dynamiteloader.DynamiteLoaderV2").getConstructor(new Class[0]).newInstance(new Object[0]));
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e2) {
            throw new LoadingException("Failed to instantiate dynamite loader", e2, null);
        }
    }

    public static int b(Context context, String str) {
        return a(context, str, false);
    }

    public static Cursor b(Context context, String str, boolean z) {
        return context.getContentResolver().query(a(str, z), null, null, null, null);
    }

    private static DynamiteModule b(Context context, String str, int i2) throws LoadingException {
        StringBuilder sb = new StringBuilder(51 + String.valueOf(str).length());
        sb.append("Selected remote version of ");
        sb.append(str);
        sb.append(", version >= ");
        sb.append(i2);
        Log.i("DynamiteModule", sb.toString());
        IDynamiteLoader a2 = a(context);
        if (a2 == null) {
            throw new LoadingException("Failed to create IDynamiteLoader.", (a) null);
        }
        try {
            IObjectWrapper createModuleContext = a2.createModuleContext(ObjectWrapper.wrap(context), str, i2);
            if (ObjectWrapper.unwrap(createModuleContext) != null) {
                return new DynamiteModule((Context) ObjectWrapper.unwrap(createModuleContext));
            }
            throw new LoadingException("Failed to load remote module.", (a) null);
        } catch (RemoteException e2) {
            throw new LoadingException("Failed to load remote module.", e2, null);
        }
    }

    private static int c(Context context, String str, boolean z) {
        IDynamiteLoader a2 = a(context);
        if (a2 == null) {
            return 0;
        }
        try {
            return a2.getModuleVersion2(ObjectWrapper.wrap(context), str, z);
        } catch (RemoteException e2) {
            String str2 = "DynamiteModule";
            String str3 = "Failed to retrieve remote module version: ";
            String valueOf = String.valueOf(e2.getMessage());
            Log.w(str2, valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
            return 0;
        }
    }

    private static DynamiteModule c(Context context, String str) {
        String str2 = "DynamiteModule";
        String str3 = "Selected local version of ";
        String valueOf = String.valueOf(str);
        Log.i(str2, valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
        return new DynamiteModule(context.getApplicationContext());
    }

    private static DynamiteModule c(Context context, String str, int i2) throws LoadingException {
        IDynamiteLoaderV2 iDynamiteLoaderV2;
        StringBuilder sb = new StringBuilder(51 + String.valueOf(str).length());
        sb.append("Selected remote version of ");
        sb.append(str);
        sb.append(", version >= ");
        sb.append(i2);
        Log.i("DynamiteModule", sb.toString());
        synchronized (DynamiteModule.class) {
            iDynamiteLoaderV2 = i;
        }
        if (iDynamiteLoaderV2 == null) {
            throw new LoadingException("DynamiteLoaderV2 was not cached.", (a) null);
        }
        b bVar = (b) k.get();
        if (bVar == null || bVar.a == null) {
            throw new LoadingException("No result cursor", (a) null);
        }
        Context a2 = a(context.getApplicationContext(), str, i2, bVar.a, iDynamiteLoaderV2);
        if (a2 != null) {
            return new DynamiteModule(a2);
        }
        throw new LoadingException("Failed to get module context", (a) null);
    }

    /* JADX WARNING: Removed duplicated region for block: B:48:0x0061  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int d(android.content.Context r2, java.lang.String r3, boolean r4) throws com.google.android.gms.dynamite.DynamiteModule.LoadingException {
        /*
            r0 = 0
            android.database.Cursor r2 = b(r2, r3, r4)     // Catch:{ Exception -> 0x004f, all -> 0x004c }
            if (r2 == 0) goto L_0x003d
            boolean r3 = r2.moveToFirst()     // Catch:{ Exception -> 0x003b }
            if (r3 != 0) goto L_0x000e
            goto L_0x003d
        L_0x000e:
            r3 = 0
            int r3 = r2.getInt(r3)     // Catch:{ Exception -> 0x003b }
            if (r3 <= 0) goto L_0x0035
            java.lang.Class<com.google.android.gms.dynamite.DynamiteModule> r4 = com.google.android.gms.dynamite.DynamiteModule.class
            monitor-enter(r4)     // Catch:{ Exception -> 0x003b }
            r1 = 2
            java.lang.String r1 = r2.getString(r1)     // Catch:{ all -> 0x0032 }
            j = r1     // Catch:{ all -> 0x0032 }
            monitor-exit(r4)     // Catch:{ all -> 0x0032 }
            java.lang.ThreadLocal<com.google.android.gms.dynamite.DynamiteModule$b> r4 = k     // Catch:{ Exception -> 0x003b }
            java.lang.Object r4 = r4.get()     // Catch:{ Exception -> 0x003b }
            com.google.android.gms.dynamite.DynamiteModule$b r4 = (com.google.android.gms.dynamite.DynamiteModule.b) r4     // Catch:{ Exception -> 0x003b }
            if (r4 == 0) goto L_0x0035
            android.database.Cursor r1 = r4.a     // Catch:{ Exception -> 0x003b }
            if (r1 != 0) goto L_0x0035
            r4.a = r2     // Catch:{ Exception -> 0x003b }
            r2 = r0
            goto L_0x0035
        L_0x0032:
            r3 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x0032 }
            throw r3     // Catch:{ Exception -> 0x003b }
        L_0x0035:
            if (r2 == 0) goto L_0x003a
            r2.close()
        L_0x003a:
            return r3
        L_0x003b:
            r3 = move-exception
            goto L_0x0051
        L_0x003d:
            java.lang.String r3 = "DynamiteModule"
            java.lang.String r4 = "Failed to retrieve remote module version."
            android.util.Log.w(r3, r4)     // Catch:{ Exception -> 0x003b }
            com.google.android.gms.dynamite.DynamiteModule$LoadingException r3 = new com.google.android.gms.dynamite.DynamiteModule$LoadingException     // Catch:{ Exception -> 0x003b }
            java.lang.String r4 = "Failed to connect to dynamite module ContentResolver."
            r3.<init>(r4, r0)     // Catch:{ Exception -> 0x003b }
            throw r3     // Catch:{ Exception -> 0x003b }
        L_0x004c:
            r3 = move-exception
            r2 = r0
            goto L_0x005f
        L_0x004f:
            r3 = move-exception
            r2 = r0
        L_0x0051:
            boolean r4 = r3 instanceof com.google.android.gms.dynamite.DynamiteModule.LoadingException     // Catch:{ all -> 0x005e }
            if (r4 == 0) goto L_0x0056
            throw r3     // Catch:{ all -> 0x005e }
        L_0x0056:
            com.google.android.gms.dynamite.DynamiteModule$LoadingException r4 = new com.google.android.gms.dynamite.DynamiteModule$LoadingException     // Catch:{ all -> 0x005e }
            java.lang.String r1 = "V2 version check failed"
            r4.<init>(r1, r3, r0)     // Catch:{ all -> 0x005e }
            throw r4     // Catch:{ all -> 0x005e }
        L_0x005e:
            r3 = move-exception
        L_0x005f:
            if (r2 == 0) goto L_0x0064
            r2.close()
        L_0x0064:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.dynamite.DynamiteModule.d(android.content.Context, java.lang.String, boolean):int");
    }

    public final Context a() {
        return this.m;
    }

    public final IBinder a(String str) throws LoadingException {
        try {
            return (IBinder) this.m.getClassLoader().loadClass(str).newInstance();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e2) {
            String str2 = "Failed to instantiate module class: ";
            String valueOf = String.valueOf(str);
            throw new LoadingException(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2), e2, null);
        }
    }
}
