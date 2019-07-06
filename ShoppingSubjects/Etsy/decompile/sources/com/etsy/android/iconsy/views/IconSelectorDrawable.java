package com.etsy.android.iconsy.views;

import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.util.StateSet;
import com.etsy.android.iconsy.views.IconDrawable.a;

public class IconSelectorDrawable extends StateListDrawable {
    public static final int[] CHECKED_STATE = {16842912};
    public static final int COLOR_TEN_PERCENT_BLACK = 436207616;
    public static final int DEFAULT_DISABLED_ALPHA = 153;
    public static final int[] DISABLED_STATE_SET = {-16842910};
    public static final int[] PRESSED_STATE_SET = {16842919};
    private IconDrawable[] mDrawables;

    public static IconSelectorDrawable create(a aVar) {
        IconSelectorDrawable iconSelectorDrawable = new IconSelectorDrawable();
        IconDrawable[] generateSetOfThreeDrawables = iconSelectorDrawable.generateSetOfThreeDrawables(aVar);
        iconSelectorDrawable.setupNonBaseStateDrawables(generateSetOfThreeDrawables);
        iconSelectorDrawable.setBaseState(generateSetOfThreeDrawables[generateSetOfThreeDrawables.length - 1]);
        return iconSelectorDrawable;
    }

    public static IconSelectorDrawable createChecked(a aVar, com.etsy.android.iconsy.a aVar2) {
        IconSelectorDrawable iconSelectorDrawable = new IconSelectorDrawable();
        IconDrawable[] generateSetOfThreeDrawables = iconSelectorDrawable.generateSetOfThreeDrawables(aVar);
        iconSelectorDrawable.setupNonBaseStateDrawables(generateSetOfThreeDrawables);
        aVar.a(aVar2);
        iconSelectorDrawable.setCheckedDrawables(aVar);
        iconSelectorDrawable.setBaseState(generateSetOfThreeDrawables[2]);
        return iconSelectorDrawable;
    }

    private void setupNonBaseStateDrawables(IconDrawable[] iconDrawableArr) {
        setDisabledState(createDisabledDrawable(iconDrawableArr[0]));
        setPressedState(createPressedDrawable(iconDrawableArr[1]));
        this.mDrawables = iconDrawableArr;
    }

    public void setBaseState(Drawable drawable) {
        addState(StateSet.WILD_CARD, drawable);
    }

    public void setPressedState(Drawable drawable) {
        addState(PRESSED_STATE_SET, drawable);
    }

    public void setDisabledState(Drawable drawable) {
        addState(DISABLED_STATE_SET, drawable);
    }

    public void setCheckedDrawables(a aVar) {
        setCheckedDrawables(generateSetOfThreeDrawables(aVar));
    }

    public void setCheckedDrawables(IconDrawable[] iconDrawableArr) {
        addState(combine(DISABLED_STATE_SET, CHECKED_STATE), createDisabledDrawable(iconDrawableArr[0]));
        addState(combine(PRESSED_STATE_SET, CHECKED_STATE), createPressedDrawable(iconDrawableArr[1]));
        addState(CHECKED_STATE, iconDrawableArr[2]);
    }

    public IconDrawable[] getDrawables() {
        return this.mDrawables;
    }

    private int[] combine(int[] iArr, int[] iArr2) {
        int[] iArr3 = new int[(iArr.length + iArr2.length)];
        System.arraycopy(iArr, 0, iArr3, 0, iArr.length);
        System.arraycopy(iArr2, 0, iArr3, iArr.length, iArr2.length);
        return iArr3;
    }

    private IconDrawable[] generateSetOfThreeDrawables(a aVar) {
        return new IconDrawable[]{aVar.a(), aVar.a(), aVar.a()};
    }

    private static IconDrawable createDisabledDrawable(IconDrawable iconDrawable) {
        iconDrawable.setDefaultAlpha(DEFAULT_DISABLED_ALPHA);
        return iconDrawable;
    }

    private static IconDrawable createPressedDrawable(IconDrawable iconDrawable) {
        iconDrawable.setDefaultColorFilter(new PorterDuffColorFilter(COLOR_TEN_PERCENT_BLACK, Mode.SRC_ATOP));
        return iconDrawable;
    }
}
