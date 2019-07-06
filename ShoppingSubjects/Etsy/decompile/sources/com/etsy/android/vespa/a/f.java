package com.etsy.android.vespa.a;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewParent;
import com.etsy.android.lib.models.apiv3.vespa.BaseActionableItem;
import com.etsy.android.lib.models.apiv3.vespa.ServerDrivenAction;
import com.etsy.android.vespa.PositionList;
import com.etsy.android.vespa.VespaBottomSheetDialog;
import com.etsy.android.vespa.b;
import java.util.List;

/* compiled from: ServerDrivenActionClickHandler */
public class f extends b<BaseActionableItem> {
    protected final com.etsy.android.vespa.f b;

    public void a(BaseActionableItem baseActionableItem) {
    }

    public f(@NonNull FragmentActivity fragmentActivity, @NonNull com.etsy.android.lib.logger.b bVar, @NonNull com.etsy.android.vespa.f fVar) {
        super(fragmentActivity, bVar);
        this.b = fVar;
    }

    public void c(@NonNull View view, @NonNull ServerDrivenAction serverDrivenAction) {
        this.b.performAction(a(view), serverDrivenAction);
    }

    public void a(@NonNull View view, ServerDrivenAction serverDrivenAction, int i) {
        this.b.performActionWithToast(a(view), serverDrivenAction, i);
    }

    public static PositionList a(View view) {
        PositionList positionList = new PositionList();
        if (view.getParent() instanceof RecyclerView) {
            RecyclerView recyclerView = (RecyclerView) view.getParent();
            positionList.setChildPosition(recyclerView.getChildAdapterPosition(view));
            View rootView = view.getRootView();
            ViewParent viewParent = recyclerView;
            while (!(viewParent.getParent() instanceof RecyclerView)) {
                viewParent = viewParent.getParent();
                if (viewParent != 0) {
                    if (viewParent == rootView) {
                    }
                }
                return positionList;
            }
            if (viewParent.getParent() instanceof RecyclerView) {
                positionList.setParentPosition(((RecyclerView) viewParent.getParent()).getChildAdapterPosition((View) viewParent));
            }
        }
        return positionList;
    }

    public void a(View view, List<ServerDrivenAction> list) {
        VespaBottomSheetDialog vespaBottomSheetDialog = new VespaBottomSheetDialog(d(), c());
        vespaBottomSheetDialog.addItems(list);
        int viewType = ((ServerDrivenAction) list.get(0)).getViewType();
        final VespaBottomSheetDialog vespaBottomSheetDialog2 = vespaBottomSheetDialog;
        final View view2 = view;
        AnonymousClass1 r0 = new b(d(), c()) {
            public void a(Object obj) {
                vespaBottomSheetDialog2.dismiss();
                f.this.c(view2, (ServerDrivenAction) obj);
            }
        };
        vespaBottomSheetDialog.registerItemClickHandler(viewType, r0);
        vespaBottomSheetDialog.show();
    }
}
