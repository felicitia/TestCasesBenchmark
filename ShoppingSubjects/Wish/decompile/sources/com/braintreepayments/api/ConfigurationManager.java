package com.braintreepayments.api;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.util.Base64;
import com.braintreepayments.api.interfaces.BraintreeResponseListener;
import com.braintreepayments.api.interfaces.ConfigurationListener;
import com.braintreepayments.api.interfaces.HttpResponseCallback;
import com.braintreepayments.api.internal.BraintreeHttpClient;
import com.braintreepayments.api.internal.BraintreeSharedPreferences;
import com.braintreepayments.api.models.ClientToken;
import com.braintreepayments.api.models.Configuration;
import com.braintreepayments.api.models.TokenizationKey;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;

class ConfigurationManager {
    static final long TTL = TimeUnit.MINUTES.toMillis(5);
    static boolean sFetchingConfiguration = false;

    static boolean isFetchingConfiguration() {
        return sFetchingConfiguration;
    }

    static void getConfiguration(BraintreeFragment braintreeFragment, ConfigurationListener configurationListener, BraintreeResponseListener<Exception> braintreeResponseListener) {
        String str = braintreeFragment.getAuthorization() instanceof ClientToken ? ((ClientToken) braintreeFragment.getAuthorization()).getAuthorizationFingerprint() : braintreeFragment.getAuthorization() instanceof TokenizationKey ? braintreeFragment.getAuthorization().toString() : "";
        final String str2 = str;
        String uri = Uri.parse(braintreeFragment.getAuthorization().getConfigUrl()).buildUpon().appendQueryParameter("configVersion", "3").build().toString();
        Context applicationContext = braintreeFragment.getApplicationContext();
        StringBuilder sb = new StringBuilder();
        sb.append(uri);
        sb.append(str2);
        Configuration cachedConfiguration = getCachedConfiguration(applicationContext, sb.toString());
        if (cachedConfiguration != null) {
            configurationListener.onConfigurationFetched(cachedConfiguration);
            return;
        }
        sFetchingConfiguration = true;
        BraintreeHttpClient httpClient = braintreeFragment.getHttpClient();
        final BraintreeFragment braintreeFragment2 = braintreeFragment;
        final String str3 = uri;
        final ConfigurationListener configurationListener2 = configurationListener;
        final BraintreeResponseListener<Exception> braintreeResponseListener2 = braintreeResponseListener;
        AnonymousClass1 r1 = new HttpResponseCallback() {
            public void success(String str) {
                try {
                    Configuration fromJson = Configuration.fromJson(str);
                    Context applicationContext = braintreeFragment2.getApplicationContext();
                    StringBuilder sb = new StringBuilder();
                    sb.append(str3);
                    sb.append(str2);
                    ConfigurationManager.cacheConfiguration(applicationContext, sb.toString(), fromJson);
                    ConfigurationManager.sFetchingConfiguration = false;
                    configurationListener2.onConfigurationFetched(fromJson);
                } catch (JSONException e) {
                    ConfigurationManager.sFetchingConfiguration = false;
                    braintreeResponseListener2.onResponse(e);
                }
            }

            public void failure(Exception exc) {
                ConfigurationManager.sFetchingConfiguration = false;
                braintreeResponseListener2.onResponse(exc);
            }
        };
        httpClient.get(uri, r1);
    }

    private static Configuration getCachedConfiguration(Context context, String str) {
        SharedPreferences sharedPreferences = BraintreeSharedPreferences.getSharedPreferences(context);
        String encodeToString = Base64.encodeToString(str.getBytes(), 0);
        StringBuilder sb = new StringBuilder();
        sb.append(encodeToString);
        sb.append("_timestamp");
        if (System.currentTimeMillis() - sharedPreferences.getLong(sb.toString(), TTL) > TTL) {
            return null;
        }
        try {
            return Configuration.fromJson(sharedPreferences.getString(encodeToString, ""));
        } catch (JSONException unused) {
            return null;
        }
    }

    /* access modifiers changed from: private */
    public static void cacheConfiguration(Context context, String str, Configuration configuration) {
        String encodeToString = Base64.encodeToString(str.getBytes(), 0);
        StringBuilder sb = new StringBuilder();
        sb.append(encodeToString);
        sb.append("_timestamp");
        BraintreeSharedPreferences.getSharedPreferences(context).edit().putString(encodeToString, configuration.toJson()).putLong(sb.toString(), System.currentTimeMillis()).apply();
    }
}
