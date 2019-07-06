package com.contextlogic.wish.api.service.standalone;

import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.model.WishProduct;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.SingleApiService;
import com.contextlogic.wish.util.JsonUtil;
import com.contextlogic.wish.util.JsonUtil.DataParser;
import java.text.ParseException;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

public class GetFeedService extends SingleApiService {

    public static class FeedContext {
        public String filter;
        public ArrayList<String> firstProductIds;
        public String sort;
    }

    public static class FeedExtraInfo {
        public String tagTitle;
    }

    public interface SuccessCallback {
        void onSuccess(ArrayList<Object> arrayList, boolean z, int i, FeedExtraInfo feedExtraInfo);
    }

    public void requestService(int i, int i2, FeedContext feedContext, final SuccessCallback successCallback, final DefaultFailureCallback defaultFailureCallback) {
        ApiRequest apiRequest = new ApiRequest("feed/get");
        apiRequest.addParameter("offset", (Object) Integer.toString(i));
        apiRequest.addParameter("count", (Object) Integer.toString(i2));
        if (feedContext.firstProductIds != null) {
            apiRequest.addParameter("first_cids[]", feedContext.firstProductIds);
        }
        apiRequest.addParameter("filter", (Object) feedContext.filter);
        apiRequest.addParameter("sort", (Object) feedContext.sort);
        startService(apiRequest, new ApiCallback() {
            public String getCallIdentifier() {
                return null;
            }

            public void handleFailure(ApiResponse apiResponse, final String str) {
                if (defaultFailureCallback != null) {
                    GetFeedService.this.postRunnable(new Runnable() {
                        public void run() {
                            defaultFailureCallback.onFailure(str);
                        }
                    });
                }
            }

            public void handleSuccess(ApiResponse apiResponse) throws JSONException {
                final ArrayList parseArray = JsonUtil.parseArray(apiResponse.getData(), "items", new DataParser<Object, JSONObject>() {
                    public Object parseData(JSONObject jSONObject) throws JSONException, ParseException {
                        return new WishProduct(jSONObject);
                    }
                });
                final boolean z = apiResponse.getData().getBoolean("feed_ended");
                final int i = apiResponse.getData().getInt("next_offset");
                final FeedExtraInfo feedExtraInfo = new FeedExtraInfo();
                feedExtraInfo.tagTitle = JsonUtil.optString(apiResponse.getData(), "tag");
                if (successCallback != null) {
                    GetFeedService getFeedService = GetFeedService.this;
                    AnonymousClass3 r3 = new Runnable() {
                        public void run() {
                            successCallback.onSuccess(parseArray, z, i, feedExtraInfo);
                        }
                    };
                    getFeedService.postRunnable(r3);
                }
            }
        });
    }
}
