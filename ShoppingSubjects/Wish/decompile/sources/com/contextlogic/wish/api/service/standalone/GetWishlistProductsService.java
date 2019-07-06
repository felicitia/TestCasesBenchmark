package com.contextlogic.wish.api.service.standalone;

import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.model.WishProduct;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.SingleApiService;
import com.contextlogic.wish.util.JsonUtil;
import com.contextlogic.wish.util.JsonUtil.DataParser;
import java.text.ParseException;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

public class GetWishlistProductsService extends SingleApiService {

    public interface SuccessCallback {
        void onSuccess(ArrayList<WishProduct> arrayList, boolean z, int i);
    }

    public void requestService(String str, int i, int i2, final SuccessCallback successCallback, final DefaultFailureCallback defaultFailureCallback) {
        ApiRequest apiRequest = new ApiRequest("user/wishlist/get-products");
        apiRequest.addParameter("wishlist_id", (Object) str);
        apiRequest.addParameter("offset", (Object) Integer.toString(i));
        apiRequest.addParameter("count", (Object) Integer.toString(i2));
        startService(apiRequest, new ApiCallback() {
            public String getCallIdentifier() {
                return null;
            }

            public void handleFailure(ApiResponse apiResponse, final String str) {
                if (defaultFailureCallback != null) {
                    GetWishlistProductsService.this.postRunnable(new Runnable() {
                        public void run() {
                            defaultFailureCallback.onFailure(str);
                        }
                    });
                }
            }

            public void handleSuccess(ApiResponse apiResponse) throws JSONException {
                final ArrayList parseArray = JsonUtil.parseArray(apiResponse.getData(), "items", new DataParser<WishProduct, JSONObject>() {
                    public WishProduct parseData(JSONObject jSONObject) throws JSONException, ParseException {
                        return new WishProduct(jSONObject);
                    }
                });
                final boolean z = apiResponse.getData().getBoolean("no_more");
                final int i = apiResponse.getData().getInt("offset");
                if (successCallback != null) {
                    GetWishlistProductsService.this.postRunnable(new Runnable() {
                        public void run() {
                            successCallback.onSuccess(parseArray, z, i);
                        }
                    });
                }
            }
        });
    }
}
