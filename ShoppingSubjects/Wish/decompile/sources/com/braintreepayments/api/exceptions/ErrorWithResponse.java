package com.braintreepayments.api.exceptions;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class ErrorWithResponse extends Exception implements Parcelable {
    public static final Creator<ErrorWithResponse> CREATOR = new Creator<ErrorWithResponse>() {
        public ErrorWithResponse createFromParcel(Parcel parcel) {
            return new ErrorWithResponse(parcel);
        }

        public ErrorWithResponse[] newArray(int i) {
            return new ErrorWithResponse[i];
        }
    };
    private List<BraintreeError> mFieldErrors;
    private String mMessage;
    private String mOriginalResponse;
    private int mStatusCode;

    public int describeContents() {
        return 0;
    }

    public ErrorWithResponse(int i, String str) {
        this.mStatusCode = i;
        this.mOriginalResponse = str;
        try {
            parseJson(str);
        } catch (JSONException unused) {
            this.mMessage = "Parsing error response failed";
            this.mFieldErrors = new ArrayList();
        }
    }

    private ErrorWithResponse() {
    }

    public static ErrorWithResponse fromJson(String str) throws JSONException {
        ErrorWithResponse errorWithResponse = new ErrorWithResponse();
        errorWithResponse.mOriginalResponse = str;
        errorWithResponse.parseJson(str);
        return errorWithResponse;
    }

    private void parseJson(String str) throws JSONException {
        JSONObject jSONObject = new JSONObject(str);
        this.mMessage = jSONObject.getJSONObject("error").getString("message");
        this.mFieldErrors = BraintreeError.fromJsonArray(jSONObject.optJSONArray("fieldErrors"));
    }

    public String getMessage() {
        return this.mMessage;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ErrorWithResponse (");
        sb.append(this.mStatusCode);
        sb.append("): ");
        sb.append(this.mMessage);
        sb.append("\n");
        sb.append(this.mFieldErrors.toString());
        return sb.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mStatusCode);
        parcel.writeString(this.mMessage);
        parcel.writeString(this.mOriginalResponse);
        parcel.writeTypedList(this.mFieldErrors);
    }

    protected ErrorWithResponse(Parcel parcel) {
        this.mStatusCode = parcel.readInt();
        this.mMessage = parcel.readString();
        this.mOriginalResponse = parcel.readString();
        this.mFieldErrors = parcel.createTypedArrayList(BraintreeError.CREATOR);
    }
}
