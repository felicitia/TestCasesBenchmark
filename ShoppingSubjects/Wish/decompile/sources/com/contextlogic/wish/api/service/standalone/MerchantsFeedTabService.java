package com.contextlogic.wish.api.service.standalone;

import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.model.MerchantFeedItem;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.SingleApiService;
import com.contextlogic.wish.util.JsonUtil;
import com.contextlogic.wish.util.JsonUtil.DataParser;
import java.text.ParseException;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class MerchantsFeedTabService extends SingleApiService {

    public interface SuccessCallback {
        void onSuccess(boolean z, List<MerchantFeedItem> list);
    }

    /* access modifiers changed from: protected */
    public abstract String getRequestPath();

    public void requestService(int i, int i2, final SuccessCallback successCallback, final DefaultFailureCallback defaultFailureCallback) {
        ApiRequest apiRequest = new ApiRequest(getRequestPath());
        apiRequest.addParameter("start", i);
        apiRequest.addParameter("count", i2);
        startService(apiRequest, new ApiCallback() {
            public String getCallIdentifier() {
                return null;
            }

            public void handleFailure(ApiResponse apiResponse, final String str) {
                if (defaultFailureCallback != null) {
                    MerchantsFeedTabService.this.postRunnable(new Runnable() {
                        public void run() {
                            defaultFailureCallback.onFailure(str);
                        }
                    });
                }
            }

            public void handleSuccess(final ApiResponse apiResponse) {
                MerchantsFeedTabService.this.postRunnable(new Runnable() {
                    public void run() {
                        List access$000 = AnonymousClass1.this.parseData(apiResponse.getData());
                        successCallback.onSuccess(AnonymousClass1.this.parseNoMoreItems(apiResponse.getData()), access$000);
                    }
                });
            }

            /* access modifiers changed from: private */
            public boolean parseNoMoreItems(JSONObject jSONObject) {
                return jSONObject.optBoolean("no_more_items", false);
            }

            /* access modifiers changed from: private */
            public List<MerchantFeedItem> parseData(JSONObject jSONObject) {
                return JsonUtil.parseArray(jSONObject, "merchant_feeds", new DataParser<MerchantFeedItem, JSONObject>() {
                    public MerchantFeedItem parseData(JSONObject jSONObject) throws JSONException, ParseException {
                        return new MerchantFeedItem(jSONObject);
                    }
                });
            }
        });
    }
}
