package com.contextlogic.wish.api.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.text.ParseException;
import org.json.JSONException;
import org.json.JSONObject;

public class WishLocalNotification extends BaseModel implements Parcelable {
    public static final Creator<WishLocalNotification> CREATOR = new Creator<WishLocalNotification>() {
        public WishLocalNotification createFromParcel(Parcel parcel) {
            return new WishLocalNotification(parcel);
        }

        public WishLocalNotification[] newArray(int i) {
            return new WishLocalNotification[i];
        }
    };
    private long mDelay;
    private String mId;
    private long mMinimumInterval;
    private String mTarget;
    private String mText;
    private String mType;

    public int describeContents() {
        return 0;
    }

    public WishLocalNotification(JSONObject jSONObject) throws JSONException, ParseException {
        super(jSONObject);
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) throws JSONException {
        this.mDelay = jSONObject.getLong("delay");
        this.mMinimumInterval = jSONObject.getLong("interval") * 1000;
        this.mId = jSONObject.getString("id");
        this.mTarget = jSONObject.getString("target");
        this.mText = jSONObject.getString("text");
        this.mType = jSONObject.getString("type");
    }

    protected WishLocalNotification(Parcel parcel) {
        this.mDelay = parcel.readLong();
        this.mMinimumInterval = parcel.readLong();
        this.mId = parcel.readString();
        this.mTarget = parcel.readString();
        this.mText = parcel.readString();
        this.mType = parcel.readString();
    }

    public long getDelay() {
        return this.mDelay;
    }

    public long getMinimumInterval() {
        return this.mMinimumInterval;
    }

    public String getId() {
        return this.mId;
    }

    public String getTarget() {
        return this.mTarget;
    }

    public String getText() {
        return this.mText;
    }

    public String getType() {
        return this.mType;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.mDelay);
        parcel.writeLong(this.mMinimumInterval);
        parcel.writeString(this.mId);
        parcel.writeString(this.mTarget);
        parcel.writeString(this.mText);
        parcel.writeString(this.mType);
    }
}
