package com.contextlogic.wish.api.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.text.ParseException;
import org.json.JSONException;
import org.json.JSONObject;

public class WishReloginInfo extends BaseModel implements Parcelable {
    public static final Creator<WishReloginInfo> CREATOR = new Creator<WishReloginInfo>() {
        public WishReloginInfo createFromParcel(Parcel parcel) {
            return new WishReloginInfo(parcel);
        }

        public WishReloginInfo[] newArray(int i) {
            return new WishReloginInfo[i];
        }
    };
    private String mEmail;
    private boolean mIsDeleted;
    private String mLoginMode;
    private String mProfilePicture;
    private String mUserId;
    private String mUserName;

    public enum LoginMode implements Parcelable {
        FACEBOOK(1),
        EMAIL(2),
        GOOGLE_PLUS(3);
        
        public static final Creator<LoginMode> CREATOR = null;
        private int mValue;

        public int describeContents() {
            return 0;
        }

        static {
            CREATOR = new Creator<LoginMode>() {
                public LoginMode createFromParcel(Parcel parcel) {
                    return LoginMode.values()[parcel.readInt()];
                }

                public LoginMode[] newArray(int i) {
                    return new LoginMode[i];
                }
            };
        }

        public static LoginMode fromInt(int i) {
            if (i == 1) {
                return FACEBOOK;
            }
            if (i == 2) {
                return EMAIL;
            }
            if (i == 3) {
                return GOOGLE_PLUS;
            }
            return null;
        }

        private LoginMode(int i) {
            this.mValue = i;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.mValue);
        }
    }

    public int describeContents() {
        return 0;
    }

    public WishReloginInfo(JSONObject jSONObject) throws JSONException, ParseException {
        super(jSONObject);
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) throws JSONException, ParseException {
        this.mUserId = jSONObject.optString("user_id");
        this.mUserName = jSONObject.optString("user_name");
        this.mProfilePicture = jSONObject.optString("profile_picture_small");
        this.mEmail = jSONObject.optString("email");
        this.mIsDeleted = jSONObject.optBoolean("is_deleted");
        LoginMode fromInt = LoginMode.fromInt(jSONObject.optInt("sign_up_method"));
        if (fromInt != null) {
            switch (fromInt) {
                case FACEBOOK:
                    this.mLoginMode = "LoginModeFB";
                    return;
                case EMAIL:
                    this.mLoginMode = "LoginModeEmail";
                    return;
                case GOOGLE_PLUS:
                    this.mLoginMode = "LoginModeGooglePlus";
                    return;
                default:
                    return;
            }
        }
    }

    public String getEmail() {
        return this.mEmail;
    }

    public String getProfilePicture() {
        return this.mProfilePicture;
    }

    public String getUserId() {
        return this.mUserId;
    }

    public String getUserName() {
        return this.mUserName;
    }

    public String getLoginMode() {
        return this.mLoginMode;
    }

    public boolean getIsDeleted() {
        return this.mIsDeleted;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte(this.mIsDeleted ? (byte) 1 : 0);
        parcel.writeString(this.mUserId);
        parcel.writeString(this.mUserName);
        parcel.writeString(this.mProfilePicture);
        parcel.writeString(this.mEmail);
        parcel.writeString(this.mLoginMode);
    }

    protected WishReloginInfo(Parcel parcel) {
        this.mIsDeleted = parcel.readByte() != 0;
        this.mUserId = parcel.readString();
        this.mUserName = parcel.readString();
        this.mProfilePicture = parcel.readString();
        this.mEmail = parcel.readString();
        this.mLoginMode = parcel.readString();
    }
}
