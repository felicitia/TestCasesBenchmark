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

public class WishUsersCurrentlyViewing extends BaseModel implements Parcelable {
    public static final Creator<WishUsersCurrentlyViewing> CREATOR = new Creator<WishUsersCurrentlyViewing>() {
        public WishUsersCurrentlyViewing createFromParcel(Parcel parcel) {
            return new WishUsersCurrentlyViewing(parcel);
        }

        public WishUsersCurrentlyViewing[] newArray(int i) {
            return new WishUsersCurrentlyViewing[i];
        }
    };
    private String mMessage;
    private ArrayList<String> mMessageList;
    private int mRefreshRate;

    public int describeContents() {
        return 0;
    }

    public WishUsersCurrentlyViewing(JSONObject jSONObject) throws JSONException, ParseException {
        super(jSONObject);
    }

    protected WishUsersCurrentlyViewing(Parcel parcel) {
        this.mMessage = parcel.readString();
        this.mRefreshRate = parcel.readInt();
        if (parcel.readByte() == 1) {
            this.mMessageList = new ArrayList<>();
            parcel.readList(this.mMessageList, String.class.getClassLoader());
            return;
        }
        this.mMessageList = null;
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) throws JSONException, ParseException {
        this.mMessage = jSONObject.getString("message");
        this.mRefreshRate = jSONObject.getInt("refresh_rate");
        this.mMessageList = JsonUtil.parseArray(jSONObject, "message_list", new DataParser<String, String>() {
            public String parseData(String str) throws JSONException, ParseException {
                return str;
            }
        });
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mMessage);
        parcel.writeInt(this.mRefreshRate);
        if (this.mMessageList == null) {
            parcel.writeByte(0);
            return;
        }
        parcel.writeByte(1);
        parcel.writeList(this.mMessageList);
    }

    public String getMessage() {
        return this.mMessage;
    }

    public long getRefreshRateMillis() {
        return (long) (this.mRefreshRate * 1000);
    }

    public ArrayList<String> getMessageList() {
        return this.mMessageList != null ? this.mMessageList : new ArrayList<>();
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        WishUsersCurrentlyViewing wishUsersCurrentlyViewing = (WishUsersCurrentlyViewing) obj;
        if (this.mRefreshRate != wishUsersCurrentlyViewing.mRefreshRate) {
            return false;
        }
        if (this.mMessage == null ? wishUsersCurrentlyViewing.mMessage != null : !this.mMessage.equals(wishUsersCurrentlyViewing.mMessage)) {
            return false;
        }
        if (this.mMessageList != null) {
            z = this.mMessageList.equals(wishUsersCurrentlyViewing.mMessageList);
        } else if (wishUsersCurrentlyViewing.mMessageList != null) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (((this.mMessage != null ? this.mMessage.hashCode() : 0) * 31) + this.mRefreshRate) * 31;
        if (this.mMessageList != null) {
            i = this.mMessageList.hashCode();
        }
        return hashCode + i;
    }
}
