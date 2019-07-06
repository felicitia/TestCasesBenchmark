package com.a;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import com.a.a.C0006a;
import com.etsy.android.lib.models.AppBuild;

/* compiled from: Decoder */
class c {

    /* compiled from: Decoder */
    private interface a {
        void a();
    }

    private static String b() {
        StringBuilder sb = new StringBuilder();
        sb.append(a.d());
        sb.append("api/click");
        return sb.toString();
    }

    /* access modifiers changed from: private */
    public static String c() {
        StringBuilder sb = new StringBuilder();
        sb.append(a.d());
        sb.append("v3/deferred_deeplink/lookup");
        return sb.toString();
    }

    static void a(Intent intent) {
        if (intent == null) {
            Log.d("BitlySDK", "Bitly SDK no valid intent found to process");
            return;
        }
        Log.d("BitlySDK", "Bitly SDK handling intent");
        a(b(intent));
    }

    private static void a(String str) {
        if (TextUtils.isEmpty(str)) {
            Log.d("BitlySDK", "Bitly SDK no valid link found to process");
            return;
        }
        String str2 = a.a(str) ? str : a.b(str) ? b(str) : null;
        if (!TextUtils.isEmpty(str2)) {
            try {
                Log.d("BitlySDK", "Bitly SDK click call started");
                b(Uri.parse(b()).buildUpon().appendQueryParameter("app_id", a.a()).appendQueryParameter("device_id", a.b()).appendQueryParameter("device_id_type", AppBuild.ANDROID_PLATFORM).appendQueryParameter("link", str2).build(), str, str2, a.c(), null);
            } catch (Exception e) {
                StringBuilder sb = new StringBuilder();
                sb.append("Bitly SDK failed to parse link: ");
                sb.append(e.getMessage());
                Log.d("BitlySDK", sb.toString());
                if (a.c() != null) {
                    a.c().a(new d(e.getMessage(), e, str, str2));
                }
            }
        } else {
            Log.d("BitlySDK", String.format("Bitly SDK URL %s is not supported by config", new Object[]{str}));
        }
    }

    static void a(Context context, boolean z) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean("bitly.should.check.deeplink", true).apply();
        b(context, 0, z);
    }

    /* access modifiers changed from: private */
    public static void b(final Context context, final int i, final boolean z) {
        final SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        long j = (long) i;
        if (j > 8000 || !defaultSharedPreferences.getBoolean("bitly.should.check.deeplink", true)) {
            Log.d("BitlySDK", "Bitly SDK found no deeplink");
        } else {
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    Log.d("BitlySDK", "Bitly SDK deeplink call started");
                    c.b(Uri.parse(c.c()).buildUpon().appendQueryParameter("app_id", a.a()).appendQueryParameter("device_id", a.b()).appendQueryParameter("device_id_type", AppBuild.ANDROID_PLATFORM).build(), null, null, new C0006a() {
                        public void a(f fVar) {
                            if (!TextUtils.isEmpty(fVar.d())) {
                                if (a.c() != null) {
                                    a.c().a(fVar);
                                }
                                defaultSharedPreferences.edit().putBoolean("bitly.should.check.deeplink", false).apply();
                            }
                        }

                        public void a(d dVar) {
                            if (a.c() != null) {
                                a.c().a(dVar);
                            }
                        }
                    }, new a() {
                        public void a() {
                            if (z) {
                                c.b(context, i == 0 ? 1000 : i + i, z);
                            }
                        }
                    });
                }
            }, j);
        }
    }

    /* access modifiers changed from: private */
    public static void b(Uri uri, String str, String str2, C0006a aVar, a aVar2) {
        final C0006a aVar3 = aVar;
        final String str3 = str;
        final String str4 = str2;
        final a aVar4 = aVar2;
        AnonymousClass2 r0 = new e(uri) {
            /* access modifiers changed from: 0000 */
            public void a(f fVar) {
                Log.d("BitlySDK", "Bitly SDK parsing JSON response and calling handler");
                if (aVar3 != null) {
                    aVar3.a(fVar);
                }
            }

            /* access modifiers changed from: 0000 */
            public void a(Exception exc) {
                StringBuilder sb = new StringBuilder();
                sb.append("Bitly SDK failed to parse JSON: ");
                sb.append(exc.getMessage());
                Log.d("BitlySDK", sb.toString());
                if (aVar3 != null) {
                    aVar3.a(new d(exc.getMessage(), exc, str3, str4));
                }
            }

            /* access modifiers changed from: protected */
            /* renamed from: a */
            public void onPostExecute(Void voidR) {
                super.onPostExecute(voidR);
                if (aVar4 != null && !b()) {
                    aVar4.a();
                }
            }
        };
        r0.a();
    }

    private static String b(String str) {
        String queryParameter = Uri.parse(str).getQueryParameter("bitly_bitlink");
        if (TextUtils.isEmpty(queryParameter)) {
            return queryParameter;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("http://");
        sb.append(queryParameter);
        return sb.toString();
    }

    private static String b(Intent intent) {
        if (!"android.intent.action.VIEW".equals(intent.getAction()) || intent.getData() == null) {
            return null;
        }
        return intent.getData().toString();
    }
}
