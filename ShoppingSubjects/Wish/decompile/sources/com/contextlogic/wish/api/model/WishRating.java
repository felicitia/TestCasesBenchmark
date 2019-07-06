package com.contextlogic.wish.api.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.contextlogic.wish.util.DateUtil;
import com.contextlogic.wish.util.JsonUtil;
import java.text.ParseException;
import java.util.Date;
import org.json.JSONException;
import org.json.JSONObject;

public class WishRating extends BaseModel implements Parcelable {
    public static final Creator<WishRating> CREATOR = new Creator<WishRating>() {
        public WishRating createFromParcel(Parcel parcel) {
            return new WishRating(parcel);
        }

        public WishRating[] newArray(int i) {
            return new WishRating[i];
        }
    };
    private WishUser mAuthor;
    private String mComment;
    private String mImageLargeUrlString;
    private String mImageThumbnailUrlString;
    private boolean mIsSyndicated;
    private int mNumUpvotes;
    private String mProductId;
    private String mProductImageUrlString;
    private String mProductName;
    private int mRating;
    private String mRatingId;
    private String mSyndicatedText;
    private WishImage mThumbnailImage;
    private Date mTimestamp;
    private boolean mUserUpvoted;
    private WishProductVideoInfo mVideoInfo;
    private String mVideoThumbnailUrlString;

    public int describeContents() {
        return 0;
    }

    public WishRating(JSONObject jSONObject) throws JSONException, ParseException {
        super(jSONObject);
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) throws JSONException, ParseException {
        this.mRatingId = jSONObject.getString("id");
        if (JsonUtil.hasValue(jSONObject, "user")) {
            this.mAuthor = new WishUser(jSONObject.getJSONObject("user"));
        }
        this.mTimestamp = DateUtil.parseIsoDate(jSONObject.getString("time"));
        this.mRating = jSONObject.getInt("rating");
        this.mComment = JsonUtil.optString(jSONObject, "comment");
        this.mNumUpvotes = jSONObject.optInt("upvote_count", 0);
        this.mUserUpvoted = jSONObject.optBoolean("user_upvoted", false);
        this.mImageLargeUrlString = JsonUtil.optString(jSONObject, "image_large_url");
        if (JsonUtil.hasValue(jSONObject, "image_thumbnail_url")) {
            this.mImageThumbnailUrlString = JsonUtil.optString(jSONObject, "image_thumbnail_url");
            this.mThumbnailImage = new WishImage(this.mImageLargeUrlString);
        }
        if (JsonUtil.hasValue(jSONObject, "video_info")) {
            JSONObject jSONObject2 = jSONObject.getJSONObject("video_info");
            this.mVideoThumbnailUrlString = JsonUtil.optString(jSONObject2, "thumbnail_url");
            this.mVideoInfo = new WishProductVideoInfo(jSONObject2);
        }
        this.mProductName = JsonUtil.optString(jSONObject, "product_name");
        this.mProductImageUrlString = JsonUtil.optString(jSONObject, "product_display_picture_url");
        this.mProductId = JsonUtil.optString(jSONObject, "product_id");
        this.mSyndicatedText = JsonUtil.optString(jSONObject, "syndicated_text");
        this.mIsSyndicated = jSONObject.optBoolean("is_syndicated", false);
    }

    protected WishRating(Parcel parcel) {
        this.mRating = parcel.readInt();
        if (parcel.readByte() != 0) {
            this.mTimestamp = new Date(parcel.readLong());
        }
        this.mComment = parcel.readString();
        this.mImageLargeUrlString = parcel.readString();
        this.mImageThumbnailUrlString = parcel.readString();
        this.mVideoThumbnailUrlString = parcel.readString();
        this.mRatingId = parcel.readString();
        boolean z = false;
        this.mUserUpvoted = parcel.readByte() != 0;
        this.mNumUpvotes = parcel.readInt();
        this.mProductName = parcel.readString();
        this.mProductImageUrlString = parcel.readString();
        this.mProductId = parcel.readString();
        this.mAuthor = (WishUser) parcel.readParcelable(WishUser.class.getClassLoader());
        this.mSyndicatedText = parcel.readString();
        if (parcel.readByte() != 0) {
            z = true;
        }
        this.mIsSyndicated = z;
    }

