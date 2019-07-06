package com.etsy.android.ui.core.listingpanel;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.text.Html;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import com.etsy.android.R;
import com.etsy.android.lib.logger.w;
import com.etsy.android.lib.models.Listing;
import com.etsy.android.uikit.BaseActivity;
import java.util.List;

/* compiled from: ListingPanelOverview */
public class g extends d {
    public g(Listing listing, BaseActivity baseActivity, @NonNull w wVar) {
        super(listing, baseActivity, wVar);
        a(R.id.panel_details_overview, R.id.panel_title_overview, R.id.img_overview_open, R.id.txt_overview_title);
    }

    /* access modifiers changed from: protected */
    public void c() {
        TableLayout tableLayout = (TableLayout) this.d.findViewById(R.id.table_overview);
        if (this.b.hasOverview()) {
            a(this.a, this.b.getOverview(), tableLayout);
        }
    }

    public static void a(Activity activity, List<String> list, TableLayout tableLayout) {
        int size = list.size();
        int dimensionPixelOffset = tableLayout.getResources().getDimensionPixelOffset(R.dimen.fixed_tiny);
        tableLayout.removeAllViews();
        for (int i = 0; i < size; i++) {
            TableRow a = a(activity, (String) list.get(i));
            if (i != 0) {
                a.setPadding(0, dimensionPixelOffset, 0, 0);
            }
            tableLayout.addView(a);
        }
    }

    private static TableRow a(Activity activity, String str) {
        TableRow tableRow = (TableRow) activity.getLayoutInflater().inflate(R.layout.listing_panel_overview_line, null, false);
        tableRow.setGravity(48);
        TextView textView = (TextView) tableRow.findViewById(R.id.line_body_text_view);
        ((TextView) tableRow.findViewById(R.id.line_bullet_text_view)).setText(Html.fromHtml("&#8226; "));
        textView.setText(str);
        return tableRow;
    }
}
