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

public class WishCommerceLoanLearnMoreInfo extends BaseModel implements Parcelable {
    public static final Creator<WishCommerceLoanLearnMoreInfo> CREATOR = new Creator<WishCommerceLoanLearnMoreInfo>() {
        public WishCommerceLoanLearnMoreInfo createFromParcel(Parcel parcel) {
            return new WishCommerceLoanLearnMoreInfo(parcel);
        }

        public WishCommerceLoanLearnMoreInfo[] newArray(int i) {
            return new WishCommerceLoanLearnMoreInfo[i];
        }
    };
    private String mMainTitle;
    private ArrayList<LearnMoreInfoParagraph> mParagraphs;

    public static class LearnMoreInfoParagraph implements Parcelable {
        public static final Creator<LearnMoreInfoParagraph> CREATOR = new Creator<LearnMoreInfoParagraph>() {
            public LearnMoreInfoParagraph createFromParcel(Parcel parcel) {
                return new LearnMoreInfoParagraph(parcel);
            }

            public LearnMoreInfoParagraph[] newArray(int i) {
                return new LearnMoreInfoParagraph[i];
            }
        };
        private String mDescription;
        private String mTitle;

        public int describeContents() {
            return 0;
        }

        public LearnMoreInfoParagraph(String str, String str2) {
            this.mTitle = str;
            this.mDescription = str2;
        }

        protected LearnMoreInfoParagraph(Parcel parcel) {
            this.mTitle = parcel.readString();
            this.mDescription = parcel.readString();
        }

        public String getTitle() {
            return this.mTitle;
        }

        public String getDescription() {
            return this.mDescription;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.mTitle);
            parcel.writeString(this.mDescription);
        }
    }

    public int describeContents() {
        return 0;
    }

    public WishCommerceLoanLearnMoreInfo(JSONObject jSONObject) throws JSONException, ParseException {
        super(jSONObject);
    }

    protected WishCommerceLoanLearnMoreInfo(Parcel parcel) {
        this.mMainTitle = parcel.readString();
        this.mParagraphs = parcel.createTypedArrayList(LearnMoreInfoParagraph.CREATOR);
    }

    public String getMainTitle() {
        return this.mMainTitle;
    }

    public ArrayList<LearnMoreInfoParagraph> getParagraphs() {
        return this.mParagraphs;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mMainTitle);
        parcel.writeTypedList(this.mParagraphs);
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) throws JSONException, ParseException {
        if (JsonUtil.hasValue(jSONObject, "main_title")) {
            this.mMainTitle = jSONObject.optString("main_title");
        }
        if (JsonUtil.hasValue(jSONObject, "paragraphs")) {
            this.mParagraphs = JsonUtil.parseArray(jSONObject, "paragraphs", new DataParser<LearnMoreInfoParagraph, JSONObject>() {
                public LearnMoreInfoParagraph parseData(JSONObject jSONObject) throws JSONException {
                    return new LearnMoreInfoParagraph(jSONObject.optString(StrongAuth.AUTH_TITLE), jSONObject.optString("description"));
                }
            });
        }
    }
}
