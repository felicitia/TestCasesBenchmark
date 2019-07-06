package com.contextlogic.wish.cache;

public class LargeParcelableCache extends StateStoreCache {
    private static LargeParcelableCache sInstance = new LargeParcelableCache();

    public static LargeParcelableCache getInstance() {
        return sInstance;
    }
}
