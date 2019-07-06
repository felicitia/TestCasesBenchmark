package com.etsy.android.vespa.viewholders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.etsy.android.lib.a.i;
import com.etsy.android.lib.a.k;
import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.apiv3.ExploreResultsHeader;
import com.etsy.android.lib.models.apiv3.vespa.CardActionableItem;
import com.etsy.android.vespa.a.f;
import kotlin.TypeCastException;
import kotlin.jvm.internal.p;

/* compiled from: ExploreResultsHeaderViewHolder.kt */
public final class ExploreResultsHeaderViewHolder extends BaseViewHolder<CardActionableItem> {
    private final View btnFilter;
    private final f mClickHandler;
    private final TextView txtTitle;

    public ExploreResultsHeaderViewHolder(ViewGroup viewGroup, f fVar) {
        p.b(viewGroup, ResponseConstants.PARENT);
        p.b(fVar, "mClickHandler");
        super(LayoutInflater.from(viewGroup.getContext()).inflate(k.list_item_explore_results_header, viewGroup, false));
        this.mClickHandler = fVar;
        View findViewById = findViewById(i.txt_title);
        if (findViewById == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.TextView");
        }
        this.txtTitle = (TextView) findViewById;
        View findViewById2 = findViewById(i.btn_filter);
        p.a((Object) findViewById2, "findViewById(R.id.btn_filter)");
        this.btnFilter = findViewById2;
    }

    public void bind(CardActionableItem cardActionableItem) {
        p.b(cardActionableItem, "item");
        super.bind(cardActionableItem);
        BaseModel data = cardActionableItem.getData();
        if (data == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.etsy.android.lib.models.apiv3.ExploreResultsHeader");
        }
        this.txtTitle.setText(((ExploreResultsHeader) data).getTitle());
    }
}
