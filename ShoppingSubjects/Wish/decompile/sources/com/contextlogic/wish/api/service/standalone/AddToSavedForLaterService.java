package com.contextlogic.wish.api.service.standalone;

import com.contextlogic.wish.analytics.GoogleAnalyticsLogger;
import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.model.WishCartItem;
import com.contextlogic.wish.api.model.WishSavedForLaterProduct;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.SingleApiService;
import com.contextlogic.wish.application.ApplicationEventManager;
import com.contextlogic.wish.application.ApplicationEventManager.EventType;
import com.contextlogic.wish.social.facebook.FacebookManager;
import com.contextlogic.wish.util.JsonUtil;
import com.contextlogic.wish.util.JsonUtil.DataParser;
import java.text.ParseException;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

public class AddToSavedForLaterService extends SingleApiService {

    public interface SuccessCallback {
        void onSuccess(ArrayList<WishSavedForLaterProduct> arrayList);
    }

    public void requestService(final WishCartItem wishCartItem, final SuccessCallback successCallback, final DefaultFailureCallback defaultFailureCallback) {
        String str;
        ApiRequest apiRequest = new ApiRequest("user/wishlist/add-to-saved-for-later");
        apiRequest.addParameter("product_id", (Object) wishCartItem.getProductId());
        apiRequest.addParameter("variation_id", (Object) wishCartItem.getVariationId());
        if (wishCartItem.getSelectedShippingOption() == null) {
            str = "standard";
        } else {
            str = wishCartItem.getSelectedShippingOption().getOptionId();
        }
        apiRequest.addParameter("shipping_id", (Object) str);
        startService(apiRequest, new ApiCallback() {
            public String getCallIdentifier() {
                return null;
            }

            public void handleSuccess(ApiResponse apiResponse) throws JSONException, ParseException {
                ApplicationEventManager.getInstance().triggerEvent(EventType.PRODUCT_WISH, wishCartItem.getProductId(), null);
                try {
                    FacebookManager.getInstance().getLogger().logEvent("fb_mobile_add_to_wishlist");
                } catch (Throwable unused) {
                }
                GoogleAnalyticsLogger.getInstance().logProductWish(wishCartItem.getProductId());
                final ArrayList parseArray = JsonUtil.parseArray(apiResponse.getData(), "items", new DataParser<WishSavedForLaterProduct, JSONObject>() {
                    public WishSavedForLaterProduct parseData(JSONObject jSONObject) throws JSONException, ParseException {
                        return new WishSavedForLaterProduct(jSONObject);
                    }
                });
                if (successCallback != null) {
                    AddToSavedForLaterService.this.postRunnable(new Runnable() {
                        public void run() {
                            successCallback.onSuccess(parseArray);
                        }
                    });
                }
            }

            public void handleFailure(ApiResponse apiResponse, final String str) {
                if (defaultFailureCallback != null) {
                    AddToSavedForLaterService.this.postRunnable(new Runnable() {
                        public void run() {
                            defaultFailureCallback.onFailure(str);
                        }
                    });
                }
            }
        });
    }
}
