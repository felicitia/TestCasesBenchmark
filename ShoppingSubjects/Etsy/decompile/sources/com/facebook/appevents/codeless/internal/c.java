package com.facebook.appevents.codeless.internal;

import android.support.annotation.Nullable;
import android.support.v4.view.NestedScrollingChild;
import android.util.Log;
import android.view.View;
import android.view.View.AccessibilityDelegate;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import com.etsy.android.lib.models.ResponseConstants;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ViewHierarchy */
public class c {
    private static final String a = c.class.getCanonicalName();

    public static List<View> a(View view) {
        ArrayList arrayList = new ArrayList();
        if (view != null && (view instanceof ViewGroup)) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                arrayList.add(viewGroup.getChildAt(i));
            }
        }
        return arrayList;
    }

    public static JSONObject b(View view) {
        JSONObject jSONObject = new JSONObject();
        try {
            String c = c(view);
            String d = d(view);
            Object tag = view.getTag();
            CharSequence contentDescription = view.getContentDescription();
            jSONObject.put("classname", view.getClass().getCanonicalName());
            jSONObject.put("classtypebitmask", f(view));
            jSONObject.put("id", view.getId());
            if (!b.a(view)) {
                jSONObject.put("text", c);
            } else {
                jSONObject.put("text", "");
            }
            jSONObject.put(ResponseConstants.HINT, d);
            if (tag != null) {
                jSONObject.put("tag", tag.toString());
            }
            if (contentDescription != null) {
                jSONObject.put("description", contentDescription.toString());
            }
            jSONObject.put("dimension", i(view));
            JSONArray jSONArray = new JSONArray();
            List a2 = a(view);
            for (int i = 0; i < a2.size(); i++) {
                jSONArray.put(b((View) a2.get(i)));
            }
            jSONObject.put("childviews", jSONArray);
        } catch (JSONException e) {
            Log.e(a, "Failed to create JSONObject for view.", e);
        }
        return jSONObject;
    }

    private static int f(View view) {
        int i = view instanceof ImageView ? 2 : 0;
        if (g(view)) {
            i |= 32;
        }
        if (h(view)) {
            i |= 512;
        }
        if (view instanceof TextView) {
            int i2 = i | 1024 | 1;
            if (view instanceof Button) {
                i2 |= 4;
                if (view instanceof Switch) {
                    i2 |= 8192;
                } else if (view instanceof CheckBox) {
                    i2 |= 32768;
                }
            }
            if (view instanceof EditText) {
                return i2 | 2048;
            }
            return i2;
        } else if ((view instanceof Spinner) || (view instanceof DatePicker)) {
            return i | 4096;
        } else {
            if (view instanceof RatingBar) {
                return i | 65536;
            }
            return view instanceof RadioGroup ? i | 16384 : i;
        }
    }

    private static boolean g(View view) {
        boolean z = false;
        try {
            Field declaredField = Class.forName("android.view.View").getDeclaredField("mListenerInfo");
            if (declaredField != null) {
                declaredField.setAccessible(true);
            }
            Object obj = declaredField.get(view);
            if (obj == null) {
                return false;
            }
            OnClickListener onClickListener = null;
            Field declaredField2 = Class.forName("android.view.View$ListenerInfo").getDeclaredField("mOnClickListener");
            if (declaredField2 != null) {
                onClickListener = (OnClickListener) declaredField2.get(obj);
            }
            if (onClickListener != null) {
                z = true;
            }
            return z;
        } catch (Exception e) {
            Log.e(a, "Failed to check if the view is clickable.", e);
            return false;
        }
    }

    private static boolean h(View view) {
        ViewParent parent = view.getParent();
        return parent != null && ((parent instanceof AdapterView) || (parent instanceof NestedScrollingChild));
    }

    /* JADX WARNING: Removed duplicated region for block: B:34:0x00c7  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00ca  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String c(android.view.View r7) {
        /*
            boolean r0 = r7 instanceof android.widget.TextView
            r1 = 0
            if (r0 == 0) goto L_0x0020
            r0 = r7
            android.widget.TextView r0 = (android.widget.TextView) r0
            java.lang.CharSequence r1 = r0.getText()
            boolean r0 = r7 instanceof android.widget.Switch
            if (r0 == 0) goto L_0x00c5
            android.widget.Switch r7 = (android.widget.Switch) r7
            boolean r7 = r7.isChecked()
            if (r7 == 0) goto L_0x001d
            java.lang.String r7 = "1"
        L_0x001a:
            r1 = r7
            goto L_0x00c5
        L_0x001d:
            java.lang.String r7 = "0"
            goto L_0x001a
        L_0x0020:
            boolean r0 = r7 instanceof android.widget.Spinner
            if (r0 == 0) goto L_0x0032
            android.widget.Spinner r7 = (android.widget.Spinner) r7
            java.lang.Object r7 = r7.getSelectedItem()
            if (r7 == 0) goto L_0x00c5
            java.lang.String r1 = r7.toString()
            goto L_0x00c5
        L_0x0032:
            boolean r0 = r7 instanceof android.widget.DatePicker
            r2 = 2
            r3 = 1
            r4 = 0
            if (r0 == 0) goto L_0x0063
            android.widget.DatePicker r7 = (android.widget.DatePicker) r7
            int r0 = r7.getYear()
            int r1 = r7.getMonth()
            int r7 = r7.getDayOfMonth()
            java.lang.String r5 = "%04d-%02d-%02d"
            r6 = 3
            java.lang.Object[] r6 = new java.lang.Object[r6]
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r6[r4] = r0
            java.lang.Integer r0 = java.lang.Integer.valueOf(r1)
            r6[r3] = r0
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)
            r6[r2] = r7
            java.lang.String r1 = java.lang.String.format(r5, r6)
            goto L_0x00c5
        L_0x0063:
            boolean r0 = r7 instanceof android.widget.TimePicker
            if (r0 == 0) goto L_0x008e
            android.widget.TimePicker r7 = (android.widget.TimePicker) r7
            java.lang.Integer r0 = r7.getCurrentHour()
            int r0 = r0.intValue()
            java.lang.Integer r7 = r7.getCurrentMinute()
            int r7 = r7.intValue()
            java.lang.String r1 = "%02d:%02d"
            java.lang.Object[] r2 = new java.lang.Object[r2]
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r2[r4] = r0
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)
            r2[r3] = r7
            java.lang.String r1 = java.lang.String.format(r1, r2)
            goto L_0x00c5
        L_0x008e:
            boolean r0 = r7 instanceof android.widget.RadioGroup
            if (r0 == 0) goto L_0x00b7
            android.widget.RadioGroup r7 = (android.widget.RadioGroup) r7
            int r0 = r7.getCheckedRadioButtonId()
            int r2 = r7.getChildCount()
        L_0x009c:
            if (r4 >= r2) goto L_0x00c5
            android.view.View r3 = r7.getChildAt(r4)
            int r5 = r3.getId()
            if (r5 != r0) goto L_0x00b4
            boolean r5 = r3 instanceof android.widget.RadioButton
            if (r5 == 0) goto L_0x00b4
            android.widget.RadioButton r3 = (android.widget.RadioButton) r3
            java.lang.CharSequence r7 = r3.getText()
            goto L_0x001a
        L_0x00b4:
            int r4 = r4 + 1
            goto L_0x009c
        L_0x00b7:
            boolean r0 = r7 instanceof android.widget.RatingBar
            if (r0 == 0) goto L_0x00c5
            android.widget.RatingBar r7 = (android.widget.RatingBar) r7
            float r7 = r7.getRating()
            java.lang.String r1 = java.lang.String.valueOf(r7)
        L_0x00c5:
            if (r1 != 0) goto L_0x00ca
            java.lang.String r7 = ""
            goto L_0x00ce
        L_0x00ca:
            java.lang.String r7 = r1.toString()
        L_0x00ce:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.appevents.codeless.internal.c.c(android.view.View):java.lang.String");
    }

    public static String d(View view) {
        CharSequence charSequence = view instanceof TextView ? ((TextView) view).getHint() : view instanceof EditText ? ((EditText) view).getHint() : null;
        if (charSequence == null) {
            return "";
        }
        return charSequence.toString();
    }

    private static JSONObject i(View view) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("top", view.getTop());
            jSONObject.put("left", view.getLeft());
            jSONObject.put(ResponseConstants.WIDTH, view.getWidth());
            jSONObject.put(ResponseConstants.HEIGHT, view.getHeight());
            jSONObject.put("scrollx", view.getScrollX());
            jSONObject.put("scrolly", view.getScrollY());
            jSONObject.put("visibility", view.getVisibility());
        } catch (JSONException e) {
            Log.e(a, "Failed to create JSONObject for dimension.", e);
        }
        return jSONObject;
    }

    @Nullable
    public static AccessibilityDelegate e(View view) {
        try {
            return (AccessibilityDelegate) view.getClass().getMethod("getAccessibilityDelegate", new Class[0]).invoke(view, new Object[0]);
        } catch (NoSuchMethodException unused) {
            return null;
        } catch (NullPointerException unused2) {
            return null;
        } catch (SecurityException unused3) {
            return null;
        } catch (IllegalAccessException unused4) {
            return null;
        } catch (InvocationTargetException unused5) {
            return null;
        }
    }
}
