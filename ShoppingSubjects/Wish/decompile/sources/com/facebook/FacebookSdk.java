package com.facebook;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.AsyncTask;
import com.facebook.internal.LockOnGetVariable;
import com.facebook.internal.Validate;
import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public final class FacebookSdk {
    private static final ThreadFactory DEFAULT_THREAD_FACTORY = new ThreadFactory() {
        private final AtomicInteger counter = new AtomicInteger(0);

        public Thread newThread(Runnable runnable) {
            StringBuilder sb = new StringBuilder();
            sb.append("FacebookSdk #");
            sb.append(this.counter.incrementAndGet());
            return new Thread(runnable, sb.toString());
        }
    };
    private static final BlockingQueue<Runnable> DEFAULT_WORK_QUEUE = new LinkedBlockingQueue(10);
    private static final Object LOCK = new Object();
    private static final String TAG = FacebookSdk.class.getCanonicalName();
    private static volatile String appClientToken = null;
    /* access modifiers changed from: private */
    public static Context applicationContext = null;
    private static volatile String applicationId = null;
    private static volatile String applicationName = null;
    private static LockOnGetVariable<File> cacheDir = null;
    private static int callbackRequestCodeOffset = 64206;
    private static volatile Executor executor = null;
    private static volatile String facebookDomain = "facebook.com";
    private static volatile boolean isDebugEnabled = false;
    private static boolean isLegacyTokenUpgradeSupported = false;
    private static final HashSet<LoggingBehavior> loggingBehaviors = new HashSet<>(Arrays.asList(new LoggingBehavior[]{LoggingBehavior.DEVELOPER_ERRORS}));
    private static AtomicLong onProgressThreshold = new AtomicLong(65536);
    private static Boolean sdkInitialized = Boolean.valueOf(false);
    private static volatile int webDialogTheme;

    public interface InitializeCallback {
        void onInitialized();
    }

    public static String getSdkVersion() {
        return "4.15.0";
    }

    public static synchronized void sdkInitialize(Context context) {
        synchronized (FacebookSdk.class) {
            sdkInitialize(context, null);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0011, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized void sdkInitialize(final android.content.Context r3, final com.facebook.FacebookSdk.InitializeCallback r4) {
        /*
            java.lang.Class<com.facebook.FacebookSdk> r0 = com.facebook.FacebookSdk.class
            monitor-enter(r0)
            java.lang.Boolean r1 = sdkInitialized     // Catch:{ all -> 0x005e }
            boolean r1 = r1.booleanValue()     // Catch:{ all -> 0x005e }
            if (r1 == 0) goto L_0x0012
            if (r4 == 0) goto L_0x0010
            r4.onInitialized()     // Catch:{ all -> 0x005e }
        L_0x0010:
            monitor-exit(r0)
            return
        L_0x0012:
            java.lang.String r1 = "applicationContext"
            com.facebook.internal.Validate.notNull(r3, r1)     // Catch:{ all -> 0x005e }
            r1 = 0
            com.facebook.internal.Validate.hasFacebookActivity(r3, r1)     // Catch:{ all -> 0x005e }
            com.facebook.internal.Validate.hasInternetPermissions(r3, r1)     // Catch:{ all -> 0x005e }
            android.content.Context r1 = r3.getApplicationContext()     // Catch:{ all -> 0x005e }
            applicationContext = r1     // Catch:{ all -> 0x005e }
            android.content.Context r1 = applicationContext     // Catch:{ all -> 0x005e }
            loadDefaultsFromMetadata(r1)     // Catch:{ all -> 0x005e }
            r1 = 1
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r1)     // Catch:{ all -> 0x005e }
            sdkInitialized = r1     // Catch:{ all -> 0x005e }
            android.content.Context r1 = applicationContext     // Catch:{ all -> 0x005e }
            java.lang.String r2 = applicationId     // Catch:{ all -> 0x005e }
            com.facebook.internal.Utility.loadAppSettingsAsync(r1, r2)     // Catch:{ all -> 0x005e }
            com.facebook.internal.NativeProtocol.updateAllAvailableProtocolVersionsAsync()     // Catch:{ all -> 0x005e }
            android.content.Context r1 = applicationContext     // Catch:{ all -> 0x005e }
            com.facebook.internal.BoltsMeasurementEventListener.getInstance(r1)     // Catch:{ all -> 0x005e }
            com.facebook.internal.LockOnGetVariable r1 = new com.facebook.internal.LockOnGetVariable     // Catch:{ all -> 0x005e }
            com.facebook.FacebookSdk$2 r2 = new com.facebook.FacebookSdk$2     // Catch:{ all -> 0x005e }
            r2.<init>()     // Catch:{ all -> 0x005e }
            r1.<init>(r2)     // Catch:{ all -> 0x005e }
            cacheDir = r1     // Catch:{ all -> 0x005e }
            java.util.concurrent.FutureTask r1 = new java.util.concurrent.FutureTask     // Catch:{ all -> 0x005e }
            com.facebook.FacebookSdk$3 r2 = new com.facebook.FacebookSdk$3     // Catch:{ all -> 0x005e }
            r2.<init>(r4, r3)     // Catch:{ all -> 0x005e }
            r1.<init>(r2)     // Catch:{ all -> 0x005e }
            java.util.concurrent.Executor r3 = getExecutor()     // Catch:{ all -> 0x005e }
            r3.execute(r1)     // Catch:{ all -> 0x005e }
            monitor-exit(r0)
            return
        L_0x005e:
            r3 = move-exception
            monitor-exit(r0)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.FacebookSdk.sdkInitialize(android.content.Context, com.facebook.FacebookSdk$InitializeCallback):void");
    }

    public static synchronized boolean isInitialized() {
        boolean booleanValue;
        synchronized (FacebookSdk.class) {
            booleanValue = sdkInitialized.booleanValue();
        }
        return booleanValue;
    }

    public static boolean isLoggingBehaviorEnabled(LoggingBehavior loggingBehavior) {
        boolean z;
        synchronized (loggingBehaviors) {
            z = isDebugEnabled() && loggingBehaviors.contains(loggingBehavior);
        }
        return z;
    }

    public static boolean isDebugEnabled() {
        return isDebugEnabled;
    }

    public static boolean isLegacyTokenUpgradeSupported() {
        return isLegacyTokenUpgradeSupported;
    }

    public static Executor getExecutor() {
        synchronized (LOCK) {
            if (executor == null) {
                executor = AsyncTask.THREAD_POOL_EXECUTOR;
            }
        }
        return executor;
    }

    public static String getFacebookDomain() {
        return facebookDomain;
    }

    public static Context getApplicationContext() {
        Validate.sdkInitialized();
        return applicationContext;
    }

    public static void publishInstallAsync(Context context, final String str) {
        final Context applicationContext2 = context.getApplicationContext();
        getExecutor().execute(new Runnable() {
            public void run() {
                FacebookSdk.publishInstallAndWaitForResponse(applicationContext2, str);
            }
        });
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x006a  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0080 A[Catch:{ JSONException -> 0x00aa, Exception -> 0x00bb }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static com.facebook.GraphResponse publishInstallAndWaitForResponse(android.content.Context r14, java.lang.String r15) {
        /*
            r0 = 0
            if (r14 == 0) goto L_0x00b3
            if (r15 != 0) goto L_0x0007
            goto L_0x00b3
        L_0x0007:
            com.facebook.internal.AttributionIdentifiers r1 = com.facebook.internal.AttributionIdentifiers.getAttributionIdentifiers(r14)     // Catch:{ Exception -> 0x00bb }
            java.lang.String r2 = "com.facebook.sdk.attributionTracking"
            r3 = 0
            android.content.SharedPreferences r2 = r14.getSharedPreferences(r2, r3)     // Catch:{ Exception -> 0x00bb }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00bb }
            r4.<init>()     // Catch:{ Exception -> 0x00bb }
            r4.append(r15)     // Catch:{ Exception -> 0x00bb }
            java.lang.String r5 = "ping"
            r4.append(r5)     // Catch:{ Exception -> 0x00bb }
            java.lang.String r4 = r4.toString()     // Catch:{ Exception -> 0x00bb }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00bb }
            r5.<init>()     // Catch:{ Exception -> 0x00bb }
            r5.append(r15)     // Catch:{ Exception -> 0x00bb }
            java.lang.String r6 = "json"
            r5.append(r6)     // Catch:{ Exception -> 0x00bb }
            java.lang.String r5 = r5.toString()     // Catch:{ Exception -> 0x00bb }
            r6 = 0
            long r8 = r2.getLong(r4, r6)     // Catch:{ Exception -> 0x00bb }
            java.lang.String r10 = r2.getString(r5, r0)     // Catch:{ Exception -> 0x00bb }
            com.facebook.internal.AppEventsLoggerUtility$GraphAPIActivityType r11 = com.facebook.internal.AppEventsLoggerUtility.GraphAPIActivityType.MOBILE_INSTALL_EVENT     // Catch:{ JSONException -> 0x00aa }
            java.lang.String r12 = com.facebook.appevents.AppEventsLogger.getAnonymousAppDeviceGUID(r14)     // Catch:{ JSONException -> 0x00aa }
            boolean r13 = getLimitEventAndDataUsage(r14)     // Catch:{ JSONException -> 0x00aa }
            org.json.JSONObject r14 = com.facebook.internal.AppEventsLoggerUtility.getJSONObjectForGraphAPICall(r11, r1, r12, r13, r14)     // Catch:{ JSONException -> 0x00aa }
            java.lang.String r1 = "%s/activities"
            r11 = 1
            java.lang.Object[] r12 = new java.lang.Object[r11]     // Catch:{ Exception -> 0x00bb }
            r12[r3] = r15     // Catch:{ Exception -> 0x00bb }
            java.lang.String r15 = java.lang.String.format(r1, r12)     // Catch:{ Exception -> 0x00bb }
            com.facebook.GraphRequest r14 = com.facebook.GraphRequest.newPostRequest(r0, r15, r14, r0)     // Catch:{ Exception -> 0x00bb }
            int r15 = (r8 > r6 ? 1 : (r8 == r6 ? 0 : -1))
            if (r15 == 0) goto L_0x0086
            if (r10 == 0) goto L_0x0067
            org.json.JSONObject r15 = new org.json.JSONObject     // Catch:{ JSONException -> 0x0067 }
            r15.<init>(r10)     // Catch:{ JSONException -> 0x0067 }
            goto L_0x0068
        L_0x0067:
            r15 = r0
        L_0x0068:
            if (r15 != 0) goto L_0x0080
            java.lang.String r15 = "true"
            com.facebook.GraphRequestBatch r1 = new com.facebook.GraphRequestBatch     // Catch:{ Exception -> 0x00bb }
            com.facebook.GraphRequest[] r2 = new com.facebook.GraphRequest[r11]     // Catch:{ Exception -> 0x00bb }
            r2[r3] = r14     // Catch:{ Exception -> 0x00bb }
            r1.<init>(r2)     // Catch:{ Exception -> 0x00bb }
            java.util.List r14 = com.facebook.GraphResponse.createResponsesFromString(r15, r0, r1)     // Catch:{ Exception -> 0x00bb }
            java.lang.Object r14 = r14.get(r3)     // Catch:{ Exception -> 0x00bb }
            com.facebook.GraphResponse r14 = (com.facebook.GraphResponse) r14     // Catch:{ Exception -> 0x00bb }
            return r14
        L_0x0080:
            com.facebook.GraphResponse r14 = new com.facebook.GraphResponse     // Catch:{ Exception -> 0x00bb }
            r14.<init>(r0, r0, r0, r15)     // Catch:{ Exception -> 0x00bb }
            return r14
        L_0x0086:
            com.facebook.GraphResponse r14 = r14.executeAndWait()     // Catch:{ Exception -> 0x00bb }
            android.content.SharedPreferences$Editor r15 = r2.edit()     // Catch:{ Exception -> 0x00bb }
            long r1 = java.lang.System.currentTimeMillis()     // Catch:{ Exception -> 0x00bb }
            r15.putLong(r4, r1)     // Catch:{ Exception -> 0x00bb }
            org.json.JSONObject r1 = r14.getJSONObject()     // Catch:{ Exception -> 0x00bb }
            if (r1 == 0) goto L_0x00a6
            org.json.JSONObject r1 = r14.getJSONObject()     // Catch:{ Exception -> 0x00bb }
            java.lang.String r1 = r1.toString()     // Catch:{ Exception -> 0x00bb }
            r15.putString(r5, r1)     // Catch:{ Exception -> 0x00bb }
        L_0x00a6:
            r15.apply()     // Catch:{ Exception -> 0x00bb }
            return r14
        L_0x00aa:
            r14 = move-exception
            com.facebook.FacebookException r15 = new com.facebook.FacebookException     // Catch:{ Exception -> 0x00bb }
            java.lang.String r1 = "An error occurred while publishing install."
            r15.<init>(r1, r14)     // Catch:{ Exception -> 0x00bb }
            throw r15     // Catch:{ Exception -> 0x00bb }
        L_0x00b3:
            java.lang.IllegalArgumentException r14 = new java.lang.IllegalArgumentException     // Catch:{ Exception -> 0x00bb }
            java.lang.String r15 = "Both context and applicationId must be non-null"
            r14.<init>(r15)     // Catch:{ Exception -> 0x00bb }
            throw r14     // Catch:{ Exception -> 0x00bb }
        L_0x00bb:
            r14 = move-exception
            java.lang.String r15 = "Facebook-publish"
            com.facebook.internal.Utility.logd(r15, r14)
            com.facebook.GraphResponse r15 = new com.facebook.GraphResponse
            com.facebook.FacebookRequestError r1 = new com.facebook.FacebookRequestError
            r1.<init>(r0, r14)
            r15.<init>(r0, r0, r1)
            return r15
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.FacebookSdk.publishInstallAndWaitForResponse(android.content.Context, java.lang.String):com.facebook.GraphResponse");
    }

    public static boolean getLimitEventAndDataUsage(Context context) {
        Validate.sdkInitialized();
        return context.getSharedPreferences("com.facebook.sdk.appEventPreferences", 0).getBoolean("limitEventUsage", false);
    }

    public static void setLimitEventAndDataUsage(Context context, boolean z) {
        context.getSharedPreferences("com.facebook.sdk.appEventPreferences", 0).edit().putBoolean("limitEventUsage", z).apply();
    }

    public static long getOnProgressThreshold() {
        Validate.sdkInitialized();
        return onProgressThreshold.get();
    }

    static void loadDefaultsFromMetadata(Context context) {
        if (context != null) {
            try {
                ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
                if (applicationInfo != null && applicationInfo.metaData != null) {
                    if (applicationId == null) {
                        Object obj = applicationInfo.metaData.get("com.facebook.sdk.ApplicationId");
                        if (obj instanceof String) {
                            String str = (String) obj;
                            if (str.toLowerCase(Locale.ROOT).startsWith("fb")) {
                                applicationId = str.substring(2);
                            } else {
                                applicationId = str;
                            }
                        } else if (obj instanceof Integer) {
                            throw new FacebookException("App Ids cannot be directly placed in the manifest.They must be prefixed by 'fb' or be placed in the string resource file.");
                        }
                    }
                    if (applicationName == null) {
                        applicationName = applicationInfo.metaData.getString("com.facebook.sdk.ApplicationName");
                    }
                    if (appClientToken == null) {
                        appClientToken = applicationInfo.metaData.getString("com.facebook.sdk.ClientToken");
                    }
                    if (webDialogTheme == 0) {
                        setWebDialogTheme(applicationInfo.metaData.getInt("com.facebook.sdk.WebDialogTheme"));
                    }
                }
            } catch (NameNotFoundException unused) {
            }
        }
    }

    public static String getApplicationId() {
        Validate.sdkInitialized();
        return applicationId;
    }

    public static void setApplicationId(String str) {
        applicationId = str;
    }

    public static String getClientToken() {
        Validate.sdkInitialized();
        return appClientToken;
    }

    public static int getWebDialogTheme() {
        Validate.sdkInitialized();
        return webDialogTheme;
    }

    public static void setWebDialogTheme(int i) {
        if (i == 0) {
            i = 16973840;
        }
        webDialogTheme = i;
    }

    public static int getCallbackRequestCodeOffset() {
        Validate.sdkInitialized();
        return callbackRequestCodeOffset;
    }
}
