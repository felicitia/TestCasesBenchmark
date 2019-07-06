package com.contextlogic.wish.api.service.standalone;

import com.braintreepayments.api.models.PayPalAccountNonce;
import com.braintreepayments.api.models.PaymentMethodNonce;
import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.model.WishUserBillingInfo;
import com.contextlogic.wish.api.service.SingleApiService;
import com.contextlogic.wish.util.JsonUtil;
import com.contextlogic.wish.util.PayPalUtil;
import java.text.ParseException;
import org.json.JSONException;

public class PreAuthorizePayPalPaymentService extends SingleApiService {

    public interface FailureCallback {
        void onFailure(String str, int i);
    }

    public interface SuccessCallback {
        void onSuccess(WishUserBillingInfo wishUserBillingInfo);
    }

    public void requestService(PaymentMethodNonce paymentMethodNonce, String str, int i, final SuccessCallback successCallback, final FailureCallback failureCallback) {
        ApiRequest apiRequest = new ApiRequest("billing-info/paypal/braintree/add-or-update");
        apiRequest.addParameter("cart_type", i);
        if (str != null) {
            apiRequest.addParameter("device_data", (Object) str);
        }
        if (paymentMethodNonce instanceof PayPalAccountNonce) {
            PayPalUtil.addNonceParams((PayPalAccountNonce) paymentMethodNonce, apiRequest);
        } else if (paymentMethodNonce.getNonce() != null) {
            apiRequest.addParameter("payment_method_nonce", (Object) paymentMethodNonce.getNonce());
        }
        startService(apiRequest, new ApiCallback() {
            public String getCallIdentifier() {
                return null;
            }

            public void handleFailure(ApiResponse apiResponse, String str) {
                if (failureCallback != null) {
                    failureCallback.onFailure(str, apiResponse != null ? apiResponse.getCode() : -1);
                }
            }

            public void handleSuccess(ApiResponse apiResponse) throws JSONException, ParseException {
                final WishUserBillingInfo wishUserBillingInfo = JsonUtil.hasValue(apiResponse.getData(), "user_billing_details") ? new WishUserBillingInfo(apiResponse.getData().getJSONObject("user_billing_details")) : null;
                if (successCallback != null) {
                    PreAuthorizePayPalPaymentService.this.postRunnable(new Runnable() {
                        public void run() {
                            successCallback.onSuccess(wishUserBillingInfo);
                        }
                    });
                }
            }
        });
    }
}
