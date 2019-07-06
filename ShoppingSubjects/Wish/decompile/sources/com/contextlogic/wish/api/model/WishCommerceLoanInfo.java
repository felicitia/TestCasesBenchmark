package com.contextlogic.wish.api.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.util.DateUtil;
import com.contextlogic.wish.util.TimezoneUtil;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import org.json.JSONException;
import org.json.JSONObject;

public class WishCommerceLoanInfo extends BaseModel implements Parcelable {
    public static final Creator<WishCommerceLoanInfo> CREATOR = new Creator<WishCommerceLoanInfo>() {
        public WishCommerceLoanInfo createFromParcel(Parcel parcel) {
            return new WishCommerceLoanInfo(parcel);
        }

        public WishCommerceLoanInfo[] newArray(int i) {
            return new WishCommerceLoanInfo[i];
        }
    };
    private WishCreditCardInfo mCreditCardBillingInfo;
    private Date mPreferredDueDate;

    public int describeContents() {
        return 0;
    }

    public WishCommerceLoanInfo(JSONObject jSONObject) throws JSONException, ParseException {
        super(jSONObject);
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) throws JSONException, ParseException {
        if (jSONObject.has("maturity_date")) {
            this.mPreferredDueDate = DateUtil.parseIsoDate(jSONObject.getString("maturity_date"), true);
            this.mPreferredDueDate = new Date(TimezoneUtil.utcToDefaultTimezone(this.mPreferredDueDate.getTime()));
        } else {
            this.mPreferredDueDate = null;
        }
        if (!ExperimentDataCenter.getInstance().canCheckoutWithCreditCard(null) || !jSONObject.has("processor_details")) {
            this.mCreditCardBillingInfo = null;
        } else {
            this.mCreditCardBillingInfo = new WishCreditCardInfo(jSONObject.getJSONObject("processor_details"));
        }
    }

    protected WishCommerceLoanInfo(Parcel parcel) {
        this.mPreferredDueDate = null;
        if (parcel.readByte() != 0) {
            Date date = new Date(parcel.readLong());
            if (date.after(Calendar.getInstance().getTime())) {
                this.mPreferredDueDate = date;
            }
        }
        this.mCreditCardBillingInfo = (WishCreditCardInfo) parcel.readParcelable(WishCreditCardInfo.class.getClassLoader());
    }

    public Date getPreferredDueDate() {
        return this.mPreferredDueDate;
    }

    public WishCreditCardInfo getCreditCardInfo() {
        return this.mCreditCardBillingInfo;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte((byte) (this.mPreferredDueDate != null ? 1 : 0));
        if (this.mPreferredDueDate != null) {
            parcel.writeLong(this.mPreferredDueDate.getTime());
        }
        parcel.writeParcelable(this.mCreditCardBillingInfo, 0);
    }
}
