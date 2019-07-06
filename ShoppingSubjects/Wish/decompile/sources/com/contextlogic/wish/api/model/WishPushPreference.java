package com.contextlogic.wish.api.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.text.ParseException;
import org.json.JSONException;
import org.json.JSONObject;

public class WishPushPreference extends BaseModel implements Parcelable {
    public static final Creator<WishPushPreference> CREATOR = new Creator<WishPushPreference>() {
        public WishPushPreference createFromParcel(Parcel parcel) {
            return new WishPushPreference(parcel);
        }

        public WishPushPreference[] newArray(int i) {
            return new WishPushPreference[i];
        }
    };
    private String mDescription;
    private int mIndex;
    private String mName;
    private boolean mPreferenceSelected;

    public int describeContents() {
        return 0;
    }

    public WishPushPreference(JSONObject jSONObject) throws JSONException, ParseException {
        super(jSONObject);
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) throws JSONException {
        this.mName = jSONObject.getString("name");
        this.mDescription = jSONObject.getString("description");
        this.mPreferenceSelected = jSONObject.getBoolean("value");
        this.mIndex = jSONObject.getInt("idx");
    }

    protected WishPushPreference(Parcel parcel) {
        this.mPreferenceSelected = parcel.readByte() != 0;
        this.mIndex = parcel.readInt();
        this.mName = parcel.readString();
        this.mDescription = parcel.readString();
    }

    public boolean isPreferenceSelected() {
        return this.mPreferenceSelected;
    }

    public void setPreferenceSelected(boolean z) {
        this.mPreferenceSelected = z;
    }

    public String getName() {
        return this.mName;
    }

    public int getIndex() {
        return this.mIndex;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte(this.mPreferenceSelected ? (byte) 1 : 0);
        parcel.writeInt(this.mIndex);
        parcel.writeString(this.mName);
        parcel.writeString(this.mDescription);
    }
}
