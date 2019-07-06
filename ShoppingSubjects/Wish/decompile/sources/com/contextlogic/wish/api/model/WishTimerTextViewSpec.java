package com.contextlogic.wish.api.model;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.contextlogic.wish.R;
import com.contextlogic.wish.ui.timer.CountdownTimerView;
import com.contextlogic.wish.util.ColorUtil;
import com.contextlogic.wish.util.DateUtil;
import com.contextlogic.wish.util.JsonUtil;
import java.text.ParseException;
import java.util.Date;
import org.json.JSONException;
import org.json.JSONObject;

public class WishTimerTextViewSpec extends WishTextViewSpec {
    public static final Creator<WishTimerTextViewSpec> CREATOR = new Creator<WishTimerTextViewSpec>() {
        public WishTimerTextViewSpec createFromParcel(Parcel parcel) {
            return new WishTimerTextViewSpec(parcel);
        }

        public WishTimerTextViewSpec[] newArray(int i) {
            return new WishTimerTextViewSpec[i];
        }
    };
    private Date mDestTime;
    private String mPreText;
    private boolean mShowCountdown;

    WishTimerTextViewSpec(JSONObject jSONObject) throws JSONException, ParseException {
        super(jSONObject);
    }

    private WishTimerTextViewSpec(Parcel parcel) {
        super(parcel);
        if (parcel.readByte() != 0) {
            this.mDestTime = new Date(parcel.readLong());
        }
        this.mShowCountdown = parcel.readByte() != 0;
        this.mPreText = parcel.readString();
    }

    public void parseJson(JSONObject jSONObject) throws JSONException, ParseException {
        super.parseJson(jSONObject);
        this.mDestTime = DateUtil.parseIsoDate(jSONObject.getString("dest_time"));
        this.mShowCountdown = jSONObject.optBoolean("show_countdown", true);
        this.mPreText = JsonUtil.optString(jSONObject, "pre_text");
    }

    public Date getDestTime() {
        return this.mDestTime;
    }

    public String getPreText() {
        return this.mPreText;
    }

    public boolean shouldShowCountdown() {
        return this.mShowCountdown;
    }

    public String getText() {
        if (this.mDestTime == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(this.mPreText == null ? "" : this.mPreText);
        sb.append(DateUtil.getLocalizedTime(this.mDestTime));
        return sb.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeByte((byte) (this.mDestTime != null ? 1 : 0));
        if (this.mDestTime != null) {
            parcel.writeLong(this.mDestTime.getTime());
        }
        parcel.writeByte(this.mShowCountdown ? (byte) 1 : 0);
        parcel.writeString(this.mPreText);
    }

    public static CountdownTimerView newCountdownTimerViewFromSpec(Context context, WishTimerTextViewSpec wishTimerTextViewSpec) {
        if (context == null || wishTimerTextViewSpec == null) {
            return null;
        }
        int safeParseColor = ColorUtil.safeParseColor(wishTimerTextViewSpec.getColor(), -16777216);
        int textSize = wishTimerTextViewSpec.getTextSize();
        if (textSize == -1) {
            textSize = context.getResources().getDimensionPixelSize(R.dimen.text_size_body);
        }
        int i = textSize;
        CountdownTimerView countdownTimerView = new CountdownTimerView(context);
        countdownTimerView.setPadding(0);
        countdownTimerView.setPreText(wishTimerTextViewSpec.getPreText());
        countdownTimerView.setup(wishTimerTextViewSpec.getDestTime(), i, 0, safeParseColor, safeParseColor);
        return countdownTimerView;
    }
}
