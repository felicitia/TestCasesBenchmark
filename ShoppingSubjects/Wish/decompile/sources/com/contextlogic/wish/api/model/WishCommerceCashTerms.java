package com.contextlogic.wish.api.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.contextlogic.wish.util.JsonUtil;
import com.contextlogic.wish.util.JsonUtil.DataParser;
import com.threatmetrix.TrustDefender.StrongAuth;
import java.text.ParseException;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

public class WishCommerceCashTerms extends BaseModel implements Parcelable {
    public static final Creator<WishCommerceCashSpecs> CREATOR = new Creator<WishCommerceCashSpecs>() {
        public WishCommerceCashSpecs createFromParcel(Parcel parcel) {
            return new WishCommerceCashSpecs(parcel);
        }

        public WishCommerceCashSpecs[] newArray(int i) {
            return new WishCommerceCashSpecs[i];
        }
    };
    private ArrayList<Term> mTerms;

    public static class Term extends BaseModel implements Parcelable {
        public static final Creator<Term> CREATOR = new Creator<Term>() {
            public Term createFromParcel(Parcel parcel) {
                return new Term(parcel);
            }

            public Term[] newArray(int i) {
                return new Term[i];
            }
        };
        private ArrayList<String> mParagraphs;
        private String mTitle;

        public int describeContents() {
            return 0;
        }

        public String getTitle() {
            return this.mTitle;
        }

        public ArrayList<String> getParagraphs() {
            return this.mParagraphs;
        }

        /* access modifiers changed from: protected */
        public void parseJson(JSONObject jSONObject) throws JSONException, ParseException {
            this.mTitle = JsonUtil.optString(jSONObject, StrongAuth.AUTH_TITLE);
            this.mParagraphs = JsonUtil.parseArray(jSONObject, "paragraphs", new DataParser<String, String>() {
                public String parseData(String str) throws JSONException {
                    return str;
                }
            });
        }

        public Term(JSONObject jSONObject) throws JSONException, ParseException {
            super(jSONObject);
        }

        protected Term(Parcel parcel) {
            this.mTitle = parcel.readString();
            this.mParagraphs = parcel.createStringArrayList();
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.mTitle);
            parcel.writeStringList(this.mParagraphs);
        }
    }

    public int describeContents() {
        return 0;
    }

    public ArrayList<Term> getTerms() {
        return this.mTerms;
    }

    public WishCommerceCashTerms(JSONObject jSONObject) throws JSONException, ParseException {
        super(jSONObject);
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) throws JSONException, ParseException {
        this.mTerms = JsonUtil.parseArray(jSONObject, "terms", new DataParser<Term, JSONObject>() {
            public Term parseData(JSONObject jSONObject) throws JSONException, ParseException {
                return new Term(jSONObject);
            }
        });
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(this.mTerms);
    }
}
