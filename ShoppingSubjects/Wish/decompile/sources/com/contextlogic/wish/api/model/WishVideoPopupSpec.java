package com.contextlogic.wish.api.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.contextlogic.wish.util.JsonUtil;
import com.facebook.network.connectionclass.ConnectionClassManager;
import com.facebook.network.connectionclass.ConnectionQuality;
import java.text.ParseException;
import java.util.Map.Entry;
import java.util.TreeMap;
import org.json.JSONException;
import org.json.JSONObject;

public class WishVideoPopupSpec extends BaseModel implements Parcelable {
    public static final Creator<WishVideoPopupSpec> CREATOR = new Creator<WishVideoPopupSpec>() {
        public WishVideoPopupSpec createFromParcel(Parcel parcel) {
            return new WishVideoPopupSpec(parcel);
        }

        public WishVideoPopupSpec[] newArray(int i) {
            return new WishVideoPopupSpec[i];
        }
    };
    private static TreeMap<ConnectionQuality, String> QUALITY_MAP = createMap();
    private boolean mMuteAudio;
    private String mQualityAppendedVideoUrl;
    String mVideoResource;

    public int describeContents() {
        return 0;
    }

    WishVideoPopupSpec(Parcel parcel) {
        this.mMuteAudio = parcel.readByte() != 0;
        this.mVideoResource = parcel.readString();
        this.mQualityAppendedVideoUrl = parcel.readString();
    }

    WishVideoPopupSpec(JSONObject jSONObject) throws JSONException, ParseException {
        super(jSONObject);
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        WishVideoPopupSpec wishVideoPopupSpec = (WishVideoPopupSpec) obj;
        if (this.mMuteAudio != wishVideoPopupSpec.mMuteAudio) {
            return false;
        }
        if (this.mVideoResource == null ? wishVideoPopupSpec.mVideoResource != null : !this.mVideoResource.equals(wishVideoPopupSpec.mVideoResource)) {
            return false;
        }
        if (this.mQualityAppendedVideoUrl != null) {
            z = this.mQualityAppendedVideoUrl.equals(wishVideoPopupSpec.mQualityAppendedVideoUrl);
        } else if (wishVideoPopupSpec.mQualityAppendedVideoUrl != null) {
            z = false;
        }
        return z;
    }

    private static TreeMap<ConnectionQuality, String> createMap() {
        TreeMap<ConnectionQuality, String> treeMap = new TreeMap<>();
        treeMap.put(ConnectionQuality.UNKNOWN, "-240p");
        treeMap.put(ConnectionQuality.POOR, "-240p");
        treeMap.put(ConnectionQuality.MODERATE, "-360p");
        treeMap.put(ConnectionQuality.GOOD, "-480p");
        treeMap.put(ConnectionQuality.EXCELLENT, "-720p");
        return treeMap;
    }

    public String getVideoResource() {
        if (TextUtils.isEmpty(this.mVideoResource)) {
            return null;
        }
        if (this.mQualityAppendedVideoUrl != null) {
            return this.mQualityAppendedVideoUrl;
        }
        ConnectionQuality currentBandwidthQuality = ConnectionClassManager.getInstance().getCurrentBandwidthQuality();
        for (Entry entry : QUALITY_MAP.entrySet()) {
            ConnectionQuality connectionQuality = (ConnectionQuality) entry.getKey();
            String str = (String) entry.getValue();
            if (currentBandwidthQuality.equals(connectionQuality)) {
                StringBuilder sb = new StringBuilder();
                sb.append(this.mVideoResource);
                sb.append(str);
                sb.append(".mp4");
                this.mQualityAppendedVideoUrl = sb.toString();
            }
        }
        return this.mQualityAppendedVideoUrl;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte(this.mMuteAudio ? (byte) 1 : 0);
        parcel.writeString(this.mVideoResource);
        parcel.writeString(this.mQualityAppendedVideoUrl);
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) throws JSONException, ParseException {
        this.mVideoResource = JsonUtil.optString(jSONObject, "video_url");
        this.mMuteAudio = jSONObject.optBoolean("mute_audio");
    }

    public boolean shouldMuteAudio() {
        return this.mMuteAudio;
    }

    public boolean canDisplay() {
        return this.mVideoResource != null;
    }
}
