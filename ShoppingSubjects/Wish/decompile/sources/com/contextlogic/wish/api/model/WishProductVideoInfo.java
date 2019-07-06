package com.contextlogic.wish.api.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.contextlogic.wish.util.JsonUtil;
import com.facebook.network.connectionclass.ConnectionClassManager;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import org.json.JSONException;
import org.json.JSONObject;

public class WishProductVideoInfo extends BaseModel implements Parcelable {
    public static final Creator<WishProductVideoInfo> CREATOR = new Creator<WishProductVideoInfo>() {
        public WishProductVideoInfo createFromParcel(Parcel parcel) {
            return new WishProductVideoInfo(parcel);
        }

        public WishProductVideoInfo[] newArray(int i) {
            return new WishProductVideoInfo[i];
        }
    };
    private double mAspectRatio;
    private String mBaseUrl;
    private String mLongUrl;
    private String mMediumUrl;
    private HashMap<String, HashMap<Integer, String>> mQualityMap;
    private String mShortUrl;

    public enum VideoLength {
        SHORT,
        MEDIUM,
        LONG
    }

    public int describeContents() {
        return 0;
    }

    public WishProductVideoInfo(String str) {
        this.mBaseUrl = str;
        this.mQualityMap = new HashMap<>();
    }

    public WishProductVideoInfo(JSONObject jSONObject) throws JSONException, ParseException {
        super(jSONObject);
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) throws JSONException, ParseException {
        this.mAspectRatio = jSONObject.optDouble("aspect_ratio", 1.0d);
        if (JsonUtil.hasValue(jSONObject, "ext_urls")) {
            parseVideoQuality(jSONObject.getJSONObject("ext_urls"));
        }
        this.mBaseUrl = jSONObject.getString("base_url");
        this.mShortUrl = JsonUtil.optString(jSONObject, "short_url");
        this.mMediumUrl = JsonUtil.optString(jSONObject, "medium_url");
        this.mLongUrl = JsonUtil.optString(jSONObject, "long_url");
    }

    protected WishProductVideoInfo(Parcel parcel) {
        this.mAspectRatio = parcel.readDouble();
        this.mBaseUrl = parcel.readString();
        this.mShortUrl = parcel.readString();
        this.mMediumUrl = parcel.readString();
        this.mLongUrl = parcel.readString();
        int readInt = parcel.readInt();
        this.mQualityMap = new HashMap<>();
        for (int i = 0; i < readInt; i++) {
            String readString = parcel.readString();
            int readInt2 = parcel.readInt();
            HashMap hashMap = new HashMap();
            for (int i2 = 0; i2 < readInt2; i2++) {
                hashMap.put(Integer.valueOf(parcel.readInt()), parcel.readString());
            }
            this.mQualityMap.put(readString, hashMap);
        }
    }

    public void parseVideoQuality(JSONObject jSONObject) throws JSONException, ParseException {
        this.mQualityMap = new HashMap<>();
        Iterator keys = jSONObject.keys();
        while (keys.hasNext()) {
            HashMap hashMap = new HashMap();
            String str = (String) keys.next();
            JSONObject jSONObject2 = jSONObject.getJSONObject(str);
            Iterator keys2 = jSONObject2.keys();
            while (keys2.hasNext()) {
                String str2 = (String) keys2.next();
                hashMap.put(Integer.valueOf(Integer.parseInt(str2)), jSONObject2.getString(str2));
            }
            this.mQualityMap.put(str, hashMap);
        }
    }

    public double getAspectRatio() {
        return this.mAspectRatio;
    }

    public String getUrlString(VideoLength videoLength) {
        String str;
        if (videoLength == VideoLength.LONG) {
            str = this.mLongUrl;
        } else if (videoLength == VideoLength.MEDIUM) {
            str = this.mMediumUrl;
        } else {
            str = this.mShortUrl;
        }
        if (str == null) {
            str = this.mBaseUrl;
        }
        String qualityAppendedVideoUrl = getQualityAppendedVideoUrl(str);
        if (qualityAppendedVideoUrl != null) {
            str = qualityAppendedVideoUrl;
        }
        return str.replace(" ", "%20");
    }

    private String getQualityAppendedVideoUrl(String str) {
        String str2 = null;
        if (this.mQualityMap.get(str) == null) {
            return null;
        }
        Double valueOf = Double.valueOf(ConnectionClassManager.getInstance().getDownloadKBitsPerSecond());
        for (Entry entry : new TreeMap((Map) this.mQualityMap.get(str)).entrySet()) {
            Integer num = (Integer) entry.getKey();
            String str3 = (String) entry.getValue();
            if (valueOf.doubleValue() > ((double) num.intValue())) {
                str2 = str3;
            }
        }
        return str2;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(this.mAspectRatio);
        parcel.writeString(this.mBaseUrl);
        parcel.writeString(this.mShortUrl);
        parcel.writeString(this.mMediumUrl);
        parcel.writeString(this.mLongUrl);
        parcel.writeInt(this.mQualityMap == null ? 0 : this.mQualityMap.size());
        if (this.mQualityMap != null) {
            for (Entry entry : this.mQualityMap.entrySet()) {
                parcel.writeString((String) entry.getKey());
                parcel.writeInt(((HashMap) entry.getValue()).size());
                for (Entry entry2 : ((HashMap) entry.getValue()).entrySet()) {
                    parcel.writeInt(((Integer) entry2.getKey()).intValue());
                    parcel.writeString((String) entry2.getValue());
                }
            }
        }
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        WishProductVideoInfo wishProductVideoInfo = (WishProductVideoInfo) obj;
        if (Double.compare(wishProductVideoInfo.mAspectRatio, this.mAspectRatio) != 0) {
            return false;
        }
        if (this.mBaseUrl == null ? wishProductVideoInfo.mBaseUrl != null : !this.mBaseUrl.equals(wishProductVideoInfo.mBaseUrl)) {
            return false;
        }
        if (this.mShortUrl == null ? wishProductVideoInfo.mShortUrl != null : !this.mShortUrl.equals(wishProductVideoInfo.mShortUrl)) {
            return false;
        }
        if (this.mMediumUrl == null ? wishProductVideoInfo.mMediumUrl != null : !this.mMediumUrl.equals(wishProductVideoInfo.mMediumUrl)) {
            return false;
        }
        if (this.mLongUrl == null ? wishProductVideoInfo.mLongUrl != null : !this.mLongUrl.equals(wishProductVideoInfo.mLongUrl)) {
            return false;
        }
        if (this.mQualityMap != null) {
            z = this.mQualityMap.equals(wishProductVideoInfo.mQualityMap);
        } else if (wishProductVideoInfo.mQualityMap != null) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        long doubleToLongBits = Double.doubleToLongBits(this.mAspectRatio);
        int i = 0;
        int hashCode = ((((((((((int) (doubleToLongBits ^ (doubleToLongBits >>> 32))) * 31) + (this.mBaseUrl != null ? this.mBaseUrl.hashCode() : 0)) * 31) + (this.mShortUrl != null ? this.mShortUrl.hashCode() : 0)) * 31) + (this.mMediumUrl != null ? this.mMediumUrl.hashCode() : 0)) * 31) + (this.mLongUrl != null ? this.mLongUrl.hashCode() : 0)) * 31;
        if (this.mQualityMap != null) {
            i = this.mQualityMap.hashCode();
        }
        return hashCode + i;
    }
}
