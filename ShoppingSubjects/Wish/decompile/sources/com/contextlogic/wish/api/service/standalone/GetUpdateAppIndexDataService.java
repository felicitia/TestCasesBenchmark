package com.contextlogic.wish.api.service.standalone;

import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.model.WishGoogleAppIndexingData;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.SingleApiService;
import com.contextlogic.wish.util.JsonUtil;
import com.contextlogic.wish.util.JsonUtil.DataParser;
import java.text.ParseException;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class GetUpdateAppIndexDataService extends SingleApiService {
    private final String END_POINT = "mobile/get-app-indexing-data";

    public interface SuccessCallback {
        void onSuccess(List<WishGoogleAppIndexingData> list);
    }

    public void requestService(final SuccessCallback successCallback, final DefaultFailureCallback defaultFailureCallback) {
        startService(new ApiRequest("mobile/get-app-indexing-data"), new ApiCallback() {
            public String getCallIdentifier() {
                return "mobile/get-app-indexing-data";
            }

            public void handleFailure(ApiResponse apiResponse, String str) {
                if (defaultFailureCallback != null) {
                    defaultFailureCallback.onFailure(str);
                }
            }

            public void handleSuccess(ApiResponse apiResponse) {
                if (successCallback != null) {
                    successCallback.onSuccess(JsonUtil.parseArray(apiResponse.getData(), "app_index_data_items", new DataParser<WishGoogleAppIndexingData, JSONObject>() {
                        public WishGoogleAppIndexingData parseData(JSONObject jSONObject) throws JSONException, ParseException {
                            return new WishGoogleAppIndexingData(jSONObject);
                        }
                    }));
                }
            }
        });
    }
}
