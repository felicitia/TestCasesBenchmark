package com.braintreepayments.api.exceptions;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.braintreepayments.api.Json;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class BraintreeError implements Parcelable {
    public static final Creator<BraintreeError> CREATOR = new Creator<BraintreeError>() {
        public BraintreeError createFromParcel(Parcel parcel) {
            return new BraintreeError(parcel);
        }

        public BraintreeError[] newArray(int i) {
            return new BraintreeError[i];
        }
    };
    private String mField;
    private List<BraintreeError> mFieldErrors;
    private String mMessage;

    public int describeContents() {
        return 0;
    }

    public static List<BraintreeError> fromJsonArray(JSONArray jSONArray) {
        if (jSONArray == null) {
            jSONArray = new JSONArray();
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                arrayList.add(fromJson(jSONArray.getJSONObject(i)));
            } catch (JSONException unused) {
            }
        }
        return arrayList;
    }

    public static BraintreeError fromJson(JSONObject jSONObject) {
        BraintreeError braintreeError = new BraintreeError();
        braintreeError.mField = Json.optString(jSONObject, "field", null);
        braintreeError.mMessage = Json.optString(jSONObject, "message", null);
        braintreeError.mFieldErrors = fromJsonArray(jSONObject.optJSONArray("fieldErrors"));
        return braintreeError;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("BraintreeError for ");
        sb.append(this.mField);
        sb.append(": ");
        sb.append(this.mMessage);
        sb.append(" -> ");
        sb.append(this.mFieldErrors != null ? this.mFieldErrors.toString() : "");
        return sb.toString();
    }

    public BraintreeError() {
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mField);
        parcel.writeString(this.mMessage);
        parcel.writeTypedList(this.mFieldErrors);
    }

    protected BraintreeError(Parcel parcel) {
        this.mField = parcel.readString();
        this.mMessage = parcel.readString();
        this.mFieldErrors = parcel.createTypedArrayList(CREATOR);
    }
}
