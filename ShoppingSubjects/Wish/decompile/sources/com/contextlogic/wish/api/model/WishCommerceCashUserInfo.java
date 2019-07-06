package com.contextlogic.wish.api.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.contextlogic.wish.util.JsonUtil;
import java.text.ParseException;
import org.json.JSONException;
import org.json.JSONObject;

public class WishCommerceCashUserInfo extends BaseModel implements Parcelable {
    public static final Creator<WishCommerceCashUserInfo> CREATOR = new Creator<WishCommerceCashUserInfo>() {
        public WishCommerceCashUserInfo createFromParcel(Parcel parcel) {
            return new WishCommerceCashUserInfo(parcel);
        }

        public WishCommerceCashUserInfo[] newArray(int i) {
            return new WishCommerceCashUserInfo[i];
        }
    };
    private WishLocalizedCurrencyValue mBalance;
    private boolean mScreenSeen;

    public int describeContents() {
        return 0;
    }

    public WishLocalizedCurrencyValue getBalance() {
        return this.mBalance;
    }

    public boolean getScreenSeen() {
        return this.mScreenSeen;
    }

    public WishCommerceCashUserInfo(JSONObject jSONObject) throws JSONException, ParseException {
        super(jSONObject);
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) throws JSONException, ParseException {
        if (JsonUtil.hasValue(jSONObject, "amount") && JsonUtil.hasValue(jSONObject, "currency_code")) {
            this.mBalance = new WishLocalizedCurrencyValue(jSONObject.optDouble("amount"), jSONObject.optJSONObject("localized_amount"));
        }
        if (JsonUtil.hasValue(jSONObject, "screen_seen")) {
            this.mScreenSeen = jSONObject.optBoolean("screen_seen");
        }
    }

    public JSONObject toJSON() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("amount", this.mBalance);
        jSONObject.put("screen_seen", this.mScreenSeen);
        return jSONObject;
    }

    protected WishCommerceCashUserInfo(Parcel parcel) {
        this.mBalance = (WishLocalizedCurrencyValue) parcel.readParcelable(WishLocalizedCurrencyValue.class.getClassLoader());
        this.mScreenSeen = parcel.readByte() != 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.mBalance, 0);
        parcel.writeByte(this.mScreenSeen ? (byte) 1 : 0);
    }
}
