package com.etsy.android.uikit.nav;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;

/* compiled from: NavBase */
public class b {
    protected final FragmentManager a;
    protected FragmentActivity b;

    protected b(FragmentActivity fragmentActivity, FragmentManager fragmentManager) {
        this.b = fragmentActivity;
        this.a = fragmentManager;
    }

    protected b(FragmentActivity fragmentActivity) {
        this.b = fragmentActivity;
        this.a = fragmentActivity.getSupportFragmentManager();
    }

    public static b b(FragmentActivity fragmentActivity) {
        return new b(fragmentActivity);
    }

    public void g() {
        a(false);
    }

    public void a(boolean z) {
        Intent parentActivityIntent = NavUtils.getParentActivityIntent(this.b);
        if (parentActivityIntent == null || (!z && !NavUtils.shouldUpRecreateTask(this.b, parentActivityIntent))) {
            NavUtils.navigateUpFromSameTask(this.b);
        } else {
            TaskStackBuilder.create(this.b).addNextIntentWithParentStack(parentActivityIntent).startActivities();
        }
        i();
    }

    public void a(Intent intent) {
        intent.addFlags(67108864);
        this.b.startActivity(intent);
        this.b.finish();
        i();
    }

    public void h() {
        this.b.finish();
        i();
    }

    public void a(int i, Intent intent) {
        this.b.setResult(i, intent);
        h();
    }

    public void i() {
        Intent intent = this.b.getIntent();
        if (intent != null) {
            this.b.overridePendingTransition(intent.getIntExtra("NAV_ANIM_BOTTOM_ENTER", 0), intent.getIntExtra("NAV_ANIM_TOP_EXIT", 0));
        }
    }
}
