package com.contextlogic.wish.activity.referralprogram;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.api.model.WishReferralProgramInfoSpec.ReferralProgramHistoryItem;
import com.contextlogic.wish.api.model.WishTextViewSpec;
import java.util.List;

public class ReferralProgramHistoryAdapter extends Adapter<ViewHolder> {
    private Context mContext;
    private List<ReferralProgramHistoryItem> mItems;
    private OnClickListener mOnInviteClickedListener;

    static class ViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder {
        TextView mReferrerText;
        TextView mStatusText;

        public ViewHolder(View view) {
            super(view);
            this.mReferrerText = (TextView) view.findViewById(R.id.referral_program_referral_history_referrer);
            this.mStatusText = (TextView) view.findViewById(R.id.referral_program_referral_history_status);
        }
    }

    public long getItemId(int i) {
        return (long) i;
    }

    ReferralProgramHistoryAdapter(Context context) {
        this.mContext = context;
    }

    public void setItems(List<ReferralProgramHistoryItem> list) {
        this.mItems = list;
    }

    public void setOnInviteClickListener(OnClickListener onClickListener) {
        this.mOnInviteClickedListener = onClickListener;
    }

    public ReferralProgramHistoryItem getItem(int i) {
        if (i < 0 || i >= getItemCount()) {
            return null;
        }
        return (ReferralProgramHistoryItem) this.mItems.get(i);
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(this.mContext).inflate(R.layout.referral_program_history_row_item, viewGroup, false));
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        ReferralProgramHistoryItem item = getItem(i);
        if (item != null) {
            WishTextViewSpec.applyTextViewSpec(viewHolder.mReferrerText, item.getReferralNameTextSpec());
            WishTextViewSpec.applyTextViewSpec(viewHolder.mStatusText, item.getStatusTextSpec());
            if (item.isComplete()) {
                viewHolder.mStatusText.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(this.mContext, R.drawable.moneybag_11x15), null, null, null);
            } else {
                viewHolder.mStatusText.setCompoundDrawables(null, null, null, null);
            }
            if (item.isInviteLink()) {
                viewHolder.itemView.setOnClickListener(this.mOnInviteClickedListener);
            } else {
                viewHolder.itemView.setOnClickListener(null);
            }
        }
    }

    public int getItemCount() {
        if (this.mItems == null) {
            return 0;
        }
        return this.mItems.size();
    }
}
