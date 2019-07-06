package com.contextlogic.wish.api.service.standalone;

import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.model.WishCart;
import com.contextlogic.wish.api.model.WishCheckoutOffer;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.SingleApiService;
import com.contextlogic.wish.util.JsonUtil;
import java.text.ParseException;
import org.json.JSONException;

public class CartAbandonOfferClaimService extends SingleApiService {

    public interface SuccessCallback {
        void onSuccess(WishCart wishCart);
    }

    public void requestService(String str, final SuccessCallback successCallback, final DefaultFailureCallback defaultFailureCallback) {
        ApiRequest apiRequest = new ApiRequest("cart-abandonment/claim");
        apiRequest.addParameter("offer_id", (Object) str);
        startService(apiRequest, new ApiCallback() {
            public String getCallIdentifier() {
                return null;
            }

            public void handleFailure(ApiResponse apiResponse, final String str) {
                if (defaultFailureCallback != null) {
                    CartAbandonOfferClaimService.this.postRunnable(new Runnable() {
                        public void run() {
                            defaultFailureCallback.onFailure(str);
                        }
                    });
                }
            }

            public void handleSuccess(ApiResponse apiResponse) throws JSONException, ParseException {
                final WishCart wishCart = new WishCart(apiResponse.getData().getJSONObject("cart_info"));
                if (JsonUtil.hasValue(apiResponse.getData(), "checkout_offer")) {
                    try {
                        wishCart.setCheckoutOffer(new WishCheckoutOffer(apiResponse.getData().getJSONObject("checkout_offer")));
                    } catch (Throwable unused) {
                    }
                }
                if (successCallback != null) {
                    CartAbandonOfferClaimService.this.postRunnable(new Runnable() {
                        public void run() {
                            successCallback.onSuccess(wishCart);
                        }
                    });
                }
            }
        });
    }
}
