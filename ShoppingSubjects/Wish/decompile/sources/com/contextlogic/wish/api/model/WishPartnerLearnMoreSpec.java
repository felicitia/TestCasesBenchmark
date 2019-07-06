package com.contextlogic.wish.api.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.contextlogic.wish.util.JsonUtil;
import com.contextlogic.wish.util.JsonUtil.DataParser;
import com.threatmetrix.TrustDefender.StrongAuth;
import java.text.ParseException;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class WishPartnerLearnMoreSpec extends BaseModel implements Parcelable {
    public static final Creator<WishPartnerLearnMoreSpec> CREATOR = new Creator<WishPartnerLearnMoreSpec>() {
        public WishPartnerLearnMoreSpec createFromParcel(Parcel parcel) {
            return new WishPartnerLearnMoreSpec(parcel);
        }

        public WishPartnerLearnMoreSpec[] newArray(int i) {
            return new WishPartnerLearnMoreSpec[i];
        }
    };
    private String mExampleLink;
    private String mExampleText;
    private List<WishPartnerLearnMoreFAQItem> mFAQItems;
    private String mGenerateButtonText;
    private String mInstructionsHeader;
    private String mMenuItemLable;
    private String mScreenTitle;
    private String mStepOneText;
    private String mStepThreeText;
    private String mStepTwoText;
    private String mSubtitle;
    private String mTitle;

    public static class WishPartnerLearnMoreFAQItem extends BaseModel implements Parcelable {
        public static final Creator<WishPartnerLearnMoreFAQItem> CREATOR = new Creator<WishPartnerLearnMoreFAQItem>() {
            public WishPartnerLearnMoreFAQItem createFromParcel(Parcel parcel) {
                return new WishPartnerLearnMoreFAQItem(parcel);
            }

            public WishPartnerLearnMoreFAQItem[] newArray(int i) {
                return new WishPartnerLearnMoreFAQItem[i];
            }
        };
        private String mAnswer;
        private String mQuestion;

        public int describeContents() {
            return 0;
        }

        public WishPartnerLearnMoreFAQItem(JSONObject jSONObject) throws JSONException, ParseException {
            super(jSONObject);
        }

        /* access modifiers changed from: protected */
        public void parseJson(JSONObject jSONObject) throws JSONException {
            this.mQuestion = JsonUtil.optString(jSONObject, "question");
            this.mAnswer = JsonUtil.optString(jSONObject, "answer");
        }

        public String getQuestion() {
            return this.mQuestion;
        }

        public String getAnswer() {
            return this.mAnswer;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.mQuestion);
            parcel.writeString(this.mAnswer);
        }

        protected WishPartnerLearnMoreFAQItem(Parcel parcel) {
            this.mQuestion = parcel.readString();
            this.mAnswer = parcel.readString();
        }
    }

    public int describeContents() {
        return 0;
    }

    public WishPartnerLearnMoreSpec(JSONObject jSONObject) throws JSONException, ParseException {
        super(jSONObject);
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) throws JSONException {
        this.mScreenTitle = JsonUtil.optString(jSONObject, "screen_title");
        this.mTitle = JsonUtil.optString(jSONObject, StrongAuth.AUTH_TITLE);
        this.mSubtitle = JsonUtil.optString(jSONObject, "subtitle");
        this.mInstructionsHeader = JsonUtil.optString(jSONObject, "instructions_header");
        this.mStepOneText = JsonUtil.optString(jSONObject, "step_1_text");
        this.mStepTwoText = JsonUtil.optString(jSONObject, "step_2_text");
        this.mStepThreeText = JsonUtil.optString(jSONObject, "step_3_text");
        this.mExampleText = JsonUtil.optString(jSONObject, "sharing_examples_text");
        this.mExampleLink = JsonUtil.optString(jSONObject, "sharing_examples_link");
        this.mGenerateButtonText = JsonUtil.optString(jSONObject, "generate_button_text");
        this.mMenuItemLable = JsonUtil.optString(jSONObject, "menu_item_label");
        if (jSONObject.has("faq_items")) {
            this.mFAQItems = JsonUtil.parseArray(jSONObject, "faq_items", new DataParser<WishPartnerLearnMoreFAQItem, JSONObject>() {
                public WishPartnerLearnMoreFAQItem parseData(JSONObject jSONObject) throws JSONException, ParseException {
                    return new WishPartnerLearnMoreFAQItem(jSONObject);
                }
            });
        }
    }

    public String getScreenTitle() {
        return this.mScreenTitle;
    }

    public String getMenuItemLable() {
        return this.mMenuItemLable;
    }

    public String getGenerateButtonText() {
        return this.mGenerateButtonText;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public String getSubtitle() {
        return this.mSubtitle;
    }

    public String getInstructionsHeader() {
        return this.mInstructionsHeader;
    }

    public String getStepOneText() {
        return this.mStepOneText;
    }

    public String getStepTwoText() {
        return this.mStepTwoText;
    }

    public String getStepThreeText() {
        return this.mStepThreeText;
    }

    public String getExampleText() {
        return this.mExampleText;
    }

    public String getExampleLink() {
        return this.mExampleLink;
    }

    public List<WishPartnerLearnMoreFAQItem> getFAQItems() {
        return this.mFAQItems;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mScreenTitle);
        parcel.writeString(this.mTitle);
        parcel.writeString(this.mSubtitle);
        parcel.writeString(this.mInstructionsHeader);
        parcel.writeString(this.mStepOneText);
        parcel.writeString(this.mStepTwoText);
        parcel.writeString(this.mStepThreeText);
        parcel.writeString(this.mExampleText);
        parcel.writeString(this.mExampleLink);
        parcel.writeTypedList(this.mFAQItems);
        parcel.writeString(this.mGenerateButtonText);
        parcel.writeString(this.mMenuItemLable);
    }

    protected WishPartnerLearnMoreSpec(Parcel parcel) {
        this.mScreenTitle = parcel.readString();
        this.mTitle = parcel.readString();
        this.mSubtitle = parcel.readString();
        this.mInstructionsHeader = parcel.readString();
        this.mStepOneText = parcel.readString();
        this.mStepTwoText = parcel.readString();
        this.mStepThreeText = parcel.readString();
        this.mExampleText = parcel.readString();
        this.mExampleLink = parcel.readString();
        this.mFAQItems = parcel.createTypedArrayList(WishPartnerLearnMoreFAQItem.CREATOR);
        this.mGenerateButtonText = parcel.readString();
        this.mMenuItemLable = parcel.readString();
    }
}
