package com.wish.android.shaky;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

class FeedbackTypeAdapter extends Adapter<RowViewHolder> {
    private final LayoutInflater inflater;
    private final FeedbackItem[] itemsList;

    static class RowViewHolder extends ViewHolder {
        public final TextView descriptionView;
        public final ImageView imageView;
        public final TextView titleView;

        RowViewHolder(View view) {
            super(view);
            this.imageView = (ImageView) view.findViewById(R.id.shaky_row_icon);
            this.titleView = (TextView) view.findViewById(R.id.shaky_row_title);
            this.descriptionView = (TextView) view.findViewById(R.id.shaky_row_description);
        }
    }

    FeedbackTypeAdapter(Context context, FeedbackItem[] feedbackItemArr) {
        this.inflater = LayoutInflater.from(context);
        this.itemsList = feedbackItemArr;
    }

    public int getItemCount() {
        if (this.itemsList == null) {
            return 0;
        }
        return this.itemsList.length;
    }

    public RowViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new RowViewHolder(this.inflater.inflate(R.layout.shaky_single_row, viewGroup, false));
    }

    public void onBindViewHolder(RowViewHolder rowViewHolder, int i) {
        final FeedbackItem feedbackItem = this.itemsList[i];
        rowViewHolder.titleView.setText(String.valueOf(feedbackItem.title));
        rowViewHolder.descriptionView.setText(String.valueOf(feedbackItem.description));
        rowViewHolder.imageView.setImageResource(feedbackItem.icon);
        rowViewHolder.itemView.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent("ActionFeedbackTypeSelected");
                intent.putExtra("ExtraFeedbackType", feedbackItem.feedbackType);
                LocalBroadcastManager.getInstance(view.getContext()).sendBroadcast(intent);
            }
        });
    }
}
