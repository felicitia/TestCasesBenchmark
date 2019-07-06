package com.contextlogic.wish.api.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.threatmetrix.TrustDefender.StrongAuth;
import java.text.ParseException;
import org.json.JSONException;
import org.json.JSONObject;

public class WishLoanRepaymentBannerSpec extends BaseModel implements Parcelable {
    public static final Creator<WishLoanRepaymentBannerSpec> CREATOR = new Creator<WishLoanRepaymentBannerSpec>() {
        public WishLoanRepaymentBannerSpec createFromParcel(Parcel parcel) {
            return new WishLoanRepaymentBannerSpec(parcel);
        }

        public WishLoanRepaymentBannerSpec[] newArray(int i) {
            return new WishLoanRepaymentBannerSpec[i];
        }
    };
    private String mMessage;
    private String mOrderDetailsButtonText;
    private String mPayNowButtonText;
    private String mSuccessSheetTitle;
    private String mTitle;
    private String mTransactionId;

    public int describeContents() {
        return 0;
    }

    public WishLoanRepaymentBannerSpec(JSONObject jSONObject) throws JSONException, ParseException {
        super(jSONObject);
    }

    private WishLoanRepaymentBannerSpec(Parcel parcel) {
        this.mTitle = parcel.readString();
        this.mMessage = parcel.readString();
        this.mOrderDetailsButtonText = parcel.readString();
        this.mPayNowButtonText = parcel.readString();
        this.mTransactionId = parcel.readString();
        this.mSuccessSheetTitle = parcel.readString();
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) throws JSONException, ParseException {
        this.mTitle = jSONObject.getString(StrongAuth.AUTH_TITLE);
        this.mMessage = jSONObject.getString("message");
        this.mOrderDetailsButtonText = jSONObject.getString("order_details_button");
        this.mPayNowButtonText = jSONObject.getString("pay_now_button");
        this.mTransactionId = jSONObject.getString("transaction_id");
        this.mSuccessSheetTitle = jSONObject.getString("success_sheet_title");
    }

    public String getTitle() {
        return this.mTitle;
    }

    public String getMessage() {
        return this.mMessage;
    }

    public String getOrderDetailsButtonText() {
        return this.mOrderDetailsButtonText;
    }

    public String getPayNowButtonText() {
        return this.mPayNowButtonText;
    }

    public String getSuccessSheetTitle() {
        return this.mSuccessSheetTitle;
    }

    public String getTransactionId() {
        return this.mTransactionId;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mTitle);
        parcel.writeString(this.mMessage);
        parcel.writeString(this.mOrderDetailsButtonText);
        parcel.writeString(this.mPayNowButtonText);
        parcel.writeString(this.mTransactionId);
        parcel.writeString(this.mSuccessSheetTitle);
    }
}
