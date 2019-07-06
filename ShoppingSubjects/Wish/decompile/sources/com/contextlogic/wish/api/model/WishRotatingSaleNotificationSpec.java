package com.contextlogic.wish.api.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.contextlogic.wish.util.JsonUtil;
import com.contextlogic.wish.util.JsonUtil.DataParser;
import com.threatmetrix.TrustDefender.StrongAuth;
import java.text.ParseException;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

public class WishRotatingSaleNotificationSpec extends BaseModel implements Parcelable {
    public static final Creator<WishRotatingSaleNotificationSpec> CREATOR = new Creator<WishRotatingSaleNotificationSpec>() {
        public WishRotatingSaleNotificationSpec createFromParcel(Parcel parcel) {
            return new WishRotatingSaleNotificationSpec(parcel);
        }

        public WishRotatingSaleNotificationSpec[] newArray(int i) {
            return new WishRotatingSaleNotificationSpec[i];
        }
    };
    private ArrayList<WishTag> mAllTags;
    private String mButtonText;
    private String mCancelText;
    private String mDialogSubtitle;
    private String mDialogTitle;
    private ArrayList<WishTag> mSelectedTags;

    public int describeContents() {
        return 0;
    }

    public WishRotatingSaleNotificationSpec(JSONObject jSONObject) throws JSONException, ParseException {
        super(jSONObject);
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) throws JSONException, ParseException {
        this.mDialogTitle = JsonUtil.optString(jSONObject, StrongAuth.AUTH_TITLE);
        this.mDialogSubtitle = JsonUtil.optString(jSONObject, "subtitle");
        this.mButtonText = JsonUtil.optString(jSONObject, "button_text");
        this.mCancelText = JsonUtil.optString(jSONObject, "cancel_text");
        this.mAllTags = JsonUtil.parseArray(jSONObject, "all_tags", new DataParser<WishTag, JSONObject>() {
            public WishTag parseData(JSONObject jSONObject) throws JSONException, ParseException {
                return new WishTag(jSONObject);
            }
        });
        if (JsonUtil.hasValue(jSONObject, "selected_tags")) {
            this.mSelectedTags = JsonUtil.parseArray(jSONObject, "selected_tags", new DataParser<WishTag, JSONObject>() {
                public WishTag parseData(JSONObject jSONObject) throws JSONException, ParseException {
                    return new WishTag(jSONObject);
                }
            });
        } else {
            this.mSelectedTags = new ArrayList<>();
        }
    }

    protected WishRotatingSaleNotificationSpec(Parcel parcel) {
        this.mDialogTitle = parcel.readString();
        this.mDialogSubtitle = parcel.readString();
        this.mButtonText = parcel.readString();
        this.mCancelText = parcel.readString();
        this.mAllTags = parcel.readArrayList(WishTag.class.getClassLoader());
        this.mSelectedTags = parcel.readArrayList(WishTag.class.getClassLoader());
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mDialogTitle);
        parcel.writeString(this.mDialogSubtitle);
        parcel.writeString(this.mButtonText);
        parcel.writeString(this.mCancelText);
        parcel.writeList(this.mAllTags);
        parcel.writeList(this.mSelectedTags);
    }

    public String getDialogTitle() {
        return this.mDialogTitle;
    }

    public String getDialogSubtitle() {
        return this.mDialogSubtitle;
    }

    public String getButtonText() {
        return this.mButtonText;
    }

    public String getCancelText() {
        return this.mCancelText;
    }

    public ArrayList<WishTag> getAllTags() {
        return this.mAllTags;
    }

    public ArrayList<WishTag> getSelectedTags() {
        return this.mSelectedTags;
    }
}
