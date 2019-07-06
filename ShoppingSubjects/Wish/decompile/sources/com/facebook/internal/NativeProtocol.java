package com.facebook.internal;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import com.facebook.FacebookException;
import com.facebook.FacebookOperationCanceledException;
import com.facebook.FacebookSdk;
import com.facebook.login.DefaultAudience;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

public final class NativeProtocol {
    private static final List<Integer> KNOWN_PROTOCOL_VERSIONS = Arrays.asList(new Integer[]{Integer.valueOf(20160327), Integer.valueOf(20141218), Integer.valueOf(20141107), Integer.valueOf(20141028), Integer.valueOf(20141001), Integer.valueOf(20140701), Integer.valueOf(20140324), Integer.valueOf(20140204), Integer.valueOf(20131107), Integer.valueOf(20130618), Integer.valueOf(20130502), Integer.valueOf(20121101)});
    private static final String TAG = "com.facebook.internal.NativeProtocol";
    private static Map<String, List<NativeAppInfo>> actionToAppInfoMap = buildActionToAppInfoMap();
    /* access modifiers changed from: private */
    public static List<NativeAppInfo> facebookAppInfoList = buildFacebookAppList();
    /* access modifiers changed from: private */
    public static AtomicBoolean protocolVersionsAsyncUpdating = new AtomicBoolean(false);

    private static class FBLiteAppInfo extends NativeAppInfo {
        /* access modifiers changed from: protected */
        public String getLoginActivity() {
            return "com.facebook.lite.platform.LoginGDPDialogActivity";
        }

        /* access modifiers changed from: protected */
        public String getPackage() {
            return "com.facebook.lite";
        }

        private FBLiteAppInfo() {
            super();
        }
    }

    private static class KatanaAppInfo extends NativeAppInfo {
        /* access modifiers changed from: protected */
        public String getLoginActivity() {
            return "com.facebook.katana.ProxyAuth";
        }

        /* access modifiers changed from: protected */
        public String getPackage() {
            return "com.facebook.katana";
        }

        private KatanaAppInfo() {
            super();
        }
    }

    private static class MessengerAppInfo extends NativeAppInfo {
        /* access modifiers changed from: protected */
        public String getLoginActivity() {
            return null;
        }

        /* access modifiers changed from: protected */
        public String getPackage() {
            return "com.facebook.orca";
        }

        private MessengerAppInfo() {
            super();
        }
    }

    private static abstract class NativeAppInfo {
        private static final HashSet<String> validAppSignatureHashes = buildAppSignatureHashes();
        private TreeSet<Integer> availableVersions;

        /* access modifiers changed from: protected */
        public abstract String getLoginActivity();

        /* access modifiers changed from: protected */
        public abstract String getPackage();

        private NativeAppInfo() {
        }

        private static HashSet<String> buildAppSignatureHashes() {
            HashSet<String> hashSet = new HashSet<>();
            hashSet.add("8a3c4b262d721acd49a4bf97d5213199c86fa2b9");
            hashSet.add("a4b7452e2ed8f5f191058ca7bbfd26b0d3214bfc");
            hashSet.add("5e8f16062ea3cd2c4a0d547876baa6f38cabf625");
            return hashSet;
        }

        public boolean validateSignature(Context context, String str) {
            String str2 = Build.BRAND;
            int i = context.getApplicationInfo().flags;
            if (str2.startsWith("generic") && (i & 2) != 0) {
                return true;
            }
            try {
                PackageInfo packageInfo = context.getPackageManager().getPackageInfo(str, 64);
                if (packageInfo.signatures == null || packageInfo.signatures.length <= 0) {
                    return false;
                }
                for (Signature byteArray : packageInfo.signatures) {
                    if (!validAppSignatureHashes.contains(Utility.sha1hash(byteArray.toByteArray()))) {
                        return false;
                    }
                }
                return true;
            } catch (NameNotFoundException unused) {
                return false;
            }
        }

        public TreeSet<Integer> getAvailableVersions() {
            if (this.availableVersions == null) {
                fetchAvailableVersions(false);
            }
            return this.availableVersions;
        }

        /* access modifiers changed from: private */
        public synchronized void fetchAvailableVersions(boolean z) {
            if (!z) {
                try {
                    if (this.availableVersions == null) {
                    }
                } finally {
                }
            }
            this.availableVersions = NativeProtocol.fetchAllAvailableProtocolVersionsForAppInfo(this);
        }
    }

    private static class WakizashiAppInfo extends NativeAppInfo {
        /* access modifiers changed from: protected */
        public String getLoginActivity() {
            return "com.facebook.katana.ProxyAuth";
        }

