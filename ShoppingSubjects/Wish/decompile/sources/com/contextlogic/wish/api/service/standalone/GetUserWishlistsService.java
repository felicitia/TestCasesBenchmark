package com.contextlogic.wish.api.service.standalone;

import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.model.WishWishlist;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.SingleApiService;
import com.contextlogic.wish.util.JsonUtil;
import com.contextlogic.wish.util.JsonUtil.DataParser;
import java.text.ParseException;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

public class GetUserWishlistsService extends SingleApiService {

    public interface SuccessCallback {
        void onSuccess(ArrayList<WishWishlist> arrayList, int i, boolean z);
    }

    public void requestService(String str, int i, int i2, int i3, SuccessCallback successCallback, DefaultFailureCallback defaultFailureCallback) {
        requestService(str, i, i2, i3, false, successCallback, defaultFailureCallback);
    }

    public void requestService(String str, int i, int i2, int i3, boolean z, final SuccessCallback successCallback, final DefaultFailureCallback defaultFailureCallback) {
        ApiRequest apiRequest = new ApiRequest("user/get-wishlists");
        apiRequest.addParameter("user_id", (Object) str);
        apiRequest.addParameter("preview_count", i3);
        apiRequest.addParameter("offset", i);
        apiRequest.addParameter("count", i2);
        if (z) {
            apiRequest.addParameter("sort_last_updated", (Object) "true");
        }
        startService(apiRequest, new ApiCallback() {
            public String getCallIdentifier() {
                return null;
            }

            public void handleFailure(ApiResponse apiResponse, final String str) {
                if (defaultFailureCallback != null) {
                    GetUserWishlistsService.this.postRunnable(new Runnable() {
                        public void run() {
                            defaultFailureCallback.onFailure(str);
                        }
                    });
                }
            }

            public void handleSuccess(ApiResponse apiResponse) throws JSONException {
                final ArrayList parseArray = JsonUtil.parseArray(apiResponse.getData(), "wishlists", new DataParser<WishWishlist, JSONObject>() {
                    public WishWishlist parseData(JSONObject jSONObject) throws JSONException, ParseException {
                        return new WishWishlist(jSONObject);
                    }
                });
                final int i = apiResponse.getData().getInt("next_offset");
                final boolean z = apiResponse.getData().getBoolean("no_more_items");
                if (successCallback != null) {
                    GetUserWishlistsService.this.postRunnable(new Runnable() {
                        public void run() {
                            successCallback.onSuccess(parseArray, i, z);
                        }
                    });
                }
            }
        });
    }
}
