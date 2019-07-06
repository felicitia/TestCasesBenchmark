package com.etsy.android.uikit.navigationview;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.widget.TextViewCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.etsy.android.lib.a;
import com.etsy.android.lib.a.e;
import com.etsy.android.lib.a.i;
import com.etsy.android.lib.a.k;
import com.etsy.android.lib.a.o;
import com.etsy.android.lib.logger.f;
import com.etsy.android.uikit.view.BadgeDrawable;
import com.etsy.android.uikit.view.BadgeDrawable.CircleType;

public class EtsyNavigationMenuItemView extends LinearLayout {
    private static final int BADGE_MAX = 99;
    public static final String BADGE_NEW_KEY = "NEW";
    private static final int[] CHECKED_STATE_SET = {16842912};
    private static final String TAG = f.a(EtsyNavigationMenuItemView.class);
    private int mBadgeCount;
    private BadgeDrawable mBadgeDrawable;
    private ImageView mBadgeIconView;
    private String mBadgeText;
    private TextView mBadgeTextView;
    private int mIconSize;
    private ColorStateList mIconTintList;
    private MenuItem mItemData;
    private TextView mTextView;

    public boolean prefersCondensedTitle() {
        return false;
    }

    public void setShortcut(boolean z, char c) {
    }

    public boolean showsIcon() {
        return true;
    }

    public EtsyNavigationMenuItemView(Context context) {
        this(context, null);
    }

    public EtsyNavigationMenuItemView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public EtsyNavigationMenuItemView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context);
    }

    private void init(Context context) {
        setOrientation(0);
        LayoutInflater.from(context).inflate(k.etsy_navigation_item_content, this, true);
        this.mTextView = (TextView) findViewById(i.etsy_menu_text);
        this.mBadgeIconView = (ImageView) findViewById(i.etsy_menu_badge_icon);
        this.mBadgeTextView = (TextView) findViewById(i.etsy_menu_badge_text);
        Resources resources = getResources();
        this.mIconSize = resources.getDimensionPixelSize(a.f.design_navigation_icon_size);
        this.mBadgeDrawable = new BadgeDrawable(getContext(), e.navigation_view_item_color, e.navigation_view_bg);
        this.mBadgeTextView.setTextColor(resources.getColor(e.navigation_view_bg));
    }

    public void initialize(MenuItem menuItem, int i) {
        this.mItemData = menuItem;
        setVisibility(menuItem.isVisible() ? 0 : 8);
        if (getBackground() == null) {
            setBackgroundDrawable(createDefaultBackground());
        }
        this.mBadgeCount = 0;
        this.mBadgeText = "";
        setCheckable(menuItem.isCheckable());
        setChecked(menuItem.isChecked());
        setEnabled(menuItem.isEnabled());
        setTitle(menuItem.getTitle());
        setIcon(menuItem.getIcon());
        updateBadge();
    }

    private StateListDrawable createDefaultBackground() {
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(CHECKED_STATE_SET, new ColorDrawable(getResources().getColor(e.nav_item_bg)));
        stateListDrawable.addState(EMPTY_STATE_SET, new ColorDrawable(0));
        return stateListDrawable;
    }

    public MenuItem getItemData() {
        return this.mItemData;
    }

    public void setTitle(CharSequence charSequence) {
        String charSequence2 = charSequence.toString();
        int length = charSequence2.length() - 1;
        if (length <= 0 || charSequence2.charAt(length) != '#') {
            this.mTextView.setText(charSequence);
            return;
        }
        int indexOf = charSequence2.indexOf(35);
        if (indexOf != length) {
            this.mTextView.setText(charSequence2.substring(0, indexOf));
            String substring = charSequence2.substring(indexOf + 1, length);
            if (substring.equals(BADGE_NEW_KEY)) {
                this.mBadgeText = BADGE_NEW_KEY;
                return;
            }
            try {
                this.mBadgeCount = Integer.parseInt(substring);
            } catch (NumberFormatException e) {
                f.e(TAG, "setTitle badge count error", e);
            }
        }
    }

    public void setCheckable(boolean z) {
        refreshDrawableState();
    }

    public void setChecked(boolean z) {
        refreshDrawableState();
    }

    public void setIcon(Drawable drawable) {
        if (drawable != null) {
            drawable = DrawableCompat.wrap(drawable).mutate();
            drawable.setBounds(0, 0, this.mIconSize, this.mIconSize);
        }
        TextViewCompat.setCompoundDrawablesRelative(this.mTextView, drawable, null, null, null);
    }

    private void updateBadge() {
        if (this.mBadgeCount > 0 || !TextUtils.isEmpty(this.mBadgeText)) {
            if (this.mBadgeCount > 0) {
                BadgeDrawable badgeDrawable = this.mBadgeDrawable;
                badgeDrawable.setCircleType(CircleType.CIRCLE);
                badgeDrawable.setBounds(0, 0, this.mIconSize, this.mIconSize);
                if (this.mBadgeCount > 99) {
                    badgeDrawable.setBadgeText(getContext().getString(o.badge_max));
                } else {
                    badgeDrawable.setCount(this.mBadgeCount);
                }
                this.mBadgeIconView.setImageDrawable(badgeDrawable);
                this.mBadgeIconView.setVisibility(0);
                this.mBadgeTextView.setText("");
                this.mBadgeTextView.setVisibility(8);
                return;
            } else if (!TextUtils.isEmpty(this.mBadgeText)) {
                this.mBadgeIconView.setImageDrawable(null);
                this.mBadgeIconView.setVisibility(8);
                this.mBadgeTextView.setText(this.mBadgeText);
                this.mBadgeTextView.setVisibility(0);
                return;
            }
        }
        this.mBadgeIconView.setImageDrawable(null);
        this.mBadgeIconView.setVisibility(8);
        this.mBadgeTextView.setText("");
        this.mBadgeTextView.setVisibility(8);
    }

    /* access modifiers changed from: protected */
    public int[] onCreateDrawableState(int i) {
        int[] onCreateDrawableState = super.onCreateDrawableState(i + 1);
        if (this.mItemData != null && this.mItemData.isCheckable() && this.mItemData.isChecked()) {
            mergeDrawableStates(onCreateDrawableState, CHECKED_STATE_SET);
        }
        return onCreateDrawableState;
    }

    /* access modifiers changed from: 0000 */
    public void setTextColor(ColorStateList colorStateList) {
        if (this.mTextView != null) {
            this.mTextView.setTextColor(colorStateList);
        }
    }

    /* access modifiers changed from: 0000 */
    public void setIconTintList(ColorStateList colorStateList) {
        this.mIconTintList = colorStateList;
        if (this.mItemData != null) {
            setIcon(this.mItemData.getIcon());
        }
    }
}
