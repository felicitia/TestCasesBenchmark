package com.etsy.android.ui.adapters;

import android.support.v4.app.FragmentActivity;
import android.view.View;
import com.etsy.android.lib.core.img.c;
import com.etsy.android.lib.logger.f;
import com.etsy.android.lib.models.Listing;
import com.etsy.android.ui.adapters.b.a;
import com.etsy.android.uikit.adapter.MultiColumnAdapter;

@Deprecated
public class MultiColumnListingAdapter extends MultiColumnAdapter<Listing> {
    private static final String TAG = f.a(MultiColumnListingAdapter.class);
    protected boolean mExtraPaddingOnTopRow;

    public long getItemId(int i) {
        return 0;
    }

    public MultiColumnListingAdapter(FragmentActivity fragmentActivity, c cVar, int i) {
        super(fragmentActivity, cVar, i, new b(fragmentActivity, cVar, i));
    }

    public MultiColumnListingAdapter(FragmentActivity fragmentActivity, c cVar, int i, int i2) {
        super(fragmentActivity, cVar, i, new b(fragmentActivity, cVar, i, i2));
    }

    /* access modifiers changed from: protected */
    public b getListingRowGenerator() {
        return (b) this.mCardRowGenerator;
    }

    public void setOnListingClickListener(a aVar) {
        getListingRowGenerator().a(aVar);
    }

    public void setWidthModifier(int i) {
        getListingRowGenerator().a(i);
    }

    public void setPadInTabletMargins(boolean z) {
        getListingRowGenerator().a(z);
    }

    public void addExtraPaddingToTopRow(boolean z) {
        this.mExtraPaddingOnTopRow = z;
    }

    /* access modifiers changed from: protected */
    public View getDefaultView(int i, View view) {
        int a = this.mCardRowGenerator.a();
        View a2 = this.mCardRowGenerator.a(view);
        if (this.mExtraPaddingOnTopRow) {
            getListingRowGenerator().b(a2, i);
        }
        bindRow(i, a, a2.getTag());
        return a2;
    }

    /* access modifiers changed from: protected */
    public void bindRow(int i, int i2, Object obj) {
        for (int i3 = 0; i3 < i2; i3++) {
            int i4 = (i * i2) + i3;
            Listing listing = null;
            if (i4 < this.mResults.size()) {
                listing = (Listing) this.mResults.get(i4);
            }
            getListingRowGenerator().a(obj, listing, i3);
        }
    }
}
