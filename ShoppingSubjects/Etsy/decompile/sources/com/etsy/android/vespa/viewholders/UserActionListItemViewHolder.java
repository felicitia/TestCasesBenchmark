package com.etsy.android.vespa.viewholders;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.etsy.android.lib.a.i;
import com.etsy.android.lib.a.k;
import com.etsy.android.lib.models.apiv3.vespa.UserAction;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.vespa.b;

public class UserActionListItemViewHolder extends BaseViewHolder<UserAction> {
    /* access modifiers changed from: private */
    public final b mClickHandler;
    private final ImageView mIcon = ((ImageView) findViewById(i.icon));
    private final TextView mTextView = ((TextView) findViewById(i.text));

    public UserActionListItemViewHolder(ViewGroup viewGroup, b bVar) {
        super(LayoutInflater.from(viewGroup.getContext()).inflate(k.list_item_bottom_sheet_user_action, viewGroup, false));
        this.mClickHandler = bVar;
    }

    public void bind(final UserAction userAction) {
        this.mTextView.setText(userAction.getDisplayName());
        this.mIcon.setVisibility(4);
        if (!TextUtils.isEmpty(userAction.getIconName())) {
            int identifier = this.itemView.getResources().getIdentifier(userAction.getIconName(), "drawable", this.itemView.getContext().getPackageName());
            if (identifier > 0) {
                this.mIcon.setImageResource(identifier);
                this.mIcon.setVisibility(0);
            }
        }
        if (this.mClickHandler != null) {
            this.itemView.setOnClickListener(new TrackingOnClickListener() {
                public void onViewClick(View view) {
                    UserActionListItemViewHolder.this.mClickHandler.a(userAction);
                }
            });
        }
    }
}
