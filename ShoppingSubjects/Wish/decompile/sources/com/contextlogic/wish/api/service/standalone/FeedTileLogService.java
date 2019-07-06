package com.contextlogic.wish.api.service.standalone;

import com.contextlogic.wish.analytics.FeedTileLogger;
import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.service.SingleApiService;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;

public class FeedTileLogService extends SingleApiService {
    public void requestService(final ArrayList<Map<String, String>> arrayList) {
        ApiRequest apiRequest = new ApiRequest("mobile/log-tile-interactions");
        Gson gson = new Gson();
        ArrayList arrayList2 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.add(gson.toJson((Object) (Map) it.next()));
        }
        apiRequest.addParameter("logged_items[]", arrayList2);
        startService(apiRequest, new ApiCallback() {
            public String getCallIdentifier() {
                return null;
            }

            public void handleSuccess(ApiResponse apiResponse) throws JSONException {
            }

            public void handleFailure(ApiResponse apiResponse, String str) {
                FeedTileLogger.getInstance().addAllToQueue(arrayList);
            }
        });
    }
}
