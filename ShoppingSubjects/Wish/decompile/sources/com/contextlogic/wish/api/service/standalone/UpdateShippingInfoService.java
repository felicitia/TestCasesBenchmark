package com.contextlogic.wish.api.service.standalone;

import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.datacenter.StatusDataCenter;
import com.contextlogic.wish.api.model.GeocodingRequest;
import com.contextlogic.wish.api.model.WishCart;
import com.contextlogic.wish.api.model.WishCheckoutOffer;
import com.contextlogic.wish.api.model.WishShippingInfo;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.SingleApiService;
import com.contextlogic.wish.util.JsonUtil;
import java.text.ParseException;
import org.json.JSONException;

public class UpdateShippingInfoService extends SingleApiService {

    public interface SuccessCallback {
        void onSuccess(WishShippingInfo wishShippingInfo, WishCart wishCart);
    }

    public void requestService(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, SuccessCallback successCallback, DefaultFailureCallback defaultFailureCallback) {
        requestService(str, str2, str3, str4, str5, str6, str7, str8, false, null, successCallback, defaultFailureCallback);
    }

    public void requestService(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, boolean z, String str9, final SuccessCallback successCallback, final DefaultFailureCallback defaultFailureCallback) {
        ApiRequest apiRequest = new ApiRequest("shipping-address/add-or-update");
        apiRequest.addParameter("force_add", z);
        if (str9 != null) {
            apiRequest.addParameter("id", (Object) str9);
        }
        if (str != null) {
            apiRequest.addParameter("full-name", (Object) str);
        }
        if (str2 != null) {
            apiRequest.addParameter("street-address1", (Object) str2);
        }
        if (str3 != null) {
            apiRequest.addParameter("street-address2", (Object) str3);
        }
        if (str4 != null) {
            apiRequest.addParameter("city", (Object) str4);
        }
        if (str5 != null) {
            apiRequest.addParameter("state", (Object) str5);
        }
        if (str6 != null) {
            apiRequest.addParameter("zipcode", (Object) str6);
        }
        if (str7 != null) {
            apiRequest.addParameter("country", (Object) str7);
        }
        if (str8 != null) {
            apiRequest.addParameter("phone-number", (Object) str8);
        }
        startService(apiRequest, new ApiCallback() {
            public String getCallIdentifier() {
                return null;
            }

            public void handleFailure(ApiResponse apiResponse, final String str) {
                if (defaultFailureCallback != null) {
                    UpdateShippingInfoService.this.postRunnable(new Runnable() {
                        public void run() {
                            defaultFailureCallback.onFailure(str);
                        }
                    });
                }
            }

            public void handleSuccess(ApiResponse apiResponse) throws JSONException, ParseException {
                final GeocodingRequest geocodingRequest = null;
                final WishShippingInfo wishShippingInfo = JsonUtil.hasValue(apiResponse.getData(), "shipping_info") ? new WishShippingInfo(apiResponse.getData().getJSONObject("shipping_info")) : null;
                final WishCart wishCart = new WishCart(apiResponse.getData().getJSONObject("cart_info"));
                if (JsonUtil.hasValue(apiResponse.getData(), "checkout_offer")) {
                    try {
                        wishCart.setCheckoutOffer(new WishCheckoutOffer(apiResponse.getData().getJSONObject("checkout_offer")));
                    } catch (Throwable unused) {
                    }
                }
                if (JsonUtil.hasValue(apiResponse.getData(), "geocoding_request")) {
                    geocodingRequest = new GeocodingRequest(apiResponse.getData().getJSONObject("geocoding_request"));
                }
                if (successCallback != null) {
                    UpdateShippingInfoService.this.postRunnable(new Runnable() {
                        public void run() {
                            StatusDataCenter.getInstance().saveGeocodingResults(geocodingRequest, false);
                            successCallback.onSuccess(wishShippingInfo, wishCart);
                        }
                    });
                }
            }
        });
    }
}
