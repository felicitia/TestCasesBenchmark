package com.bumptech.glide.a;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.ClassUtils;

/* compiled from: DiskLruCache */
public final class a implements Closeable {
    final ThreadPoolExecutor a;
    /* access modifiers changed from: private */
    public final File b;
    private final File c;
    private final File d;
    private final File e;
    private final int f;
    private long g;
    /* access modifiers changed from: private */
    public final int h;
    private long i = 0;
    /* access modifiers changed from: private */
    public Writer j;
    private final LinkedHashMap<String, b> k = new LinkedHashMap<>(0, 0.75f, true);
    /* access modifiers changed from: private */
    public int l;
    private long m = 0;
    private final Callable<Void> n;

    /* renamed from: com.bumptech.glide.a.a$a reason: collision with other inner class name */
    /* compiled from: DiskLruCache */
    public final class C0007a {
        /* access modifiers changed from: private */
        public final b b;
        /* access modifiers changed from: private */
        public final boolean[] c;
        private boolean d;

        private C0007a(b bVar) {
            this.b = bVar;
            this.c = bVar.f ? null : new boolean[a.this.h];
        }

        public File a(int i) throws IOException {
            File b2;
            synchronized (a.this) {
                if (this.b.g != this) {
                    throw new IllegalStateException();
                }
                if (!this.b.f) {
                    this.c[i] = true;
                }
                b2 = this.b.b(i);
                if (!a.this.b.exists()) {
                    a.this.b.mkdirs();
                }
            }
            return b2;
        }

        public void a() throws IOException {
            a.this.a(this, true);
            this.d = true;
        }

        public void b() throws IOException {
            a.this.a(this, false);
        }

        public void c() {
            if (!this.d) {
                try {
                    b();
                } catch (IOException unused) {
                }
            }
        }
    }

    /* compiled from: DiskLruCache */
    private final class b {
        File[] a;
        File[] b;
        /* access modifiers changed from: private */
        public final String d;
        /* access modifiers changed from: private */
        public final long[] e;
        /* access modifiers changed from: private */
        public boolean f;
        /* access modifiers changed from: private */
        public C0007a g;
        /* access modifiers changed from: private */
        public long h;

        private b(String str) {
            this.d = str;
            this.e = new long[a.this.h];
            this.a = new File[a.this.h];
            this.b = new File[a.this.h];
            StringBuilder sb = new StringBuilder(str);
            sb.append(ClassUtils.PACKAGE_SEPARATOR_CHAR);
            int length = sb.length();
            for (int i = 0; i < a.this.h; i++) {
                sb.append(i);
                this.a[i] = new File(a.this.b, sb.toString());
                sb.append(".tmp");
                this.b[i] = new File(a.this.b, sb.toString());
                sb.setLength(length);
            }
        }

        public String a() throws IOException {
            long[] jArr;
            StringBuilder sb = new StringBuilder();
            for (long j : this.e) {
                sb.append(' ');
                sb.append(j);
            }
            return sb.toString();
        }

        /* access modifiers changed from: private */
        public void a(String[] strArr) throws IOException {
            if (strArr.length != a.this.h) {
                throw b(strArr);
            }
            int i = 0;
            while (i < strArr.length) {
                try {
                    this.e[i] = Long.parseLong(strArr[i]);
                    i++;
                } catch (NumberFormatException unused) {
                    throw b(strArr);
                }
            }
        }

        private IOException b(String[] strArr) throws IOException {
            StringBuilder sb = new StringBuilder();
            sb.append("unexpected journal line: ");
            sb.append(Arrays.toString(strArr));
            throw new IOException(sb.toString());
        }

        public File a(int i) {
            return this.a[i];
        }

        public File b(int i) {
            return this.b[i];
        }
    }

    /* compiled from: DiskLruCache */
    public final class c {
        private final String b;
        private final long c;
        private final long[] d;
        private final File[] e;

        private c(String str, long j, File[] fileArr, long[] jArr) {
            this.b = str;
            this.c = j;
            this.e = fileArr;
            this.d = jArr;
        }

        public File a(int i) {
            return this.e[i];
        }
    }

