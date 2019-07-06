package com.contextlogic.wish.api.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.contextlogic.wish.api.model.WishCart.PaymentMode;
import com.contextlogic.wish.util.JsonUtil;
import com.contextlogic.wish.util.JsonUtil.DataParser;
import java.text.ParseException;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

public class WishDeclineRedirectInfo extends BaseModel implements Parcelable {
    public static final Creator<WishDeclineRedirectInfo> CREATOR = new Creator<WishDeclineRedirectInfo>() {
        public WishDeclineRedirectInfo createFromParcel(Parcel parcel) {
            return new WishDeclineRedirectInfo(parcel);
        }

        public WishDeclineRedirectInfo[] newArray(int i) {
            return new WishDeclineRedirectInfo[i];
        }
    };
    private ArrayList<String> mRedirectButtonTitles;
    private ArrayList<PaymentMode> mRedirectModes;
    private String mRedirectSubtitle;
    private String mRedirectTitle;

    public int describeContents() {
        return 0;
    }

    public String getRedirectTitle() {
        return this.mRedirectTitle;
    }

    public String getRedirectSubtitle() {
        return this.mRedirectSubtitle;
    }

    public ArrayList<PaymentMode> getRedirectModes() {
        return this.mRedirectModes;
    }

    public ArrayList<String> getRedirectButtonTitles() {
        return this.mRedirectButtonTitles;
    }

    public WishDeclineRedirectInfo(JSONObject jSONObject) throws JSONException, ParseException {
        super(jSONObject);
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) throws JSONException {
        if (jSONObject.has("redirect_payment_modes")) {
            this.mRedirectModes = JsonUtil.parseArray(jSONObject, "redirect_payment_modes", new DataParser<PaymentMode, Long>() {
                public PaymentMode parseData(Long l) throws JSONException {
                    return PaymentMode.valueOf(l.intValue());
                }
            });
            this.mRedirectButtonTitles = JsonUtil.parseArray(jSONObject, "redirect_button_titles", new DataParser<String, String>() {
                public String parseData(String str) {
                    return str;
                }
            });
        }
        this.mRedirectTitle = jSONObject.optString("redirect_title", null);
        this.mRedirectSubtitle = jSONObject.optString("redirect_subtitle", null);
    }

    protected WishDeclineRedirectInfo(Parcel parcel) {
        this.mRedirectModes = parcel.createTypedArrayList(PaymentMode.CREATOR);
        this.mRedirectButtonTitles = parcel.createStringArrayList();
        this.mRedirectTitle = parcel.readString();
        this.mRedirectSubtitle = parcel.readString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(this.mRedirectModes);
        parcel.writeStringList(this.mRedirectButtonTitles);
        parcel.writeString(this.mRedirectTitle);
        parcel.writeString(this.mRedirectSubtitle);
    }
}
