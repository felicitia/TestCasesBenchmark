package com.kount.api;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.json.JSONObject;

public class DataCollector {
    private static DataCollector instance;
    /* access modifiers changed from: private */
    public static final Object lock = new Object();
    protected String collectionURL;
    /* access modifiers changed from: private */
    public Context context;
    private boolean debug;
    /* access modifiers changed from: private */
    public int environment;
    private ExecutorService executor;
    /* access modifiers changed from: private */
    public LocationConfig locationConfig;
    /* access modifiers changed from: private */
    public int merchantID;
    /* access modifiers changed from: private */
    public int timeoutInMS;

    public interface CompletionHandler {
        void completed(String str);

        void failed(String str, Error error);
    }

    public enum Error {
        NO_NETWORK(0, "No network"),
        INVALID_ENVIRONMENT(1, "Invalid Environment"),
        INVALID_MERCHANT(2, "Invalid Merchant ID"),
        INVALID_SESSION(3, "Invalid Session ID"),
        RUNTIME_FAILURE(4, "Runtime Failure"),
        VALIDATION_FAILURE(5, "Validation Failure"),
        TIMEOUT(6, "Timeout"),
        CONTEXT_NOT_SET(7, "Context Not Set");
        
        private final int code;
        private final String description;

        private Error(int i, String str) {
            this.code = i;
            this.description = str;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.code);
            sb.append(": ");
            sb.append(this.description);
            return sb.toString();
        }
    }

    public enum LocationConfig {
        COLLECT,
        SKIP
    }

    public void setContext(Context context2) {
        this.context = context2;
    }

    public void setMerchantID(int i) {
        debugMessage(null, String.format(Locale.US, "Setting Merchant ID to %d.", new Object[]{Integer.valueOf(i)}));
        this.merchantID = i;
    }

    public void setEnvironment(int i) {
        if (i != 999999) {
            switch (i) {
                case 1:
                    debugMessage(null, "Setting Environment to Test");
                    setURL("https://tst.kaptcha.com/m.html");
                    break;
                case 2:
                    debugMessage(null, "Setting Environment to Production");
                    setURL("https://ssl.kaptcha.com/m.html");
                    break;
                default:
                    this.environment = 0;
                    debugMessage(null, "Invalid Environment");
                    this.collectionURL = null;
                    return;
            }
        } else {
            debugMessage(null, "Setting Environment to QA");
            setURL("https://mqa.kaptcha.com/m.html");
        }
        this.environment = i;
    }

    /* access modifiers changed from: protected */
    public void setURL(String str) {
        debugMessage(null, String.format("Setting Collection URL to %s.", new Object[]{str}));
        this.collectionURL = str;
    }

    public void setLocationCollectorConfig(LocationConfig locationConfig2) {
        switch (locationConfig2) {
            case COLLECT:
                debugMessage(null, "Location collection enabled.");
                break;
            case SKIP:
                debugMessage(null, "Skipping location collection.");
                break;
        }
        this.locationConfig = locationConfig2;
    }

    protected DataCollector() {
        this.environment = 0;
        this.timeoutInMS = 15000;
        this.locationConfig = LocationConfig.COLLECT;
        this.context = null;
        this.executor = null;
        this.executor = Executors.newSingleThreadExecutor();
    }

    /* access modifiers changed from: private */
    public void debugMessage(Object obj, String str) {
        if (this.debug) {
            Log.d("DataCollector", String.format("<Data Collector> %s", new Object[]{str}));
        }
        if (obj != null) {
            try {
                Method declaredMethod = obj.getClass().getDeclaredMethod("collectorDebugMessage", new Class[]{String.class});
                declaredMethod.setAccessible(true);
                declaredMethod.invoke(obj, new Object[]{str});
            } catch (Exception e) {
                Log.d("DataCollector", String.format("Exception: %s", new Object[]{e.getMessage()}));
            }
        }
    }

    public static DataCollector getInstance() {
        if (instance == null) {
            instance = new DataCollector();
        }
        return instance;
    }

    public void collectForSession(String str, CompletionHandler completionHandler) {
        collectForSession(str, completionHandler, null);
    }

    /* access modifiers changed from: protected */
    public boolean noNetwork(Context context2) {
        boolean z = true;
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context2.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting()) {
                z = false;
            }
            return z;
        } catch (Exception unused) {
            return true;
        }
    }

    /* access modifiers changed from: private */
    public String formatDataForServer(Hashtable<String, String> hashtable, Hashtable<String, String> hashtable2, String str) {
        StringBuilder sb = new StringBuilder();
        try {
            sb.append(String.format(Locale.US, "%s=%d&%s=%s", new Object[]{URLEncoder.encode(PostKey.MERCHANT_ID.toString(), "UTF-8"), Integer.valueOf(this.merchantID), URLEncoder.encode(PostKey.SESSION_ID.toString(), "UTF-8"), URLEncoder.encode(str, "UTF-8")}));
            for (String str2 : hashtable.keySet()) {
                sb.append(String.format("&%s=%s", new Object[]{URLEncoder.encode(str2, "UTF-8"), URLEncoder.encode((String) hashtable.get(str2), "UTF-8")}));
            }
            if (hashtable2.size() > 0) {
                sb.append(String.format(Locale.US, "&%s=%s", new Object[]{URLEncoder.encode(PostKey.SOFT_ERRORS.toString(), "UTF-8"), URLEncoder.encode(new JSONObject(hashtable2).toString(), "UTF-8")}));
            }
            return sb.toString();
        } catch (Exception unused) {
            return null;
        }
    }

    /* access modifiers changed from: private */
    public void callCompletionHandler(CompletionHandler completionHandler, Object obj, String str, Boolean bool, Error error) {
        if (completionHandler != null) {
            Handler handler = new Handler(Looper.getMainLooper());
            final Boolean bool2 = bool;
            final Object obj2 = obj;
            final String str2 = str;
            final CompletionHandler completionHandler2 = completionHandler;
            final Error error2 = error;
            AnonymousClass1 r2 = new Runnable() {
                public void run() {
                    if (bool2.booleanValue()) {
                        DataCollector.this.debugMessage(obj2, String.format(Locale.US, "(%s) Collector completed successfully.", new Object[]{str2}));
                        completionHandler2.completed(str2);
                        return;
                    }
                    completionHandler2.failed(str2, error2);
                }
            };
            handler.post(r2);
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x00bf A[SYNTHETIC, Splitter:B:25:0x00bf] */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x00dc A[SYNTHETIC, Splitter:B:31:0x00dc] */
    /* JADX WARNING: Removed duplicated region for block: B:39:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void sendMobileDataToServer(java.lang.Object r8, java.lang.String r9, java.lang.String r10, java.lang.String r11) {
        /*
            r7 = this;
            r0 = 0
            r1 = 1
            if (r10 == 0) goto L_0x00f7
            r2 = 0
            r3 = 2
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00a7 }
            r4.<init>()     // Catch:{ Exception -> 0x00a7 }
            r4.append(r10)     // Catch:{ Exception -> 0x00a7 }
            java.lang.String r10 = "/m.html"
            r4.append(r10)     // Catch:{ Exception -> 0x00a7 }
            java.lang.String r10 = r4.toString()     // Catch:{ Exception -> 0x00a7 }
            java.util.Locale r4 = java.util.Locale.US     // Catch:{ Exception -> 0x00a7 }
            java.lang.String r5 = "(%s) Posting data:\n%s"
            java.lang.Object[] r6 = new java.lang.Object[r3]     // Catch:{ Exception -> 0x00a7 }
            r6[r0] = r9     // Catch:{ Exception -> 0x00a7 }
            r6[r1] = r11     // Catch:{ Exception -> 0x00a7 }
            java.lang.String r4 = java.lang.String.format(r4, r5, r6)     // Catch:{ Exception -> 0x00a7 }
            r7.debugMessage(r8, r4)     // Catch:{ Exception -> 0x00a7 }
            java.net.URL r4 = new java.net.URL     // Catch:{ Exception -> 0x00a7 }
            r4.<init>(r10)     // Catch:{ Exception -> 0x00a7 }
            java.net.URLConnection r10 = r4.openConnection()     // Catch:{ Exception -> 0x00a7 }
            java.lang.Object r10 = com.google.firebase.perf.network.FirebasePerfUrlConnection.instrument(r10)     // Catch:{ Exception -> 0x00a7 }
            java.net.URLConnection r10 = (java.net.URLConnection) r10     // Catch:{ Exception -> 0x00a7 }
            javax.net.ssl.HttpsURLConnection r10 = (javax.net.ssl.HttpsURLConnection) r10     // Catch:{ Exception -> 0x00a7 }
            java.lang.String r2 = "POST"
            r10.setRequestMethod(r2)     // Catch:{ Exception -> 0x00a2, all -> 0x009f }
            r10.setDoOutput(r1)     // Catch:{ Exception -> 0x00a2, all -> 0x009f }
            java.io.OutputStream r2 = r10.getOutputStream()     // Catch:{ Exception -> 0x00a2, all -> 0x009f }
            byte[] r11 = r11.getBytes()     // Catch:{ Exception -> 0x00a2, all -> 0x009f }
            r2.write(r11)     // Catch:{ Exception -> 0x00a2, all -> 0x009f }
            r2.flush()     // Catch:{ Exception -> 0x00a2, all -> 0x009f }
            r2.close()     // Catch:{ Exception -> 0x00a2, all -> 0x009f }
            int r11 = r10.getResponseCode()     // Catch:{ Exception -> 0x00a2, all -> 0x009f }
            r2 = 200(0xc8, float:2.8E-43)
            if (r11 != r2) goto L_0x006c
            java.util.Locale r11 = java.util.Locale.US     // Catch:{ Exception -> 0x00a2, all -> 0x009f }
            java.lang.String r2 = "(%s) Sent data to %s."
            java.lang.Object[] r5 = new java.lang.Object[r3]     // Catch:{ Exception -> 0x00a2, all -> 0x009f }
            r5[r0] = r9     // Catch:{ Exception -> 0x00a2, all -> 0x009f }
            r5[r1] = r4     // Catch:{ Exception -> 0x00a2, all -> 0x009f }
            java.lang.String r11 = java.lang.String.format(r11, r2, r5)     // Catch:{ Exception -> 0x00a2, all -> 0x009f }
            r7.debugMessage(r8, r11)     // Catch:{ Exception -> 0x00a2, all -> 0x009f }
            goto L_0x0088
        L_0x006c:
            java.util.Locale r11 = java.util.Locale.US     // Catch:{ Exception -> 0x00a2, all -> 0x009f }
            java.lang.String r2 = "(%s) Failed to send data to %s: Response code: %d"
            r5 = 3
            java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch:{ Exception -> 0x00a2, all -> 0x009f }
            r5[r0] = r9     // Catch:{ Exception -> 0x00a2, all -> 0x009f }
            r5[r1] = r4     // Catch:{ Exception -> 0x00a2, all -> 0x009f }
            int r4 = r10.getResponseCode()     // Catch:{ Exception -> 0x00a2, all -> 0x009f }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ Exception -> 0x00a2, all -> 0x009f }
            r5[r3] = r4     // Catch:{ Exception -> 0x00a2, all -> 0x009f }
            java.lang.String r11 = java.lang.String.format(r11, r2, r5)     // Catch:{ Exception -> 0x00a2, all -> 0x009f }
            r7.debugMessage(r8, r11)     // Catch:{ Exception -> 0x00a2, all -> 0x009f }
        L_0x0088:
            if (r10 == 0) goto L_0x0104
            r10.disconnect()     // Catch:{ Exception -> 0x008f }
            goto L_0x0104
        L_0x008f:
            r10 = move-exception
            java.util.Locale r11 = java.util.Locale.US
            java.lang.String r2 = "(%s) Exception encountered sending data: %s"
            java.lang.Object[] r3 = new java.lang.Object[r3]
            r3[r0] = r9
            java.lang.String r9 = r10.getMessage()
            r3[r1] = r9
            goto L_0x00d2
        L_0x009f:
            r11 = move-exception
            r2 = r10
            goto L_0x00da
        L_0x00a2:
            r11 = move-exception
            r2 = r10
            goto L_0x00a8
        L_0x00a5:
            r11 = move-exception
            goto L_0x00da
        L_0x00a7:
            r11 = move-exception
        L_0x00a8:
            java.util.Locale r10 = java.util.Locale.US     // Catch:{ all -> 0x00a5 }
            java.lang.String r4 = "(%s) Exception encountered sending data: %s"
            java.lang.Object[] r5 = new java.lang.Object[r3]     // Catch:{ all -> 0x00a5 }
            r5[r0] = r9     // Catch:{ all -> 0x00a5 }
            java.lang.String r11 = r11.getMessage()     // Catch:{ all -> 0x00a5 }
            r5[r1] = r11     // Catch:{ all -> 0x00a5 }
            java.lang.String r10 = java.lang.String.format(r10, r4, r5)     // Catch:{ all -> 0x00a5 }
            r7.debugMessage(r8, r10)     // Catch:{ all -> 0x00a5 }
            if (r2 == 0) goto L_0x0104
            r2.disconnect()     // Catch:{ Exception -> 0x00c3 }
            goto L_0x0104
        L_0x00c3:
            r10 = move-exception
            java.util.Locale r11 = java.util.Locale.US
            java.lang.String r2 = "(%s) Exception encountered sending data: %s"
            java.lang.Object[] r3 = new java.lang.Object[r3]
            r3[r0] = r9
            java.lang.String r9 = r10.getMessage()
            r3[r1] = r9
        L_0x00d2:
            java.lang.String r9 = java.lang.String.format(r11, r2, r3)
            r7.debugMessage(r8, r9)
            goto L_0x0104
        L_0x00da:
            if (r2 == 0) goto L_0x00f6
            r2.disconnect()     // Catch:{ Exception -> 0x00e0 }
            goto L_0x00f6
        L_0x00e0:
            r10 = move-exception
            java.util.Locale r2 = java.util.Locale.US
            java.lang.String r4 = "(%s) Exception encountered sending data: %s"
            java.lang.Object[] r3 = new java.lang.Object[r3]
            r3[r0] = r9
            java.lang.String r9 = r10.getMessage()
            r3[r1] = r9
            java.lang.String r9 = java.lang.String.format(r2, r4, r3)
            r7.debugMessage(r8, r9)
        L_0x00f6:
            throw r11
        L_0x00f7:
            java.lang.String r10 = "(%s) No server URL to send data to, skipping send."
            java.lang.Object[] r11 = new java.lang.Object[r1]
            r11[r0] = r9
            java.lang.String r9 = java.lang.String.format(r10, r11)
            r7.debugMessage(r8, r9)
        L_0x0104:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.kount.api.DataCollector.sendMobileDataToServer(java.lang.Object, java.lang.String, java.lang.String, java.lang.String):void");
    }

    /* access modifiers changed from: protected */
    public LocationCollector createLocationCollector(Object obj) {
        return new LocationCollector(obj, this.context);
    }

    /* access modifiers changed from: protected */
    public void collectForSession(final String str, final CompletionHandler completionHandler, final Object obj) {
        if (this.context == null) {
            callCompletionHandler(completionHandler, obj, str, Boolean.valueOf(false), Error.CONTEXT_NOT_SET);
            return;
        }
        debugMessage(obj, String.format(Locale.US, "(%s) Adding to queue", new Object[]{str}));
        this.executor.execute(new Runnable() {
            public void run() {
                Date date = new Date();
                DataCollector.this.debugMessage(obj, String.format(Locale.US, "(%s) Starting collection", new Object[]{str}));
                if (DataCollector.this.collectionURL == null || !DataCollector.this.collectionURL.matches("^https?://[\\w-]+(\\.[\\w-]+)+(/[^?]*)?$")) {
                    DataCollector.this.callCompletionHandler(completionHandler, obj, str, Boolean.valueOf(false), Error.INVALID_ENVIRONMENT);
                } else if (DataCollector.this.environment != 2 && DataCollector.this.environment != 1 && DataCollector.this.environment != 999999) {
                    DataCollector.this.callCompletionHandler(completionHandler, obj, str, Boolean.valueOf(false), Error.INVALID_ENVIRONMENT);
                } else if (DataCollector.this.merchantID <= 0 || DataCollector.this.merchantID > 999999) {
                    DataCollector.this.callCompletionHandler(completionHandler, obj, str, Boolean.valueOf(false), Error.INVALID_MERCHANT);
                } else if (str == null || !str.matches("^[\\w-]{1,32}$")) {
                    DataCollector.this.callCompletionHandler(completionHandler, obj, str, Boolean.valueOf(false), Error.INVALID_SESSION);
                } else if (DataCollector.this.noNetwork(DataCollector.this.context)) {
                    DataCollector.this.callCompletionHandler(completionHandler, obj, str, Boolean.valueOf(false), Error.NO_NETWORK);
                } else {
                    Hashtable hashtable = new Hashtable();
                    Hashtable hashtable2 = new Hashtable();
                    ArrayList arrayList = new ArrayList();
                    ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 10, 1000, TimeUnit.MILLISECONDS, new LinkedBlockingQueue());
                    ArrayList arrayList2 = new ArrayList();
                    if (DataCollector.this.locationConfig == LocationConfig.COLLECT) {
                        arrayList2.add(DataCollector.this.createLocationCollector(obj));
                    } else {
                        hashtable2.put(LocationCollector.internalName(), SoftError.SKIPPED.toString());
                    }
                    arrayList2.add(new SystemCollector(obj, DataCollector.this.context));
                    arrayList2.add(new FingerprintCollector(obj, DataCollector.this.context));
                    Iterator it = arrayList2.iterator();
                    while (it.hasNext()) {
                        final CollectorTaskBase collectorTaskBase = (CollectorTaskBase) it.next();
                        final Hashtable hashtable3 = hashtable;
                        AnonymousClass1 r9 = r1;
                        final Hashtable hashtable4 = hashtable2;
                        ThreadPoolExecutor threadPoolExecutor2 = threadPoolExecutor;
                        final ArrayList arrayList3 = arrayList;
                        AnonymousClass1 r1 = new Runnable() {
                            public void run() {
                                final Semaphore semaphore = new Semaphore(1);
                                try {
                                    semaphore.acquire();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                collectorTaskBase.collectForSession(str, new RequestHandler() {
                                    public void completed(Boolean bool, Error error, Hashtable<String, String> hashtable, Hashtable<String, String> hashtable2) {
                                        synchronized (DataCollector.lock) {
                                            for (String str : hashtable.keySet()) {
                                                hashtable3.put(str, hashtable.get(str));
                                            }
                                            for (String str2 : hashtable2.keySet()) {
                                                hashtable4.put(str2, hashtable2.get(str2));
                                            }
                                            if (error != null) {
                                                arrayList3.add(error);
                                            }
                                        }
                                        semaphore.release();
                                    }
                                });
                                try {
                                    semaphore.tryAcquire((long) DataCollector.this.timeoutInMS, TimeUnit.MILLISECONDS);
                                } catch (InterruptedException e2) {
                                    e2.printStackTrace();
                                }
                            }
                        };
                        threadPoolExecutor2.execute(r9);
                        threadPoolExecutor = threadPoolExecutor2;
                    }
                    ThreadPoolExecutor threadPoolExecutor3 = threadPoolExecutor;
                    threadPoolExecutor3.shutdown();
                    try {
                        threadPoolExecutor3.awaitTermination((long) DataCollector.this.timeoutInMS, TimeUnit.MILLISECONDS);
                    } catch (InterruptedException e) {
                        InterruptedException interruptedException = e;
                        DataCollector.this.debugMessage(obj, String.format(Locale.US, "(%s) Exception encountered waiting for threads: %s", new Object[]{str, interruptedException.getMessage()}));
                    }
                    if (arrayList.size() > 0) {
                        DataCollector dataCollector = DataCollector.this;
                        CompletionHandler completionHandler = completionHandler;
                        Object obj = obj;
                        dataCollector.callCompletionHandler(completionHandler, obj, str, Boolean.valueOf(false), (Error) arrayList.get(0));
                    } else {
                        long time = new Date().getTime() - date.getTime();
                        DataCollector.this.debugMessage(obj, String.format(Locale.US, "(%s) Collection time: %d ms.", new Object[]{str, Long.valueOf(time)}));
                        hashtable.put(PostKey.ELAPSED.toString(), Long.toString(time));
                        hashtable.put(PostKey.SDK_TYPE.toString(), "A");
                        hashtable.put(PostKey.SDK_VERSION.toString(), "3.2");
                        String access$800 = DataCollector.this.formatDataForServer(hashtable, hashtable2, str);
                        if (access$800 != null) {
                            try {
                                String format = String.format("https://%s", new Object[]{new URI(DataCollector.this.collectionURL).getHost()});
                                DataCollector.this.debugMessage(obj, String.format("Collection host is: %s", new Object[]{format}));
                                DataCollector.this.sendMobileDataToServer(obj, str, format, access$800);
                                DataCollector.this.callCompletionHandler(completionHandler, obj, str, Boolean.valueOf(true), null);
                            } catch (URISyntaxException unused) {
                                DataCollector.this.debugMessage(obj, "Error parsing collection host name");
                                DataCollector.this.callCompletionHandler(completionHandler, obj, str, Boolean.valueOf(false), Error.INVALID_ENVIRONMENT);
                            }
                        } else {
                            DataCollector.this.callCompletionHandler(completionHandler, obj, str, Boolean.valueOf(false), Error.RUNTIME_FAILURE);
                        }
                    }
                }
            }
        });
    }
}
