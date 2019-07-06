package com.contextlogic.wish.api.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.text.ParseException;
import org.json.JSONException;
import org.json.JSONObject;

public class WishPromotionSpec extends BaseModel implements Parcelable {
    public static final Creator<WishPromotionSpec> CREATOR = new Creator<WishPromotionSpec>() {
        public WishPromotionSpec createFromParcel(Parcel parcel) {
            return new WishPromotionSpec(parcel);
        }

        public WishPromotionSpec[] newArray(int i) {
            return new WishPromotionSpec[i];
        }
    };
    private WishPromotionBaseSpec wishPromotionDeal;

    private enum PromotionActionType implements Parcelable {
        COUPON(0),
        NO_COUPON(1),
        ROTATING(2),
        GIFT_PACK(3);
        
        public static final Creator<PromotionActionType> CREATOR = null;
        private int mValue;

        public int describeContents() {
            return 0;
        }

        static {
            CREATOR = new Creator<PromotionActionType>() {
                public PromotionActionType createFromParcel(Parcel parcel) {
                    return PromotionActionType.values()[parcel.readInt()];
                }

                public PromotionActionType[] newArray(int i) {
                    return new PromotionActionType[i];
                }
            };
        }

        public static PromotionActionType fromInt(int i) {
            if (i == 0) {
                return COUPON;
            }
            if (i == 1) {
                return NO_COUPON;
            }
            if (i == 2) {
                return ROTATING;
            }
            if (i == 3) {
                return GIFT_PACK;
            }
            return null;
        }

        private PromotionActionType(int i) {
            this.mValue = i;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.mValue);
        }
    }

    public int describeContents() {
        return 0;
    }

    private WishPromotionSpec(Parcel parcel) {
        this.wishPromotionDeal = (WishPromotionBaseSpec) parcel.readParcelable(WishPromotionBaseSpec.class.getClassLoader());
    }

    public WishPromotionSpec(JSONObject jSONObject) throws JSONException, ParseException {
        super(jSONObject);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.wishPromotionDeal, i);
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) throws JSONException, ParseException {
        PromotionActionType fromInt = PromotionActionType.fromInt(jSONObject.getInt("promo_type"));
        if (fromInt == null) {
            fromInt = PromotionActionType.COUPON;
        }
        JSONObject jSONObject2 = jSONObject.getJSONObject("promo_spec");
        switch (fromInt) {
            case COUPON:
                this.wishPromotionDeal = new WishPromotionCouponSpec(jSONObject2);
                return;
            case NO_COUPON:
                this.wishPromotionDeal = new WishPromotionNoCouponSpec(jSONObject2);
                return;
            case ROTATING:
                this.wishPromotionDeal = new WishPromotionRotatingSpec(jSONObject2);
                return;
            case GIFT_PACK:
                this.wishPromotionDeal = new NewUserGiftPackSpec(jSONObject2);
                return;
            default:
                return;
        }
    }

    public WishPromotionBaseSpec getWishPromotionDeal() {
        return this.wishPromotionDeal;
    }
}
