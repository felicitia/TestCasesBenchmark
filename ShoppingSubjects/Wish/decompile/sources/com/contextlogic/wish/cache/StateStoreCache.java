package com.contextlogic.wish.cache;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.util.LruCache;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class StateStoreCache {
    private static StateStoreCache sInstance = new StateStoreCache();
    private LruCache<String, Object> mCache = new LruCache<>(25);

    protected StateStoreCache() {
    }

    public static StateStoreCache getInstance() {
        return sInstance;
    }

    public <T extends Parcelable> T getParcelable(Bundle bundle, String str, Class cls) {
        if (bundle != null) {
            String string = bundle.getString(str);
            if (string != null) {
                return getParcelable(string, cls);
            }
        }
        return null;
    }

    public <T extends Parcelable> T getParcelable(String str, Class cls) {
        T t;
        synchronized (this.mCache) {
            t = (Parcelable) this.mCache.get(str);
        }
        return t == null ? ParcelDiskCache.getInstance().get(str, cls) : t;
    }

    public <T extends Parcelable> ArrayList<T> getParcelableList(Bundle bundle, String str, Class cls) {
        if (bundle != null) {
            String string = bundle.getString(str);
            if (string != null) {
                return getParcelableList(string, cls);
            }
        }
        return null;
    }

    public <T extends Parcelable> ArrayList<T> getParcelableList(String str, Class cls) {
        ArrayList<T> arrayList;
        synchronized (this.mCache) {
            arrayList = (ArrayList) this.mCache.get(str);
        }
        return arrayList == null ? ParcelDiskCache.getInstance().getList(str, cls) : arrayList;
    }

    public <T extends Parcelable> String storeParcelable(T t) {
        return storeParcelable(UUID.randomUUID().toString(), t);
    }

    public <T extends Parcelable> String storeParcelable(String str, T t) {
        synchronized (this.mCache) {
            if (t == null) {
                try {
                    this.mCache.remove(str);
                } catch (Throwable th) {
                    while (true) {
                        throw th;
                    }
                }
            } else {
                this.mCache.put(str, t);
            }
        }
        if (t == null) {
            ParcelDiskCache.getInstance().remove(str);
        } else {
            ParcelDiskCache.getInstance().set(str, (Parcelable) t, false);
        }
        return str;
    }

    public <T extends Parcelable> String storeParcelableList(ArrayList<T> arrayList) {
        String uuid = UUID.randomUUID().toString();
        synchronized (this.mCache) {
            if (arrayList == null) {
                try {
                    this.mCache.remove(uuid);
                } catch (Throwable th) {
                    while (true) {
                        throw th;
                    }
                }
            } else {
                this.mCache.put(uuid, arrayList);
            }
        }
        if (arrayList == null) {
            ParcelDiskCache.getInstance().remove(uuid);
        } else {
            ParcelDiskCache.getInstance().set(uuid, (List<T>) arrayList, false);
        }
        return uuid;
    }

    public void clearCache() {
        synchronized (this.mCache) {
            this.mCache.evictAll();
        }
    }
}
