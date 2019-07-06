package com.facebook.internal;

import com.etsy.android.lib.models.ResponseConstants;
import com.facebook.LoggingBehavior;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;
import org.apache.commons.math3.geometry.VectorFormat;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

/* compiled from: FileLruCache */
public final class l {
    static final String a = "l";
    /* access modifiers changed from: private */
    public static final AtomicLong b = new AtomicLong();
    private final String c;
    private final d d;
    private final File e;
    private boolean f;
    private boolean g;
    private final Object h;
    /* access modifiers changed from: private */
    public AtomicLong i = new AtomicLong(0);

    /* compiled from: FileLruCache */
    private static class a {
        private static final FilenameFilter a = new FilenameFilter() {
            public boolean accept(File file, String str) {
                return !str.startsWith("buffer");
            }
        };
        private static final FilenameFilter b = new FilenameFilter() {
            public boolean accept(File file, String str) {
                return str.startsWith("buffer");
            }
        };

        static void a(File file) {
            File[] listFiles = file.listFiles(b());
            if (listFiles != null) {
                for (File delete : listFiles) {
                    delete.delete();
                }
            }
        }

        static FilenameFilter a() {
            return a;
        }

        static FilenameFilter b() {
            return b;
        }

        static File b(File file) {
            StringBuilder sb = new StringBuilder();
            sb.append("buffer");
            sb.append(Long.valueOf(l.b.incrementAndGet()).toString());
            return new File(file, sb.toString());
        }
    }

    /* compiled from: FileLruCache */
    private static class b extends OutputStream {
        final OutputStream a;
        final f b;

        b(OutputStream outputStream, f fVar) {
            this.a = outputStream;
            this.b = fVar;
        }

        public void close() throws IOException {
            try {
                this.a.close();
            } finally {
                this.b.a();
            }
        }

        public void flush() throws IOException {
            this.a.flush();
        }

        public void write(byte[] bArr, int i, int i2) throws IOException {
            this.a.write(bArr, i, i2);
        }

        public void write(byte[] bArr) throws IOException {
            this.a.write(bArr);
        }

        public void write(int i) throws IOException {
            this.a.write(i);
        }
    }

    /* compiled from: FileLruCache */
    private static final class c extends InputStream {
        final InputStream a;
        final OutputStream b;

        public boolean markSupported() {
            return false;
        }

        c(InputStream inputStream, OutputStream outputStream) {
            this.a = inputStream;
            this.b = outputStream;
        }

        public int available() throws IOException {
            return this.a.available();
        }

        public void close() throws IOException {
            try {
                this.a.close();
            } finally {
                this.b.close();
            }
        }

        public void mark(int i) {
            throw new UnsupportedOperationException();
        }

        public int read(byte[] bArr) throws IOException {
            int read = this.a.read(bArr);
            if (read > 0) {
                this.b.write(bArr, 0, read);
            }
            return read;
        }

        public int read() throws IOException {
            int read = this.a.read();
            if (read >= 0) {
                this.b.write(read);
            }
            return read;
        }

        public int read(byte[] bArr, int i, int i2) throws IOException {
            int read = this.a.read(bArr, i, i2);
            if (read > 0) {
                this.b.write(bArr, i, read);
            }
            return read;
        }

        public synchronized void reset() {
            throw new UnsupportedOperationException();
        }

        public long skip(long j) throws IOException {
            byte[] bArr = new byte[1024];
            long j2 = 0;
            while (j2 < j) {
                int read = read(bArr, 0, (int) Math.min(j - j2, (long) bArr.length));
                if (read < 0) {
                    return j2;
                }
                j2 += (long) read;
            }
            return j2;
        }
    }

    /* compiled from: FileLruCache */
    public static final class d {
        private int a = 1048576;
        private int b = 1024;

        /* access modifiers changed from: 0000 */
        public int a() {
            return this.a;
        }

        /* access modifiers changed from: 0000 */
        public int b() {
            return this.b;
        }
    }

    /* compiled from: FileLruCache */
    private static final class e implements Comparable<e> {
        private final File a;
        private final long b;

        e(File file) {
            this.a = file;
            this.b = file.lastModified();
        }

        /* access modifiers changed from: 0000 */
        public File a() {
            return this.a;
        }

        /* access modifiers changed from: 0000 */
        public long b() {
            return this.b;
        }

        /* renamed from: a */
        public int compareTo(e eVar) {
            if (b() < eVar.b()) {
                return -1;
            }
            if (b() > eVar.b()) {
                return 1;
            }
            return a().compareTo(eVar.a());
        }

        public boolean equals(Object obj) {
            return (obj instanceof e) && compareTo((e) obj) == 0;
        }

