package com.contextlogic.wish.api.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.contextlogic.wish.util.JsonUtil;
import java.text.ParseException;
import org.json.JSONException;
import org.json.JSONObject;

public class WishPartnerProductDetailInfo extends BaseModel implements Parcelable {
    public static final Creator<WishPartnerProductDetailInfo> CREATOR = new Creator<WishPartnerProductDetailInfo>() {
        public WishPartnerProductDetailInfo createFromParcel(Parcel parcel) {
            return new WishPartnerProductDetailInfo(parcel);
        }

        public WishPartnerProductDetailInfo[] newArray(int i) {
            return new WishPartnerProductDetailInfo[i];
        }
    };
    private String mActionText;
    private String mBody;
    private String mLegalHighlightText;
    private String mLegalText;
    private String mLegalTextLink;
    private String mTitle;

    public int describeContents() {
        return 0;
    }

    public WishPartnerProductDetailInfo(JSONObject jSONObject) throws JSONException, ParseException {
        super(jSONObject);
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) throws JSONException {
        this.mTitle = JsonUtil.optString(jSONObject, "title_text");
        this.mBody = JsonUtil.optString(jSONObject, "body_text");
        this.mActionText = JsonUtil.optString(jSONObject, "action_text");
        this.mLegalText = JsonUtil.optString(jSONObject, "legal_text");
        this.mLegalHighlightText = JsonUtil.optString(jSONObject, "legal_highlight_text");
        this.mLegalTextLink = JsonUtil.optString(jSONObject, "legal_highlight_link");
    }

    public String getTitle() {
        return this.mTitle;
    }

    public String getLegalText() {
        return this.mLegalText;
    }

    public String getLegalHighlightText() {
        return this.mLegalHighlightText;
    }

    public String getLegalTextLink() {
        return this.mLegalTextLink;
    }

    public String getBody() {
        return this.mBody;
    }

    public String getActionText() {
        return this.mActionText;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mTitle);
        parcel.writeString(this.mBody);
        parcel.writeString(this.mActionText);
        parcel.writeString(this.mLegalText);
        parcel.writeString(this.mLegalHighlightText);
        parcel.writeString(this.mLegalTextLink);
    }

    protected WishPartnerProductDetailInfo(Parcel parcel) {
        this.mTitle = parcel.readString();
        this.mBody = parcel.readString();
        this.mActionText = parcel.readString();
        this.mLegalText = parcel.readString();
        this.mLegalHighlightText = parcel.readString();
        this.mLegalTextLink = parcel.readString();
    }
}
