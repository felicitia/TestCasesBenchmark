package com.contextlogic.wish.api.service.standalone;

import com.contextlogic.wish.R;
import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.SingleApiService;
import com.contextlogic.wish.application.WishApplication;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

public class GetCurrencySettingsService extends SingleApiService {

    public interface SuccessCallback {
        void onSuccess(String str, ArrayList<String> arrayList, ArrayList<String> arrayList2);
    }

    public void requestService(final SuccessCallback successCallback, final DefaultFailureCallback defaultFailureCallback) {
        startService(new ApiRequest("settings/get"), new ApiCallback() {
            public String getCallIdentifier() {
                return null;
            }

            public void handleFailure(ApiResponse apiResponse, final String str) {
                if (defaultFailureCallback != null) {
                    GetCurrencySettingsService.this.postRunnable(new Runnable() {
                        public void run() {
                            defaultFailureCallback.onFailure(str);
                        }
                    });
                }
            }

            public void handleSuccess(ApiResponse apiResponse) {
                boolean z = true;
                if (apiResponse.hasData()) {
                    JSONObject optJSONObject = apiResponse.getData().optJSONObject("currency_preferences");
                    final String optString = optJSONObject.optString("preferred_currency");
                    final ArrayList arrayList = new ArrayList();
                    final ArrayList arrayList2 = new ArrayList();
                    JSONArray optJSONArray = optJSONObject.optJSONArray("currencies");
                    if (optJSONArray != null) {
                        for (int i = 0; i < optJSONArray.length(); i++) {
                            JSONArray optJSONArray2 = optJSONArray.optJSONArray(i);
                            arrayList.add(optJSONArray2.optString(0));
                            arrayList2.add(optJSONArray2.optString(1));
                        }
                    }
                    if (successCallback != null && optString != null && !arrayList.isEmpty() && arrayList.size() == arrayList2.size()) {
                        GetCurrencySettingsService.this.postRunnable(new Runnable() {
                            public void run() {
                                successCallback.onSuccess(optString, arrayList, arrayList2);
                            }
                        });
                        if (!z && defaultFailureCallback != null) {
                            defaultFailureCallback.onFailure(WishApplication.getInstance().getString(R.string.error_load_currency_settings));
                            return;
                        }
                    }
                }
                z = false;
                if (!z) {
                }
            }
        });
    }
}
