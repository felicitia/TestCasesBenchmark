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

public class WishCommerceCashHelpInfo extends BaseModel implements Parcelable {
    public static final Creator<WishCommerceCashHelpInfo> CREATOR = new Creator<WishCommerceCashHelpInfo>() {
        public WishCommerceCashHelpInfo createFromParcel(Parcel parcel) {
            return new WishCommerceCashHelpInfo(parcel);
        }

        public WishCommerceCashHelpInfo[] newArray(int i) {
            return new WishCommerceCashHelpInfo[i];
        }
    };
    private ArrayList<HelpQuestion> mHelpQuestions;
    private boolean mHideTermsAndConditions;
    private boolean mHideTitle;
    private String mMessage;
    private String mTitle;

    public static class HelpQuestion implements Parcelable {
        public static final Creator<HelpQuestion> CREATOR = new Creator<HelpQuestion>() {
            public HelpQuestion createFromParcel(Parcel parcel) {
                return new HelpQuestion(parcel);
            }

            public HelpQuestion[] newArray(int i) {
                return new HelpQuestion[i];
            }
        };
        private String mAnswer;
        private String mQuestion;

        public int describeContents() {
            return 0;
        }

        public String getQuestion() {
            return this.mQuestion;
        }

        public String getAnswer() {
            return this.mAnswer;
        }

        public HelpQuestion(String str, String str2) {
            this.mQuestion = str;
            this.mAnswer = str2;
        }

        protected HelpQuestion(Parcel parcel) {
            this.mQuestion = parcel.readString();
            this.mAnswer = parcel.readString();
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.mQuestion);
            parcel.writeString(this.mAnswer);
        }
    }

    public int describeContents() {
        return 0;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public String getMessage() {
        return this.mMessage;
    }

    public ArrayList<HelpQuestion> getHelpQuestions() {
        return this.mHelpQuestions;
    }

    public boolean shouldHideTermsAndConditions() {
        return this.mHideTermsAndConditions;
    }

    public boolean shouldHideTitle() {
        return this.mHideTitle;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mTitle);
        parcel.writeString(this.mMessage);
        parcel.writeTypedList(this.mHelpQuestions);
        parcel.writeByte(this.mHideTermsAndConditions ? (byte) 1 : 0);
        parcel.writeByte(this.mHideTitle ? (byte) 1 : 0);
    }

    public WishCommerceCashHelpInfo(JSONObject jSONObject) throws JSONException, ParseException {
        super(jSONObject);
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) throws JSONException, ParseException {
        if (JsonUtil.hasValue(jSONObject, StrongAuth.AUTH_TITLE)) {
            this.mTitle = jSONObject.optString(StrongAuth.AUTH_TITLE);
        }
        if (JsonUtil.hasValue(jSONObject, "message")) {
            this.mMessage = jSONObject.optString("message");
        }
        if (JsonUtil.hasValue(jSONObject, "questions")) {
            this.mHelpQuestions = JsonUtil.parseArray(jSONObject, "questions", new DataParser<HelpQuestion, JSONObject>() {
                public HelpQuestion parseData(JSONObject jSONObject) throws JSONException {
                    return new HelpQuestion(jSONObject.optString("q"), jSONObject.optString("a"));
                }
            });
        }
        this.mHideTermsAndConditions = jSONObject.optBoolean("hide_terms");
        this.mHideTitle = jSONObject.optBoolean("hide_title");
    }

    protected WishCommerceCashHelpInfo(Parcel parcel) {
        this.mTitle = parcel.readString();
        this.mMessage = parcel.readString();
        this.mHelpQuestions = parcel.createTypedArrayList(HelpQuestion.CREATOR);
        boolean z = false;
        this.mHideTermsAndConditions = parcel.readByte() != 0;
        if (parcel.readByte() != 0) {
            z = true;
        }
        this.mHideTitle = z;
    }
}
