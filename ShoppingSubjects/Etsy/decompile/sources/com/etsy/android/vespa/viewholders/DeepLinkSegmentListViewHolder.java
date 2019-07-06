package com.etsy.android.vespa.viewholders;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.etsy.android.extensions.j;
import com.etsy.android.lib.a.f;
import com.etsy.android.lib.a.i;
import com.etsy.android.lib.a.k;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.apiv3.DeepLinkSegmentList;
import com.etsy.android.lib.models.apiv3.Segment;
import com.etsy.android.vespa.a.c;
import java.util.ArrayList;
import java.util.List;
import kotlin.TypeCastException;
import kotlin.jvm.internal.p;

/* compiled from: DeepLinkSegmentListViewHolder.kt */
public final class DeepLinkSegmentListViewHolder extends BaseViewHolder<DeepLinkSegmentList> {
    private final ArrayList<ViewGroup> chipContainers = new ArrayList<>();
    private final c clickHandler;

    public final c getClickHandler() {
        return this.clickHandler;
    }

    public DeepLinkSegmentListViewHolder(ViewGroup viewGroup, c cVar) {
        p.b(viewGroup, ResponseConstants.PARENT);
        p.b(cVar, "clickHandler");
        super(LayoutInflater.from(viewGroup.getContext()).inflate(k.list_item_deep_link_segment_list, viewGroup, false));
        this.clickHandler = cVar;
    }

    public void bind(DeepLinkSegmentList deepLinkSegmentList) {
        p.b(deepLinkSegmentList, "deepLinkSegmentList");
        super.bind(deepLinkSegmentList);
        View view = this.itemView;
        p.a((Object) view, "itemView");
        LinearLayout linearLayout = (LinearLayout) view.findViewById(i.deep_link_segment_row_container);
        if (linearLayout == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.LinearLayout");
        }
        linearLayout.removeAllViews();
        this.chipContainers.clear();
        String title = deepLinkSegmentList.getTitle();
        int numberOfRows = deepLinkSegmentList.getNumberOfRows();
        List segments = deepLinkSegmentList.getSegments();
        View view2 = this.itemView;
        p.a((Object) view2, "itemView");
        TextView textView = (TextView) view2.findViewById(i.deep_link_segment_list_title);
        CharSequence charSequence = title;
        textView.setText(charSequence);
        textView.setVisibility(TextUtils.isEmpty(charSequence) ? 8 : 0);
        int ceil = (int) Math.ceil(((double) segments.size()) / ((double) numberOfRows));
        for (int i = 0; i < numberOfRows; i++) {
            View a = com.etsy.android.extensions.k.a(linearLayout, k.explore_segment_list_row, false, 2, null);
            LinearLayout linearLayout2 = (LinearLayout) a.findViewById(i.chips_container);
            this.chipContainers.add(linearLayout2);
            int i2 = i * ceil;
            for (Segment segment : segments.subList(i2, Math.min(i2 + ceil, segments.size()))) {
                p.a((Object) linearLayout2, "chipsContainer");
                ViewGroup viewGroup = linearLayout2;
                p.a((Object) segment, "it");
                addSegmentChip(viewGroup, segment);
            }
            linearLayout.addView(a);
        }
        this.itemView.requestLayout();
    }

    private final void addSegmentChip(ViewGroup viewGroup, Segment segment) {
        View a = com.etsy.android.extensions.k.a(viewGroup, k.view_explore_segment_chip, false, 2, null);
        if (a == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.TextView");
        }
        TextView textView = (TextView) a;
        textView.setText(segment.getTitle());
        Integer backgroundColor = segment.getBackgroundColor();
        if (backgroundColor != null) {
            GradientDrawable gradientDrawable = new GradientDrawable();
            Context context = textView.getContext();
            p.a((Object) context, ResponseConstants.CONTEXT);
            gradientDrawable.setCornerRadius((float) context.getResources().getDimensionPixelSize(f.sk_space_1));
            p.a((Object) backgroundColor, "it");
            gradientDrawable.setColor(backgroundColor.intValue());
            textView.setBackground(gradientDrawable);
        }
        View view = textView;
        j.a(view, new DeepLinkSegmentListViewHolder$addSegmentChip$$inlined$apply$lambda$1(this, segment));
        viewGroup.addView(view);
    }
}
