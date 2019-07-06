package com.contextlogic.wish.api.service.standalone;

import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.model.WishSignupFreeGiftCart;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.SingleApiService;
import java.text.ParseException;
import org.json.JSONException;

public class ClaimFreeSignupGiftService extends SingleApiService {

    public interface SuccessCallback {
        void onSuccess(WishSignupFreeGiftCart wishSignupFreeGiftCart);
    }

    public void requestService(String str, String str2, final boolean z, boolean z2, final SuccessCallback successCallback, final DefaultFailureCallback defaultFailureCallback) {
        ApiRequest apiRequest = new ApiRequest("mobile/claim-signup-gift");
        apiRequest.addParameter("product_id", (Object) str);
        apiRequest.addParameter("variation_id", (Object) str2);
        if (z) {
            apiRequest.addParameter("remove_from_cart", (Object) "true");
        }
        if (z2) {
            apiRequest.addParameter("from_free_gift_tab", (Object) "true");
        }
        startService(apiRequest, new ApiCallback() {
            public String getCallIdentifier() {
                return null;
            }

            public void handleFailure(ApiResponse apiResponse, final String str) {
                if (defaultFailureCallback != null) {
                    ClaimFreeSignupGiftService.this.postRunnable(new Runnable() {
                        public void run() {
                            defaultFailureCallback.onFailure(str);
                        }
                    });
                }
            }

            public void handleSuccess(ApiResponse apiResponse) throws JSONException, ParseException {
                if (successCallback == null) {
                    return;
                }
                if (z) {
                    ClaimFreeSignupGiftService.this.postRunnable(new Runnable() {
                        public void run() {
                            successCallback.onSuccess(null);
                        }
                    });
                    return;
                }
                final WishSignupFreeGiftCart wishSignupFreeGiftCart = new WishSignupFreeGiftCart(apiResponse.getData());
                ClaimFreeSignupGiftService.this.postRunnable(new Runnable() {
                    public void run() {
                        successCallback.onSuccess(wishSignupFreeGiftCart);
                    }
                });
            }
        });
    }
}