    private a(File file, int i2, int i3, long j2) {
        File file2 = file;
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue());
        this.a = threadPoolExecutor;
        this.n = new Callable<Void>() {
            /* JADX WARNING: Code restructure failed: missing block: B:11:0x0027, code lost:
                return null;
             */
            /* renamed from: a */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public java.lang.Void call() throws java.lang.Exception {
                /*
                    r4 = this;
                    com.bumptech.glide.a.a r0 = com.bumptech.glide.a.a.this
                    monitor-enter(r0)
                    com.bumptech.glide.a.a r1 = com.bumptech.glide.a.a.this     // Catch:{ all -> 0x0028 }
                    java.io.Writer r1 = r1.j     // Catch:{ all -> 0x0028 }
                    r2 = 0
                    if (r1 != 0) goto L_0x000e
                    monitor-exit(r0)     // Catch:{ all -> 0x0028 }
                    return r2
                L_0x000e:
                    com.bumptech.glide.a.a r1 = com.bumptech.glide.a.a.this     // Catch:{ all -> 0x0028 }
                    r1.g()     // Catch:{ all -> 0x0028 }
                    com.bumptech.glide.a.a r1 = com.bumptech.glide.a.a.this     // Catch:{ all -> 0x0028 }
                    boolean r1 = r1.e()     // Catch:{ all -> 0x0028 }
                    if (r1 == 0) goto L_0x0026
                    com.bumptech.glide.a.a r1 = com.bumptech.glide.a.a.this     // Catch:{ all -> 0x0028 }
                    r1.d()     // Catch:{ all -> 0x0028 }
                    com.bumptech.glide.a.a r1 = com.bumptech.glide.a.a.this     // Catch:{ all -> 0x0028 }
                    r3 = 0
                    r1.l = r3     // Catch:{ all -> 0x0028 }
                L_0x0026:
                    monitor-exit(r0)     // Catch:{ all -> 0x0028 }
                    return r2
                L_0x0028:
                    r1 = move-exception
                    monitor-exit(r0)     // Catch:{ all -> 0x0028 }
                    throw r1
                */
                throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.a.a.AnonymousClass1.call():java.lang.Void");
            }
        };
        this.b = file2;
        this.f = i2;
        this.c = new File(file2, "journal");
        this.d = new File(file2, "journal.tmp");
        this.e = new File(file2, "journal.bkp");
        this.h = i3;
        this.g = j2;
    }

    public static a a(File file, int i2, int i3, long j2) throws IOException {
        if (j2 <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        } else if (i3 <= 0) {
            throw new IllegalArgumentException("valueCount <= 0");
        } else {
            File file2 = new File(file, "journal.bkp");
            if (file2.exists()) {
                File file3 = new File(file, "journal");
                if (file3.exists()) {
                    file2.delete();
                } else {
                    a(file2, file3, false);
                }
            }
            a aVar = new a(file, i2, i3, j2);
            if (aVar.c.exists()) {
                try {
                    aVar.b();
                    aVar.c();
                    return aVar;
                } catch (IOException e2) {
                    PrintStream printStream = System.out;
                    StringBuilder sb = new StringBuilder();
                    sb.append("DiskLruCache ");
                    sb.append(file);
                    sb.append(" is corrupt: ");
                    sb.append(e2.getMessage());
                    sb.append(", removing");
                    printStream.println(sb.toString());
                    aVar.a();
                }
            }
            file.mkdirs();
            a aVar2 = new a(file, i2, i3, j2);
            aVar2.d();
            return aVar2;
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(5:16|17|(1:19)(1:20)|21|22) */
    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        r8.l = r1 - r8.k.size();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x006b, code lost:
        if (r0.b() != false) goto L_0x006d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x006d, code lost:
        d();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0071, code lost:
        r8.j = new java.io.BufferedWriter(new java.io.OutputStreamWriter(new java.io.FileOutputStream(r8.c, true), com.bumptech.glide.a.c.a));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x008a, code lost:
        return;
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:16:0x005e */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:23:0x008b=Splitter:B:23:0x008b, B:16:0x005e=Splitter:B:16:0x005e} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void b() throws java.io.IOException {
        /*
            r8 = this;
            com.bumptech.glide.a.b r0 = new com.bumptech.glide.a.b
            java.io.FileInputStream r1 = new java.io.FileInputStream
            java.io.File r2 = r8.c
            r1.<init>(r2)
            java.nio.charset.Charset r2 = com.bumptech.glide.a.c.a
            r0.<init>(r1, r2)
            java.lang.String r1 = r0.a()     // Catch:{ all -> 0x00bf }
            java.lang.String r2 = r0.a()     // Catch:{ all -> 0x00bf }
            java.lang.String r3 = r0.a()     // Catch:{ all -> 0x00bf }
            java.lang.String r4 = r0.a()     // Catch:{ all -> 0x00bf }
            java.lang.String r5 = r0.a()     // Catch:{ all -> 0x00bf }
            java.lang.String r6 = "libcore.io.DiskLruCache"
            boolean r6 = r6.equals(r1)     // Catch:{ all -> 0x00bf }
            if (r6 == 0) goto L_0x008b
            java.lang.String r6 = "1"
            boolean r6 = r6.equals(r2)     // Catch:{ all -> 0x00bf }
            if (r6 == 0) goto L_0x008b
            int r6 = r8.f     // Catch:{ all -> 0x00bf }
            java.lang.String r6 = java.lang.Integer.toString(r6)     // Catch:{ all -> 0x00bf }
            boolean r3 = r6.equals(r3)     // Catch:{ all -> 0x00bf }
            if (r3 == 0) goto L_0x008b
            int r3 = r8.h     // Catch:{ all -> 0x00bf }
            java.lang.String r3 = java.lang.Integer.toString(r3)     // Catch:{ all -> 0x00bf }
            boolean r3 = r3.equals(r4)     // Catch:{ all -> 0x00bf }
            if (r3 == 0) goto L_0x008b
            java.lang.String r3 = ""
            boolean r3 = r3.equals(r5)     // Catch:{ all -> 0x00bf }
            if (r3 != 0) goto L_0x0053
            goto L_0x008b
        L_0x0053:
            r1 = 0
        L_0x0054:
            java.lang.String r2 = r0.a()     // Catch:{ EOFException -> 0x005e }
            r8.d(r2)     // Catch:{ EOFException -> 0x005e }
            int r1 = r1 + 1
            goto L_0x0054
        L_0x005e:
            java.util.LinkedHashMap<java.lang.String, com.bumptech.glide.a.a$b> r2 = r8.k     // Catch:{ all -> 0x00bf }
            int r2 = r2.size()     // Catch:{ all -> 0x00bf }
            int r1 = r1 - r2
            r8.l = r1     // Catch:{ all -> 0x00bf }
            boolean r1 = r0.b()     // Catch:{ all -> 0x00bf }
            if (r1 == 0) goto L_0x0071
            r8.d()     // Catch:{ all -> 0x00bf }
            goto L_0x0087
        L_0x0071:
            java.io.BufferedWriter r1 = new java.io.BufferedWriter     // Catch:{ all -> 0x00bf }
            java.io.OutputStreamWriter r2 = new java.io.OutputStreamWriter     // Catch:{ all -> 0x00bf }
            java.io.FileOutputStream r3 = new java.io.FileOutputStream     // Catch:{ all -> 0x00bf }
            java.io.File r4 = r8.c     // Catch:{ all -> 0x00bf }
            r5 = 1
            r3.<init>(r4, r5)     // Catch:{ all -> 0x00bf }
            java.nio.charset.Charset r4 = com.bumptech.glide.a.c.a     // Catch:{ all -> 0x00bf }
            r2.<init>(r3, r4)     // Catch:{ all -> 0x00bf }
            r1.<init>(r2)     // Catch:{ all -> 0x00bf }
            r8.j = r1     // Catch:{ all -> 0x00bf }
        L_0x0087:
            com.bumptech.glide.a.c.a(r0)
            return
        L_0x008b:
            java.io.IOException r3 = new java.io.IOException     // Catch:{ all -> 0x00bf }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x00bf }
            r6.<init>()     // Catch:{ all -> 0x00bf }
            java.lang.String r7 = "unexpected journal header: ["
            r6.append(r7)     // Catch:{ all -> 0x00bf }
            r6.append(r1)     // Catch:{ all -> 0x00bf }
            java.lang.String r1 = ", "
            r6.append(r1)     // Catch:{ all -> 0x00bf }
            r6.append(r2)     // Catch:{ all -> 0x00bf }
            java.lang.String r1 = ", "
            r6.append(r1)     // Catch:{ all -> 0x00bf }
            r6.append(r4)     // Catch:{ all -> 0x00bf }
            java.lang.String r1 = ", "
            r6.append(r1)     // Catch:{ all -> 0x00bf }
            r6.append(r5)     // Catch:{ all -> 0x00bf }
            java.lang.String r1 = "]"
            r6.append(r1)     // Catch:{ all -> 0x00bf }
            java.lang.String r1 = r6.toString()     // Catch:{ all -> 0x00bf }
            r3.<init>(r1)     // Catch:{ all -> 0x00bf }
            throw r3     // Catch:{ all -> 0x00bf }
        L_0x00bf:
            r1 = move-exception
            com.bumptech.glide.a.c.a(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.a.a.b():void");
    }

    private void d(String str) throws IOException {
        String str2;
        int indexOf = str.indexOf(32);
        if (indexOf == -1) {
            StringBuilder sb = new StringBuilder();
            sb.append("unexpected journal line: ");
            sb.append(str);
            throw new IOException(sb.toString());
        }
        int i2 = indexOf + 1;
        int indexOf2 = str.indexOf(32, i2);
        if (indexOf2 == -1) {
            str2 = str.substring(i2);
            if (indexOf == "REMOVE".length() && str.startsWith("REMOVE")) {
                this.k.remove(str2);
                return;
            }
        } else {
            str2 = str.substring(i2, indexOf2);
        }
        b bVar = (b) this.k.get(str2);
        if (bVar == null) {
            bVar = new b(str2);
            this.k.put(str2, bVar);
        }
        if (indexOf2 != -1 && indexOf == "CLEAN".length() && str.startsWith("CLEAN")) {
            String[] split = str.substring(indexOf2 + 1).split(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
            bVar.f = true;
            bVar.g = null;
            bVar.a(split);
        } else if (indexOf2 == -1 && indexOf == "DIRTY".length() && str.startsWith("DIRTY")) {
            bVar.g = new C0007a(bVar);
        } else if (!(indexOf2 == -1 && indexOf == "READ".length() && str.startsWith("READ"))) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("unexpected journal line: ");
            sb2.append(str);
            throw new IOException(sb2.toString());
        }
    }

    private void c() throws IOException {
        a(this.d);
        Iterator it = this.k.values().iterator();
        while (it.hasNext()) {
            b bVar = (b) it.next();
            int i2 = 0;
            if (bVar.g == null) {
                while (i2 < this.h) {
                    this.i += bVar.e[i2];
                    i2++;
                }
            } else {
                bVar.g = null;
                while (i2 < this.h) {
                    a(bVar.a(i2));
                    a(bVar.b(i2));
                    i2++;
                }
                it.remove();
            }
        }
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: private */
    public synchronized void d() throws IOException {
        if (this.j != null) {
            this.j.close();
        }
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.d), c.a));
        try {
            bufferedWriter.write("libcore.io.DiskLruCache");
            bufferedWriter.write("\n");
            bufferedWriter.write("1");
            bufferedWriter.write("\n");
            bufferedWriter.write(Integer.toString(this.f));
            bufferedWriter.write("\n");
            bufferedWriter.write(Integer.toString(this.h));
            bufferedWriter.write("\n");
            bufferedWriter.write("\n");
            for (b bVar : this.k.values()) {
                if (bVar.g != null) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("DIRTY ");
                    sb.append(bVar.d);
                    sb.append(10);
                    bufferedWriter.write(sb.toString());
                } else {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("CLEAN ");
                    sb2.append(bVar.d);
                    sb2.append(bVar.a());
                    sb2.append(10);
                    bufferedWriter.write(sb2.toString());
                }
            }
            bufferedWriter.close();
            if (this.c.exists()) {
                a(this.c, this.e, true);
            }
            a(this.d, this.c, false);
            this.e.delete();
            this.j = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.c, true), c.a));
        } catch (Throwable th) {
            bufferedWriter.close();
            throw th;
        }
    }

    private static void a(File file) throws IOException {
        if (file.exists() && !file.delete()) {
            throw new IOException();
        }
    }

    private static void a(File file, File file2, boolean z) throws IOException {
        if (z) {
            a(file2);
        }
        if (!file.renameTo(file2)) {
            throw new IOException();
        }
    }

    public synchronized c a(String str) throws IOException {
        f();
        b bVar = (b) this.k.get(str);
        if (bVar == null) {
            return null;
        }
        if (!bVar.f) {
            return null;
        }
        for (File exists : bVar.a) {
            if (!exists.exists()) {
                return null;
            }
        }
        this.l++;
        this.j.append("READ");
        this.j.append(' ');
        this.j.append(str);
        this.j.append(10);
        if (e()) {
            this.a.submit(this.n);
        }
        c cVar = new c(str, bVar.h, bVar.a, bVar.e);
        return cVar;
    }

    public C0007a b(String str) throws IOException {
        return a(str, -1);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001e, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized com.bumptech.glide.a.a.C0007a a(java.lang.String r6, long r7) throws java.io.IOException {
        /*
            r5 = this;
            monitor-enter(r5)
            r5.f()     // Catch:{ all -> 0x005d }
            java.util.LinkedHashMap<java.lang.String, com.bumptech.glide.a.a$b> r0 = r5.k     // Catch:{ all -> 0x005d }
            java.lang.Object r0 = r0.get(r6)     // Catch:{ all -> 0x005d }
            com.bumptech.glide.a.a$b r0 = (com.bumptech.glide.a.a.b) r0     // Catch:{ all -> 0x005d }
            r1 = -1
            int r3 = (r7 > r1 ? 1 : (r7 == r1 ? 0 : -1))
            r1 = 0
            if (r3 == 0) goto L_0x001f
            if (r0 == 0) goto L_0x001d
            long r2 = r0.h     // Catch:{ all -> 0x005d }
            int r4 = (r2 > r7 ? 1 : (r2 == r7 ? 0 : -1))
            if (r4 == 0) goto L_0x001f
        L_0x001d:
            monitor-exit(r5)
            return r1
        L_0x001f:
            if (r0 != 0) goto L_0x002c
            com.bumptech.glide.a.a$b r0 = new com.bumptech.glide.a.a$b     // Catch:{ all -> 0x005d }
            r0.<init>(r6)     // Catch:{ all -> 0x005d }
            java.util.LinkedHashMap<java.lang.String, com.bumptech.glide.a.a$b> r7 = r5.k     // Catch:{ all -> 0x005d }
            r7.put(r6, r0)     // Catch:{ all -> 0x005d }
            goto L_0x0034
        L_0x002c:
            com.bumptech.glide.a.a$a r7 = r0.g     // Catch:{ all -> 0x005d }
            if (r7 == 0) goto L_0x0034
            monitor-exit(r5)
            return r1
        L_0x0034:
            com.bumptech.glide.a.a$a r7 = new com.bumptech.glide.a.a$a     // Catch:{ all -> 0x005d }
            r7.<init>(r0)     // Catch:{ all -> 0x005d }
            r0.g = r7     // Catch:{ all -> 0x005d }
            java.io.Writer r8 = r5.j     // Catch:{ all -> 0x005d }
            java.lang.String r0 = "DIRTY"
            r8.append(r0)     // Catch:{ all -> 0x005d }
            java.io.Writer r8 = r5.j     // Catch:{ all -> 0x005d }
            r0 = 32
            r8.append(r0)     // Catch:{ all -> 0x005d }
            java.io.Writer r8 = r5.j     // Catch:{ all -> 0x005d }
            r8.append(r6)     // Catch:{ all -> 0x005d }
            java.io.Writer r6 = r5.j     // Catch:{ all -> 0x005d }
            r8 = 10
            r6.append(r8)     // Catch:{ all -> 0x005d }
            java.io.Writer r6 = r5.j     // Catch:{ all -> 0x005d }
            r6.flush()     // Catch:{ all -> 0x005d }
            monitor-exit(r5)
            return r7
        L_0x005d:
            r6 = move-exception
            monitor-exit(r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.a.a.a(java.lang.String, long):com.bumptech.glide.a.a$a");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x0110, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void a(com.bumptech.glide.a.a.C0007a r12, boolean r13) throws java.io.IOException {
        /*
            r11 = this;
            monitor-enter(r11)
            com.bumptech.glide.a.a$b r0 = r12.b     // Catch:{ all -> 0x0111 }
            com.bumptech.glide.a.a$a r1 = r0.g     // Catch:{ all -> 0x0111 }
            if (r1 == r12) goto L_0x0011
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException     // Catch:{ all -> 0x0111 }
            r12.<init>()     // Catch:{ all -> 0x0111 }
            throw r12     // Catch:{ all -> 0x0111 }
        L_0x0011:
            r1 = 0
            if (r13 == 0) goto L_0x0053
            boolean r2 = r0.f     // Catch:{ all -> 0x0111 }
            if (r2 != 0) goto L_0x0053
            r2 = r1
        L_0x001b:
            int r3 = r11.h     // Catch:{ all -> 0x0111 }
            if (r2 >= r3) goto L_0x0053
            boolean[] r3 = r12.c     // Catch:{ all -> 0x0111 }
            boolean r3 = r3[r2]     // Catch:{ all -> 0x0111 }
            if (r3 != 0) goto L_0x0041
            r12.b()     // Catch:{ all -> 0x0111 }
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException     // Catch:{ all -> 0x0111 }
            java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch:{ all -> 0x0111 }
            r13.<init>()     // Catch:{ all -> 0x0111 }
            java.lang.String r0 = "Newly created entry didn't create value for index "
            r13.append(r0)     // Catch:{ all -> 0x0111 }
            r13.append(r2)     // Catch:{ all -> 0x0111 }
            java.lang.String r13 = r13.toString()     // Catch:{ all -> 0x0111 }
            r12.<init>(r13)     // Catch:{ all -> 0x0111 }
            throw r12     // Catch:{ all -> 0x0111 }
        L_0x0041:
            java.io.File r3 = r0.b(r2)     // Catch:{ all -> 0x0111 }
            boolean r3 = r3.exists()     // Catch:{ all -> 0x0111 }
            if (r3 != 0) goto L_0x0050
            r12.b()     // Catch:{ all -> 0x0111 }
            monitor-exit(r11)
            return
        L_0x0050:
            int r2 = r2 + 1
            goto L_0x001b
        L_0x0053:
            int r12 = r11.h     // Catch:{ all -> 0x0111 }
            if (r1 >= r12) goto L_0x0089
            java.io.File r12 = r0.b(r1)     // Catch:{ all -> 0x0111 }
            if (r13 == 0) goto L_0x0083
            boolean r2 = r12.exists()     // Catch:{ all -> 0x0111 }
            if (r2 == 0) goto L_0x0086
            java.io.File r2 = r0.a(r1)     // Catch:{ all -> 0x0111 }
            r12.renameTo(r2)     // Catch:{ all -> 0x0111 }
            long[] r12 = r0.e     // Catch:{ all -> 0x0111 }
            r3 = r12[r1]     // Catch:{ all -> 0x0111 }
            long r5 = r2.length()     // Catch:{ all -> 0x0111 }
            long[] r12 = r0.e     // Catch:{ all -> 0x0111 }
            r12[r1] = r5     // Catch:{ all -> 0x0111 }
            long r7 = r11.i     // Catch:{ all -> 0x0111 }
            long r9 = r7 - r3
            long r2 = r9 + r5
            r11.i = r2     // Catch:{ all -> 0x0111 }
            goto L_0x0086
        L_0x0083:
            a(r12)     // Catch:{ all -> 0x0111 }
        L_0x0086:
            int r1 = r1 + 1
            goto L_0x0053
        L_0x0089:
            int r12 = r11.l     // Catch:{ all -> 0x0111 }
            r1 = 1
            int r12 = r12 + r1
            r11.l = r12     // Catch:{ all -> 0x0111 }
            r12 = 0
            r0.g = r12     // Catch:{ all -> 0x0111 }
            boolean r12 = r0.f     // Catch:{ all -> 0x0111 }
            r12 = r12 | r13
            r2 = 10
            r3 = 32
            if (r12 == 0) goto L_0x00d2
            r0.f = r1     // Catch:{ all -> 0x0111 }
            java.io.Writer r12 = r11.j     // Catch:{ all -> 0x0111 }
            java.lang.String r1 = "CLEAN"
            r12.append(r1)     // Catch:{ all -> 0x0111 }
            java.io.Writer r12 = r11.j     // Catch:{ all -> 0x0111 }
            r12.append(r3)     // Catch:{ all -> 0x0111 }
            java.io.Writer r12 = r11.j     // Catch:{ all -> 0x0111 }
            java.lang.String r1 = r0.d     // Catch:{ all -> 0x0111 }
            r12.append(r1)     // Catch:{ all -> 0x0111 }
            java.io.Writer r12 = r11.j     // Catch:{ all -> 0x0111 }
            java.lang.String r1 = r0.a()     // Catch:{ all -> 0x0111 }
            r12.append(r1)     // Catch:{ all -> 0x0111 }
            java.io.Writer r12 = r11.j     // Catch:{ all -> 0x0111 }
            r12.append(r2)     // Catch:{ all -> 0x0111 }
            if (r13 == 0) goto L_0x00f5
            long r12 = r11.m     // Catch:{ all -> 0x0111 }
            r1 = 1
            long r3 = r12 + r1
            r11.m = r3     // Catch:{ all -> 0x0111 }
            r0.h = r12     // Catch:{ all -> 0x0111 }
            goto L_0x00f5
        L_0x00d2:
            java.util.LinkedHashMap<java.lang.String, com.bumptech.glide.a.a$b> r12 = r11.k     // Catch:{ all -> 0x0111 }
            java.lang.String r13 = r0.d     // Catch:{ all -> 0x0111 }
            r12.remove(r13)     // Catch:{ all -> 0x0111 }
            java.io.Writer r12 = r11.j     // Catch:{ all -> 0x0111 }
            java.lang.String r13 = "REMOVE"
            r12.append(r13)     // Catch:{ all -> 0x0111 }
            java.io.Writer r12 = r11.j     // Catch:{ all -> 0x0111 }
            r12.append(r3)     // Catch:{ all -> 0x0111 }
            java.io.Writer r12 = r11.j     // Catch:{ all -> 0x0111 }
            java.lang.String r13 = r0.d     // Catch:{ all -> 0x0111 }
            r12.append(r13)     // Catch:{ all -> 0x0111 }
            java.io.Writer r12 = r11.j     // Catch:{ all -> 0x0111 }
            r12.append(r2)     // Catch:{ all -> 0x0111 }
        L_0x00f5:
            java.io.Writer r12 = r11.j     // Catch:{ all -> 0x0111 }
            r12.flush()     // Catch:{ all -> 0x0111 }
            long r12 = r11.i     // Catch:{ all -> 0x0111 }
            long r0 = r11.g     // Catch:{ all -> 0x0111 }
            int r2 = (r12 > r0 ? 1 : (r12 == r0 ? 0 : -1))
            if (r2 > 0) goto L_0x0108
            boolean r12 = r11.e()     // Catch:{ all -> 0x0111 }
            if (r12 == 0) goto L_0x010f
        L_0x0108:
            java.util.concurrent.ThreadPoolExecutor r12 = r11.a     // Catch:{ all -> 0x0111 }
            java.util.concurrent.Callable<java.lang.Void> r13 = r11.n     // Catch:{ all -> 0x0111 }
            r12.submit(r13)     // Catch:{ all -> 0x0111 }
        L_0x010f:
            monitor-exit(r11)
            return
        L_0x0111:
            r12 = move-exception
            monitor-exit(r11)
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.a.a.a(com.bumptech.glide.a.a$a, boolean):void");
    }

    /* access modifiers changed from: private */
    public boolean e() {
        return this.l >= 2000 && this.l >= this.k.size();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x008b, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x008d, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized boolean c(java.lang.String r10) throws java.io.IOException {
        /*
            r9 = this;
            monitor-enter(r9)
            r9.f()     // Catch:{ all -> 0x008e }
            java.util.LinkedHashMap<java.lang.String, com.bumptech.glide.a.a$b> r0 = r9.k     // Catch:{ all -> 0x008e }
            java.lang.Object r0 = r0.get(r10)     // Catch:{ all -> 0x008e }
            com.bumptech.glide.a.a$b r0 = (com.bumptech.glide.a.a.b) r0     // Catch:{ all -> 0x008e }
            r1 = 0
            if (r0 == 0) goto L_0x008c
            com.bumptech.glide.a.a$a r2 = r0.g     // Catch:{ all -> 0x008e }
            if (r2 == 0) goto L_0x0016
            goto L_0x008c
        L_0x0016:
            int r2 = r9.h     // Catch:{ all -> 0x008e }
            if (r1 >= r2) goto L_0x0058
            java.io.File r2 = r0.a(r1)     // Catch:{ all -> 0x008e }
            boolean r3 = r2.exists()     // Catch:{ all -> 0x008e }
            if (r3 == 0) goto L_0x0041
            boolean r3 = r2.delete()     // Catch:{ all -> 0x008e }
            if (r3 != 0) goto L_0x0041
            java.io.IOException r10 = new java.io.IOException     // Catch:{ all -> 0x008e }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x008e }
            r0.<init>()     // Catch:{ all -> 0x008e }
            java.lang.String r1 = "failed to delete "
            r0.append(r1)     // Catch:{ all -> 0x008e }
            r0.append(r2)     // Catch:{ all -> 0x008e }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x008e }
            r10.<init>(r0)     // Catch:{ all -> 0x008e }
            throw r10     // Catch:{ all -> 0x008e }
        L_0x0041:
            long r2 = r9.i     // Catch:{ all -> 0x008e }
            long[] r4 = r0.e     // Catch:{ all -> 0x008e }
            r5 = r4[r1]     // Catch:{ all -> 0x008e }
            long r7 = r2 - r5
            r9.i = r7     // Catch:{ all -> 0x008e }
            long[] r2 = r0.e     // Catch:{ all -> 0x008e }
            r3 = 0
            r2[r1] = r3     // Catch:{ all -> 0x008e }
            int r1 = r1 + 1
            goto L_0x0016
        L_0x0058:
            int r0 = r9.l     // Catch:{ all -> 0x008e }
            r1 = 1
            int r0 = r0 + r1
            r9.l = r0     // Catch:{ all -> 0x008e }
            java.io.Writer r0 = r9.j     // Catch:{ all -> 0x008e }
            java.lang.String r2 = "REMOVE"
            r0.append(r2)     // Catch:{ all -> 0x008e }
            java.io.Writer r0 = r9.j     // Catch:{ all -> 0x008e }
            r2 = 32
            r0.append(r2)     // Catch:{ all -> 0x008e }
            java.io.Writer r0 = r9.j     // Catch:{ all -> 0x008e }
            r0.append(r10)     // Catch:{ all -> 0x008e }
            java.io.Writer r0 = r9.j     // Catch:{ all -> 0x008e }
            r2 = 10
            r0.append(r2)     // Catch:{ all -> 0x008e }
            java.util.LinkedHashMap<java.lang.String, com.bumptech.glide.a.a$b> r0 = r9.k     // Catch:{ all -> 0x008e }
            r0.remove(r10)     // Catch:{ all -> 0x008e }
            boolean r10 = r9.e()     // Catch:{ all -> 0x008e }
            if (r10 == 0) goto L_0x008a
            java.util.concurrent.ThreadPoolExecutor r10 = r9.a     // Catch:{ all -> 0x008e }
            java.util.concurrent.Callable<java.lang.Void> r0 = r9.n     // Catch:{ all -> 0x008e }
            r10.submit(r0)     // Catch:{ all -> 0x008e }
        L_0x008a:
            monitor-exit(r9)
            return r1
        L_0x008c:
            monitor-exit(r9)
            return r1
        L_0x008e:
            r10 = move-exception
            monitor-exit(r9)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.a.a.c(java.lang.String):boolean");
    }

    private void f() {
        if (this.j == null) {
            throw new IllegalStateException("cache is closed");
        }
    }

    public synchronized void close() throws IOException {
        if (this.j != null) {
            Iterator it = new ArrayList(this.k.values()).iterator();
            while (it.hasNext()) {
                b bVar = (b) it.next();
                if (bVar.g != null) {
                    bVar.g.b();
                }
            }
            g();
            this.j.close();
            this.j = null;
        }
    }

    /* access modifiers changed from: private */
    public void g() throws IOException {
        while (this.i > this.g) {
            c((String) ((Entry) this.k.entrySet().iterator().next()).getKey());
        }
    }

    public void a() throws IOException {
        close();
        c.a(this.b);
    }
}
