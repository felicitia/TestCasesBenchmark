package com.etsy.android.ui.homescreen;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog.Builder;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import com.etsy.android.R;
import com.etsy.android.lib.core.http.request.a;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.util.BOEOptionsMenuItemHelper;
import java.util.HashMap;
import java.util.Map;

public class RecentlyViewedPageFragment extends LandingPageFragment {
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setHasOptionsMenu(true);
    }

    /* access modifiers changed from: protected */
    public Map<String, String> getParams() {
        HashMap params = this.mPageLink.getParams();
        params.putAll(b.a().c());
        return params;
    }

    public final void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menu.clear();
        FragmentActivity activity = getActivity();
        if (activity != null) {
            menuInflater.inflate(R.menu.recently_viewed_action_bar, menu);
            MenuItem findItem = menu.findItem(R.id.clear);
            View a = BOEOptionsMenuItemHelper.a(activity, R.color.sk_orange_20, R.string.clear);
            findItem.setActionView(a);
            a.setOnClickListener(new TrackingOnClickListener() {
                public void onViewClick(View view) {
                    RecentlyViewedPageFragment.this.confirmClearRecentlyViewed();
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void confirmClearRecentlyViewed() {
        Builder builder = new Builder(getContext());
        builder.setTitle((int) R.string.confirm_clear_recently_viewed_title);
        builder.setMessage((int) R.string.confirm_clear_recently_viewed);
        builder.setNegativeButton((int) R.string.clear, (OnClickListener) new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                RecentlyViewedPageFragment.this.clearRecentlyViewed();
            }
        });
        builder.setPositiveButton((int) R.string.cancel, (OnClickListener) new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        builder.create().show();
    }

    /* access modifiers changed from: private */
    public void clearRecentlyViewed() {
        getRequestQueue().a((a<?, ?, ?>) b.a().a(getActivity(), new b.a() {
            public void a() {
                FragmentActivity activity = RecentlyViewedPageFragment.this.getActivity();
                if (activity != null) {
                    activity.finish();
                }
            }
        }));
    }
}
