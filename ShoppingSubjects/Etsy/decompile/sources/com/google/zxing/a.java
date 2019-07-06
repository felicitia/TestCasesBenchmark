package com.google.zxing;

import com.etsy.android.ui.dialog.EtsyDialogFragment;

/* compiled from: Dimension */
public final class a {
    private final int a;
    private final int b;

    public int a() {
        return this.a;
    }

    public int b() {
        return this.b;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof a)) {
            return false;
        }
        a aVar = (a) obj;
        if (this.a == aVar.a && this.b == aVar.b) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (this.a * 32713) + this.b;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.a);
        sb.append(EtsyDialogFragment.OPT_X_BUTTON);
        sb.append(this.b);
        return sb.toString();
    }
}
