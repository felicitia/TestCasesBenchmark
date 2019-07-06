package com.contextlogic.wish.api.service.standalone;

import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.model.WishUser;
import com.contextlogic.wish.api.model.WishWishlist;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.SingleApiService;
import com.contextlogic.wish.util.JsonUtil;
import com.contextlogic.wish.util.JsonUtil.DataParser;
import java.text.ParseException;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

public class GetRecommendedWishlistsService extends SingleApiService {

    public interface SuccessCallback {
        void onSuccess(ArrayList<WishWishlist> arrayList, ArrayList<WishUser> arrayList2, String str, String str2);
    }

    public void requestService(String str, final SuccessCallback successCallback, final DefaultFailureCallback defaultFailureCallback) {
        ApiRequest apiRequest = new ApiRequest("user/wishlist/get-recommended-wishlists");
        apiRequest.addParameter("product_id", (Object) str);
        startService(apiRequest, new ApiCallback() {
            public String getCallIdentifier() {
                return null;
            }

            public void handleFailure(ApiResponse apiResponse, final String str) {
                if (defaultFailureCallback != null) {
                    GetRecommendedWishlistsService.this.postRunnable(new Runnable() {
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
                final ArrayList parseArray2 = JsonUtil.parseArray(apiResponse.getData(), "users", new DataParser<WishUser, JSONObject>() {
                    public WishUser parseData(JSONObject jSONObject) throws JSONException, ParseException {
                        return new WishUser(jSONObject);
                    }
                });
                final String string = apiResponse.getData().getString("wishlist_title");
                final String string2 = apiResponse.getData().getString("related_product_title");
                if (successCallback != null) {
                    GetRecommendedWishlistsService getRecommendedWishlistsService = GetRecommendedWishlistsService.this;
                    AnonymousClass4 r3 = new Runnable() {
                        public void run() {
                            successCallback.onSuccess(parseArray, parseArray2, string, string2);
                        }
                    };
                    getRecommendedWishlistsService.postRunnable(r3);
                }
            }
        });
    }
}
