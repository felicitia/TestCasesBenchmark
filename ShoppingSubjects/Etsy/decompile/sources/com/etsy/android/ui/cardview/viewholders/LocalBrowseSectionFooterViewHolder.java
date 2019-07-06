package com.etsy.android.ui.cardview.viewholders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.etsy.android.R;
import com.etsy.android.lib.logger.i;
import com.etsy.android.lib.models.apiv3.LocalBrowseModule;
import com.etsy.android.uikit.util.TrackingOnClickListener;

public class LocalBrowseSectionFooterViewHolder extends ViewHolder {
    private TextView mLandingPageLink = ((TextView) this.itemView.findViewById(R.id.btn_landing_page_link));
    /* access modifiers changed from: private */
    public a mListener;

    public interface a {
        void a(LocalBrowseModule localBrowseModule);
    }

    public LocalBrowseSectionFooterViewHolder(ViewGroup viewGroup, @NonNull a aVar) {
        super(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.section_footer_local_browse, viewGroup, false));
        this.mListener = aVar;
    }

    public void bind(final LocalBrowseModule localBrowseModule) {
        if (localBrowseModule != null) {
            if (localBrowseModule.getLandingPage() != null) {
                this.mLandingPageLink.setText(localBrowseModule.getLandingPage().getLinkTitle());
            }
            this.mLandingPageLink.setOnClickListener(new TrackingOnClickListener(new i[]{localBrowseModule}) {
                public void onViewClick(View view) {
                    LocalBrowseSectionFooterViewHolder.this.mListener.a(localBrowseModule);
                }
            });
        }
    }
}
