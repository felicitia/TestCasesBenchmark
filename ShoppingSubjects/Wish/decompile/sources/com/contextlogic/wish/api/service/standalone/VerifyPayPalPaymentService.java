package com.contextlogic.wish.api.service.standalone;

import com.braintreepayments.api.models.PayPalAccountNonce;
import com.braintreepayments.api.models.PaymentMethodNonce;
import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.model.WishDeclineRedirectInfo;
import com.contextlogic.wish.api.service.SingleApiService;
import com.contextlogic.wish.util.JsonUtil;
import com.contextlogic.wish.util.PayPalUtil;
import org.json.JSONException;

public class VerifyPayPalPaymentService extends SingleApiService {

    public interface FailureCallback {
        void onFailure(String str, int i, WishDeclineRedirectInfo wishDeclineRedirectInfo);
    }

    public interface SuccessCallback {
        void onSuccess(String str);
    }

    public void requestService(PaymentMethodNonce paymentMethodNonce, String str, String str2, String str3, int i, final SuccessCallback successCallback, final FailureCallback failureCallback) {
        ApiRequest apiRequest = new ApiRequest("payment/paypal/braintree/complete");
        apiRequest.addParameter("cart_type", i);
        if (str3 != null) {
            apiRequest.addParameter("checkout_offer_id", (Object) str3);
        }
        if (paymentMethodNonce instanceof PayPalAccountNonce) {
            PayPalUtil.addNonceParams((PayPalAccountNonce) paymentMethodNonce, apiRequest);
        } else if (paymentMethodNonce.getNonce() != null) {
            apiRequest.addParameter("payment_method_nonce", (Object) paymentMethodNonce.getNonce());
        }
        if (str != null) {
            apiRequest.addParameter("device_data", (Object) str);
        }
        apiRequest.addParameter("currency", (Object) str2);
        startService(apiRequest, new ApiCallback() {
            public String getCallIdentifier() {
                return null;
            }

            public void handleFailure(ApiResponse apiResponse, final String str) {
                if (failureCallback != null) {
                    final int code = apiResponse != null ? apiResponse.getCode() : -1;
                    final WishDeclineRedirectInfo wishDeclineRedirectInfo = null;
                    if (!(apiResponse == null || apiResponse.getData() == null || !JsonUtil.hasValue(apiResponse.getData(), "redirect_info"))) {
                        try {
                            wishDeclineRedirectInfo = new WishDeclineRedirectInfo(apiResponse.getData().getJSONObject("redirect_info"));
                        } catch (Throwable unused) {
                        }
                    }
                    VerifyPayPalPaymentService.this.postRunnable(new Runnable() {
                        public void run() {
                            failureCallback.onFailure(str, code, wishDeclineRedirectInfo);
                        }
                    });
                }
            }

            public void handleSuccess(ApiResponse apiResponse) throws JSONException {
                final String string = apiResponse.getData().getString("transaction_id");
                if (successCallback != null) {
                    VerifyPayPalPaymentService.this.postRunnable(new Runnable() {
                        public void run() {
                            successCallback.onSuccess(string);
                        }
                    });
                }
            }
        });
    }
}
