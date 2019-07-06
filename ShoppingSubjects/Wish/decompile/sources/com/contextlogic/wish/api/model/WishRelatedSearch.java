package com.contextlogic.wish.api.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.threatmetrix.TrustDefender.StrongAuth;
import java.text.ParseException;
import org.json.JSONException;
import org.json.JSONObject;

public class WishRelatedSearch extends BaseModel implements Parcelable {
    public static final Creator<WishRelatedSearch> CREATOR = new Creator<WishRelatedSearch>() {
        public WishRelatedSearch createFromParcel(Parcel parcel) {
            return new WishRelatedSearch(parcel);
        }

        public WishRelatedSearch[] newArray(int i) {
            return new WishRelatedSearch[i];
        }
    };
    private String mQuery;
    private String mTitle;

    public int describeContents() {
        return 0;
    }

    public WishRelatedSearch(JSONObject jSONObject) throws JSONException, ParseException {
        super(jSONObject);
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) throws JSONException {
        this.mQuery = jSONObject.getString("query");
        this.mTitle = jSONObject.getString(StrongAuth.AUTH_TITLE);
    }

    protected WishRelatedSearch(Parcel parcel) {
        this.mQuery = parcel.readString();
        this.mTitle = parcel.readString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mQuery);
        parcel.writeString(this.mTitle);
    }
}
