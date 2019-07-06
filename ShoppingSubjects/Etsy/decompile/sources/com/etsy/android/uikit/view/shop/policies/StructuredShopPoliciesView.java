package com.etsy.android.uikit.view.shop.policies;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.SpannableStringBuilder;
import android.text.style.URLSpan;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.etsy.android.iconsy.views.IconView;
import com.etsy.android.lib.a.i;
import com.etsy.android.lib.a.k;
import com.etsy.android.lib.a.q;
import com.etsy.android.uikit.util.EtsyLinkify;
import com.etsy.android.uikit.util.TrackingOnClickListener;

public abstract class StructuredShopPoliciesView extends RelativeLayout {
    protected static final String BULLET_POINT_AND_SPACE = "&#8226; ";
    @Nullable
    private View mBtnExpand;
    @Nullable
    private View mExpansionSection;
    private boolean mIsContentCollapsible = false;
    private boolean mIsExpanded = true;
    private boolean mSellerMode;

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
        boolean a;
        boolean b;

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public void writeToParcel(@NonNull Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.a ? 1 : 0);
            parcel.writeInt(this.b ? 1 : 0);
        }

        private SavedState(Parcel parcel) {
            super(parcel);
            boolean z = false;
            this.a = parcel.readInt() == 1;
            if (parcel.readInt() == 1) {
                z = true;
            }
            this.b = z;
        }
    }

    public interface a {
        void a();
    }

    /* access modifiers changed from: protected */
    public abstract void init(Context context, LinearLayout linearLayout);

    public StructuredShopPoliciesView(Context context) {
        super(context);
        init(context, null, 0, 0);
    }

    public StructuredShopPoliciesView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context, attributeSet, 0, 0);
    }

    public StructuredShopPoliciesView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context, attributeSet, i, 0);
    }

    @TargetApi(21)
    public StructuredShopPoliciesView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        init(context, attributeSet, i, i2);
    }

    private void init(Context context, AttributeSet attributeSet, int i, int i2) {
        inflate(context, k.view_structured_shop_policies, this);
        IconView iconView = (IconView) findViewById(i.edit_icon);
        LinearLayout linearLayout = (LinearLayout) findViewById(i.content);
        init(context, linearLayout);
        this.mExpansionSection = linearLayout.findViewById(i.expansion_section);
        this.mBtnExpand = linearLayout.findViewById(i.btn_expand);
        if (linearLayout.findViewById(i.edit_icon) != null) {
            ((ViewGroup) iconView.getParent()).removeView(iconView);
            iconView = (IconView) linearLayout.findViewById(i.edit_icon);
        }
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, q.StructuredShopPoliciesView, i, i2);
            int i3 = 0;
            this.mSellerMode = obtainStyledAttributes.getBoolean(q.StructuredShopPoliciesView_editMode, false);
            if (!this.mSellerMode) {
                i3 = 8;
            }
            iconView.setVisibility(i3);
            obtainStyledAttributes.recycle();
        }
    }

    public boolean isSellerMode() {
        return this.mSellerMode;
    }

    /* access modifiers changed from: protected */
    public void setContentCollapsible(boolean z) {
        this.mIsContentCollapsible = z;
    }

    public boolean setExpanded(boolean z) {
        int i = 0;
        if ((!z && !this.mIsContentCollapsible) || this.mBtnExpand == null || this.mExpansionSection == null) {
            return false;
        }
        this.mBtnExpand.setVisibility(z ? 8 : 0);
        this.mBtnExpand.setOnClickListener(z ? null : new TrackingOnClickListener() {
            public void onViewClick(View view) {
                StructuredShopPoliciesView.this.setExpanded(true);
            }
        });
        View view = this.mExpansionSection;
        if (!z) {
            i = 8;
        }
        view.setVisibility(i);
        this.mIsExpanded = z;
        return true;
    }

    /* access modifiers changed from: protected */
    public void linkifyContactShopUrlSpan(TextView textView, @Nullable final a aVar) {
        if (aVar == null) {
            URLSpan[] urls = textView.getUrls();
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(textView.getText());
            for (URLSpan removeSpan : urls) {
                spannableStringBuilder.removeSpan(removeSpan);
            }
            textView.setText(spannableStringBuilder);
            return;
        }
        EtsyLinkify.a(textView, false, (OnClickListener) new TrackingOnClickListener() {
            public void onViewClick(View view) {
                aVar.a();
            }
        });
    }

    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.a = this.mIsContentCollapsible;
        savedState.b = this.mIsExpanded;
        return savedState;
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        setContentCollapsible(savedState.a);
        setExpanded(savedState.b);
    }
}
