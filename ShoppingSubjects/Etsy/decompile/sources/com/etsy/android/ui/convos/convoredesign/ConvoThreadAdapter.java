package com.etsy.android.ui.convos.convoredesign;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.format.DateUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.etsy.android.R;
import com.etsy.android.e.a;
import com.etsy.android.extensions.k;
import com.etsy.android.lib.core.img.c;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.ui.convos.convoredesign.af.b;
import com.etsy.android.ui.convos.convoredesign.af.d;
import com.etsy.android.ui.convos.convoredesign.af.e;
import com.etsy.android.uikit.adapter.BaseRecyclerViewAdapter;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.p;

/* compiled from: ConvoThreadAdapter.kt */
public final class ConvoThreadAdapter extends BaseRecyclerViewAdapter<o> {
    private final b imageClickedListener;
    private final d linkCardBoundListener;
    private final e linkCardClickListener;

    /* compiled from: ConvoThreadAdapter.kt */
    public final class DateConvoThreadViewHolder extends ViewHolder {
        final /* synthetic */ ConvoThreadAdapter this$0;

        public DateConvoThreadViewHolder(ConvoThreadAdapter convoThreadAdapter, View view) {
            p.b(view, "view");
            this.this$0 = convoThreadAdapter;
            super(view);
        }

        public final void bind(o.b bVar) {
            p.b(bVar, "item");
            View view = this.itemView;
            p.a((Object) view, "itemView");
            TextView textView = (TextView) view.findViewById(a.date);
            p.a((Object) textView, "itemView.date");
            textView.setText(DateUtils.getRelativeTimeSpanString(bVar.a()));
        }
    }

    public ConvoThreadAdapter(FragmentActivity fragmentActivity, c cVar, b bVar, d dVar, e eVar) {
        p.b(fragmentActivity, "activity");
        p.b(cVar, "imageBatch");
        p.b(bVar, "imageClickedListener");
        p.b(dVar, "linkCardBoundListener");
        p.b(eVar, "linkCardClickListener");
        super(fragmentActivity, cVar);
        this.imageClickedListener = bVar;
        this.linkCardBoundListener = dVar;
        this.linkCardClickListener = eVar;
    }

    /* access modifiers changed from: protected */
    public int getListItemViewType(int i) {
        o oVar = (o) getItem(i);
        if (oVar instanceof o.a) {
            return R.layout.list_item_current_user_convo_message;
        }
        if (oVar instanceof o.c) {
            return R.layout.list_item_other_user_convo_message;
        }
        if (oVar instanceof o.b) {
            return R.layout.list_item_convo_date;
        }
        throw new NoWhenBranchMatchedException();
    }

    /* access modifiers changed from: protected */
    public ViewHolder onCreateListItemViewHolder(ViewGroup viewGroup, int i) {
        p.b(viewGroup, ResponseConstants.PARENT);
        View a = k.a(viewGroup, i, false, 2, null);
        if (i == R.layout.list_item_convo_date) {
            return new DateConvoThreadViewHolder(this, a);
        }
        if (i == R.layout.list_item_current_user_convo_message) {
            c cVar = this.mImageBatch;
            p.a((Object) cVar, "mImageBatch");
            return new CurrentUserConvoThreadViewHolder(a, cVar, this.imageClickedListener, this.linkCardClickListener);
        } else if (i != R.layout.list_item_other_user_convo_message) {
            throw new IllegalArgumentException("Unrecognized view type!");
        } else {
            c cVar2 = this.mImageBatch;
            p.a((Object) cVar2, "mImageBatch");
            return new OtherUserConvoThreadViewHolder(a, cVar2, this.imageClickedListener, this.linkCardClickListener);
        }
    }

    /* access modifiers changed from: protected */
    public void onBindListItemViewHolder(ViewHolder viewHolder, int i) {
        p.b(viewHolder, "viewHolder");
        o oVar = (o) getItem(i);
        if (oVar instanceof o.a) {
            o.a aVar = (o.a) oVar;
            this.linkCardBoundListener.a(i, aVar.c());
            ((CurrentUserConvoThreadViewHolder) viewHolder).bind(aVar);
        } else if (oVar instanceof o.c) {
            o.c cVar = (o.c) oVar;
            this.linkCardBoundListener.a(i, cVar.d());
            ((OtherUserConvoThreadViewHolder) viewHolder).bind(cVar);
        } else if (oVar instanceof o.b) {
            ((DateConvoThreadViewHolder) viewHolder).bind((o.b) oVar);
        } else {
            throw new NoWhenBranchMatchedException();
        }
    }
}
