package com.contextlogic.wish.api.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.contextlogic.wish.R;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.util.DateUtil;
import com.contextlogic.wish.util.JsonUtil;
import com.contextlogic.wish.util.JsonUtil.DataParser;
import com.threatmetrix.TrustDefender.StrongAuth;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import org.json.JSONException;
import org.json.JSONObject;

public class WishPartnerSummary extends BaseModel implements Parcelable {
    public static final Creator<WishPartnerSummary> CREATOR = new Creator<WishPartnerSummary>() {
        public WishPartnerSummary createFromParcel(Parcel parcel) {
            return new WishPartnerSummary(parcel);
        }

        public WishPartnerSummary[] newArray(int i) {
            return new WishPartnerSummary[i];
        }
    };
    private WishPartnerCashOutInfo mCashOutInfo;
    private WishPartnerDashboardInfo mDashboardInfo;
    private WishPartnerMainEmptyStateSpec mEmptyStateSpec;
    private ArrayList<WishPartnerEvent> mEvents;
    private String mNextOffset;
    private boolean mNoMoreItems;

    public enum CashOutOption {
        WISH_CASH,
        PAYPAL;

        public static CashOutOption fromInt(int i) {
            switch (i) {
                case 1:
                    return WISH_CASH;
                case 2:
                    return PAYPAL;
                default:
                    return WISH_CASH;
            }
        }
    }

    public static class DefaultPaymentAccount extends BaseModel implements Parcelable {
        public static final Creator<DefaultPaymentAccount> CREATOR = new Creator<DefaultPaymentAccount>() {
            public DefaultPaymentAccount createFromParcel(Parcel parcel) {
                return new DefaultPaymentAccount(parcel);
            }

            public DefaultPaymentAccount[] newArray(int i) {
                return new DefaultPaymentAccount[i];
            }
        };
        private String mDisplayText;
        private String mEmail;
        private String mId;

        public int describeContents() {
            return 0;
        }

        public DefaultPaymentAccount(JSONObject jSONObject) throws JSONException, ParseException {
            super(jSONObject);
        }

        /* access modifiers changed from: protected */
        public void parseJson(JSONObject jSONObject) throws JSONException, ParseException {
            this.mDisplayText = JsonUtil.optString(jSONObject, "display_text");
            this.mEmail = JsonUtil.optString(jSONObject, "paypal_email");
            this.mId = JsonUtil.optString(jSONObject, "id");
        }

        public String getDisplayText() {
            return this.mDisplayText;
        }

        public String getId() {
            return this.mId;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.mDisplayText);
            parcel.writeString(this.mEmail);
            parcel.writeString(this.mId);
        }

