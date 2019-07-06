package com.contextlogic.wish.dialog.prompt;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.contextlogic.wish.R;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.dialog.BaseChoice;

public class PromptDialogChoice extends BaseChoice implements Parcelable {
    public static final Creator<PromptDialogChoice> CREATOR = new Creator<PromptDialogChoice>() {
        public PromptDialogChoice createFromParcel(Parcel parcel) {
            return new PromptDialogChoice(parcel);
        }

        public PromptDialogChoice[] newArray(int i) {
            return new PromptDialogChoice[i];
        }
    };
    private boolean mIsPositive;
    private String mText;

    public int describeContents() {
        return 0;
    }

    public static PromptDialogChoice createOkChoice() {
        return new PromptDialogChoice(WishApplication.getInstance().getString(R.string.ok), 3, true);
    }

    public PromptDialogChoice(String str, int i, boolean z) {
        super(i);
        this.mText = str;
        this.mIsPositive = z;
    }

    protected PromptDialogChoice(Parcel parcel) {
        super(parcel.readInt());
        this.mText = parcel.readString();
        this.mIsPositive = parcel.readByte() != 0;
    }

    public String getText() {
        return this.mText;
    }

    public boolean isPositive() {
        return this.mIsPositive;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(getChoiceId());
        parcel.writeString(this.mText);
        parcel.writeByte(this.mIsPositive ? (byte) 1 : 0);
    }
}
