package com.contextlogic.wish.api.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.contextlogic.wish.api.datacenter.AuthenticationDataCenter;
import com.contextlogic.wish.application.ApplicationEventManager;
import com.contextlogic.wish.application.ApplicationEventManager.ApplicationEventBundle;
import com.contextlogic.wish.application.ApplicationEventManager.ApplicationEventCallback;
import com.contextlogic.wish.application.ApplicationEventManager.EventType;
import com.contextlogic.wish.util.DateUtil;
import com.contextlogic.wish.util.JsonUtil;
import com.contextlogic.wish.util.JsonUtil.DataParser;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import org.json.JSONException;
import org.json.JSONObject;

public class WishUser extends BaseModel implements Parcelable, ApplicationEventCallback {
    public static final Creator<WishUser> CREATOR = new Creator<WishUser>() {
        public WishUser createFromParcel(Parcel parcel) {
            return new WishUser(parcel);
        }

        public WishUser[] newArray(int i) {
            return new WishUser[i];
        }
    };
    private String mBdayBannerDeepLink;
    private Date mBirthday;
    private String mBirthdayMessage;
    private boolean mCanGift;
    private String mCountryCode;
    private String mEmail;
    private String mFbId;
    private Date mFetchTime;
    private String mFirstName;
    private ArrayList<WishUser> mFollowers;
    private int mFollowersCount;
    /* access modifiers changed from: private */
    public String mFollowersSetId;
    /* access modifiers changed from: private */
    public ArrayList<WishUser> mFollowing;
    private int mFollowingCount;
    /* access modifiers changed from: private */
    public String mFollowingSetId;
    private String mFriendJsLink;
    private String mGender;
    private String mGoogleId;
    private boolean mHasProfilePic;
    private boolean mIsAdmin;
    private boolean mIsFollowing;
    private boolean mIsPreview;
    private boolean mIsWishStar;
    /* access modifiers changed from: private */
    public String mName;
    /* access modifiers changed from: private */
    public ArrayList<WishUserProductBucket> mProductBuckets;
    private WishImage mProfileImage;
    private int mPublicWishesCount;
    /* access modifiers changed from: private */
    public ArrayList<WishTag> mTags;
    /* access modifiers changed from: private */
    public String mUserId;
    private WishUserState mUserState;
    private int mWishesCount;

    public enum WishUserState implements Parcelable {
        Registered,
        Unregistered,
        Unknown;
        
        public static final Creator<WishUserState> CREATOR = null;

        public int describeContents() {
            return 0;
        }

        static {
            CREATOR = new Creator<WishUserState>() {
                public WishUserState createFromParcel(Parcel parcel) {
                    return WishUserState.values()[parcel.readInt()];
                }

                public WishUserState[] newArray(int i) {
                    return new WishUserState[i];
                }
            };
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(ordinal());
        }
    }

    public int describeContents() {
        return 0;
    }

