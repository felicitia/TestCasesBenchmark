package com.etsy.android.uikit;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import com.etsy.android.lib.a.g;
import com.etsy.android.lib.a.i;
import com.etsy.android.lib.a.k;
import com.etsy.android.lib.a.o;

public abstract class BasePreferenceActivity extends AppCompatActivity {
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(k.activity_base_preference);
        initActionBar();
    }

    /* access modifiers changed from: protected */
    public void initActionBar() {
        setSupportActionBar((Toolbar) findViewById(i.app_bar_toolbar));
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
            supportActionBar.setDisplayShowHomeEnabled(true);
            supportActionBar.setIcon(g.ic_menu_e);
            supportActionBar.setTitle((CharSequence) getString(o.config));
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            if (!(getCurrentFocus() == null || getCurrentFocus().getWindowToken() == null)) {
                ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            }
            getFragmentManager().popBackStack();
        } else {
            finish();
        }
        return true;
    }
}
