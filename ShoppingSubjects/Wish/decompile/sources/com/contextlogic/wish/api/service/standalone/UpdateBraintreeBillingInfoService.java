package com.contextlogic.wish.api.service.standalone;

import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.model.WishShippingInfo;
import com.contextlogic.wish.api.model.WishUserBillingInfo;
import com.contextlogic.wish.api.service.SingleApiService;
import com.contextlogic.wish.util.JsonUtil;
import java.text.ParseException;
import org.json.JSONException;

public class UpdateBraintreeBillingInfoService extends SingleApiService {

    public interface FailureCallback {
        void onFailure(String str);
    }

    public interface SuccessCallback {
        void onSuccess(WishUserBillingInfo wishUserBillingInfo);
    }

    public void requestService(String str, String str2, String str3, WishShippingInfo wishShippingInfo, boolean z, final SuccessCallback successCallback, final FailureCallback failureCallback) {
        ApiRequest apiRequest = new ApiRequest("billing-info/braintree/add-or-update");
        apiRequest.addParameter("card_id", (Object) str);
        apiRequest.addParameter("card-nonce", (Object) str2);
        if (str3 != null) {
            apiRequest.addParameter("device_data", (Object) str3);
        }
        if (wishShippingInfo.getName() != null) {
            apiRequest.addParameter("full_name", (Object) wishShippingInfo.getName());
            apiRequest.addParameter("cardholder_name", (Object) wishShippingInfo.getName());
        }
        if (wishShippingInfo.getStreetAddressLineOne() != null) {
            apiRequest.addParameter("street_address1", (Object) wishShippingInfo.getStreetAddressLineOne());
        }
        if (wishShippingInfo.getStreetAddressLineTwo() != null) {
            apiRequest.addParameter("street_address2", (Object) wishShippingInfo.getStreetAddressLineTwo());
        }
        if (wishShippingInfo.getCity() != null) {
            apiRequest.addParameter("city", (Object) wishShippingInfo.getCity());
        }
        if (wishShippingInfo.getState() != null) {
            apiRequest.addParameter("state", (Object) wishShippingInfo.getState());
        }
        if (wishShippingInfo.getZipCode() != null) {
            apiRequest.addParameter("zipcode", (Object) wishShippingInfo.getZipCode());
        }
        if (wishShippingInfo.getCountryCode() != null) {
            apiRequest.addParameter("country", (Object) wishShippingInfo.getCountryCode());
        }
        if (wishShippingInfo.getPhoneNumber() != null) {
            apiRequest.addParameter("phone_number", (Object) wishShippingInfo.getPhoneNumber());
        }
        apiRequest.addParameter("is_for_commerce_loan", z);
        startService(apiRequest, new ApiCallback() {
            public String getCallIdentifier() {
                return null;
            }

            public void handleFailure(ApiResponse apiResponse, final String str) {
                if (failureCallback != null) {
                    UpdateBraintreeBillingInfoService.this.postRunnable(new Runnable() {
                        public void run() {
                            failureCallback.onFailure(str);
                        }
                    });
                }
            }

            public void handleSuccess(ApiResponse apiResponse) throws JSONException, ParseException {
                final WishUserBillingInfo wishUserBillingInfo = JsonUtil.hasValue(apiResponse.getData(), "user_billing_details") ? new WishUserBillingInfo(apiResponse.getData().getJSONObject("user_billing_details")) : null;
                if (successCallback != null) {
                    UpdateBraintreeBillingInfoService.this.postRunnable(new Runnable() {
                        public void run() {
                            successCallback.onSuccess(wishUserBillingInfo);
                        }
                    });
                }
            }
        });
    }
}
