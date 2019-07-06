package com.contextlogic.wish.api.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.contextlogic.wish.util.JsonUtil;
import com.google.firebase.appindexing.Action;
import com.google.firebase.appindexing.Indexable;
import com.google.firebase.appindexing.Indexable.Builder;
import com.google.firebase.appindexing.builders.Actions;
import com.threatmetrix.TrustDefender.StrongAuth;
import java.text.ParseException;
import org.json.JSONException;
import org.json.JSONObject;

public class WishGoogleAppIndexingData extends BaseModel implements Parcelable {
    public static final Creator<WishGoogleAppIndexingData> CREATOR = new Creator<WishGoogleAppIndexingData>() {
        public WishGoogleAppIndexingData createFromParcel(Parcel parcel) {
            return new WishGoogleAppIndexingData(parcel);
        }

        public WishGoogleAppIndexingData[] newArray(int i) {
            return new WishGoogleAppIndexingData[i];
        }
    };
    private String mTitle;
    private String mType;
    private String mUrl;

    public int describeContents() {
        return 0;
    }

    public WishGoogleAppIndexingData(JSONObject jSONObject) throws JSONException, ParseException {
        super(jSONObject);
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) throws JSONException {
        this.mUrl = jSONObject.getString("web_url");
        this.mTitle = jSONObject.getString(StrongAuth.AUTH_TITLE);
        this.mType = JsonUtil.optString(jSONObject, "type", null);
    }

    protected WishGoogleAppIndexingData(Parcel parcel) {
        this.mUrl = parcel.readString();
        this.mTitle = parcel.readString();
        this.mType = parcel.readString();
    }

    public String getUrl() {
        return this.mUrl;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public String getType() {
        return this.mType;
    }

    public Action toAppIndexAction() {
        return Actions.newView(getTitle(), getUrl());
    }

    public Indexable toAppIndexIndexable() {
        Builder builder;
        if (TextUtils.isEmpty(getType())) {
            builder = new Builder();
        } else {
            builder = new Builder(getType());
        }
        return ((Builder) ((Builder) builder.setName(getTitle())).setUrl(getUrl())).build();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mUrl);
        parcel.writeString(this.mTitle);
        parcel.writeString(this.mType);
    }
}
