package uk.co.chrisjenx.calligraphy;

import android.content.Context;
import android.content.res.Resources.NotFoundException;
import android.content.res.Resources.Theme;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.TextView;
import android.widget.TextView.BufferType;

/* compiled from: CalligraphyUtils */
public final class e {
    private static Boolean a;

    public static CharSequence a(CharSequence charSequence, Typeface typeface) {
        if (charSequence != null && charSequence.length() > 0) {
            if (!(charSequence instanceof Spannable)) {
                charSequence = new SpannableString(charSequence);
            }
            ((Spannable) charSequence).setSpan(h.a(typeface), 0, charSequence.length(), 33);
        }
        return charSequence;
    }

    public static boolean a(TextView textView, final Typeface typeface, boolean z) {
        if (textView == null || typeface == null) {
            return false;
        }
        textView.setPaintFlags(textView.getPaintFlags() | 128 | 1);
        textView.setTypeface(typeface);
        if (z) {
            textView.setText(a(textView.getText(), typeface), BufferType.SPANNABLE);
            textView.addTextChangedListener(new TextWatcher() {
                public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                }

                public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                }

                public void afterTextChanged(Editable editable) {
                    e.a(editable, typeface);
                }
            });
        }
        return true;
    }

    public static boolean a(Context context, TextView textView, String str) {
        return a(context, textView, str, false);
    }

    static boolean a(Context context, TextView textView, String str, boolean z) {
        if (textView == null || context == null) {
            return false;
        }
        return a(textView, h.a(context.getAssets(), str), z);
    }

    static void a(Context context, TextView textView, b bVar, boolean z) {
        if (context != null && textView != null && bVar != null && bVar.c()) {
            a(context, textView, bVar.b(), z);
        }
    }

    static void a(Context context, TextView textView, b bVar, String str, boolean z) {
        if (context != null && textView != null && bVar != null) {
            if (TextUtils.isEmpty(str) || !a(context, textView, str, z)) {
                a(context, textView, bVar, z);
            }
        }
    }

    static String a(Context context, AttributeSet attributeSet, int i) {
        String str;
        if (i == -1 || attributeSet == null) {
            return null;
        }
        try {
            String resourceEntryName = context.getResources().getResourceEntryName(i);
            int attributeResourceValue = attributeSet.getAttributeResourceValue(null, resourceEntryName, -1);
            if (attributeResourceValue > 0) {
                str = context.getString(attributeResourceValue);
            } else {
                str = attributeSet.getAttributeValue(null, resourceEntryName);
            }
            return str;
        } catch (NotFoundException unused) {
            return null;
        }
    }

    static String b(Context context, AttributeSet attributeSet, int i) {
        if (i == -1 || attributeSet == null) {
            return null;
        }
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{i});
        if (obtainStyledAttributes != null) {
            try {
                String string = obtainStyledAttributes.getString(0);
                if (!TextUtils.isEmpty(string)) {
                    obtainStyledAttributes.recycle();
                    return string;
                }
            } catch (Exception unused) {
            } catch (Throwable th) {
                obtainStyledAttributes.recycle();
                throw th;
            }
            obtainStyledAttributes.recycle();
        }
        return null;
    }

    static String c(Context context, AttributeSet attributeSet, int i) {
        int i2 = -1;
        if (i == -1 || attributeSet == null) {
            return null;
        }
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{16842804});
        if (obtainStyledAttributes != null) {
            try {
                i2 = obtainStyledAttributes.getResourceId(0, -1);
            } catch (Exception unused) {
                return null;
            } finally {
                obtainStyledAttributes.recycle();
            }
        }
        TypedArray obtainStyledAttributes2 = context.obtainStyledAttributes(i2, new int[]{i});
        if (obtainStyledAttributes2 == null) {
            return null;
        }
        try {
            return obtainStyledAttributes2.getString(0);
        } catch (Exception unused2) {
            return null;
        } finally {
            obtainStyledAttributes2.recycle();
        }
    }

    static String a(Context context, int i, int i2) {
        if (i == -1 || i2 == -1) {
            return null;
        }
        Theme theme = context.getTheme();
        TypedValue typedValue = new TypedValue();
        theme.resolveAttribute(i, typedValue, true);
        TypedArray obtainStyledAttributes = theme.obtainStyledAttributes(typedValue.resourceId, new int[]{i2});
        try {
            return obtainStyledAttributes.getString(0);
        } catch (Exception unused) {
            return null;
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    static String a(Context context, int i, int i2, int i3) {
        if (i == -1 || i3 == -1) {
            return null;
        }
        Theme theme = context.getTheme();
        TypedValue typedValue = new TypedValue();
        theme.resolveAttribute(i, typedValue, true);
        TypedArray obtainStyledAttributes = theme.obtainStyledAttributes(typedValue.resourceId, new int[]{i2});
        try {
            int resourceId = obtainStyledAttributes.getResourceId(0, -1);
            if (resourceId == -1) {
                return null;
            }
            TypedArray obtainStyledAttributes2 = context.obtainStyledAttributes(resourceId, new int[]{i3});
            if (obtainStyledAttributes2 == null) {
                return null;
            }
            try {
                return obtainStyledAttributes2.getString(0);
            } catch (Exception unused) {
                return null;
            } finally {
                obtainStyledAttributes2.recycle();
            }
        } catch (Exception unused2) {
            return null;
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    static boolean a() {
        if (a == null) {
            try {
                Class.forName("android.support.v7.widget.Toolbar");
                a = Boolean.TRUE;
            } catch (ClassNotFoundException unused) {
                a = Boolean.FALSE;
            }
        }
        return a.booleanValue();
    }
}
