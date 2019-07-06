package com.contextlogic.wish.api.service.standalone;

import com.contextlogic.wish.analytics.GoogleAnalyticsLogger;
import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.model.WishWishlist;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.SingleApiService;
import com.contextlogic.wish.application.ApplicationEventManager;
import com.contextlogic.wish.application.ApplicationEventManager.EventType;
import com.contextlogic.wish.social.facebook.FacebookManager;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONException;

public class AddToWishlistService extends SingleApiService {

    public interface SuccessCallback {
        void onSuccess(WishWishlist wishWishlist);
    }

    public void requestService(String str, String str2, SuccessCallback successCallback, DefaultFailureCallback defaultFailureCallback) {
        requestService(str, str2, false, (String) null, successCallback, defaultFailureCallback);
    }

    public void requestService(ArrayList<String> arrayList, String str, SuccessCallback successCallback, DefaultFailureCallback defaultFailureCallback) {
        requestService(arrayList, str, false, (String) null, successCallback, defaultFailureCallback);
    }

    public void requestService(String str, String str2, boolean z, String str3, SuccessCallback successCallback, DefaultFailureCallback defaultFailureCallback) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(str);
        requestService(arrayList, str2, z, str3, successCallback, defaultFailureCallback);
    }

    public void requestService(final ArrayList<String> arrayList, String str, boolean z, String str2, final SuccessCallback successCallback, final DefaultFailureCallback defaultFailureCallback) {
        ApiRequest apiRequest = new ApiRequest("user/wishlist/add-product");
        apiRequest.addParameter("product_ids[]", arrayList);
        if (z) {
            apiRequest.addParameter("to_default_wishlist", (Object) "true");
        }
        if (str2 != null) {
            apiRequest.addParameter("name", (Object) str2);
        }
        if (str != null) {
            apiRequest.addParameter("wishlist_id", (Object) str);
        }
        startService(apiRequest, new ApiCallback() {
            public String getCallIdentifier() {
                return null;
            }

            public void handleSuccess(final ApiResponse apiResponse) throws JSONException, ParseException {
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    String str = (String) it.next();
                    ApplicationEventManager.getInstance().triggerEvent(EventType.PRODUCT_WISH, str, null);
                    try {
                        FacebookManager.getInstance().getLogger().logEvent("fb_mobile_add_to_wishlist");
                    } catch (Throwable unused) {
                    }
                    GoogleAnalyticsLogger.getInstance().logProductWish(str);
                }
                if (successCallback != null) {
                    AddToWishlistService.this.postRunnable(new Runnable() {
                        public void run() {
                            try {
                                successCallback.onSuccess(new WishWishlist(apiResponse.getData()));
                            } catch (ParseException | JSONException e) {
                                AnonymousClass1.this.handleFailure(apiResponse, e.getMessage());
                            }
                        }
                    });
                }
            }

            public void handleFailure(ApiResponse apiResponse, final String str) {
                if (defaultFailureCallback != null) {
                    AddToWishlistService.this.postRunnable(new Runnable() {
                        public void run() {
                            defaultFailureCallback.onFailure(str);
                        }
                    });
                }
            }
        });
    }
}
