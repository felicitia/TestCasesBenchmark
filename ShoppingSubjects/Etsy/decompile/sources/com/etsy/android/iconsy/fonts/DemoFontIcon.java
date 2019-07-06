package com.etsy.android.iconsy.fonts;

import com.etsy.android.iconsy.a;

public enum DemoFontIcon implements a {
    EXAMPLE("H");
    
    private String mIcon;

    private DemoFontIcon(String str) {
        this.mIcon = str;
    }

    public String toString() {
        return this.mIcon;
    }
}
