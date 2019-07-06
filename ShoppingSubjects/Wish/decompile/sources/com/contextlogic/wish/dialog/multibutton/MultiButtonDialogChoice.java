package com.contextlogic.wish.dialog.multibutton;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.contextlogic.wish.R;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.dialog.BaseChoice;
import java.util.HashMap;
import java.util.Map;

public class MultiButtonDialogChoice extends BaseChoice implements Parcelable {
    public static final Creator<MultiButtonDialogChoice> CREATOR = new Creator<MultiButtonDialogChoice>() {
        public MultiButtonDialogChoice createFromParcel(Parcel parcel) {
            return new MultiButtonDialogChoice(parcel);
        }

        public MultiButtonDialogChoice[] newArray(int i) {
            return new MultiButtonDialogChoice[i];
        }
    };
    private int mBackgroundColorId = 0;
    private int mBackgroundDrawableId = 0;
    private BackgroundType mBackgroundType;
    private ChoiceType mChoiceType;
    private String mText;
    private int mTextColorId = 0;

    public enum BackgroundType {
        COLOR(0),
        DRAWABLE(1),
        NONE(2);
        
        private static Map<Integer, BackgroundType> map;
        private int value;

        static {
            int i;
            BackgroundType[] values;
            map = new HashMap();
            for (BackgroundType backgroundType : values()) {
                map.put(Integer.valueOf(backgroundType.getValue()), backgroundType);
            }
        }

        private BackgroundType(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }

        public static BackgroundType valueOf(int i) {
            return (BackgroundType) map.get(Integer.valueOf(i));
        }
    }

    public enum ChoiceType {
        TEXT_ONLY(0),
        DEFAULT(1);
        
        private static Map<Integer, ChoiceType> map;
        private int value;

        static {
            int i;
            ChoiceType[] values;
            map = new HashMap();
            for (ChoiceType choiceType : values()) {
                map.put(Integer.valueOf(choiceType.getValue()), choiceType);
            }
        }

        private ChoiceType(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }

        public static ChoiceType valueOf(int i) {
            return (ChoiceType) map.get(Integer.valueOf(i));
        }
    }

    public int describeContents() {
        return 0;
    }

    public MultiButtonDialogChoice(int i, String str, int i2, int i3, BackgroundType backgroundType, ChoiceType choiceType) {
        super(i);
        this.mText = str;
        this.mTextColorId = i2;
        this.mBackgroundType = backgroundType;
        this.mChoiceType = choiceType;
        if (choiceType != ChoiceType.DEFAULT) {
            return;
        }
        if (backgroundType == BackgroundType.COLOR) {
            this.mBackgroundColorId = i3;
        } else if (backgroundType == BackgroundType.DRAWABLE) {
            this.mBackgroundDrawableId = i3;
        }
    }

    protected MultiButtonDialogChoice(Parcel parcel) {
        super(parcel.readInt());
        this.mText = parcel.readString();
        this.mTextColorId = parcel.readInt();
        this.mBackgroundDrawableId = parcel.readInt();
        this.mBackgroundColorId = parcel.readInt();
        this.mBackgroundType = BackgroundType.valueOf(parcel.readInt());
        this.mChoiceType = ChoiceType.valueOf(parcel.readInt());
    }

    public static MultiButtonDialogChoice createOkChoice() {
        MultiButtonDialogChoice multiButtonDialogChoice = new MultiButtonDialogChoice(3, WishApplication.getInstance().getString(R.string.ok), R.color.white, R.drawable.main_button_selector, BackgroundType.DRAWABLE, ChoiceType.DEFAULT);
        return multiButtonDialogChoice;
    }

    public static MultiButtonDialogChoice createYesChoice() {
        MultiButtonDialogChoice multiButtonDialogChoice = new MultiButtonDialogChoice(1, WishApplication.getInstance().getString(R.string.yes), R.color.white, R.drawable.main_button_selector, BackgroundType.DRAWABLE, ChoiceType.DEFAULT);
        return multiButtonDialogChoice;
    }

    public static MultiButtonDialogChoice createCustomYesChoice(String str, int i) {
        MultiButtonDialogChoice multiButtonDialogChoice = new MultiButtonDialogChoice(1, str, R.color.white, i, BackgroundType.DRAWABLE, ChoiceType.DEFAULT);
        return multiButtonDialogChoice;
    }

    public static MultiButtonDialogChoice createCustomNoChoice(String str) {
        MultiButtonDialogChoice multiButtonDialogChoice = new MultiButtonDialogChoice(2, str, R.color.main_primary, 0, BackgroundType.NONE, ChoiceType.TEXT_ONLY);
        return multiButtonDialogChoice;
    }

    public static MultiButtonDialogChoice createNoChoice() {
        MultiButtonDialogChoice multiButtonDialogChoice = new MultiButtonDialogChoice(2, WishApplication.getInstance().getString(R.string.no), R.color.main_primary, 0, BackgroundType.NONE, ChoiceType.TEXT_ONLY);
        return multiButtonDialogChoice;
    }

    public int getTextColorId() {
        return this.mTextColorId;
    }

    public int getBackgroundDrawableId() {
        return this.mBackgroundDrawableId;
    }

    public int getBackgroundColorId() {
        return this.mBackgroundColorId;
    }

    public String getText() {
        return this.mText;
    }

    public ChoiceType getChoiceType() {
        return this.mChoiceType;
    }

    public BackgroundType getBackgroundType() {
        return this.mBackgroundType;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(getChoiceId());
        parcel.writeString(this.mText);
        parcel.writeInt(this.mTextColorId);
        parcel.writeInt(this.mBackgroundDrawableId);
        parcel.writeInt(this.mBackgroundColorId);
        parcel.writeInt(this.mBackgroundType.getValue());
        parcel.writeInt(this.mChoiceType.getValue());
    }
}
