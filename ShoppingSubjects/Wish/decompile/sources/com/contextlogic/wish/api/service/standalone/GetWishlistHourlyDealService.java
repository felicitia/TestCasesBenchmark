package com.contextlogic.wish.api.service.standalone;

import com.contextlogic.wish.activity.orderconfirmed.OrderConfirmedFragment.WishlistInfo;
import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.model.WishProduct;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.SingleApiService;
import com.contextlogic.wish.util.JsonUtil;
import com.contextlogic.wish.util.JsonUtil.DataParser;
import java.text.ParseException;
import org.json.JSONException;
import org.json.JSONObject;

public class GetWishlistHourlyDealService extends SingleApiService {

    public interface SuccessCallback {
        void onSuccess(WishlistInfo wishlistInfo);
    }

    public void requestService(final SuccessCallback successCallback, final DefaultFailureCallback defaultFailureCallback) {
        startService(new ApiRequest("upsell-after-order/wishlist"), new ApiCallback() {
            public String getCallIdentifier() {
                return null;
            }

            public void handleFailure(ApiResponse apiResponse, final String str) {
                if (defaultFailureCallback != null) {
                    GetWishlistHourlyDealService.this.postRunnable(new Runnable() {
                        public void run() {
                            defaultFailureCallback.onFailure(str);
                        }
                    });
                }
            }

            public void handleSuccess(ApiResponse apiResponse) throws JSONException, ParseException {
                final WishlistInfo wishlistInfo = new WishlistInfo(apiResponse.getData().getString("title_text"), JsonUtil.parseArray(apiResponse.getData(), "products", new DataParser<WishProduct, JSONObject>() {
                    public WishProduct parseData(JSONObject jSONObject) throws JSONException, ParseException {
                        return new WishProduct(jSONObject);
                    }
                }));
                if (successCallback != null) {
                    GetWishlistHourlyDealService.this.postRunnable(new Runnable() {
                        public void run() {
                            successCallback.onSuccess(wishlistInfo);
                        }
                    });
                }
            }
        });
    }
}
