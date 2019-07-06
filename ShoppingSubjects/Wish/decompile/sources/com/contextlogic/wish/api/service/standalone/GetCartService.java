package com.contextlogic.wish.api.service.standalone;

import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.datacenter.StatusDataCenter;
import com.contextlogic.wish.api.model.WishCart;
import com.contextlogic.wish.api.model.WishCheckoutOffer;
import com.contextlogic.wish.api.model.WishCommerceLoanBannerSpec;
import com.contextlogic.wish.api.model.WishCommerceLoanTabSpec;
import com.contextlogic.wish.api.model.WishLoanRepaymentBannerSpec;
import com.contextlogic.wish.api.model.WishPaymentStructureSelectionSpec;
import com.contextlogic.wish.api.model.WishShippingInfo;
import com.contextlogic.wish.api.model.WishUserBillingInfo;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.SingleApiService;
import com.contextlogic.wish.util.JsonUtil;
import java.text.ParseException;
import org.json.JSONException;
import org.json.JSONObject;

public class GetCartService extends SingleApiService {

    public interface SuccessCallback {
        void onSuccess(WishCart wishCart, WishShippingInfo wishShippingInfo, WishUserBillingInfo wishUserBillingInfo, WishCommerceLoanTabSpec wishCommerceLoanTabSpec, WishCommerceLoanBannerSpec wishCommerceLoanBannerSpec, WishPaymentStructureSelectionSpec wishPaymentStructureSelectionSpec, WishLoanRepaymentBannerSpec wishLoanRepaymentBannerSpec);
    }

    public void requestService(final SuccessCallback successCallback, final DefaultFailureCallback defaultFailureCallback) {
        startService(new ApiRequest("cart/get"), new ApiCallback() {
            public String getCallIdentifier() {
                return null;
            }

            public void handleFailure(ApiResponse apiResponse, final String str) {
                if (defaultFailureCallback != null) {
                    GetCartService.this.postRunnable(new Runnable() {
                        public void run() {
                            defaultFailureCallback.onFailure(str);
                        }
                    });
                }
            }

            public void handleSuccess(ApiResponse apiResponse) throws JSONException, ParseException {
                final WishUserBillingInfo wishUserBillingInfo;
                JSONObject jSONObject = apiResponse.getData().getJSONObject("cart_info");
                final WishCart wishCart = new WishCart(jSONObject);
                StatusDataCenter.getInstance().updateCartCount(wishCart.getTotalItemCount());
                final WishShippingInfo wishShippingInfo = JsonUtil.hasValue(apiResponse.getData(), "shipping_info") ? new WishShippingInfo(apiResponse.getData().getJSONObject("shipping_info")) : null;
                try {
                    wishUserBillingInfo = JsonUtil.hasValue(apiResponse.getData(), "user_billing_details") ? new WishUserBillingInfo(apiResponse.getData().getJSONObject("user_billing_details")) : null;
                } catch (Throwable unused) {
                    wishUserBillingInfo = null;
                }
                if (JsonUtil.hasValue(apiResponse.getData(), "checkout_offer")) {
                    try {
                        wishCart.setCheckoutOffer(new WishCheckoutOffer(apiResponse.getData().getJSONObject("checkout_offer")));
                    } catch (Throwable unused2) {
                    }
                }
                final WishCommerceLoanTabSpec wishCommerceLoanTabSpec = JsonUtil.hasValue(apiResponse.getData(), "commerce_loan_tab_spec") ? new WishCommerceLoanTabSpec(apiResponse.getData().getJSONObject("commerce_loan_tab_spec")) : null;
                final WishCommerceLoanBannerSpec wishCommerceLoanBannerSpec = JsonUtil.hasValue(apiResponse.getData(), "commerce_loan_banner_spec") ? new WishCommerceLoanBannerSpec(apiResponse.getData().getJSONObject("commerce_loan_banner_spec")) : null;
                final WishPaymentStructureSelectionSpec wishPaymentStructureSelectionSpec = JsonUtil.hasValue(apiResponse.getData(), "payment_structure_spec") ? new WishPaymentStructureSelectionSpec(apiResponse.getData().getJSONObject("payment_structure_spec")) : null;
                final WishLoanRepaymentBannerSpec wishLoanRepaymentBannerSpec = JsonUtil.hasValue(jSONObject, "repayment_banner_spec") ? new WishLoanRepaymentBannerSpec(jSONObject.getJSONObject("repayment_banner_spec")) : null;
                if (successCallback != null) {
                    GetCartService getCartService = GetCartService.this;
                    AnonymousClass2 r1 = new Runnable() {
                        public void run() {
                            successCallback.onSuccess(wishCart, wishShippingInfo, wishUserBillingInfo, wishCommerceLoanTabSpec, wishCommerceLoanBannerSpec, wishPaymentStructureSelectionSpec, wishLoanRepaymentBannerSpec);
                        }
                    };
                    getCartService.postRunnable(r1);
                }
            }
        });
    }
}
