package com.etsy.android.ui.feedback;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import com.etsy.android.R;
import com.etsy.android.lib.core.b.a;
import com.etsy.android.ui.BOENavDrawerActivity;
import com.etsy.android.ui.nav.e;
import java.util.HashMap;
import kotlin.jvm.internal.p;

/* compiled from: FeedbackActivity.kt */
public final class FeedbackActivity extends BOENavDrawerActivity implements a {
    private HashMap _$_findViewCache;
    private Bundle bundle;
    public g presenter;

    public void _$_clearFindViewByIdCache() {
        if (this._$_findViewCache != null) {
            this._$_findViewCache.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View findViewById = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    public final g getPresenter() {
        g gVar = this.presenter;
        if (gVar == null) {
            p.b("presenter");
        }
        return gVar;
    }

    public final void setPresenter(g gVar) {
        p.b(gVar, "<set-?>");
        this.presenter = gVar;
    }

    public final Bundle getBundle() {
        return this.bundle;
    }

    public final void setBundle(Bundle bundle2) {
        this.bundle = bundle2;
    }

    public void onCreate(Bundle bundle2) {
        super.onCreate(bundle2);
        setContentView((int) R.layout.activity_feedback);
        this.bundle = bundle2;
        setTitle(R.string.feedback);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        g gVar = this.presenter;
        if (gVar == null) {
            p.b("presenter");
        }
        View findViewById = findViewById(R.id.screen_feedback);
        p.a((Object) findViewById, "findViewById(R.id.screen_feedback)");
        FeedbackView feedbackView = (FeedbackView) findViewById;
        Activity activity = this;
        Bundle bundle2 = this.bundle;
        e a = e.a((FragmentActivity) this);
        p.a((Object) a, "Nav.with(this)");
        gVar.a(feedbackView, activity, bundle2, a, io.reactivex.a.b.a.a());
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
        g gVar = this.presenter;
        if (gVar == null) {
            p.b("presenter");
        }
        gVar.a();
    }

    public boolean onCreateOptionsMenuWithIcons(Menu menu) {
        getMenuInflater().inflate(R.menu.feedback_send_menu, menu);
        return super.onCreateOptionsMenuWithIcons(menu);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        g gVar = this.presenter;
        if (gVar == null) {
            p.b("presenter");
        }
        gVar.b();
        return super.onOptionsItemSelected(menuItem);
    }
}
