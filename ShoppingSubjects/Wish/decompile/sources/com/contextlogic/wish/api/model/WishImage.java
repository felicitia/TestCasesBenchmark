package com.contextlogic.wish.api.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.contextlogic.wish.social.facebook.FacebookManager;
import com.contextlogic.wish.social.facebook.FacebookManager.ProfileImageSize;
import com.contextlogic.wish.social.google.GoogleManager;
import com.contextlogic.wish.util.JsonUtil;
import java.text.ParseException;
import org.json.JSONException;
import org.json.JSONObject;

public class WishImage extends BaseModel implements Parcelable {
    public static final Creator<WishImage> CREATOR = new Creator<WishImage>() {
        public WishImage createFromParcel(Parcel parcel) {
            return new WishImage(parcel);
        }

        public WishImage[] newArray(int i) {
            return new WishImage[i];
        }
    };
    private String mBaseUrl;
    private String mCacheBuster;
    private String mLargeUrl;
    private String mMediumUrl;
    private String mOriginalUrl;
    private String mSmallUrl;

    public enum ImageSize {
        SMALL,
        MEDIUM,
        LARGE,
        ORIGINAL
    }

    public int describeContents() {
        return 0;
    }

    public WishImage(String str, String str2) {
        if (str != null) {
            this.mBaseUrl = FacebookManager.getProfileImageUrlString(str, ProfileImageSize.LARGE);
            this.mSmallUrl = FacebookManager.getProfileImageUrlString(str, ProfileImageSize.SMALL);
            this.mMediumUrl = FacebookManager.getProfileImageUrlString(str, ProfileImageSize.MEDIUM);
            this.mLargeUrl = this.mBaseUrl;
            this.mOriginalUrl = this.mLargeUrl;
        } else if (str2 != null) {
            this.mBaseUrl = GoogleManager.getProfileImageUrlString(str2, GoogleManager.ProfileImageSize.LARGE);
            this.mSmallUrl = GoogleManager.getProfileImageUrlString(str2, GoogleManager.ProfileImageSize.SMALL);
            this.mMediumUrl = GoogleManager.getProfileImageUrlString(str2, GoogleManager.ProfileImageSize.MEDIUM);
            this.mLargeUrl = this.mBaseUrl;
            this.mOriginalUrl = this.mLargeUrl;
        } else {
            this.mBaseUrl = "";
        }
    }

    public WishImage(String str) {
        this(str, null, null, null, null);
    }

    public WishImage(String str, String str2, String str3, String str4, String str5) {
        this.mBaseUrl = str;
        this.mSmallUrl = str2;
        this.mMediumUrl = str3;
        this.mLargeUrl = str4;
        this.mOriginalUrl = str5;
    }

    public WishImage(JSONObject jSONObject) throws JSONException, ParseException {
        super(jSONObject);
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) throws JSONException {
        this.mBaseUrl = JsonUtil.optString(jSONObject, "baseUrl");
        this.mSmallUrl = JsonUtil.optString(jSONObject, "smallUrl");
        this.mMediumUrl = JsonUtil.optString(jSONObject, "mediumUrl");
        this.mLargeUrl = JsonUtil.optString(jSONObject, "largeUrl");
        this.mOriginalUrl = JsonUtil.optString(jSONObject, "originalUrl");
        this.mCacheBuster = JsonUtil.optString(jSONObject, "cacheBuster");
    }

    protected WishImage(Parcel parcel) {
        this.mBaseUrl = parcel.readString();
        this.mSmallUrl = parcel.readString();
        this.mMediumUrl = parcel.readString();
        this.mLargeUrl = parcel.readString();
        this.mOriginalUrl = parcel.readString();
        this.mCacheBuster = parcel.readString();
    }

    public String getUrlString(ImageSize imageSize) {
        switch (imageSize) {
            case SMALL:
                if (this.mSmallUrl == null) {
                    this.mSmallUrl = resizeImageUrlString(this.mBaseUrl, imageSize);
                }
                return this.mSmallUrl;
            case MEDIUM:
                if (this.mMediumUrl == null) {
                    this.mMediumUrl = resizeImageUrlString(this.mBaseUrl, imageSize);
                }
                return this.mMediumUrl;
            case LARGE:
                if (this.mLargeUrl == null) {
                    this.mLargeUrl = resizeImageUrlString(this.mBaseUrl, imageSize);
                }
                return this.mLargeUrl;
            case ORIGINAL:
                if (this.mOriginalUrl == null) {
                    this.mOriginalUrl = resizeImageUrlString(this.mBaseUrl, imageSize);
                }
                return this.mOriginalUrl;
            default:
                return this.mBaseUrl;
        }
    }

