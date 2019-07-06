package com.etsy.android.vespa.viewholders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.etsy.android.extensions.j;
import com.etsy.android.lib.a;
import com.etsy.android.lib.a.i;
import com.etsy.android.lib.a.k;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.apiv3.ExploreSegment;
import com.etsy.android.lib.models.apiv3.ExploreSegmentList;
import com.etsy.android.lib.models.apiv3.vespa.CardActionableItem;
import com.etsy.android.lib.models.apiv3.vespa.ServerDrivenAction;
import com.etsy.android.vespa.a.f;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.TypeCastException;
import kotlin.jvm.internal.p;

/* compiled from: ExploreSegmentListViewHolder.kt */
public final class ExploreSegmentListViewHolder extends BaseViewHolder<CardActionableItem> {
    private final ArrayList<ViewGroup> chipContainers = new ArrayList<>();
    /* access modifiers changed from: private */
    public final f clickHandler;

    public ExploreSegmentListViewHolder(ViewGroup viewGroup, f fVar) {
        p.b(viewGroup, ResponseConstants.PARENT);
        p.b(fVar, "clickHandler");
        super(LayoutInflater.from(viewGroup.getContext()).inflate(k.list_item_explore_segment_list, viewGroup, false));
        this.clickHandler = fVar;
    }

    public void bind(CardActionableItem cardActionableItem) {
        p.b(cardActionableItem, "item");
        super.bind(cardActionableItem);
        View view = this.itemView;
        if (view == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.view.ViewGroup");
        }
        ViewGroup viewGroup = (ViewGroup) view;
        viewGroup.removeAllViews();
        this.chipContainers.clear();
        ExploreSegmentList exploreSegmentList = (ExploreSegmentList) cardActionableItem.getData();
        if (exploreSegmentList != null) {
            int numberOfRows = exploreSegmentList.getNumberOfRows();
            List exploreSegments = exploreSegmentList.getExploreSegments();
            int ceil = (int) Math.ceil(((double) exploreSegments.size()) / ((double) numberOfRows));
            for (int i = 0; i < numberOfRows; i++) {
                View a = com.etsy.android.extensions.k.a(viewGroup, k.explore_segment_list_row, false, 2, null);
                LinearLayout linearLayout = (LinearLayout) a.findViewById(i.chips_container);
                this.chipContainers.add(linearLayout);
                int i2 = i * ceil;
                int min = Math.min(i2 + ceil, exploreSegments.size());
                ServerDrivenAction action = cardActionableItem.getAction(ServerDrivenAction.TYPE_EXPLORE_SEGMENT_SELECT);
                for (ExploreSegment exploreSegment : exploreSegments.subList(i2, min)) {
                    p.a((Object) linearLayout, "chipsContainer");
                    ViewGroup viewGroup2 = linearLayout;
                    p.a((Object) exploreSegments, "exploreSegmentList");
                    Collection collection = exploreSegments;
                    p.a((Object) exploreSegment, "it");
                    addSegmentChip(viewGroup2, collection, exploreSegment, action);
                }
                viewGroup.addView(a);
            }
            this.itemView.addOnLayoutChangeListener(new ExploreSegmentListViewHolder$bind$2(this));
            this.itemView.requestLayout();
        }
    }

    private final void addSegmentChip(ViewGroup viewGroup, Collection<? extends ExploreSegment> collection, ExploreSegment exploreSegment, ServerDrivenAction serverDrivenAction) {
        View a = com.etsy.android.extensions.k.a(viewGroup, k.view_explore_segment_chip, false, 2, null);
        if (a == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.TextView");
        }
        TextView textView = (TextView) a;
        textView.setText(exploreSegment.getTitle());
        textView.setSelected(exploreSegment.isSelected());
        textView.setTag(exploreSegment.getKey());
        View view = textView;
        ExploreSegmentListViewHolder$addSegmentChip$$inlined$apply$lambda$1 exploreSegmentListViewHolder$addSegmentChip$$inlined$apply$lambda$1 = new ExploreSegmentListViewHolder$addSegmentChip$$inlined$apply$lambda$1(textView, this, exploreSegment, serverDrivenAction, collection);
        j.a(view, exploreSegmentListViewHolder$addSegmentChip$$inlined$apply$lambda$1);
        viewGroup.addView(view);
    }

    public final void setChipSelected(String str) {
        p.b(str, "segmentKey");
        for (ViewGroup viewGroup : this.chipContainers) {
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = viewGroup.getChildAt(i);
                p.a((Object) childAt, "chip");
                childAt.setSelected(p.a((Object) str, childAt.getTag()));
            }
        }
    }

    /* access modifiers changed from: private */
    public final void scrollToSelectedSegment() {
        View view = this.itemView;
        p.a((Object) view, "itemView");
        int dimensionPixelOffset = view.getResources().getDimensionPixelOffset(a.f.sk_space_2);
        for (ViewGroup viewGroup : this.chipContainers) {
            ViewParent parent = viewGroup.getParent();
            if (parent != null) {
                HorizontalScrollView horizontalScrollView = (HorizontalScrollView) parent;
                int childCount = viewGroup.getChildCount();
                int i = 0;
                while (true) {
                    if (i >= childCount) {
                        break;
                    }
                    View childAt = viewGroup.getChildAt(i);
                    p.a((Object) childAt, "chip");
                    int right = childAt.getRight() + dimensionPixelOffset;
                    if (childAt.isSelected() && right > horizontalScrollView.getWidth()) {
                        horizontalScrollView.scrollTo(right - horizontalScrollView.getWidth(), 0);
                        break;
                    }
                    i++;
                }
            } else {
                throw new TypeCastException("null cannot be cast to non-null type android.widget.HorizontalScrollView");
            }
        }
    }
}
