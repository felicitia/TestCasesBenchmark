package com.etsy.android.uikit.nav;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import com.etsy.android.lib.a.C0051a;
import com.etsy.android.lib.a.o;
import com.etsy.android.lib.core.EtsyApplication;
import com.etsy.android.lib.ui.nav.a;
import com.etsy.android.lib.util.af;
import com.etsy.android.lib.util.l;
import com.etsy.android.uikit.nav.ActivityNavigator;
import com.etsy.android.uikit.ui.core.BaseDialogFragment;
import com.etsy.android.uikit.ui.core.DialogLauncherActivity;
import com.etsy.android.uikit.ui.core.TextDialogFragment;

public abstract class ActivityNavigator<NavigatorClass extends ActivityNavigator<NavigatorClass>> extends a<NavigatorClass> {
    protected boolean a;
    protected boolean b;
    protected boolean c;
    protected boolean d;
    protected int e;
    protected String f;
    protected AnimationMode g;
    protected boolean h;
    protected Activity i;
    protected Fragment j;

    public enum AnimationMode {
        SLIDE_RIGHT,
        SLIDE_LEFT,
        SLIDE_BOTTOM,
        FADE_SLOW,
        FADE_IN_OUT,
        POP,
        DEFAULT,
        DEFAULT_OUT,
        ZOOM_IN_OUT,
        NONE
    }

    /* access modifiers changed from: protected */
    public abstract void a(Class<? extends BaseDialogFragment> cls, @StringRes int i2, Bundle bundle);

    /* access modifiers changed from: protected */
    public abstract void b(Intent intent);

    protected ActivityNavigator(Activity activity) {
        this.i = activity;
    }

    /* access modifiers changed from: protected */
    public Activity a() {
        return this.i;
    }

    /* access modifiers changed from: protected */
    public Intent c(Intent intent) {
        if (this.a) {
            intent.putExtra("NAV_DRAWER", true);
        }
        if (this.b) {
            intent.putExtra("NAV_TOP_LEVEL_DRAWER", true);
        }
        if (this.d) {
            intent.putExtra("NAV_UP_TO_PARENT", true);
        }
        if (!TextUtils.isEmpty(this.f)) {
            intent.putExtra("opened_notification_type", this.f);
        }
        return a(intent, this.g);
    }

    /* access modifiers changed from: protected */
    public void a(Class<? extends DialogLauncherActivity> cls, Class<? extends BaseDialogFragment> cls2, String str, Bundle bundle) {
        Intent intent = new Intent(this.i, cls);
        intent.putExtra("dialog_fragment", cls2.getName());
        intent.putExtra("title", str);
        intent.putExtra("dialog_fragment_bundle", bundle);
        if (l.c(this.i)) {
            this.h = true;
            this.g = AnimationMode.FADE_SLOW;
        } else {
            this.g = AnimationMode.SLIDE_BOTTOM;
        }
        b(intent);
    }

    /* access modifiers changed from: protected */
    public void a(Class<? extends DialogLauncherActivity> cls, Class<? extends BaseDialogFragment> cls2, @StringRes int i2, Bundle bundle) {
        a(cls, cls2, this.i.getString(i2), bundle);
    }

    public static Intent a(Intent intent, AnimationMode animationMode) {
        int i2;
        int i3 = 0;
        switch (animationMode) {
            case SLIDE_RIGHT:
                i3 = C0051a.nav_bottom_enter;
                i2 = C0051a.nav_top_exit_right;
                break;
            case SLIDE_LEFT:
                i3 = C0051a.nav_bottom_enter;
                i2 = C0051a.nav_top_exit_right;
                break;
            case SLIDE_BOTTOM:
                i3 = C0051a.nav_bottom_enter;
                i2 = C0051a.nav_top_exit_bottom;
                break;
            case FADE_SLOW:
                i3 = C0051a.nav_none;
                i2 = C0051a.nav_fade_out;
                break;
            case POP:
                i3 = C0051a.nav_fade_in;
                i2 = C0051a.nav_top_exit_pop;
                break;
            case FADE_IN_OUT:
                i3 = C0051a.nav_fade_in;
                i2 = C0051a.nav_fade_out;
                break;
            case DEFAULT:
                i3 = C0051a.nav_top_zoom_enter;
                i2 = C0051a.nav_top_exit_default;
                break;
            case DEFAULT_OUT:
                i3 = C0051a.nav_top_zoom_enter;
                i2 = C0051a.nav_top_exit_default;
                break;
            case ZOOM_IN_OUT:
                i3 = C0051a.nav_top_zoom_enter;
                i2 = C0051a.nav_bottom_zoom_exit;
                break;
            default:
                i2 = 0;
                break;
        }
        intent.putExtra("NAV_ANIM_BOTTOM_ENTER", i3);
        intent.putExtra("NAV_ANIM_TOP_EXIT", i2);
        return intent;
    }

    public static void a(Activity activity, AnimationMode animationMode) {
        int i2;
        int i3 = 0;
        switch (animationMode) {
            case SLIDE_RIGHT:
                i3 = C0051a.nav_top_enter_right;
                i2 = C0051a.nav_bottom_exit;
                break;
            case SLIDE_LEFT:
                i3 = C0051a.nav_top_enter_left;
                i2 = C0051a.nav_bottom_exit;
                break;
            case SLIDE_BOTTOM:
                i3 = C0051a.nav_top_enter_bottom;
                i2 = C0051a.nav_bottom_exit;
                break;
            case FADE_SLOW:
                i3 = C0051a.nav_fade_in;
                i2 = C0051a.nav_none;
                break;
            case POP:
                i3 = C0051a.nav_top_enter_pop;
                i2 = C0051a.nav_fade_out;
                break;
            case FADE_IN_OUT:
                i3 = C0051a.nav_fade_in;
                i2 = C0051a.nav_fade_out;
                break;
            case DEFAULT:
                i3 = C0051a.nav_top_enter_default;
                i2 = C0051a.nav_top_zoom_exit;
                break;
            case DEFAULT_OUT:
                i3 = C0051a.nav_empty;
                i2 = C0051a.nav_empty;
                break;
            case ZOOM_IN_OUT:
                i3 = C0051a.nav_bottom_zoom_enter;
                i2 = C0051a.nav_top_zoom_exit;
                break;
            default:
                i2 = 0;
                break;
        }
        activity.overridePendingTransition(i3, i2);
    }

    public static void a(@NonNull FragmentActivity fragmentActivity) {
        Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.addCategory("android.intent.category.DEFAULT");
        StringBuilder sb = new StringBuilder();
        sb.append(EtsyApplication.PACKAGE_PREFIX);
        sb.append(fragmentActivity.getPackageName());
        intent.setData(Uri.parse(sb.toString()));
        fragmentActivity.startActivity(intent);
    }

    public void k(@Nullable String str) {
        if (af.b(str)) {
            a(o.terms_and_conditions_title, (CharSequence) str);
        }
    }

    public void a(@StringRes int i2, @NonNull CharSequence charSequence) {
        Bundle bundle = new Bundle();
        bundle.putCharSequence("text", charSequence);
        a(TextDialogFragment.class, i2, bundle);
    }
}
