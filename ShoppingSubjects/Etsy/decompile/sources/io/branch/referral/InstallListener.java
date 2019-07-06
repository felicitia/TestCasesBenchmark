package io.branch.referral;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import com.android.installreferrer.api.InstallReferrerClient;
import com.android.installreferrer.api.InstallReferrerStateListener;
import io.branch.referral.Defines.Jsonkey;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;

public class InstallListener extends BroadcastReceiver {
    private static a callback_ = null;
    private static String installID_ = "bnc_no_value";
    private static boolean isReferrerClientAvailable;
    private static boolean isWaitingForReferrer;
    static boolean unReportedReferrerAvailable;

    interface a {
        void f();
    }

    private static class b {
        private Object a;
        private Context b;

        private b(Context context) {
            this.b = context;
        }

        /* access modifiers changed from: private */
        public boolean a() {
            try {
                InstallReferrerClient build = InstallReferrerClient.newBuilder(this.b).build();
                this.a = build;
                build.startConnection(new InstallReferrerStateListener() {
                });
                return true;
            } catch (Throwable th) {
                m.b("BranchSDK", th.getMessage());
                return false;
            }
        }
    }

    public static void captureInstallReferrer(Context context, long j, a aVar) {
        callback_ = aVar;
        if (unReportedReferrerAvailable) {
            reportInstallReferrer();
            return;
        }
        isWaitingForReferrer = true;
        isReferrerClientAvailable = new b(context).a();
        new Handler().postDelayed(new Runnable() {
            public void run() {
                InstallListener.reportInstallReferrer();
            }
        }, j);
    }

    public void onReceive(Context context, Intent intent) {
        processReferrerInfo(context, intent.getStringExtra("referrer"), 0, 0);
        if (isWaitingForReferrer && !isReferrerClientAvailable) {
            reportInstallReferrer();
        }
    }

    /* access modifiers changed from: private */
    public static void onReferrerClientFinished(Context context, String str, long j, long j2) {
        processReferrerInfo(context, str, j, j2);
        if (isWaitingForReferrer) {
            reportInstallReferrer();
        }
    }

    /* access modifiers changed from: private */
    public static void onReferrerClientError() {
        isReferrerClientAvailable = false;
    }

    private static void processReferrerInfo(Context context, String str, long j, long j2) {
        String[] split;
        m a2 = m.a(context);
        if (j > 0) {
            a2.a("bnc_referrer_click_ts", j);
        }
        if (j2 > 0) {
            a2.a("bnc_install_begin_ts", j2);
        }
        if (str != null) {
            try {
                String decode = URLDecoder.decode(str, "UTF-8");
                HashMap hashMap = new HashMap();
                for (String str2 : decode.split("&")) {
                    if (!TextUtils.isEmpty(str2)) {
                        String str3 = "=";
                        if (!str2.contains("=") && str2.contains("-")) {
                            str3 = "-";
                        }
                        String[] split2 = str2.split(str3);
                        if (split2.length > 1) {
                            hashMap.put(URLDecoder.decode(split2[0], "UTF-8"), URLDecoder.decode(split2[1], "UTF-8"));
                        }
                    }
                }
                if (hashMap.containsKey(Jsonkey.LinkClickID.getKey())) {
                    installID_ = (String) hashMap.get(Jsonkey.LinkClickID.getKey());
                    a2.j(installID_);
                }
                if (hashMap.containsKey(Jsonkey.IsFullAppConv.getKey()) && hashMap.containsKey(Jsonkey.ReferringLink.getKey())) {
                    a2.b(Boolean.parseBoolean((String) hashMap.get(Jsonkey.IsFullAppConv.getKey())));
                    a2.m((String) hashMap.get(Jsonkey.ReferringLink.getKey()));
                }
                if (hashMap.containsKey(Jsonkey.GoogleSearchInstallReferrer.getKey())) {
                    a2.k((String) hashMap.get(Jsonkey.GoogleSearchInstallReferrer.getKey()));
                    a2.l(decode);
                }
            } catch (UnsupportedEncodingException e) {
                com.google.a.a.a.a.a.a.a(e);
            } catch (IllegalArgumentException e2) {
                com.google.a.a.a.a.a.a.a(e2);
                Log.w("BranchSDK", "Illegal characters in url encoded string");
            }
        }
    }

    public static String getInstallationID() {
        return installID_;
    }

    /* access modifiers changed from: private */
    public static void reportInstallReferrer() {
        unReportedReferrerAvailable = true;
        if (callback_ != null) {
            callback_.f();
            callback_ = null;
            unReportedReferrerAvailable = false;
            isWaitingForReferrer = false;
            isReferrerClientAvailable = false;
        }
    }
}
