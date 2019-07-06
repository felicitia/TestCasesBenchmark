package com.braintreepayments.api;

import android.text.TextUtils;
import com.braintreepayments.api.interfaces.BraintreeResponseListener;
import com.braintreepayments.api.interfaces.ConfigurationListener;
import com.braintreepayments.api.internal.UUIDHelper;
import com.braintreepayments.api.models.Configuration;
import com.kount.api.DataCollector.CompletionHandler;
import com.kount.api.DataCollector.Error;
import com.kount.api.DataCollector.LocationConfig;
import org.json.JSONException;
import org.json.JSONObject;

public class DataCollector {
    public static void collectDeviceData(BraintreeFragment braintreeFragment, BraintreeResponseListener<String> braintreeResponseListener) {
        collectDeviceData(braintreeFragment, null, braintreeResponseListener);
    }

    public static void collectDeviceData(final BraintreeFragment braintreeFragment, final String str, final BraintreeResponseListener<String> braintreeResponseListener) {
        braintreeFragment.waitForConfiguration(new ConfigurationListener() {
            public void onConfigurationFetched(Configuration configuration) {
                final String str;
                final JSONObject jSONObject = new JSONObject();
                try {
                    String payPalClientMetadataId = DataCollector.getPayPalClientMetadataId(braintreeFragment.getApplicationContext());
                    if (!TextUtils.isEmpty(payPalClientMetadataId)) {
                        jSONObject.put("correlation_id", payPalClientMetadataId);
                    }
                } catch (JSONException unused) {
                }
                if (configuration.getKount().isEnabled()) {
                    if (str != null) {
                        str = str;
                    } else {
                        str = configuration.getKount().getKountMerchantId();
                    }
                    try {
                        final String formattedUUID = UUIDHelper.getFormattedUUID();
                        DataCollector.startDeviceCollector(braintreeFragment, str, formattedUUID, new BraintreeResponseListener<String>() {
                            public void onResponse(String str) {
                                try {
                                    jSONObject.put("device_session_id", formattedUUID);
                                    jSONObject.put("fraud_merchant_id", str);
                                } catch (JSONException unused) {
                                }
                                braintreeResponseListener.onResponse(jSONObject.toString());
                            }
                        });
                    } catch (ClassNotFoundException | NoClassDefFoundError | NumberFormatException unused2) {
                        braintreeResponseListener.onResponse(jSONObject.toString());
                    }
                } else {
                    braintreeResponseListener.onResponse(jSONObject.toString());
                }
            }
        });
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0009, code lost:
        return com.paypal.android.sdk.data.collector.PayPalDataCollector.getClientMetadataId(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x000c, code lost:
        return "";
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0005 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getPayPalClientMetadataId(android.content.Context r1) {
        /*
            java.lang.String r0 = com.paypal.android.sdk.onetouch.core.PayPalOneTouchCore.getClientMetadataId(r1)     // Catch:{ NoClassDefFoundError -> 0x0005 }
            return r0
        L_0x0005:
            java.lang.String r1 = com.paypal.android.sdk.data.collector.PayPalDataCollector.getClientMetadataId(r1)     // Catch:{ NoClassDefFoundError -> 0x000a }
            return r1
        L_0x000a:
            java.lang.String r1 = ""
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.braintreepayments.api.DataCollector.getPayPalClientMetadataId(android.content.Context):java.lang.String");
    }

    /* access modifiers changed from: private */
    public static void startDeviceCollector(final BraintreeFragment braintreeFragment, final String str, final String str2, final BraintreeResponseListener<String> braintreeResponseListener) throws ClassNotFoundException, NumberFormatException {
        braintreeFragment.sendAnalyticsEvent("data-collector.kount.started");
        Class.forName(com.kount.api.DataCollector.class.getName());
        braintreeFragment.waitForConfiguration(new ConfigurationListener() {
            public void onConfigurationFetched(Configuration configuration) {
                com.kount.api.DataCollector instance = com.kount.api.DataCollector.getInstance();
                instance.setContext(braintreeFragment.getApplicationContext());
                instance.setMerchantID(Integer.parseInt(str));
                instance.setLocationCollectorConfig(LocationConfig.COLLECT);
                instance.setEnvironment(DataCollector.getDeviceCollectorEnvironment(configuration.getEnvironment()));
                instance.collectForSession(str2, new CompletionHandler() {
                    public void completed(String str) {
                        braintreeFragment.sendAnalyticsEvent("data-collector.kount.succeeded");
                        if (braintreeResponseListener != null) {
                            braintreeResponseListener.onResponse(str);
                        }
                    }

                    public void failed(String str, Error error) {
                        braintreeFragment.sendAnalyticsEvent("data-collector.kount.failed");
                        if (braintreeResponseListener != null) {
                            braintreeResponseListener.onResponse(str);
                        }
                    }
                });
            }
        });
    }

    static int getDeviceCollectorEnvironment(String str) {
        return "production".equalsIgnoreCase(str) ? 2 : 1;
    }
}
