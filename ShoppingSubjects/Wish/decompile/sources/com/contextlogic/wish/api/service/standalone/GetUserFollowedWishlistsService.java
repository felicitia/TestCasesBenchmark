package com.contextlogic.wish.api.service.standalone;

import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.model.WishFollowedWishlist;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.SingleApiService;
import com.contextlogic.wish.util.JsonUtil;
import com.contextlogic.wish.util.JsonUtil.DataParser;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class GetUserFollowedWishlistsService extends SingleApiService {

    public interface SuccessCallback {
        void onSuccess(List<WishFollowedWishlist> list, int i, boolean z);
    }

    public void requestService(String str, int i, int i2, int i3, final SuccessCallback successCallback, final DefaultFailureCallback defaultFailureCallback) {
        ApiRequest apiRequest = new ApiRequest("user/wishlist/get-followed-wishlists");
        apiRequest.addParameter("user_id", (Object) str);
        apiRequest.addParameter("preview_count", i3);
        apiRequest.addParameter("offset", i);
        apiRequest.addParameter("count", i2);
        startService(apiRequest, new ApiCallback() {
            public String getCallIdentifier() {
                return null;
            }

            public void handleFailure(ApiResponse apiResponse, final String str) {
                if (defaultFailureCallback != null) {
                    GetUserFollowedWishlistsService.this.postRunnable(new Runnable() {
                        public void run() {
                            defaultFailureCallback.onFailure(str);
                        }
                    });
                }
            }

            public void handleSuccess(ApiResponse apiResponse) throws JSONException {
                final ArrayList parseArray = JsonUtil.parseArray(apiResponse.getData(), "wishlists", new DataParser<WishFollowedWishlist, JSONObject>() {
                    public WishFollowedWishlist parseData(JSONObject jSONObject) throws JSONException, ParseException {
                        return new WishFollowedWishlist(jSONObject);
                    }
                });
                final int i = apiResponse.getData().getInt("next_offset");
                final boolean z = apiResponse.getData().getBoolean("no_more_items");
                if (successCallback != null) {
                    GetUserFollowedWishlistsService.this.postRunnable(new Runnable() {
                        public void run() {
                            successCallback.onSuccess(parseArray, i, z);
                        }
                    });
                }
            }
        });
    }
}
