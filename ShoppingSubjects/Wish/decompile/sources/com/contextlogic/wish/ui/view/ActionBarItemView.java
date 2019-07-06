package com.contextlogic.wish.ui.view;

import android.content.Context;
import android.graphics.PorterDuff.Mode;
import android.view.LayoutInflater;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.actionbar.ActionBarItem;
import com.contextlogic.wish.ui.image.AutoReleasableImageView;
import com.contextlogic.wish.ui.text.ThemedTextView;

public class ActionBarItemView extends FrameLayout {
    private AutoReleasableImageView mIcon;

    public ActionBarItemView(Context context, ActionBarItem actionBarItem) {
        super(context);
        setLayoutParams(new LayoutParams(-2, -2));
        setPadding(context.getResources().getDimensionPixelSize(R.dimen.action_bar_menu_item_margin), 0, context.getResources().getDimensionPixelSize(R.dimen.action_bar_menu_item_margin), 0);
        ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.action_bar_menu_item, this);
        setTag(Integer.valueOf(actionBarItem.getActionId()));
        initIcon(actionBarItem);
        initBadge(actionBarItem);
    }

    public int getActionId() {
        return ((Integer) getTag()).intValue();
    }

    private void initIcon(ActionBarItem actionBarItem) {
        this.mIcon = (AutoReleasableImageView) findViewById(R.id.action_bar_item_icon);
        if (actionBarItem.getIconDrawable() != null) {
            this.mIcon.setImageDrawable(actionBarItem.getIconDrawable());
        }
    }

    private void initBadge(ActionBarItem actionBarItem) {
        ThemedTextView themedTextView = (ThemedTextView) findViewById(R.id.action_bar_item_badge);
        if (actionBarItem.getBadgeCount() > 0) {
            themedTextView.setText(String.valueOf(actionBarItem.getBadgeCount()));
            themedTextView.setVisibility(0);
            return;
        }
        themedTextView.setVisibility(8);
    }

    public void setIconColor(int i) {
        if (this.mIcon != null && this.mIcon.getDrawable() != null) {
            this.mIcon.getDrawable().setColorFilter(i, Mode.SRC_ATOP);
        }
    }
}
