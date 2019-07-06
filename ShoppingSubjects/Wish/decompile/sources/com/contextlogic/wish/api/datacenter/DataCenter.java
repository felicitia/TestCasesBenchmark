package com.contextlogic.wish.api.datacenter;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import com.contextlogic.wish.application.ForegroundWatcher;
import com.contextlogic.wish.application.ForegroundWatcher.ForegroundListener;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.util.PreferenceUtil;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import org.json.JSONObject;

public abstract class DataCenter {
    /* access modifiers changed from: private */
    public Handler mHandler = new Handler(Looper.getMainLooper());
    /* access modifiers changed from: private */
    public boolean mNeedsSerialization = false;
    /* access modifiers changed from: private */
    public boolean mRefreshInProgress = false;
    /* access modifiers changed from: private */
    public Runnable mRefreshJob = new Runnable() {
        public void run() {
            if (!DataCenter.this.mRefreshInProgress) {
                DataCenter.this.mRefreshInProgress = true;
                DataCenter.this.refresh();
                DataCenter.this.mRefreshInProgress = false;
                DataCenter.this.repostRefreshRunnable();
            }
        }
    };
    private Runnable mSerializationJob = new Runnable() {
        public void run() {
            DataCenter.this.serialize();
            DataCenter.this.mNeedsSerialization = false;
        }
    };
    private ExecutorService mSerializationPool = Executors.newFixedThreadPool(1, new ThreadFactory() {
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable);
            thread.setPriority(1);
            return thread;
        }
    });

    protected enum RefreshMode {
        PERIODIC,
        ON_FOREGROUND,
        MANUAL
    }

    /* access modifiers changed from: protected */
    public abstract boolean canDeserialize();

    /* access modifiers changed from: protected */
    public abstract void cancelAllRequests();

    /* access modifiers changed from: protected */
    public abstract void clearData();

    /* access modifiers changed from: protected */
    public String getLegacySerializationFileName() {
        return null;
    }

    /* access modifiers changed from: protected */
    public String getLegacySerializationPreferenceName() {
        return null;
    }

    /* access modifiers changed from: protected */
    public abstract RefreshMode getRefreshMode();

    /* access modifiers changed from: protected */
    public abstract JSONObject getSerializationData();

    /* access modifiers changed from: protected */
    public abstract String getSerializationFileName();

    /* access modifiers changed from: protected */
    public abstract String getSerializationPreferenceName();

    /* access modifiers changed from: protected */
    public void onDeserializationCompleted() {
    }

    /* access modifiers changed from: protected */
    public boolean processLegacySerializedData(JSONObject jSONObject, Bundle bundle) {
        return false;
    }

    /* access modifiers changed from: protected */
    public abstract boolean processSerializedData(JSONObject jSONObject, Bundle bundle);

    public abstract void refresh();

    /* access modifiers changed from: protected */
    public boolean shouldCheckLegacySerialization() {
        return false;
    }

    protected DataCenter() {
        ForegroundWatcher.getInstance().addListener(new ForegroundListener() {
            public void onForeground() {
            }

            public void onLoggedInForeground() {
                if (DataCenter.this.getRefreshMode() == RefreshMode.PERIODIC || DataCenter.this.getRefreshMode() == RefreshMode.ON_FOREGROUND) {
                    DataCenter.this.startRefresh();
                }
            }

            public void onBackground() {
                DataCenter.this.stopRefresh();
            }
        });
    }

    public void markForSerialization() {
        if (canSerializeToPreference() || canSerializeToFile()) {
            this.mNeedsSerialization = true;
            this.mHandler.post(this.mSerializationJob);
        }
    }

    /* access modifiers changed from: private */
    public void serialize() {
        if (this.mNeedsSerialization) {
            final JSONObject serializationData = getSerializationData();
            this.mNeedsSerialization = false;
            if (canSerializeToFile()) {
                final String serializationFileName = getSerializationFileName();
                if (serializationData != null) {
                    this.mSerializationPool.execute(new Runnable() {
                        /* JADX WARNING: Removed duplicated region for block: B:32:0x0047 A[SYNTHETIC, Splitter:B:32:0x0047] */
                        /* JADX WARNING: Removed duplicated region for block: B:36:0x004c A[SYNTHETIC, Splitter:B:36:0x004c] */
                        /* JADX WARNING: Removed duplicated region for block: B:40:0x0051 A[SYNTHETIC, Splitter:B:40:0x0051] */
                        /* JADX WARNING: Removed duplicated region for block: B:48:0x0059 A[SYNTHETIC, Splitter:B:48:0x0059] */
                        /* JADX WARNING: Removed duplicated region for block: B:52:0x005e A[SYNTHETIC, Splitter:B:52:0x005e] */
                        /* JADX WARNING: Removed duplicated region for block: B:59:? A[RETURN, SYNTHETIC] */
                        /* Code decompiled incorrectly, please refer to instructions dump. */
                        public void run() {
                            /*
                                r5 = this;
                                r0 = 0
                                com.contextlogic.wish.application.WishApplication r1 = com.contextlogic.wish.application.WishApplication.getInstance()     // Catch:{ Throwable -> 0x0055, all -> 0x0040 }
                                java.lang.String r2 = r1     // Catch:{ Throwable -> 0x0055, all -> 0x0040 }
                                r3 = 0
                                java.io.FileOutputStream r1 = r1.openFileOutput(r2, r3)     // Catch:{ Throwable -> 0x0055, all -> 0x0040 }
                                java.io.OutputStreamWriter r2 = new java.io.OutputStreamWriter     // Catch:{ Throwable -> 0x003e, all -> 0x0039 }
                                java.lang.String r3 = "UTF-8"
                                r2.<init>(r1, r3)     // Catch:{ Throwable -> 0x003e, all -> 0x0039 }
                                com.google.gson.stream.JsonWriter r3 = new com.google.gson.stream.JsonWriter     // Catch:{ Throwable -> 0x0057, all -> 0x0034 }
                                r3.<init>(r2)     // Catch:{ Throwable -> 0x0057, all -> 0x0034 }
                                org.json.JSONObject r0 = r0     // Catch:{ Throwable -> 0x0032, all -> 0x0030 }
                                com.contextlogic.wish.util.JsonUtil.serializeJsonObject(r0, r3)     // Catch:{ Throwable -> 0x0032, all -> 0x0030 }
                                r3.close()     // Catch:{ Throwable -> 0x0032, all -> 0x0030 }
                                if (r3 == 0) goto L_0x0025
                                r3.close()     // Catch:{ Throwable -> 0x0025 }
                            L_0x0025:
                                if (r2 == 0) goto L_0x002a
                                r2.close()     // Catch:{ Throwable -> 0x002a }
                            L_0x002a:
                                if (r1 == 0) goto L_0x0064
                            L_0x002c:
                                r1.close()     // Catch:{ Throwable -> 0x0064 }
                                goto L_0x0064
                            L_0x0030:
                                r0 = move-exception
                                goto L_0x0045
                            L_0x0032:
                                r0 = r3
                                goto L_0x0057
                            L_0x0034:
                                r3 = move-exception
                                r4 = r3
                                r3 = r0
                                r0 = r4
                                goto L_0x0045
                            L_0x0039:
                                r2 = move-exception
                                r3 = r0
                                r0 = r2
                                r2 = r3
                                goto L_0x0045
                            L_0x003e:
                                r2 = r0
                                goto L_0x0057
                            L_0x0040:
                                r1 = move-exception
                                r2 = r0
                                r3 = r2
                                r0 = r1
                                r1 = r3
                            L_0x0045:
                                if (r3 == 0) goto L_0x004a
                                r3.close()     // Catch:{ Throwable -> 0x004a }
                            L_0x004a:
                                if (r2 == 0) goto L_0x004f
                                r2.close()     // Catch:{ Throwable -> 0x004f }
                            L_0x004f:
                                if (r1 == 0) goto L_0x0054
                                r1.close()     // Catch:{ Throwable -> 0x0054 }
                            L_0x0054:
                                throw r0
                            L_0x0055:
                                r1 = r0
                                r2 = r1
                            L_0x0057:
                                if (r0 == 0) goto L_0x005c
                                r0.close()     // Catch:{ Throwable -> 0x005c }
                            L_0x005c:
                                if (r2 == 0) goto L_0x0061
                                r2.close()     // Catch:{ Throwable -> 0x0061 }
                            L_0x0061:
                                if (r1 == 0) goto L_0x0064
                                goto L_0x002c
                            L_0x0064:
                                return
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.contextlogic.wish.api.datacenter.DataCenter.AnonymousClass5.run():void");
                        }
                    });
                }
            } else if (canSerializeToPreference()) {
                final String serializationPreferenceName = getSerializationPreferenceName();
                this.mSerializationPool.execute(new Runnable() {
                    public void run() {
                        PreferenceUtil.setSecureJSONObject(serializationPreferenceName, serializationData);
                    }
                });
            }
        }
    }

    private void removeSerialization() {
        this.mHandler.removeCallbacks(this.mSerializationJob);
        if (canSerializeToFile()) {
            try {
                WishApplication.getInstance().deleteFile(getSerializationFileName());
            } catch (Throwable unused) {
            }
        } else if (canSerializeToPreference()) {
            PreferenceUtil.setSecureJSONObject(getSerializationPreferenceName(), null);
        }
        removeLegacySerialization();
    }

    private void removeLegacySerialization() {
        if (hasLegacySerializeToFile()) {
            try {
                WishApplication.getInstance().deleteFile(getLegacySerializationFileName());
            } catch (Throwable unused) {
            }
        } else if (hasLegacySerializeToPreference()) {
            PreferenceUtil.setSecureJSONObject(getLegacySerializationPreferenceName(), null);
        }
    }

    private boolean hasLegacySerializeToFile() {
        return getLegacySerializationFileName() != null;
    }

    private boolean hasLegacySerializeToPreference() {
        return getLegacySerializationPreferenceName() != null;
    }

    private boolean canSerializeToFile() {
        return getSerializationFileName() != null;
    }

    private boolean canSerializeToPreference() {
        return getSerializationPreferenceName() != null;
    }

    public boolean deserialize() {
        return deserialize(new Bundle());
    }

    /* JADX WARNING: type inference failed for: r2v0 */
    /* JADX WARNING: type inference failed for: r2v2, types: [com.google.gson.stream.JsonReader] */
    /* JADX WARNING: type inference failed for: r2v3, types: [com.google.gson.stream.JsonReader] */
    /* JADX WARNING: type inference failed for: r2v4 */
    /* JADX WARNING: type inference failed for: r2v5 */
    /* JADX WARNING: type inference failed for: r4v8, types: [com.google.gson.stream.JsonReader] */
    /* JADX WARNING: type inference failed for: r3v8, types: [java.io.InputStreamReader] */
    /* JADX WARNING: type inference failed for: r3v9 */
    /* JADX WARNING: type inference failed for: r4v9 */
    /* JADX WARNING: type inference failed for: r3v11, types: [java.io.InputStreamReader] */
    /* JADX WARNING: type inference failed for: r2v8, types: [com.google.gson.stream.JsonReader] */
    /* JADX WARNING: type inference failed for: r3v12 */
    /* JADX WARNING: type inference failed for: r3v15 */
    /* JADX WARNING: type inference failed for: r4v11 */
    /* JADX WARNING: type inference failed for: r2v9 */
    /* JADX WARNING: type inference failed for: r2v10 */
    /* JADX WARNING: type inference failed for: r2v11 */
    /* JADX WARNING: type inference failed for: r2v12 */
    /* JADX WARNING: type inference failed for: r2v13 */
    /* JADX WARNING: type inference failed for: r2v14 */
    /* JADX WARNING: type inference failed for: r2v15 */
    /* JADX WARNING: type inference failed for: r2v16 */
    /* JADX WARNING: type inference failed for: r2v17 */
    /* JADX WARNING: type inference failed for: r2v18 */
    /* JADX WARNING: type inference failed for: r3v17 */
    /* JADX WARNING: type inference failed for: r3v18 */
    /* JADX WARNING: type inference failed for: r3v19 */
    /* JADX WARNING: type inference failed for: r3v20 */
    /* JADX WARNING: type inference failed for: r3v21 */
    /* JADX WARNING: type inference failed for: r4v13 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:119:0x00f4 A[SYNTHETIC, Splitter:B:119:0x00f4] */
    /* JADX WARNING: Removed duplicated region for block: B:123:0x00f9 A[SYNTHETIC, Splitter:B:123:0x00f9] */
    /* JADX WARNING: Removed duplicated region for block: B:127:0x00fe A[SYNTHETIC, Splitter:B:127:0x00fe] */
    /* JADX WARNING: Removed duplicated region for block: B:135:0x0106 A[SYNTHETIC, Splitter:B:135:0x0106] */
    /* JADX WARNING: Removed duplicated region for block: B:139:0x010b A[SYNTHETIC, Splitter:B:139:0x010b] */
    /* JADX WARNING: Removed duplicated region for block: B:143:0x0110 A[SYNTHETIC, Splitter:B:143:0x0110] */
    /* JADX WARNING: Removed duplicated region for block: B:145:0x0114  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x005c A[SYNTHETIC, Splitter:B:40:0x005c] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0061 A[SYNTHETIC, Splitter:B:44:0x0061] */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x0066 A[SYNTHETIC, Splitter:B:48:0x0066] */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x006f A[SYNTHETIC, Splitter:B:57:0x006f] */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x0074 A[SYNTHETIC, Splitter:B:61:0x0074] */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x0079 A[SYNTHETIC, Splitter:B:65:0x0079] */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x00a7  */
    /* JADX WARNING: Unknown variable types count: 7 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean deserialize(android.os.Bundle r7) {
        /*
            r6 = this;
            boolean r0 = r6.canDeserialize()
            r1 = 0
            if (r0 != 0) goto L_0x0008
            return r1
        L_0x0008:
            boolean r0 = r6.canSerializeToFile()
            r2 = 0
            if (r0 == 0) goto L_0x007f
            r6.cancelAllRequests()
            com.contextlogic.wish.application.WishApplication r0 = com.contextlogic.wish.application.WishApplication.getInstance()     // Catch:{ Throwable -> 0x006a, all -> 0x0057 }
            java.lang.String r3 = r6.getSerializationFileName()     // Catch:{ Throwable -> 0x006a, all -> 0x0057 }
            java.io.FileInputStream r0 = r0.openFileInput(r3)     // Catch:{ Throwable -> 0x006a, all -> 0x0057 }
            java.io.InputStreamReader r3 = new java.io.InputStreamReader     // Catch:{ Throwable -> 0x0055, all -> 0x0052 }
            java.lang.String r4 = "UTF-8"
            r3.<init>(r0, r4)     // Catch:{ Throwable -> 0x0055, all -> 0x0052 }
            com.google.gson.stream.JsonReader r4 = new com.google.gson.stream.JsonReader     // Catch:{ Throwable -> 0x0050, all -> 0x004e }
            r4.<init>(r3)     // Catch:{ Throwable -> 0x0050, all -> 0x004e }
            com.contextlogic.wish.http.JsonObjectParser r5 = new com.contextlogic.wish.http.JsonObjectParser     // Catch:{ Throwable -> 0x006d, all -> 0x004b }
            r5.<init>()     // Catch:{ Throwable -> 0x006d, all -> 0x004b }
            org.json.JSONObject r5 = r5.parseJsonObject(r4)     // Catch:{ Throwable -> 0x006d, all -> 0x004b }
            if (r5 != 0) goto L_0x0037
            r5 = 0
            goto L_0x003b
        L_0x0037:
            boolean r5 = r6.processSerializedData(r5, r7)     // Catch:{ Throwable -> 0x006d, all -> 0x004b }
        L_0x003b:
            if (r4 == 0) goto L_0x0040
            r4.close()     // Catch:{ Throwable -> 0x0040 }
        L_0x0040:
            if (r3 == 0) goto L_0x0045
            r3.close()     // Catch:{ Throwable -> 0x0045 }
        L_0x0045:
            if (r0 == 0) goto L_0x007d
            r0.close()     // Catch:{ Throwable -> 0x007d }
            goto L_0x007d
        L_0x004b:
            r7 = move-exception
            r2 = r4
            goto L_0x005a
        L_0x004e:
            r7 = move-exception
            goto L_0x005a
        L_0x0050:
            r4 = r2
            goto L_0x006d
        L_0x0052:
            r7 = move-exception
            r3 = r2
            goto L_0x005a
        L_0x0055:
            r3 = r2
            goto L_0x006c
        L_0x0057:
            r7 = move-exception
            r0 = r2
            r3 = r0
        L_0x005a:
            if (r2 == 0) goto L_0x005f
            r2.close()     // Catch:{ Throwable -> 0x005f }
        L_0x005f:
            if (r3 == 0) goto L_0x0064
            r3.close()     // Catch:{ Throwable -> 0x0064 }
        L_0x0064:
            if (r0 == 0) goto L_0x0069
            r0.close()     // Catch:{ Throwable -> 0x0069 }
        L_0x0069:
            throw r7
        L_0x006a:
            r0 = r2
            r3 = r0
        L_0x006c:
            r4 = r3
        L_0x006d:
            if (r4 == 0) goto L_0x0072
            r4.close()     // Catch:{ Throwable -> 0x0072 }
        L_0x0072:
            if (r3 == 0) goto L_0x0077
            r3.close()     // Catch:{ Throwable -> 0x0077 }
        L_0x0077:
            if (r0 == 0) goto L_0x007c
            r0.close()     // Catch:{ Throwable -> 0x007c }
        L_0x007c:
            r5 = 0
        L_0x007d:
            r0 = r5
            goto L_0x0099
        L_0x007f:
            boolean r0 = r6.canSerializeToPreference()
            if (r0 == 0) goto L_0x0098
            r6.cancelAllRequests()
            java.lang.String r0 = r6.getSerializationPreferenceName()
            org.json.JSONObject r0 = com.contextlogic.wish.util.PreferenceUtil.getSecureJSONObject(r0)
            if (r0 != 0) goto L_0x0093
            goto L_0x0098
        L_0x0093:
            boolean r0 = r6.processSerializedData(r0, r7)
            goto L_0x0099
        L_0x0098:
            r0 = 0
        L_0x0099:
            if (r0 != 0) goto L_0x012e
            boolean r3 = r6.shouldCheckLegacySerialization()
            if (r3 == 0) goto L_0x012e
            boolean r3 = r6.hasLegacySerializeToFile()
            if (r3 == 0) goto L_0x0114
            r6.cancelAllRequests()
            com.contextlogic.wish.application.WishApplication r3 = com.contextlogic.wish.application.WishApplication.getInstance()     // Catch:{ Throwable -> 0x0102, all -> 0x00ef }
            java.lang.String r4 = r6.getLegacySerializationFileName()     // Catch:{ Throwable -> 0x0102, all -> 0x00ef }
            java.io.FileInputStream r3 = r3.openFileInput(r4)     // Catch:{ Throwable -> 0x0102, all -> 0x00ef }
            java.io.InputStreamReader r4 = new java.io.InputStreamReader     // Catch:{ Throwable -> 0x00ed, all -> 0x00ea }
            java.lang.String r5 = "UTF-8"
            r4.<init>(r3, r5)     // Catch:{ Throwable -> 0x00ed, all -> 0x00ea }
            com.google.gson.stream.JsonReader r5 = new com.google.gson.stream.JsonReader     // Catch:{ Throwable -> 0x0104, all -> 0x00e8 }
            r5.<init>(r4)     // Catch:{ Throwable -> 0x0104, all -> 0x00e8 }
            com.contextlogic.wish.http.JsonObjectParser r2 = new com.contextlogic.wish.http.JsonObjectParser     // Catch:{ Throwable -> 0x00e6, all -> 0x00e3 }
            r2.<init>()     // Catch:{ Throwable -> 0x00e6, all -> 0x00e3 }
            org.json.JSONObject r2 = r2.parseJsonObject(r5)     // Catch:{ Throwable -> 0x00e6, all -> 0x00e3 }
            if (r2 != 0) goto L_0x00ce
            goto L_0x00d2
        L_0x00ce:
            boolean r1 = r6.processLegacySerializedData(r2, r7)     // Catch:{ Throwable -> 0x00e6, all -> 0x00e3 }
        L_0x00d2:
            if (r5 == 0) goto L_0x00d7
            r5.close()     // Catch:{ Throwable -> 0x00d7 }
        L_0x00d7:
            if (r4 == 0) goto L_0x00dc
            r4.close()     // Catch:{ Throwable -> 0x00dc }
        L_0x00dc:
            if (r3 == 0) goto L_0x00e1
            r3.close()     // Catch:{ Throwable -> 0x00e1 }
        L_0x00e1:
            r0 = r1
            goto L_0x012e
        L_0x00e3:
            r7 = move-exception
            r2 = r5
            goto L_0x00f2
        L_0x00e6:
            r2 = r5
            goto L_0x0104
        L_0x00e8:
            r7 = move-exception
            goto L_0x00f2
        L_0x00ea:
            r7 = move-exception
            r4 = r2
            goto L_0x00f2
        L_0x00ed:
            r4 = r2
            goto L_0x0104
        L_0x00ef:
            r7 = move-exception
            r3 = r2
            r4 = r3
        L_0x00f2:
            if (r2 == 0) goto L_0x00f7
            r2.close()     // Catch:{ Throwable -> 0x00f7 }
        L_0x00f7:
            if (r4 == 0) goto L_0x00fc
            r4.close()     // Catch:{ Throwable -> 0x00fc }
        L_0x00fc:
            if (r3 == 0) goto L_0x0101
            r3.close()     // Catch:{ Throwable -> 0x0101 }
        L_0x0101:
            throw r7
        L_0x0102:
            r3 = r2
            r4 = r3
        L_0x0104:
            if (r2 == 0) goto L_0x0109
            r2.close()     // Catch:{ Throwable -> 0x0109 }
        L_0x0109:
            if (r4 == 0) goto L_0x010e
            r4.close()     // Catch:{ Throwable -> 0x010e }
        L_0x010e:
            if (r3 == 0) goto L_0x012e
            r3.close()     // Catch:{ Throwable -> 0x012e }
            goto L_0x012e
        L_0x0114:
            boolean r2 = r6.hasLegacySerializeToPreference()
            if (r2 == 0) goto L_0x012e
            r6.cancelAllRequests()
            java.lang.String r0 = r6.getLegacySerializationPreferenceName()
            org.json.JSONObject r0 = com.contextlogic.wish.util.PreferenceUtil.getSecureJSONObject(r0)
            if (r0 != 0) goto L_0x0129
            r0 = 0
            goto L_0x012e
        L_0x0129:
            boolean r1 = r6.processLegacySerializedData(r0, r7)
            goto L_0x00e1
        L_0x012e:
            r6.onDeserializationCompleted()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.contextlogic.wish.api.datacenter.DataCenter.deserialize(android.os.Bundle):boolean");
    }

    public void reset(boolean z) {
        clearData();
        cancelAllRequests();
        if (z) {
            removeSerialization();
        }
    }

    /* access modifiers changed from: protected */
    public void startRefresh() {
        this.mHandler.post(new Runnable() {
            public void run() {
                DataCenter.this.mHandler.removeCallbacks(DataCenter.this.mRefreshJob);
                DataCenter.this.mRefreshJob.run();
            }
        });
    }

    /* access modifiers changed from: protected */
    public void stopRefresh() {
        this.mHandler.post(new Runnable() {
            public void run() {
                DataCenter.this.mHandler.removeCallbacks(DataCenter.this.mRefreshJob);
            }
        });
    }

    /* access modifiers changed from: private */
    public void repostRefreshRunnable() {
        if (getRefreshMode() == RefreshMode.PERIODIC && ForegroundWatcher.getInstance().isForeground()) {
            this.mHandler.postDelayed(this.mRefreshJob, 30000);
        }
    }
}