    public WishUser(JSONObject jSONObject) throws JSONException, ParseException {
        super(jSONObject);
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) throws JSONException {
        this.mProductBuckets = new ArrayList<>();
        this.mFollowing = new ArrayList<>();
        this.mFollowers = new ArrayList<>();
        this.mTags = new ArrayList<>();
        this.mFetchTime = new Date();
        JSONObject optJSONObject = jSONObject.optJSONObject("user");
        if (optJSONObject != null) {
            this.mIsPreview = false;
        } else {
            this.mIsPreview = true;
            optJSONObject = jSONObject;
        }
        if (optJSONObject.has("id")) {
            this.mUserId = optJSONObject.getString("id");
        } else if (optJSONObject.has("uid")) {
            this.mUserId = optJSONObject.getString("uid");
        } else {
            throw new JSONException("Missing user ID");
        }
        ApplicationEventManager.getInstance().addCallback(EventType.USER_FOLLOW, this.mUserId, this);
        ApplicationEventManager.getInstance().addCallback(EventType.USER_UNFOLLOW, this.mUserId, this);
        if (JsonUtil.hasValue(optJSONObject, "fb_uid")) {
            this.mFbId = String.valueOf(optJSONObject.get("fb_uid"));
        }
        if (JsonUtil.hasValue(optJSONObject, "google_plus_uid")) {
            this.mGoogleId = String.valueOf(optJSONObject.get("google_plus_uid"));
        }
        if (!optJSONObject.has("has_profile_pic") || !optJSONObject.getBoolean("has_profile_pic")) {
            this.mHasProfilePic = false;
        } else {
            this.mHasProfilePic = true;
        }
        if (this.mHasProfilePic) {
            String optString = JsonUtil.optString(optJSONObject, "profile_pic_small");
            String optString2 = JsonUtil.optString(optJSONObject, "profile_pic_medium");
            String optString3 = JsonUtil.optString(optJSONObject, "profile_pic_large");
            if (optString != null && optString2 != null && optString3 != null) {
                WishImage wishImage = new WishImage(optString3, optString, optString2, optString3, optString3);
                this.mProfileImage = wishImage;
            } else if (this.mFbId != null) {
                this.mProfileImage = new WishImage(this.mFbId, null);
            } else {
                this.mProfileImage = null;
            }
        } else {
            this.mProfileImage = null;
        }
        this.mIsAdmin = optJSONObject.optBoolean("is_admin", false);
        if (optJSONObject.has("country_code") && !optJSONObject.isNull("country_code")) {
            this.mCountryCode = optJSONObject.getString("country_code");
        }
        if (optJSONObject.has("birthday")) {
            try {
                this.mBirthday = DateUtil.parseIsoDate(optJSONObject.getString("birthday"), false);
            } catch (ParseException unused) {
            }
        }
        this.mName = optJSONObject.getString("name");
        if (optJSONObject.has("short_name")) {
            this.mFirstName = optJSONObject.getString("short_name");
        }
        if (jSONObject.has("birthday_banner")) {
            JSONObject optJSONObject2 = jSONObject.optJSONObject("birthday_banner");
            if (optJSONObject2 != null) {
                if (optJSONObject2.has("long_message")) {
                    this.mBirthdayMessage = optJSONObject2.getString("long_message");
                }
                if (optJSONObject2.has("deep_link")) {
                    this.mBdayBannerDeepLink = optJSONObject2.getString("deep_link");
                }
            }
        }
        this.mCanGift = optJSONObject.optBoolean("can_gift", false);
        this.mUserState = jSONObject.optBoolean("is_registered", true) ? WishUserState.Registered : WishUserState.Unregistered;
        this.mIsFollowing = jSONObject.optBoolean("is_following", false);
        this.mIsWishStar = optJSONObject.optBoolean("is_wish_star", false);
        if (!this.mIsPreview) {
            this.mWishesCount = jSONObject.getInt("num_wishes");
            this.mFollowersCount = jSONObject.getInt("num_followers");
            this.mFollowingCount = jSONObject.getInt("num_following");
            if (optJSONObject.has("friend_js")) {
                this.mFriendJsLink = optJSONObject.getString("friend_js");
            }
            if (optJSONObject.has("email")) {
                this.mEmail = optJSONObject.getString("email");
            }
            this.mGender = JsonUtil.optString(optJSONObject, "gender", "female");
            JsonUtil.parseArray(jSONObject, "bucket_data", new DataParser<Void, JSONObject>() {
                public Void parseData(JSONObject jSONObject) throws JSONException, ParseException {
                    String string = jSONObject.getString("type");
                    if (string.equals("product")) {
                        WishUserProductBucket wishUserProductBucket = new WishUserProductBucket(jSONObject);
                        wishUserProductBucket.setUserName(WishUser.this.mName);
                        wishUserProductBucket.setUserId(WishUser.this.mUserId);
                        if (wishUserProductBucket.getProductCount() > 0) {
                            WishUser.this.mProductBuckets.add(wishUserProductBucket);
                        }
                        if (wishUserProductBucket.getTag() != null) {
                            WishUser.this.mTags.add(wishUserProductBucket.getTag());
                        }
                    } else if (string.equals("user.following")) {
                        WishUser.this.mFollowingSetId = jSONObject.getString("set_id");
                        WishUser.this.mFollowing = WishUser.this.getFollowingUsers(jSONObject);
                    } else if (string.equals("user.followers")) {
                        WishUser.this.mFollowersSetId = jSONObject.getString("set_id");
                        WishUser.this.mFollowing = WishUser.this.getFollowingUsers(jSONObject);
                    }
                    return null;
                }
            });
            if (jSONObject.has("num_public_wishes")) {
                this.mPublicWishesCount = jSONObject.getInt("num_public_wishes");
            }
        }
    }

