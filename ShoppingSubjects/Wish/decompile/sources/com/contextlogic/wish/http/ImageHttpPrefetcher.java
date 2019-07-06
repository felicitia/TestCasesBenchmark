package com.contextlogic.wish.http;

import com.contextlogic.wish.api.model.WishImage;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ImageHttpPrefetcher {
    private ConcurrentHashMap<String, Boolean> mFetchDeduplicator;
    private ConcurrentLinkedQueue<WishImage> mFetchQueue;
    private String mPrefetchPrefix;

    public ImageHttpPrefetcher() {
        resetQueues();
        StringBuilder sb = new StringBuilder();
        sb.append(toString());
        sb.append("prefetch_");
        this.mPrefetchPrefix = sb.toString();
    }

    public void queueImage(WishImage wishImage) {
        if (wishImage != null) {
            String prefetchTag = getPrefetchTag(wishImage);
            if (!this.mFetchDeduplicator.containsKey(prefetchTag)) {
                this.mFetchQueue.add(wishImage);
                this.mFetchDeduplicator.put(prefetchTag, Boolean.valueOf(true));
            }
        }
    }

    public WishImage dequeueImage() {
        while (true) {
            WishImage wishImage = null;
            boolean z = true;
            while (true) {
                if (!z) {
                    return wishImage;
                }
                wishImage = (WishImage) this.mFetchQueue.poll();
                if (wishImage != null) {
                    String prefetchTag = getPrefetchTag(wishImage);
                    if (((Boolean) this.mFetchDeduplicator.get(prefetchTag)).booleanValue()) {
                        this.mFetchDeduplicator.put(prefetchTag, Boolean.valueOf(false));
                    }
                }
                z = false;
            }
        }
    }

    public String getPrefetchTag(WishImage wishImage) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.mPrefetchPrefix);
        sb.append(wishImage.getBaseUrlString());
        return sb.toString();
    }

    public void cancelPrefetch(WishImage wishImage) {
        String prefetchTag = getPrefetchTag(wishImage);
        this.mFetchDeduplicator.put(prefetchTag, Boolean.valueOf(false));
        ImageHttpManager.getInstance().cancelRequest(prefetchTag);
    }

    public void pausePrefetching() {
        for (Entry key : this.mFetchDeduplicator.entrySet()) {
            ImageHttpManager.getInstance().pauseRequest(key.getKey());
        }
    }

    public void resumePrefetching() {
        for (Entry key : this.mFetchDeduplicator.entrySet()) {
            ImageHttpManager.getInstance().resumeRequest(key.getKey());
        }
    }

    public void cancelPrefetching() {
        for (Entry key : this.mFetchDeduplicator.entrySet()) {
            ImageHttpManager.getInstance().cancelRequest(key.getKey());
        }
        resetQueues();
    }

    private void resetQueues() {
        this.mFetchQueue = new ConcurrentLinkedQueue<>();
        this.mFetchDeduplicator = new ConcurrentHashMap<>();
    }
}
