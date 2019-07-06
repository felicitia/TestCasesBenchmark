package com.contextlogic.wish.api.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.contextlogic.wish.activity.feed.dailygiveaway.DailyGiveawayFeedView.DailyGiveawayState;
import com.contextlogic.wish.activity.feed.dailyraffle.DailyRaffleFeedView.DailyRaffleWinState;
import com.contextlogic.wish.util.DateUtil;
import com.contextlogic.wish.util.JsonUtil;
import com.contextlogic.wish.util.JsonUtil.DataParser;
import com.threatmetrix.TrustDefender.StrongAuth;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class WishCurrentDailyGiveawayInfo extends BaseModel implements Parcelable {
    public static final Creator<WishCurrentDailyGiveawayInfo> CREATOR = new Creator<WishCurrentDailyGiveawayInfo>() {
        public WishCurrentDailyGiveawayInfo createFromParcel(Parcel parcel) {
            return new WishCurrentDailyGiveawayInfo(parcel);
        }

        public WishCurrentDailyGiveawayInfo[] newArray(int i) {
            return new WishCurrentDailyGiveawayInfo[i];
        }
    };
    private String mBannerText;
    private WishProduct mClaimedProduct;
    private String mMessage;
    private int mPercentageOff;
    private ArrayList<WishProduct> mProducts;
    private Date mRaffleChooseWinnersTime;
    private String mRaffleCloseMessage;
    private String mRaffleStatusMessage;
    private Date mRaffleTimeEnd;
    private String mSelectedItemCid;
    private DailyGiveawayState mState;
    private String mStatusMessage;
    private String mStatusTitle;
    private String mSubtitle;
    private Date mTimeLeftToClaim;
    private String mTitle;
    private DailyRaffleWinState mWinState;
    private HashMap<String, List<WishUser>> mWinnerList;
    private String mWinnersBeingChosenMessage1;
    private String mWinnersBeingChosenMessage2;

    public int describeContents() {
        return 0;
    }

    public WishCurrentDailyGiveawayInfo(JSONObject jSONObject) throws JSONException, ParseException {
        super(jSONObject);
    }

    public ArrayList<WishProduct> getProducts() {
        return this.mProducts;
    }

    public DailyGiveawayState getState() {
        return this.mState;
    }

    public WishProduct getClaimedProduct() {
        return this.mClaimedProduct;
    }

    public String getSelectedItemCid() {
        return this.mSelectedItemCid;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public String getMessage() {
        return this.mMessage;
    }

    public String getStatusTitle() {
        return this.mStatusTitle;
    }

    public String getStatusMessage() {
        return this.mStatusMessage;
    }

    public String getSubtitle() {
        return this.mSubtitle;
    }

    public String getRaffleStatusMessage() {
        return this.mRaffleStatusMessage;
    }

    public String getRaffleCloseMessage() {
        return this.mRaffleCloseMessage;
    }

    public Date getRaffleTimeEnd() {
        return this.mRaffleTimeEnd;
    }

    public Date getRaffleChooseWinnersTime() {
        return this.mRaffleChooseWinnersTime;
    }

    public String getWinnersBeingChosenMessage1() {
        return this.mWinnersBeingChosenMessage1;
    }

    public String getWinnersBeingChosenMessage2() {
        return this.mWinnersBeingChosenMessage2;
    }

    public DailyRaffleWinState getWinState() {
        return this.mWinState;
    }

    public HashMap<String, List<WishUser>> getWinnerDict() {
        return this.mWinnerList;
    }

    public int getPercentageOff() {
        return this.mPercentageOff;
    }

    public Date getTimeLeftToClaim() {
        return this.mTimeLeftToClaim;
    }

    public String getRaffleBannerText() {
        return this.mBannerText;
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) throws JSONException, ParseException {
        if (JsonUtil.hasValue(jSONObject, "giveaways")) {
            this.mProducts = JsonUtil.parseArray(jSONObject, "giveaways", new DataParser<WishProduct, JSONObject>() {
                public WishProduct parseData(JSONObject jSONObject) throws JSONException, ParseException {
                    return new WishProduct(jSONObject);
                }
            });
        }
        if (JsonUtil.hasValue(jSONObject, "last_giveaway")) {
            this.mClaimedProduct = new WishProduct(jSONObject.getJSONObject("last_giveaway"));
        }
        this.mState = DailyGiveawayState.fromInteger(jSONObject.optInt("status_type", 0));
        this.mTitle = jSONObject.optString(StrongAuth.AUTH_TITLE);
        this.mMessage = jSONObject.optString("message");
        this.mSubtitle = jSONObject.optString("subtitle");
        this.mStatusTitle = jSONObject.optString("status_title");
        this.mStatusMessage = jSONObject.optString("status_message");
        this.mRaffleStatusMessage = jSONObject.optString("raffle_status_message");
        this.mRaffleCloseMessage = jSONObject.optString("raffle_closing_status_message");
        if (JsonUtil.hasValue(jSONObject, "raffle_end_time")) {
            this.mRaffleTimeEnd = DateUtil.parseIsoDate(jSONObject.optString("raffle_end_time"));
        }
        if (JsonUtil.hasValue(jSONObject, "choose_winner_time")) {
            this.mRaffleChooseWinnersTime = DateUtil.parseIsoDate(jSONObject.optString("choose_winner_time"));
        }
        this.mWinnersBeingChosenMessage1 = jSONObject.optString("raffle_winners_being_chosen_message_1");
        this.mWinnersBeingChosenMessage2 = jSONObject.optString("raffle_winners_being_chosen_message_2");
        this.mWinState = DailyRaffleWinState.fromInteger(jSONObject.optInt("raffle_win_state", 0));
        if (JsonUtil.hasValue(jSONObject, "time_left_to_claim")) {
            this.mTimeLeftToClaim = DateUtil.parseIsoDate(jSONObject.optString("time_left_to_claim"));
        }
        if (JsonUtil.hasValue(jSONObject, "winners")) {
            this.mWinnerList = new HashMap<>();
            JSONObject jSONObject2 = jSONObject.getJSONObject("winners");
            Iterator keys = jSONObject2.keys();
            while (keys.hasNext()) {
                String str = (String) keys.next();
                this.mWinnerList.put(str, JsonUtil.parseArray(jSONObject2, str, new DataParser<WishUser, JSONObject>() {
                    public WishUser parseData(JSONObject jSONObject) throws JSONException, ParseException {
                        return new WishUser(jSONObject);
                    }
                }));
            }
        }
        this.mSelectedItemCid = jSONObject.optString("selected_item_cid", null);
        this.mPercentageOff = jSONObject.optInt("percentage_off", -1);
        this.mBannerText = jSONObject.optString("raffle_banner_text", null);
    }

    protected WishCurrentDailyGiveawayInfo(Parcel parcel) {
        this.mProducts = parcel.readArrayList(WishProduct.class.getClassLoader());
        this.mClaimedProduct = (WishProduct) parcel.readParcelable(WishProduct.class.getClassLoader());
        this.mState = DailyGiveawayState.fromInteger(parcel.readInt());
        this.mWinState = DailyRaffleWinState.fromInteger(parcel.readInt());
        this.mTitle = parcel.readString();
        this.mSubtitle = parcel.readString();
        this.mMessage = parcel.readString();
        this.mStatusTitle = parcel.readString();
        this.mStatusMessage = parcel.readString();
        this.mRaffleStatusMessage = parcel.readString();
        this.mRaffleCloseMessage = parcel.readString();
        if (parcel.readByte() != 0) {
            this.mRaffleTimeEnd = new Date(parcel.readLong());
        }
        this.mSelectedItemCid = parcel.readString();
        this.mPercentageOff = parcel.readInt();
        this.mWinnersBeingChosenMessage1 = parcel.readString();
        this.mWinnersBeingChosenMessage2 = parcel.readString();
        if (parcel.readByte() != 0) {
            this.mRaffleChooseWinnersTime = new Date(parcel.readLong());
        }
        if (parcel.readByte() != 0) {
            this.mTimeLeftToClaim = new Date(parcel.readLong());
        }
        this.mBannerText = parcel.readString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeList(this.mProducts);
        parcel.writeParcelable(this.mClaimedProduct, i);
        parcel.writeInt(this.mState.ordinal());
        parcel.writeInt(this.mWinState.ordinal());
        parcel.writeString(this.mTitle);
        parcel.writeString(this.mSubtitle);
        parcel.writeString(this.mMessage);
        parcel.writeString(this.mStatusTitle);
        parcel.writeString(this.mStatusMessage);
        parcel.writeString(this.mRaffleStatusMessage);
        parcel.writeString(this.mRaffleCloseMessage);
        int i2 = 0;
        parcel.writeByte((byte) (this.mRaffleTimeEnd != null ? 1 : 0));
        if (this.mRaffleTimeEnd != null) {
            parcel.writeLong(this.mRaffleTimeEnd.getTime());
        }
        parcel.writeString(this.mSelectedItemCid);
        parcel.writeInt(this.mPercentageOff);
        parcel.writeString(this.mWinnersBeingChosenMessage1);
        parcel.writeString(this.mWinnersBeingChosenMessage2);
        parcel.writeByte((byte) (this.mRaffleChooseWinnersTime != null ? 1 : 0));
        if (this.mRaffleChooseWinnersTime != null) {
            parcel.writeLong(this.mRaffleChooseWinnersTime.getTime());
        }
        if (this.mTimeLeftToClaim != null) {
            i2 = 1;
        }
        parcel.writeByte((byte) i2);
        if (this.mTimeLeftToClaim != null) {
            parcel.writeLong(this.mTimeLeftToClaim.getTime());
        }
        parcel.writeString(this.mBannerText);
    }
}
