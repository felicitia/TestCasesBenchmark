package com.contextlogic.wish.api.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.contextlogic.wish.util.JsonUtil;
import java.text.ParseException;
import org.json.JSONException;
import org.json.JSONObject;

public class WishPriceWatchChangeInfo extends BaseModel {
    public static final Creator<WishPriceWatchChangeInfo> CREATOR = new Creator<WishPriceWatchChangeInfo>() {
        public WishPriceWatchChangeInfo createFromParcel(Parcel parcel) {
            return new WishPriceWatchChangeInfo(parcel);
        }

        public WishPriceWatchChangeInfo[] newArray(int i) {
            return new WishPriceWatchChangeInfo[i];
        }
    };
    private String mBody;
    private boolean mShowLink;
    private String mTitle;

    public int describeContents() {
        return 0;
    }

    public WishPriceWatchChangeInfo(JSONObject jSONObject) throws JSONException, ParseException {
        super(jSONObject);
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) throws JSONException, ParseException {
        this.mTitle = JsonUtil.optString(jSONObject, "title_text");
        this.mBody = JsonUtil.optString(jSONObject, "sub_text");
        this.mShowLink = jSONObject.optBoolean("show_price_watch_link", false);
    }

    public String getTitle() {
        return this.mTitle;
    }

    public String getBody() {
        return this.mBody;
    }

    public boolean showLink() {
        return this.mShowLink;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mTitle);
        parcel.writeString(this.mBody);
        parcel.writeByte(this.mShowLink ? (byte) 1 : 0);
    }

    protected WishPriceWatchChangeInfo(Parcel parcel) {
        this.mTitle = parcel.readString();
        this.mBody = parcel.readString();
        this.mShowLink = parcel.readByte() != 0;
    }
}
