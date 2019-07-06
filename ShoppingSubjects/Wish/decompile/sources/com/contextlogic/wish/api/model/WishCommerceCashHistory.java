package com.contextlogic.wish.api.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.contextlogic.wish.util.JsonUtil;
import com.contextlogic.wish.util.JsonUtil.DataParser;
import java.text.ParseException;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class WishCommerceCashHistory extends BaseModel implements Parcelable {
    public static final Creator<WishCommerceCashHistory> CREATOR = new Creator<WishCommerceCashHistory>() {
        public WishCommerceCashHistory createFromParcel(Parcel parcel) {
            return new WishCommerceCashHistory(parcel);
        }

        public WishCommerceCashHistory[] newArray(int i) {
            return new WishCommerceCashHistory[i];
        }
    };
    private List<WishCommerceCashEvent> mEvents;
    private int mNextOffset;
    private boolean mNoMoreItems;

    public int describeContents() {
        return 0;
    }

    public List<WishCommerceCashEvent> getEvents() {
        return this.mEvents;
    }

    public int getNextOffset() {
        return this.mNextOffset;
    }

    public boolean getHasNoMoreItems() {
        return this.mNoMoreItems;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(this.mEvents);
        parcel.writeInt(this.mNextOffset);
        parcel.writeByte(this.mNoMoreItems ? (byte) 1 : 0);
    }

    public WishCommerceCashHistory(JSONObject jSONObject) throws JSONException, ParseException {
        super(jSONObject);
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) throws JSONException, ParseException {
        this.mEvents = JsonUtil.parseArray(jSONObject, "cash_events", new DataParser<WishCommerceCashEvent, JSONObject>() {
            public WishCommerceCashEvent parseData(JSONObject jSONObject) throws JSONException, ParseException {
                return new WishCommerceCashEvent(jSONObject);
            }
        });
        this.mNextOffset = jSONObject.getInt("offset");
        this.mNoMoreItems = jSONObject.getBoolean("no_more_items");
    }

    private WishCommerceCashHistory(Parcel parcel) {
        this.mEvents = parcel.createTypedArrayList(WishCommerceCashEvent.CREATOR);
        this.mNextOffset = parcel.readInt();
        this.mNoMoreItems = parcel.readByte() != 0;
    }
}
