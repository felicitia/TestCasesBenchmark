package com.etsy.android.ui.user;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.MenuItem;
import com.etsy.android.R;
import com.etsy.android.ui.BOENavDrawerActivity;
import com.etsy.android.ui.nav.e;

public class LeaveFeedbackActivity extends BOENavDrawerActivity {
    public boolean isTopLevelActivity() {
        return false;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle == null) {
            e.a((FragmentActivity) this).f().a(getIntent().getExtras()).f();
        }
        getWindow().setBackgroundDrawableResource(R.color.sk_white);
        setTitle(R.string.write_a_review);
        setNavStyleModal();
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != R.id.review_submit) {
            return super.onOptionsItemSelected(menuItem);
        }
        return false;
    }
}