    public Date getTimestamp() {
        return this.mTimestamp;
    }

    public WishUser getAuthor() {
        return this.mAuthor;
    }

    public int getRating() {
        return this.mRating;
    }

    public String getComment() {
        return this.mComment;
    }

    public String getImageLargeUrlString() {
        return this.mImageLargeUrlString;
    }

    public String getImageThumbnailUrlString() {
        return this.mImageThumbnailUrlString;
    }

    public String getVideoThumbnailUrlString() {
        return this.mVideoThumbnailUrlString;
    }

    public WishImage getThumbnailImage() {
        return this.mThumbnailImage;
    }

    public String getSyndicatedText() {
        return this.mSyndicatedText;
    }

    public boolean isSyndicated() {
        return this.mIsSyndicated;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mRating);
        parcel.writeByte((byte) (this.mTimestamp != null ? 1 : 0));
        if (this.mTimestamp != null) {
            parcel.writeLong(this.mTimestamp.getTime());
        }
        parcel.writeString(this.mComment);
        parcel.writeString(this.mImageLargeUrlString);
        parcel.writeString(this.mImageThumbnailUrlString);
        parcel.writeString(this.mVideoThumbnailUrlString);
        parcel.writeString(this.mRatingId);
        parcel.writeByte(this.mUserUpvoted ? (byte) 1 : 0);
        parcel.writeInt(this.mNumUpvotes);
        parcel.writeString(this.mProductName);
        parcel.writeString(this.mProductImageUrlString);
        parcel.writeString(this.mProductId);
        parcel.writeParcelable(this.mAuthor, 0);
        parcel.writeString(this.mSyndicatedText);
        parcel.writeByte(this.mIsSyndicated ? (byte) 1 : 0);
    }

    public int getNumUpvotes() {
        return this.mNumUpvotes;
    }

    public void setNumUpvotes(int i) {
        this.mNumUpvotes = i;
    }

    public boolean hasUserUpvoted() {
        return this.mUserUpvoted;
    }

    public void setUserUpvoted(boolean z) {
        this.mUserUpvoted = z;
    }

    public String getRatingId() {
        return this.mRatingId;
    }

    public String getProductName() {
        return this.mProductName;
    }

    public String getProductImageUrlString() {
        return this.mProductImageUrlString;
    }

    public String getProductId() {
        return this.mProductId;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        WishRating wishRating = (WishRating) obj;
        if (this.mRating != wishRating.mRating || this.mNumUpvotes != wishRating.mNumUpvotes || this.mUserUpvoted != wishRating.mUserUpvoted || this.mIsSyndicated != wishRating.mIsSyndicated) {
            return false;
        }
        if (this.mTimestamp == null ? wishRating.mTimestamp != null : !this.mTimestamp.equals(wishRating.mTimestamp)) {
            return false;
        }
        if (this.mComment == null ? wishRating.mComment != null : !this.mComment.equals(wishRating.mComment)) {
            return false;
        }
        if (this.mImageLargeUrlString == null ? wishRating.mImageLargeUrlString != null : !this.mImageLargeUrlString.equals(wishRating.mImageLargeUrlString)) {
            return false;
        }
        if (this.mImageThumbnailUrlString == null ? wishRating.mImageThumbnailUrlString != null : !this.mImageThumbnailUrlString.equals(wishRating.mImageThumbnailUrlString)) {
            return false;
        }
        if (this.mVideoThumbnailUrlString == null ? wishRating.mVideoThumbnailUrlString != null : !this.mVideoThumbnailUrlString.equals(wishRating.mVideoThumbnailUrlString)) {
            return false;
        }
        if (this.mRatingId == null ? wishRating.mRatingId != null : !this.mRatingId.equals(wishRating.mRatingId)) {
            return false;
        }
        if (this.mAuthor == null ? wishRating.mAuthor != null : !this.mAuthor.equals(wishRating.mAuthor)) {
            return false;
        }
        if (this.mProductName == null ? wishRating.mProductName != null : !this.mProductName.equals(wishRating.mProductName)) {
            return false;
        }
        if (this.mProductImageUrlString == null ? wishRating.mProductImageUrlString != null : !this.mProductImageUrlString.equals(wishRating.mProductImageUrlString)) {
            return false;
        }
        if (this.mProductId == null ? wishRating.mProductId != null : !this.mProductId.equals(wishRating.mProductId)) {
            return false;
        }
        if (this.mSyndicatedText != null) {
            z = this.mSyndicatedText.equals(wishRating.mSyndicatedText);
        } else if (wishRating.mSyndicatedText != null) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((((((((((((((((((((((this.mRating * 31) + (this.mTimestamp != null ? this.mTimestamp.hashCode() : 0)) * 31) + (this.mComment != null ? this.mComment.hashCode() : 0)) * 31) + (this.mImageLargeUrlString != null ? this.mImageLargeUrlString.hashCode() : 0)) * 31) + (this.mImageThumbnailUrlString != null ? this.mImageThumbnailUrlString.hashCode() : 0)) * 31) + (this.mVideoThumbnailUrlString != null ? this.mVideoThumbnailUrlString.hashCode() : 0)) * 31) + (this.mRatingId != null ? this.mRatingId.hashCode() : 0)) * 31) + (this.mAuthor != null ? this.mAuthor.hashCode() : 0)) * 31) + this.mNumUpvotes) * 31) + (this.mUserUpvoted ? 1 : 0)) * 31) + (this.mProductName != null ? this.mProductName.hashCode() : 0)) * 31) + (this.mProductImageUrlString != null ? this.mProductImageUrlString.hashCode() : 0)) * 31) + (this.mProductId != null ? this.mProductId.hashCode() : 0)) * 31) + (this.mIsSyndicated ? 1 : 0)) * 31;
        if (this.mSyndicatedText != null) {
            i = this.mSyndicatedText.hashCode();
        }
        return hashCode + i;
    }

    public WishProductExtraImage getExtraImage() {
        if (this.mImageLargeUrlString == null) {
            return null;
        }
        WishProductExtraImage wishProductExtraImage = new WishProductExtraImage(new WishImage(this.mImageLargeUrlString));
        wishProductExtraImage.setIsUgc(true);
        wishProductExtraImage.setRating(this.mRating);
        wishProductExtraImage.setRatingId(this.mRatingId);
        wishProductExtraImage.setTimestamp(this.mTimestamp);
        wishProductExtraImage.setVideoThumbnail(this.mImageThumbnailUrlString);
        wishProductExtraImage.setComment(getComment());
        wishProductExtraImage.setUploader(this.mAuthor);
        wishProductExtraImage.setHasUserUpvoted(this.mUserUpvoted);
        wishProductExtraImage.setUpvoteCount(this.mNumUpvotes);
        return wishProductExtraImage;
    }

    public WishProductExtraImage getExtraVideo() {
        if (this.mVideoInfo == null) {
            return null;
        }
        WishProductExtraImage wishProductExtraImage = new WishProductExtraImage(this.mVideoInfo);
        wishProductExtraImage.setIsUgc(true);
        wishProductExtraImage.setRating(this.mRating);
        wishProductExtraImage.setRatingId(this.mRatingId);
        wishProductExtraImage.setTimestamp(this.mTimestamp);
        wishProductExtraImage.setVideoThumbnail(this.mVideoThumbnailUrlString);
        wishProductExtraImage.setComment(getComment());
        wishProductExtraImage.setUploader(this.mAuthor);
        wishProductExtraImage.setHasUserUpvoted(this.mUserUpvoted);
        wishProductExtraImage.setUpvoteCount(this.mNumUpvotes);
        return wishProductExtraImage;
    }
}
