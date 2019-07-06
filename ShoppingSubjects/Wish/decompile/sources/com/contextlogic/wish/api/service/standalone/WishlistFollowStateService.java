package com.contextlogic.wish.api.service.standalone;

import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.model.WishWishlist;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.SingleApiService;
import java.text.ParseException;
import org.json.JSONException;
import org.json.JSONObject;

public class WishlistFollowStateService extends SingleApiService {

    public interface SuccessCallback {
        void onSuccess(WishWishlist wishWishlist, boolean z, boolean z2);
    }

    public void requestService(String str, final SuccessCallback successCallback, final DefaultFailureCallback defaultFailureCallback) {
        ApiRequest apiRequest = new ApiRequest("user/wishlist/get-wishlist-follow-state");
        apiRequest.addParameter("wishlist_id", (Object) str);
        startService(apiRequest, new ApiCallback() {
            public String getCallIdentifier() {
                return null;
            }

            public void handleFailure(ApiResponse apiResponse, final String str) {
                if (defaultFailureCallback != null) {
                    WishlistFollowStateService.this.postRunnable(new Runnable() {
                        public void run() {
                            defaultFailureCallback.onFailure(str);
                        }
                    });
                }
            }

            public void handleSuccess(ApiResponse apiResponse) throws JSONException {
                if (successCallback != null) {
                    JSONObject data = apiResponse.getData();
                    final boolean z = data.getBoolean("is_following");
                    final boolean z2 = data.getBoolean("can_follow");
                    final WishWishlist wishWishlist = null;
                    try {
                        wishWishlist = new WishWishlist(data.getJSONObject("wishlist"));
                    } catch (ParseException | JSONException unused) {
                    }
                    WishlistFollowStateService.this.postRunnable(new Runnable() {
                        public void run() {
                            successCallback.onSuccess(wishWishlist, z, z2);
                        }
                    });
                }
            }
        });
    }
}
