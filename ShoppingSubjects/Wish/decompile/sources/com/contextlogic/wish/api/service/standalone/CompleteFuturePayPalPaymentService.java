package com.contextlogic.wish.api.service.standalone;

import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.model.WishDeclineRedirectInfo;
import com.contextlogic.wish.api.service.SingleApiService;
import org.json.JSONException;

public class CompleteFuturePayPalPaymentService extends SingleApiService {

    public interface FailureCallback {
        void onFailure(String str, boolean z, int i, WishDeclineRedirectInfo wishDeclineRedirectInfo);
    }

    public interface SuccessCallback {
        void onSuccess(String str);
    }

    public void requestService(String str, String str2, String str3, int i, final SuccessCallback successCallback, final FailureCallback failureCallback) {
        ApiRequest apiRequest = new ApiRequest("payment/paypal/braintree/complete");
        apiRequest.addParameter("cart_type", i);
        if (str3 != null) {
            apiRequest.addParameter("checkout_offer_id", (Object) str3);
        }
        if (str != null) {
            apiRequest.addParameter("device_data", (Object) str);
        }
        apiRequest.addParameter("currency", (Object) str2);
        startService(apiRequest, new ApiCallback() {
            public String getCallIdentifier() {
                return null;
            }

            /* JADX WARNING: Removed duplicated region for block: B:24:0x004d  */
            /* JADX WARNING: Removed duplicated region for block: B:25:0x0053  */
            /* JADX WARNING: Removed duplicated region for block: B:27:0x0057  */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void handleFailure(com.contextlogic.wish.api.ApiResponse r10, java.lang.String r11) {
                /*
                    r9 = this;
                    com.contextlogic.wish.api.service.standalone.CompleteFuturePayPalPaymentService$FailureCallback r0 = r8
                    if (r0 != 0) goto L_0x0005
                    return
                L_0x0005:
                    r0 = 0
                    if (r10 == 0) goto L_0x002b
                    org.json.JSONObject r1 = r10.getData()
                    if (r1 == 0) goto L_0x002b
                    org.json.JSONObject r1 = r10.getData()
                    java.lang.String r2 = "redirect_info"
                    boolean r1 = com.contextlogic.wish.util.JsonUtil.hasValue(r1, r2)
                    if (r1 == 0) goto L_0x002b
                    com.contextlogic.wish.api.model.WishDeclineRedirectInfo r1 = new com.contextlogic.wish.api.model.WishDeclineRedirectInfo     // Catch:{ Throwable -> 0x002b }
                    org.json.JSONObject r2 = r10.getData()     // Catch:{ Throwable -> 0x002b }
                    java.lang.String r3 = "redirect_info"
                    org.json.JSONObject r2 = r2.getJSONObject(r3)     // Catch:{ Throwable -> 0x002b }
                    r1.<init>(r2)     // Catch:{ Throwable -> 0x002b }
                    r8 = r1
                    goto L_0x002c
                L_0x002b:
                    r8 = r0
                L_0x002c:
                    if (r10 == 0) goto L_0x0049
                    int r0 = r10.getCode()
                    r1 = 22
                    if (r0 == r1) goto L_0x0046
                    int r0 = r10.getCode()
                    r1 = 21
                    if (r0 == r1) goto L_0x0046
                    int r0 = r10.getCode()
                    r1 = 27
                    if (r0 != r1) goto L_0x0049
                L_0x0046:
                    r0 = 1
                    r6 = 1
                    goto L_0x004b
                L_0x0049:
                    r0 = 0
                    r6 = 0
                L_0x004b:
                    if (r10 == 0) goto L_0x0053
                    int r0 = r10.getCode()
                    r7 = r0
                    goto L_0x0055
                L_0x0053:
                    r0 = -1
                    r7 = -1
                L_0x0055:
                    if (r6 == 0) goto L_0x006e
                    java.util.HashMap r0 = new java.util.HashMap
                    r0.<init>()
                    java.lang.String r1 = "error_code"
                    int r10 = r10.getCode()
                    java.lang.String r10 = java.lang.String.valueOf(r10)
                    r0.put(r1, r10)
                    com.contextlogic.wish.analytics.WishAnalyticsLogger$WishAnalyticsEvent r10 = com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent.CLICK_MOBILE_FUTURE_PAYPAL_ERROR_REVOKE_TOKEN_REAUTH
                    com.contextlogic.wish.analytics.WishAnalyticsLogger.trackEvent(r10, r0)
                L_0x006e:
                    com.contextlogic.wish.api.service.standalone.CompleteFuturePayPalPaymentService r10 = com.contextlogic.wish.api.service.standalone.CompleteFuturePayPalPaymentService.this
                    com.contextlogic.wish.api.service.standalone.CompleteFuturePayPalPaymentService$1$1 r0 = new com.contextlogic.wish.api.service.standalone.CompleteFuturePayPalPaymentService$1$1
                    r3 = r0
                    r4 = r9
                    r5 = r11
                    r3.<init>(r5, r6, r7, r8)
                    r10.postRunnable(r0)
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.contextlogic.wish.api.service.standalone.CompleteFuturePayPalPaymentService.AnonymousClass1.handleFailure(com.contextlogic.wish.api.ApiResponse, java.lang.String):void");
            }

            public void handleSuccess(ApiResponse apiResponse) throws JSONException {
                final String string = apiResponse.getData().getString("transaction_id");
                if (successCallback != null) {
                    CompleteFuturePayPalPaymentService.this.postRunnable(new Runnable() {
                        public void run() {
                            successCallback.onSuccess(string);
                        }
                    });
                }
            }
        });
    }
}
