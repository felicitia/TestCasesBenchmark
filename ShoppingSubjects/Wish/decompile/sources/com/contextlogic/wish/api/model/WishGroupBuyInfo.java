package com.contextlogic.wish.api.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.contextlogic.wish.util.JsonUtil;
import com.threatmetrix.TrustDefender.StrongAuth;
import java.text.ParseException;
import org.json.JSONException;
import org.json.JSONObject;

public class WishGroupBuyInfo extends BaseModel implements Parcelable {
    public static final Creator<WishGroupBuyInfo> CREATOR = new Creator<WishGroupBuyInfo>() {
        public WishGroupBuyInfo createFromParcel(Parcel parcel) {
            return new WishGroupBuyInfo(parcel);
        }

        public WishGroupBuyInfo[] newArray(int i) {
            return new WishGroupBuyInfo[i];
        }
    };
    private boolean mCanCreate;
    private String mCreateButtonText;
    private String mCreateInstruction;
    private WishImage mCreatorImage;
    private String mCreatorMessage;
    private String mCreatorName;
    private String mCreatorTitle;
    private String mDescription;
    private String mLearnMoreCreateText;
    private String mLearnMoreCreateTitle;
    private String mLearnMoreDetail;
    private String mLearnMoreJoinText;
    private String mLearnMoreJoinTitle;
    private String mLearnMoreReceiveText;
    private String mLearnMoreReceiveTitle;
    private String mLearnMoreText;
    private String mLearnMoreTitle;
    private String mSubtitle;
    private String mTitle;

    public int describeContents() {
        return 0;
    }

    protected WishGroupBuyInfo(Parcel parcel) {
        this.mTitle = parcel.readString();
        this.mCreateButtonText = parcel.readString();
        this.mDescription = parcel.readString();
        this.mLearnMoreText = parcel.readString();
        this.mLearnMoreDetail = parcel.readString();
        this.mCreateInstruction = parcel.readString();
        this.mSubtitle = parcel.readString();
        this.mCanCreate = parcel.readByte() != 0;
        this.mCreatorTitle = parcel.readString();
        this.mCreatorName = parcel.readString();
        this.mCreatorMessage = parcel.readString();
        if (parcel.readByte() != 0) {
            this.mCreatorImage = (WishImage) parcel.readParcelable(WishImage.class.getClassLoader());
        }
        this.mLearnMoreTitle = parcel.readString();
        this.mLearnMoreJoinTitle = parcel.readString();
        this.mLearnMoreJoinText = parcel.readString();
        this.mLearnMoreCreateTitle = parcel.readString();
        this.mLearnMoreCreateText = parcel.readString();
        this.mLearnMoreReceiveTitle = parcel.readString();
        this.mLearnMoreReceiveText = parcel.readString();
    }

    public WishGroupBuyInfo(JSONObject jSONObject) throws JSONException, ParseException {
        super(jSONObject);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mTitle);
        parcel.writeString(this.mCreateButtonText);
        parcel.writeString(this.mDescription);
        parcel.writeString(this.mLearnMoreText);
        parcel.writeString(this.mLearnMoreDetail);
        parcel.writeString(this.mCreateInstruction);
        parcel.writeString(this.mSubtitle);
        parcel.writeByte(this.mCanCreate ? (byte) 1 : 0);
        parcel.writeString(this.mCreatorTitle);
        parcel.writeString(this.mCreatorName);
        parcel.writeString(this.mCreatorMessage);
        parcel.writeByte((byte) (this.mCreatorImage != null ? 1 : 0));
        if (this.mCreatorImage != null) {
            parcel.writeParcelable(this.mCreatorImage, 0);
        }
        parcel.writeString(this.mLearnMoreTitle);
        parcel.writeString(this.mLearnMoreJoinTitle);
        parcel.writeString(this.mLearnMoreJoinText);
        parcel.writeString(this.mLearnMoreCreateTitle);
        parcel.writeString(this.mLearnMoreCreateText);
        parcel.writeString(this.mLearnMoreReceiveTitle);
        parcel.writeString(this.mLearnMoreReceiveText);
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) throws JSONException, ParseException {
        this.mTitle = jSONObject.getString(StrongAuth.AUTH_TITLE);
        this.mCreateButtonText = jSONObject.getString("create_button_text");
        this.mDescription = JsonUtil.optString(jSONObject, "description");
        this.mLearnMoreText = jSONObject.getString("learn_more_text");
        this.mLearnMoreDetail = jSONObject.getString("learn_more_description");
        this.mCreateInstruction = jSONObject.getString("create_instruction");
        this.mSubtitle = jSONObject.getString("subtitle");
        this.mCanCreate = jSONObject.getBoolean("can_create");
        this.mCreatorTitle = jSONObject.getString("creator_title");
        this.mCreatorName = jSONObject.getString("creator_name");
        this.mCreatorMessage = jSONObject.getString("creator_message");
        String optString = JsonUtil.optString(jSONObject, "creator_image");
        if (optString != null) {
            this.mCreatorImage = new WishImage(optString);
        }
        this.mLearnMoreTitle = jSONObject.getString("learn_more_title");
        this.mLearnMoreJoinTitle = jSONObject.getString("learn_more_join_title");
        this.mLearnMoreJoinText = jSONObject.getString("learn_more_join_text");
        this.mLearnMoreCreateTitle = jSONObject.getString("learn_more_create_title");
        this.mLearnMoreCreateText = jSONObject.getString("learn_more_create_text");
        this.mLearnMoreReceiveTitle = jSONObject.getString("learn_more_receive_title");
        this.mLearnMoreReceiveText = jSONObject.getString("learn_more_receive_text");
    }

    public String getTitle() {
        return this.mTitle;
    }

    public String getDescription() {
        return this.mDescription;
    }

    public String getLearnMoreText() {
        return this.mLearnMoreText;
    }

    public String getLearnMoreDetail() {
        return this.mLearnMoreDetail;
    }

    public String getCreateInstruction() {
        return this.mCreateInstruction;
    }

    public String getSubtitle() {
        return this.mSubtitle;
    }

    public boolean canCreate() {
        return this.mCanCreate;
    }

    public String getCreatorTitle() {
        return this.mCreatorTitle;
    }

    public String getCreatorName() {
        return this.mCreatorName;
    }

    public String getCreatorMessage() {
        return this.mCreatorMessage;
    }

    public WishImage getCreatorImage() {
        return this.mCreatorImage;
    }

    public String getCreateButtonText() {
        return this.mCreateButtonText;
    }

    public String getLearnMoreTitle() {
        return this.mLearnMoreTitle;
    }

    public String getLearnMoreJoinTitle() {
        return this.mLearnMoreJoinTitle;
    }

    public String getLearnMoreJoinText() {
        return this.mLearnMoreJoinText;
    }

    public String getLearnMoreCreateTitle() {
        return this.mLearnMoreCreateTitle;
    }

    public String getLearnMoreCreateText() {
        return this.mLearnMoreCreateText;
    }

    public String getLearnMoreReceiveTitle() {
        return this.mLearnMoreReceiveTitle;
    }

    public String getLearnMoreReceiveText() {
        return this.mLearnMoreReceiveText;
    }
}
