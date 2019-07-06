package com.etsy.android.ui.cart.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.text.Html;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.etsy.android.R;
import com.etsy.android.e.b;
import com.etsy.android.lib.models.apiv3.cart.MessageBubble;
import com.etsy.android.lib.models.cardviewelement.BaseMessage;
import com.etsy.android.uikit.view.ZeroSpinner;

public class CartMessageBubbleView extends LinearLayout {
    private static final int NO_ICON = 0;
    private View mBtnDismiss;
    private View mBubbleBackground;
    private ImageView mBubblePointer;
    private boolean mHasPointer;
    private ImageView mIcon;
    private ZeroSpinner mSpinnerError;
    private TextView mTxtMessage;
    private MessageType mType = MessageType.INFO;

    public enum MessageType {
        INFO("info", R.color.sk_gray_10, R.color.sk_gray_70, 0),
        WARNING(BaseMessage.TYPE_WARNING, R.color.sk_secondary_creamsicle, R.color.sk_gray_70, R.drawable.sk_ic_warning),
        ERROR("error", R.color.sk_secondary_autumn, R.color.sk_white, 0),
        SUCCESS("success", R.color.sk_secondary_blinding_sandstorm, R.color.sk_gray_70, R.drawable.sk_ic_check),
        NOTIFY(BaseMessage.TYPE_NOTIFY, R.color.sk_gray_60, R.color.sk_white, 0);
        
        private final int mBackgroundColorId;
        private final int mDrawableResId;
        private final String mName;
        private final int mTextColorId;

        private MessageType(String str, int i, int i2, @ColorRes int i3) {
            this.mName = str;
            this.mBackgroundColorId = i;
            this.mTextColorId = i2;
            this.mDrawableResId = i3;
        }

        public String getName() {
            return this.mName;
        }

        @ColorRes
        public int getBackgroundColorId() {
            return this.mBackgroundColorId;
        }

        @ColorRes
        public int getTextColorId() {
            return this.mTextColorId;
        }

        @DrawableRes
        public int getDrawableResId() {
            return this.mDrawableResId;
        }

        public static MessageType fromString(String str) {
            MessageType[] values;
            for (MessageType messageType : values()) {
                if (messageType.getName().equals(str)) {
                    return messageType;
                }
            }
            return null;
        }
    }

    private static class SavedState extends BaseSavedState {
        public static final Creator<SavedState> CREATOR = new Creator<SavedState>() {
            /* renamed from: a */
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            /* renamed from: a */
            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        };
        MessageType a;
        String b;
        boolean c;

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public void writeToParcel(@NonNull Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeSerializable(this.a);
            parcel.writeString(this.b);
            parcel.writeInt(this.c ? 1 : 0);
        }

        private SavedState(Parcel parcel) {
            super(parcel);
            this.a = (MessageType) parcel.readSerializable();
            this.b = parcel.readString();
            boolean z = true;
            if (parcel.readInt() != 1) {
                z = false;
            }
            this.c = z;
        }
    }

    public ZeroSpinner getErrorSpinner() {
        return this.mSpinnerError;
    }

    public CartMessageBubbleView(Context context) {
        super(context);
        init(context, null, 0, 0);
    }

