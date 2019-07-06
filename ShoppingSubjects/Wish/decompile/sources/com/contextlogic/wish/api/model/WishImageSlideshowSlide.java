package com.contextlogic.wish.api.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.contextlogic.wish.util.JsonUtil;
import java.text.ParseException;
import org.json.JSONException;
import org.json.JSONObject;

public class WishImageSlideshowSlide extends BaseModel implements Parcelable {
    public static final Creator<WishImageSlideshowSlide> CREATOR = new Creator<WishImageSlideshowSlide>() {
        public WishImageSlideshowSlide createFromParcel(Parcel parcel) {
            return new WishImageSlideshowSlide(parcel);
        }

        public WishImageSlideshowSlide[] newArray(int i) {
            return new WishImageSlideshowSlide[i];
        }
    };
    private boolean mCropImage;
    private long mDuration;
    private WishImage mImage;
    private String mReviewText;
    private double mStarRating;
    private long mTransitionDuration;
    private TransitionType mTransitionType;
    private WishImage mUserImage;
    private String mUserName;

    public enum TransitionType implements Parcelable {
        None(0),
        SlideLeft(1),
        SlideRight(2),
        SlideDown(3),
        SlideUp(4),
        Fade(5),
        FlipLeft(6),
        FlipRight(7),
        FlipDown(8),
        FlipUp(9);
        
        public static final Creator<TransitionType> CREATOR = null;
        private int mValue;

        public int describeContents() {
            return 0;
        }

        static {
            CREATOR = new Creator<TransitionType>() {
                public TransitionType createFromParcel(Parcel parcel) {
                    return TransitionType.values()[parcel.readInt()];
                }

                public TransitionType[] newArray(int i) {
                    return new TransitionType[i];
                }
            };
        }

        private TransitionType(int i) {
            this.mValue = i;
        }

        public int getValue() {
            return this.mValue;
        }

        public static TransitionType parseValue(int i) {
            TransitionType[] values;
            for (TransitionType transitionType : values()) {
                if (transitionType.getValue() == i) {
                    return transitionType;
                }
            }
            return None;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(ordinal());
        }
    }

    public int describeContents() {
        return 0;
    }

    public WishImageSlideshowSlide(JSONObject jSONObject) throws JSONException, ParseException {
        super(jSONObject);
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) throws JSONException {
        this.mDuration = jSONObject.getLong("duration");
        this.mTransitionDuration = jSONObject.getLong("transition_duration");
        this.mUserName = JsonUtil.optString(jSONObject, "user_name");
        this.mReviewText = JsonUtil.optString(jSONObject, "review_text");
        this.mStarRating = jSONObject.optDouble("star_rating");
        this.mTransitionType = TransitionType.parseValue(jSONObject.optInt("transition_type", TransitionType.None.getValue()));
        this.mImage = new WishImage(jSONObject.getString("image_url"));
        if (JsonUtil.hasValue(jSONObject, "user_image_url")) {
            this.mUserImage = new WishImage(jSONObject.getString("user_image_url"));
        }
        this.mCropImage = jSONObject.optBoolean("crop_image", true);
    }

    protected WishImageSlideshowSlide(Parcel parcel) {
        this.mDuration = parcel.readLong();
        this.mTransitionDuration = parcel.readLong();
        this.mUserName = parcel.readString();
        this.mReviewText = parcel.readString();
        this.mStarRating = parcel.readDouble();
        this.mTransitionType = (TransitionType) parcel.readValue(TransitionType.class.getClassLoader());
        this.mImage = (WishImage) parcel.readParcelable(WishImage.class.getClassLoader());
        this.mUserImage = (WishImage) parcel.readParcelable(WishImage.class.getClassLoader());
        this.mCropImage = parcel.readByte() != 0;
    }

    public long getDuration() {
        return this.mDuration;
    }

    public long getTransitionDuration() {
        return this.mTransitionDuration;
    }

    public String getUserName() {
        return this.mUserName;
    }

    public String getReviewText() {
        return this.mReviewText;
    }

    public double getStarRating() {
        return this.mStarRating;
    }

    public TransitionType getTransitionType() {
        return this.mTransitionType;
    }

    public WishImage getImage() {
        return this.mImage;
    }

    public WishImage getUserImage() {
        return this.mUserImage;
    }

    public boolean getCropImage() {
        return this.mCropImage;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.mDuration);
        parcel.writeLong(this.mTransitionDuration);
        parcel.writeString(this.mUserName);
        parcel.writeString(this.mReviewText);
        parcel.writeDouble(this.mStarRating);
        parcel.writeValue(this.mTransitionType);
        parcel.writeParcelable(this.mImage, 0);
        parcel.writeParcelable(this.mUserImage, 0);
        parcel.writeByte(this.mCropImage ? (byte) 1 : 0);
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        WishImageSlideshowSlide wishImageSlideshowSlide = (WishImageSlideshowSlide) obj;
        if (this.mDuration != wishImageSlideshowSlide.mDuration || this.mTransitionDuration != wishImageSlideshowSlide.mTransitionDuration || Double.compare(wishImageSlideshowSlide.mStarRating, this.mStarRating) != 0 || this.mCropImage != wishImageSlideshowSlide.mCropImage || this.mTransitionType != wishImageSlideshowSlide.mTransitionType) {
            return false;
        }
        if (this.mUserName == null ? wishImageSlideshowSlide.mUserName != null : !this.mUserName.equals(wishImageSlideshowSlide.mUserName)) {
            return false;
        }
        if (this.mImage == null ? wishImageSlideshowSlide.mImage != null : !this.mImage.equals(wishImageSlideshowSlide.mImage)) {
            return false;
        }
        if (this.mUserImage == null ? wishImageSlideshowSlide.mUserImage != null : !this.mUserImage.equals(wishImageSlideshowSlide.mUserImage)) {
            return false;
        }
        if (this.mReviewText != null) {
            z = this.mReviewText.equals(wishImageSlideshowSlide.mReviewText);
        } else if (wishImageSlideshowSlide.mReviewText != null) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (((((((((((this.mTransitionType != null ? this.mTransitionType.hashCode() : 0) * 31) + ((int) (this.mDuration ^ (this.mDuration >>> 32)))) * 31) + ((int) (this.mTransitionDuration ^ (this.mTransitionDuration >>> 32)))) * 31) + (this.mUserName != null ? this.mUserName.hashCode() : 0)) * 31) + (this.mImage != null ? this.mImage.hashCode() : 0)) * 31) + (this.mUserImage != null ? this.mUserImage.hashCode() : 0)) * 31;
        if (this.mReviewText != null) {
            i = this.mReviewText.hashCode();
        }
        int i2 = hashCode + i;
        long doubleToLongBits = Double.doubleToLongBits(this.mStarRating);
        return (((i2 * 31) + ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32)))) * 31) + (this.mCropImage ? 1 : 0);
    }
}
