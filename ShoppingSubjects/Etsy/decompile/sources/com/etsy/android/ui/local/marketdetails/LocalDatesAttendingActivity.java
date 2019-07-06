package com.etsy.android.ui.local.marketdetails;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import com.etsy.android.lib.models.Attendee;
import com.etsy.android.lib.models.LocalMarket;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.ui.BOENavDrawerActivity;
import com.etsy.android.ui.nav.e;

public class LocalDatesAttendingActivity extends BOENavDrawerActivity {
    public boolean isTopLevelActivity() {
        return false;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle == null) {
            e.a((FragmentActivity) this).f().a((Attendee) getIntent().getSerializableExtra("attendee"), (LocalMarket) getIntent().getSerializableExtra(ResponseConstants.LOCAL_MARKET));
        }
        getAppBarHelper().hideAppBar();
    }
}
