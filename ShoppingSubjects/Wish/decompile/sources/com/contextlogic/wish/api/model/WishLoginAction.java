package com.contextlogic.wish.api.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.contextlogic.wish.util.JsonUtil;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class WishLoginAction extends BaseModel implements Parcelable {
    public static final Creator<WishLoginAction> CREATOR = new Creator<WishLoginAction>() {
        public WishLoginAction createFromParcel(Parcel parcel) {
            return new WishLoginAction(parcel);
        }

        public WishLoginAction[] newArray(int i) {
            return new WishLoginAction[i];
        }
    };
    private List<ActionType> mActionTypes;
    private LoggedOutCountdownCoupon mLoggedOutCountdownCoupon;
    private WishClipboardCouponPopupDialogSpec mWishClipboardCouponPopupDialogSpec;
    private WishDailyLoginStampSpec mWishDailyLoginStampSpec;
    private WishLoginActionDeepLink mWishLoginActionDeepLink;
    private WishLoginActionPopup mWishLoginActionPopup;
    private WishPromotionSpec mWishLoginActionPromotion;
    private WishLoginActionRateApp mWishLoginActionRateApp;
    private WishLoginActionUgcFeedback mWishLoginActionUgcFeedback;
    private WishLoginActionUpdateApp mWishLoginActionUpdateApp;
    private WishNotShippableCountryPopupSpec mWishNotShippableCountryPopupSpec;
    private WishVideoPopupSpec mWishVideoPopupSpec;

    public enum ActionType implements Parcelable {
        NONE(0),
        POPUP(1),
        DEEP_LINK(2),
        PROMOTION(3),
        UPDATE_APP(4),
        RATE_APP(5),
        INVITE_FRIEND(6),
        CLIPBOARD(7),
        UGC_FEEDBACK(8),
        DAILY_LOGIN_BONUS(9),
        WISH_STAR(11),
        CAMERA_FEATURE(13),
        COUNTDOWN_COUPON_CLAIMED(14),
        NOT_SHIPPABLE_POPUP(15),
        TERMS_OF_USE_UPDATE(16),
        VIDEO_POPUP(17);
        
        public static final Creator<ActionType> CREATOR = null;
        private int mValue;

        public int describeContents() {
            return 0;
        }

        static {
            CREATOR = new Creator<ActionType>() {
                public ActionType createFromParcel(Parcel parcel) {
                    return ActionType.values()[parcel.readInt()];
                }

                public ActionType[] newArray(int i) {
                    return new ActionType[i];
                }
            };
        }

        public static ActionType fromInt(int i) {
            if (i == 1) {
                return POPUP;
            }
            if (i == 2) {
                return DEEP_LINK;
            }
            if (i == 3) {
                return PROMOTION;
            }
            if (i == 4) {
                return UPDATE_APP;
            }
            if (i == 5) {
                return RATE_APP;
            }
            if (i == 6) {
                return INVITE_FRIEND;
            }
            if (i == 7) {
                return CLIPBOARD;
            }
            if (i == 8) {
                return UGC_FEEDBACK;
            }
            if (i == 9) {
                return DAILY_LOGIN_BONUS;
            }
            if (i == 11) {
                return WISH_STAR;
            }
            if (i == 13) {
                return CAMERA_FEATURE;
            }
            if (i == 14) {
                return COUNTDOWN_COUPON_CLAIMED;
            }
            if (i == 15) {
                return NOT_SHIPPABLE_POPUP;
            }
            if (i == 16) {
                return TERMS_OF_USE_UPDATE;
            }
            if (i == 17) {
                return VIDEO_POPUP;
            }
            return null;
        }

        private ActionType(int i) {
            this.mValue = i;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.mValue);
        }
    }

    public int describeContents() {
        return 0;
    }

    protected WishLoginAction(Parcel parcel) {
        this.mWishLoginActionPopup = (WishLoginActionPopup) parcel.readParcelable(WishLoginActionPopup.class.getClassLoader());
        this.mWishLoginActionDeepLink = (WishLoginActionDeepLink) parcel.readParcelable(WishLoginActionDeepLink.class.getClassLoader());
        this.mWishLoginActionPromotion = (WishPromotionSpec) parcel.readParcelable(WishPromotionSpec.class.getClassLoader());
        this.mWishLoginActionRateApp = (WishLoginActionRateApp) parcel.readParcelable(WishLoginActionRateApp.class.getClassLoader());
        this.mWishLoginActionUpdateApp = (WishLoginActionUpdateApp) parcel.readParcelable(WishLoginActionUpdateApp.class.getClassLoader());
        this.mWishClipboardCouponPopupDialogSpec = (WishClipboardCouponPopupDialogSpec) parcel.readParcelable(WishClipboardCouponPopupDialogSpec.class.getClassLoader());
        this.mWishLoginActionUgcFeedback = (WishLoginActionUgcFeedback) parcel.readParcelable(WishLoginActionUgcFeedback.class.getClassLoader());
        this.mWishDailyLoginStampSpec = (WishDailyLoginStampSpec) parcel.readParcelable(WishDailyLoginStampSpec.class.getClassLoader());
        this.mLoggedOutCountdownCoupon = (LoggedOutCountdownCoupon) parcel.readParcelable(LoggedOutCountdownCoupon.class.getClassLoader());
        this.mWishNotShippableCountryPopupSpec = (WishNotShippableCountryPopupSpec) parcel.readParcelable(WishNotShippableCountryPopupSpec.class.getClassLoader());
        this.mWishVideoPopupSpec = (WishVideoPopupSpec) parcel.readParcelable(WishVideoPopupSpec.class.getClassLoader());
        this.mActionTypes = parcel.readArrayList(ActionType.class.getClassLoader());
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.mWishLoginActionPopup, i);
        parcel.writeParcelable(this.mWishLoginActionDeepLink, i);
        parcel.writeParcelable(this.mWishLoginActionPromotion, i);
        parcel.writeParcelable(this.mWishLoginActionRateApp, i);
        parcel.writeParcelable(this.mWishLoginActionUpdateApp, i);
        parcel.writeParcelable(this.mWishClipboardCouponPopupDialogSpec, i);
        parcel.writeParcelable(this.mWishLoginActionUgcFeedback, i);
        parcel.writeParcelable(this.mWishDailyLoginStampSpec, i);
        parcel.writeParcelable(this.mLoggedOutCountdownCoupon, i);
        parcel.writeParcelable(this.mWishNotShippableCountryPopupSpec, i);
        parcel.writeParcelable(this.mWishVideoPopupSpec, i);
        parcel.writeList(this.mActionTypes);
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) throws JSONException, ParseException {
        if (JsonUtil.hasValue(jSONObject, "actions_array")) {
            this.mActionTypes = new ArrayList();
            JSONArray jSONArray = jSONObject.getJSONArray("actions_array");
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                ActionType fromInt = ActionType.fromInt(jSONObject2.getInt("action_type"));
                if (fromInt != null) {
                    this.mActionTypes.add(fromInt);
                    if (JsonUtil.hasValue(jSONObject2, "action")) {
                        if (fromInt != ActionType.VIDEO_POPUP || !JsonUtil.hasValue(jSONObject2.getJSONObject("action"), "popup_spec")) {
                            parseAction(fromInt, jSONObject2.getJSONObject("action"));
                        } else {
                            parseAction(fromInt, jSONObject2.getJSONObject("action").getJSONObject("popup_spec"));
                        }
                    }
                }
            }
        }
    }

    public void parseAction(ActionType actionType, JSONObject jSONObject) throws JSONException, ParseException {
        switch (actionType) {
            case POPUP:
                this.mWishLoginActionPopup = new WishLoginActionPopup(jSONObject);
                return;
            case DEEP_LINK:
                this.mWishLoginActionDeepLink = new WishLoginActionDeepLink(jSONObject);
                return;
            case PROMOTION:
                this.mWishLoginActionPromotion = new WishPromotionSpec(jSONObject);
                return;
            case RATE_APP:
                this.mWishLoginActionRateApp = new WishLoginActionRateApp(jSONObject);
                return;
            case UPDATE_APP:
                this.mWishLoginActionUpdateApp = new WishLoginActionUpdateApp(jSONObject);
                return;
            case CLIPBOARD:
                this.mWishClipboardCouponPopupDialogSpec = new WishClipboardCouponPopupDialogSpec(jSONObject);
                return;
            case UGC_FEEDBACK:
                this.mWishLoginActionUgcFeedback = new WishLoginActionUgcFeedback(jSONObject);
                return;
            case DAILY_LOGIN_BONUS:
                this.mWishDailyLoginStampSpec = new WishDailyLoginStampSpec(jSONObject);
                return;
            case COUNTDOWN_COUPON_CLAIMED:
                this.mLoggedOutCountdownCoupon = new LoggedOutCountdownCoupon(jSONObject);
                return;
            case NOT_SHIPPABLE_POPUP:
                this.mWishNotShippableCountryPopupSpec = new WishNotShippableCountryPopupSpec(jSONObject);
                return;
            case TERMS_OF_USE_UPDATE:
                this.mWishLoginActionPopup = new WishLoginActionPopup(jSONObject);
                return;
            case VIDEO_POPUP:
                this.mWishVideoPopupSpec = new WishVideoPopupSpec(jSONObject);
                return;
            default:
                return;
        }
    }

    public WishLoginAction(JSONObject jSONObject) throws JSONException, ParseException {
        super(jSONObject);
    }

    public WishLoginActionPopup getWishLoginActionPopup() {
        return this.mWishLoginActionPopup;
    }

    public WishLoginActionDeepLink getWishLoginActionDeepLink() {
        return this.mWishLoginActionDeepLink;
    }

    public WishPromotionSpec getWishLoginActionPromotion() {
        return this.mWishLoginActionPromotion;
    }

    public WishLoginActionRateApp getWishLoginActionRateApp() {
        return this.mWishLoginActionRateApp;
    }

    public WishLoginActionUpdateApp getWishLoginActionUpdateApp() {
        return this.mWishLoginActionUpdateApp;
    }

    public WishClipboardCouponPopupDialogSpec getWishCouponPopup() {
        return this.mWishClipboardCouponPopupDialogSpec;
    }

    public WishLoginActionUgcFeedback getWishUgcFeedback() {
        return this.mWishLoginActionUgcFeedback;
    }

    public WishDailyLoginStampSpec getWishDailyLoginStampSpec() {
        return this.mWishDailyLoginStampSpec;
    }

    public List<ActionType> getActionTypesArray() {
        return this.mActionTypes;
    }

    public WishNotShippableCountryPopupSpec getWishNotShippableCountryPopupSpec() {
        return this.mWishNotShippableCountryPopupSpec;
    }

    public WishVideoPopupSpec getVideoPopupSpec() {
        return this.mWishVideoPopupSpec;
    }

    public LoggedOutCountdownCoupon getLoggedOutCountdownCoupon() {
        return this.mLoggedOutCountdownCoupon;
    }
}
