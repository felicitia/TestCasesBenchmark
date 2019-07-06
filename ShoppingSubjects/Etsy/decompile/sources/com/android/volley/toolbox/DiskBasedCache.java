package com.android.volley.toolbox;

import android.os.SystemClock;
import com.android.volley.Cache;
import com.android.volley.Cache.Entry;
import com.android.volley.VolleyLog;
import java.io.EOFException;
import java.io.File;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class DiskBasedCache implements Cache {
    private static final int CACHE_MAGIC = 538183203;
    public static final int DEFAULT_DISK_USAGE_BYTES = 5242880;
    private static final float HYSTERESIS_FACTOR = 0.9f;
    private final Map<String, CacheHeader> mEntries;
    private final int mMaxCacheSizeInBytes;
    private final File mRootDirectory;
    private long mTotalSize;

    static class CacheHeader {
        public String etag;
        public String key;
        public Map<String, String> responseHeaders;
        public long serverDate;
        public long size;
        public long softTtl;
        public long ttl;

        private CacheHeader() {
        }

        public CacheHeader(String str, Entry entry) {
            this.key = str;
            this.size = (long) entry.data.length;
            this.etag = entry.etag;
            this.serverDate = entry.serverDate;
            this.ttl = entry.ttl;
            this.softTtl = entry.softTtl;
            this.responseHeaders = entry.responseHeaders;
        }

        public static CacheHeader readHeader(InputStream inputStream) throws IOException {
            CacheHeader cacheHeader = new CacheHeader();
            if (DiskBasedCache.readInt(inputStream) != DiskBasedCache.CACHE_MAGIC) {
                throw new IOException();
            }
            cacheHeader.key = DiskBasedCache.readString(inputStream);
            cacheHeader.etag = DiskBasedCache.readString(inputStream);
            if (cacheHeader.etag.equals("")) {
                cacheHeader.etag = null;
            }
            cacheHeader.serverDate = DiskBasedCache.readLong(inputStream);
            cacheHeader.ttl = DiskBasedCache.readLong(inputStream);
            cacheHeader.softTtl = DiskBasedCache.readLong(inputStream);
            cacheHeader.responseHeaders = DiskBasedCache.readStringStringMap(inputStream);
            return cacheHeader;
        }

        public Entry toCacheEntry(byte[] bArr) {
            Entry entry = new Entry();
            entry.data = bArr;
            entry.etag = this.etag;
            entry.serverDate = this.serverDate;
            entry.ttl = this.ttl;
            entry.softTtl = this.softTtl;
            entry.responseHeaders = this.responseHeaders;
            return entry;
        }

        public boolean writeHeader(OutputStream outputStream) {
            try {
                DiskBasedCache.writeInt(outputStream, DiskBasedCache.CACHE_MAGIC);
                DiskBasedCache.writeString(outputStream, this.key);
                DiskBasedCache.writeString(outputStream, this.etag == null ? "" : this.etag);
                DiskBasedCache.writeLong(outputStream, this.serverDate);
                DiskBasedCache.writeLong(outputStream, this.ttl);
                DiskBasedCache.writeLong(outputStream, this.softTtl);
                DiskBasedCache.writeStringStringMap(this.responseHeaders, outputStream);
                outputStream.flush();
                return true;
            } catch (IOException e) {
                VolleyLog.d("%s", e.toString());
                return false;
            }
        }
    }

    private static class CountingInputStream extends FilterInputStream {
        /* access modifiers changed from: private */
        public int bytesRead;

        private CountingInputStream(InputStream inputStream) {
            super(inputStream);
            this.bytesRead = 0;
        }

        public int read() throws IOException {
            int read = super.read();
            if (read != -1) {
                this.bytesRead++;
            }
            return read;
        }

        public int read(byte[] bArr, int i, int i2) throws IOException {
            int read = super.read(bArr, i, i2);
            if (read != -1) {
                this.bytesRead += read;
            }
            return read;
        }
    }

    public DiskBasedCache(File file, int i) {
        this.mEntries = new LinkedHashMap(16, 0.75f, true);
        this.mTotalSize = 0;
        this.mRootDirectory = file;
        this.mMaxCacheSizeInBytes = i;
    }

    public DiskBasedCache(File file) {
        this(file, DEFAULT_DISK_USAGE_BYTES);
    }

    public synchronized void clear() {
        File[] listFiles = this.mRootDirectory.listFiles();
        if (listFiles != null) {
            for (File delete : listFiles) {
                delete.delete();
            }
        }
        this.mEntries.clear();
        this.mTotalSize = 0;
        VolleyLog.d("Cache cleared.", new Object[0]);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0044, code lost:
        return r0;
     */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0066 A[SYNTHETIC, Splitter:B:32:0x0066] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x0088 A[SYNTHETIC, Splitter:B:45:0x0088] */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x0093 A[SYNTHETIC, Splitter:B:54:0x0093] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized com.android.volley.Cache.Entry get(java.lang.String r14) {
        /*
            r13 = this;
            monitor-enter(r13)
            java.util.Map<java.lang.String, com.android.volley.toolbox.DiskBasedCache$CacheHeader> r0 = r13.mEntries     // Catch:{ all -> 0x009a }
            java.lang.Object r0 = r0.get(r14)     // Catch:{ all -> 0x009a }
            com.android.volley.toolbox.DiskBasedCache$CacheHeader r0 = (com.android.volley.toolbox.DiskBasedCache.CacheHeader) r0     // Catch:{ all -> 0x009a }
            r1 = 0
            if (r0 != 0) goto L_0x000e
            monitor-exit(r13)
            return r1
        L_0x000e:
            java.io.File r2 = r13.getFileForKey(r14)     // Catch:{ all -> 0x009a }
            r3 = 1
            r4 = 0
            r5 = 2
            com.android.volley.toolbox.DiskBasedCache$CountingInputStream r6 = new com.android.volley.toolbox.DiskBasedCache$CountingInputStream     // Catch:{ IOException -> 0x006e, NegativeArraySizeException -> 0x004c, all -> 0x0049 }
            java.io.BufferedInputStream r7 = new java.io.BufferedInputStream     // Catch:{ IOException -> 0x006e, NegativeArraySizeException -> 0x004c, all -> 0x0049 }
            java.io.FileInputStream r8 = new java.io.FileInputStream     // Catch:{ IOException -> 0x006e, NegativeArraySizeException -> 0x004c, all -> 0x0049 }
            r8.<init>(r2)     // Catch:{ IOException -> 0x006e, NegativeArraySizeException -> 0x004c, all -> 0x0049 }
            r7.<init>(r8)     // Catch:{ IOException -> 0x006e, NegativeArraySizeException -> 0x004c, all -> 0x0049 }
            r6.<init>(r7)     // Catch:{ IOException -> 0x006e, NegativeArraySizeException -> 0x004c, all -> 0x0049 }
            com.android.volley.toolbox.DiskBasedCache.CacheHeader.readHeader(r6)     // Catch:{ IOException -> 0x0047, NegativeArraySizeException -> 0x0045 }
            long r7 = r2.length()     // Catch:{ IOException -> 0x0047, NegativeArraySizeException -> 0x0045 }
            int r9 = r6.bytesRead     // Catch:{ IOException -> 0x0047, NegativeArraySizeException -> 0x0045 }
            long r9 = (long) r9     // Catch:{ IOException -> 0x0047, NegativeArraySizeException -> 0x0045 }
            long r11 = r7 - r9
            int r7 = (int) r11     // Catch:{ IOException -> 0x0047, NegativeArraySizeException -> 0x0045 }
            byte[] r7 = streamToBytes(r6, r7)     // Catch:{ IOException -> 0x0047, NegativeArraySizeException -> 0x0045 }
            com.android.volley.Cache$Entry r0 = r0.toCacheEntry(r7)     // Catch:{ IOException -> 0x0047, NegativeArraySizeException -> 0x0045 }
            if (r6 == 0) goto L_0x0043
            r6.close()     // Catch:{ IOException -> 0x0041 }
            goto L_0x0043
        L_0x0041:
            monitor-exit(r13)
            return r1
        L_0x0043:
            monitor-exit(r13)
            return r0
        L_0x0045:
            r0 = move-exception
            goto L_0x004e
        L_0x0047:
            r0 = move-exception
            goto L_0x0070
        L_0x0049:
            r14 = move-exception
            r6 = r1
            goto L_0x0091
        L_0x004c:
            r0 = move-exception
            r6 = r1
        L_0x004e:
            java.lang.String r7 = "%s: %s"
            java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch:{ all -> 0x0090 }
            java.lang.String r2 = r2.getAbsolutePath()     // Catch:{ all -> 0x0090 }
            r5[r4] = r2     // Catch:{ all -> 0x0090 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x0090 }
            r5[r3] = r0     // Catch:{ all -> 0x0090 }
            com.android.volley.VolleyLog.d(r7, r5)     // Catch:{ all -> 0x0090 }
            r13.remove(r14)     // Catch:{ all -> 0x0090 }
            if (r6 == 0) goto L_0x006c
            r6.close()     // Catch:{ IOException -> 0x006a }
            goto L_0x006c
        L_0x006a:
            monitor-exit(r13)
            return r1
        L_0x006c:
            monitor-exit(r13)
            return r1
        L_0x006e:
            r0 = move-exception
            r6 = r1
        L_0x0070:
            java.lang.String r7 = "%s: %s"
            java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch:{ all -> 0x0090 }
            java.lang.String r2 = r2.getAbsolutePath()     // Catch:{ all -> 0x0090 }
            r5[r4] = r2     // Catch:{ all -> 0x0090 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x0090 }
            r5[r3] = r0     // Catch:{ all -> 0x0090 }
            com.android.volley.VolleyLog.d(r7, r5)     // Catch:{ all -> 0x0090 }
            r13.remove(r14)     // Catch:{ all -> 0x0090 }
            if (r6 == 0) goto L_0x008e
            r6.close()     // Catch:{ IOException -> 0x008c }
            goto L_0x008e
        L_0x008c:
            monitor-exit(r13)
            return r1
        L_0x008e:
            monitor-exit(r13)
            return r1
        L_0x0090:
            r14 = move-exception
        L_0x0091:
            if (r6 == 0) goto L_0x0099
            r6.close()     // Catch:{ IOException -> 0x0097 }
            goto L_0x0099
        L_0x0097:
            monitor-exit(r13)
            return r1
        L_0x0099:
            throw r14     // Catch:{ all -> 0x009a }
        L_0x009a:
            r14 = move-exception
            monitor-exit(r13)
            throw r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.volley.toolbox.DiskBasedCache.get(java.lang.String):com.android.volley.Cache$Entry");
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(5:26|27|(2:36|37)|38|39) */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0023, code lost:
        return;
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:38:0x0065 */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x005c A[SYNTHETIC, Splitter:B:33:0x005c] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0068 A[SYNTHETIC, Splitter:B:41:0x0068] */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x006b A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void initialize() {
        /*
            r8 = this;
            monitor-enter(r8)
            java.io.File r0 = r8.mRootDirectory     // Catch:{ all -> 0x0070 }
            boolean r0 = r0.exists()     // Catch:{ all -> 0x0070 }
            r1 = 0
            if (r0 != 0) goto L_0x0024
            java.io.File r0 = r8.mRootDirectory     // Catch:{ all -> 0x0070 }
            boolean r0 = r0.mkdirs()     // Catch:{ all -> 0x0070 }
            if (r0 != 0) goto L_0x0022
            java.lang.String r0 = "Unable to create cache dir %s"
            r2 = 1
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ all -> 0x0070 }
            java.io.File r3 = r8.mRootDirectory     // Catch:{ all -> 0x0070 }
            java.lang.String r3 = r3.getAbsolutePath()     // Catch:{ all -> 0x0070 }
            r2[r1] = r3     // Catch:{ all -> 0x0070 }
            com.android.volley.VolleyLog.e(r0, r2)     // Catch:{ all -> 0x0070 }
        L_0x0022:
            monitor-exit(r8)
            return
        L_0x0024:
            java.io.File r0 = r8.mRootDirectory     // Catch:{ all -> 0x0070 }
            java.io.File[] r0 = r0.listFiles()     // Catch:{ all -> 0x0070 }
            if (r0 != 0) goto L_0x002e
            monitor-exit(r8)
            return
        L_0x002e:
            int r2 = r0.length     // Catch:{ all -> 0x0070 }
        L_0x002f:
            if (r1 >= r2) goto L_0x006e
            r3 = r0[r1]     // Catch:{ all -> 0x0070 }
            r4 = 0
            java.io.BufferedInputStream r5 = new java.io.BufferedInputStream     // Catch:{ IOException -> 0x005a }
            java.io.FileInputStream r6 = new java.io.FileInputStream     // Catch:{ IOException -> 0x005a }
            r6.<init>(r3)     // Catch:{ IOException -> 0x005a }
            r5.<init>(r6)     // Catch:{ IOException -> 0x005a }
            com.android.volley.toolbox.DiskBasedCache$CacheHeader r4 = com.android.volley.toolbox.DiskBasedCache.CacheHeader.readHeader(r5)     // Catch:{ IOException -> 0x0056, all -> 0x0053 }
            long r6 = r3.length()     // Catch:{ IOException -> 0x0056, all -> 0x0053 }
            r4.size = r6     // Catch:{ IOException -> 0x0056, all -> 0x0053 }
            java.lang.String r6 = r4.key     // Catch:{ IOException -> 0x0056, all -> 0x0053 }
            r8.putEntry(r6, r4)     // Catch:{ IOException -> 0x0056, all -> 0x0053 }
            if (r5 == 0) goto L_0x006b
            r5.close()     // Catch:{ IOException -> 0x006b }
            goto L_0x006b
        L_0x0053:
            r0 = move-exception
            r4 = r5
            goto L_0x0060
        L_0x0056:
            r4 = r5
            goto L_0x005a
        L_0x0058:
            r0 = move-exception
            goto L_0x0060
        L_0x005a:
            if (r3 == 0) goto L_0x0066
            r3.delete()     // Catch:{ all -> 0x0058 }
            goto L_0x0066
        L_0x0060:
            if (r4 == 0) goto L_0x0065
            r4.close()     // Catch:{ IOException -> 0x0065 }
        L_0x0065:
            throw r0     // Catch:{ all -> 0x0070 }
        L_0x0066:
            if (r4 == 0) goto L_0x006b
            r4.close()     // Catch:{ IOException -> 0x006b }
        L_0x006b:
            int r1 = r1 + 1
            goto L_0x002f
        L_0x006e:
            monitor-exit(r8)
            return
        L_0x0070:
            r0 = move-exception
            monitor-exit(r8)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.volley.toolbox.DiskBasedCache.initialize():void");
    }

    public synchronized void invalidate(String str, boolean z) {
        Entry entry = get(str);
        if (entry != null) {
            entry.softTtl = 0;
            if (z) {
                entry.ttl = 0;
            }
            put(str, entry);
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(5:12|13|(1:15)|16|17) */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0044, code lost:
        if (r0.delete() == false) goto L_0x0046;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0046, code lost:
        com.android.volley.VolleyLog.d("Could not clean up file %s", r0.getAbsolutePath());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0054, code lost:
        return;
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:12:0x0040 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void put(java.lang.String r7, com.android.volley.Cache.Entry r8) {
        /*
            r6 = this;
            monitor-enter(r6)
            byte[] r0 = r8.data     // Catch:{ all -> 0x0055 }
            int r0 = r0.length     // Catch:{ all -> 0x0055 }
            r6.pruneIfNeeded(r0)     // Catch:{ all -> 0x0055 }
            java.io.File r0 = r6.getFileForKey(r7)     // Catch:{ all -> 0x0055 }
            r1 = 0
            r2 = 1
            java.io.FileOutputStream r3 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x0040 }
            r3.<init>(r0)     // Catch:{ IOException -> 0x0040 }
            com.android.volley.toolbox.DiskBasedCache$CacheHeader r4 = new com.android.volley.toolbox.DiskBasedCache$CacheHeader     // Catch:{ IOException -> 0x0040 }
            r4.<init>(r7, r8)     // Catch:{ IOException -> 0x0040 }
            boolean r5 = r4.writeHeader(r3)     // Catch:{ IOException -> 0x0040 }
            if (r5 != 0) goto L_0x0033
            r3.close()     // Catch:{ IOException -> 0x0040 }
            java.lang.String r7 = "Failed to write header for %s"
            java.lang.Object[] r8 = new java.lang.Object[r2]     // Catch:{ IOException -> 0x0040 }
            java.lang.String r3 = r0.getAbsolutePath()     // Catch:{ IOException -> 0x0040 }
            r8[r1] = r3     // Catch:{ IOException -> 0x0040 }
            com.android.volley.VolleyLog.d(r7, r8)     // Catch:{ IOException -> 0x0040 }
            java.io.IOException r7 = new java.io.IOException     // Catch:{ IOException -> 0x0040 }
            r7.<init>()     // Catch:{ IOException -> 0x0040 }
            throw r7     // Catch:{ IOException -> 0x0040 }
        L_0x0033:
            byte[] r8 = r8.data     // Catch:{ IOException -> 0x0040 }
            r3.write(r8)     // Catch:{ IOException -> 0x0040 }
            r3.close()     // Catch:{ IOException -> 0x0040 }
            r6.putEntry(r7, r4)     // Catch:{ IOException -> 0x0040 }
            monitor-exit(r6)
            return
        L_0x0040:
            boolean r7 = r0.delete()     // Catch:{ all -> 0x0055 }
            if (r7 != 0) goto L_0x0053
            java.lang.String r7 = "Could not clean up file %s"
            java.lang.Object[] r8 = new java.lang.Object[r2]     // Catch:{ all -> 0x0055 }
            java.lang.String r0 = r0.getAbsolutePath()     // Catch:{ all -> 0x0055 }
            r8[r1] = r0     // Catch:{ all -> 0x0055 }
            com.android.volley.VolleyLog.d(r7, r8)     // Catch:{ all -> 0x0055 }
        L_0x0053:
            monitor-exit(r6)
            return
        L_0x0055:
            r7 = move-exception
            monitor-exit(r6)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.volley.toolbox.DiskBasedCache.put(java.lang.String, com.android.volley.Cache$Entry):void");
    }

    public synchronized void remove(String str) {
        boolean delete = getFileForKey(str).delete();
        removeEntry(str);
        if (!delete) {
            VolleyLog.d("Could not delete cache entry for key=%s, filename=%s", str, getFilenameForKey(str));
        }
    }

    private String getFilenameForKey(String str) {
        int length = str.length() / 2;
        String valueOf = String.valueOf(str.substring(0, length).hashCode());
        StringBuilder sb = new StringBuilder();
        sb.append(valueOf);
        sb.append(String.valueOf(str.substring(length).hashCode()));
        return sb.toString();
    }

    public File getFileForKey(String str) {
        return new File(this.mRootDirectory, getFilenameForKey(str));
    }

    private void pruneIfNeeded(int i) {
        long j;
        long j2;
        long j3 = (long) i;
        if (this.mTotalSize + j3 >= ((long) this.mMaxCacheSizeInBytes)) {
            if (VolleyLog.DEBUG) {
                VolleyLog.v("Pruning old cache entries.", new Object[0]);
            }
            long j4 = this.mTotalSize;
            long elapsedRealtime = SystemClock.elapsedRealtime();
            Iterator it = this.mEntries.entrySet().iterator();
            int i2 = 0;
            while (true) {
                if (!it.hasNext()) {
                    j = elapsedRealtime;
                    break;
                }
                CacheHeader cacheHeader = (CacheHeader) ((Map.Entry) it.next()).getValue();
                if (getFileForKey(cacheHeader.key).delete()) {
                    j2 = j3;
                    j = elapsedRealtime;
                    this.mTotalSize -= cacheHeader.size;
                } else {
                    j2 = j3;
                    j = elapsedRealtime;
                    VolleyLog.d("Could not delete cache entry for key=%s, filename=%s", cacheHeader.key, getFilenameForKey(cacheHeader.key));
                }
                it.remove();
                i2++;
                if (((float) (this.mTotalSize + j2)) < ((float) this.mMaxCacheSizeInBytes) * HYSTERESIS_FACTOR) {
                    break;
                }
                j3 = j2;
                elapsedRealtime = j;
            }
            if (VolleyLog.DEBUG) {
                VolleyLog.v("pruned %d files, %d bytes, %d ms", Integer.valueOf(i2), Long.valueOf(this.mTotalSize - j4), Long.valueOf(SystemClock.elapsedRealtime() - j));
            }
        }
    }

    private void putEntry(String str, CacheHeader cacheHeader) {
        if (!this.mEntries.containsKey(str)) {
            this.mTotalSize += cacheHeader.size;
        } else {
            this.mTotalSize += cacheHeader.size - ((CacheHeader) this.mEntries.get(str)).size;
        }
        this.mEntries.put(str, cacheHeader);
    }

    private void removeEntry(String str) {
        CacheHeader cacheHeader = (CacheHeader) this.mEntries.get(str);
        if (cacheHeader != null) {
            this.mTotalSize -= cacheHeader.size;
            this.mEntries.remove(str);
        }
    }

    private static byte[] streamToBytes(InputStream inputStream, int i) throws IOException {
        try {
            byte[] bArr = new byte[i];
            int i2 = 0;
            while (i2 < i) {
                int read = inputStream.read(bArr, i2, i - i2);
                if (read == -1) {
                    break;
                }
                i2 += read;
            }
            if (i2 == i) {
                return bArr;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Expected ");
            sb.append(i);
            sb.append(" bytes, read ");
            sb.append(i2);
            sb.append(" bytes");
            throw new IOException(sb.toString());
        } catch (OutOfMemoryError unused) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Couldn't allocate ");
            sb2.append(i);
            sb2.append(" bytes to stream. May have parsed the stream length incorrectly");
            throw new IOException(sb2.toString());
        }
    }

    private static int read(InputStream inputStream) throws IOException {
        int read = inputStream.read();
        if (read != -1) {
            return read;
        }
        throw new EOFException();
    }

    static void writeInt(OutputStream outputStream, int i) throws IOException {
        outputStream.write((i >> 0) & 255);
        outputStream.write((i >> 8) & 255);
        outputStream.write((i >> 16) & 255);
        outputStream.write((i >> 24) & 255);
    }

    static int readInt(InputStream inputStream) throws IOException {
        return (read(inputStream) << 24) | (read(inputStream) << 0) | 0 | (read(inputStream) << 8) | (read(inputStream) << 16);
    }

    static void writeLong(OutputStream outputStream, long j) throws IOException {
        outputStream.write((byte) ((int) (j >>> 0)));
        outputStream.write((byte) ((int) (j >>> 8)));
        outputStream.write((byte) ((int) (j >>> 16)));
        outputStream.write((byte) ((int) (j >>> 24)));
        outputStream.write((byte) ((int) (j >>> 32)));
        outputStream.write((byte) ((int) (j >>> 40)));
        outputStream.write((byte) ((int) (j >>> 48)));
        outputStream.write((byte) ((int) (j >>> 56)));
    }

    static long readLong(InputStream inputStream) throws IOException {
        return 0 | ((((long) read(inputStream)) & 255) << 0) | ((((long) read(inputStream)) & 255) << 8) | ((((long) read(inputStream)) & 255) << 16) | ((((long) read(inputStream)) & 255) << 24) | ((((long) read(inputStream)) & 255) << 32) | ((((long) read(inputStream)) & 255) << 40) | ((((long) read(inputStream)) & 255) << 48) | ((((long) read(inputStream)) & 255) << 56);
    }

    static void writeString(OutputStream outputStream, String str) throws IOException {
        byte[] bytes = str.getBytes("UTF-8");
        writeLong(outputStream, (long) bytes.length);
        outputStream.write(bytes, 0, bytes.length);
    }

    static String readString(InputStream inputStream) throws IOException {
        return new String(streamToBytes(inputStream, (int) readLong(inputStream)), "UTF-8");
    }

    static void writeStringStringMap(Map<String, String> map, OutputStream outputStream) throws IOException {
        if (map != null) {
            writeInt(outputStream, map.size());
            for (Map.Entry entry : map.entrySet()) {
                writeString(outputStream, (String) entry.getKey());
                writeString(outputStream, (String) entry.getValue());
            }
            return;
        }
        writeInt(outputStream, 0);
    }

    static Map<String, String> readStringStringMap(InputStream inputStream) throws IOException {
        int readInt = readInt(inputStream);
        Map<String, String> emptyMap = readInt == 0 ? Collections.emptyMap() : new HashMap<>(readInt);
        for (int i = 0; i < readInt; i++) {
            emptyMap.put(readString(inputStream).intern(), readString(inputStream).intern());
        }
        return emptyMap;
    }
}
