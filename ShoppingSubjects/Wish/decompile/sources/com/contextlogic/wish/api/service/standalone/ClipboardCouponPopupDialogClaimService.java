package com.contextlogic.wish.api.service.standalone;

import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.model.WishClipboardCouponClaimResponse;
import com.contextlogic.wish.api.model.WishClipboardCouponClaimResponse.ResponseAction;
import com.contextlogic.wish.api.model.WishClipboardCouponPopupDialogSpec;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.SingleApiService;
import com.contextlogic.wish.link.DeepLink;
import java.text.ParseException;
import org.json.JSONException;

public class ClipboardCouponPopupDialogClaimService extends SingleApiService {

    /* renamed from: com.contextlogic.wish.api.service.standalone.ClipboardCouponPopupDialogClaimService$2 reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$contextlogic$wish$api$model$WishClipboardCouponClaimResponse$ResponseAction = new int[ResponseAction.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|8) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        static {
            /*
                com.contextlogic.wish.api.model.WishClipboardCouponClaimResponse$ResponseAction[] r0 = com.contextlogic.wish.api.model.WishClipboardCouponClaimResponse.ResponseAction.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$contextlogic$wish$api$model$WishClipboardCouponClaimResponse$ResponseAction = r0
                int[] r0 = $SwitchMap$com$contextlogic$wish$api$model$WishClipboardCouponClaimResponse$ResponseAction     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.contextlogic.wish.api.model.WishClipboardCouponClaimResponse$ResponseAction r1 = com.contextlogic.wish.api.model.WishClipboardCouponClaimResponse.ResponseAction.RESOLVE_DEEPLINK     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = $SwitchMap$com$contextlogic$wish$api$model$WishClipboardCouponClaimResponse$ResponseAction     // Catch:{ NoSuchFieldError -> 0x001f }
                com.contextlogic.wish.api.model.WishClipboardCouponClaimResponse$ResponseAction r1 = com.contextlogic.wish.api.model.WishClipboardCouponClaimResponse.ResponseAction.SHOW_DIALOG     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = $SwitchMap$com$contextlogic$wish$api$model$WishClipboardCouponClaimResponse$ResponseAction     // Catch:{ NoSuchFieldError -> 0x002a }
                com.contextlogic.wish.api.model.WishClipboardCouponClaimResponse$ResponseAction r1 = com.contextlogic.wish.api.model.WishClipboardCouponClaimResponse.ResponseAction.NOTHING     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.contextlogic.wish.api.service.standalone.ClipboardCouponPopupDialogClaimService.AnonymousClass2.<clinit>():void");
        }
    }

    public interface SuccessCallback {
        void onSuccessDoNothing();

        void onSuccessResolveDeepLink(DeepLink deepLink);

        void onSuccessShowDialog(WishClipboardCouponPopupDialogSpec wishClipboardCouponPopupDialogSpec);
    }

    public void requestService(String str, final SuccessCallback successCallback, final DefaultFailureCallback defaultFailureCallback) {
        ApiRequest apiRequest = new ApiRequest("mobile/claim-clipboard-coupon");
        apiRequest.addParameter("coupon_code", (Object) str);
        startService(apiRequest, new ApiCallback() {
            public String getCallIdentifier() {
                return null;
            }

            public void handleFailure(ApiResponse apiResponse, final String str) {
                if (defaultFailureCallback != null) {
                    ClipboardCouponPopupDialogClaimService.this.postRunnable(new Runnable() {
                        public void run() {
                            defaultFailureCallback.onFailure(str);
                        }
                    });
                }
            }

            public void handleSuccess(ApiResponse apiResponse) throws JSONException, ParseException {
                if (successCallback != null) {
                    final WishClipboardCouponClaimResponse wishClipboardCouponClaimResponse = new WishClipboardCouponClaimResponse(apiResponse.getData());
                    ClipboardCouponPopupDialogClaimService.this.postRunnable(new Runnable() {
                        public void run() {
                            switch (AnonymousClass2.$SwitchMap$com$contextlogic$wish$api$model$WishClipboardCouponClaimResponse$ResponseAction[wishClipboardCouponClaimResponse.getResponseAction().ordinal()]) {
                                case 1:
                                    if (wishClipboardCouponClaimResponse.getResponseDeepLink() != null) {
                                        successCallback.onSuccessResolveDeepLink(new DeepLink(wishClipboardCouponClaimResponse.getResponseDeepLink()));
                                        return;
                                    }
                                    return;
                                case 2:
                                    if (wishClipboardCouponClaimResponse.getResponseDialogSpec() != null) {
                                        successCallback.onSuccessShowDialog(wishClipboardCouponClaimResponse.getResponseDialogSpec());
                                        return;
                                    }
                                    return;
                                default:
                                    successCallback.onSuccessDoNothing();
                                    return;
                            }
                        }
                    });
                }
            }
        });
    }
}
