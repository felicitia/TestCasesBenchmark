package com.contextlogic.wish.activity.rewards.redesign;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.contextlogic.wish.R;
import com.contextlogic.wish.api.model.WishRewardsDashboardInfo.BadgeType;
import com.contextlogic.wish.api.model.WishRewardsHelpInfo.Chart;
import com.contextlogic.wish.api.model.WishRewardsHelpInfo.Chart.Cell;
import com.contextlogic.wish.api.model.WishRewardsHelpInfo.Chart.Row;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.ui.image.AutoReleasableImageView;
import com.contextlogic.wish.ui.text.ThemedTextView;
import java.util.Iterator;

public class RewardsHelpChartView extends LinearLayout {
    public RewardsHelpChartView(Context context) {
        super(context);
    }

    public void setup(Chart chart) {
        setOrientation(1);
        addTitle(chart);
        addChartTitles(chart);
        addChartRows(chart);
    }

    private void addTitle(Chart chart) {
        Resources resources = WishApplication.getInstance().getResources();
        int dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.sixteen_padding);
        int dimensionPixelSize2 = resources.getDimensionPixelSize(R.dimen.twenty_four_padding);
        if (chart.getTitle() != null) {
            ThemedTextView themedTextView = new ThemedTextView(getContext());
            themedTextView.setPadding(dimensionPixelSize, dimensionPixelSize2, dimensionPixelSize, 0);
            themedTextView.setText(chart.getTitle());
            themedTextView.setTextSize(0, (float) resources.getDimensionPixelSize(R.dimen.text_size_subtitle));
            themedTextView.setTypeface(1);
            themedTextView.setTextColor(resources.getColor(R.color.text_primary));
            addView(themedTextView);
        }
    }

    private void addChartTitles(Chart chart) {
        if (chart.getRowTitles() != null) {
            Resources resources = WishApplication.getInstance().getResources();
            int dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.sixteen_padding);
            int dimensionPixelSize2 = resources.getDimensionPixelSize(R.dimen.eight_padding);
            int dimensionPixelSize3 = resources.getDimensionPixelSize(R.dimen.four_padding);
            int dimensionPixelSize4 = resources.getDimensionPixelSize(R.dimen.text_size_body);
            int color = resources.getColor(R.color.text_primary);
            LinearLayout linearLayout = new LinearLayout(getContext());
            linearLayout.setOrientation(0);
            linearLayout.setLayoutParams(new LayoutParams(-1, -2));
            linearLayout.setPadding(dimensionPixelSize, dimensionPixelSize2, dimensionPixelSize, dimensionPixelSize3);
            addTextColumn((String) chart.getRowTitles().get(0), chart.getRowTitles().size() == 2 ? 0.5f : 1.0f, dimensionPixelSize4, linearLayout, true, false, color);
            for (int i = 1; i < chart.getRowTitles().size(); i++) {
                addTextColumn((String) chart.getRowTitles().get(i), 1.0f, dimensionPixelSize4, linearLayout, true, false, color);
            }
            addView(linearLayout);
            View view = new View(getContext());
            view.setLayoutParams(new LayoutParams(-1, resources.getDimensionPixelSize(R.dimen.divider)));
            view.setBackgroundColor(resources.getColor(R.color.divider));
            addView(view);
        }
    }

    private LinearLayout getRowContainer() {
        Resources resources = WishApplication.getInstance().getResources();
        int dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.sixteen_padding);
        int dimensionPixelSize2 = resources.getDimensionPixelSize(R.dimen.eight_padding);
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setOrientation(0);
        linearLayout.setLayoutParams(new LayoutParams(-1, -2));
        linearLayout.setPadding(dimensionPixelSize, dimensionPixelSize2, dimensionPixelSize, dimensionPixelSize2);
        linearLayout.setGravity(16);
        return linearLayout;
    }

    private void addChartRows(Chart chart) {
        LinearLayout linearLayout;
        int i;
        Row row;
        if (chart.getRows() != null) {
            Resources resources = WishApplication.getInstance().getResources();
            int dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.eight_padding);
            int dimensionPixelSize2 = resources.getDimensionPixelSize(R.dimen.text_size_body);
            int color = resources.getColor(R.color.text_primary);
            ColorDrawable colorDrawable = new ColorDrawable();
            colorDrawable.setColor(resources.getColor(R.color.main_primary));
            colorDrawable.setAlpha(20);
            if (chart.getRows().size() > 0) {
                Iterator it = chart.getRows().iterator();
                boolean z = false;
                while (it.hasNext()) {
                    Row row2 = (Row) it.next();
                    LinearLayout rowContainer = getRowContainer();
                    if (z) {
                        rowContainer.setBackgroundDrawable(colorDrawable);
                    }
                    boolean z2 = !z;
                    float f = row2.getCells().size() == 2 ? 0.5f : 1.0f;
                    int i2 = 0;
                    while (i2 < row2.getCells().size()) {
                        Cell cell = (Cell) row2.getCells().get(i2);
                        if (cell.getBadgeType() != null) {
                            i = i2;
                            linearLayout = rowContainer;
                            row = row2;
                            addBadgeColumn(cell.getText(), BadgeType.getBadgeResource(cell.getBadgeType()), i2 == 0 ? f : 1.0f, dimensionPixelSize2, rowContainer, dimensionPixelSize, cell.isBold(), color);
                        } else {
                            i = i2;
                            linearLayout = rowContainer;
                            row = row2;
                            addTextColumn(cell.getText(), i == 0 ? f : 1.0f, dimensionPixelSize2, linearLayout, false, cell.isBold(), color);
                        }
                        i2 = i + 1;
                        row2 = row;
                        rowContainer = linearLayout;
                    }
                    addView(rowContainer);
                    z = z2;
                }
            }
            View view = new View(getContext());
            view.setLayoutParams(new LayoutParams(-1, resources.getDimensionPixelSize(R.dimen.divider)));
            view.setBackgroundColor(resources.getColor(R.color.divider));
            addView(view);
        }
    }

    private void addBadgeColumn(String str, int i, float f, int i2, LinearLayout linearLayout, int i3, boolean z, int i4) {
        LinearLayout linearLayout2 = new LinearLayout(getContext());
        linearLayout2.setOrientation(0);
        linearLayout2.setLayoutParams(new LayoutParams(0, -2, f));
        linearLayout2.setGravity(16);
        AutoReleasableImageView autoReleasableImageView = new AutoReleasableImageView(getContext());
        autoReleasableImageView.setLayoutParams(new LayoutParams(WishApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.rewards_info_tab_badge_size), WishApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.rewards_info_tab_badge_size)));
        autoReleasableImageView.setImageResource(i);
        ThemedTextView themedTextView = new ThemedTextView(getContext());
        themedTextView.setLayoutParams(new LayoutParams(-2, -2));
        themedTextView.setPadding(i3, 0, i3, 0);
        themedTextView.setTextSize(0, (float) i2);
        themedTextView.setText(str);
        if (z) {
            themedTextView.setTypeface(1);
        }
        themedTextView.setTextColor(i4);
        linearLayout2.addView(autoReleasableImageView);
        linearLayout2.addView(themedTextView);
        linearLayout.addView(linearLayout2);
    }

    private void addTextColumn(String str, float f, int i, LinearLayout linearLayout, boolean z, boolean z2, int i2) {
        ThemedTextView themedTextView = new ThemedTextView(getContext());
        themedTextView.setLayoutParams(new LayoutParams(0, -2, f));
        themedTextView.setTextSize(0, (float) i);
        themedTextView.setAllCaps(z);
        themedTextView.setText(str);
        themedTextView.setTextColor(i2);
        if (z2) {
            themedTextView.setTypeface(1);
        }
        linearLayout.addView(themedTextView);
    }
}
