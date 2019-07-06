package com.contextlogic.wish.api.service.standalone;

import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.model.WishRedeemableRewardItem;
import com.contextlogic.wish.api.model.WishRewardsDashboardInfo;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.SingleApiService;
import com.contextlogic.wish.util.JsonUtil;
import com.contextlogic.wish.util.JsonUtil.DataParser;
import java.text.ParseException;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

public class GetRedeemableRewardsDashboardInfo extends SingleApiService {

    public interface SuccessCallback {
        void onSuccess(WishRewardsDashboardInfo wishRewardsDashboardInfo, ArrayList<WishRedeemableRewardItem> arrayList, boolean z, int i, String str);
    }

    public void requestService(int i, int i2, int i3, boolean z, final SuccessCallback successCallback, final DefaultFailureCallback defaultFailureCallback) {
        ApiRequest apiRequest = new ApiRequest("redeemable-rewards/get-rewards");
        apiRequest.addParameter("get_dashboard_info", (Object) Boolean.toString(z));
        apiRequest.addParameter("offset", (Object) Integer.toString(i));
        apiRequest.addParameter("count", (Object) Integer.toString(i2));
        apiRequest.addParameter("reward_type", (Object) Integer.toString(i3));
        startService(apiRequest, new ApiCallback() {
            public String getCallIdentifier() {
                return null;
            }

            public void handleFailure(ApiResponse apiResponse, final String str) {
                if (defaultFailureCallback != null) {
                    GetRedeemableRewardsDashboardInfo.this.postRunnable(new Runnable() {
                        public void run() {
                            defaultFailureCallback.onFailure(str);
                        }
                    });
                }
            }

            public void handleSuccess(ApiResponse apiResponse) throws JSONException, ParseException {
                final WishRewardsDashboardInfo wishRewardsDashboardInfo = apiResponse.getData().has("dashboard_info") ? new WishRewardsDashboardInfo(apiResponse.getData().getJSONObject("dashboard_info")) : null;
                final ArrayList parseArray = JsonUtil.parseArray(apiResponse.getData(), "redeemable_rewards", new DataParser<WishRedeemableRewardItem, JSONObject>() {
                    public WishRedeemableRewardItem parseData(JSONObject jSONObject) throws JSONException, ParseException {
                        return new WishRedeemableRewardItem(jSONObject);
                    }
                });
                final boolean z = apiResponse.getData().getBoolean("rewards_end");
                final int i = apiResponse.getData().getInt("next_offset");
                final String optString = apiResponse.getData().optString("first_dashboard_visit");
                if (successCallback != null) {
                    GetRedeemableRewardsDashboardInfo getRedeemableRewardsDashboardInfo = GetRedeemableRewardsDashboardInfo.this;
                    AnonymousClass3 r1 = new Runnable() {
                        public void run() {
                            successCallback.onSuccess(wishRewardsDashboardInfo, parseArray, z, i, optString);
                        }
                    };
                    getRedeemableRewardsDashboardInfo.postRunnable(r1);
                }
            }
        });
    }
}
