package com.contextlogic.wish.http;

import android.app.Activity;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import com.contextlogic.wish.http.DiskLruCache.Editor;
import com.contextlogic.wish.http.DiskLruCache.Snapshot;
import com.contextlogic.wish.http.ImageHttpManager.LoadingSource;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;

public class ImageHttpCache {
    private static ImageHttpCache sInstance = new ImageHttpCache();
    private LruCache<String, Bitmap> mActivityCache;
    private ExecutorService mCacheInitializationThreadPool;
    private ExecutorService mCacheWriteThreadPool;
    /* access modifiers changed from: private */
    public DiskLruCache mDiskCache;
    /* access modifiers changed from: private */
    public File mExternalDiskCache;
    /* access modifiers changed from: private */
    public File mInternalDiskCache;
    private LruCache<String, CachedObject> mMemoryCache;
    private ConcurrentHashMap<Long, byte[]> mReadBufferCache;

    public interface CacheClearCallback {
        void onCacheCleared();
    }

    private static class CachedObject {
        private long mExpiryTime;
        private byte[] mObject;

        public CachedObject(long j, byte[] bArr) {
            this.mExpiryTime = j;
            this.mObject = bArr;
        }

        public long getExpiryTime() {
            return this.mExpiryTime;
        }

        public byte[] getObject() {
            return this.mObject;
        }
    }

    public static class ExpirableBitmap {
        private Bitmap mBitmap;
        private long mExpiryTime;
        private LoadingSource mLoadingSource;

        public ExpirableBitmap(Bitmap bitmap, long j, LoadingSource loadingSource) {
            this.mBitmap = bitmap;
            this.mExpiryTime = j;
            this.mLoadingSource = loadingSource;
        }

        public long getExpiryTime() {
            return this.mExpiryTime;
        }

        public Bitmap getBitmap() {
            return this.mBitmap;
        }

        public LoadingSource getLoadingSource() {
            return this.mLoadingSource;
        }
    }

