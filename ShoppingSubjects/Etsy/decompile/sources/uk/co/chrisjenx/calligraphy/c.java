package uk.co.chrisjenx.calligraphy;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build.VERSION;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.TextView;
import uk.co.chrisjenx.calligraphy.f.b;

/* compiled from: CalligraphyFactory */
class c {
    private final int a;

    protected static int[] a(TextView textView) {
        int[] iArr = {-1, -1};
        if (b(textView)) {
            iArr[0] = 16843470;
            iArr[1] = 16843512;
        } else if (c(textView)) {
            iArr[0] = 16843470;
            iArr[1] = 16843513;
        }
        if (iArr[0] == -1) {
            iArr[0] = b.a().f().containsKey(textView.getClass()) ? ((Integer) b.a().f().get(textView.getClass())).intValue() : 16842804;
        }
        return iArr;
    }

    @SuppressLint({"NewApi"})
    protected static boolean b(TextView textView) {
        if (a(textView, "action_bar_title")) {
            return true;
        }
        if (a((View) textView)) {
            return TextUtils.equals(((Toolbar) textView.getParent()).getTitle(), textView.getText());
        }
        return false;
    }

    @SuppressLint({"NewApi"})
    protected static boolean c(TextView textView) {
        if (a(textView, "action_bar_subtitle")) {
            return true;
        }
        if (a((View) textView)) {
            return TextUtils.equals(((Toolbar) textView.getParent()).getSubtitle(), textView.getText());
        }
        return false;
    }

    protected static boolean a(View view) {
        return e.a() && view.getParent() != null && (view.getParent() instanceof Toolbar);
    }

    protected static boolean a(View view, String str) {
        if (view.getId() == -1) {
            return false;
        }
        return view.getResources().getResourceEntryName(view.getId()).equalsIgnoreCase(str);
    }

    public c(int i) {
        this.a = i;
    }

    public View a(View view, Context context, AttributeSet attributeSet) {
        if (!(view == null || view.getTag(b.calligraphy_tag_id) == Boolean.TRUE)) {
            b(view, context, attributeSet);
            view.setTag(b.calligraphy_tag_id, Boolean.TRUE);
        }
        return view;
    }

    /* access modifiers changed from: 0000 */
    public void b(View view, final Context context, AttributeSet attributeSet) {
        if (view instanceof TextView) {
            TextView textView = (TextView) view;
            if (!h.b(textView.getTypeface())) {
                String a2 = e.a(context, attributeSet, this.a);
                if (TextUtils.isEmpty(a2)) {
                    a2 = e.b(context, attributeSet, this.a);
                }
                if (TextUtils.isEmpty(a2)) {
                    a2 = e.c(context, attributeSet, this.a);
                }
                boolean z = false;
                if (TextUtils.isEmpty(a2)) {
                    int[] a3 = a(textView);
                    if (a3[1] != -1) {
                        a2 = e.a(context, a3[0], a3[1], this.a);
                    } else {
                        a2 = e.a(context, a3[0], this.a);
                    }
                }
                if (a(view, "action_bar_title") || a(view, "action_bar_subtitle")) {
                    z = true;
                }
                e.a(context, textView, b.a(), a2, z);
            } else {
                return;
            }
        }
        if (e.a() && (view instanceof Toolbar)) {
            final ViewGroup viewGroup = (ViewGroup) view;
            viewGroup.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
                @TargetApi(16)
                public void onGlobalLayout() {
                    int childCount = viewGroup.getChildCount();
                    if (childCount != 0) {
                        for (int i = 0; i < childCount; i++) {
                            c.this.a(viewGroup.getChildAt(i), context, null);
                        }
                    }
                    if (VERSION.SDK_INT < 16) {
                        viewGroup.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                    } else {
                        viewGroup.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                }
            });
        }
    }
}
