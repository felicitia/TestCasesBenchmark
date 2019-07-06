package com.contextlogic.wish.api.service.standalone;

import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.model.WishCart;
import com.contextlogic.wish.api.model.WishCommerceCashCart;
import com.contextlogic.wish.api.model.WishCommerceLoanCart;
import com.contextlogic.wish.api.model.WishShippingInfo;
import com.contextlogic.wish.api.model.WishUserBillingInfo;
import com.contextlogic.wish.api.service.SingleApiService;

public class PreVerifyPayPalPaymentService extends SingleApiService {

    public interface FailureCallback {
        void onFailure(String str, int i);
    }

    public interface SuccessCallback {
        void onSuccess(WishCart wishCart, WishCommerceCashCart wishCommerceCashCart, WishCommerceLoanCart wishCommerceLoanCart, WishShippingInfo wishShippingInfo, WishUserBillingInfo wishUserBillingInfo);
    }

    public void requestService(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, final int i, final SuccessCallback successCallback, final FailureCallback failureCallback) {
        startService(getApiRequest(str, str2, str3, str4, str5, str6, str7, str8, str9, i), new ApiCallback() {
            public String getCallIdentifier() {
                return null;
            }

            public void handleFailure(final ApiResponse apiResponse, final String str) {
                if (failureCallback != null) {
                    PreVerifyPayPalPaymentService.this.postRunnable(new Runnable() {
                        public void run() {
                            failureCallback.onFailure(str, apiResponse != null ? apiResponse.getCode() : -1);
                        }
                    });
                }
            }

            /* JADX WARNING: type inference failed for: r8v0, types: [com.contextlogic.wish.api.model.WishShippingInfo] */
            /* JADX WARNING: type inference failed for: r7v0, types: [com.contextlogic.wish.api.model.WishCommerceLoanCart] */
            /* JADX WARNING: type inference failed for: r6v0, types: [com.contextlogic.wish.api.model.WishCommerceCashCart] */
            /* JADX WARNING: type inference failed for: r6v1 */
            /* JADX WARNING: type inference failed for: r0v7, types: [com.contextlogic.wish.api.model.WishCommerceLoanCart] */
            /* JADX WARNING: type inference failed for: r7v1 */
            /* JADX WARNING: type inference failed for: r6v2 */
            /* JADX WARNING: type inference failed for: r8v1 */
            /* JADX WARNING: type inference failed for: r0v8, types: [com.contextlogic.wish.api.model.WishCommerceCashCart] */
            /* JADX WARNING: type inference failed for: r6v3 */
            /* JADX WARNING: type inference failed for: r7v2 */
            /* JADX WARNING: type inference failed for: r7v3 */
            /* JADX WARNING: type inference failed for: r6v4 */
            /* JADX WARNING: type inference failed for: r8v2 */
            /* JADX WARNING: type inference failed for: r6v5 */
            /* JADX WARNING: type inference failed for: r7v4 */
            /* JADX WARNING: type inference failed for: r6v6 */
            /* JADX WARNING: type inference failed for: r1v19, types: [com.contextlogic.wish.api.model.WishShippingInfo] */
            /* JADX WARNING: type inference failed for: r8v3 */
            /* JADX WARNING: type inference failed for: r6v7 */
            /* JADX WARNING: type inference failed for: r7v5 */
            /* JADX WARNING: type inference failed for: r7v6 */
            /* JADX WARNING: type inference failed for: r6v8 */
            /* JADX WARNING: type inference failed for: r6v9 */
            /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r6v4
              assigns: []
              uses: []
              mth insns count: 82
            	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
            	at jadx.core.dex.visitors.typeinference.TypeSearch$$Lambda$100/871566395.accept(Unknown Source)
            	at java.util.ArrayList.forEach(ArrayList.java:1249)
            	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
            	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
            	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
            	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
            	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
            	at jadx.core.dex.visitors.DepthTraversal$$Lambda$34/1534130292.accept(Unknown Source)
            	at java.util.ArrayList.forEach(ArrayList.java:1249)
            	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
            	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$0(DepthTraversal.java:13)
            	at jadx.core.dex.visitors.DepthTraversal$$Lambda$33/410251182.accept(Unknown Source)
            	at java.util.ArrayList.forEach(ArrayList.java:1249)
            	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:13)
            	at jadx.core.ProcessClass.process(ProcessClass.java:30)
            	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
            	at jadx.api.JavaClass.decompile(JavaClass.java:62)
            	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
            	at jadx.api.JadxDecompiler$$Lambda$28/1037163664.run(Unknown Source)
             */
            /* JADX WARNING: Removed duplicated region for block: B:25:0x00b2 A[SYNTHETIC, Splitter:B:25:0x00b2] */
            /* JADX WARNING: Removed duplicated region for block: B:32:0x00c8  */
            /* JADX WARNING: Removed duplicated region for block: B:34:? A[RETURN, SYNTHETIC] */
            /* JADX WARNING: Unknown variable types count: 9 */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void handleSuccess(com.contextlogic.wish.api.ApiResponse r11) throws org.json.JSONException, java.text.ParseException {
                /*
                    r10 = this;
                    int r0 = r10
                    com.contextlogic.wish.payments.CartContext$CartType r1 = com.contextlogic.wish.payments.CartContext.CartType.COMMERCE_GOODS
                    int r1 = r1.getValue()
                    r2 = 0
                    if (r0 != r1) goto L_0x0068
                    com.contextlogic.wish.api.model.WishCart r0 = new com.contextlogic.wish.api.model.WishCart
                    org.json.JSONObject r1 = r11.getData()
                    java.lang.String r3 = "cart_info"
                    org.json.JSONObject r1 = r1.getJSONObject(r3)
                    r0.<init>(r1)
                    com.contextlogic.wish.api.datacenter.StatusDataCenter r1 = com.contextlogic.wish.api.datacenter.StatusDataCenter.getInstance()
                    int r3 = r0.getTotalItemCount()
                    r1.updateCartCount(r3)
                    org.json.JSONObject r1 = r11.getData()
                    java.lang.String r3 = "checkout_offer"
                    boolean r1 = com.contextlogic.wish.util.JsonUtil.hasValue(r1, r3)
                    if (r1 == 0) goto L_0x0043
                    com.contextlogic.wish.api.model.WishCheckoutOffer r1 = new com.contextlogic.wish.api.model.WishCheckoutOffer     // Catch:{ Throwable -> 0x0043 }
                    org.json.JSONObject r3 = r11.getData()     // Catch:{ Throwable -> 0x0043 }
                    java.lang.String r4 = "checkout_offer"
                    org.json.JSONObject r3 = r3.getJSONObject(r4)     // Catch:{ Throwable -> 0x0043 }
                    r1.<init>(r3)     // Catch:{ Throwable -> 0x0043 }
                    r0.setCheckoutOffer(r1)     // Catch:{ Throwable -> 0x0043 }
                L_0x0043:
                    org.json.JSONObject r1 = r11.getData()
                    java.lang.String r3 = "shipping_info"
                    boolean r1 = com.contextlogic.wish.util.JsonUtil.hasValue(r1, r3)
                    if (r1 == 0) goto L_0x0063
                    com.contextlogic.wish.api.model.WishShippingInfo r1 = new com.contextlogic.wish.api.model.WishShippingInfo     // Catch:{ Throwable -> 0x0063 }
                    org.json.JSONObject r3 = r11.getData()     // Catch:{ Throwable -> 0x0063 }
                    java.lang.String r4 = "shipping_info"
                    org.json.JSONObject r3 = r3.getJSONObject(r4)     // Catch:{ Throwable -> 0x0063 }
                    r1.<init>(r3)     // Catch:{ Throwable -> 0x0063 }
                    r5 = r0
                    r8 = r1
                    r6 = r2
                    r7 = r6
                    goto L_0x00a6
                L_0x0063:
                    r5 = r0
                    r6 = r2
                L_0x0065:
                    r7 = r6
                L_0x0066:
                    r8 = r7
                    goto L_0x00a6
                L_0x0068:
                    int r0 = r10
                    com.contextlogic.wish.payments.CartContext$CartType r1 = com.contextlogic.wish.payments.CartContext.CartType.COMMERCE_CASH
                    int r1 = r1.getValue()
                    if (r0 != r1) goto L_0x0085
                    com.contextlogic.wish.api.model.WishCommerceCashCart r0 = new com.contextlogic.wish.api.model.WishCommerceCashCart
                    org.json.JSONObject r1 = r11.getData()
                    java.lang.String r3 = "cart_info"
                    org.json.JSONObject r1 = r1.getJSONObject(r3)
                    r0.<init>(r1)
                    r6 = r0
                    r5 = r2
                    r7 = r5
                    goto L_0x0066
                L_0x0085:
                    int r0 = r10
                    com.contextlogic.wish.payments.CartContext$CartType r1 = com.contextlogic.wish.payments.CartContext.CartType.COMMERCE_LOAN
                    int r1 = r1.getValue()
                    if (r0 != r1) goto L_0x00a3
                    com.contextlogic.wish.api.model.WishCommerceLoanCart r0 = new com.contextlogic.wish.api.model.WishCommerceLoanCart
                    org.json.JSONObject r1 = r11.getData()
                    java.lang.String r3 = "cart_info"
                    org.json.JSONObject r1 = r1.getJSONObject(r3)
                    r0.<init>(r1)
                    r7 = r0
                    r5 = r2
                    r6 = r5
                    r8 = r6
                    goto L_0x00a6
                L_0x00a3:
                    r5 = r2
                    r6 = r5
                    goto L_0x0065
                L_0x00a6:
                    org.json.JSONObject r0 = r11.getData()
                    java.lang.String r1 = "user_billing_details"
                    boolean r0 = com.contextlogic.wish.util.JsonUtil.hasValue(r0, r1)
                    if (r0 == 0) goto L_0x00c3
                    com.contextlogic.wish.api.model.WishUserBillingInfo r0 = new com.contextlogic.wish.api.model.WishUserBillingInfo     // Catch:{ Throwable -> 0x00c3 }
                    org.json.JSONObject r11 = r11.getData()     // Catch:{ Throwable -> 0x00c3 }
                    java.lang.String r1 = "user_billing_details"
                    org.json.JSONObject r11 = r11.getJSONObject(r1)     // Catch:{ Throwable -> 0x00c3 }
                    r0.<init>(r11)     // Catch:{ Throwable -> 0x00c3 }
                    r9 = r0
                    goto L_0x00c4
                L_0x00c3:
                    r9 = r2
                L_0x00c4:
                    com.contextlogic.wish.api.service.standalone.PreVerifyPayPalPaymentService$SuccessCallback r11 = r11
                    if (r11 == 0) goto L_0x00d4
                    com.contextlogic.wish.api.service.standalone.PreVerifyPayPalPaymentService r11 = com.contextlogic.wish.api.service.standalone.PreVerifyPayPalPaymentService.this
                    com.contextlogic.wish.api.service.standalone.PreVerifyPayPalPaymentService$1$2 r0 = new com.contextlogic.wish.api.service.standalone.PreVerifyPayPalPaymentService$1$2
                    r3 = r0
                    r4 = r10
                    r3.<init>(r5, r6, r7, r8, r9)
                    r11.postRunnable(r0)
                L_0x00d4:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.contextlogic.wish.api.service.standalone.PreVerifyPayPalPaymentService.AnonymousClass1.handleSuccess(com.contextlogic.wish.api.ApiResponse):void");
            }
        });
    }

    private ApiRequest getApiRequest(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, int i) {
        ApiRequest apiRequest = new ApiRequest("payment/paypal/preverify");
        apiRequest.addParameter("cart_type", i);
        if (str != null) {
            apiRequest.addParameter("full_name", (Object) str);
        }
        if (str2 != null) {
            apiRequest.addParameter("street_address1", (Object) str2);
        }
        if (str3 != null) {
            apiRequest.addParameter("street_address2", (Object) str3);
        }
        if (str4 != null) {
            apiRequest.addParameter("city", (Object) str4);
        }
        if (str5 != null) {
            apiRequest.addParameter("state", (Object) str5);
        }
        if (str6 != null) {
            apiRequest.addParameter("zipcode", (Object) str6);
        }
        if (str7 != null) {
            apiRequest.addParameter("country", (Object) str7);
        }
        if (str8 != null) {
            apiRequest.addParameter("phone_number", (Object) str8);
        }
        apiRequest.addParameter("supports_address", (Object) "true");
        apiRequest.addParameter("currency", (Object) str9);
        return apiRequest;
    }
}
