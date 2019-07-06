package com.contextlogic.wish.api.model;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.contextlogic.wish.util.JsonUtil;
import com.contextlogic.wish.util.JsonUtil.DataParser;
import com.crashlytics.android.Crashlytics;
import com.threatmetrix.TrustDefender.StrongAuth;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import org.json.JSONException;
import org.json.JSONObject;

public class WishHomePageInfo implements Parcelable {
    public static final Creator<WishHomePageInfo> CREATOR = new Creator<WishHomePageInfo>() {
        public WishHomePageInfo createFromParcel(Parcel parcel) {
            WishHomePageInfo instance = WishHomePageInfo.getInstance();
            instance.updateHomePageInfo(parcel);
            return instance;
        }

        public WishHomePageInfo[] newArray(int i) {
            return new WishHomePageInfo[i];
        }
    };
    private static WishHomePageInfo sInstance;
    private String mCommerceLoanDeepLink;
    private int mCommerceLoanRowId;
    /* access modifiers changed from: private */
    public ArrayList<HomePageCommerceLoanItemHolder> mCommerceLoans;
    private HomePagePriceChopItemHolder mHomePagePriceChopItemHolder;
    private boolean mLandOnHomePage;
    private int mNotificationRowId;
    private String mNotificationViewAllDeepLink;
    /* access modifiers changed from: private */
    public ArrayList<HomePageNotificationItemHolder> mNotifications;
    private String mNotificationsTitle;
    private int mOrderStatusRowId;
    /* access modifiers changed from: private */
    public ArrayList<HomePageOrderStatusItemHolder> mOrderStatuses;
    private String mOrderStatusesDeepLink;
    private String mOrderStatusesTitle;
    /* access modifiers changed from: private */
    public HashMap<Long, HomePageProductListItemHolder> mProductsMap;
    private ArrayList<Long> mRowIds;
    private ArrayList<RowType> mRowOrdering;
    private String mTabToAppendHomePageTo;
    /* access modifiers changed from: private */
    public HashMap<Long, HomePageVideoListItemHolder> mVideoMaps;

    public static class HomePageCommerceLoanItemHolder implements Parcelable {
        public static final Creator<HomePageCommerceLoanItemHolder> CREATOR = new Creator<HomePageCommerceLoanItemHolder>() {
            public HomePageCommerceLoanItemHolder createFromParcel(Parcel parcel) {
                return new HomePageCommerceLoanItemHolder(parcel);
            }

            public HomePageCommerceLoanItemHolder[] newArray(int i) {
                return new HomePageCommerceLoanItemHolder[i];
            }
        };
        private String mButtonText;
        private String mDeepLink;
        private String mDescription;
        private String mSuccessSheetTitle;
        private String mTitle;
        private String mTransactionId;

        public int describeContents() {
            return 0;
        }

        HomePageCommerceLoanItemHolder(String str, String str2, String str3, String str4, String str5, String str6) {
            this.mDeepLink = str;
            this.mTitle = str2;
            this.mDescription = str3;
            this.mButtonText = str4;
            this.mTransactionId = str5;
            this.mSuccessSheetTitle = str6;
        }

        HomePageCommerceLoanItemHolder(Parcel parcel) {
            this.mDeepLink = parcel.readString();
            this.mTitle = parcel.readString();
            this.mDescription = parcel.readString();
            this.mButtonText = parcel.readString();
            this.mTransactionId = parcel.readString();
            this.mSuccessSheetTitle = parcel.readString();
        }

        public String getTitle() {
            return this.mTitle;
        }

        public String getDescription() {
            return this.mDescription;
        }

        public String getButtonText() {
            return this.mButtonText;
        }

        public String getTransactionId() {
            return this.mTransactionId;
        }

