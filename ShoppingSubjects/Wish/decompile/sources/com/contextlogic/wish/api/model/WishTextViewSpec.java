package com.contextlogic.wish.api.model;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.widget.TextView;
import com.contextlogic.wish.ui.text.ThemedTextView;
import com.contextlogic.wish.util.ColorUtil;
import com.contextlogic.wish.util.FontUtil;
import com.contextlogic.wish.util.ValueUtil;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class WishTextViewSpec extends BaseModel implements Parcelable {
    public static final Creator<WishTextViewSpec> CREATOR = new Creator<WishTextViewSpec>() {
        public WishTextViewSpec createFromParcel(Parcel parcel) {
            return new WishTextViewSpec(parcel);
        }

        public WishTextViewSpec[] newArray(int i) {
            return new WishTextViewSpec[i];
        }
    };
    private String mColor;
    private boolean mIsBold;
    private int mMaxLines;
    private int mPaddingBottom;
    private int mPaddingLeft;
    private int mPaddingRight;
    private int mPaddingTop;
    private String mText;
    private int mTextSize;

    public int describeContents() {
        return 0;
    }

    public String getText() {
        return this.mText;
    }

    public String getColor() {
        return this.mColor;
    }

    public int getMaxLines() {
        return this.mMaxLines;
    }

    public int getTextSize() {
        return this.mTextSize;
    }

    public int getPaddingLeft() {
        return this.mPaddingLeft;
    }

    public int getPaddingTop() {
        return this.mPaddingTop;
    }

    public int getPaddingRight() {
        return this.mPaddingRight;
    }

    public int getPaddingBottom() {
        return this.mPaddingBottom;
    }

    public boolean isBold() {
        return this.mIsBold;
    }

    WishTextViewSpec(JSONObject jSONObject) throws JSONException, ParseException {
        super(jSONObject);
    }

    protected WishTextViewSpec(Parcel parcel) {
        this.mText = parcel.readString();
        this.mColor = parcel.readString();
        this.mMaxLines = parcel.readInt();
        this.mTextSize = parcel.readInt();
        this.mPaddingLeft = parcel.readInt();
        this.mPaddingTop = parcel.readInt();
        this.mPaddingRight = parcel.readInt();
        this.mPaddingBottom = parcel.readInt();
        this.mIsBold = parcel.readByte() != 0;
    }

    public void parseJson(JSONObject jSONObject) throws JSONException, ParseException {
        this.mText = jSONObject.optString("text");
        this.mMaxLines = jSONObject.optInt("max_lines", -1);
        if (jSONObject.has("text_color")) {
            this.mColor = jSONObject.getString("text_color");
        }
        if (jSONObject.has("text_size")) {
            this.mTextSize = (int) ValueUtil.convertDpToPx((float) jSONObject.getInt("text_size"));
        } else {
            this.mTextSize = -1;
        }
        if (jSONObject.has("padding_left")) {
            this.mPaddingLeft = (int) ValueUtil.convertDpToPx((float) jSONObject.getInt("padding_left"));
        } else {
            this.mPaddingLeft = -1;
        }
        if (jSONObject.has("padding_top")) {
            this.mPaddingTop = (int) ValueUtil.convertDpToPx((float) jSONObject.getInt("padding_top"));
        } else {
            this.mPaddingTop = -1;
        }
        if (jSONObject.has("padding_right")) {
            this.mPaddingRight = (int) ValueUtil.convertDpToPx((float) jSONObject.getInt("padding_right"));
        } else {
            this.mPaddingRight = -1;
        }
        if (jSONObject.has("padding_bottom")) {
            this.mPaddingBottom = (int) ValueUtil.convertDpToPx((float) jSONObject.getInt("padding_bottom"));
        } else {
            this.mPaddingBottom = -1;
        }
        this.mIsBold = jSONObject.optBoolean("text_bold", false);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mText);
        parcel.writeString(this.mColor);
        parcel.writeInt(this.mMaxLines);
        parcel.writeInt(this.mTextSize);
        parcel.writeInt(this.mPaddingLeft);
        parcel.writeInt(this.mPaddingTop);
        parcel.writeInt(this.mPaddingRight);
        parcel.writeInt(this.mPaddingBottom);
        parcel.writeByte(this.mIsBold ? (byte) 1 : 0);
    }

    public static ThemedTextView newTextViewFromSpec(Context context, WishTextViewSpec wishTextViewSpec) {
        ThemedTextView themedTextView = new ThemedTextView(context);
        applyTextViewSpec(themedTextView, wishTextViewSpec);
        return themedTextView;
    }

    public static void applyTextViewSpec(TextView textView, WishTextViewSpec wishTextViewSpec) {
        if (textView != null) {
            if (wishTextViewSpec == null) {
                textView.setVisibility(8);
            } else {
                if (wishTextViewSpec.getText() != null) {
                    textView.setText(wishTextViewSpec.getText());
                }
                if (wishTextViewSpec.getColor() != null && !wishTextViewSpec.getColor().equals("")) {
                    textView.setTextColor(ColorUtil.safeParseColor(wishTextViewSpec.getColor(), -16777216));
                }
                if (wishTextViewSpec.getTextSize() > 0) {
                    textView.setTextSize(0, (float) wishTextViewSpec.getTextSize());
                }
                if (wishTextViewSpec.isBold()) {
                    textView.setTypeface(FontUtil.getTypefaceForStyle(1), 1);
                } else {
                    textView.setTypeface(FontUtil.getTypefaceForStyle(0), 0);
                }
                if (wishTextViewSpec.getMaxLines() > 0) {
                    textView.setMaxLines(wishTextViewSpec.getMaxLines());
                    textView.setEllipsize(TruncateAt.END);
                }
                if (wishTextViewSpec.getPaddingLeft() >= 0 || wishTextViewSpec.getPaddingTop() >= 0 || wishTextViewSpec.getPaddingRight() >= 0 || wishTextViewSpec.getPaddingBottom() >= 0) {
                    textView.setPadding(Math.max(wishTextViewSpec.getPaddingLeft(), 0), Math.max(wishTextViewSpec.getPaddingTop(), 0), Math.max(wishTextViewSpec.getPaddingRight(), 0), Math.max(wishTextViewSpec.getPaddingBottom(), 0));
                }
                textView.setVisibility(0);
            }
        }
    }

    public static void applyTextViewSpecs(TextView textView, List<WishTextViewSpec> list, CharSequence charSequence) {
        if (list.isEmpty()) {
            textView.setVisibility(8);
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (WishTextViewSpec spannableString : list) {
            arrayList.add(spannableString.toSpannableString());
            arrayList.add(charSequence);
        }
        arrayList.remove(arrayList.size() - 1);
        CharSequence[] charSequenceArr = new CharSequence[arrayList.size()];
        arrayList.toArray(charSequenceArr);
        textView.setText(TextUtils.concat(charSequenceArr));
    }

    public SpannableString toSpannableString() {
        SpannableString spannableString = new SpannableString(this.mText);
        int length = this.mText.length();
        if (!TextUtils.isEmpty(this.mColor)) {
            spannableString.setSpan(new ForegroundColorSpan(ColorUtil.safeParseColor(this.mColor, -16777216)), 0, length, 17);
        }
        if (this.mTextSize > 0) {
            spannableString.setSpan(new AbsoluteSizeSpan(this.mTextSize), 0, length, 17);
        }
        if (this.mIsBold) {
            spannableString.setSpan(new StyleSpan(1), 0, length, 17);
        }
        return spannableString;
    }
}