    public CartMessageBubbleView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context, attributeSet, 0, 0);
    }

    public CartMessageBubbleView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context, attributeSet, i, 0);
    }

    @TargetApi(21)
    public CartMessageBubbleView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        init(context, attributeSet, i, i2);
    }

    private void init(Context context, AttributeSet attributeSet, int i, int i2) {
        inflate(context, R.layout.view_cart_message_bubble, this);
        this.mBubbleBackground = findViewById(R.id.message_bubble);
        this.mBubblePointer = (ImageView) findViewById(R.id.pointer);
        this.mIcon = (ImageView) findViewById(R.id.icon);
        this.mTxtMessage = (TextView) findViewById(R.id.txt_message);
        this.mSpinnerError = (ZeroSpinner) findViewById(R.id.btn_resolution);
        this.mBtnDismiss = findViewById(R.id.btn_dismiss);
        setOrientation(1);
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, b.CartMessageBubbleView, i, i2);
            this.mType = MessageType.values()[obtainStyledAttributes.getInt(2, MessageType.INFO.ordinal())];
            this.mHasPointer = obtainStyledAttributes.getBoolean(1, false);
            this.mTxtMessage.setText(obtainStyledAttributes.getResourceId(0, 0));
            obtainStyledAttributes.recycle();
        }
        updateViewStates();
    }

    public void setType(MessageType messageType) {
        if (messageType != null) {
            this.mType = messageType;
            updateViewStates();
        }
    }

    public void setMessageText(String str) {
        if (VERSION.SDK_INT >= 24) {
            this.mTxtMessage.setText(Html.fromHtml(str, 63));
        } else {
            this.mTxtMessage.setText(Html.fromHtml(str));
        }
    }

    public void setHasPointer(boolean z) {
        this.mHasPointer = z;
        updatePointerDisplay();
    }

    public void bindBaseMessage(@NonNull BaseMessage baseMessage) {
        setType(MessageType.fromString(baseMessage.getType()));
        setMessageText(baseMessage.getMessage());
        setHasPointer(baseMessage.getHasPointer());
        updateViewStates();
    }

    public void bindMessageBubble(@NonNull MessageBubble messageBubble) {
        bindBaseMessage(messageBubble);
    }

    public void setDismissButtonClickListener(@Nullable OnClickListener onClickListener) {
        this.mBtnDismiss.setOnClickListener(onClickListener);
    }

    private void updateViewStates() {
        updateColors();
        if (MessageType.NOTIFY.equals(this.mType)) {
            this.mIcon.setVisibility(8);
            this.mBtnDismiss.setVisibility(0);
        } else {
            this.mIcon.setVisibility(0);
            this.mBtnDismiss.setVisibility(8);
        }
        updateIcon();
        updatePointerDisplay();
        invalidate();
        requestLayout();
    }

    private void updatePointerDisplay() {
        int i = 0;
        if (this.mHasPointer) {
            LayoutParams layoutParams = (LayoutParams) this.mBubblePointer.getLayoutParams();
            if (MessageType.NOTIFY.equals(this.mType)) {
                layoutParams.gravity = 1;
                layoutParams.leftMargin = 0;
            } else {
                layoutParams.gravity = 3;
                layoutParams.leftMargin = getResources().getDimensionPixelOffset(R.dimen.cart_message_pointer_margin_left);
            }
            this.mBubblePointer.setLayoutParams(layoutParams);
        }
        ImageView imageView = this.mBubblePointer;
        if (!this.mHasPointer) {
            i = 8;
        }
        imageView.setVisibility(i);
    }

    private void updateColors() {
        Resources resources = getResources();
        setTextColor(resources.getColor(this.mType.getTextColorId()));
        setBubbleBackgroundColor(resources.getColor(this.mType.getBackgroundColorId()));
    }

    private void setTextColor(int i) {
        this.mTxtMessage.setTextColor(i);
    }

    private void setBubbleBackgroundColor(int i) {
        this.mBubblePointer.setColorFilter(i);
        DrawableCompat.setTint(DrawableCompat.wrap(this.mBubbleBackground.getBackground()), i);
    }

    private void updateIcon() {
        int drawableResId = this.mType.getDrawableResId();
        if (drawableResId == 0) {
            this.mIcon.setVisibility(8);
            return;
        }
        this.mIcon.setImageDrawable(VectorDrawableCompat.create(getResources(), drawableResId, getContext().getTheme()));
        this.mIcon.setVisibility(0);
    }

    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.a = this.mType;
        savedState.c = this.mHasPointer;
        return savedState;
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        super.onRestoreInstanceState(((SavedState) parcelable).getSuperState());
        updateViewStates();
    }
}
