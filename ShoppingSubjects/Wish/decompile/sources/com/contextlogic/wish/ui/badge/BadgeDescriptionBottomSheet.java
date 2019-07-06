package com.contextlogic.wish.ui.badge;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.BottomSheetDialog;
import android.view.View;
import android.widget.ListView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.productdetails.relateditemsrow.BadgeDescriptionListViewAdapter;
import com.contextlogic.wish.api.model.WishProductBadge;
import java.util.List;

public class BadgeDescriptionBottomSheet extends BottomSheetDialog {
    List<WishProductBadge> mBadgeList;

    public BadgeDescriptionBottomSheet(Context context) {
        super(context);
    }

    public static BadgeDescriptionBottomSheet create(Context context, List<WishProductBadge> list) {
        BadgeDescriptionBottomSheet badgeDescriptionBottomSheet = new BadgeDescriptionBottomSheet(context);
        badgeDescriptionBottomSheet.mBadgeList = list;
        badgeDescriptionBottomSheet.init();
        return badgeDescriptionBottomSheet;
    }

    private void init() {
        ListView listView = new ListView(getContext());
        listView.setBackground(new ColorDrawable(getContext().getResources().getColor(R.color.white)));
        listView.setSelector(R.color.transparent);
        listView.setAdapter(new BadgeDescriptionListViewAdapter(getContext(), this.mBadgeList));
        setContentView((View) listView);
    }
}