    public String getBaseUrlString() {
        return this.mBaseUrl;
    }

    public String getCacheBuster() {
        return this.mCacheBuster;
    }

    public void setCacheBuster(String str) {
        this.mCacheBuster = str;
    }

    private String resizeImageUrlString(String str, ImageSize imageSize) {
        String str2;
        switch (imageSize) {
            case SMALL:
                str2 = "-small.jpg";
                break;
            case MEDIUM:
                str2 = "-medium.jpg";
                break;
            case LARGE:
                str2 = "-large.jpg";
                break;
            case ORIGINAL:
                str2 = "-original.jpg";
                break;
            default:
                str2 = null;
                break;
        }
        if (str.contains("-tiny.jpg")) {
            return str.replace("-tiny.jpg", str2);
        }
        if (str.contains("-small.jpg")) {
            return str.replace("-small.jpg", str2);
        }
        if (str.contains("-medium.jpg")) {
            return str.replace("-medium.jpg", str2);
        }
        if (str.contains("-large.jpg")) {
            return str.replace("-large.jpg", str2);
        }
        if (str.contains("-original.jpg")) {
            return str.replace("-original.jpg", str2);
        }
        return str.contains("-contest.jpg") ? str.replace("-contest.jpg", str2) : str;
    }

    public JSONObject getJSONObject() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("baseUrl", this.mBaseUrl);
            jSONObject.put("smallUrl", getUrlString(ImageSize.SMALL));
            jSONObject.put("mediumUrl", getUrlString(ImageSize.MEDIUM));
            jSONObject.put("largeUrl", getUrlString(ImageSize.LARGE));
            jSONObject.put("originalUrl", getUrlString(ImageSize.ORIGINAL));
            jSONObject.put("cacheBuster", this.mCacheBuster);
        } catch (Throwable unused) {
        }
        return jSONObject;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mBaseUrl);
        parcel.writeString(this.mSmallUrl);
        parcel.writeString(this.mMediumUrl);
        parcel.writeString(this.mLargeUrl);
        parcel.writeString(this.mOriginalUrl);
        parcel.writeString(this.mCacheBuster);
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        WishImage wishImage = (WishImage) obj;
        if (this.mBaseUrl == null ? wishImage.mBaseUrl != null : !this.mBaseUrl.equals(wishImage.mBaseUrl)) {
            return false;
        }
        if (this.mSmallUrl == null ? wishImage.mSmallUrl != null : !this.mSmallUrl.equals(wishImage.mSmallUrl)) {
            return false;
        }
        if (this.mMediumUrl == null ? wishImage.mMediumUrl != null : !this.mMediumUrl.equals(wishImage.mMediumUrl)) {
            return false;
        }
        if (this.mLargeUrl == null ? wishImage.mLargeUrl != null : !this.mLargeUrl.equals(wishImage.mLargeUrl)) {
            return false;
        }
        if (this.mOriginalUrl == null ? wishImage.mOriginalUrl != null : !this.mOriginalUrl.equals(wishImage.mOriginalUrl)) {
            return false;
        }
        if (this.mCacheBuster != null) {
            z = this.mCacheBuster.equals(wishImage.mCacheBuster);
        } else if (wishImage.mCacheBuster != null) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (((((((((this.mBaseUrl != null ? this.mBaseUrl.hashCode() : 0) * 31) + (this.mSmallUrl != null ? this.mSmallUrl.hashCode() : 0)) * 31) + (this.mMediumUrl != null ? this.mMediumUrl.hashCode() : 0)) * 31) + (this.mLargeUrl != null ? this.mLargeUrl.hashCode() : 0)) * 31) + (this.mOriginalUrl != null ? this.mOriginalUrl.hashCode() : 0)) * 31;
        if (this.mCacheBuster != null) {
            i = this.mCacheBuster.hashCode();
        }
        return hashCode + i;
    }
}
