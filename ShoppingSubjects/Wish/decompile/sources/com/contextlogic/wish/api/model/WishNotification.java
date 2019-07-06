package com.contextlogic.wish.api.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.contextlogic.wish.R;
import com.contextlogic.wish.api.model.WishImage.ImageSize;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.util.DateUtil;
import com.contextlogic.wish.util.JsonUtil;
import com.contextlogic.wish.util.JsonUtil.DataParser;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map.Entry;
import org.json.JSONException;
import org.json.JSONObject;

public class WishNotification extends BaseModel implements Parcelable {
    public static final Creator<WishNotification> CREATOR = new Creator<WishNotification>() {
        public WishNotification createFromParcel(Parcel parcel) {
            return new WishNotification(parcel);
        }

        public WishNotification[] newArray(int i) {
            return new WishNotification[i];
        }
    };
    private String mActionButtonText;
    private int mBucketNumber;
    private NotificationDisplayType mDisplayType;
    private ArrayList<String> mExtraImages;
    private HashMap<String, String> mExtraInfo;
    private NotificationIcon mIcon;
    private WishImage mImage;
    private boolean mIsClicked;
    private boolean mIsNew;
    private boolean mIsReward;
    private boolean mIsViewed;
    private String mMessage;
    private int mNotificationNumber;
    private Object mTarget;
    private NotificationTargetType mTargetType;
    private Date mTimestamp;
    private NotificationType mType;