    protected WishUser(Parcel parcel) {
        this.mFollowersCount = parcel.readInt();
        this.mFollowingCount = parcel.readInt();
        this.mWishesCount = parcel.readInt();
        boolean z = false;
        this.mCanGift = parcel.readByte() != 0;
        this.mIsAdmin = parcel.readByte() != 0;
        this.mIsPreview = parcel.readByte() != 0;
        this.mIsFollowing = parcel.readByte() != 0;
        this.mHasProfilePic = parcel.readByte() != 0;
        if (parcel.readByte() != 0) {
            z = true;
        }
        this.mIsWishStar = z;
        this.mFollowers = parcel.createTypedArrayList(CREATOR);
        this.mFollowing = parcel.createTypedArrayList(CREATOR);
        this.mProductBuckets = parcel.createTypedArrayList(WishUserProductBucket.CREATOR);
        this.mTags = parcel.createTypedArrayList(WishTag.CREATOR);
        if (parcel.readByte() != 0) {
            this.mBirthday = new Date(parcel.readLong());
        }
        if (parcel.readByte() != 0) {
            this.mFetchTime = new Date(parcel.readLong());
        }
        this.mBdayBannerDeepLink = parcel.readString();
        this.mBirthdayMessage = parcel.readString();
        this.mCountryCode = parcel.readString();
        this.mEmail = parcel.readString();
        this.mFbId = parcel.readString();
        this.mFollowersSetId = parcel.readString();
        this.mFollowingSetId = parcel.readString();
        this.mFriendJsLink = parcel.readString();
        this.mGender = parcel.readString();
        this.mGoogleId = parcel.readString();
        this.mName = parcel.readString();
        this.mFirstName = parcel.readString();
        this.mUserId = parcel.readString();
        this.mProfileImage = (WishImage) parcel.readParcelable(WishImage.class.getClassLoader());
        this.mUserState = (WishUserState) parcel.readParcelable(WishUserState.class.getClassLoader());
        this.mPublicWishesCount = parcel.readInt();
    }

    public ArrayList<WishUser> getFollowingUsers(JSONObject jSONObject) {
        return JsonUtil.parseArray(jSONObject, "preview", new DataParser<WishUser, JSONObject>() {
            public WishUser parseData(JSONObject jSONObject) throws JSONException, ParseException {
                return new WishUser(jSONObject);
            }
        });
    }

    public WishUserState getUserState() {
        return this.mUserState;
    }

    public String getUserId() {
        return this.mUserId;
    }

    public String getFbId() {
        return this.mFbId;
    }

    public String getGoogleId() {
        return this.mGoogleId;
    }

    public String getFirstName() {
        if (this.mFirstName == null) {
            return getName();
        }
        return this.mFirstName;
    }

    public String getName() {
        return this.mName;
    }

    public String getCountryCode() {
        return this.mCountryCode;
    }

    public Date getBirthday() {
        return this.mBirthday;
    }

    public String getFollowingSetId() {
        return this.mFollowingSetId;
    }

    public WishImage getProfileImage() {
        return this.mProfileImage;
    }

    public String getFollowersSetId() {
        return this.mFollowersSetId;
    }

    public int getFollowersCount() {
        return this.mFollowersCount;
    }

    public int getFollowingCount() {
        return this.mFollowingCount;
    }

    public int getWishesCount() {
        return this.mWishesCount;
    }

    public boolean isPreview() {
        return this.mIsPreview;
    }

    public boolean isWishStar() {
        return this.mIsWishStar;
    }

    public boolean isFollowing() {
        return this.mIsFollowing;
    }

    public boolean isLoggedInUser() {
        String userId = AuthenticationDataCenter.getInstance().getUserId();
        return userId != null && userId.equals(this.mUserId);
    }

    public String getEmail() {
        return this.mEmail;
    }

    public String getGender() {
        return this.mGender;
    }

    public boolean isAdmin() {
        return this.mIsAdmin;
    }

