package com.contextlogic.wish.analytics;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.api.model.WishProduct.VideoStatus;
import com.contextlogic.wish.api.service.standalone.FeedTileLogService;
import com.contextlogic.wish.util.PreferenceUtil;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class FeedTileLogger {
    private static final FeedTileLogger sInstance = new FeedTileLogger();
    private ArrayList<Map<String, String>> mItems = new ArrayList<>();
    private final Object mLock = new Object();

    public enum Action implements Parcelable {
        CLICKED("clicked"),
        IMPRESSION("impression");
        
        public static final Creator<Action> CREATOR = null;
        private String mAction;

        public int describeContents() {
            return 0;
        }

        static {
            CREATOR = new Creator<Action>() {
                public Action createFromParcel(Parcel parcel) {
                    return Action.values()[parcel.readInt()];
                }

                public Action[] newArray(int i) {
                    return new Action[i];
                }
            };
        }

        private Action(String str) {
            this.mAction = str;
        }

        public String toString() {
            return this.mAction;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(ordinal());
        }
    }

    public enum FeedType implements Parcelable {
        FREE_GIFT("freeGift"),
        UNSPECIFIED("unspecified");
        
        public static final Creator<FeedType> CREATOR = null;
        private String mFeedType;

        public int describeContents() {
            return 0;
        }

        static {
            CREATOR = new Creator<FeedType>() {
                public FeedType createFromParcel(Parcel parcel) {
                    return FeedType.values()[parcel.readInt()];
                }

                public FeedType[] newArray(int i) {
                    return new FeedType[i];
                }
            };
        }

        private FeedType(String str) {
            this.mFeedType = str;
        }

        public String toString() {
            return this.mFeedType;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(ordinal());
        }
    }

    protected FeedTileLogger() {
        Executors.newScheduledThreadPool(1).scheduleAtFixedRate(new Runnable() {
            public void run() {
                FeedTileLogger.this.dumpLogs();
            }
        }, 0, 10, TimeUnit.SECONDS);
    }

    public static FeedTileLogger getInstance() {
        return sInstance;
    }

    public void addToQueue(Map<String, String> map, Action action, int i, int i2, String str) {
        if (ExperimentDataCenter.getInstance().shouldRunFeedTileLogger() && map != null && map.size() > 0) {
            synchronized (this.mLock) {
                HashMap hashMap = new HashMap();
                hashMap.putAll(map);
                hashMap.put("log_action", action.toString());
                hashMap.put("log_tile_position", Integer.toString(i));
                hashMap.put("log_interaction_timestamp", Long.toString(System.currentTimeMillis()));
                hashMap.put("log_video_status", Integer.toString(i2));
                hashMap.put("log_feed_type", str);
                this.mItems.add(hashMap);
                serialize();
            }
            if (action == Action.CLICKED) {
                dumpLogs();
            }
        }
    }

    public void addToQueue(Map<String, String> map, Action action, int i, int i2) {
        addToQueue(map, action, i, i2, FeedType.UNSPECIFIED.toString());
    }

    public void addToQueue(Map<String, String> map, Action action, int i) {
        addToQueue(map, action, i, VideoStatus.NO_VIDEO.ordinal(), FeedType.UNSPECIFIED.toString());
    }

    public void addAllToQueue(ArrayList<Map<String, String>> arrayList) {
        if (ExperimentDataCenter.getInstance().shouldRunFeedTileLogger()) {
            synchronized (this.mLock) {
                this.mItems.addAll(arrayList);
                serialize();
            }
        }
    }

    /* access modifiers changed from: private */
    public synchronized void dumpLogs() {
        FeedTileLogService feedTileLogService = new FeedTileLogService();
        synchronized (this.mLock) {
            if (!this.mItems.isEmpty()) {
                feedTileLogService.requestService(this.mItems);
                this.mItems.clear();
                serialize();
            }
        }
    }

    public void serialize() {
        ArrayList arrayList = new ArrayList();
        Gson gson = new Gson();
        Iterator it = this.mItems.iterator();
        while (it.hasNext()) {
            arrayList.add(gson.toJson((Object) (Map) it.next()));
        }
        PreferenceUtil.setStringArray("tile_logger_prefs", arrayList);
    }

    public boolean deserialize() {
        ArrayList stringArray = PreferenceUtil.getStringArray("tile_logger_prefs");
        ArrayList arrayList = new ArrayList();
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>() {
        }.getType();
        Iterator it = stringArray.iterator();
        while (it.hasNext()) {
            try {
                arrayList.add((Map) gson.fromJson((String) it.next(), type));
            } catch (JsonSyntaxException unused) {
                return false;
            }
        }
        addAllToQueue(arrayList);
        return true;
    }
}