        public int hashCode() {
            return ((1073 + this.a.hashCode()) * 37) + ((int) (this.b % 2147483647L));
        }
    }

    /* compiled from: FileLruCache */
    private interface f {
        void a();
    }

    /* compiled from: FileLruCache */
    private static final class g {
        static void a(OutputStream outputStream, JSONObject jSONObject) throws IOException {
            byte[] bytes = jSONObject.toString().getBytes();
            outputStream.write(0);
            outputStream.write((bytes.length >> 16) & 255);
            outputStream.write((bytes.length >> 8) & 255);
            outputStream.write((bytes.length >> 0) & 255);
            outputStream.write(bytes);
        }

        static JSONObject a(InputStream inputStream) throws IOException {
            if (inputStream.read() != 0) {
                return null;
            }
            int i = 0;
            int i2 = 0;
            for (int i3 = 0; i3 < 3; i3++) {
                int read = inputStream.read();
                if (read == -1) {
                    t.a(LoggingBehavior.CACHE, l.a, "readHeader: stream.read returned -1 while reading header size");
                    return null;
                }
                i2 = (i2 << 8) + (read & 255);
            }
            byte[] bArr = new byte[i2];
            while (i < bArr.length) {
                int read2 = inputStream.read(bArr, i, bArr.length - i);
                if (read2 < 1) {
                    LoggingBehavior loggingBehavior = LoggingBehavior.CACHE;
                    String str = l.a;
                    StringBuilder sb = new StringBuilder();
                    sb.append("readHeader: stream.read stopped at ");
                    sb.append(Integer.valueOf(i));
                    sb.append(" when expected ");
                    sb.append(bArr.length);
                    t.a(loggingBehavior, str, sb.toString());
                    return null;
                }
                i += read2;
            }
            try {
                Object nextValue = new JSONTokener(new String(bArr)).nextValue();
                if (nextValue instanceof JSONObject) {
                    return (JSONObject) nextValue;
                }
                LoggingBehavior loggingBehavior2 = LoggingBehavior.CACHE;
                String str2 = l.a;
                StringBuilder sb2 = new StringBuilder();
                sb2.append("readHeader: expected JSONObject, got ");
                sb2.append(nextValue.getClass().getCanonicalName());
                t.a(loggingBehavior2, str2, sb2.toString());
                return null;
            } catch (JSONException e) {
                throw new IOException(e.getMessage());
            }
        }
    }

    public l(String str, d dVar) {
        this.c = str;
        this.d = dVar;
        this.e = new File(com.facebook.f.o(), str);
        this.h = new Object();
        if (this.e.mkdirs() || this.e.isDirectory()) {
            a.a(this.e);
        }
    }

    public InputStream a(String str) throws IOException {
        return a(str, (String) null);
    }