    public void onApplicationEventReceived(EventType eventType, String str, ApplicationEventBundle applicationEventBundle) {
        if (str != null && str.equals(this.mUserId)) {
            if (eventType == EventType.USER_UNFOLLOW) {
                this.mIsFollowing = false;
            } else if (eventType == EventType.USER_FOLLOW) {
                this.mIsFollowing = true;
            }
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mFollowersCount);
        parcel.writeInt(this.mFollowingCount);
        parcel.writeInt(this.mWishesCount);
        parcel.writeByte(this.mCanGift ? (byte) 1 : 0);
        parcel.writeByte(this.mIsAdmin ? (byte) 1 : 0);
        parcel.writeByte(this.mIsPreview ? (byte) 1 : 0);
        parcel.writeByte(this.mIsFollowing ? (byte) 1 : 0);
        parcel.writeByte(this.mHasProfilePic ? (byte) 1 : 0);
        parcel.writeByte(this.mIsWishStar ? (byte) 1 : 0);
        parcel.writeTypedList(this.mFollowers);
        parcel.writeTypedList(this.mFollowing);
        parcel.writeTypedList(this.mProductBuckets);
        parcel.writeTypedList(this.mTags);
        int i2 = 1;
        parcel.writeByte((byte) (this.mBirthday != null ? 1 : 0));
        if (this.mBirthday != null) {
            parcel.writeLong(this.mBirthday.getTime());
        }
        if (this.mFetchTime == null) {
            i2 = 0;
        }
        parcel.writeByte((byte) i2);
        if (this.mFetchTime != null) {
            parcel.writeLong(this.mFetchTime.getTime());
        }
        parcel.writeString(this.mBdayBannerDeepLink);
        parcel.writeString(this.mBirthdayMessage);
        parcel.writeString(this.mCountryCode);
        parcel.writeString(this.mEmail);
        parcel.writeString(this.mFbId);
        parcel.writeString(this.mFollowersSetId);
        parcel.writeString(this.mFollowingSetId);
        parcel.writeString(this.mFriendJsLink);
        parcel.writeString(this.mGender);
        parcel.writeString(this.mGoogleId);
        parcel.writeString(this.mName);
        parcel.writeString(this.mFirstName);
        parcel.writeString(this.mUserId);
        parcel.writeParcelable(this.mProfileImage, 0);
        parcel.writeParcelable(this.mUserState, 0);
        parcel.writeInt(this.mPublicWishesCount);
    }

