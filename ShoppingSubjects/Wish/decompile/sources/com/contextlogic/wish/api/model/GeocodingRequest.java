package com.contextlogic.wish.api.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import java.text.ParseException;
import org.json.JSONException;
import org.json.JSONObject;

public class GeocodingRequest extends BaseModel {
    public static final Creator<GeocodingRequest> CREATOR = new Creator<GeocodingRequest>() {
        public GeocodingRequest createFromParcel(Parcel parcel) {
            return new GeocodingRequest(parcel);
        }

        public GeocodingRequest[] newArray(int i) {
            return new GeocodingRequest[i];
        }
    };
    private String mHash;
    private int mSource;
    private String mUrl;

    public int describeContents() {
        return 0;
    }

    public GeocodingRequest(JSONObject jSONObject) throws JSONException, ParseException {
        super(jSONObject);
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) throws JSONException, ParseException {
        this.mUrl = jSONObject.getString("url");
        this.mHash = jSONObject.getString("hash");
        this.mSource = jSONObject.getInt("source");
    }

    public String getUrl() {
        return this.mUrl;
    }

    public String getHash() {
        return this.mHash;
    }

    public int getSource() {
        return this.mSource;
    }

    public JSONObject getJSONObject() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("url", this.mUrl);
            jSONObject.put("hash", this.mHash);
            jSONObject.put("source", this.mSource);
            return jSONObject;
        } catch (JSONException unused) {
            return null;
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mUrl);
        parcel.writeString(this.mHash);
        parcel.writeInt(this.mSource);
    }

    protected GeocodingRequest(Parcel parcel) {
        this.mUrl = parcel.readString();
        this.mHash = parcel.readString();
        this.mSource = parcel.readInt();
    }
}
