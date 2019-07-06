package com.contextlogic.wish.api.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.text.ParseException;
import org.json.JSONException;
import org.json.JSONObject;

public class WishSettingItem extends BaseModel implements Parcelable {
    public static final Creator<WishSettingItem> CREATOR = new Creator<WishSettingItem>() {
        public WishSettingItem createFromParcel(Parcel parcel) {
            return new WishSettingItem(parcel);
        }

        public WishSettingItem[] newArray(int i) {
            return new WishSettingItem[i];
        }
    };
    private String mAction;
    private String mName;

    public int describeContents() {
        return 0;
    }

    public WishSettingItem(JSONObject jSONObject) throws JSONException, ParseException {
        super(jSONObject);
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) throws JSONException {
        this.mAction = jSONObject.getString("action");
        this.mName = jSONObject.getString("name");
    }

    public JSONObject getJSONObject() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("name", this.mName);
            jSONObject.put("action", this.mAction);
        } catch (Throwable unused) {
        }
        return jSONObject;
    }

    protected WishSettingItem(Parcel parcel) {
        this.mAction = parcel.readString();
        this.mName = parcel.readString();
    }

    public String getName() {
        return this.mName;
    }

    public String action() {
        return this.mAction;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mAction);
        parcel.writeString(this.mName);
    }
}
