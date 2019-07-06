package com.contextlogic.wish.api.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.text.ParseException;
import org.json.JSONException;
import org.json.JSONObject;

public class WishKlarnaInfo extends BaseModel implements Parcelable {
    public static final Creator<WishKlarnaInfo> CREATOR = new Creator<WishKlarnaInfo>() {
        public WishKlarnaInfo createFromParcel(Parcel parcel) {
            return new WishKlarnaInfo(parcel);
        }

        public WishKlarnaInfo[] newArray(int i) {
            return new WishKlarnaInfo[i];
        }
    };
    private String mEmail;

    public int describeContents() {
        return 0;
    }

    public WishKlarnaInfo(JSONObject jSONObject) throws JSONException, ParseException {
        super(jSONObject);
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) throws JSONException {
        this.mEmail = jSONObject.getString("email");
    }

    protected WishKlarnaInfo(Parcel parcel) {
        parcel.readString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mEmail);
    }
}
