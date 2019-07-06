package com.contextlogic.wish.api.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.contextlogic.wish.R;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.util.DateUtil;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class WishProductExtraImage extends BaseModel implements Parcelable {
    public static final Creator<WishProductExtraImage> CREATOR = new Creator<WishProductExtraImage>() {
        public WishProductExtraImage createFromParcel(Parcel parcel) {
            return new WishProductExtraImage(parcel);
        }

        public WishProductExtraImage[] newArray(int i) {
            return new WishProductExtraImage[i];
        }
    };
    private String mComment;
    private boolean mHasUserUpvoted;
    private WishImage mImage;
    private boolean mIsUgc;
    private int mRating;
    private String mRatingId;
    private int mSequenceId;
    private String mSize;
    private WishImageSlideshow mSlideshow;
    private SourceType mSourceType;
    private Date mTimestamp;
    private WishUser mUploader;
    private int mUserUpvoteCount;
    private WishProductVideoInfo mVideoInfo;
    private WishImage mVideoThumbnail;

    public enum SourceType {
        Image(0),
        Video(1),
        Slideshow(2);
        
        private static Map<Integer, SourceType> map;
        private int value;

        static {
            int i;
            SourceType[] values;
            map = new HashMap();
            for (SourceType sourceType : values()) {
                map.put(Integer.valueOf(sourceType.getValue()), sourceType);
            }
        }

        private SourceType(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }

        public static SourceType valueOf(int i) {
            return (SourceType) map.get(Integer.valueOf(i));
        }
    }

    public int describeContents() {
        return 0;
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) {
    }

    public WishProductExtraImage(WishImage wishImage) {
        this.mSourceType = SourceType.Image;
        this.mSequenceId = -1;
        this.mImage = wishImage;
        this.mIsUgc = false;
    }

    public WishProductExtraImage(WishProductVideoInfo wishProductVideoInfo) {
        this.mSourceType = SourceType.Image;
        this.mSequenceId = -1;
        this.mSourceType = SourceType.Video;
        this.mVideoInfo = wishProductVideoInfo;
        this.mIsUgc = false;
    }

    public WishProductExtraImage(int i, WishProductVideoInfo wishProductVideoInfo) {
        this.mSourceType = SourceType.Image;
        this.mSequenceId = i;
        this.mSourceType = SourceType.Video;
        this.mVideoInfo = wishProductVideoInfo;
        this.mIsUgc = false;
    }

    public WishProductExtraImage(WishImageSlideshow wishImageSlideshow) {
        this.mSourceType = SourceType.Image;
        this.mSequenceId = -1;
        this.mSourceType = SourceType.Slideshow;
        this.mSlideshow = wishImageSlideshow;
        this.mIsUgc = false;
    }

    public WishProductExtraImage(int i, String str, String str2, boolean z) {
        this.mSourceType = SourceType.Image;
        this.mSequenceId = i;
        this.mIsUgc = false;
        if (z) {
            this.mSourceType = SourceType.Video;
            this.mVideoInfo = new WishProductVideoInfo(str);
            return;
        }
        this.mImage = new WishImage(str);
        this.mImage.setCacheBuster(str2);
    }

    protected WishProductExtraImage(Parcel parcel) {
        this.mSourceType = SourceType.Image;
        this.mSourceType = SourceType.valueOf(parcel.readInt());
        this.mSequenceId = parcel.readInt();
        this.mSize = parcel.readString();
        this.mImage = (WishImage) parcel.readParcelable(WishImage.class.getClassLoader());
        this.mUploader = (WishUser) parcel.readParcelable(WishUser.class.getClassLoader());
        this.mVideoInfo = (WishProductVideoInfo) parcel.readParcelable(WishProductVideoInfo.class.getClassLoader());
        this.mSlideshow = (WishImageSlideshow) parcel.readParcelable(WishImageSlideshow.class.getClassLoader());
        if (parcel.readByte() != 0) {
            this.mTimestamp = new Date(parcel.readLong());
        }
        this.mRating = parcel.readInt();
        this.mComment = parcel.readString();
        boolean z = false;
        this.mIsUgc = parcel.readByte() != 0;
        this.mUserUpvoteCount = parcel.readInt();
        if (parcel.readByte() != 0) {
            z = true;
        }
        this.mHasUserUpvoted = z;
        this.mRatingId = parcel.readString();
        this.mVideoThumbnail = (WishImage) parcel.readParcelable(WishImage.class.getClassLoader());
    }

    public SourceType getSourceType() {
        return this.mSourceType;
    }

    public void setIsUgc(boolean z) {
        this.mIsUgc = z;
    }

    public boolean isUgc() {
        return this.mIsUgc;
    }

    public int getSequenceId() {
        return this.mSequenceId;
    }

    public WishImage getImage() {
        return this.mImage;
    }

    public WishUser getUploader() {
        return this.mUploader;
    }

    public String getFormattedTimestamp() {
        return DateUtil.getFuzzyDateFromNow(this.mTimestamp);
    }

    public String getComment() {
        return this.mComment;
    }

    public WishProductVideoInfo getVideoInfo() {
        return this.mVideoInfo;
    }

    public WishImageSlideshow getSlideshow() {
        return this.mSlideshow;
    }

    public void setUploader(WishUser wishUser) {
        this.mUploader = wishUser;
    }

    public void setSize(String str) {
        this.mSize = str;
    }

    public String getSize() {
        return this.mSize;
    }

    public void setTimestamp(Date date) {
        this.mTimestamp = date;
    }

    public void setRating(int i) {
        this.mRating = i;
    }

    public void setComment(String str) {
        this.mComment = str;
    }

    public void setRatingId(String str) {
        this.mRatingId = str;
    }

    public String getRatingId() {
        return this.mRatingId;
    }

    public void setUpvoteCount(int i) {
        this.mUserUpvoteCount = i;
    }

    public int getUserUpvoteCount() {
        return this.mUserUpvoteCount;
    }

    public String getUpvoteString() {
        if (this.mUserUpvoteCount == 0) {
            return "";
        }
        return WishApplication.getInstance().getResources().getQuantityString(R.plurals.number_said_thanks, this.mUserUpvoteCount, new Object[]{Integer.valueOf(this.mUserUpvoteCount)});
    }

    public void setHasUserUpvoted(boolean z) {
        this.mHasUserUpvoted = z;
    }

    public boolean hasUserUpvoted() {
        return this.mHasUserUpvoted;
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x002a A[Catch:{ Exception -> 0x002f, all -> 0x0024, Throwable -> 0x0035 }] */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0031 A[Catch:{ Exception -> 0x002f, all -> 0x0024, Throwable -> 0x0035 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.graphics.Bitmap getGeneratedThumbnail() {
        /*
            r4 = this;
            com.contextlogic.wish.api.model.WishProductExtraImage$SourceType r0 = r4.mSourceType
            com.contextlogic.wish.api.model.WishProductExtraImage$SourceType r1 = com.contextlogic.wish.api.model.WishProductExtraImage.SourceType.Video
            r2 = 0
            if (r0 != r1) goto L_0x0038
            android.media.MediaMetadataRetriever r0 = new android.media.MediaMetadataRetriever     // Catch:{ Exception -> 0x002e, all -> 0x0026 }
            r0.<init>()     // Catch:{ Exception -> 0x002e, all -> 0x0026 }
            com.contextlogic.wish.api.model.WishProductVideoInfo r1 = r4.mVideoInfo     // Catch:{ Exception -> 0x002f, all -> 0x0024 }
            java.lang.String r1 = r1.getUrlString(r2)     // Catch:{ Exception -> 0x002f, all -> 0x0024 }
            java.util.HashMap r3 = new java.util.HashMap     // Catch:{ Exception -> 0x002f, all -> 0x0024 }
            r3.<init>()     // Catch:{ Exception -> 0x002f, all -> 0x0024 }
            r0.setDataSource(r1, r3)     // Catch:{ Exception -> 0x002f, all -> 0x0024 }
            android.graphics.Bitmap r1 = r0.getFrameAtTime()     // Catch:{ Exception -> 0x002f, all -> 0x0024 }
            if (r0 == 0) goto L_0x0037
            r0.release()     // Catch:{ Throwable -> 0x0035 }
            goto L_0x0037
        L_0x0024:
            r1 = move-exception
            goto L_0x0028
        L_0x0026:
            r1 = move-exception
            r0 = r2
        L_0x0028:
            if (r0 == 0) goto L_0x002d
            r0.release()     // Catch:{ Throwable -> 0x0035 }
        L_0x002d:
            throw r1     // Catch:{ Throwable -> 0x0035 }
        L_0x002e:
            r0 = r2
        L_0x002f:
            if (r0 == 0) goto L_0x0036
            r0.release()     // Catch:{ Throwable -> 0x0035 }
            goto L_0x0036
        L_0x0035:
            return r2
        L_0x0036:
            r1 = r2
        L_0x0037:
            return r1
        L_0x0038:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.contextlogic.wish.api.model.WishProductExtraImage.getGeneratedThumbnail():android.graphics.Bitmap");
    }

    public WishImage getThumbnail() {
        if (this.mSourceType == SourceType.Image) {
            return this.mImage;
        }
        if (this.mSourceType == SourceType.Video) {
            return this.mVideoThumbnail;
        }
        return null;
    }

    public void setVideoThumbnail(String str) {
        this.mVideoThumbnail = new WishImage(str);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mSourceType.getValue());
        parcel.writeInt(this.mSequenceId);
        parcel.writeString(this.mSize);
        parcel.writeParcelable(this.mImage, 0);
        parcel.writeParcelable(this.mUploader, 0);
        parcel.writeParcelable(this.mVideoInfo, 0);
        parcel.writeParcelable(this.mSlideshow, 0);
        parcel.writeByte((byte) (this.mTimestamp != null ? 1 : 0));
        if (this.mTimestamp != null) {
            parcel.writeLong(this.mTimestamp.getTime());
        }
        parcel.writeInt(this.mRating);
        parcel.writeString(this.mComment);
        parcel.writeByte(this.mIsUgc ? (byte) 1 : 0);
        parcel.writeInt(this.mUserUpvoteCount);
        parcel.writeByte(this.mHasUserUpvoted ? (byte) 1 : 0);
        parcel.writeString(this.mRatingId);
        parcel.writeParcelable(this.mVideoThumbnail, 0);
    }

    public void setVideoInfoQuality(JSONObject jSONObject) throws JSONException, ParseException {
        this.mVideoInfo.parseVideoQuality(jSONObject);
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        WishProductExtraImage wishProductExtraImage = (WishProductExtraImage) obj;
        if (this.mSequenceId != wishProductExtraImage.mSequenceId || this.mRating != wishProductExtraImage.mRating || this.mIsUgc != wishProductExtraImage.mIsUgc || this.mUserUpvoteCount != wishProductExtraImage.mUserUpvoteCount || this.mHasUserUpvoted != wishProductExtraImage.mHasUserUpvoted || this.mSourceType != wishProductExtraImage.mSourceType) {
            return false;
        }
        if (this.mSize == null ? wishProductExtraImage.mSize != null : !this.mSize.equals(wishProductExtraImage.mSize)) {
            return false;
        }
        if (this.mImage == null ? wishProductExtraImage.mImage != null : !this.mImage.equals(wishProductExtraImage.mImage)) {
            return false;
        }
        if (this.mVideoThumbnail == null ? wishProductExtraImage.mVideoThumbnail != null : !this.mVideoThumbnail.equals(wishProductExtraImage.mVideoThumbnail)) {
            return false;
        }
        if (this.mUploader == null ? wishProductExtraImage.mUploader != null : !this.mUploader.equals(wishProductExtraImage.mUploader)) {
            return false;
        }
        if (this.mTimestamp == null ? wishProductExtraImage.mTimestamp != null : !this.mTimestamp.equals(wishProductExtraImage.mTimestamp)) {
            return false;
        }
        if (this.mComment == null ? wishProductExtraImage.mComment != null : !this.mComment.equals(wishProductExtraImage.mComment)) {
            return false;
        }
        if (this.mRatingId == null ? wishProductExtraImage.mRatingId != null : !this.mRatingId.equals(wishProductExtraImage.mRatingId)) {
            return false;
        }
        if (this.mVideoInfo == null ? wishProductExtraImage.mVideoInfo != null : !this.mVideoInfo.equals(wishProductExtraImage.mVideoInfo)) {
            return false;
        }
        if (this.mSlideshow != null) {
            z = this.mSlideshow.equals(wishProductExtraImage.mSlideshow);
        } else if (wishProductExtraImage.mSlideshow != null) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (((((((((((((((((((((((((((this.mSourceType != null ? this.mSourceType.hashCode() : 0) * 31) + this.mSequenceId) * 31) + (this.mSize != null ? this.mSize.hashCode() : 0)) * 31) + (this.mImage != null ? this.mImage.hashCode() : 0)) * 31) + (this.mVideoThumbnail != null ? this.mVideoThumbnail.hashCode() : 0)) * 31) + (this.mUploader != null ? this.mUploader.hashCode() : 0)) * 31) + (this.mTimestamp != null ? this.mTimestamp.hashCode() : 0)) * 31) + this.mRating) * 31) + (this.mComment != null ? this.mComment.hashCode() : 0)) * 31) + (this.mIsUgc ? 1 : 0)) * 31) + this.mUserUpvoteCount) * 31) + (this.mHasUserUpvoted ? 1 : 0)) * 31) + (this.mRatingId != null ? this.mRatingId.hashCode() : 0)) * 31) + (this.mVideoInfo != null ? this.mVideoInfo.hashCode() : 0)) * 31;
        if (this.mSlideshow != null) {
            i = this.mSlideshow.hashCode();
        }
        return hashCode + i;
    }
}
