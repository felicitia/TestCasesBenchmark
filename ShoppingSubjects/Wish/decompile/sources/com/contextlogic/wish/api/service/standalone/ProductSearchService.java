package com.contextlogic.wish.api.service.standalone;

import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.model.WishFilter;
import com.contextlogic.wish.api.model.WishGoogleAppIndexingData;
import com.contextlogic.wish.api.model.WishProduct;
import com.contextlogic.wish.api.model.WishRelatedSearch;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.util.JsonUtil;
import com.contextlogic.wish.util.JsonUtil.DataParser;
import java.text.ParseException;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

public class ProductSearchService extends BaseFeedApiService<WishProduct> {

    public static class FeedContext {
        public ArrayList<WishFilter> filters;
        public boolean onlyWishExpress;
        public boolean requestSearchFilter;
    }

    public static class FeedExtraInfo {
        public WishGoogleAppIndexingData appIndexingData;
        public ArrayList<WishRelatedSearch> relatedSearches;
        public int totalCount;
    }

    public interface SuccessCallback {
        void onSuccess(ArrayList<WishProduct> arrayList, int i, FeedExtraInfo feedExtraInfo);
    }

    public void requestService(String str, int i, int i2, FeedContext feedContext, final SuccessCallback successCallback, final DefaultFailureCallback defaultFailureCallback) {
        ApiRequest apiRequest = new ApiRequest("search");
        apiRequest.addParameter("query", (Object) str);
        apiRequest.addParameter("start", i);
        apiRequest.addParameter("count", i2);
        apiRequest.addParameter("transform", true);
        apiRequest.addParameter("only_wish_express", feedContext.onlyWishExpress);
        if (feedContext.filters != null && feedContext.filters.size() > 0) {
            apiRequest.addParameter("filters[]", feedContext.filters);
        }
        startService(apiRequest, new ApiCallback() {
            public String getCallIdentifier() {
                return null;
            }

            public void handleFailure(ApiResponse apiResponse, String str) {
                ProductSearchService.this.parseFailure(apiResponse, str, defaultFailureCallback);
            }

            public void handleSuccess(ApiResponse apiResponse) throws JSONException, ParseException {
                final ArrayList parseArray = JsonUtil.parseArray(apiResponse.getData(), "results", new DataParser<WishProduct, JSONObject>() {
                    public WishProduct parseData(JSONObject jSONObject) throws JSONException, ParseException {
                        return new WishProduct(jSONObject);
                    }
                });
                ArrayList<WishRelatedSearch> parseArray2 = JsonUtil.parseArray(apiResponse.getData(), "related_search_specs", new DataParser<WishRelatedSearch, JSONObject>() {
                    public WishRelatedSearch parseData(JSONObject jSONObject) throws JSONException, ParseException {
                        return new WishRelatedSearch(jSONObject);
                    }
                });
                final int optInt = apiResponse.getData().optInt("next_offset");
                final FeedExtraInfo feedExtraInfo = new FeedExtraInfo();
                feedExtraInfo.relatedSearches = parseArray2;
                feedExtraInfo.totalCount = apiResponse.getData().optInt("num_found");
                if (JsonUtil.hasValue(apiResponse.getData(), "app_indexing_data")) {
                    feedExtraInfo.appIndexingData = new WishGoogleAppIndexingData(apiResponse.getData().getJSONObject("app_indexing_data"));
                }
                if (successCallback != null) {
                    ProductSearchService.this.postRunnable(new Runnable() {
                        public void run() {
                            successCallback.onSuccess(parseArray, optInt, feedExtraInfo);
                        }
                    });
                }
            }
        });
    }
}
