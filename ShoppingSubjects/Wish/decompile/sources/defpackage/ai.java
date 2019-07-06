package defpackage;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/* renamed from: ai reason: default package */
/* compiled from: GA */
public final class ai extends q {
    private final Context b;
    private final int c;

    public ai(Context context, int i) {
        this.b = context;
        this.c = i;
    }

    public final void c() {
        WindowManager windowManager = this.b != null ? (WindowManager) this.b.getSystemService("window") : null;
        if (windowManager != null) {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
            switch (this.c) {
                case 0:
                    super.a(Integer.toString(displayMetrics.widthPixels));
                    return;
                case 1:
                    super.a(Integer.toString(displayMetrics.heightPixels));
                    return;
                case 2:
                    super.a(String.valueOf(displayMetrics.scaledDensity));
                    break;
            }
        }
    }
}
