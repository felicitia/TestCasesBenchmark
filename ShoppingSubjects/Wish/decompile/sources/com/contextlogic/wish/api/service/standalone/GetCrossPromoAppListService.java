package com.contextlogic.wish.api.service.standalone;

import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.model.WishCrossPromoApp;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.SingleApiService;
import com.contextlogic.wish.util.JsonUtil;
import com.contextlogic.wish.util.JsonUtil.DataParser;
import java.text.ParseException;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

public class GetCrossPromoAppListService extends SingleApiService {

    public interface SuccessCallback {
        void onSuccess(ArrayList<WishCrossPromoApp> arrayList);
    }

    public void requestService(final SuccessCallback successCallback, final DefaultFailureCallback defaultFailureCallback) {
        startService(new ApiRequest("mobile/app-list"), new ApiCallback() {
            public String getCallIdentifier() {
                return null;
            }

            public void handleFailure(ApiResponse apiResponse, final String str) {
                if (defaultFailureCallback != null) {
                    GetCrossPromoAppListService.this.postRunnable(new Runnable() {
                        public void run() {
                            defaultFailureCallback.onFailure(str);
                        }
                    });
                }
            }

            public void handleSuccess(ApiResponse apiResponse) {
                final ArrayList parseArray = JsonUtil.parseArray(apiResponse.getData(), "apps", new DataParser<WishCrossPromoApp, JSONObject>() {
                    public WishCrossPromoApp parseData(JSONObject jSONObject) throws JSONException, ParseException {
                        return new WishCrossPromoApp(jSONObject);
                    }
                });
                if (successCallback != null) {
                    GetCrossPromoAppListService.this.postRunnable(new Runnable() {
                        public void run() {
                            successCallback.onSuccess(parseArray);
                        }
                    });
                }
            }
        });
    }
}