    /* renamed from: com.contextlogic.wish.api.model.WishNotification$3 reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$com$contextlogic$wish$api$model$WishNotification$NotificationIcon = new int[NotificationIcon.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(22:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|13|14|15|16|17|18|19|20|21|22|23|24|25|26|28) */
        /* JADX WARNING: Can't wrap try/catch for region: R(23:0|1|2|3|(2:5|6)|7|(2:9|10)|11|13|14|15|16|17|18|19|20|21|22|23|24|25|26|28) */
        /* JADX WARNING: Can't wrap try/catch for region: R(24:0|1|2|3|5|6|7|(2:9|10)|11|13|14|15|16|17|18|19|20|21|22|23|24|25|26|28) */
        /* JADX WARNING: Code restructure failed: missing block: B:29:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x003d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0047 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x0051 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x005c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x0067 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x0072 */
        static {
            /*
                com.contextlogic.wish.api.model.WishNotification$NotificationIcon[] r0 = com.contextlogic.wish.api.model.WishNotification.NotificationIcon.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$contextlogic$wish$api$model$WishNotification$NotificationIcon = r0
                r0 = 1
                int[] r1 = $SwitchMap$com$contextlogic$wish$api$model$WishNotification$NotificationIcon     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.contextlogic.wish.api.model.WishNotification$NotificationIcon r2 = com.contextlogic.wish.api.model.WishNotification.NotificationIcon.PERCENTAGE     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r1[r2] = r0     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                r1 = 2
                int[] r2 = $SwitchMap$com$contextlogic$wish$api$model$WishNotification$NotificationIcon     // Catch:{ NoSuchFieldError -> 0x001f }
                com.contextlogic.wish.api.model.WishNotification$NotificationIcon r3 = com.contextlogic.wish.api.model.WishNotification.NotificationIcon.BOX     // Catch:{ NoSuchFieldError -> 0x001f }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2[r3] = r1     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                r2 = 3
                int[] r3 = $SwitchMap$com$contextlogic$wish$api$model$WishNotification$NotificationIcon     // Catch:{ NoSuchFieldError -> 0x002a }
                com.contextlogic.wish.api.model.WishNotification$NotificationIcon r4 = com.contextlogic.wish.api.model.WishNotification.NotificationIcon.APP     // Catch:{ NoSuchFieldError -> 0x002a }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r3[r4] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                com.contextlogic.wish.api.model.WishNotification$TargetClass[] r3 = com.contextlogic.wish.api.model.WishNotification.TargetClass.values()
                int r3 = r3.length
                int[] r3 = new int[r3]
                $SwitchMap$com$contextlogic$wish$api$model$WishNotification$TargetClass = r3
                int[] r3 = $SwitchMap$com$contextlogic$wish$api$model$WishNotification$TargetClass     // Catch:{ NoSuchFieldError -> 0x003d }
                com.contextlogic.wish.api.model.WishNotification$TargetClass r4 = com.contextlogic.wish.api.model.WishNotification.TargetClass.NULL     // Catch:{ NoSuchFieldError -> 0x003d }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x003d }
                r3[r4] = r0     // Catch:{ NoSuchFieldError -> 0x003d }
            L_0x003d:
                int[] r0 = $SwitchMap$com$contextlogic$wish$api$model$WishNotification$TargetClass     // Catch:{ NoSuchFieldError -> 0x0047 }
                com.contextlogic.wish.api.model.WishNotification$TargetClass r3 = com.contextlogic.wish.api.model.WishNotification.TargetClass.WISH_PRODUCT     // Catch:{ NoSuchFieldError -> 0x0047 }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x0047 }
                r0[r3] = r1     // Catch:{ NoSuchFieldError -> 0x0047 }
            L_0x0047:
                int[] r0 = $SwitchMap$com$contextlogic$wish$api$model$WishNotification$TargetClass     // Catch:{ NoSuchFieldError -> 0x0051 }
                com.contextlogic.wish.api.model.WishNotification$TargetClass r1 = com.contextlogic.wish.api.model.WishNotification.TargetClass.WISH_USER     // Catch:{ NoSuchFieldError -> 0x0051 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0051 }
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0051 }
            L_0x0051:
                int[] r0 = $SwitchMap$com$contextlogic$wish$api$model$WishNotification$TargetClass     // Catch:{ NoSuchFieldError -> 0x005c }
                com.contextlogic.wish.api.model.WishNotification$TargetClass r1 = com.contextlogic.wish.api.model.WishNotification.TargetClass.OBJECT     // Catch:{ NoSuchFieldError -> 0x005c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x005c }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x005c }
            L_0x005c:
                int[] r0 = $SwitchMap$com$contextlogic$wish$api$model$WishNotification$TargetClass     // Catch:{ NoSuchFieldError -> 0x0067 }
                com.contextlogic.wish.api.model.WishNotification$TargetClass r1 = com.contextlogic.wish.api.model.WishNotification.TargetClass.JSON_OBJECT     // Catch:{ NoSuchFieldError -> 0x0067 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0067 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0067 }
            L_0x0067:
                int[] r0 = $SwitchMap$com$contextlogic$wish$api$model$WishNotification$TargetClass     // Catch:{ NoSuchFieldError -> 0x0072 }
                com.contextlogic.wish.api.model.WishNotification$TargetClass r1 = com.contextlogic.wish.api.model.WishNotification.TargetClass.WISH_TAG     // Catch:{ NoSuchFieldError -> 0x0072 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0072 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0072 }
            L_0x0072:
                int[] r0 = $SwitchMap$com$contextlogic$wish$api$model$WishNotification$TargetClass     // Catch:{ NoSuchFieldError -> 0x007d }
                com.contextlogic.wish.api.model.WishNotification$TargetClass r1 = com.contextlogic.wish.api.model.WishNotification.TargetClass.WISH_BRAND_FILTER     // Catch:{ NoSuchFieldError -> 0x007d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x007d }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x007d }
            L_0x007d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.contextlogic.wish.api.model.WishNotification.AnonymousClass3.<clinit>():void");
        }
    }

    public enum NotificationDisplayType implements Parcelable {
        DEFAULT(1),
        SMALL(2),
        LARGE(3),
        DEAL_DASH(4),
        REWARD(5),
        GET_GIVE_COUPON(6),
        DAILY_GIVEAWAY(7),
        DAILY_LOGIN_BONUS(8);
        
        public static final Creator<NotificationDisplayType> CREATOR = null;
        private int mValue;

        public int describeContents() {
            return 0;
        }

        static {
            CREATOR = new Creator<NotificationDisplayType>() {
                public NotificationDisplayType createFromParcel(Parcel parcel) {
                    return NotificationDisplayType.values()[parcel.readInt()];
                }

                public NotificationDisplayType[] newArray(int i) {
                    return new NotificationDisplayType[i];
                }
            };
        }

        private NotificationDisplayType(int i) {
            this.mValue = i;
        }

        public int getValue() {
            return this.mValue;
        }

        public static NotificationDisplayType fromInteger(int i) {
            switch (i) {
                case 1:
                    return DEFAULT;
                case 2:
                    return SMALL;
                case 3:
                    return LARGE;
                case 4:
                    return DEAL_DASH;
                case 5:
                    return REWARD;
                case 6:
                    return GET_GIVE_COUPON;
                case 7:
                    return DAILY_GIVEAWAY;
                case 8:
                    return DAILY_LOGIN_BONUS;
                default:
                    return DEFAULT;
            }
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(ordinal());
        }
    }

    public enum NotificationIcon implements Parcelable {
        PERCENTAGE(1),
        BOX(2),
        APP(3);
        
        public static final Creator<NotificationIcon> CREATOR = null;
        private int mValue;

        public int describeContents() {
            return 0;
        }

        static {
            CREATOR = new Creator<NotificationIcon>() {
                public NotificationIcon createFromParcel(Parcel parcel) {
                    return NotificationIcon.values()[parcel.readInt()];
                }

                public NotificationIcon[] newArray(int i) {
                    return new NotificationIcon[i];
                }
            };
        }

        private NotificationIcon(int i) {
            this.mValue = i;
        }

        public static NotificationIcon fromInteger(int i) {
            switch (i) {
                case 1:
                    return PERCENTAGE;
                case 2:
                    return BOX;
                case 3:
                    return APP;
                default:
                    return null;
            }
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(ordinal());
        }
    }

    public enum NotificationTargetType implements Parcelable {
        Website,
        Product,
        None,
        Invite,
        Feed,
        Profile,
        Alert,
        BrandFeed,
        Rate,
        DeepLink;
        
        public static final Creator<NotificationTargetType> CREATOR = null;

        public int describeContents() {
            return 0;
        }

        static {
            CREATOR = new Creator<NotificationTargetType>() {
                public NotificationTargetType createFromParcel(Parcel parcel) {
                    return NotificationTargetType.values()[parcel.readInt()];
                }

                public NotificationTargetType[] newArray(int i) {
                    return new NotificationTargetType[i];
                }
            };
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(ordinal());
        }
    }

    public enum NotificationType implements Parcelable {
        Default,
        Recommendation,
        BeingFollowed,
        VisitFeed,
        VisitProfile;
        
        public static final Creator<NotificationType> CREATOR = null;

        public int describeContents() {
            return 0;
        }

        static {
            CREATOR = new Creator<NotificationType>() {
                public NotificationType createFromParcel(Parcel parcel) {
                    return NotificationType.values()[parcel.readInt()];
                }

                public NotificationType[] newArray(int i) {
                    return new NotificationType[i];
                }
            };
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(ordinal());
        }
    }

    private enum TargetClass implements Parcelable {
        NULL(0),
        WISH_PRODUCT(1),
        WISH_USER(2),
        OBJECT(3),
        JSON_OBJECT(4),
        WISH_TAG(5),
        WISH_BRAND_FILTER(6);
        
        public static final Creator<TargetClass> CREATOR = null;
        int mValue;

        public int describeContents() {
            return 0;
        }

        static {
            CREATOR = new Creator<TargetClass>() {
                public TargetClass createFromParcel(Parcel parcel) {
                    return TargetClass.values()[parcel.readInt()];
                }

                public TargetClass[] newArray(int i) {
                    return new TargetClass[i];
                }
            };
        }

        private TargetClass(int i) {
            this.mValue = i;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(ordinal());
        }
    }

    public int describeContents() {
        return 0;
    }

    public WishNotification(int i, int i2) {
        this.mBucketNumber = i;
        this.mNotificationNumber = i2;
    }

    public WishNotification(JSONObject jSONObject) throws JSONException, ParseException {
        super(jSONObject);
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) throws JSONException, ParseException {
        this.mExtraInfo = new HashMap<>();
        this.mBucketNumber = jSONObject.getInt("bucket");
        this.mNotificationNumber = jSONObject.getInt("num");
        this.mIsViewed = jSONObject.getBoolean("viewed");
        this.mIsClicked = this.mIsViewed;
        this.mIsNew = !this.mIsViewed;
        this.mTimestamp = DateUtil.parseIsoDate(jSONObject.getString("isotime"));
        Object obj = null;
        this.mActionButtonText = null;
        this.mIsReward = false;
        this.mTarget = null;
        this.mImage = null;
        this.mExtraImages = new ArrayList<>();
        WishApplication instance = WishApplication.getInstance();
        String string = jSONObject.getString("type");
        if (string.equals("visit.feed")) {
            this.mType = NotificationType.VisitFeed;
            this.mTargetType = NotificationTargetType.Feed;
            this.mMessage = instance.getString(R.string.notification_visit_feed);
        } else if (string.equals("visit.profile")) {
            this.mType = NotificationType.VisitProfile;
            this.mTargetType = NotificationTargetType.Profile;
            this.mMessage = instance.getString(R.string.notification_visit_profile);
        } else {
            this.mType = NotificationType.Default;
            if (JsonUtil.hasValue(jSONObject, "mobile_message")) {
                this.mMessage = jSONObject.getString("mobile_message");
            } else {
                this.mMessage = instance.getString(R.string.notification_default_message);
            }
            if (JsonUtil.hasValue(jSONObject, "mobile_image_url")) {
                this.mImage = new WishImage(jSONObject.getString("mobile_image_url"));
            }
            if (JsonUtil.hasValue(jSONObject, "mobile_action_button_text")) {
                this.mActionButtonText = jSONObject.getString("mobile_action_button_text");
            }
            if (JsonUtil.hasValue(jSONObject, "mobile_target_param")) {
                obj = jSONObject.get(jSONObject.getString("mobile_target_param"));
            }
            if (JsonUtil.hasValue(jSONObject, "mobile_is_reward")) {
                this.mIsReward = jSONObject.getBoolean("mobile_is_reward");
            }
            switch (jSONObject.getInt("mobile_target_type")) {
                case 1:
                    if (obj == null) {
                        this.mTargetType = NotificationTargetType.None;
                        break;
                    } else {
                        this.mTargetType = NotificationTargetType.Product;
                        WishProduct wishProduct = new WishProduct((JSONObject) obj);
                        this.mTarget = wishProduct;
                        if (this.mImage == null) {
                            this.mImage = wishProduct.getImage();
                            break;
                        }
                    }
                    break;
                case 2:
                    this.mTargetType = NotificationTargetType.Invite;
                    break;
                case 3:
                    if (obj == null) {
                        this.mTargetType = NotificationTargetType.None;
                        break;
                    } else {
                        this.mTargetType = NotificationTargetType.Website;
                        this.mTarget = obj;
                        break;
                    }
                case 4:
                    if (obj == null) {
                        this.mTargetType = NotificationTargetType.None;
                        break;
                    } else {
                        this.mTargetType = NotificationTargetType.Profile;
                        WishUser wishUser = new WishUser((JSONObject) obj);
                        this.mTarget = wishUser;
                        if (this.mImage == null) {
                            this.mImage = wishUser.getProfileImage();
                            break;
                        }
                    }
                    break;
                case 5:
                    this.mTargetType = NotificationTargetType.Feed;
                    if (obj != null) {
                        this.mTarget = new WishTag((JSONObject) obj);
                        break;
                    }
                    break;
                case 7:
                    if (obj == null) {
                        this.mTargetType = NotificationTargetType.None;
                        break;
                    } else {
                        this.mTargetType = NotificationTargetType.Alert;
                        this.mTarget = obj;
                        break;
                    }
                case 8:
                    if (obj == null) {
                        this.mTargetType = NotificationTargetType.None;
                        break;
                    } else {
                        this.mTargetType = NotificationTargetType.BrandFeed;
                        this.mTarget = new WishBrandFilter((JSONObject) obj);
                        break;
                    }
                case 10:
                    this.mTargetType = NotificationTargetType.Rate;
                    break;
                case 11:
                    if (obj == null) {
                        this.mTargetType = NotificationTargetType.None;
                        break;
                    } else {
                        this.mTargetType = NotificationTargetType.DeepLink;
                        this.mTarget = obj;
                        break;
                    }
                default:
                    this.mTargetType = NotificationTargetType.None;
                    break;
            }
            if (JsonUtil.hasValue(jSONObject, "icon_type")) {
                this.mIcon = NotificationIcon.fromInteger(jSONObject.getInt("icon_type"));
            }
            if (JsonUtil.hasValue(jSONObject, "display_type")) {
                this.mDisplayType = NotificationDisplayType.fromInteger(jSONObject.getInt("display_type"));
            }
        }
        if (this.mImage != null) {
            this.mExtraImages.add(0, this.mImage.getUrlString(ImageSize.SMALL));
        } else if (JsonUtil.hasValue(jSONObject, "extra_images")) {
            this.mExtraImages = JsonUtil.parseArray(jSONObject, "extra_images", new DataParser<String, String>() {
                public String parseData(String str) throws JSONException {
                    return str;
                }
            });
        }
    }

    protected WishNotification(Parcel parcel) {
        boolean z = true;
        this.mIsClicked = parcel.readByte() != 0;
        this.mIsNew = parcel.readByte() != 0;
        this.mIsReward = parcel.readByte() != 0;
        if (parcel.readByte() == 0) {
            z = false;
        }
        this.mIsViewed = z;
        this.mBucketNumber = parcel.readInt();
        this.mNotificationNumber = parcel.readInt();
        this.mExtraImages = parcel.createStringArrayList();
        if (parcel.readByte() != 0) {
            this.mTimestamp = new Date(parcel.readLong());
        }
        int readInt = parcel.readInt();
        this.mExtraInfo = new HashMap<>();
        for (int i = 0; i < readInt; i++) {
            this.mExtraInfo.put(parcel.readString(), parcel.readString());
        }
        this.mIcon = (NotificationIcon) parcel.readParcelable(NotificationIcon.class.getClassLoader());
        this.mDisplayType = (NotificationDisplayType) parcel.readParcelable(NotificationDisplayType.class.getClassLoader());
        this.mTargetType = (NotificationTargetType) parcel.readParcelable(NotificationTargetType.class.getClassLoader());
        this.mType = (NotificationType) parcel.readParcelable(NotificationType.class.getClassLoader());
        switch ((TargetClass) parcel.readParcelable(TargetClass.class.getClassLoader())) {
            case NULL:
                this.mTarget = parcel.readValue(null);
                break;
            case WISH_PRODUCT:
                this.mTarget = parcel.readParcelable(WishProduct.class.getClassLoader());
                break;
            case WISH_USER:
                this.mTarget = parcel.readParcelable(WishUser.class.getClassLoader());
                break;
            case OBJECT:
                this.mTarget = null;
                break;
            case JSON_OBJECT:
                try {
                    this.mTarget = new JSONObject(parcel.readString());
                    break;
                } catch (JSONException unused) {
                    this.mTarget = null;
                    break;
                }
            case WISH_TAG:
                this.mTarget = parcel.readParcelable(WishTag.class.getClassLoader());
                break;
            case WISH_BRAND_FILTER:
                this.mTarget = parcel.readParcelable(WishBrandFilter.class.getClassLoader());
                break;
        }
        this.mActionButtonText = parcel.readString();
        this.mMessage = parcel.readString();
        this.mImage = (WishImage) parcel.readParcelable(WishImage.class.getClassLoader());
    }

    public Date getTimestamp() {
        return this.mTimestamp;
    }

    public int getBucketNumber() {
        return this.mBucketNumber;
    }

    public int getNotificationNumber() {
        return this.mNotificationNumber;
    }

    public boolean isNew() {
        return this.mIsNew;
    }

    public boolean isClicked() {
        return this.mIsClicked;
    }

    public NotificationTargetType getTargetType() {
        return this.mTargetType;
    }

    public Object getTarget() {
        return this.mTarget;
    }

    public String getMessage() {
        return this.mMessage;
    }

    public void markViewed() {
        this.mIsViewed = true;
    }

    public void markClicked() {
        this.mIsClicked = true;
    }

    public ArrayList<String> getExtraImages() {
        return this.mExtraImages;
    }

    public NotificationDisplayType getNotificationDisplayType() {
        return this.mDisplayType;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte(this.mIsClicked ? (byte) 1 : 0);
        parcel.writeByte(this.mIsNew ? (byte) 1 : 0);
        parcel.writeByte(this.mIsReward ? (byte) 1 : 0);
        parcel.writeByte(this.mIsViewed ? (byte) 1 : 0);
        parcel.writeInt(this.mBucketNumber);
        parcel.writeInt(this.mNotificationNumber);
        parcel.writeStringList(this.mExtraImages);
        parcel.writeByte((byte) (this.mTimestamp != null ? 1 : 0));
        if (this.mTimestamp != null) {
            parcel.writeLong(this.mTimestamp.getTime());
        }
        parcel.writeInt(this.mExtraInfo == null ? 0 : this.mExtraInfo.size());
        if (this.mExtraInfo != null) {
            for (Entry entry : this.mExtraInfo.entrySet()) {
                parcel.writeString((String) entry.getKey());
                parcel.writeString((String) entry.getValue());
            }
        }
        parcel.writeParcelable(this.mIcon, 0);
        parcel.writeParcelable(this.mDisplayType, 0);
        parcel.writeParcelable(this.mTargetType, 0);
        parcel.writeParcelable(this.mType, 0);
        if (this.mTarget == null) {
            parcel.writeParcelable(TargetClass.NULL, 0);
            parcel.writeValue(this.mTarget);
        } else if (this.mTarget instanceof WishProduct) {
            parcel.writeParcelable(TargetClass.WISH_PRODUCT, 0);
            parcel.writeParcelable((WishProduct) this.mTarget, 0);
        } else if (this.mTarget instanceof WishUser) {
            parcel.writeParcelable(TargetClass.WISH_USER, 0);
            parcel.writeParcelable((WishUser) this.mTarget, 0);
        } else if (this.mTarget instanceof JSONObject) {
            parcel.writeParcelable(TargetClass.JSON_OBJECT, 0);
            parcel.writeString(this.mTarget.toString());
        } else if (this.mTarget instanceof WishTag) {
            parcel.writeParcelable(TargetClass.WISH_TAG, 0);
            parcel.writeParcelable((WishTag) this.mTarget, 0);
        } else if (this.mTarget instanceof WishBrandFilter) {
            parcel.writeParcelable(TargetClass.WISH_BRAND_FILTER, 0);
            parcel.writeParcelable((WishBrandFilter) this.mTarget, 0);
        } else {
            parcel.writeParcelable(TargetClass.OBJECT, 0);
        }
        parcel.writeString(this.mActionButtonText);
        parcel.writeString(this.mMessage);
        parcel.writeParcelable(this.mImage, 0);
    }
}
