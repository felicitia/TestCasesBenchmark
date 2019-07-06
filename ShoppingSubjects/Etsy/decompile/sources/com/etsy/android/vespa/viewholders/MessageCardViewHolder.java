package com.etsy.android.vespa.viewholders;

import android.view.View;
import android.view.ViewGroup;
import com.etsy.android.lib.models.homescreen.MessageCard;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.uikit.view.EmptyMessageView;
import com.etsy.android.vespa.b;

public class MessageCardViewHolder extends BaseViewHolder<MessageCard> {
    private final EmptyMessageView mEmptyMessageView = ((EmptyMessageView) this.itemView);
    /* access modifiers changed from: private */
    public b mListener;

    public MessageCardViewHolder(ViewGroup viewGroup, b bVar) {
        super(new EmptyMessageView(viewGroup.getContext()));
        this.mListener = bVar;
    }

    public void bind(final MessageCard messageCard) {
        this.mEmptyMessageView.bind(messageCard);
        if (!messageCard.getDeepLinkUrl().isEmpty()) {
            this.mEmptyMessageView.setButtonClickListener(new TrackingOnClickListener() {
                public void onViewClick(View view) {
                    if (MessageCardViewHolder.this.mListener != null) {
                        MessageCardViewHolder.this.mListener.a(messageCard);
                    }
                }
            });
        }
    }

    public void recycle() {
        this.mEmptyMessageView.recycle();
    }
}
