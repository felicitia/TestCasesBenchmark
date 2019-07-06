package com.facebook.appevents.internal;

import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import com.etsy.android.lib.models.ResponseConstants;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.f;
import com.facebook.internal.aa;
import com.facebook.internal.j;
import com.facebook.internal.k;
import com.facebook.internal.z;
import java.math.BigDecimal;
import java.util.Currency;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: AutomaticAnalyticsLogger */
public class c {
    /* access modifiers changed from: private */
    public static final String a = c.class.getCanonicalName();
    /* access modifiers changed from: private */
    @Nullable
    public static Object b;

    public static void a() {
        Context f = f.f();
        String j = f.j();
        boolean m = f.m();
        aa.a((Object) f, ResponseConstants.CONTEXT);
        if (!m) {
            return;
        }
        if (f instanceof Application) {
            AppEventsLogger.a((Application) f, j);
        } else {
            Log.w(a, "Automatic logging of basic events will not happen, because FacebookSdk.getApplicationContext() returns object that is not instance of android.app.Application. Make sure you call FacebookSdk.sdkInitialize() from Application class and pass application context.");
        }
    }

    public static void a(String str, long j) {
        Context f = f.f();
        String j2 = f.j();
        aa.a((Object) f, ResponseConstants.CONTEXT);
        j a2 = k.a(j2, false);
        if (a2 != null && a2.f() && j > 0) {
            AppEventsLogger a3 = AppEventsLogger.a(f);
            Bundle bundle = new Bundle(1);
            bundle.putCharSequence("fb_aa_time_spent_view_name", str);
            a3.a("fb_aa_time_spent_on_view", (double) j, bundle);
        }
    }

    public static boolean a(final Context context, int i, Intent intent) {
        if (intent == null || !b()) {
            return false;
        }
        final String stringExtra = intent.getStringExtra("INAPP_PURCHASE_DATA");
        if (i == -1) {
            AnonymousClass1 r3 = new ServiceConnection() {
                public void onServiceDisconnected(ComponentName componentName) {
                    c.b = null;
                    z.b(c.a, "In-app billing service disconnected");
                }

                public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                    c.b = e.a(context, iBinder);
                    try {
                        JSONObject jSONObject = new JSONObject(stringExtra);
                        String string = jSONObject.getString("productId");
                        String a2 = e.a(context, string, c.b, jSONObject.has("autoRenewing"));
                        if (a2.equals("")) {
                            context.unbindService(this);
                            return;
                        }
                        JSONObject jSONObject2 = new JSONObject(a2);
                        AppEventsLogger a3 = AppEventsLogger.a(context);
                        Bundle bundle = new Bundle(1);
                        bundle.putCharSequence("fb_iap_product_id", string);
                        bundle.putCharSequence("fb_iap_purchase_time", jSONObject.getString("purchaseTime"));
                        bundle.putCharSequence("fb_iap_purchase_state", jSONObject.getString("purchaseState"));
                        bundle.putCharSequence("fb_iap_purchase_token", jSONObject.getString("purchaseToken"));
                        bundle.putCharSequence("fb_iap_package_name", jSONObject.getString("packageName"));
                        bundle.putCharSequence("fb_iap_product_type", jSONObject2.getString("type"));
                        bundle.putCharSequence("fb_iap_product_title", jSONObject2.getString("title"));
                        bundle.putCharSequence("fb_iap_product_description", jSONObject2.getString("description"));
                        bundle.putCharSequence("fb_iap_subs_auto_renewing", Boolean.toString(jSONObject.optBoolean("autoRenewing", false)));
                        bundle.putCharSequence("fb_iap_subs_period", jSONObject2.optString("subscriptionPeriod"));
                        bundle.putCharSequence("fb_free_trial_period", jSONObject2.optString("freeTrialPeriod"));
                        bundle.putCharSequence("fb_intro_price_amount_micros", jSONObject2.optString("introductoryPriceAmountMicros"));
                        bundle.putCharSequence("fb_intro_price_cycles", jSONObject2.optString("introductoryPriceCycles"));
                        a3.b(new BigDecimal(((double) jSONObject2.getLong("price_amount_micros")) / 1000000.0d), Currency.getInstance(jSONObject2.getString("price_currency_code")), bundle);
                        context.unbindService(this);
                    } catch (JSONException e) {
                        Log.e(c.a, "Error parsing in-app purchase data.", e);
                    } catch (Throwable th) {
                        context.unbindService(this);
                        throw th;
                    }
                }
            };
            Intent intent2 = new Intent("com.android.vending.billing.InAppBillingService.BIND");
            intent2.setPackage("com.android.vending");
            context.bindService(intent2, r3, 1);
        }
        return true;
    }

    public static boolean b() {
        j a2 = k.a(f.j());
        boolean z = false;
        if (a2 == null) {
            return false;
        }
        if (f.m() && a2.j()) {
            z = true;
        }
        return z;
    }
}
