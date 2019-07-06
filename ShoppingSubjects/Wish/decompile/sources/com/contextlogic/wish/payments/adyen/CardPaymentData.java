package com.contextlogic.wish.payments.adyen;

import android.util.Log;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import org.json.JSONException;
import org.json.JSONObject;

public class CardPaymentData {
    private static final String tag = "CardPaymentData";
    private String cardHolderName;
    private String cvc;
    private String expiryMonth;
    private String expiryYear;
    private Date generationTime;
    private String number;

    public void setNumber(String str) {
        this.number = str;
    }

    public void setExpiryMonth(String str) {
        this.expiryMonth = str;
    }

    public void setExpiryYear(String str) {
        this.expiryYear = str;
    }

    public void setCardHolderName(String str) {
        this.cardHolderName = str;
    }

    public void setCvc(String str) {
        this.cvc = str;
    }

    public void setGenerationTime(Date date) {
        this.generationTime = date;
    }

    public String serialize() throws EncrypterException {
        JSONObject jSONObject = new JSONObject();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        try {
            jSONObject.put("generationtime", simpleDateFormat.format(this.generationTime));
            jSONObject.put("number", this.number);
            jSONObject.put("holderName", this.cardHolderName);
            jSONObject.put("cvc", this.cvc);
            jSONObject.put("expiryMonth", this.expiryMonth);
            jSONObject.put("expiryYear", this.expiryYear);
            return Adyen.encryptData(jSONObject.toString());
        } catch (JSONException e) {
            Log.e(tag, e.getMessage(), e);
            return null;
        }
    }

    public String toString() {
        JSONObject jSONObject = new JSONObject();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        try {
            jSONObject.put("generationtime", simpleDateFormat.format(this.generationTime));
            if (this.number.length() >= 4) {
                jSONObject.put("number", this.number.substring(0, 3));
            }
            jSONObject.put("holderName", this.cardHolderName);
        } catch (JSONException e) {
            Log.e(tag, e.getMessage(), e);
        }
        return jSONObject.toString();
    }
}
