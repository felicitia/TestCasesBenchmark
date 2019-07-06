package com.contextlogic.wish.api.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.text.ParseException;
import org.json.JSONException;
import org.json.JSONObject;

public class WishClipboardCouponClaimResponse extends BaseModel implements Parcelable {
    public static final Creator<WishClipboardCouponClaimResponse> CREATOR = new Creator<WishClipboardCouponClaimResponse>() {
        public WishClipboardCouponClaimResponse createFromParcel(Parcel parcel) {
            return new WishClipboardCouponClaimResponse(parcel);
        }

        public WishClipboardCouponClaimResponse[] newArray(int i) {
            return new WishClipboardCouponClaimResponse[i];
        }
    };
    private ResponseAction mResponseAction;
    private String mResponseDeepLink;
    private WishClipboardCouponPopupDialogSpec mResponseDialogSpec;

    public enum ResponseAction {
        NOTHING,
        RESOLVE_DEEPLINK,
        SHOW_DIALOG;

        static ResponseAction fromInt(int i) {
            switch (i) {
                case 0:
                    return NOTHING;
                case 1:
                    return RESOLVE_DEEPLINK;
                case 2:
                    return SHOW_DIALOG;
                default:
                    return NOTHING;
            }
        }
    }

    public int describeContents() {
        return 0;
    }

    public WishClipboardCouponClaimResponse(JSONObject jSONObject) throws JSONException, ParseException {
        super(jSONObject);
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) throws JSONException, ParseException {
        this.mResponseAction = ResponseAction.fromInt(jSONObject.getInt("action"));
        if (jSONObject.has("dialog_spec")) {
            this.mResponseDialogSpec = new WishClipboardCouponPopupDialogSpec(jSONObject.getJSONObject("dialog_spec"));
        }
        if (jSONObject.has("deep_link")) {
            this.mResponseDeepLink = jSONObject.getString("deep_link");
        }
    }

    protected WishClipboardCouponClaimResponse(Parcel parcel) {
        this.mResponseAction = ResponseAction.fromInt(parcel.readInt());
        this.mResponseDialogSpec = (WishClipboardCouponPopupDialogSpec) parcel.readParcelable(WishClipboardCouponPopupDialogSpec.class.getClassLoader());
        this.mResponseDeepLink = parcel.readString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mResponseAction.ordinal());
        parcel.writeParcelable(this.mResponseDialogSpec, i);
        parcel.writeString(this.mResponseDeepLink);
    }

    public ResponseAction getResponseAction() {
        return this.mResponseAction;
    }

    public WishClipboardCouponPopupDialogSpec getResponseDialogSpec() {
        return this.mResponseDialogSpec;
    }

    public String getResponseDeepLink() {
        return this.mResponseDeepLink;
    }
}