        public String getSuccessSheetTitle() {
            return this.mSuccessSheetTitle;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.mDeepLink);
            parcel.writeString(this.mTitle);
            parcel.writeString(this.mDescription);
            parcel.writeString(this.mButtonText);
            parcel.writeString(this.mTransactionId);
            parcel.writeString(this.mSuccessSheetTitle);
        }
    }

    public static class HomePageNotificationItemHolder implements Parcelable {
        public static final Creator<HomePageNotificationItemHolder> CREATOR = new Creator<HomePageNotificationItemHolder>() {
            public HomePageNotificationItemHolder createFromParcel(Parcel parcel) {
                return new HomePageNotificationItemHolder(parcel);
            }

            public HomePageNotificationItemHolder[] newArray(int i) {
                return new HomePageNotificationItemHolder[i];
            }
        };
        private int mBucketId;
        private String mDeepLink;
        private ArrayList<String> mImageUrls;
        private int mNotificationId;
        private String mText;

        public int describeContents() {
            return 0;
        }

        public HomePageNotificationItemHolder(int i, int i2, String str, String str2, ArrayList<String> arrayList) {
            this.mBucketId = i;
            this.mNotificationId = i2;
            this.mDeepLink = str;
            this.mText = str2;
            this.mImageUrls = arrayList;
        }

        protected HomePageNotificationItemHolder(Parcel parcel) {
            this.mBucketId = parcel.readInt();
            this.mNotificationId = parcel.readInt();
            this.mImageUrls = parcel.createStringArrayList();
            this.mDeepLink = parcel.readString();
            this.mText = parcel.readString();
        }

        public int getBucketId() {
            return this.mBucketId;
        }

        public String getDeepLink() {
            return this.mDeepLink;
        }

        public String getText() {
            return this.mText;
        }

        public ArrayList<String> getImageUrls() {
            return this.mImageUrls;
        }

        public int getNotificationId() {
            return this.mNotificationId;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.mBucketId);
            parcel.writeInt(this.mNotificationId);
            parcel.writeStringList(this.mImageUrls);
            parcel.writeString(this.mDeepLink);
            parcel.writeString(this.mText);
        }
    }

    public static class HomePageOrderStatusItemHolder implements Parcelable {
        public static final Creator<HomePageOrderStatusItemHolder> CREATOR = new Creator<HomePageOrderStatusItemHolder>() {
            public HomePageOrderStatusItemHolder createFromParcel(Parcel parcel) {
                return new HomePageOrderStatusItemHolder(parcel);
            }

            public HomePageOrderStatusItemHolder[] newArray(int i) {
                return new HomePageOrderStatusItemHolder[i];
            }
        };
        private String mContestImageUrl;
        private String mDeepLink;
        private String mDescription;
        private String mDetail;
        private String mTitle;

        public int describeContents() {
            return 0;
        }

        public HomePageOrderStatusItemHolder(String str, String str2, String str3, String str4, String str5) {
            this.mDeepLink = str;
            this.mContestImageUrl = str2;
            this.mTitle = str3;
            this.mDescription = str4;
            this.mDetail = str5;
        }

        protected HomePageOrderStatusItemHolder(Parcel parcel) {
            this.mContestImageUrl = parcel.readString();
            this.mDeepLink = parcel.readString();
            this.mDescription = parcel.readString();
            this.mTitle = parcel.readString();
            this.mDetail = parcel.readString();
        }

        public String getDeepLink() {
            return this.mDeepLink;
        }

        public String getContestImageUrl() {
            return this.mContestImageUrl;
        }

        public String getTitle() {
            return this.mTitle;
        }

        public String getDescription() {
            return this.mDescription;
        }

        public String getDetail() {
            return this.mDetail;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.mContestImageUrl);
            parcel.writeString(this.mDeepLink);
            parcel.writeString(this.mDescription);
            parcel.writeString(this.mTitle);
            parcel.writeString(this.mDetail);
        }
    }

    @SuppressLint({"NullableNonNullAnnotationRequired"})
    public static class HomePagePriceChopItemHolder extends BaseModel {
        public static final Creator<HomePagePriceChopItemHolder> CREATOR = new Creator<HomePagePriceChopItemHolder>() {
            public HomePagePriceChopItemHolder createFromParcel(Parcel parcel) {
                return new HomePagePriceChopItemHolder(parcel);
            }

            public HomePagePriceChopItemHolder[] newArray(int i) {
                return new HomePagePriceChopItemHolder[i];
            }
        };
        private List<HomePagePriceChopProduct> mProducts;
        private String mTitle;

        public int describeContents() {
            return 0;
        }

        public HomePagePriceChopItemHolder(JSONObject jSONObject) throws JSONException, ParseException {
            parseJson(jSONObject);
        }

        /* access modifiers changed from: protected */
        public void parseJson(JSONObject jSONObject) throws JSONException, ParseException {
            this.mTitle = jSONObject.getString(StrongAuth.AUTH_TITLE);
            this.mProducts = JsonUtil.parseArray(jSONObject, "price_chop_products", new DataParser<HomePagePriceChopProduct, JSONObject>() {
                public HomePagePriceChopProduct parseData(JSONObject jSONObject) throws JSONException, ParseException {
                    return new HomePagePriceChopProduct(jSONObject);
                }
            });
        }

        public String getTitle() {
            return this.mTitle;
        }

        public List<HomePagePriceChopProduct> getProducts() {
            return this.mProducts;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.mTitle);
            parcel.writeTypedList(this.mProducts);
        }

        protected HomePagePriceChopItemHolder(Parcel parcel) {
            this.mTitle = parcel.readString();
            this.mProducts = parcel.createTypedArrayList(HomePagePriceChopProduct.CREATOR);
        }
    }

    @SuppressLint({"NullableNonNullAnnotationRequired"})
    public static class HomePagePriceChopProduct extends BaseModel {
        public static final Creator<HomePagePriceChopProduct> CREATOR = new Creator<HomePagePriceChopProduct>() {
            public HomePagePriceChopProduct createFromParcel(Parcel parcel) {
                return new HomePagePriceChopProduct(parcel);
            }

            public HomePagePriceChopProduct[] newArray(int i) {
                return new HomePagePriceChopProduct[i];
            }
        };
        private Date mExpireDate;
        private WishLocalizedCurrencyValue mOriginalPrice;
        private WishLocalizedCurrencyValue mPrice;
        private String mProductId;
        private String mProductImageUrl;

        public int describeContents() {
            return 0;
        }

        public HomePagePriceChopProduct(JSONObject jSONObject) throws JSONException, ParseException {
            parseJson(jSONObject);
        }

        /* access modifiers changed from: protected */
        public void parseJson(JSONObject jSONObject) throws JSONException, ParseException {
            this.mPrice = new WishLocalizedCurrencyValue(jSONObject.getDouble("price"), jSONObject.getJSONObject("price_localized"));
            this.mOriginalPrice = new WishLocalizedCurrencyValue(jSONObject.getDouble("original_price"), jSONObject.getJSONObject("original_price_localized"));
            this.mProductId = jSONObject.getString("product_id");
            this.mProductImageUrl = jSONObject.getString("image_url");
            this.mExpireDate = new Date(System.currentTimeMillis() + (jSONObject.getLong("time_left_seconds") * 1000));
        }

        public WishLocalizedCurrencyValue getPrice() {
            return this.mPrice;
        }

        public WishLocalizedCurrencyValue getOriginalPrice() {
            return this.mOriginalPrice;
        }

        public String getProductId() {
            return this.mProductId;
        }

        public String getProductImageUrl() {
            return this.mProductImageUrl;
        }

        public Date getExpireDate() {
            return this.mExpireDate;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeParcelable(this.mPrice, i);
            parcel.writeParcelable(this.mOriginalPrice, i);
            parcel.writeString(this.mProductId);
            parcel.writeString(this.mProductImageUrl);
            parcel.writeLong(this.mExpireDate != null ? this.mExpireDate.getTime() : -1);
        }

        protected HomePagePriceChopProduct(Parcel parcel) {
            Date date;
            this.mPrice = (WishLocalizedCurrencyValue) parcel.readParcelable(WishLocalizedCurrencyValue.class.getClassLoader());
            this.mOriginalPrice = (WishLocalizedCurrencyValue) parcel.readParcelable(WishLocalizedCurrencyValue.class.getClassLoader());
            this.mProductId = parcel.readString();
            this.mProductImageUrl = parcel.readString();
            long readLong = parcel.readLong();
            if (readLong == -1) {
                date = null;
            } else {
                date = new Date(readLong);
            }
            this.mExpireDate = date;
        }
    }

    public static class HomePageProductListItemHolder implements Parcelable {
        public static final Creator<HomePageProductListItemHolder> CREATOR = new Creator<HomePageProductListItemHolder>() {
            public HomePageProductListItemHolder createFromParcel(Parcel parcel) {
                return new HomePageProductListItemHolder(parcel);
            }

            public HomePageProductListItemHolder[] newArray(int i) {
                return new HomePageProductListItemHolder[i];
            }
        };
        private String mDeepLink;
        private String mFilterFeedId;
        private ArrayList<WishProduct> mProducts;
        private int mRowId;
        private boolean mTileRedirectsToViewAll;
        private String mTitle;
        private String mViewAllText;

        public int describeContents() {
            return 0;
        }

        public HomePageProductListItemHolder(ArrayList<WishProduct> arrayList, String str, String str2, int i, String str3, boolean z, String str4) {
            this.mProducts = arrayList;
            this.mTitle = str;
            this.mFilterFeedId = str2;
            this.mRowId = i;
            this.mDeepLink = str3;
            this.mTileRedirectsToViewAll = z;
            this.mViewAllText = str4;
        }

        protected HomePageProductListItemHolder(Parcel parcel) {
            this.mRowId = parcel.readInt();
            this.mProducts = parcel.createTypedArrayList(WishProduct.CREATOR);
            this.mFilterFeedId = parcel.readString();
            this.mTitle = parcel.readString();
            this.mDeepLink = parcel.readString();
            this.mTileRedirectsToViewAll = parcel.readByte() != 0;
            this.mViewAllText = parcel.readString();
        }

        public ArrayList<WishProduct> getProducts() {
            return this.mProducts;
        }

        public String getTitle() {
            return this.mTitle;
        }

        public String getDeeplink() {
            return this.mDeepLink;
        }

        public boolean isTileRedirectsToViewAll() {
            return this.mTileRedirectsToViewAll;
        }

        public String getFilterFeedId() {
            return this.mFilterFeedId;
        }

        public int getRowId() {
            return this.mRowId;
        }

        public String getViewAllText() {
            return this.mViewAllText;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.mRowId);
            parcel.writeTypedList(this.mProducts);
            parcel.writeString(this.mFilterFeedId);
            parcel.writeString(this.mTitle);
            parcel.writeString(this.mDeepLink);
            parcel.writeByte(this.mTileRedirectsToViewAll ? (byte) 1 : 0);
            parcel.writeString(this.mViewAllText);
        }
    }

    public static class HomePageVideoListItemHolder implements Parcelable {
        public static final Creator<HomePageVideoListItemHolder> CREATOR = new Creator<HomePageVideoListItemHolder>() {
            public HomePageVideoListItemHolder createFromParcel(Parcel parcel) {
                return new HomePageVideoListItemHolder(parcel);
            }

            public HomePageVideoListItemHolder[] newArray(int i) {
                return new HomePageVideoListItemHolder[i];
            }
        };
        private int mRowId;
        private ArrayList<String> mThumbnailUrls;
        private String mTitle;
        private ArrayList<String> mVideoUrls;

        public int describeContents() {
            return 0;
        }

        public HomePageVideoListItemHolder(ArrayList<String> arrayList, ArrayList<String> arrayList2, String str, int i) {
            this.mVideoUrls = arrayList;
            this.mThumbnailUrls = arrayList2;
            this.mTitle = str;
            this.mRowId = i;
        }

        protected HomePageVideoListItemHolder(Parcel parcel) {
            this.mThumbnailUrls = parcel.createStringArrayList();
            this.mVideoUrls = parcel.createStringArrayList();
            this.mRowId = parcel.readInt();
            this.mTitle = parcel.readString();
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeStringList(this.mThumbnailUrls);
            parcel.writeStringList(this.mVideoUrls);
            parcel.writeInt(this.mRowId);
            parcel.writeString(this.mTitle);
        }
    }

    public enum RowType implements Parcelable {
        PRODUCT(1),
        NOTIFICATION(2),
        STATUS_UPDATE(3),
        DETAILED_PRODUCT(4),
        VIDEO(5),
        COMMERCE_LOAN(6),
        PRICE_CHOP(7);
        
        public static final Creator<RowType> CREATOR = null;
        private int mValue;

        public int describeContents() {
            return 0;
        }

        static {
            CREATOR = new Creator<RowType>() {
                public RowType createFromParcel(Parcel parcel) {
                    return RowType.values()[parcel.readInt()];
                }

                public RowType[] newArray(int i) {
                    return new RowType[i];
                }
            };
        }

        private RowType(int i) {
            this.mValue = i;
        }

        public int getValue() {
            return this.mValue;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(ordinal());
        }
    }

    public int describeContents() {
        return 0;
    }

    protected WishHomePageInfo() {
    }

    private void resetState() {
        sInstance.mProductsMap = new HashMap<>();
        sInstance.mNotifications = new ArrayList<>();
        sInstance.mOrderStatuses = new ArrayList<>();
        sInstance.mCommerceLoans = new ArrayList<>();
        sInstance.mVideoMaps = new HashMap<>();
        sInstance.mRowOrdering = new ArrayList<>();
        sInstance.mRowIds = new ArrayList<>();
        sInstance.mOrderStatusRowId = 0;
        sInstance.mOrderStatusesTitle = null;
        sInstance.mOrderStatusesDeepLink = null;
        sInstance.mCommerceLoanDeepLink = null;
        sInstance.mNotificationRowId = 0;
        sInstance.mNotificationsTitle = null;
        sInstance.mNotificationViewAllDeepLink = null;
        sInstance.mCommerceLoanRowId = 0;
        sInstance.mLandOnHomePage = true;
    }

    public static WishHomePageInfo getInstance() {
        if (sInstance == null) {
            sInstance = new WishHomePageInfo();
            sInstance.resetState();
        }
        return sInstance;
    }

    public void updateHomePageInfo(JSONObject jSONObject) throws JSONException {
        boolean hasValue = JsonUtil.hasValue(jSONObject, "home_page_ordering");
        boolean hasValue2 = JsonUtil.hasValue(jSONObject, "home_page_row_ids");
        if (hasValue && hasValue2) {
            sInstance.resetState();
        }
        this.mTabToAppendHomePageTo = JsonUtil.optString(jSONObject, "tab_to_append_home_page_to");
        if (JsonUtil.hasValue(jSONObject, "land_on_home_page")) {
            this.mLandOnHomePage = jSONObject.getBoolean("land_on_home_page");
        }
        final RowType[] values = RowType.values();
        this.mRowOrdering = JsonUtil.parseArray(jSONObject, "home_page_ordering", new DataParser<RowType, Long>() {
            public RowType parseData(Long l) throws JSONException {
                for (RowType value : values) {
                    if (((long) value.getValue()) == l.longValue()) {
                        return values[(int) (l.longValue() - 1)];
                    }
                }
                throw new JSONException("RowType Not Found");
            }
        });
        this.mRowIds = JsonUtil.parseArray(jSONObject, "home_page_row_ids", new DataParser<Long, Long>() {
            public Long parseData(Long l) {
                return l;
            }
        });
        JsonUtil.parseArray(jSONObject, "product_feeds", new DataParser<Void, JSONObject>() {
            public Void parseData(JSONObject jSONObject) throws JSONException {
                HomePageProductListItemHolder homePageProductListItemHolder = new HomePageProductListItemHolder(JsonUtil.parseArray(jSONObject, "products", new DataParser<WishProduct, JSONObject>() {
                    public WishProduct parseData(JSONObject jSONObject) throws JSONException, ParseException {
                        return new WishProduct(jSONObject);
                    }
                }), jSONObject.getString(StrongAuth.AUTH_TITLE), JsonUtil.optString(jSONObject, "filter_id"), jSONObject.getInt("row_id"), JsonUtil.optString(jSONObject, "view_all_deep_link"), jSONObject.optBoolean("tile_redirects_to_view_all"), JsonUtil.optString(jSONObject, "view_all_text"));
                WishHomePageInfo.this.mProductsMap.put(Long.valueOf(jSONObject.getLong("row_id")), homePageProductListItemHolder);
                throw new JSONException("");
            }
        });
        JsonUtil.parseArray(jSONObject, "video_feeds", new DataParser<Void, JSONObject>() {
            public Void parseData(JSONObject jSONObject) throws JSONException {
                final ArrayList arrayList = new ArrayList();
                final ArrayList arrayList2 = new ArrayList();
                JsonUtil.parseArray(jSONObject, "videos", new DataParser<Void, JSONObject>() {
                    public Void parseData(JSONObject jSONObject) throws JSONException {
                        arrayList.add(jSONObject.getString("video_url"));
                        arrayList2.add(jSONObject.getString("thumbnail_url"));
                        throw new JSONException("");
                    }
                });
                WishHomePageInfo.this.mVideoMaps.put(Long.valueOf(jSONObject.getLong("row_id")), new HomePageVideoListItemHolder(arrayList, arrayList2, jSONObject.getString(StrongAuth.AUTH_TITLE), jSONObject.getInt("row_id")));
                throw new JSONException("");
            }
        });
        if (JsonUtil.hasValue(jSONObject, "notifications_row")) {
            JSONObject jSONObject2 = jSONObject.getJSONObject("notifications_row");
            JsonUtil.parseArray(jSONObject2, "notis", new DataParser<Void, JSONObject>() {
                public Void parseData(JSONObject jSONObject) throws JSONException {
                    HomePageNotificationItemHolder homePageNotificationItemHolder = new HomePageNotificationItemHolder(jSONObject.optInt("bucket"), jSONObject.optInt("notification_id"), jSONObject.optString("deep_link"), jSONObject.optString("text"), JsonUtil.parseArray(jSONObject, "image_urls", new DataParser<String, String>() {
                        public String parseData(String str) {
                            return str;
                        }
                    }));
                    WishHomePageInfo.this.mNotifications.add(homePageNotificationItemHolder);
                    throw new JSONException("");
                }
            });
            this.mNotificationViewAllDeepLink = jSONObject2.optString("view_all_deep_link");
            this.mNotificationsTitle = jSONObject2.getString(StrongAuth.AUTH_TITLE);
            this.mNotificationRowId = jSONObject2.getInt("row_id");
        }
        if (JsonUtil.hasValue(jSONObject, "order_status_row")) {
            JSONObject jSONObject3 = jSONObject.getJSONObject("order_status_row");
            JsonUtil.parseArray(jSONObject3, "order_statuses", new DataParser<Void, JSONObject>() {
                public Void parseData(JSONObject jSONObject) throws JSONException {
                    HomePageOrderStatusItemHolder homePageOrderStatusItemHolder = new HomePageOrderStatusItemHolder(jSONObject.optString("deep_link"), jSONObject.optString("contest_image_url"), jSONObject.optString(StrongAuth.AUTH_TITLE), jSONObject.optString("description"), jSONObject.optString("detail"));
                    WishHomePageInfo.this.mOrderStatuses.add(homePageOrderStatusItemHolder);
                    return null;
                }
            });
            this.mOrderStatusesDeepLink = jSONObject3.optString("view_all_deep_link");
            this.mOrderStatusesTitle = jSONObject3.getString(StrongAuth.AUTH_TITLE);
            this.mOrderStatusRowId = jSONObject3.getInt("row_id");
        }
        if (JsonUtil.hasValue(jSONObject, "commerce_loan_row")) {
            JSONObject jSONObject4 = jSONObject.getJSONObject("commerce_loan_row");
            JsonUtil.parseArray(jSONObject4, "commerce_loans", new DataParser<Void, JSONObject>() {
                public Void parseData(JSONObject jSONObject) throws JSONException {
                    try {
                        HomePageCommerceLoanItemHolder homePageCommerceLoanItemHolder = new HomePageCommerceLoanItemHolder(jSONObject.optString("deep_link"), jSONObject.optString(StrongAuth.AUTH_TITLE), jSONObject.optString("description"), jSONObject.optString("button_text"), jSONObject.optString("transaction_id"), jSONObject.optString("success_sheet_title"));
                        WishHomePageInfo.this.mCommerceLoans.add(homePageCommerceLoanItemHolder);
                    } catch (Exception e) {
                        Crashlytics.logException(e);
                    }
                    return null;
                }
            });
            this.mCommerceLoanDeepLink = jSONObject4.optString("pay_now_deep_link");
            this.mCommerceLoanRowId = jSONObject4.getInt("row_id");
        }
        if (JsonUtil.hasValue(jSONObject, "price_chop_row")) {
            try {
                this.mHomePagePriceChopItemHolder = new HomePagePriceChopItemHolder(jSONObject.getJSONObject("price_chop_row"));
            } catch (Exception e) {
                Crashlytics.logException(e);
            }
        }
    }

    public void updateHomePageInfo(Parcel parcel) {
        this.mLandOnHomePage = parcel.readByte() != 0;
        this.mNotificationRowId = parcel.readInt();
        this.mOrderStatusRowId = parcel.readInt();
        this.mCommerceLoanRowId = parcel.readInt();
        this.mNotifications = parcel.createTypedArrayList(HomePageNotificationItemHolder.CREATOR);
        this.mOrderStatuses = parcel.createTypedArrayList(HomePageOrderStatusItemHolder.CREATOR);
        this.mCommerceLoans = parcel.createTypedArrayList(HomePageCommerceLoanItemHolder.CREATOR);
        if (parcel.readByte() == 1) {
            this.mRowIds = new ArrayList<>();
            parcel.readList(this.mRowIds, Long.class.getClassLoader());
        } else {
            this.mRowIds = null;
        }
        this.mRowOrdering = parcel.createTypedArrayList(RowType.CREATOR);
        int readInt = parcel.readInt();
        this.mProductsMap = new HashMap<>();
        for (int i = 0; i < readInt; i++) {
            this.mProductsMap.put(Long.valueOf(parcel.readLong()), (HomePageProductListItemHolder) parcel.readParcelable(HomePageProductListItemHolder.class.getClassLoader()));
        }
        int readInt2 = parcel.readInt();
        this.mVideoMaps = new HashMap<>();
        for (int i2 = 0; i2 < readInt2; i2++) {
            this.mVideoMaps.put(Long.valueOf(parcel.readLong()), (HomePageVideoListItemHolder) parcel.readParcelable(HomePageVideoListItemHolder.class.getClassLoader()));
        }
        this.mNotificationsTitle = parcel.readString();
        this.mNotificationViewAllDeepLink = parcel.readString();
        this.mOrderStatusesDeepLink = parcel.readString();
        this.mOrderStatusesTitle = parcel.readString();
        this.mCommerceLoanDeepLink = parcel.readString();
        this.mTabToAppendHomePageTo = parcel.readString();
    }

    public HomePageProductListItemHolder getProductListContentView(long j) {
        if (this.mProductsMap.containsKey(Long.valueOf(j))) {
            return (HomePageProductListItemHolder) this.mProductsMap.get(Long.valueOf(j));
        }
        return null;
    }

    public ArrayList<RowType> getRowOrdering() {
        return this.mRowOrdering;
    }

    public ArrayList<Long> getRowIds() {
        return this.mRowIds;
    }

    public ArrayList<HomePageNotificationItemHolder> getNotifications() {
        return this.mNotifications;
    }

    public String getNotificationsTitle() {
        return this.mNotificationsTitle;
    }

    public int getNotificationRowId() {
        return this.mNotificationRowId;
    }

    public int getOrderStatusRowId() {
        return this.mOrderStatusRowId;
    }

    public String getNotificationViewAllDeepLink() {
        return this.mNotificationViewAllDeepLink;
    }

    public ArrayList<HomePageOrderStatusItemHolder> getOrderStatuses() {
        return this.mOrderStatuses;
    }

    public ArrayList<HomePageCommerceLoanItemHolder> getCommerceLoans() {
        return this.mCommerceLoans;
    }

    public String getOrderStatusesDeepLink() {
        return this.mOrderStatusesDeepLink;
    }

    public String getOrderStatusesTitle() {
        return this.mOrderStatusesTitle;
    }

    public HomePagePriceChopItemHolder getPriceChop() {
        return this.mHomePagePriceChopItemHolder;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte(this.mLandOnHomePage ? (byte) 1 : 0);
        parcel.writeInt(this.mNotificationRowId);
        parcel.writeInt(this.mOrderStatusRowId);
        parcel.writeInt(this.mCommerceLoanRowId);
        parcel.writeTypedList(this.mNotifications);
        parcel.writeTypedList(this.mOrderStatuses);
        parcel.writeTypedList(this.mCommerceLoans);
        if (this.mRowIds == null) {
            parcel.writeByte(0);
        } else {
            parcel.writeByte(1);
            parcel.writeList(this.mRowIds);
        }
        parcel.writeTypedList(this.mRowOrdering);
        parcel.writeInt(this.mProductsMap == null ? 0 : this.mProductsMap.size());
        if (this.mProductsMap != null) {
            for (Entry entry : this.mProductsMap.entrySet()) {
                parcel.writeLong(((Long) entry.getKey()).longValue());
                parcel.writeParcelable((Parcelable) entry.getValue(), 0);
            }
        }
        parcel.writeInt(this.mVideoMaps == null ? 0 : this.mVideoMaps.size());
        if (this.mVideoMaps != null) {
            for (Entry entry2 : this.mVideoMaps.entrySet()) {
                parcel.writeLong(((Long) entry2.getKey()).longValue());
                parcel.writeParcelable((Parcelable) entry2.getValue(), 0);
            }
        }
        parcel.writeString(this.mNotificationsTitle);
        parcel.writeString(this.mNotificationViewAllDeepLink);
        parcel.writeString(this.mOrderStatusesDeepLink);
        parcel.writeString(this.mOrderStatusesTitle);
        parcel.writeString(this.mCommerceLoanDeepLink);
        parcel.writeString(this.mTabToAppendHomePageTo);
    }
}