    public void initialize() {
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(7:4|5|6|(1:8)|9|10|(1:12)) */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0033 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x004f */
    /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x002b */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0031 A[Catch:{ Throwable -> 0x0033 }] */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x004c A[Catch:{ Throwable -> 0x004f }] */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0055 A[Catch:{ Throwable -> 0x0057 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private ImageHttpCache() {
        /*
            r3 = this;
            r3.<init>()
            java.lang.String r0 = android.os.Environment.getExternalStorageState()     // Catch:{ Throwable -> 0x0033 }
            java.lang.String r1 = "mounted"
            boolean r0 = r0.equals(r1)     // Catch:{ Throwable -> 0x0033 }
            if (r0 == 0) goto L_0x0033
            java.io.File r0 = new java.io.File     // Catch:{ Throwable -> 0x0033 }
            com.contextlogic.wish.application.WishApplication r1 = com.contextlogic.wish.application.WishApplication.getInstance()     // Catch:{ Throwable -> 0x0033 }
            java.io.File r1 = r1.getExternalCacheDir()     // Catch:{ Throwable -> 0x0033 }
            java.lang.String r1 = r1.getAbsolutePath()     // Catch:{ Throwable -> 0x0033 }
            java.lang.String r2 = "wish-http-image-cache"
            r0.<init>(r1, r2)     // Catch:{ Throwable -> 0x0033 }
            boolean r1 = r0.exists()     // Catch:{ Throwable -> 0x002b }
            if (r1 != 0) goto L_0x002b
            r0.mkdirs()     // Catch:{ Throwable -> 0x002b }
        L_0x002b:
            boolean r1 = r0.exists()     // Catch:{ Throwable -> 0x0033 }
            if (r1 == 0) goto L_0x0033
            r3.mExternalDiskCache = r0     // Catch:{ Throwable -> 0x0033 }
        L_0x0033:
            java.io.File r0 = new java.io.File     // Catch:{ Throwable -> 0x0057 }
            com.contextlogic.wish.application.WishApplication r1 = com.contextlogic.wish.application.WishApplication.getInstance()     // Catch:{ Throwable -> 0x0057 }
            java.io.File r1 = r1.getCacheDir()     // Catch:{ Throwable -> 0x0057 }
            java.lang.String r1 = r1.getAbsolutePath()     // Catch:{ Throwable -> 0x0057 }
            java.lang.String r2 = "wish-http-image-cache"
            r0.<init>(r1, r2)     // Catch:{ Throwable -> 0x0057 }
            boolean r1 = r0.exists()     // Catch:{ Throwable -> 0x004f }
            if (r1 != 0) goto L_0x004f
            r0.mkdirs()     // Catch:{ Throwable -> 0x004f }
        L_0x004f:
            boolean r1 = r0.exists()     // Catch:{ Throwable -> 0x0057 }
            if (r1 == 0) goto L_0x0057
            r3.mInternalDiskCache = r0     // Catch:{ Throwable -> 0x0057 }
        L_0x0057:
            r0 = 2
            com.contextlogic.wish.http.ImageHttpCache$1 r1 = new com.contextlogic.wish.http.ImageHttpCache$1
            r1.<init>()
            java.util.concurrent.ExecutorService r0 = java.util.concurrent.Executors.newFixedThreadPool(r0, r1)
            r3.mCacheWriteThreadPool = r0
            r0 = 1
            com.contextlogic.wish.http.ImageHttpCache$2 r1 = new com.contextlogic.wish.http.ImageHttpCache$2
            r1.<init>()
            java.util.concurrent.ExecutorService r0 = java.util.concurrent.Executors.newFixedThreadPool(r0, r1)
            r3.mCacheInitializationThreadPool = r0
            java.util.concurrent.ConcurrentHashMap r0 = new java.util.concurrent.ConcurrentHashMap
            r0.<init>()
            r3.mReadBufferCache = r0
            r3.initializeDiskCache()
            com.contextlogic.wish.http.ImageHttpCache$3 r0 = new com.contextlogic.wish.http.ImageHttpCache$3
            r1 = 5242880(0x500000, float:7.34684E-39)
            r0.<init>(r1)
            r3.mMemoryCache = r0
            com.contextlogic.wish.http.ImageHttpCache$4 r0 = new com.contextlogic.wish.http.ImageHttpCache$4
            r0.<init>(r1)
            r3.mActivityCache = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.contextlogic.wish.http.ImageHttpCache.<init>():void");
    }

    public static ImageHttpCache getInstance() {
        return sInstance;
    }

    /* access modifiers changed from: private */
    public void initializeDiskCache() {
        if (this.mExternalDiskCache != null) {
            this.mCacheInitializationThreadPool.execute(new Runnable() {
                public void run() {
                    try {
                        ImageHttpCache.this.mDiskCache = DiskLruCache.open(ImageHttpCache.this.mExternalDiskCache, 1, 1, WishHttpClient.calculateOptimalCacheSize(ImageHttpCache.this.mExternalDiskCache));
                    } catch (Throwable unused) {
                    }
                }
            });
        } else if (this.mInternalDiskCache != null) {
            this.mCacheInitializationThreadPool.execute(new Runnable() {
                public void run() {
                    try {
                        ImageHttpCache.this.mDiskCache = DiskLruCache.open(ImageHttpCache.this.mInternalDiskCache, 1, 1, WishHttpClient.calculateOptimalCacheSize(ImageHttpCache.this.mInternalDiskCache));
                    } catch (Throwable unused) {
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public String getSanitizedCacheKey(String str) {
        if (str == null) {
            throw new IllegalArgumentException("cacheKey cannot be null");
        }
        StringBuilder sb = new StringBuilder(str.length());
        boolean z = false;
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (charAt != '.' && charAt != ':' && charAt != '/' && charAt != ',' && charAt != '%' && charAt != '?' && charAt != '&' && charAt != '=' && charAt != '+') {
                sb.append(charAt);
                z = false;
            } else if (!z) {
                sb.append('+');
                z = true;
            }
        }
        return sb.toString();
    }

    public Bitmap getCachedActivityBitmap(String str, Activity activity) {
        Bitmap bitmap;
        if (str == null) {
            return null;
        }
        synchronized (this.mActivityCache) {
            StringBuilder sb = new StringBuilder();
            sb.append(activity.hashCode());
            sb.append("$@$@");
            sb.append(str);
            String sb2 = sb.toString();
            bitmap = (Bitmap) this.mActivityCache.get(sb2);
            if (bitmap != null) {
                if (bitmap.isRecycled()) {
                    this.mActivityCache.remove(sb2);
                }
            }
            bitmap = null;
        }
        return bitmap;
    }

    public void cacheActivityBitmap(String str, Bitmap bitmap, Activity activity) {
        if (str != null) {
            synchronized (this.mActivityCache) {
                StringBuilder sb = new StringBuilder();
                sb.append(activity.hashCode());
                sb.append("$@$@");
                sb.append(str);
                this.mActivityCache.put(sb.toString(), bitmap);
            }
        }
    }

    public void clearActivityCache(Activity activity) {
        synchronized (this.mActivityCache) {
            Map snapshot = this.mActivityCache.snapshot();
            StringBuilder sb = new StringBuilder();
            sb.append(activity.hashCode());
            sb.append("$@$@");
            String sb2 = sb.toString();
            for (String str : snapshot.keySet()) {
                if (str.startsWith(sb2)) {
                    this.mActivityCache.remove(str);
                }
            }
        }
    }

    public boolean hasCachedBitmap(String str) {
        if (str == null) {
            return false;
        }
        if (((CachedObject) this.mMemoryCache.get(str)) != null) {
            return true;
        }
        if (this.mDiskCache != null) {
            try {
                Snapshot poll = this.mDiskCache.poll(getSanitizedCacheKey(str));
                return poll != null && !poll.isExpired();
            } catch (IOException unused) {
                return false;
            }
        }
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:34:0x00a9 */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0039 A[SYNTHETIC, Splitter:B:17:0x0039] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00be  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.contextlogic.wish.http.ImageHttpCache.ExpirableBitmap getCachedBitmap(java.lang.String r8, boolean r9, int r10, int r11) {
        /*
            r7 = this;
            r0 = 0
            if (r8 != 0) goto L_0x0004
            return r0
        L_0x0004:
            if (r9 != 0) goto L_0x0026
            android.support.v4.util.LruCache<java.lang.String, com.contextlogic.wish.http.ImageHttpCache$CachedObject> r1 = r7.mMemoryCache
            java.lang.Object r1 = r1.get(r8)
            com.contextlogic.wish.http.ImageHttpCache$CachedObject r1 = (com.contextlogic.wish.http.ImageHttpCache.CachedObject) r1
            if (r1 == 0) goto L_0x0026
            byte[] r2 = r1.getObject()
            android.graphics.Bitmap r2 = com.contextlogic.wish.util.BitmapUtil.decodeBitmapBytes(r2, r10, r11)
            if (r2 == 0) goto L_0x0026
            com.contextlogic.wish.http.ImageHttpCache$ExpirableBitmap r3 = new com.contextlogic.wish.http.ImageHttpCache$ExpirableBitmap
            long r4 = r1.getExpiryTime()
            com.contextlogic.wish.http.ImageHttpManager$LoadingSource r1 = com.contextlogic.wish.http.ImageHttpManager.LoadingSource.MEMORY
            r3.<init>(r2, r4, r1)
            goto L_0x0027
        L_0x0026:
            r3 = r0
        L_0x0027:
            if (r3 != 0) goto L_0x00c1
            com.contextlogic.wish.http.DiskLruCache r1 = r7.mDiskCache
            if (r1 == 0) goto L_0x00c1
            java.lang.String r1 = r7.getSanitizedCacheKey(r8)
            com.contextlogic.wish.http.DiskLruCache r2 = r7.mDiskCache     // Catch:{ IOException -> 0x00bb }
            com.contextlogic.wish.http.DiskLruCache$Snapshot r2 = r2.get(r1)     // Catch:{ IOException -> 0x00bb }
            if (r2 == 0) goto L_0x00c1
            boolean r0 = r2.isExpired()     // Catch:{ IOException -> 0x00bc }
            if (r0 != 0) goto L_0x00b2
            java.util.concurrent.ConcurrentHashMap<java.lang.Long, byte[]> r0 = r7.mReadBufferCache     // Catch:{ Throwable -> 0x00a9, all -> 0x00ad }
            java.lang.Thread r1 = java.lang.Thread.currentThread()     // Catch:{ Throwable -> 0x00a9, all -> 0x00ad }
            long r4 = r1.getId()     // Catch:{ Throwable -> 0x00a9, all -> 0x00ad }
            java.lang.Long r1 = java.lang.Long.valueOf(r4)     // Catch:{ Throwable -> 0x00a9, all -> 0x00ad }
            java.lang.Object r0 = r0.get(r1)     // Catch:{ Throwable -> 0x00a9, all -> 0x00ad }
            byte[] r0 = (byte[]) r0     // Catch:{ Throwable -> 0x00a9, all -> 0x00ad }
            if (r0 != 0) goto L_0x006a
            r0 = 4096(0x1000, float:5.74E-42)
            byte[] r0 = new byte[r0]     // Catch:{ Throwable -> 0x00a9, all -> 0x00ad }
            java.util.concurrent.ConcurrentHashMap<java.lang.Long, byte[]> r1 = r7.mReadBufferCache     // Catch:{ Throwable -> 0x00a9, all -> 0x00ad }
            java.lang.Thread r4 = java.lang.Thread.currentThread()     // Catch:{ Throwable -> 0x00a9, all -> 0x00ad }
            long r4 = r4.getId()     // Catch:{ Throwable -> 0x00a9, all -> 0x00ad }
            java.lang.Long r4 = java.lang.Long.valueOf(r4)     // Catch:{ Throwable -> 0x00a9, all -> 0x00ad }
            r1.put(r4, r0)     // Catch:{ Throwable -> 0x00a9, all -> 0x00ad }
        L_0x006a:
            long[] r1 = r2.getLengths()     // Catch:{ Throwable -> 0x00a9, all -> 0x00ad }
            r4 = 0
            if (r1 == 0) goto L_0x007f
            long[] r1 = r2.getLengths()     // Catch:{ Throwable -> 0x00a9, all -> 0x00ad }
            int r1 = r1.length     // Catch:{ Throwable -> 0x00a9, all -> 0x00ad }
            if (r1 <= 0) goto L_0x007f
            long[] r1 = r2.getLengths()     // Catch:{ Throwable -> 0x00a9, all -> 0x00ad }
            r5 = r1[r4]     // Catch:{ Throwable -> 0x00a9, all -> 0x00ad }
            goto L_0x0081
        L_0x007f:
            r5 = 0
        L_0x0081:
            java.io.InputStream r1 = r2.getInputStream(r4)     // Catch:{ Throwable -> 0x00a9, all -> 0x00ad }
            byte[] r0 = com.contextlogic.wish.util.FileUtil.getBytes(r1, r0, r5)     // Catch:{ Throwable -> 0x00a9, all -> 0x00ad }
            if (r9 != 0) goto L_0x009b
            com.contextlogic.wish.http.ImageHttpCache$ExpirableBitmap r9 = new com.contextlogic.wish.http.ImageHttpCache$ExpirableBitmap     // Catch:{ Throwable -> 0x00a9, all -> 0x00ad }
            android.graphics.Bitmap r10 = com.contextlogic.wish.util.BitmapUtil.decodeBitmapBytes(r0, r10, r11)     // Catch:{ Throwable -> 0x00a9, all -> 0x00ad }
            long r4 = r2.getExpiry()     // Catch:{ Throwable -> 0x00a9, all -> 0x00ad }
            com.contextlogic.wish.http.ImageHttpManager$LoadingSource r11 = com.contextlogic.wish.http.ImageHttpManager.LoadingSource.DISK     // Catch:{ Throwable -> 0x00a9, all -> 0x00ad }
            r9.<init>(r10, r4, r11)     // Catch:{ Throwable -> 0x00a9, all -> 0x00ad }
            r3 = r9
        L_0x009b:
            android.support.v4.util.LruCache<java.lang.String, com.contextlogic.wish.http.ImageHttpCache$CachedObject> r9 = r7.mMemoryCache     // Catch:{ Throwable -> 0x00a9, all -> 0x00ad }
            com.contextlogic.wish.http.ImageHttpCache$CachedObject r10 = new com.contextlogic.wish.http.ImageHttpCache$CachedObject     // Catch:{ Throwable -> 0x00a9, all -> 0x00ad }
            long r4 = r2.getExpiry()     // Catch:{ Throwable -> 0x00a9, all -> 0x00ad }
            r10.<init>(r4, r0)     // Catch:{ Throwable -> 0x00a9, all -> 0x00ad }
            r9.put(r8, r10)     // Catch:{ Throwable -> 0x00a9, all -> 0x00ad }
        L_0x00a9:
            r2.close()     // Catch:{ IOException -> 0x00bc }
            goto L_0x00c1
        L_0x00ad:
            r8 = move-exception
            r2.close()     // Catch:{ IOException -> 0x00bc }
            throw r8     // Catch:{ IOException -> 0x00bc }
        L_0x00b2:
            r2.close()     // Catch:{ IOException -> 0x00bc }
            com.contextlogic.wish.http.DiskLruCache r8 = r7.mDiskCache     // Catch:{ IOException -> 0x00bc }
            r8.remove(r1)     // Catch:{ IOException -> 0x00bc }
            goto L_0x00c1
        L_0x00bb:
            r2 = r0
        L_0x00bc:
            if (r2 == 0) goto L_0x00c1
            r2.close()
        L_0x00c1:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.contextlogic.wish.http.ImageHttpCache.getCachedBitmap(java.lang.String, boolean, int, int):com.contextlogic.wish.http.ImageHttpCache$ExpirableBitmap");
    }

    public void cacheBitmap(String str, long j, byte[] bArr) {
        cacheBitmap(str, j, bArr, false);
    }

    public void cacheBitmap(String str, long j, byte[] bArr, boolean z) {
        if (bArr != null) {
            if (!z) {
                this.mMemoryCache.put(str, new CachedObject(j, bArr));
            }
            if (this.mDiskCache != null) {
                ExecutorService executorService = this.mCacheWriteThreadPool;
                final String str2 = str;
                final long j2 = j;
                final byte[] bArr2 = bArr;
                AnonymousClass7 r0 = new Runnable() {
                    public void run() {
                        Editor editor = null;
                        try {
                            Editor edit = ImageHttpCache.this.mDiskCache.edit(ImageHttpCache.this.getSanitizedCacheKey(str2));
                            if (edit != null) {
                                try {
                                    edit.setExpiry(j2);
                                    edit.set(0, bArr2);
                                    edit.commit();
                                } catch (Throwable unused) {
                                    editor = edit;
                                }
                            }
                        } catch (Throwable unused2) {
                            if (editor != null) {
                                try {
                                    editor.abort();
                                } catch (Throwable unused3) {
                                }
                            }
                        }
                    }
                };
                executorService.execute(r0);
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0021 A[SYNTHETIC, Splitter:B:16:0x0021] */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0028 A[SYNTHETIC, Splitter:B:24:0x0028] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x002e A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x002f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void cacheBitmap(java.lang.String r9, long r10, android.graphics.Bitmap r12) {
        /*
            r8 = this;
            if (r12 != 0) goto L_0x0003
            return
        L_0x0003:
            r0 = 0
            java.io.ByteArrayOutputStream r1 = new java.io.ByteArrayOutputStream     // Catch:{ Throwable -> 0x0025, all -> 0x001d }
            r1.<init>()     // Catch:{ Throwable -> 0x0025, all -> 0x001d }
            android.graphics.Bitmap$CompressFormat r2 = android.graphics.Bitmap.CompressFormat.PNG     // Catch:{ Throwable -> 0x0026, all -> 0x001b }
            r3 = 100
            r12.compress(r2, r3, r1)     // Catch:{ Throwable -> 0x0026, all -> 0x001b }
            byte[] r12 = r1.toByteArray()     // Catch:{ Throwable -> 0x0026, all -> 0x001b }
            if (r1 == 0) goto L_0x0019
            r1.close()     // Catch:{ Throwable -> 0x0019 }
        L_0x0019:
            r7 = r12
            goto L_0x002c
        L_0x001b:
            r9 = move-exception
            goto L_0x001f
        L_0x001d:
            r9 = move-exception
            r1 = r0
        L_0x001f:
            if (r1 == 0) goto L_0x0024
            r1.close()     // Catch:{ Throwable -> 0x0024 }
        L_0x0024:
            throw r9
        L_0x0025:
            r1 = r0
        L_0x0026:
            if (r1 == 0) goto L_0x002b
            r1.close()     // Catch:{ Throwable -> 0x002b }
        L_0x002b:
            r7 = r0
        L_0x002c:
            if (r7 != 0) goto L_0x002f
            return
        L_0x002f:
            android.support.v4.util.LruCache<java.lang.String, com.contextlogic.wish.http.ImageHttpCache$CachedObject> r12 = r8.mMemoryCache
            com.contextlogic.wish.http.ImageHttpCache$CachedObject r0 = new com.contextlogic.wish.http.ImageHttpCache$CachedObject
            r0.<init>(r10, r7)
            r12.put(r9, r0)
            com.contextlogic.wish.http.DiskLruCache r12 = r8.mDiskCache
            if (r12 == 0) goto L_0x004b
            java.util.concurrent.ExecutorService r12 = r8.mCacheWriteThreadPool
            com.contextlogic.wish.http.ImageHttpCache$8 r0 = new com.contextlogic.wish.http.ImageHttpCache$8
            r2 = r0
            r3 = r8
            r4 = r9
            r5 = r10
            r2.<init>(r4, r5, r7)
            r12.execute(r0)
        L_0x004b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.contextlogic.wish.http.ImageHttpCache.cacheBitmap(java.lang.String, long, android.graphics.Bitmap):void");
    }

    public void clearCache(final CacheClearCallback cacheClearCallback) {
        this.mMemoryCache.evictAll();
        this.mActivityCache.evictAll();
        if (this.mDiskCache != null) {
            final DiskLruCache diskLruCache = this.mDiskCache;
            this.mDiskCache = null;
            this.mCacheInitializationThreadPool.execute(new Runnable() {
                public void run() {
                    try {
                        if (ImageHttpCache.this.mExternalDiskCache != null) {
                            ImageHttpCache.this.mExternalDiskCache.delete();
                            ImageHttpCache.this.mExternalDiskCache.mkdirs();
                        }
                        if (ImageHttpCache.this.mInternalDiskCache != null) {
                            ImageHttpCache.this.mInternalDiskCache.delete();
                            ImageHttpCache.this.mInternalDiskCache.mkdirs();
                        }
                        diskLruCache.close();
                    } catch (Throwable th) {
                        ImageHttpCache.this.initializeDiskCache();
                        throw th;
                    }
                    ImageHttpCache.this.initializeDiskCache();
                    cacheClearCallback.onCacheCleared();
                }
            });
        }
    }
}