    public int getPublicWishesCount() {
        return this.mPublicWishesCount;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        WishUser wishUser = (WishUser) obj;
        if (this.mFollowersCount != wishUser.mFollowersCount || this.mFollowingCount != wishUser.mFollowingCount || this.mWishesCount != wishUser.mWishesCount || this.mPublicWishesCount != wishUser.mPublicWishesCount || this.mCanGift != wishUser.mCanGift || this.mIsAdmin != wishUser.mIsAdmin || this.mIsPreview != wishUser.mIsPreview || this.mIsFollowing != wishUser.mIsFollowing || this.mHasProfilePic != wishUser.mHasProfilePic || this.mIsWishStar != wishUser.mIsWishStar) {
            return false;
        }
        if (this.mFollowers == null ? wishUser.mFollowers != null : !this.mFollowers.equals(wishUser.mFollowers)) {
            return false;
        }
        if (this.mFollowing == null ? wishUser.mFollowing != null : !this.mFollowing.equals(wishUser.mFollowing)) {
            return false;
        }
        if (this.mProductBuckets == null ? wishUser.mProductBuckets != null : !this.mProductBuckets.equals(wishUser.mProductBuckets)) {
            return false;
        }
        if (this.mTags == null ? wishUser.mTags != null : !this.mTags.equals(wishUser.mTags)) {
            return false;
        }
        if (this.mBirthday == null ? wishUser.mBirthday != null : !this.mBirthday.equals(wishUser.mBirthday)) {
            return false;
        }
        if (this.mFetchTime == null ? wishUser.mFetchTime != null : !this.mFetchTime.equals(wishUser.mFetchTime)) {
            return false;
        }
        if (this.mBdayBannerDeepLink == null ? wishUser.mBdayBannerDeepLink != null : !this.mBdayBannerDeepLink.equals(wishUser.mBdayBannerDeepLink)) {
            return false;
        }
        if (this.mBirthdayMessage == null ? wishUser.mBirthdayMessage != null : !this.mBirthdayMessage.equals(wishUser.mBirthdayMessage)) {
            return false;
        }
        if (this.mCountryCode == null ? wishUser.mCountryCode != null : !this.mCountryCode.equals(wishUser.mCountryCode)) {
            return false;
        }
        if (this.mEmail == null ? wishUser.mEmail != null : !this.mEmail.equals(wishUser.mEmail)) {
            return false;
        }
        if (this.mFbId == null ? wishUser.mFbId != null : !this.mFbId.equals(wishUser.mFbId)) {
            return false;
        }
        if (this.mFollowersSetId == null ? wishUser.mFollowersSetId != null : !this.mFollowersSetId.equals(wishUser.mFollowersSetId)) {
            return false;
        }
        if (this.mFollowingSetId == null ? wishUser.mFollowingSetId != null : !this.mFollowingSetId.equals(wishUser.mFollowingSetId)) {
            return false;
        }
        if (this.mFriendJsLink == null ? wishUser.mFriendJsLink != null : !this.mFriendJsLink.equals(wishUser.mFriendJsLink)) {
            return false;
        }
        if (this.mGender == null ? wishUser.mGender != null : !this.mGender.equals(wishUser.mGender)) {
            return false;
        }
        if (this.mGoogleId == null ? wishUser.mGoogleId != null : !this.mGoogleId.equals(wishUser.mGoogleId)) {
            return false;
        }
        if (this.mName == null ? wishUser.mName != null : !this.mName.equals(wishUser.mName)) {
            return false;
        }
        if (this.mFirstName == null ? wishUser.mFirstName != null : !this.mFirstName.equals(wishUser.mFirstName)) {
            return false;
        }
        if (this.mUserId == null ? wishUser.mUserId != null : !this.mUserId.equals(wishUser.mUserId)) {
            return false;
        }
        if (this.mProfileImage == null ? wishUser.mProfileImage != null : !this.mProfileImage.equals(wishUser.mProfileImage)) {
            return false;
        }
        if (this.mUserState != wishUser.mUserState) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((((((((((((((((((((((((((((((((((((((((((((((((((((((this.mFollowersCount * 31) + this.mFollowingCount) * 31) + this.mWishesCount) * 31) + this.mPublicWishesCount) * 31) + (this.mCanGift ? 1 : 0)) * 31) + (this.mIsAdmin ? 1 : 0)) * 31) + (this.mIsPreview ? 1 : 0)) * 31) + (this.mIsFollowing ? 1 : 0)) * 31) + (this.mHasProfilePic ? 1 : 0)) * 31) + (this.mIsWishStar ? 1 : 0)) * 31) + (this.mFollowers != null ? this.mFollowers.hashCode() : 0)) * 31) + (this.mFollowing != null ? this.mFollowing.hashCode() : 0)) * 31) + (this.mProductBuckets != null ? this.mProductBuckets.hashCode() : 0)) * 31) + (this.mTags != null ? this.mTags.hashCode() : 0)) * 31) + (this.mBirthday != null ? this.mBirthday.hashCode() : 0)) * 31) + (this.mFetchTime != null ? this.mFetchTime.hashCode() : 0)) * 31) + (this.mBdayBannerDeepLink != null ? this.mBdayBannerDeepLink.hashCode() : 0)) * 31) + (this.mBirthdayMessage != null ? this.mBirthdayMessage.hashCode() : 0)) * 31) + (this.mCountryCode != null ? this.mCountryCode.hashCode() : 0)) * 31) + (this.mEmail != null ? this.mEmail.hashCode() : 0)) * 31) + (this.mFbId != null ? this.mFbId.hashCode() : 0)) * 31) + (this.mFollowersSetId != null ? this.mFollowersSetId.hashCode() : 0)) * 31) + (this.mFollowingSetId != null ? this.mFollowingSetId.hashCode() : 0)) * 31) + (this.mFriendJsLink != null ? this.mFriendJsLink.hashCode() : 0)) * 31) + (this.mGender != null ? this.mGender.hashCode() : 0)) * 31) + (this.mGoogleId != null ? this.mGoogleId.hashCode() : 0)) * 31) + (this.mName != null ? this.mName.hashCode() : 0)) * 31) + (this.mFirstName != null ? this.mFirstName.hashCode() : 0)) * 31) + (this.mUserId != null ? this.mUserId.hashCode() : 0)) * 31) + (this.mProfileImage != null ? this.mProfileImage.hashCode() : 0)) * 31;
        if (this.mUserState != null) {
            i = this.mUserState.hashCode();
        }
        return hashCode + i;
    }
}
