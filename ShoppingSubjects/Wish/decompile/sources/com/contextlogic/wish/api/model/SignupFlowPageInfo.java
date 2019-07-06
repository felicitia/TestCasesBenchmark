package com.contextlogic.wish.api.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.contextlogic.wish.util.JsonUtil;
import com.threatmetrix.TrustDefender.StrongAuth;
import java.text.ParseException;
import org.json.JSONException;
import org.json.JSONObject;

public class SignupFlowPageInfo extends BaseModel implements Parcelable {
    public static final Creator<SignupFlowPageInfo> CREATOR = new Creator<SignupFlowPageInfo>() {
        public SignupFlowPageInfo createFromParcel(Parcel parcel) {
            return new SignupFlowPageInfo(parcel);
        }

        public SignupFlowPageInfo[] newArray(int i) {
            return new SignupFlowPageInfo[i];
        }
    };
    private String mBody;
    private String mButton;
    private String mPage;
    private String mTitle;

    public int describeContents() {
        return 0;
    }

    public SignupFlowPageInfo(JSONObject jSONObject) throws JSONException, ParseException {
        super(jSONObject);
    }

    protected SignupFlowPageInfo(Parcel parcel) {
        this.mPage = parcel.readString();
        this.mTitle = parcel.readString();
        this.mBody = parcel.readString();
        this.mButton = parcel.readString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mPage);
        parcel.writeString(this.mTitle);
        parcel.writeString(this.mBody);
        parcel.writeString(this.mButton);
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) throws JSONException, ParseException {
        this.mPage = JsonUtil.optString(jSONObject, "page");
        this.mTitle = JsonUtil.optString(jSONObject, StrongAuth.AUTH_TITLE);
        this.mBody = JsonUtil.optString(jSONObject, "body");
        this.mButton = JsonUtil.optString(jSONObject, "button");
    }

    public String getTitleText() {
        return this.mTitle;
    }

    public String getBodyText() {
        return this.mBody;
    }

    public String getButtonText() {
        return this.mButton;
    }
}
