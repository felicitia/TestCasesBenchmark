package com.google.a.a.a.a.a;

import java.io.PrintStream;
import java.io.PrintWriter;

/* compiled from: ThrowableExtension */
public final class a {
    static final C0128a a;
    static final int b;

    /* renamed from: com.google.a.a.a.a.a.a$a reason: collision with other inner class name */
    /* compiled from: ThrowableExtension */
    static abstract class C0128a {
        protected static final Throwable[] a = new Throwable[0];

        public abstract void a(Throwable th);

        public abstract void a(Throwable th, PrintStream printStream);

        public abstract void a(Throwable th, PrintWriter printWriter);

        public abstract void a(Throwable th, Throwable th2);

        C0128a() {
        }
    }

    /* compiled from: ThrowableExtension */
    static final class b extends C0128a {
        public void a(Throwable th, Throwable th2) {
        }

        b() {
        }

        public void a(Throwable th) {
            th.printStackTrace();
        }

        public void a(Throwable th, PrintStream printStream) {
            th.printStackTrace(printStream);
        }

        public void a(Throwable th, PrintWriter printWriter) {
            th.printStackTrace(printWriter);
        }
    }

    /* compiled from: ThrowableExtension */
    static final class c extends C0128a {
        c() {
        }

        public void a(Throwable th, Throwable th2) {
            th.addSuppressed(th2);
        }

        public void a(Throwable th) {
            th.printStackTrace();
        }

        public void a(Throwable th, PrintStream printStream) {
            th.printStackTrace(printStream);
        }

        public void a(Throwable th, PrintWriter printWriter) {
            th.printStackTrace(printWriter);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0059  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x005b  */
    static {
        /*
            java.lang.Integer r0 = b()     // Catch:{ Throwable -> 0x0028 }
            if (r0 == 0) goto L_0x0016
            int r1 = r0.intValue()     // Catch:{ Throwable -> 0x0014 }
            r2 = 19
            if (r1 < r2) goto L_0x0016
            com.google.a.a.a.a.a.a$c r1 = new com.google.a.a.a.a.a.a$c     // Catch:{ Throwable -> 0x0014 }
            r1.<init>()     // Catch:{ Throwable -> 0x0014 }
            goto L_0x0055
        L_0x0014:
            r1 = move-exception
            goto L_0x002a
        L_0x0016:
            boolean r1 = a()     // Catch:{ Throwable -> 0x0014 }
            if (r1 == 0) goto L_0x0022
            com.google.a.a.a.a.a.a$b r1 = new com.google.a.a.a.a.a.a$b     // Catch:{ Throwable -> 0x0014 }
            r1.<init>()     // Catch:{ Throwable -> 0x0014 }
            goto L_0x0055
        L_0x0022:
            com.google.a.a.a.a.a.a$b r1 = new com.google.a.a.a.a.a.a$b     // Catch:{ Throwable -> 0x0014 }
            r1.<init>()     // Catch:{ Throwable -> 0x0014 }
            goto L_0x0055
        L_0x0028:
            r1 = move-exception
            r0 = 0
        L_0x002a:
            java.io.PrintStream r2 = java.lang.System.err
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "An error has occured when initializing the try-with-resources desuguring strategy. The default strategy "
            r3.append(r4)
            java.lang.Class<com.google.a.a.a.a.a.a$b> r4 = com.google.a.a.a.a.a.a.b.class
            java.lang.String r4 = r4.getName()
            r3.append(r4)
            java.lang.String r4 = "will be used. The error is: "
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            r2.println(r3)
            java.io.PrintStream r2 = java.lang.System.err
            r1.printStackTrace(r2)
            com.google.a.a.a.a.a.a$b r1 = new com.google.a.a.a.a.a.a$b
            r1.<init>()
        L_0x0055:
            a = r1
            if (r0 != 0) goto L_0x005b
            r0 = 1
            goto L_0x005f
        L_0x005b:
            int r0 = r0.intValue()
        L_0x005f:
            b = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.a.a.a.a.a.a.<clinit>():void");
    }

    public static void a(Throwable th, Throwable th2) {
        a.a(th, th2);
    }

    public static void a(Throwable th) {
        a.a(th);
    }

    public static void a(Throwable th, PrintWriter printWriter) {
        a.a(th, printWriter);
    }

    public static void a(Throwable th, PrintStream printStream) {
        a.a(th, printStream);
    }

    private static boolean a() {
        return !Boolean.getBoolean("com.google.devtools.build.android.desugar.runtime.twr_disable_mimic");
    }

    private static Integer b() {
        try {
            return (Integer) Class.forName("android.os.Build$VERSION").getField("SDK_INT").get(null);
        } catch (Exception e) {
            System.err.println("Failed to retrieve value from android.os.Build$VERSION.SDK_INT due to the following exception.");
            e.printStackTrace(System.err);
            return null;
        }
    }
}
