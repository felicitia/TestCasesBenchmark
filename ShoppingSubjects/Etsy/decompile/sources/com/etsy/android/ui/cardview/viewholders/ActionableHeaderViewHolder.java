package com.etsy.android.ui.cardview.viewholders;

import android.content.res.Resources;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ImageSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.etsy.android.R;
import com.etsy.android.lib.models.apiv3.vespa.BaseActionableItem;
import com.etsy.android.lib.models.cardviewelement.BasicSectionHeader;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.vespa.a.f;
import com.etsy.android.vespa.viewholders.BaseViewHolder;

public class ActionableHeaderViewHolder extends BaseViewHolder<BaseActionableItem> {
    private final View mMenuButton = findViewById(R.id.btn_menu);
    /* access modifiers changed from: private */
    public final f mServerDrivenActionClickHandler;
    private final TextView mTitle = ((TextView) findViewById(R.id.txt_module_title));

    public ActionableHeaderViewHolder(ViewGroup viewGroup, f fVar) {
        super(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_actionable_header, viewGroup, false));
        this.mServerDrivenActionClickHandler = fVar;
    }

    public void bind(final BaseActionableItem baseActionableItem) {
        BasicSectionHeader basicSectionHeader = (BasicSectionHeader) baseActionableItem.getData();
        if (!TextUtils.isEmpty(basicSectionHeader.getIcon())) {
            setIconAndText(basicSectionHeader.getTitle(), basicSectionHeader.getIcon());
        } else {
            this.mTitle.setText(basicSectionHeader.getTitle());
        }
        this.mMenuButton.setVisibility(baseActionableItem.getActions().size() > 0 ? 0 : 4);
        if (baseActionableItem.getActions().size() > 0 && this.mServerDrivenActionClickHandler != null) {
            this.mMenuButton.setOnClickListener(new TrackingOnClickListener() {
                public void onViewClick(View view) {
                    ActionableHeaderViewHolder.this.mServerDrivenActionClickHandler.a(ActionableHeaderViewHolder.this.getRootView(), baseActionableItem.getActions());
                }
            });
        }
    }

    private void setIconAndText(String str, String str2) {
        Resources resources = this.itemView.getResources();
        StringBuilder sb = new StringBuilder();
        sb.append("sk_ic_");
        sb.append(str2);
        int identifier = resources.getIdentifier(sb.toString(), "drawable", getRootView().getContext().getPackageName());
        if (identifier > 0) {
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(str);
            spannableStringBuilder.insert(0, "  ");
            VectorDrawableCompat create = VectorDrawableCompat.create(resources, identifier, this.itemView.getContext().getTheme());
            int dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.text_view_icon_drawable_medium);
            DrawableCompat.setTint(create, resources.getColor(R.color.sk_text_gray_darker));
            create.setBounds(0, 0, dimensionPixelSize, dimensionPixelSize);
            spannableStringBuilder.setSpan(new ImageSpan(create, 1), 0, 1, 33);
            this.mTitle.setText(spannableStringBuilder);
            return;
        }
        this.mTitle.setText(str);
    }
}
