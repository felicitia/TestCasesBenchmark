package com.contextlogic.wish.api.service.standalone;

import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.datacenter.StatusDataCenter;
import com.contextlogic.wish.api.model.WishCart;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.SingleApiService;
import java.text.ParseException;
import org.json.JSONException;

public class ClaimDailyGiveawayService extends SingleApiService {

    public interface SuccessCallback {
        void onSuccess(WishCart wishCart, String str, String str2);
    }

    public void requestService(String str, String str2, final SuccessCallback successCallback, final DefaultFailureCallback defaultFailureCallback) {
        ApiRequest apiRequest = new ApiRequest("daily-giveaway/claim");
        apiRequest.addParameter("product_id", (Object) str);
        apiRequest.addParameter("variation_id", (Object) str2);
        startService(apiRequest, new ApiCallback() {
            public String getCallIdentifier() {
                return null;
            }

            public void handleFailure(ApiResponse apiResponse, final String str) {
                if (defaultFailureCallback != null) {
                    ClaimDailyGiveawayService.this.postRunnable(new Runnable() {
                        public void run() {
                            defaultFailureCallback.onFailure(str);
                        }
                    });
                }
            }

            public void handleSuccess(ApiResponse apiResponse) throws JSONException, ParseException {
                if (successCallback != null) {
                    final String string = apiResponse.getData().getString("popup_title");
                    final String string2 = apiResponse.getData().getString("popup_msg");
                    if (apiResponse.getData().has("cart")) {
                        final WishCart wishCart = new WishCart(apiResponse.getData().getJSONObject("cart").getJSONObject("cart_info"));
                        StatusDataCenter.getInstance().updateCartCount(wishCart.getTotalItemCount());
                        ClaimDailyGiveawayService.this.postRunnable(new Runnable() {
                            public void run() {
                                successCallback.onSuccess(wishCart, string, string2);
                            }
                        });
                        return;
                    }
                    ClaimDailyGiveawayService.this.postRunnable(new Runnable() {
                        public void run() {
                            successCallback.onSuccess(null, string, string2);
                        }
                    });
                }
            }
        });
    }
}
