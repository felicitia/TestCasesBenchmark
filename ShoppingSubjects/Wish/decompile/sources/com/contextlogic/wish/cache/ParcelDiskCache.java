package com.contextlogic.wish.cache;

import android.os.Parcel;
import android.os.Parcelable;
import com.contextlogic.wish.http.DiskLruCache;
import com.contextlogic.wish.http.DiskLruCache.Editor;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class ParcelDiskCache {
    private static ParcelDiskCache sInstance = new ParcelDiskCache();
    private DiskLruCache mCache;
    private Executor mStoreExecutor;

    private static class StoreParcelableValueTask implements Runnable {
        private final DiskLruCache mSaveCache;
        private final String mSaveKey;
        private final Parcel mSaveValue;

        public StoreParcelableValueTask(DiskLruCache diskLruCache, Parcel parcel, String str) {
            this.mSaveValue = parcel;
            this.mSaveKey = str;
            this.mSaveCache = diskLruCache;
        }

        public void run() {
            ParcelDiskCache.saveValue(this.mSaveCache, this.mSaveValue, this.mSaveKey);
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(7:4|5|6|(1:8)|9|10|(1:12)) */
    /* JADX WARNING: Can't wrap try/catch for region: R(8:15|16|17|18|(1:20)|21|22|(1:24)) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0051 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x002c */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0032  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0057  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private ParcelDiskCache() {
        /*
            r4 = this;
            r4.<init>()
            r0 = 0
            java.lang.String r1 = android.os.Environment.getExternalStorageState()     // Catch:{ Throwable -> 0x0033 }
            java.lang.String r2 = "mounted"
            boolean r1 = r1.equals(r2)     // Catch:{ Throwable -> 0x0033 }
            if (r1 == 0) goto L_0x0033
            java.io.File r1 = new java.io.File     // Catch:{ Throwable -> 0x0033 }
            com.contextlogic.wish.application.WishApplication r2 = com.contextlogic.wish.application.WishApplication.getInstance()     // Catch:{ Throwable -> 0x0033 }
            java.io.File r2 = r2.getExternalCacheDir()     // Catch:{ Throwable -> 0x0033 }
            java.lang.String r2 = r2.getAbsolutePath()     // Catch:{ Throwable -> 0x0033 }
            java.lang.String r3 = "wish-parcelable-cache"
            r1.<init>(r2, r3)     // Catch:{ Throwable -> 0x0033 }
            boolean r2 = r1.exists()     // Catch:{ Throwable -> 0x002c }
            if (r2 != 0) goto L_0x002c
            r1.mkdirs()     // Catch:{ Throwable -> 0x002c }
        L_0x002c:
            boolean r2 = r1.exists()     // Catch:{ Throwable -> 0x0033 }
            if (r2 == 0) goto L_0x0033
            r0 = r1
        L_0x0033:
            if (r0 != 0) goto L_0x0058
            java.io.File r1 = new java.io.File     // Catch:{ Throwable -> 0x0058 }
            com.contextlogic.wish.application.WishApplication r2 = com.contextlogic.wish.application.WishApplication.getInstance()     // Catch:{ Throwable -> 0x0058 }
            java.io.File r2 = r2.getCacheDir()     // Catch:{ Throwable -> 0x0058 }
            java.lang.String r2 = r2.getAbsolutePath()     // Catch:{ Throwable -> 0x0058 }
            java.lang.String r3 = "wish-parcelable-cache"
            r1.<init>(r2, r3)     // Catch:{ Throwable -> 0x0058 }
            boolean r2 = r1.exists()     // Catch:{ Throwable -> 0x0051 }
            if (r2 != 0) goto L_0x0051
            r1.mkdirs()     // Catch:{ Throwable -> 0x0051 }
        L_0x0051:
            boolean r2 = r1.exists()     // Catch:{ Throwable -> 0x0058 }
            if (r2 == 0) goto L_0x0058
            r0 = r1
        L_0x0058:
            java.util.concurrent.ExecutorService r1 = java.util.concurrent.Executors.newSingleThreadExecutor()
            r4.mStoreExecutor = r1
            if (r0 == 0) goto L_0x006b
            long r1 = com.contextlogic.wish.http.WishHttpClient.calculateOptimalCacheSize(r0)     // Catch:{ Exception -> 0x006b }
            r3 = 1
            com.contextlogic.wish.http.DiskLruCache r0 = com.contextlogic.wish.http.DiskLruCache.open(r0, r3, r3, r1)     // Catch:{ Exception -> 0x006b }
            r4.mCache = r0     // Catch:{ Exception -> 0x006b }
        L_0x006b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.contextlogic.wish.cache.ParcelDiskCache.<init>():void");
    }

    public static ParcelDiskCache getInstance() {
        return sInstance;
    }

    public void set(String str, Parcelable parcelable, boolean z) {
        if (this.mCache != null) {
            String validateKey = validateKey(str);
            Parcel obtain = Parcel.obtain();
            obtain.writeString("TYPE_PARCELABLE");
            obtain.writeParcelable(parcelable, 0);
            if (z) {
                saveValue(this.mCache, obtain, validateKey);
            } else {
                this.mStoreExecutor.execute(new StoreParcelableValueTask(this.mCache, obtain, validateKey));
            }
        }
    }

    public <T> void set(String str, List<T> list, boolean z) {
        if (this.mCache != null) {
            String validateKey = validateKey(str);
            Parcel obtain = Parcel.obtain();
            obtain.writeString("TYPE_LIST");
            obtain.writeList(list);
            if (z) {
                saveValue(this.mCache, obtain, validateKey);
            } else {
                this.mStoreExecutor.execute(new StoreParcelableValueTask(this.mCache, obtain, validateKey));
            }
        }
    }

    public <T extends Parcelable> T get(String str, Class cls) {
        Parcel parcel = getParcel(validateKey(str));
        if (parcel != null) {
            try {
                String readString = parcel.readString();
                if (readString.equals("TYPE_LIST")) {
                    throw new IllegalAccessError("get list data with getList method");
                } else if (readString == null || readString.equals("TYPE_PARCELABLE")) {
                    return parcel.readParcelable(cls.getClassLoader());
                } else {
                    throw new IllegalAccessError("Parcel doesn't contain parcelable data");
                }
            } catch (Exception unused) {
            } finally {
                parcel.recycle();
            }
        }
        return null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x002d  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0034  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.os.Parcel getParcel(java.lang.String r4) {
        /*
            r3 = this;
            com.contextlogic.wish.http.DiskLruCache r0 = r3.mCache
            r1 = 0
            if (r0 != 0) goto L_0x0006
            return r1
        L_0x0006:
            java.lang.String r4 = r3.validateKey(r4)
            r0 = 0
            com.contextlogic.wish.http.DiskLruCache r2 = r3.mCache     // Catch:{ Throwable -> 0x0031, all -> 0x0029 }
            com.contextlogic.wish.http.DiskLruCache$Snapshot r4 = r2.get(r4)     // Catch:{ Throwable -> 0x0031, all -> 0x0029 }
            if (r4 != 0) goto L_0x0019
            if (r4 == 0) goto L_0x0018
            r4.close()
        L_0x0018:
            return r1
        L_0x0019:
            java.io.InputStream r2 = r4.getInputStream(r0)     // Catch:{ Throwable -> 0x0032, all -> 0x0027 }
            byte[] r2 = getBytesFromStream(r2)     // Catch:{ Throwable -> 0x0032, all -> 0x0027 }
            if (r4 == 0) goto L_0x0038
            r4.close()
            goto L_0x0038
        L_0x0027:
            r0 = move-exception
            goto L_0x002b
        L_0x0029:
            r0 = move-exception
            r4 = r1
        L_0x002b:
            if (r4 == 0) goto L_0x0030
            r4.close()
        L_0x0030:
            throw r0
        L_0x0031:
            r4 = r1
        L_0x0032:
            if (r4 == 0) goto L_0x0037
            r4.close()
        L_0x0037:
            r2 = r1
        L_0x0038:
            android.os.Parcel r4 = android.os.Parcel.obtain()     // Catch:{ Throwable -> 0x0044 }
            int r1 = r2.length     // Catch:{ Throwable -> 0x0045 }
            r4.unmarshall(r2, r0, r1)     // Catch:{ Throwable -> 0x0045 }
            r4.setDataPosition(r0)     // Catch:{ Throwable -> 0x0045 }
            goto L_0x0045
        L_0x0044:
            r4 = r1
        L_0x0045:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.contextlogic.wish.cache.ParcelDiskCache.getParcel(java.lang.String):android.os.Parcel");
    }

    private String validateKey(String str) {
        Matcher matcher = getPattern("[a-z0-9_-]{1,5}").matcher(str);
        StringBuilder sb = new StringBuilder();
        while (matcher.find()) {
            String group = matcher.group();
            if (sb.length() + group.length() > 62) {
                break;
            }
            sb.append(group);
        }
        return sb.toString().toLowerCase();
    }

    public Pattern getPattern(String str) {
        return Pattern.compile(str, 42);
    }

    /* JADX WARNING: Incorrect type for immutable var: ssa=java.lang.Class, code=java.lang.Class<java.util.ArrayList>, for r5v0, types: [java.lang.Class] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public <T extends android.os.Parcelable> java.util.ArrayList<T> getList(java.lang.String r4, java.lang.Class<java.util.ArrayList> r5) {
        /*
            r3 = this;
            java.lang.String r4 = r3.validateKey(r4)
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            android.os.Parcel r4 = r3.getParcel(r4)
            if (r4 == 0) goto L_0x004b
            java.lang.String r1 = r4.readString()     // Catch:{ Throwable -> 0x0048, all -> 0x0043 }
            java.lang.String r2 = "TYPE_PARCELABLE"
            boolean r2 = r1.equals(r2)     // Catch:{ Throwable -> 0x0048, all -> 0x0043 }
            if (r2 == 0) goto L_0x0023
            java.lang.IllegalAccessError r5 = new java.lang.IllegalAccessError     // Catch:{ Throwable -> 0x0048, all -> 0x0043 }
            java.lang.String r1 = "Get not a list data with get method"
            r5.<init>(r1)     // Catch:{ Throwable -> 0x0048, all -> 0x0043 }
            throw r5     // Catch:{ Throwable -> 0x0048, all -> 0x0043 }
        L_0x0023:
            if (r1 == 0) goto L_0x0035
            java.lang.String r2 = "TYPE_LIST"
            boolean r1 = r1.equals(r2)     // Catch:{ Throwable -> 0x0048, all -> 0x0043 }
            if (r1 != 0) goto L_0x0035
            java.lang.IllegalAccessError r5 = new java.lang.IllegalAccessError     // Catch:{ Throwable -> 0x0048, all -> 0x0043 }
            java.lang.String r1 = "Parcel doesn't contain list data"
            r5.<init>(r1)     // Catch:{ Throwable -> 0x0048, all -> 0x0043 }
            throw r5     // Catch:{ Throwable -> 0x0048, all -> 0x0043 }
        L_0x0035:
            if (r5 == 0) goto L_0x003c
        L_0x0037:
            java.lang.ClassLoader r5 = r5.getClassLoader()     // Catch:{ Throwable -> 0x0048, all -> 0x0043 }
            goto L_0x003f
        L_0x003c:
            java.lang.Class<java.util.ArrayList> r5 = java.util.ArrayList.class
            goto L_0x0037
        L_0x003f:
            r4.readList(r0, r5)     // Catch:{ Throwable -> 0x0048, all -> 0x0043 }
            goto L_0x0048
        L_0x0043:
            r5 = move-exception
            r4.recycle()
            throw r5
        L_0x0048:
            r4.recycle()
        L_0x004b:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.contextlogic.wish.cache.ParcelDiskCache.getList(java.lang.String, java.lang.Class):java.util.ArrayList");
    }

    public boolean remove(String str) {
        if (this.mCache == null) {
            return false;
        }
        try {
            return this.mCache.remove(validateKey(str).toLowerCase());
        } catch (IOException unused) {
            return false;
        }
    }

    /* access modifiers changed from: private */
    public static void saveValue(DiskLruCache diskLruCache, Parcel parcel, String str) {
        if (diskLruCache != null) {
            String lowerCase = str.toLowerCase();
            try {
                synchronized (lowerCase.intern()) {
                    Editor edit = diskLruCache.edit(lowerCase);
                    writeBytesToStream(edit.newOutputStream(0), parcel.marshall());
                    edit.commit();
                }
            } catch (Throwable th) {
                parcel.recycle();
                throw th;
            }
            parcel.recycle();
        }
    }

    public static byte[] getBytesFromStream(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byte[] bArr = new byte[1024];
            while (true) {
                int read = inputStream.read(bArr, 0, bArr.length);
                if (read != -1) {
                    byteArrayOutputStream.write(bArr, 0, read);
                } else {
                    byteArrayOutputStream.flush();
                    return byteArrayOutputStream.toByteArray();
                }
            }
        } finally {
            inputStream.close();
            byteArrayOutputStream.close();
        }
    }

    public static void writeBytesToStream(OutputStream outputStream, byte[] bArr) throws IOException {
        outputStream.write(bArr);
        outputStream.flush();
        outputStream.close();
    }
}
