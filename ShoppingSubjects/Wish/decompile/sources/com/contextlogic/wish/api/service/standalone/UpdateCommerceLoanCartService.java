package com.contextlogic.wish.api.service.standalone;

import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.model.WishCommerceLoanCart;
import com.contextlogic.wish.api.model.WishUserBillingInfo;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.SingleApiService;
import com.contextlogic.wish.util.JsonUtil;
import java.text.ParseException;
import org.json.JSONException;

public class UpdateCommerceLoanCartService extends SingleApiService {

    public interface SuccessCallback {
        void onSuccess(WishCommerceLoanCart wishCommerceLoanCart, WishUserBillingInfo wishUserBillingInfo);
    }

    public void requestService(final SuccessCallback successCallback, final DefaultFailureCallback defaultFailureCallback) {
        startService(new ApiRequest("commerce-loan-cart/update"), new ApiCallback() {
            public String getCallIdentifier() {
                return null;
            }

            public void handleFailure(ApiResponse apiResponse, final String str) {
                if (defaultFailureCallback != null) {
                    UpdateCommerceLoanCartService.this.postRunnable(new Runnable() {
                        public void run() {
                            defaultFailureCallback.onFailure(str);
                        }
                    });
                }
            }

            public void handleSuccess(ApiResponse apiResponse) throws JSONException, ParseException {
                final WishCommerceLoanCart wishCommerceLoanCart = new WishCommerceLoanCart(apiResponse.getData().getJSONObject("cart_info"));
                final WishUserBillingInfo wishUserBillingInfo = null;
                try {
                    if (JsonUtil.hasValue(apiResponse.getData(), "user_billing_details")) {
                        wishUserBillingInfo = new WishUserBillingInfo(apiResponse.getData().getJSONObject("user_billing_details"));
                    }
                } catch (Throwable unused) {
                }
                if (successCallback != null) {
                    UpdateCommerceLoanCartService.this.postRunnable(new Runnable() {
                        public void run() {
                            successCallback.onSuccess(wishCommerceLoanCart, wishUserBillingInfo);
                        }
                    });
                }
            }
        });
    }
}
