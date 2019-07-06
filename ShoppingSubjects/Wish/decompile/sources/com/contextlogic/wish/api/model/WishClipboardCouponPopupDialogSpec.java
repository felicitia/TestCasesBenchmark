package com.contextlogic.wish.api.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.contextlogic.wish.util.JsonUtil;
import com.contextlogic.wish.util.JsonUtil.DataParser;
import java.text.ParseException;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class WishClipboardCouponPopupDialogSpec extends BaseModel implements Parcelable {
    public static final Creator<WishClipboardCouponPopupDialogSpec> CREATOR = new Creator<WishClipboardCouponPopupDialogSpec>() {
        public WishClipboardCouponPopupDialogSpec createFromParcel(Parcel parcel) {
            return new WishClipboardCouponPopupDialogSpec(parcel);
        }

        public WishClipboardCouponPopupDialogSpec[] newArray(int i) {
            return new WishClipboardCouponPopupDialogSpec[i];
        }
    };
    private List<WishTextViewSpec> mBodyText;
    private ButtonActionType mButtonActionType;
    private String mButtonDeeplink;
    private int mButtonEventId;
    private String mButtonText;
    private String mCouponCode;
    private int mDialogImpressionEventId;
    private String mFooterDeeplink;
    private List<WishTextViewSpec> mFooterText;
    private List<WishTextViewSpec> mHeaderText;
    private List<WishProduct> mProducts;
    private boolean mShowHeader;
    private boolean mShowXButton;

    public enum ButtonActionType {
        CLAIM_COUPON,
        DEEPLINK,
        DO_NOTHING;

        public static ButtonActionType fromInt(int i) {
            switch (i) {
                case 0:
                    return CLAIM_COUPON;
                case 1:
                    return DEEPLINK;
                default:
                    return DO_NOTHING;
            }
        }
    }

    public int describeContents() {
        return 0;
    }

    public WishClipboardCouponPopupDialogSpec(JSONObject jSONObject) throws JSONException, ParseException {
        super(jSONObject);
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) throws JSONException, ParseException {
        this.mButtonActionType = ButtonActionType.fromInt(jSONObject.optInt("button_type", -1));
        this.mButtonDeeplink = jSONObject.optString("button_deeplink");
        this.mCouponCode = jSONObject.optString("coupon_code");
        this.mShowHeader = jSONObject.optBoolean("show_header");
        this.mShowXButton = jSONObject.optBoolean("show_x_button");
        if (JsonUtil.hasValue(jSONObject, "products")) {
            this.mProducts = JsonUtil.parseArray(jSONObject, "products", new DataParser<WishProduct, JSONObject>() {
                public WishProduct parseData(JSONObject jSONObject) throws JSONException, ParseException {
                    return new WishProduct(jSONObject);
                }
            });
        }
        if (JsonUtil.hasValue(jSONObject, "header_text")) {
            this.mHeaderText = JsonUtil.parseArray(jSONObject, "header_text", new DataParser<WishTextViewSpec, JSONObject>() {
                public WishTextViewSpec parseData(JSONObject jSONObject) throws JSONException, ParseException {
                    return new WishTextViewSpec(jSONObject);
                }
            });
        }
        if (JsonUtil.hasValue(jSONObject, "body_text")) {
            this.mBodyText = JsonUtil.parseArray(jSONObject, "body_text", new DataParser<WishTextViewSpec, JSONObject>() {
                public WishTextViewSpec parseData(JSONObject jSONObject) throws JSONException, ParseException {
                    return new WishTextViewSpec(jSONObject);
                }
            });
        }
        if (JsonUtil.hasValue(jSONObject, "footer_text")) {
            this.mFooterText = JsonUtil.parseArray(jSONObject, "footer_text", new DataParser<WishTextViewSpec, JSONObject>() {
                public WishTextViewSpec parseData(JSONObject jSONObject) throws JSONException, ParseException {
                    return new WishTextViewSpec(jSONObject);
                }
            });
        }
        this.mButtonText = jSONObject.optString("button_text");
        this.mFooterDeeplink = jSONObject.optString("footer_deeplink");
        this.mButtonEventId = jSONObject.optInt("button_event_id", -1);
        this.mDialogImpressionEventId = jSONObject.optInt("dialog_impression_event_id", -1);
    }

    protected WishClipboardCouponPopupDialogSpec(Parcel parcel) {
        this.mButtonActionType = ButtonActionType.fromInt(parcel.readInt());
        this.mCouponCode = parcel.readString();
        this.mButtonDeeplink = parcel.readString();
        boolean z = false;
        this.mShowHeader = parcel.readByte() != 0;
        if (parcel.readByte() != 0) {
            z = true;
        }
        this.mShowXButton = z;
        this.mProducts = parcel.readArrayList(WishProduct.class.getClassLoader());
        this.mHeaderText = parcel.readArrayList(WishTextViewSpec.class.getClassLoader());
        this.mBodyText = parcel.readArrayList(WishTextViewSpec.class.getClassLoader());
        this.mFooterText = parcel.readArrayList(WishTextViewSpec.class.getClassLoader());
        this.mButtonText = parcel.readString();
        this.mFooterDeeplink = parcel.readString();
        this.mButtonEventId = parcel.readInt();
        this.mDialogImpressionEventId = parcel.readInt();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mButtonActionType.ordinal());
        parcel.writeString(this.mCouponCode);
        parcel.writeString(this.mButtonDeeplink);
        parcel.writeByte(this.mShowHeader ? (byte) 1 : 0);
        parcel.writeByte(this.mShowXButton ? (byte) 1 : 0);
        parcel.writeList(this.mProducts);
        parcel.writeList(this.mHeaderText);
        parcel.writeList(this.mBodyText);
        parcel.writeList(this.mFooterText);
        parcel.writeString(this.mButtonText);
        parcel.writeString(this.mFooterDeeplink);
        parcel.writeInt(this.mButtonEventId);
        parcel.writeInt(this.mDialogImpressionEventId);
    }

    public boolean shouldShowXButton() {
        return this.mShowXButton;
    }

    public boolean shouldShowHeader() {
        return this.mShowHeader;
    }

    public List<WishProduct> getProducts() {
        return this.mProducts;
    }

    public List<WishTextViewSpec> getHeaderText() {
        return this.mHeaderText;
    }

    public List<WishTextViewSpec> getBodyText() {
        return this.mBodyText;
    }

    public List<WishTextViewSpec> getFooterText() {
        return this.mFooterText;
    }

    public String getButtonText() {
        return this.mButtonText;
    }

    public String getFooterDeeplink() {
        return this.mFooterDeeplink;
    }

    public int getDialogImpressionEventId() {
        return this.mDialogImpressionEventId;
    }

    public int getActionButtonEventId() {
        return this.mButtonEventId;
    }

    public ButtonActionType getButtonActionType() {
        return this.mButtonActionType;
    }

    public String getCouponCode() {
        return this.mCouponCode;
    }

    public String getButtonDeeplink() {
        return this.mButtonDeeplink;
    }
}
