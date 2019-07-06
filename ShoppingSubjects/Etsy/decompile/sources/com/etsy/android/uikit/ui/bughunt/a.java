package com.etsy.android.uikit.ui.bughunt;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import com.etsy.android.lib.a.C0051a;
import com.etsy.android.lib.a.i;

/* compiled from: BugHuntNav */
public class a {
    private FragmentActivity a;

    protected a(FragmentActivity fragmentActivity) {
        this.a = fragmentActivity;
    }

    public static a a(FragmentActivity fragmentActivity) {
        return new a(fragmentActivity);
    }

    public void a() {
        c(null);
    }

    public void a(String str) {
        c(str);
    }

    private void c(@Nullable String str) {
        Intent intent = new Intent(this.a, BugHuntActivity.class);
        if (!TextUtils.isEmpty(str)) {
            intent.putExtra("image_uri", str);
        }
        this.a.startActivity(intent);
        this.a.overridePendingTransition(C0051a.nav_bottom_enter, C0051a.nav_bottom_exit);
    }

    public void b() {
        FragmentManager supportFragmentManager = this.a.getSupportFragmentManager();
        if (supportFragmentManager.findFragmentByTag("statsFragment") == null) {
            FragmentTransaction beginTransaction = supportFragmentManager.beginTransaction();
            beginTransaction.replace(i.fragment_container, BugHuntLeaderboardFragment.newInstance(), "statsFragment");
            beginTransaction.setCustomAnimations(C0051a.nav_frag_bottom_enter, C0051a.nav_frag_bottom_exit);
            beginTransaction.commitAllowingStateLoss();
        }
    }

    public void b(String str) {
        FragmentManager supportFragmentManager = this.a.getSupportFragmentManager();
        if (supportFragmentManager.findFragmentByTag("composeFragment") == null) {
            FragmentTransaction beginTransaction = supportFragmentManager.beginTransaction();
            beginTransaction.replace(i.fragment_container, BugHuntComposeFragment.newInstance(str), "composeFragment");
            beginTransaction.setCustomAnimations(C0051a.nav_frag_bottom_enter, C0051a.nav_frag_bottom_exit);
            beginTransaction.commitAllowingStateLoss();
        }
    }

    public BugHuntComposeFragment c() {
        return (BugHuntComposeFragment) this.a.getSupportFragmentManager().findFragmentByTag("composeFragment");
    }
}
