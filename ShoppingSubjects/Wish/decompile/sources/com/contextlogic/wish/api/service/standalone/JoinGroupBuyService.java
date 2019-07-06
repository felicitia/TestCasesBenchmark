package com.contextlogic.wish.api.service.standalone;

import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.model.WishGroupBuyInfo;
import com.contextlogic.wish.api.model.WishGroupBuyRowInfo;
import com.contextlogic.wish.api.service.SingleApiService;
import com.contextlogic.wish.util.JsonUtil;
import com.contextlogic.wish.util.JsonUtil.DataParser;
import java.text.ParseException;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

public class JoinGroupBuyService extends SingleApiService {

    public interface FailureCallback {
        void onFailure(String str);
    }

    public interface SuccessCallback {
        void onSuccess(Boolean bool, String str, ArrayList<WishGroupBuyRowInfo> arrayList, WishGroupBuyInfo wishGroupBuyInfo);
    }

    public void requestService(String str, String str2, final SuccessCallback successCallback, final FailureCallback failureCallback) {
        ApiRequest apiRequest = new ApiRequest("group-buy/join");
        apiRequest.addParameter("group_id", (Object) str);
        apiRequest.addParameter("product_id", (Object) str2);
        startService(apiRequest, new ApiCallback() {
            public String getCallIdentifier() {
                return null;
            }

            public void handleFailure(ApiResponse apiResponse, final String str) {
                if (failureCallback != null) {
                    JoinGroupBuyService.this.postRunnable(new Runnable() {
                        public void run() {
                            failureCallback.onFailure(str);
                        }
                    });
                }
            }

            public void handleSuccess(ApiResponse apiResponse) throws JSONException, ParseException {
                if (successCallback != null) {
                    ArrayList arrayList = new ArrayList();
                    if (JsonUtil.hasValue(apiResponse.getData(), "group_buys")) {
                        arrayList = JsonUtil.parseArray(apiResponse.getData(), "group_buys", new DataParser<WishGroupBuyRowInfo, JSONObject>() {
                            public WishGroupBuyRowInfo parseData(JSONObject jSONObject) throws JSONException, ParseException {
                                return new WishGroupBuyRowInfo(jSONObject);
                            }
                        });
                    }
                    final ArrayList arrayList2 = arrayList;
                    WishGroupBuyInfo wishGroupBuyInfo = null;
                    if (JsonUtil.hasValue(apiResponse.getData(), "group_buy_info")) {
                        wishGroupBuyInfo = new WishGroupBuyInfo(apiResponse.getData().getJSONObject("group_buy_info"));
                    }
                    final WishGroupBuyInfo wishGroupBuyInfo2 = wishGroupBuyInfo;
                    final boolean z = apiResponse.getData().getBoolean("success");
                    final String string = apiResponse.getData().getString("error_message");
                    JoinGroupBuyService joinGroupBuyService = JoinGroupBuyService.this;
                    AnonymousClass3 r1 = new Runnable() {
                        public void run() {
                            successCallback.onSuccess(Boolean.valueOf(z), string, arrayList2, wishGroupBuyInfo2);
                        }
                    };
                    joinGroupBuyService.postRunnable(r1);
                }
            }
        });
    }
}
