package com.etsy.android.ui.cardview.viewholders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.etsy.android.R;
import com.etsy.android.uikit.util.TrackingOnClickListener;

public class LocalBrowseBaseHeaderViewHolder extends ViewHolder {
    /* access modifiers changed from: private */
    public View mClickTarget = this.itemView;
    /* access modifiers changed from: private */
    public a mListener;
    private View mTitleContent = this.itemView.findViewById(R.id.title_content);

    public interface a {
        void a();
    }

    public LocalBrowseBaseHeaderViewHolder(ViewGroup viewGroup, @NonNull a aVar) {
        super(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.local_browse_list_header, viewGroup, false));
        this.mListener = aVar;
    }

    public void bind(final boolean z) {
        if (z) {
            this.mTitleContent.setVisibility(0);
        } else {
            this.mTitleContent.setVisibility(8);
        }
        this.mClickTarget.setOnClickListener(new TrackingOnClickListener() {
            public void onViewClick(View view) {
                LocalBrowseBaseHeaderViewHolder.this.mListener.a();
                if (!z) {
                    LocalBrowseBaseHeaderViewHolder.this.mClickTarget.setPressed(false);
                    LocalBrowseBaseHeaderViewHolder.this.mClickTarget.jumpDrawablesToCurrentState();
                }
            }
        });
    }
}
