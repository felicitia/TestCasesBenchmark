package com.etsy.android.ui.convos;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.MenuItem;
import com.etsy.android.R;
import com.etsy.android.lib.convos.h;
import com.etsy.android.lib.core.b.a;
import com.etsy.android.lib.logger.f;
import com.etsy.android.lib.models.Conversation;
import com.etsy.android.lib.util.l;
import com.etsy.android.ui.BOENavDrawerActivity;
import com.etsy.android.ui.nav.e;

public class ConvoViewActivity extends BOENavDrawerActivity implements a {
    private static final String TAG = f.a(ConvoViewActivity.class);

    public void onCreate(Bundle bundle) {
        Conversation conversation;
        super.onCreate(bundle);
        boolean z = true;
        if (getIntent() != null) {
            Bundle extras = getIntent().getExtras();
            conversation = (Conversation) extras.getSerializable("conversation");
            z = extras.getBoolean("convo_change_read_state", true);
        } else {
            conversation = null;
        }
        if (l.a((Activity) this) || conversation == null) {
            finish();
            return;
        }
        setContentView((int) R.layout.activity_convo);
        if (bundle == null) {
            e.a((FragmentActivity) this).f().a("convo_thread_fragment").a(conversation, z);
            setTitle(conversation.getOtherUser().getDisplayName());
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        h.b(this);
        navigateUpAsBack();
        return false;
    }

    public void onBackPressed() {
        h.b(this);
        com.etsy.android.uikit.f fVar = (com.etsy.android.uikit.f) getSupportFragmentManager().findFragmentByTag("convo_thread_fragment");
        if (fVar != null) {
            fVar.handleBackPressed();
        }
        super.onBackPressed();
    }
}
