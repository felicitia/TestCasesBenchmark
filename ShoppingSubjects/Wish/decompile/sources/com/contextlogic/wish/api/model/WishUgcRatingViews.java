package com.contextlogic.wish.api.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.contextlogic.wish.util.JsonUtil;
import java.text.ParseException;
import org.json.JSONException;
import org.json.JSONObject;

public class WishUgcRatingViews extends BaseModel implements Parcelable {
    public static final Creator<WishUgcRatingViews> CREATOR = new Creator<WishUgcRatingViews>() {
        public WishUgcRatingViews createFromParcel(Parcel parcel) {
            return new WishUgcRatingViews(parcel);
        }

        public WishUgcRatingViews[] newArray(int i) {
            return new WishUgcRatingViews[i];
        }
    };
    private String mRatingComment;
    private WishImage mRatingImage;
    private String mRatingImageUrl;
    private String mRatingImageViews;
    private String mRatingViews;

    public int describeContents() {
        return 0;
    }

    public WishUgcRatingViews(JSONObject jSONObject) throws JSONException, ParseException {
        super(jSONObject);
    }

    protected WishUgcRatingViews(Parcel parcel) {
        this.mRatingComment = parcel.readString();
        this.mRatingImageUrl = parcel.readString();
        this.mRatingViews = parcel.readString();
        this.mRatingImageViews = parcel.readString();
        this.mRatingImage = (WishImage) parcel.readParcelable(WishImage.class.getClassLoader());
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) throws JSONException, ParseException {
        this.mRatingComment = JsonUtil.optString(jSONObject, "rating_comment");
        this.mRatingImageUrl = JsonUtil.optString(jSONObject, "rating_image_url");
        this.mRatingViews = JsonUtil.optString(jSONObject, "rating_views");
        this.mRatingImageViews = JsonUtil.optString(jSONObject, "rating_image_views");
        if (this.mRatingImageUrl != null) {
            this.mRatingImage = new WishImage(this.mRatingImageUrl);
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mRatingComment);
        parcel.writeString(this.mRatingImageUrl);
        parcel.writeString(this.mRatingViews);
        parcel.writeString(this.mRatingImageViews);
        parcel.writeParcelable(this.mRatingImage, 0);
    }
}
