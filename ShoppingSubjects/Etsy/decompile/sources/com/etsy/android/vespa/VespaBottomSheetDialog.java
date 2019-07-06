package com.etsy.android.vespa;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import com.etsy.android.lib.a.i;
import com.etsy.android.lib.logger.b;
import com.etsy.android.lib.models.apiv3.vespa.ServerDrivenAction;
import com.etsy.android.vespa.viewholders.BaseViewHolder;
import com.etsy.android.vespa.viewholders.SingleLineTextViewHolder;
import com.etsy.android.vespa.viewholders.UserActionListItemViewHolder;
import java.util.List;

public class VespaBottomSheetDialog extends BottomSheetDialog {
    private final BaseViewHolderFactoryRecyclerViewAdapter mAdapter;

    class a extends c {
        public a(Context context, b bVar) {
            super(context, bVar);
        }

        @Nullable
        public BaseViewHolder a(ViewGroup viewGroup, int i) {
            if (i == i.view_type_single_line_text) {
                return new SingleLineTextViewHolder(viewGroup, b(i));
            }
            if (i == i.view_type_bottom_sheet_list_item) {
                return new UserActionListItemViewHolder(viewGroup, b(i));
            }
            return null;
        }
    }

    public VespaBottomSheetDialog(Context context, b bVar) {
        super(context);
        RecyclerView recyclerView = new RecyclerView(context);
        setContentView((View) recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        this.mAdapter = new BaseViewHolderFactoryRecyclerViewAdapter((FragmentActivity) context, bVar);
        this.mAdapter.getViewHolderFactory().a((c) new a(context, bVar));
        recyclerView.setAdapter(this.mAdapter);
    }

    public void addItems(List<? extends k> list) {
        this.mAdapter.addItems(list);
    }

    public void addItem(ServerDrivenAction serverDrivenAction) {
        this.mAdapter.addItem(serverDrivenAction);
    }

    public void registerItemClickHandler(@IdRes int i, b bVar) {
        this.mAdapter.registerClickHandler(i, bVar);
    }
}
