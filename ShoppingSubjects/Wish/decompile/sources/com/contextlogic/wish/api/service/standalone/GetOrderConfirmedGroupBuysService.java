package com.contextlogic.wish.api.service.standalone;

import com.contextlogic.wish.activity.orderconfirmed.OrderConfirmedFragment.GroupBuyInfo;
import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.model.WishImage;
import com.contextlogic.wish.api.model.WishLocalizedCurrencyValue;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.SingleApiService;
import com.contextlogic.wish.util.DateUtil;
import com.contextlogic.wish.util.JsonUtil;
import com.contextlogic.wish.util.JsonUtil.DataParser;
import com.threatmetrix.TrustDefender.StrongAuth;
import java.text.ParseException;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

public class GetOrderConfirmedGroupBuysService extends SingleApiService {

    public interface SuccessCallback {
        void onSuccess(ArrayList<GroupBuyInfo> arrayList);
    }

    public void requestService(String str, final SuccessCallback successCallback, final DefaultFailureCallback defaultFailureCallback) {
        ApiRequest apiRequest = new ApiRequest("group-buy/get-order-confirmed");
        apiRequest.addParameter("transaction_id", (Object) str);
        startService(apiRequest, new ApiCallback() {
            public String getCallIdentifier() {
                return null;
            }

            public void handleFailure(ApiResponse apiResponse, final String str) {
                if (defaultFailureCallback != null) {
                    GetOrderConfirmedGroupBuysService.this.postRunnable(new Runnable() {
                        public void run() {
                            defaultFailureCallback.onFailure(str);
                        }
                    });
                }
            }

            public void handleSuccess(final ApiResponse apiResponse) throws JSONException, ParseException {
                if (successCallback != null) {
                    GetOrderConfirmedGroupBuysService.this.postRunnable(new Runnable() {
                        public void run() {
                            successCallback.onSuccess(JsonUtil.parseArray(apiResponse.getData(), "group_buys", new DataParser<GroupBuyInfo, JSONObject>() {
                                public GroupBuyInfo parseData(JSONObject jSONObject) throws JSONException, ParseException {
                                    GroupBuyInfo groupBuyInfo = new GroupBuyInfo(new WishImage(jSONObject.getString("product_image")), new WishImage(jSONObject.getString("user_image")), DateUtil.parseIsoDate(jSONObject.getString("expiry")), jSONObject.getString("user_name"), jSONObject.getString("message"), jSONObject.getString(StrongAuth.AUTH_TITLE), new WishLocalizedCurrencyValue(jSONObject.getDouble("price"), jSONObject.optJSONObject("localized_price")));
                                    return groupBuyInfo;
                                }
                            }));
                        }
                    });
                }
            }
        });
    }
}
