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

public class WishImageSlideshow extends BaseModel implements Parcelable {
    public static final Creator<WishImageSlideshow> CREATOR = new Creator<WishImageSlideshow>() {
        public WishImageSlideshow createFromParcel(Parcel parcel) {
            return new WishImageSlideshow(parcel);
        }

        public WishImageSlideshow[] newArray(int i) {
            return new WishImageSlideshow[i];
        }
    };
    private boolean mLoop;
    private ArrayList<WishImageSlideshowSlide> mSlides;
    private String mSlideshowId;

    public int describeContents() {
        return 0;
    }

    public WishImageSlideshow(JSONObject jSONObject) throws JSONException, ParseException {
        super(jSONObject);
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) throws JSONException {
        this.mSlideshowId = jSONObject.getString("id");
        this.mLoop = jSONObject.optBoolean("loop", true);
        this.mSlides = JsonUtil.parseArray(jSONObject, "slides", new DataParser<WishImageSlideshowSlide, JSONObject>() {
            public WishImageSlideshowSlide parseData(JSONObject jSONObject) throws JSONException, ParseException {
                return new WishImageSlideshowSlide(jSONObject);
            }
        });
    }

    protected WishImageSlideshow(Parcel parcel) {
        this.mSlideshowId = parcel.readString();
        this.mLoop = parcel.readByte() != 0;
        this.mSlides = parcel.createTypedArrayList(WishImageSlideshowSlide.CREATOR);
    }

    public boolean getLoop() {
        return this.mLoop;
    }

    public ArrayList<WishImageSlideshowSlide> getSlides() {
        return this.mSlides;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mSlideshowId);
        parcel.writeByte(this.mLoop ? (byte) 1 : 0);
        parcel.writeTypedList(this.mSlides);
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        WishImageSlideshow wishImageSlideshow = (WishImageSlideshow) obj;
        if (this.mLoop != wishImageSlideshow.mLoop) {
            return false;
        }
        if (this.mSlideshowId == null ? wishImageSlideshow.mSlideshowId != null : !this.mSlideshowId.equals(wishImageSlideshow.mSlideshowId)) {
            return false;
        }
        if (this.mSlides != null) {
            z = this.mSlides.equals(wishImageSlideshow.mSlides);
        } else if (wishImageSlideshow.mSlides != null) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (((this.mSlideshowId != null ? this.mSlideshowId.hashCode() : 0) * 31) + (this.mLoop ? 1 : 0)) * 31;
        if (this.mSlides != null) {
            i = this.mSlides.hashCode();
        }
        return hashCode + i;
    }
}
