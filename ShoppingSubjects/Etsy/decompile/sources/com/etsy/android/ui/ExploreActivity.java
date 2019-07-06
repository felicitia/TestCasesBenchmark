package com.etsy.android.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import com.etsy.android.R;
import com.etsy.android.lib.models.homescreen.HomescreenTab;
import com.etsy.android.ui.homescreen.HomescreenFragment;
import com.etsy.android.ui.nav.e;
import java.util.HashMap;
import kotlin.jvm.internal.p;
import org.parceler.d;

/* compiled from: ExploreActivity.kt */
public final class ExploreActivity extends BOENavDrawerActivity {
    public static final a Companion = new a(null);
    public static final String DATA = "data";
    private HashMap _$_findViewCache;

    /* compiled from: ExploreActivity.kt */
    public static final class a {
        private a() {
        }

        public /* synthetic */ a(o oVar) {
            this();
        }
    }

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

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle == null) {
            Intent intent = getIntent();
            p.a((Object) intent, "intent");
            Bundle extras = intent.getExtras();
            if (extras != null) {
                Object obj = extras.get("data");
                if (obj != null && (obj instanceof HomescreenTab)) {
                    setTitle(((HomescreenTab) obj).getTitle());
                }
                Bundle bundle2 = new Bundle();
                bundle2.putParcelable(HomescreenFragment.ARG_TAB_DATA, d.a(obj));
                e.a((FragmentActivity) this).f().c(bundle2);
            }
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        p.b(menuItem, "item");
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        return navigateUpAsBack();
    }

    public boolean onCreateOptionsMenuWithIcons(Menu menu) {
        p.b(menu, "menu");
        getMenuInflater().inflate(R.menu.default_action_bar, menu);
        return true;
    }
}
