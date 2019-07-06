package com.etsy.android.uikit.share;

import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.etsy.android.lib.a.i;
import com.etsy.android.uikit.adapter.BaseRecyclerViewAdapter;
import com.etsy.android.uikit.util.TrackingOnClickListener;

public class ShareIntentListAdapter extends BaseRecyclerViewAdapter<ResolveInfo> {
    private int mLayoutId;
    private a mOnClickListener;
    private PackageManager mPkgManager;

    public static class IntentItemHolder extends ViewHolder {
        public ImageView intentIcon;
        public TextView intentLabel;
        /* access modifiers changed from: private */
        public a mListener;

        public interface a {
            void onIntentItemClick(int i);
        }

        public IntentItemHolder(View view) {
            super(view);
            this.intentIcon = (ImageView) view.findViewById(i.item_image);
            this.intentLabel = (TextView) view.findViewById(i.item_label);
            view.setOnClickListener(new TrackingOnClickListener() {
                public void onViewClick(View view) {
                    if (IntentItemHolder.this.mListener != null) {
                        IntentItemHolder.this.mListener.onIntentItemClick(IntentItemHolder.this.getAdapterPosition());
                    }
                }
            });
        }

        public void setOnClickListener(a aVar) {
            this.mListener = aVar;
        }
    }

    /* access modifiers changed from: protected */
    public int getListItemViewType(int i) {
        return 0;
    }

    public ShareIntentListAdapter(FragmentActivity fragmentActivity, int i) {
        super(fragmentActivity, null);
        this.mLayoutId = i;
        this.mPkgManager = fragmentActivity.getPackageManager();
    }

    public ViewHolder onCreateListItemViewHolder(ViewGroup viewGroup, int i) {
        return new IntentItemHolder(this.mInflater.inflate(this.mLayoutId, viewGroup, false));
    }

    /* access modifiers changed from: protected */
    public void onBindListItemViewHolder(ViewHolder viewHolder, int i) {
        if (viewHolder != null && (viewHolder instanceof IntentItemHolder) && getItem(i) != null) {
            bindIntentItemViewHolder((IntentItemHolder) viewHolder, i);
        }
    }

    private void bindIntentItemViewHolder(IntentItemHolder intentItemHolder, int i) {
        ResolveInfo resolveInfo = (ResolveInfo) getItem(i);
        intentItemHolder.intentIcon.setImageDrawable(resolveInfo.loadIcon(this.mPkgManager));
        intentItemHolder.intentLabel.setText(resolveInfo.loadLabel(this.mPkgManager));
        intentItemHolder.setOnClickListener(this.mOnClickListener);
    }

    public void setOnClickListener(a aVar) {
        this.mOnClickListener = aVar;
    }
}