        /* access modifiers changed from: protected */
        public String getPackage() {
            return "com.facebook.wakizashi";
        }

        private WakizashiAppInfo() {
            super();
        }
    }

    private static List<NativeAppInfo> buildFacebookAppList() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KatanaAppInfo());
        arrayList.add(new WakizashiAppInfo());
        return arrayList;
    }

    private static Map<String, List<NativeAppInfo>> buildActionToAppInfoMap() {
        HashMap hashMap = new HashMap();
        ArrayList arrayList = new ArrayList();
        arrayList.add(new MessengerAppInfo());
        hashMap.put("com.facebook.platform.action.request.OGACTIONPUBLISH_DIALOG", facebookAppInfoList);
        hashMap.put("com.facebook.platform.action.request.FEED_DIALOG", facebookAppInfoList);
        hashMap.put("com.facebook.platform.action.request.LIKE_DIALOG", facebookAppInfoList);
        hashMap.put("com.facebook.platform.action.request.APPINVITES_DIALOG", facebookAppInfoList);
        hashMap.put("com.facebook.platform.action.request.MESSAGE_DIALOG", arrayList);
        hashMap.put("com.facebook.platform.action.request.OGMESSAGEPUBLISH_DIALOG", arrayList);
        return hashMap;
    }

    static Intent validateActivityIntent(Context context, Intent intent, NativeAppInfo nativeAppInfo) {
        if (intent == null) {
            return null;
        }
        ResolveInfo resolveActivity = context.getPackageManager().resolveActivity(intent, 0);
        if (resolveActivity != null && nativeAppInfo.validateSignature(context, resolveActivity.activityInfo.packageName)) {
            return intent;
        }
        return null;
    }

    static Intent validateServiceIntent(Context context, Intent intent, NativeAppInfo nativeAppInfo) {
        if (intent == null) {
            return null;
        }
        ResolveInfo resolveService = context.getPackageManager().resolveService(intent, 0);
        if (resolveService != null && nativeAppInfo.validateSignature(context, resolveService.serviceInfo.packageName)) {
            return intent;
        }
        return null;
    }

    public static Intent createFacebookLiteIntent(Context context, String str, Collection<String> collection, String str2, boolean z, boolean z2, DefaultAudience defaultAudience, String str3) {
        FBLiteAppInfo fBLiteAppInfo = new FBLiteAppInfo();
        return validateActivityIntent(context, createNativeAppIntent(fBLiteAppInfo, str, collection, str2, z, z2, defaultAudience, str3), fBLiteAppInfo);
    }

    private static Intent createNativeAppIntent(NativeAppInfo nativeAppInfo, String str, Collection<String> collection, String str2, boolean z, boolean z2, DefaultAudience defaultAudience, String str3) {
        String loginActivity = nativeAppInfo.getLoginActivity();
        if (loginActivity == null) {
            return null;
        }
        Intent putExtra = new Intent().setClassName(nativeAppInfo.getPackage(), loginActivity).putExtra("client_id", str);
        if (!Utility.isNullOrEmpty(collection)) {
            putExtra.putExtra("scope", TextUtils.join(",", collection));
        }
        if (!Utility.isNullOrEmpty(str2)) {
            putExtra.putExtra("e2e", str2);
        }
        putExtra.putExtra("state", str3);
        putExtra.putExtra("response_type", "token,signed_request");
        putExtra.putExtra("return_scopes", "true");
        if (z2) {
            putExtra.putExtra("default_audience", defaultAudience.getNativeProtocolAudience());
        }
        putExtra.putExtra("legacy_override", "v2.7");
        putExtra.putExtra("auth_type", "rerequest");
        return putExtra;
    }

    public static Intent createProxyAuthIntent(Context context, String str, Collection<String> collection, String str2, boolean z, boolean z2, DefaultAudience defaultAudience, String str3) {
        for (NativeAppInfo nativeAppInfo : facebookAppInfoList) {
            Intent validateActivityIntent = validateActivityIntent(context, createNativeAppIntent(nativeAppInfo, str, collection, str2, z, z2, defaultAudience, str3), nativeAppInfo);
            if (validateActivityIntent != null) {
                return validateActivityIntent;
            }
        }
        return null;
    }

    public static final int getLatestKnownVersion() {
        return ((Integer) KNOWN_PROTOCOL_VERSIONS.get(0)).intValue();
    }

    public static boolean isVersionCompatibleWithBucketedIntent(int i) {
        return KNOWN_PROTOCOL_VERSIONS.contains(Integer.valueOf(i)) && i >= 20140701;
    }

    public static Intent createProtocolResultIntent(Intent intent, Bundle bundle, FacebookException facebookException) {
        UUID callIdFromIntent = getCallIdFromIntent(intent);
        if (callIdFromIntent == null) {
            return null;
        }
        Intent intent2 = new Intent();
        intent2.putExtra("com.facebook.platform.protocol.PROTOCOL_VERSION", getProtocolVersionFromIntent(intent));
        Bundle bundle2 = new Bundle();
        bundle2.putString("action_id", callIdFromIntent.toString());
        if (facebookException != null) {
            bundle2.putBundle("error", createBundleForException(facebookException));
        }
        intent2.putExtra("com.facebook.platform.protocol.BRIDGE_ARGS", bundle2);
        if (bundle != null) {
            intent2.putExtra("com.facebook.platform.protocol.RESULT_ARGS", bundle);
        }
        return intent2;
    }

    public static Intent createPlatformServiceIntent(Context context) {
        for (NativeAppInfo nativeAppInfo : facebookAppInfoList) {
            Intent validateServiceIntent = validateServiceIntent(context, new Intent("com.facebook.platform.PLATFORM_SERVICE").setPackage(nativeAppInfo.getPackage()).addCategory("android.intent.category.DEFAULT"), nativeAppInfo);
            if (validateServiceIntent != null) {
                return validateServiceIntent;
            }
        }
        return null;
    }

    public static int getProtocolVersionFromIntent(Intent intent) {
        return intent.getIntExtra("com.facebook.platform.protocol.PROTOCOL_VERSION", 0);
    }

    public static UUID getCallIdFromIntent(Intent intent) {
        String str;
        UUID uuid;
        if (intent == null) {
            return null;
        }
        if (isVersionCompatibleWithBucketedIntent(getProtocolVersionFromIntent(intent))) {
            Bundle bundleExtra = intent.getBundleExtra("com.facebook.platform.protocol.BRIDGE_ARGS");
            str = bundleExtra != null ? bundleExtra.getString("action_id") : null;
        } else {
            str = intent.getStringExtra("com.facebook.platform.protocol.CALL_ID");
        }
        if (str != null) {
            try {
                uuid = UUID.fromString(str);
            } catch (IllegalArgumentException unused) {
            }
            return uuid;
        }
        uuid = null;
        return uuid;
    }

    public static Bundle getMethodArgumentsFromIntent(Intent intent) {
        if (!isVersionCompatibleWithBucketedIntent(getProtocolVersionFromIntent(intent))) {
            return intent.getExtras();
        }
        return intent.getBundleExtra("com.facebook.platform.protocol.METHOD_ARGS");
    }

    public static FacebookException getExceptionFromErrorData(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        String string = bundle.getString("error_type");
        if (string == null) {
            string = bundle.getString("com.facebook.platform.status.ERROR_TYPE");
        }
        String string2 = bundle.getString("error_description");
        if (string2 == null) {
            string2 = bundle.getString("com.facebook.platform.status.ERROR_DESCRIPTION");
        }
        if (string == null || !string.equalsIgnoreCase("UserCanceled")) {
            return new FacebookException(string2);
        }
        return new FacebookOperationCanceledException(string2);
    }

    public static Bundle createBundleForException(FacebookException facebookException) {
        if (facebookException == null) {
            return null;
        }
        Bundle bundle = new Bundle();
        bundle.putString("error_description", facebookException.toString());
        if (facebookException instanceof FacebookOperationCanceledException) {
            bundle.putString("error_type", "UserCanceled");
        }
        return bundle;
    }

    public static int getLatestAvailableProtocolVersionForService(int i) {
        return getLatestAvailableProtocolVersionForAppInfoList(facebookAppInfoList, new int[]{i});
    }

    private static int getLatestAvailableProtocolVersionForAppInfoList(List<NativeAppInfo> list, int[] iArr) {
        updateAllAvailableProtocolVersionsAsync();
        if (list == null) {
            return -1;
        }
        for (NativeAppInfo availableVersions : list) {
            int computeLatestAvailableVersionFromVersionSpec = computeLatestAvailableVersionFromVersionSpec(availableVersions.getAvailableVersions(), getLatestKnownVersion(), iArr);
            if (computeLatestAvailableVersionFromVersionSpec != -1) {
                return computeLatestAvailableVersionFromVersionSpec;
            }
        }
        return -1;
    }

    public static void updateAllAvailableProtocolVersionsAsync() {
        if (protocolVersionsAsyncUpdating.compareAndSet(false, true)) {
            FacebookSdk.getExecutor().execute(new Runnable() {
                public void run() {
                    try {
                        for (NativeAppInfo access$700 : NativeProtocol.facebookAppInfoList) {
                            access$700.fetchAvailableVersions(true);
                        }
                    } finally {
                        NativeProtocol.protocolVersionsAsyncUpdating.set(false);
                    }
                }
            });
        }
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: private */
    /* JADX WARNING: Can't wrap try/catch for region: R(2:8|9) */
    /* JADX WARNING: Code restructure failed: missing block: B:9:?, code lost:
        android.util.Log.e(TAG, "Failed to query content resolver.");
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:8:0x0047 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.TreeSet<java.lang.Integer> fetchAllAvailableProtocolVersionsForAppInfo(com.facebook.internal.NativeProtocol.NativeAppInfo r9) {
        /*
            java.util.TreeSet r0 = new java.util.TreeSet
            r0.<init>()
            android.content.Context r1 = com.facebook.FacebookSdk.getApplicationContext()
            android.content.ContentResolver r2 = r1.getContentResolver()
            r1 = 1
            java.lang.String[] r4 = new java.lang.String[r1]
            java.lang.String r1 = "version"
            r3 = 0
            r4[r3] = r1
            android.net.Uri r1 = buildPlatformProviderVersionURI(r9)
            r8 = 0
            android.content.Context r5 = com.facebook.FacebookSdk.getApplicationContext()     // Catch:{ all -> 0x006e }
            android.content.pm.PackageManager r5 = r5.getPackageManager()     // Catch:{ all -> 0x006e }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x006e }
            r6.<init>()     // Catch:{ all -> 0x006e }
            java.lang.String r9 = r9.getPackage()     // Catch:{ all -> 0x006e }
            r6.append(r9)     // Catch:{ all -> 0x006e }
            java.lang.String r9 = ".provider.PlatformProvider"
            r6.append(r9)     // Catch:{ all -> 0x006e }
            java.lang.String r9 = r6.toString()     // Catch:{ all -> 0x006e }
            android.content.pm.ProviderInfo r9 = r5.resolveContentProvider(r9, r3)     // Catch:{ all -> 0x006e }
            if (r9 == 0) goto L_0x0068
            r5 = 0
            r6 = 0
            r7 = 0
            r3 = r1
            android.database.Cursor r9 = r2.query(r3, r4, r5, r6, r7)     // Catch:{ NullPointerException | SecurityException -> 0x0047 }
            r8 = r9
            goto L_0x004e
        L_0x0047:
            java.lang.String r9 = TAG     // Catch:{ all -> 0x006e }
            java.lang.String r1 = "Failed to query content resolver."
            android.util.Log.e(r9, r1)     // Catch:{ all -> 0x006e }
        L_0x004e:
            if (r8 == 0) goto L_0x0068
        L_0x0050:
            boolean r9 = r8.moveToNext()     // Catch:{ all -> 0x006e }
            if (r9 == 0) goto L_0x0068
            java.lang.String r9 = "version"
            int r9 = r8.getColumnIndex(r9)     // Catch:{ all -> 0x006e }
            int r9 = r8.getInt(r9)     // Catch:{ all -> 0x006e }
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)     // Catch:{ all -> 0x006e }
            r0.add(r9)     // Catch:{ all -> 0x006e }
            goto L_0x0050
        L_0x0068:
            if (r8 == 0) goto L_0x006d
            r8.close()
        L_0x006d:
            return r0
        L_0x006e:
            r9 = move-exception
            if (r8 == 0) goto L_0x0074
            r8.close()
        L_0x0074:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.internal.NativeProtocol.fetchAllAvailableProtocolVersionsForAppInfo(com.facebook.internal.NativeProtocol$NativeAppInfo):java.util.TreeSet");
    }

    public static int computeLatestAvailableVersionFromVersionSpec(TreeSet<Integer> treeSet, int i, int[] iArr) {
        int length = iArr.length - 1;
        Iterator descendingIterator = treeSet.descendingIterator();
        int i2 = -1;
        int i3 = length;
        int i4 = -1;
        while (descendingIterator.hasNext()) {
            int intValue = ((Integer) descendingIterator.next()).intValue();
            i4 = Math.max(i4, intValue);
            while (i3 >= 0 && iArr[i3] > intValue) {
                i3--;
            }
            if (i3 < 0) {
                return -1;
            }
            if (iArr[i3] == intValue) {
                if (i3 % 2 == 0) {
                    i2 = Math.min(i4, i);
                }
                return i2;
            }
        }
        return -1;
    }

    private static Uri buildPlatformProviderVersionURI(NativeAppInfo nativeAppInfo) {
        StringBuilder sb = new StringBuilder();
        sb.append("content://");
        sb.append(nativeAppInfo.getPackage());
        sb.append(".provider.PlatformProvider/versions");
        return Uri.parse(sb.toString());
    }
}