        protected DefaultPaymentAccount(Parcel parcel) {
            this.mDisplayText = parcel.readString();
            this.mEmail = parcel.readString();
            this.mId = parcel.readString();
        }
    }

    public static class WishPartnerCashOutInfo extends BaseModel implements Parcelable {
        public static final Creator<WishPartnerCashOutInfo> CREATOR = new Creator<WishPartnerCashOutInfo>() {
            public WishPartnerCashOutInfo createFromParcel(Parcel parcel) {
                return new WishPartnerCashOutInfo(parcel);
            }

            public WishPartnerCashOutInfo[] newArray(int i) {
                return new WishPartnerCashOutInfo[i];
            }
        };
        private String mCashOutAmountText;
        private String mCashOutConfirmModalButtonCancel;
        private String mCashOutConfirmModalButtonConfirm;
        private String mCashOutConfirmModalMessage;
        private String mCashOutConfirmModalTitle;
        private ArrayList<WishPartnerCashOutOption> mCashOutOptions;
        private String mCashOutRemainingText;
        private String mCashOutTitle;
        private String mCashoutOptionsHeader;
        private String mCashoutWithdrawButtonText;

        public int describeContents() {
            return 0;
        }

        public WishPartnerCashOutInfo(JSONObject jSONObject) throws JSONException, ParseException {
            super(jSONObject);
        }

        /* access modifiers changed from: protected */
        public void parseJson(JSONObject jSONObject) throws JSONException, ParseException {
            if (JsonUtil.hasValue(jSONObject, "cash_out_options")) {
                this.mCashOutOptions = JsonUtil.parseArray(jSONObject, "cash_out_options", new DataParser<WishPartnerCashOutOption, JSONObject>() {
                    public WishPartnerCashOutOption parseData(JSONObject jSONObject) throws JSONException, ParseException {
                        return new WishPartnerCashOutOption(jSONObject);
                    }
                });
            }
            this.mCashOutTitle = JsonUtil.optString(jSONObject, "cash_out_title");
            this.mCashOutRemainingText = JsonUtil.optString(jSONObject, "cash_out_remaining_text");
            this.mCashOutAmountText = JsonUtil.optString(jSONObject, "cash_out_amount_text");
            this.mCashoutOptionsHeader = JsonUtil.optString(jSONObject, "cash_out_options_header");
            this.mCashoutWithdrawButtonText = JsonUtil.optString(jSONObject, "cash_out_withdraw_button_text");
            this.mCashOutConfirmModalTitle = JsonUtil.optString(jSONObject, "cash_out_confirm_modal_title");
            this.mCashOutConfirmModalMessage = JsonUtil.optString(jSONObject, "cash_out_confirm_modal_message");
            this.mCashOutConfirmModalButtonConfirm = JsonUtil.optString(jSONObject, "cash_out_confirm_modal_button_confirm");
            this.mCashOutConfirmModalButtonCancel = JsonUtil.optString(jSONObject, "cash_out_confirm_modal_button_cancel");
        }

        public ArrayList<WishPartnerCashOutOption> getCashOutOptions() {
            return this.mCashOutOptions;
        }

        public void setCashOutOptions(ArrayList<WishPartnerCashOutOption> arrayList) {
            this.mCashOutOptions = arrayList;
        }

        public String getCashOutTitle() {
            return this.mCashOutTitle;
        }

        public String getCashOutAmountText() {
            return this.mCashOutAmountText;
        }

        public String getCashOutRemainingText() {
            return this.mCashOutRemainingText;
        }

        public String getCashoutOptionsHeader() {
            return this.mCashoutOptionsHeader;
        }

        public String getCashoutWithdrawButtonText() {
            return this.mCashoutWithdrawButtonText;
        }

        public String getCashOutConfirmModalTitle() {
            return this.mCashOutConfirmModalTitle;
        }

        public String getCashOutConfirmModalMessage() {
            return this.mCashOutConfirmModalMessage;
        }

        public String getCashOutConfirmModalButtonConfirm() {
            return this.mCashOutConfirmModalButtonConfirm;
        }

        public String getCashOutConfirmModalButtonCancel() {
            return this.mCashOutConfirmModalButtonCancel;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeTypedList(this.mCashOutOptions);
            parcel.writeString(this.mCashOutTitle);
            parcel.writeString(this.mCashOutAmountText);
            parcel.writeString(this.mCashOutRemainingText);
            parcel.writeString(this.mCashoutOptionsHeader);
            parcel.writeString(this.mCashoutWithdrawButtonText);
            parcel.writeString(this.mCashOutConfirmModalTitle);
            parcel.writeString(this.mCashOutConfirmModalMessage);
            parcel.writeString(this.mCashOutConfirmModalButtonConfirm);
            parcel.writeString(this.mCashOutConfirmModalButtonCancel);
        }

        protected WishPartnerCashOutInfo(Parcel parcel) {
            this.mCashOutOptions = parcel.createTypedArrayList(WishPartnerCashOutOption.CREATOR);
            this.mCashOutTitle = parcel.readString();
            this.mCashOutAmountText = parcel.readString();
            this.mCashOutRemainingText = parcel.readString();
            this.mCashoutOptionsHeader = parcel.readString();
            this.mCashoutWithdrawButtonText = parcel.readString();
            this.mCashOutConfirmModalTitle = parcel.readString();
            this.mCashOutConfirmModalMessage = parcel.readString();
            this.mCashOutConfirmModalButtonConfirm = parcel.readString();
            this.mCashOutConfirmModalButtonCancel = parcel.readString();
        }
    }

    public static class WishPartnerCashOutOption extends BaseModel implements Parcelable {
        public static final Creator<WishPartnerCashOutOption> CREATOR = new Creator<WishPartnerCashOutOption>() {
            public WishPartnerCashOutOption createFromParcel(Parcel parcel) {
                return new WishPartnerCashOutOption(parcel);
            }

            public WishPartnerCashOutOption[] newArray(int i) {
                return new WishPartnerCashOutOption[i];
            }
        };
        private String mBonusText;
        private CashOutOption mCashOutOption;
        private DefaultPaymentAccount mDefaultPaymentAccount;
        private String mName;
        private String mRightText;

        public int describeContents() {
            return 0;
        }

        public WishPartnerCashOutOption(JSONObject jSONObject) throws JSONException, ParseException {
            super(jSONObject);
        }

        /* access modifiers changed from: protected */
        public void parseJson(JSONObject jSONObject) throws JSONException, ParseException {
            this.mCashOutOption = CashOutOption.fromInt(jSONObject.optInt("type"));
            this.mName = JsonUtil.optString(jSONObject, "name");
            this.mBonusText = JsonUtil.optString(jSONObject, "bonus_text");
            this.mRightText = JsonUtil.optString(jSONObject, "right_text");
            if (JsonUtil.hasValue(jSONObject, "default_payment_account")) {
                this.mDefaultPaymentAccount = new DefaultPaymentAccount(jSONObject.getJSONObject("default_payment_account"));
            } else {
                this.mDefaultPaymentAccount = null;
            }
        }

        public CashOutOption getCashOutOption() {
            return this.mCashOutOption;
        }

        public String getName() {
            return this.mName;
        }

        public String getBonusText() {
            return this.mBonusText;
        }

        public String getRightText() {
            return this.mRightText;
        }

        public DefaultPaymentAccount getDefaultPaymentAccount() {
            return this.mDefaultPaymentAccount;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.mCashOutOption == null ? -1 : this.mCashOutOption.ordinal());
            parcel.writeString(this.mName);
            parcel.writeString(this.mBonusText);
            parcel.writeString(this.mRightText);
            parcel.writeParcelable(this.mDefaultPaymentAccount, i);
        }

        protected WishPartnerCashOutOption(Parcel parcel) {
            CashOutOption cashOutOption;
            int readInt = parcel.readInt();
            if (readInt == -1) {
                cashOutOption = null;
            } else {
                cashOutOption = CashOutOption.values()[readInt];
            }
            this.mCashOutOption = cashOutOption;
            this.mName = parcel.readString();
            this.mBonusText = parcel.readString();
            this.mRightText = parcel.readString();
            this.mDefaultPaymentAccount = (DefaultPaymentAccount) parcel.readParcelable(DefaultPaymentAccount.class.getClassLoader());
        }
    }

    public static class WishPartnerDashboardInfo extends BaseModel implements Parcelable {
        public static final Creator<WishPartnerDashboardInfo> CREATOR = new Creator<WishPartnerDashboardInfo>() {
            public WishPartnerDashboardInfo createFromParcel(Parcel parcel) {
                return new WishPartnerDashboardInfo(parcel);
            }

            public WishPartnerDashboardInfo[] newArray(int i) {
                return new WishPartnerDashboardInfo[i];
            }
        };
        private WishLocalizedCurrencyValue mBalance;
        private String mBalanceTitle;
        private String mCashOutButtonText;
        private String mCommunityTabText;
        private String mMainTabText;

        public int describeContents() {
            return 0;
        }

        public WishPartnerDashboardInfo(JSONObject jSONObject) throws JSONException, ParseException {
            super(jSONObject);
        }

        /* access modifiers changed from: protected */
        public void parseJson(JSONObject jSONObject) throws JSONException, ParseException {
            this.mBalanceTitle = JsonUtil.optString(jSONObject, "balance_title");
            this.mBalance = new WishLocalizedCurrencyValue(jSONObject.optDouble("balance"), jSONObject.optJSONObject("localized_balance"));
            this.mCashOutButtonText = JsonUtil.optString(jSONObject, "cash_out_button_text");
            this.mMainTabText = JsonUtil.optString(jSONObject, "main_tab_text", WishApplication.getInstance().getString(R.string.recent_earnings));
            this.mCommunityTabText = JsonUtil.optString(jSONObject, "community_tab_text", WishApplication.getInstance().getString(R.string.community));
        }

        public String getBalanceTitle() {
            return this.mBalanceTitle;
        }

        public WishLocalizedCurrencyValue getBalance() {
            return this.mBalance;
        }

        public String getCashOutButtonText() {
            return this.mCashOutButtonText;
        }

        public String getMainTabText() {
            return this.mMainTabText;
        }

        public String getCommunityTabText() {
            return this.mCommunityTabText;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.mBalanceTitle);
            parcel.writeParcelable(this.mBalance, i);
            parcel.writeString(this.mCashOutButtonText);
            parcel.writeString(this.mMainTabText);
            parcel.writeString(this.mCommunityTabText);
        }

        protected WishPartnerDashboardInfo(Parcel parcel) {
            this.mBalanceTitle = parcel.readString();
            this.mBalance = (WishLocalizedCurrencyValue) parcel.readParcelable(WishLocalizedCurrencyValue.class.getClassLoader());
            this.mCashOutButtonText = parcel.readString();
            this.mMainTabText = parcel.readString();
            this.mCommunityTabText = parcel.readString();
        }
    }

    public static class WishPartnerEvent extends BaseModel implements Parcelable {
        public static final Creator<WishPartnerEvent> CREATOR = new Creator<WishPartnerEvent>() {
            public WishPartnerEvent createFromParcel(Parcel parcel) {
                return new WishPartnerEvent(parcel);
            }

            public WishPartnerEvent[] newArray(int i) {
                return new WishPartnerEvent[i];
            }
        };
        private WishLocalizedCurrencyValue mAmount;
        private boolean mIsCashOut;
        private String mMainImageUrl;
        private String mSecondaryImageUrl;
        private String mSubtitle;
        private Date mTimestamp;
        private String mTitle;
        private String mUserName;

        public int describeContents() {
            return 0;
        }

        public WishPartnerEvent(JSONObject jSONObject) throws JSONException, ParseException {
            super(jSONObject);
        }

        /* access modifiers changed from: protected */
        public void parseJson(JSONObject jSONObject) throws JSONException, ParseException {
            this.mMainImageUrl = JsonUtil.optString(jSONObject, "main_image_url");
            this.mSecondaryImageUrl = JsonUtil.optString(jSONObject, "secondary_image_url");
            this.mUserName = JsonUtil.optString(jSONObject, "user_name");
            this.mTimestamp = DateUtil.parseIsoDate(jSONObject.getString("timestamp"));
            this.mIsCashOut = jSONObject.optBoolean("is_cash_out");
            this.mAmount = new WishLocalizedCurrencyValue(jSONObject.getDouble("amount"), jSONObject.optJSONObject("localized_amount"));
            this.mTitle = JsonUtil.optString(jSONObject, StrongAuth.AUTH_TITLE);
            this.mSubtitle = JsonUtil.optString(jSONObject, "subtitle_text");
        }

        public String getSubtitle() {
            return this.mSubtitle;
        }

        public String geMainImageUrl() {
            return this.mMainImageUrl;
        }

        public String getSecondaryImageUrl() {
            return this.mSecondaryImageUrl;
        }

        public String getUserName() {
            return this.mUserName;
        }

        public Date getTimestamp() {
            return this.mTimestamp;
        }

        public boolean isCashOut() {
            return this.mIsCashOut;
        }

        public String getTitle() {
            return this.mTitle;
        }

        public WishLocalizedCurrencyValue getAmount() {
            return this.mAmount;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.mMainImageUrl);
            parcel.writeString(this.mSecondaryImageUrl);
            parcel.writeString(this.mUserName);
            if (this.mTimestamp != null) {
                parcel.writeLong(this.mTimestamp.getTime());
            }
            parcel.writeByte(this.mIsCashOut ? (byte) 1 : 0);
            parcel.writeString(this.mTitle);
            parcel.writeParcelable(this.mAmount, i);
            parcel.writeString(this.mSubtitle);
        }

        protected WishPartnerEvent(Parcel parcel) {
            this.mMainImageUrl = parcel.readString();
            this.mSecondaryImageUrl = parcel.readString();
            this.mUserName = parcel.readString();
            if (parcel.readByte() != 0) {
                this.mTimestamp = new Date(parcel.readLong());
            }
            this.mIsCashOut = parcel.readByte() != 0;
            this.mTitle = parcel.readString();
            this.mAmount = (WishLocalizedCurrencyValue) parcel.readParcelable(WishLocalizedCurrencyValue.class.getClassLoader());
            this.mSubtitle = parcel.readString();
        }
    }

    public static class WishPartnerMainEmptyStateSpec extends BaseModel implements Parcelable {
        public static final Creator<WishPartnerMainEmptyStateSpec> CREATOR = new Creator<WishPartnerMainEmptyStateSpec>() {
            public WishPartnerMainEmptyStateSpec createFromParcel(Parcel parcel) {
                return new WishPartnerMainEmptyStateSpec(parcel);
            }

            public WishPartnerMainEmptyStateSpec[] newArray(int i) {
                return new WishPartnerMainEmptyStateSpec[i];
            }
        };
        private String mActionText;
        private String mSubtitle;
        private String mTitle;

        public int describeContents() {
            return 0;
        }

        public WishPartnerMainEmptyStateSpec(JSONObject jSONObject) throws JSONException, ParseException {
            super(jSONObject);
        }

        /* access modifiers changed from: protected */
        public void parseJson(JSONObject jSONObject) throws JSONException {
            this.mTitle = jSONObject.optString("title_text");
            this.mSubtitle = jSONObject.optString("subtitle_text");
            this.mActionText = jSONObject.optString("action_text");
        }

        public String getTitle() {
            return this.mTitle;
        }

        public String getSubtitle() {
            return this.mSubtitle;
        }

        public String getActionText() {
            return this.mActionText;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.mTitle);
            parcel.writeString(this.mSubtitle);
            parcel.writeString(this.mActionText);
        }

        protected WishPartnerMainEmptyStateSpec(Parcel parcel) {
            this.mTitle = parcel.readString();
            this.mSubtitle = parcel.readString();
            this.mActionText = parcel.readString();
        }
    }

    public int describeContents() {
        return 0;
    }

    public WishPartnerSummary(JSONObject jSONObject) throws JSONException, ParseException {
        super(jSONObject);
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) throws JSONException, ParseException {
        if (JsonUtil.hasValue(jSONObject, "partner_dashboard_info")) {
            this.mDashboardInfo = new WishPartnerDashboardInfo(jSONObject.getJSONObject("partner_dashboard_info"));
        }
        if (JsonUtil.hasValue(jSONObject, "cash_out_info")) {
            this.mCashOutInfo = new WishPartnerCashOutInfo(jSONObject.getJSONObject("cash_out_info"));
        }
        if (JsonUtil.hasValue(jSONObject, "events")) {
            this.mEvents = JsonUtil.parseArray(jSONObject, "events", new DataParser<WishPartnerEvent, JSONObject>() {
                public WishPartnerEvent parseData(JSONObject jSONObject) throws JSONException, ParseException {
                    return new WishPartnerEvent(jSONObject);
                }
            });
        }
        this.mNextOffset = JsonUtil.optString(jSONObject, "next_offset");
        this.mNoMoreItems = jSONObject.optBoolean("no_more_items");
        if (JsonUtil.hasValue(jSONObject, "empty_state_info")) {
            this.mEmptyStateSpec = new WishPartnerMainEmptyStateSpec(jSONObject.getJSONObject("empty_state_info"));
        }
    }

    public WishPartnerMainEmptyStateSpec getEmpryStateSpec() {
        return this.mEmptyStateSpec;
    }

    public WishPartnerCashOutInfo getCashOutInfo() {
        return this.mCashOutInfo;
    }

    public WishPartnerDashboardInfo getDashboardInfo() {
        return this.mDashboardInfo;
    }

    public ArrayList<WishPartnerEvent> getEvents() {
        return this.mEvents;
    }

    public String getNextOffset() {
        return this.mNextOffset;
    }

    public boolean isNoMoreItems() {
        return this.mNoMoreItems;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.mCashOutInfo, i);
        parcel.writeParcelable(this.mDashboardInfo, i);
        parcel.writeList(this.mEvents);
        parcel.writeString(this.mNextOffset);
        parcel.writeByte(this.mNoMoreItems ? (byte) 1 : 0);
        parcel.writeParcelable(this.mEmptyStateSpec, i);
    }

    protected WishPartnerSummary(Parcel parcel) {
        this.mCashOutInfo = (WishPartnerCashOutInfo) parcel.readParcelable(WishPartnerCashOutInfo.class.getClassLoader());
        this.mDashboardInfo = (WishPartnerDashboardInfo) parcel.readParcelable(WishPartnerDashboardInfo.class.getClassLoader());
        this.mEvents = new ArrayList<>();
        parcel.readList(this.mEvents, WishPartnerEvent.class.getClassLoader());
        this.mNextOffset = parcel.readString();
        this.mNoMoreItems = parcel.readByte() != 0;
        this.mEmptyStateSpec = (WishPartnerMainEmptyStateSpec) parcel.readParcelable(WishPartnerMainEmptyStateSpec.class.getClassLoader());
    }
}
