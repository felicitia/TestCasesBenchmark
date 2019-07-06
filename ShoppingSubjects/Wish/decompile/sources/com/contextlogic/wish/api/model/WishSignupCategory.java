package com.contextlogic.wish.api.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.contextlogic.wish.util.JsonUtil;
import com.contextlogic.wish.util.JsonUtil.DataParser;
import java.text.ParseException;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

public class WishSignupCategory extends BaseModel implements Parcelable {
    public static final Creator<WishSignupCategory> CREATOR = new Creator<WishSignupCategory>() {
        public WishSignupCategory createFromParcel(Parcel parcel) {
            return new WishSignupCategory(parcel);
        }

        public WishSignupCategory[] newArray(int i) {
            return new WishSignupCategory[i];
        }
    };
    private boolean mAlreadyWishing;
    private String mId;
    private String mName;
    private ArrayList<WishImage> mPreviews;

    public int describeContents() {
        return 0;
    }

    public WishSignupCategory(JSONObject jSONObject) throws JSONException, ParseException {
        super(jSONObject);
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) throws JSONException {
        this.mPreviews = JsonUtil.parseArray(jSONObject, "previews", new DataParser<WishImage, String>() {
            public WishImage parseData(String str) throws JSONException {
                return new WishImage(str);
            }
        });
        this.mName = JsonUtil.optString(jSONObject, "name");
        this.mId = jSONObject.getString("id");
        this.mAlreadyWishing = jSONObject.optBoolean("alreadyWishing");
    }

    protected WishSignupCategory(Parcel parcel) {
        this.mAlreadyWishing = parcel.readByte() != 0;
        this.mPreviews = parcel.createTypedArrayList(WishImage.CREATOR);
        this.mId = parcel.readString();
        this.mName = parcel.readString();
    }

    public boolean isAlreadyWishing() {
        return this.mAlreadyWishing;
    }

    public void setAlreadyWishing(boolean z) {
        this.mAlreadyWishing = z;
    }

    public ArrayList<WishImage> getPreviews() {
        return this.mPreviews;
    }

    public String getId() {
        return this.mId;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte(this.mAlreadyWishing ? (byte) 1 : 0);
        parcel.writeTypedList(this.mPreviews);
        parcel.writeString(this.mId);
        parcel.writeString(this.mName);
    }
}
