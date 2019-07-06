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

public class WishCollection extends BaseModel implements Parcelable {
    public static final Creator<WishCollection> CREATOR = new Creator<WishCollection>() {
        public WishCollection createFromParcel(Parcel parcel) {
            return new WishCollection(parcel);
        }

        public WishCollection[] newArray(int i) {
            return new WishCollection[i];
        }
    };
    private String mActionUrl;
    private String mId;
    private ArrayList<WishImage> mPreviewImages;

    public int describeContents() {
        return 0;
    }

    public WishCollection(JSONObject jSONObject) throws JSONException, ParseException {
        super(jSONObject);
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) throws JSONException {
        this.mPreviewImages = JsonUtil.parseArray(jSONObject, "preview_images", new DataParser<WishImage, String>() {
            public WishImage parseData(String str) throws JSONException {
                return new WishImage(str);
            }
        });
        this.mId = jSONObject.getString("id");
        this.mActionUrl = jSONObject.getString("action_url");
    }

    protected WishCollection(Parcel parcel) {
        this.mPreviewImages = parcel.createTypedArrayList(WishImage.CREATOR);
        this.mId = parcel.readString();
        this.mActionUrl = parcel.readString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(this.mPreviewImages);
        parcel.writeString(this.mId);
        parcel.writeString(this.mActionUrl);
    }
}
