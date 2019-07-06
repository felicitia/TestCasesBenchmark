package com.contextlogic.wish.api.service.standalone;

import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.model.WishGroupBuyInfo;
import com.contextlogic.wish.api.model.WishGroupBuyRowInfo;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.SingleApiService;
import com.contextlogic.wish.util.JsonUtil;
import com.contextlogic.wish.util.JsonUtil.DataParser;
import java.text.ParseException;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

public class GetGroupBuysService extends SingleApiService {

    public interface SuccessCallback {
        void onSuccess(ArrayList<WishGroupBuyRowInfo> arrayList, WishGroupBuyInfo wishGroupBuyInfo);
    }

    public void requestService(String str, final SuccessCallback successCallback, final DefaultFailureCallback defaultFailureCallback) {
        ApiRequest apiRequest = new ApiRequest("group-buy/get");
        apiRequest.addParameter("product_id", (Object) str);
        startService(apiRequest, new ApiCallback() {
            public String getCallIdentifier() {
                return null;
            }

            public void handleFailure(ApiResponse apiResponse, final String str) {
                if (defaultFailureCallback != null) {
                    GetGroupBuysService.this.postRunnable(new Runnable() {
                        public void run() {
                            defaultFailureCallback.onFailure(str);
                        }
                    });
                }
            }

            public void handleSuccess(ApiResponse apiResponse) throws JSONException, ParseException {
                final ArrayList parseArray = JsonUtil.parseArray(apiResponse.getData(), "group_buys", new DataParser<WishGroupBuyRowInfo, JSONObject>() {
                    public WishGroupBuyRowInfo parseData(JSONObject jSONObject) throws JSONException, ParseException {
                        return new WishGroupBuyRowInfo(jSONObject);
                    }
                });
                final WishGroupBuyInfo wishGroupBuyInfo = JsonUtil.hasValue(apiResponse.getData(), "group_buy_info") ? new WishGroupBuyInfo(apiResponse.getData().getJSONObject("group_buy_info")) : null;
                if (successCallback != null) {
                    GetGroupBuysService.this.postRunnable(new Runnable() {
                        public void run() {
                            successCallback.onSuccess(parseArray, wishGroupBuyInfo);
                        }
                    });
                }
            }
        });
    }
}