    public InputStream a(String str, String str2) throws IOException {
        File file = new File(this.e, z.b(str));
        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file), 8192);
            try {
                JSONObject a2 = g.a(bufferedInputStream);
                if (a2 == null) {
                    return null;
                }
                String optString = a2.optString(ResponseConstants.KEY);
                if (optString != null) {
                    if (optString.equals(str)) {
                        String optString2 = a2.optString("tag", null);
                        if ((str2 != null || optString2 == null) && (str2 == null || str2.equals(optString2))) {
                            long time = new Date().getTime();
                            LoggingBehavior loggingBehavior = LoggingBehavior.CACHE;
                            String str3 = a;
                            StringBuilder sb = new StringBuilder();
                            sb.append("Setting lastModified to ");
                            sb.append(Long.valueOf(time));
                            sb.append(" for ");
                            sb.append(file.getName());
                            t.a(loggingBehavior, str3, sb.toString());
                            file.setLastModified(time);
                            return bufferedInputStream;
                        }
                        bufferedInputStream.close();
                        return null;
                    }
                }
                bufferedInputStream.close();
                return null;
            } finally {
                bufferedInputStream.close();
            }
        } catch (IOException unused) {
            return null;
        }
    }

    public OutputStream b(String str) throws IOException {
        return b(str, null);
    }

    public OutputStream b(String str, String str2) throws IOException {
        final File b2 = a.b(this.e);
        b2.delete();
        if (!b2.createNewFile()) {
            StringBuilder sb = new StringBuilder();
            sb.append("Could not create file at ");
            sb.append(b2.getAbsolutePath());
            throw new IOException(sb.toString());
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(b2);
            final long currentTimeMillis = System.currentTimeMillis();
            final String str3 = str;
            AnonymousClass1 r1 = new f() {
                public void a() {
                    if (currentTimeMillis < l.this.i.get()) {
                        b2.delete();
                    } else {
                        l.this.a(str3, b2);
                    }
                }
            };
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new b(fileOutputStream, r1), 8192);
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(ResponseConstants.KEY, str);
                if (!z.a(str2)) {
                    jSONObject.put("tag", str2);
                }
                g.a(bufferedOutputStream, jSONObject);
                return bufferedOutputStream;
            } catch (JSONException e2) {
                LoggingBehavior loggingBehavior = LoggingBehavior.CACHE;
                String str4 = a;
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Error creating JSON header for cache file: ");
                sb2.append(e2);
                t.a(loggingBehavior, 5, str4, sb2.toString());
                throw new IOException(e2.getMessage());
            } catch (Throwable th) {
                bufferedOutputStream.close();
                throw th;
            }
        } catch (FileNotFoundException e3) {
            LoggingBehavior loggingBehavior2 = LoggingBehavior.CACHE;
            String str5 = a;
            StringBuilder sb3 = new StringBuilder();
            sb3.append("Error creating buffer output stream: ");
            sb3.append(e3);
            t.a(loggingBehavior2, 5, str5, sb3.toString());
            throw new IOException(e3.getMessage());
        }
    }

    public void a() {
        final File[] listFiles = this.e.listFiles(a.a());
        this.i.set(System.currentTimeMillis());
        if (listFiles != null) {
            com.facebook.f.d().execute(new Runnable() {
                public void run() {
                    for (File delete : listFiles) {
                        delete.delete();
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void a(String str, File file) {
        if (!file.renameTo(new File(this.e, z.b(str)))) {
            file.delete();
        }
        c();
    }

    public InputStream a(String str, InputStream inputStream) throws IOException {
        return new c(inputStream, b(str));
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{FileLruCache: tag:");
        sb.append(this.c);
        sb.append(" file:");
        sb.append(this.e.getName());
        sb.append(VectorFormat.DEFAULT_SUFFIX);
        return sb.toString();
    }

    private void c() {
        synchronized (this.h) {
            if (!this.f) {
                this.f = true;
                com.facebook.f.d().execute(new Runnable() {
                    public void run() {
                        l.this.d();
                    }
                });
            }
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00f2  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void d() {
        /*
            r17 = this;
            r1 = r17
            java.lang.Object r2 = r1.h
            monitor-enter(r2)
            r3 = 0
            r1.f = r3     // Catch:{ all -> 0x0100 }
            r4 = 1
            r1.g = r4     // Catch:{ all -> 0x0100 }
            monitor-exit(r2)     // Catch:{ all -> 0x0100 }
            com.facebook.LoggingBehavior r2 = com.facebook.LoggingBehavior.CACHE     // Catch:{ all -> 0x00ed }
            java.lang.String r4 = a     // Catch:{ all -> 0x00ed }
            java.lang.String r5 = "trim started"
            com.facebook.internal.t.a(r2, r4, r5)     // Catch:{ all -> 0x00ed }
            java.util.PriorityQueue r2 = new java.util.PriorityQueue     // Catch:{ all -> 0x00ed }
            r2.<init>()     // Catch:{ all -> 0x00ed }
            java.io.File r4 = r1.e     // Catch:{ all -> 0x00ed }
            java.io.FilenameFilter r5 = com.facebook.internal.l.a.a()     // Catch:{ all -> 0x00ed }
            java.io.File[] r4 = r4.listFiles(r5)     // Catch:{ all -> 0x00ed }
            r7 = 0
            if (r4 == 0) goto L_0x0088
            int r9 = r4.length     // Catch:{ all -> 0x00ed }
            r10 = r7
            r12 = r10
            r7 = r3
        L_0x002c:
            if (r7 >= r9) goto L_0x0084
            r8 = r4[r7]     // Catch:{ all -> 0x00ed }
            com.facebook.internal.l$e r14 = new com.facebook.internal.l$e     // Catch:{ all -> 0x00ed }
            r14.<init>(r8)     // Catch:{ all -> 0x00ed }
            r2.add(r14)     // Catch:{ all -> 0x00ed }
            com.facebook.LoggingBehavior r15 = com.facebook.LoggingBehavior.CACHE     // Catch:{ all -> 0x00ed }
            java.lang.String r3 = a     // Catch:{ all -> 0x00ed }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x00ed }
            r5.<init>()     // Catch:{ all -> 0x00ed }
            java.lang.String r6 = "  trim considering time="
            r5.append(r6)     // Catch:{ all -> 0x00ed }
            r16 = r2
            long r1 = r14.b()     // Catch:{ all -> 0x007e }
            java.lang.Long r1 = java.lang.Long.valueOf(r1)     // Catch:{ all -> 0x007e }
            r5.append(r1)     // Catch:{ all -> 0x007e }
            java.lang.String r1 = " name="
            r5.append(r1)     // Catch:{ all -> 0x007e }
            java.io.File r1 = r14.a()     // Catch:{ all -> 0x007e }
            java.lang.String r1 = r1.getName()     // Catch:{ all -> 0x007e }
            r5.append(r1)     // Catch:{ all -> 0x007e }
            java.lang.String r1 = r5.toString()     // Catch:{ all -> 0x007e }
            com.facebook.internal.t.a(r15, r3, r1)     // Catch:{ all -> 0x007e }
            long r1 = r8.length()     // Catch:{ all -> 0x007e }
            long r5 = r10 + r1
            r1 = 1
            long r10 = r12 + r1
            int r7 = r7 + 1
            r12 = r10
            r2 = r16
            r1 = r17
            r3 = 0
            r10 = r5
            goto L_0x002c
        L_0x007e:
            r0 = move-exception
            r2 = r0
            r1 = r17
            goto L_0x00ef
        L_0x0084:
            r16 = r2
            r7 = r10
            goto L_0x008b
        L_0x0088:
            r16 = r2
            r12 = r7
        L_0x008b:
            com.facebook.internal.l$d r2 = r1.d     // Catch:{ all -> 0x00ed }
            int r2 = r2.a()     // Catch:{ all -> 0x00ed }
            long r2 = (long) r2     // Catch:{ all -> 0x00ed }
            int r4 = (r7 > r2 ? 1 : (r7 == r2 ? 0 : -1))
            if (r4 > 0) goto L_0x00b3
            com.facebook.internal.l$d r2 = r1.d     // Catch:{ all -> 0x00ed }
            int r2 = r2.b()     // Catch:{ all -> 0x00ed }
            long r2 = (long) r2
            int r4 = (r12 > r2 ? 1 : (r12 == r2 ? 0 : -1))
            if (r4 <= 0) goto L_0x00a2
            goto L_0x00b3
        L_0x00a2:
            java.lang.Object r2 = r1.h
            monitor-enter(r2)
            r3 = 0
            r1.g = r3     // Catch:{ all -> 0x00af }
            java.lang.Object r3 = r1.h     // Catch:{ all -> 0x00af }
            r3.notifyAll()     // Catch:{ all -> 0x00af }
            monitor-exit(r2)     // Catch:{ all -> 0x00af }
            return
        L_0x00af:
            r0 = move-exception
            r3 = r0
            monitor-exit(r2)     // Catch:{ all -> 0x00af }
            throw r3
        L_0x00b3:
            r2 = r16
            java.lang.Object r3 = r2.remove()     // Catch:{ all -> 0x00ed }
            com.facebook.internal.l$e r3 = (com.facebook.internal.l.e) r3     // Catch:{ all -> 0x00ed }
            java.io.File r3 = r3.a()     // Catch:{ all -> 0x00ed }
            com.facebook.LoggingBehavior r4 = com.facebook.LoggingBehavior.CACHE     // Catch:{ all -> 0x00ed }
            java.lang.String r5 = a     // Catch:{ all -> 0x00ed }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x00ed }
            r6.<init>()     // Catch:{ all -> 0x00ed }
            java.lang.String r9 = "  trim removing "
            r6.append(r9)     // Catch:{ all -> 0x00ed }
            java.lang.String r9 = r3.getName()     // Catch:{ all -> 0x00ed }
            r6.append(r9)     // Catch:{ all -> 0x00ed }
            java.lang.String r6 = r6.toString()     // Catch:{ all -> 0x00ed }
            com.facebook.internal.t.a(r4, r5, r6)     // Catch:{ all -> 0x00ed }
            long r4 = r3.length()     // Catch:{ all -> 0x00ed }
            long r9 = r7 - r4
            r4 = 1
            long r6 = r12 - r4
            r3.delete()     // Catch:{ all -> 0x00ed }
            r16 = r2
            r12 = r6
            r7 = r9
            goto L_0x008b
        L_0x00ed:
            r0 = move-exception
            r2 = r0
        L_0x00ef:
            java.lang.Object r3 = r1.h
            monitor-enter(r3)
            r4 = 0
            r1.g = r4     // Catch:{ all -> 0x00fc }
            java.lang.Object r4 = r1.h     // Catch:{ all -> 0x00fc }
            r4.notifyAll()     // Catch:{ all -> 0x00fc }
            monitor-exit(r3)     // Catch:{ all -> 0x00fc }
            throw r2
        L_0x00fc:
            r0 = move-exception
            r2 = r0
            monitor-exit(r3)     // Catch:{ all -> 0x00fc }
            throw r2
        L_0x0100:
            r0 = move-exception
            r3 = r0
            monitor-exit(r2)     // Catch:{ all -> 0x0100 }
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.internal.l.d():void");
    }
}
