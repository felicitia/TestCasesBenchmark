package com.contextlogic.wish.api.service.standalone;

import com.contextlogic.wish.activity.orderconfirmed.OrderConfirmedFragment.ProductInfo;
import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.model.WishImage;
import com.contextlogic.wish.api.model.WishProduct;
import com.contextlogic.wish.api.model.WishShippingInfo;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.SingleApiService;
import com.contextlogic.wish.util.JsonUtil;
import com.contextlogic.wish.util.JsonUtil.DataParser;
import java.text.ParseException;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

public class GetOrderConfirmedProductService extends SingleApiService {

    public interface SuccessCallback {
        void onSuccess(ArrayList<ProductInfo> arrayList, WishShippingInfo wishShippingInfo, String str, String str2, String str3, String str4);
    }

    public void requestService(String str, final SuccessCallback successCallback, final DefaultFailureCallback defaultFailureCallback) {
        ApiRequest apiRequest = new ApiRequest("upsell-after-order/products");
        apiRequest.addParameter("transaction_id", (Object) str);
        startService(apiRequest, new ApiCallback() {
            public String getCallIdentifier() {
                return null;
            }

            public void handleFailure(ApiResponse apiResponse, final String str) {
                if (defaultFailureCallback != null) {
                    GetOrderConfirmedProductService.this.postRunnable(new Runnable() {
                        public void run() {
                            defaultFailureCallback.onFailure(str);
                        }
                    });
                }
            }

            public void handleSuccess(ApiResponse apiResponse) throws JSONException, ParseException {
                JSONObject data = apiResponse.getData();
                final WishShippingInfo wishShippingInfo = data.has("shipping_details") ? new WishShippingInfo(data.getJSONObject("shipping_details")) : null;
                final String optString = JsonUtil.optString(apiResponse.getData(), "boleto_due_date");
                final String optString2 = JsonUtil.optString(apiResponse.getData(), "commerce_loan_due_date");
                final String optString3 = JsonUtil.optString(apiResponse.getData(), "commerce_loan_processed_date");
                final String optString4 = JsonUtil.optString(apiResponse.getData(), "commerce_loan_original_transaction");
                if (successCallback != null) {
                    GetOrderConfirmedProductService getOrderConfirmedProductService = GetOrderConfirmedProductService.this;
                    final ApiResponse apiResponse2 = apiResponse;
                    AnonymousClass2 r2 = new Runnable() {
                        public void run() {
                            successCallback.onSuccess(JsonUtil.parseArray(apiResponse2.getData(), "products", new DataParser<ProductInfo, JSONObject>() {
                                public ProductInfo parseData(JSONObject jSONObject) throws JSONException, ParseException {
                                    WishImage wishImage;
                                    String string = jSONObject.getString("title_text");
                                    String string2 = jSONObject.getString("name");
                                    String string3 = jSONObject.getString("product_id");
                                    if (!JsonUtil.hasValue(jSONObject, "picture") || JsonUtil.hasValue(jSONObject, "display_picture")) {
                                        wishImage = new WishImage(jSONObject.getString("display_picture"), jSONObject.getString("small_picture"), jSONObject.getString("display_picture"), jSONObject.getString("contest_page_picture"), null);
                                    } else {
                                        wishImage = new WishImage(jSONObject.getString("picture"));
                                    }
                                    ProductInfo productInfo = new ProductInfo(string, string2, wishImage, string3, JsonUtil.parseArray(jSONObject, "related_products", new DataParser<WishProduct, JSONObject>() {
                                        public WishProduct parseData(JSONObject jSONObject) throws JSONException, ParseException {
                                            return new WishProduct(jSONObject);
                                        }
                                    }));
                                    return productInfo;
                                }
                            }), wishShippingInfo, optString, optString2, optString3, optString4);
                        }
                    };
                    getOrderConfirmedProductService.postRunnable(r2);
                }
            }
        });
    }
}
