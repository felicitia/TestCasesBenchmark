package com.contextlogic.wish.http;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public final class DiskLruCache implements Closeable {
    private static final Charset UTF_8 = Charset.forName("UTF-8");
    private final int appVersion;
    private final Callable<Void> cleanupCallable;
    /* access modifiers changed from: private */
    public final File directory;
    private final ExecutorService executorService;
    private final File journalFile;
    private final File journalFileTmp;
    /* access modifiers changed from: private */
    public Writer journalWriter;
    private final LinkedHashMap<String, Entry> lruEntries = new LinkedHashMap<>(0, 0.75f, true);
    private final long maxSize;
    private long nextSequenceNumber = 0;
    /* access modifiers changed from: private */
    public int redundantOpCount;
    private long size = 0;
    /* access modifiers changed from: private */
    public final int valueCount;

    public final class Editor {
        /* access modifiers changed from: private */
        public final Entry entry;
        /* access modifiers changed from: private */
        public boolean hasErrors;

        private class FaultHidingOutputStream extends FilterOutputStream {
            private FaultHidingOutputStream(OutputStream outputStream) {
                super(outputStream);
            }

            public void write(int i) {
                try {
                    this.out.write(i);
                } catch (IOException unused) {
                    Editor.this.hasErrors = true;
                }
            }

            public void write(byte[] bArr, int i, int i2) {
                try {
                    this.out.write(bArr, i, i2);
                } catch (IOException unused) {
                    Editor.this.hasErrors = true;
                }
            }

            public void close() {
                try {
                    this.out.close();
                } catch (IOException unused) {
                    Editor.this.hasErrors = true;
                }
            }

            public void flush() {
                try {
                    this.out.flush();
                } catch (IOException unused) {
                    Editor.this.hasErrors = true;
                }
            }
        }

        private Editor(Entry entry2) {
            this.entry = entry2;
        }

        public OutputStream newOutputStream(int i) throws IOException {
            FaultHidingOutputStream faultHidingOutputStream;
            synchronized (DiskLruCache.this) {
                if (this.entry.currentEditor != this) {
                    throw new IllegalStateException();
                }
                faultHidingOutputStream = new FaultHidingOutputStream(new FileOutputStream(this.entry.getDirtyFile(i)));
            }
            return faultHidingOutputStream;
        }

        public void set(int i, byte[] bArr) throws IOException {
            BufferedOutputStream bufferedOutputStream = null;
            try {
                BufferedOutputStream bufferedOutputStream2 = new BufferedOutputStream(newOutputStream(i), 2048);
                try {
                    bufferedOutputStream2.write(bArr);
                    DiskLruCache.closeQuietly(bufferedOutputStream2);
                } catch (Throwable th) {
                    th = th;
                    bufferedOutputStream = bufferedOutputStream2;
                    DiskLruCache.closeQuietly(bufferedOutputStream);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                DiskLruCache.closeQuietly(bufferedOutputStream);
                throw th;
            }
        }

        public void setExpiry(long j) {
            this.entry.expiry = j;
        }

        public void commit() throws IOException {
            if (this.hasErrors) {
                DiskLruCache.this.completeEdit(this, false);
                DiskLruCache.this.remove(this.entry.key);
                return;
            }
            DiskLruCache.this.completeEdit(this, true);
        }

        public void abort() throws IOException {
            DiskLruCache.this.completeEdit(this, false);
        }
    }

    private final class Entry {
        /* access modifiers changed from: private */
        public Editor currentEditor;
        /* access modifiers changed from: private */
        public long expiry;
        /* access modifiers changed from: private */
        public final String key;
        /* access modifiers changed from: private */
        public final long[] lengths;
        /* access modifiers changed from: private */
        public boolean readable;
        /* access modifiers changed from: private */
        public long sequenceNumber;

        private Entry(String str) {
            this.key = str;
            this.lengths = new long[DiskLruCache.this.valueCount];
        }

        public String getLengths() throws IOException {
            long[] jArr;
            StringBuilder sb = new StringBuilder();
            for (long j : this.lengths) {
                sb.append(' ');
                sb.append(j);
            }
            return sb.toString();
        }

        public void setExpiry(String str) throws NumberFormatException {
            this.expiry = Long.parseLong(str);
        }

        /* access modifiers changed from: private */
        public void setLengths(String[] strArr) throws IOException {
            if (strArr.length != DiskLruCache.this.valueCount) {
                throw invalidLengths(strArr);
            }
            int i = 0;
            while (i < strArr.length) {
                try {
                    this.lengths[i] = Long.parseLong(strArr[i]);
                    i++;
                } catch (NumberFormatException unused) {
                    throw invalidLengths(strArr);
                }
            }
        }

        private IOException invalidLengths(String[] strArr) throws IOException {
            StringBuilder sb = new StringBuilder();
            sb.append("unexpected journal line: ");
            sb.append(Arrays.toString(strArr));
            throw new IOException(sb.toString());
        }

        public File getCleanFile(int i) {
            File access$2300 = DiskLruCache.this.directory;
            StringBuilder sb = new StringBuilder();
            sb.append(this.key);
            sb.append(".");
            sb.append(i);
            return new File(access$2300, sb.toString());
        }

        public File getDirtyFile(int i) {
            File access$2300 = DiskLruCache.this.directory;
            StringBuilder sb = new StringBuilder();
            sb.append(this.key);
            sb.append(".");
            sb.append(i);
            sb.append(".tmp");
            return new File(access$2300, sb.toString());
        }
    }

    public final class Snapshot implements Closeable {
        private final long expiry;
        private final InputStream[] ins;
        private final String key;
        private long[] lengths;
        private final long sequenceNumber;

        private Snapshot(String str, long j, long j2, long[] jArr, InputStream[] inputStreamArr) {
            this.key = str;
            this.sequenceNumber = j;
            this.ins = inputStreamArr;
            this.expiry = j2;
            this.lengths = jArr;
        }

        public long getExpiry() {
            return this.expiry;
        }

        public boolean isExpired() {
            return this.expiry < System.currentTimeMillis();
        }

        public long[] getLengths() {
            return this.lengths;
        }

        public InputStream getInputStream(int i) {
            return this.ins[i];
        }

        public void close() {
            for (InputStream closeQuietly : this.ins) {
                DiskLruCache.closeQuietly(closeQuietly);
            }
        }
    }

    private static <T> T[] copyOfRange(T[] tArr, int i, int i2) {
        int length = tArr.length;
        if (i > i2) {
            throw new IllegalArgumentException();
        } else if (i < 0 || i > length) {
            throw new ArrayIndexOutOfBoundsException();
        } else {
            int i3 = i2 - i;
            int min = Math.min(i3, length - i);
            T[] tArr2 = (Object[]) Array.newInstance(tArr.getClass().getComponentType(), i3);
            System.arraycopy(tArr, i, tArr2, 0, min);
            return tArr2;
        }
    }

    public static String readAsciiLine(InputStream inputStream) throws IOException {
        StringBuilder sb = new StringBuilder(80);
        while (true) {
            int read = inputStream.read();
            if (read == -1) {
                throw new EOFException();
            } else if (read == 10) {
                int length = sb.length();
                if (length > 0) {
                    int i = length - 1;
                    if (sb.charAt(i) == 13) {
                        sb.setLength(i);
                    }
                }
                return sb.toString();
            } else {
                sb.append((char) read);
            }
        }
    }

    public static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception unused) {
            }
        }
    }

    public static void deleteContents(File file) throws IOException {
        File[] listFiles = file.listFiles();
        if (listFiles == null) {
            StringBuilder sb = new StringBuilder();
            sb.append("not a directory: ");
            sb.append(file);
            throw new IllegalArgumentException(sb.toString());
        }
        for (File file2 : listFiles) {
            if (file2.isDirectory()) {
                deleteContents(file2);
            }
            if (!file2.delete()) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("failed to delete file: ");
                sb2.append(file2);
                throw new IOException(sb2.toString());
            }
        }
    }

    private DiskLruCache(File file, int i, int i2, long j) {
        File file2 = file;
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue());
        this.executorService = threadPoolExecutor;
        this.cleanupCallable = new Callable<Void>() {
            /* JADX WARNING: Code restructure failed: missing block: B:11:0x0027, code lost:
                return null;
             */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public java.lang.Void call() throws java.lang.Exception {
                /*
                    r4 = this;
                    com.contextlogic.wish.http.DiskLruCache r0 = com.contextlogic.wish.http.DiskLruCache.this
                    monitor-enter(r0)
                    com.contextlogic.wish.http.DiskLruCache r1 = com.contextlogic.wish.http.DiskLruCache.this     // Catch:{ all -> 0x0028 }
                    java.io.Writer r1 = r1.journalWriter     // Catch:{ all -> 0x0028 }
                    r2 = 0
                    if (r1 != 0) goto L_0x000e
                    monitor-exit(r0)     // Catch:{ all -> 0x0028 }
                    return r2
                L_0x000e:
                    com.contextlogic.wish.http.DiskLruCache r1 = com.contextlogic.wish.http.DiskLruCache.this     // Catch:{ all -> 0x0028 }
                    r1.trimToSize()     // Catch:{ all -> 0x0028 }
                    com.contextlogic.wish.http.DiskLruCache r1 = com.contextlogic.wish.http.DiskLruCache.this     // Catch:{ all -> 0x0028 }
                    boolean r1 = r1.journalRebuildRequired()     // Catch:{ all -> 0x0028 }
                    if (r1 == 0) goto L_0x0026
                    com.contextlogic.wish.http.DiskLruCache r1 = com.contextlogic.wish.http.DiskLruCache.this     // Catch:{ all -> 0x0028 }
                    r1.rebuildJournal()     // Catch:{ all -> 0x0028 }
                    com.contextlogic.wish.http.DiskLruCache r1 = com.contextlogic.wish.http.DiskLruCache.this     // Catch:{ all -> 0x0028 }
                    r3 = 0
                    r1.redundantOpCount = r3     // Catch:{ all -> 0x0028 }
                L_0x0026:
                    monitor-exit(r0)     // Catch:{ all -> 0x0028 }
                    return r2
                L_0x0028:
                    r1 = move-exception
                    monitor-exit(r0)     // Catch:{ all -> 0x0028 }
                    throw r1
                */
                throw new UnsupportedOperationException("Method not decompiled: com.contextlogic.wish.http.DiskLruCache.AnonymousClass1.call():java.lang.Void");
            }
        };
        this.directory = file2;
        this.appVersion = i;
        this.journalFile = new File(file2, "journal");
        this.journalFileTmp = new File(file2, "journal.tmp");
        this.valueCount = i2;
        this.maxSize = j;
    }

    public static DiskLruCache open(File file, int i, int i2, long j) throws IOException {
        if (j <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        } else if (i2 <= 0) {
            throw new IllegalArgumentException("valueCount <= 0");
        } else {
            DiskLruCache diskLruCache = new DiskLruCache(file, i, i2, j);
            if (diskLruCache.journalFile.exists()) {
                try {
                    diskLruCache.readJournal();
                    diskLruCache.processJournal();
                    diskLruCache.journalWriter = new BufferedWriter(new FileWriter(diskLruCache.journalFile, true), 8192);
                    return diskLruCache;
                } catch (IOException unused) {
                    diskLruCache.delete();
                }
            }
            file.mkdirs();
            DiskLruCache diskLruCache2 = new DiskLruCache(file, i, i2, j);
            diskLruCache2.rebuildJournal();
            return diskLruCache2;
        }
    }

    private void readJournal() throws IOException {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(this.journalFile), 8192);
        try {
            String readAsciiLine = readAsciiLine(bufferedInputStream);
            String readAsciiLine2 = readAsciiLine(bufferedInputStream);
            String readAsciiLine3 = readAsciiLine(bufferedInputStream);
            String readAsciiLine4 = readAsciiLine(bufferedInputStream);
            String readAsciiLine5 = readAsciiLine(bufferedInputStream);
            if (!"libcore.io.DiskLruCache".equals(readAsciiLine) || !"1".equals(readAsciiLine2) || !Integer.toString(this.appVersion).equals(readAsciiLine3) || !Integer.toString(this.valueCount).equals(readAsciiLine4) || !"".equals(readAsciiLine5)) {
                StringBuilder sb = new StringBuilder();
                sb.append("unexpected journal header: [");
                sb.append(readAsciiLine);
                sb.append(", ");
                sb.append(readAsciiLine2);
                sb.append(", ");
                sb.append(readAsciiLine4);
                sb.append(", ");
                sb.append(readAsciiLine5);
                sb.append("]");
                throw new IOException(sb.toString());
            }
            while (true) {
                try {
                    readJournalLine(readAsciiLine(bufferedInputStream));
                } catch (EOFException unused) {
                    closeQuietly(bufferedInputStream);
                    return;
                }
            }
        } catch (Throwable th) {
            closeQuietly(bufferedInputStream);
            throw th;
        }
    }

    private void readJournalLine(String str) throws IOException {
        String[] split = str.split(" ");
        if (split.length < 2) {
            StringBuilder sb = new StringBuilder();
            sb.append("unexpected journal line: ");
            sb.append(str);
            throw new IOException(sb.toString());
        }
        String str2 = split[1];
        if (!split[0].equals("REMOVE") || split.length != 2) {
            Entry entry = (Entry) this.lruEntries.get(str2);
            if (entry == null) {
                entry = new Entry(str2);
                this.lruEntries.put(str2, entry);
            }
            if (split[0].equals("CLEAN") && split.length == this.valueCount + 3) {
                entry.readable = true;
                entry.currentEditor = null;
                try {
                    entry.setExpiry(split[2]);
                    entry.setLengths((String[]) copyOfRange(split, 3, split.length));
                } catch (NumberFormatException unused) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("unexpected journal line: ");
                    sb2.append(str);
                    throw new IOException(sb2.toString());
                }
            } else if (split[0].equals("DIRTY") && split.length == 2) {
                entry.currentEditor = new Editor(entry);
            } else if (!split[0].equals("READ") || split.length != 2) {
                StringBuilder sb3 = new StringBuilder();
                sb3.append("unexpected journal line: ");
                sb3.append(str);
                throw new IOException(sb3.toString());
            }
            return;
        }
        this.lruEntries.remove(str2);
    }

    private void processJournal() throws IOException {
        deleteIfExists(this.journalFileTmp);
        Iterator it = this.lruEntries.values().iterator();
        while (it.hasNext()) {
            Entry entry = (Entry) it.next();
            int i = 0;
            if (entry.currentEditor == null) {
                while (i < this.valueCount) {
                    this.size += entry.lengths[i];
                    i++;
                }
            } else {
                entry.currentEditor = null;
                while (i < this.valueCount) {
                    deleteIfExists(entry.getCleanFile(i));
                    deleteIfExists(entry.getDirtyFile(i));
                    i++;
                }
                it.remove();
            }
        }
    }

    /* access modifiers changed from: private */
    public synchronized void rebuildJournal() throws IOException {
        if (this.journalWriter != null) {
            this.journalWriter.close();
        }
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(this.journalFileTmp), 8192);
        bufferedWriter.write("libcore.io.DiskLruCache");
        bufferedWriter.write("\n");
        bufferedWriter.write("1");
        bufferedWriter.write("\n");
        bufferedWriter.write(Integer.toString(this.appVersion));
        bufferedWriter.write("\n");
        bufferedWriter.write(Integer.toString(this.valueCount));
        bufferedWriter.write("\n");
        bufferedWriter.write("\n");
        for (Entry entry : this.lruEntries.values()) {
            if (entry.currentEditor != null) {
                StringBuilder sb = new StringBuilder();
                sb.append("DIRTY ");
                sb.append(entry.key);
                sb.append(10);
                bufferedWriter.write(sb.toString());
            } else {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("CLEAN ");
                sb2.append(entry.key);
                sb2.append(' ');
                sb2.append(entry.expiry);
                sb2.append(entry.getLengths());
                sb2.append(10);
                bufferedWriter.write(sb2.toString());
            }
        }
        bufferedWriter.close();
        this.journalFileTmp.renameTo(this.journalFile);
        this.journalWriter = new BufferedWriter(new FileWriter(this.journalFile, true), 8192);
    }

    private static void deleteIfExists(File file) throws IOException {
        if (file.exists() && !file.delete()) {
            throw new IOException();
        }
    }

    public Snapshot poll(String str) throws IOException {
        checkNotClosed();
        validateKey(str);
        Entry entry = (Entry) this.lruEntries.get(str);
        if (entry == null || !entry.readable) {
            return null;
        }
        Snapshot snapshot = new Snapshot(str, entry.sequenceNumber, entry.expiry, entry.lengths, null);
        return snapshot;
    }

    public synchronized Snapshot get(String str) throws IOException {
        checkNotClosed();
        validateKey(str);
        Entry entry = (Entry) this.lruEntries.get(str);
        if (entry == null) {
            return null;
        }
        if (!entry.readable) {
            return null;
        }
        InputStream[] inputStreamArr = new InputStream[this.valueCount];
        int i = 0;
        while (i < this.valueCount) {
            try {
                inputStreamArr[i] = new FileInputStream(entry.getCleanFile(i));
                i++;
            } catch (FileNotFoundException unused) {
                return null;
            }
        }
        this.redundantOpCount++;
        Writer writer = this.journalWriter;
        StringBuilder sb = new StringBuilder();
        sb.append("READ ");
        sb.append(str);
        sb.append(10);
        writer.append(sb.toString());
        if (journalRebuildRequired()) {
            this.executorService.submit(this.cleanupCallable);
        }
        Snapshot snapshot = new Snapshot(str, entry.sequenceNumber, entry.expiry, entry.lengths, inputStreamArr);
        return snapshot;
    }

    public Editor edit(String str) throws IOException {
        return edit(str, -1);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0021, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized com.contextlogic.wish.http.DiskLruCache.Editor edit(java.lang.String r6, long r7) throws java.io.IOException {
        /*
            r5 = this;
            monitor-enter(r5)
            r5.checkNotClosed()     // Catch:{ all -> 0x0061 }
            r5.validateKey(r6)     // Catch:{ all -> 0x0061 }
            java.util.LinkedHashMap<java.lang.String, com.contextlogic.wish.http.DiskLruCache$Entry> r0 = r5.lruEntries     // Catch:{ all -> 0x0061 }
            java.lang.Object r0 = r0.get(r6)     // Catch:{ all -> 0x0061 }
            com.contextlogic.wish.http.DiskLruCache$Entry r0 = (com.contextlogic.wish.http.DiskLruCache.Entry) r0     // Catch:{ all -> 0x0061 }
            r1 = -1
            int r3 = (r7 > r1 ? 1 : (r7 == r1 ? 0 : -1))
            r1 = 0
            if (r3 == 0) goto L_0x0022
            if (r0 == 0) goto L_0x0020
            long r2 = r0.sequenceNumber     // Catch:{ all -> 0x0061 }
            int r4 = (r2 > r7 ? 1 : (r2 == r7 ? 0 : -1))
            if (r4 == 0) goto L_0x0022
        L_0x0020:
            monitor-exit(r5)
            return r1
        L_0x0022:
            if (r0 != 0) goto L_0x002f
            com.contextlogic.wish.http.DiskLruCache$Entry r0 = new com.contextlogic.wish.http.DiskLruCache$Entry     // Catch:{ all -> 0x0061 }
            r0.<init>(r6)     // Catch:{ all -> 0x0061 }
            java.util.LinkedHashMap<java.lang.String, com.contextlogic.wish.http.DiskLruCache$Entry> r7 = r5.lruEntries     // Catch:{ all -> 0x0061 }
            r7.put(r6, r0)     // Catch:{ all -> 0x0061 }
            goto L_0x0037
        L_0x002f:
            com.contextlogic.wish.http.DiskLruCache$Editor r7 = r0.currentEditor     // Catch:{ all -> 0x0061 }
            if (r7 == 0) goto L_0x0037
            monitor-exit(r5)
            return r1
        L_0x0037:
            com.contextlogic.wish.http.DiskLruCache$Editor r7 = new com.contextlogic.wish.http.DiskLruCache$Editor     // Catch:{ all -> 0x0061 }
            r7.<init>(r0)     // Catch:{ all -> 0x0061 }
            r0.currentEditor = r7     // Catch:{ all -> 0x0061 }
            java.io.Writer r8 = r5.journalWriter     // Catch:{ all -> 0x0061 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x0061 }
            r0.<init>()     // Catch:{ all -> 0x0061 }
            java.lang.String r1 = "DIRTY "
            r0.append(r1)     // Catch:{ all -> 0x0061 }
            r0.append(r6)     // Catch:{ all -> 0x0061 }
            r6 = 10
            r0.append(r6)     // Catch:{ all -> 0x0061 }
            java.lang.String r6 = r0.toString()     // Catch:{ all -> 0x0061 }
            r8.write(r6)     // Catch:{ all -> 0x0061 }
            java.io.Writer r6 = r5.journalWriter     // Catch:{ all -> 0x0061 }
            r6.flush()     // Catch:{ all -> 0x0061 }
            monitor-exit(r5)
            return r7
        L_0x0061:
            r6 = move-exception
            monitor-exit(r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.contextlogic.wish.http.DiskLruCache.edit(java.lang.String, long):com.contextlogic.wish.http.DiskLruCache$Editor");
    }

    /* access modifiers changed from: private */
    public synchronized void completeEdit(Editor editor, boolean z) throws IOException {
        Entry access$1500 = editor.entry;
        if (access$1500.currentEditor != editor) {
            throw new IllegalStateException();
        }
        if (z && !access$1500.readable) {
            for (int i = 0; i < this.valueCount; i++) {
                if (!access$1500.getDirtyFile(i).exists()) {
                    editor.abort();
                    StringBuilder sb = new StringBuilder();
                    sb.append("edit didn't create file ");
                    sb.append(i);
                    throw new IllegalStateException(sb.toString());
                }
            }
        }
        for (int i2 = 0; i2 < this.valueCount; i2++) {
            File dirtyFile = access$1500.getDirtyFile(i2);
            if (!z) {
                deleteIfExists(dirtyFile);
            } else if (dirtyFile.exists()) {
                File cleanFile = access$1500.getCleanFile(i2);
                dirtyFile.renameTo(cleanFile);
                long j = access$1500.lengths[i2];
                long length = cleanFile.length();
                access$1500.lengths[i2] = length;
                this.size = (this.size - j) + length;
            }
        }
        this.redundantOpCount++;
        access$1500.currentEditor = null;
        if (access$1500.readable || z) {
            access$1500.readable = true;
            Writer writer = this.journalWriter;
            StringBuilder sb2 = new StringBuilder();
            sb2.append("CLEAN ");
            sb2.append(access$1500.key);
            sb2.append(' ');
            sb2.append(access$1500.expiry);
            sb2.append(access$1500.getLengths());
            sb2.append(10);
            writer.write(sb2.toString());
            if (z) {
                long j2 = this.nextSequenceNumber;
                this.nextSequenceNumber = j2 + 1;
                access$1500.sequenceNumber = j2;
            }
        } else {
            this.lruEntries.remove(access$1500.key);
            Writer writer2 = this.journalWriter;
            StringBuilder sb3 = new StringBuilder();
            sb3.append("REMOVE ");
            sb3.append(access$1500.key);
            sb3.append(10);
            writer2.write(sb3.toString());
        }
        if (this.size > this.maxSize || journalRebuildRequired()) {
            this.executorService.submit(this.cleanupCallable);
        }
    }

    /* access modifiers changed from: private */
    public boolean journalRebuildRequired() {
        return this.redundantOpCount >= 2000 && this.redundantOpCount >= this.lruEntries.size();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x008a, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x008c, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized boolean remove(java.lang.String r10) throws java.io.IOException {
        /*
            r9 = this;
            monitor-enter(r9)
            r9.checkNotClosed()     // Catch:{ all -> 0x008d }
            r9.validateKey(r10)     // Catch:{ all -> 0x008d }
            java.util.LinkedHashMap<java.lang.String, com.contextlogic.wish.http.DiskLruCache$Entry> r0 = r9.lruEntries     // Catch:{ all -> 0x008d }
            java.lang.Object r0 = r0.get(r10)     // Catch:{ all -> 0x008d }
            com.contextlogic.wish.http.DiskLruCache$Entry r0 = (com.contextlogic.wish.http.DiskLruCache.Entry) r0     // Catch:{ all -> 0x008d }
            r1 = 0
            if (r0 == 0) goto L_0x008b
            com.contextlogic.wish.http.DiskLruCache$Editor r2 = r0.currentEditor     // Catch:{ all -> 0x008d }
            if (r2 == 0) goto L_0x0019
            goto L_0x008b
        L_0x0019:
            int r2 = r9.valueCount     // Catch:{ all -> 0x008d }
            if (r1 >= r2) goto L_0x0056
            java.io.File r2 = r0.getCleanFile(r1)     // Catch:{ all -> 0x008d }
            boolean r3 = r2.delete()     // Catch:{ all -> 0x008d }
            if (r3 != 0) goto L_0x003e
            java.io.IOException r10 = new java.io.IOException     // Catch:{ all -> 0x008d }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x008d }
            r0.<init>()     // Catch:{ all -> 0x008d }
            java.lang.String r1 = "failed to delete "
            r0.append(r1)     // Catch:{ all -> 0x008d }
            r0.append(r2)     // Catch:{ all -> 0x008d }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x008d }
            r10.<init>(r0)     // Catch:{ all -> 0x008d }
            throw r10     // Catch:{ all -> 0x008d }
        L_0x003e:
            long r2 = r9.size     // Catch:{ all -> 0x008d }
            long[] r4 = r0.lengths     // Catch:{ all -> 0x008d }
            r5 = r4[r1]     // Catch:{ all -> 0x008d }
            r4 = 0
            long r7 = r2 - r5
            r9.size = r7     // Catch:{ all -> 0x008d }
            long[] r2 = r0.lengths     // Catch:{ all -> 0x008d }
            r3 = 0
            r2[r1] = r3     // Catch:{ all -> 0x008d }
            int r1 = r1 + 1
            goto L_0x0019
        L_0x0056:
            int r0 = r9.redundantOpCount     // Catch:{ all -> 0x008d }
            r1 = 1
            int r0 = r0 + r1
            r9.redundantOpCount = r0     // Catch:{ all -> 0x008d }
            java.io.Writer r0 = r9.journalWriter     // Catch:{ all -> 0x008d }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x008d }
            r2.<init>()     // Catch:{ all -> 0x008d }
            java.lang.String r3 = "REMOVE "
            r2.append(r3)     // Catch:{ all -> 0x008d }
            r2.append(r10)     // Catch:{ all -> 0x008d }
            r3 = 10
            r2.append(r3)     // Catch:{ all -> 0x008d }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x008d }
            r0.append(r2)     // Catch:{ all -> 0x008d }
            java.util.LinkedHashMap<java.lang.String, com.contextlogic.wish.http.DiskLruCache$Entry> r0 = r9.lruEntries     // Catch:{ all -> 0x008d }
            r0.remove(r10)     // Catch:{ all -> 0x008d }
            boolean r10 = r9.journalRebuildRequired()     // Catch:{ all -> 0x008d }
            if (r10 == 0) goto L_0x0089
            java.util.concurrent.ExecutorService r10 = r9.executorService     // Catch:{ all -> 0x008d }
            java.util.concurrent.Callable<java.lang.Void> r0 = r9.cleanupCallable     // Catch:{ all -> 0x008d }
            r10.submit(r0)     // Catch:{ all -> 0x008d }
        L_0x0089:
            monitor-exit(r9)
            return r1
        L_0x008b:
            monitor-exit(r9)
            return r1
        L_0x008d:
            r10 = move-exception
            monitor-exit(r9)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.contextlogic.wish.http.DiskLruCache.remove(java.lang.String):boolean");
    }

    private void checkNotClosed() {
        if (this.journalWriter == null) {
            throw new IllegalStateException("cache is closed");
        }
    }

    public synchronized void close() throws IOException {
        if (this.journalWriter != null) {
            Iterator it = new ArrayList(this.lruEntries.values()).iterator();
            while (it.hasNext()) {
                Entry entry = (Entry) it.next();
                if (entry.currentEditor != null) {
                    entry.currentEditor.abort();
                }
            }
            trimToSize();
            this.journalWriter.close();
            this.journalWriter = null;
        }
    }

    /* access modifiers changed from: private */
    public void trimToSize() throws IOException {
        while (this.size > this.maxSize) {
            remove((String) ((java.util.Map.Entry) this.lruEntries.entrySet().iterator().next()).getKey());
        }
    }

    public void delete() throws IOException {
        close();
        deleteContents(this.directory);
    }

    private void validateKey(String str) {
        if (str.contains(" ") || str.contains("\n") || str.contains("\r")) {
            StringBuilder sb = new StringBuilder();
            sb.append("keys must not contain spaces or newlines: \"");
            sb.append(str);
            sb.append("\"");
            throw new IllegalArgumentException(sb.toString());
        }
    }
}
