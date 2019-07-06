package com.etsy.android.ui.cardview.viewholders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.etsy.android.R;
import com.etsy.android.e.a;
import com.etsy.android.lib.models.EtsyAssociativeArray;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.apiv3.vespa.CardActionableItem;
import com.etsy.android.vespa.viewholders.BaseViewHolder;
import kotlin.jvm.internal.p;

/* compiled from: QuestionViewHolder.kt */
public final class QuestionViewHolder extends BaseViewHolder<CardActionableItem> {
    public QuestionViewHolder(ViewGroup viewGroup) {
        p.b(viewGroup, ResponseConstants.PARENT);
        super(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_question, viewGroup, false));
    }

    public void bind(CardActionableItem cardActionableItem) {
        EtsyAssociativeArray etsyAssociativeArray = (EtsyAssociativeArray) (cardActionableItem != null ? cardActionableItem.getData() : null);
        if (etsyAssociativeArray != null) {
            View view = this.itemView;
            p.a((Object) view, "itemView");
            TextView textView = (TextView) view.findViewById(a.txt_title);
            p.a((Object) textView, "itemView.txt_title");
            textView.setText(etsyAssociativeArray.getString("message"));
            String string = etsyAssociativeArray.getString("positive");
            View view2 = this.itemView;
            p.a((Object) view2, "itemView");
            Button button = (Button) view2.findViewById(a.btn_positive);
            p.a((Object) button, "itemView.btn_positive");
            CharSequence charSequence = string;
            button.setText(charSequence);
            View view3 = this.itemView;
            p.a((Object) view3, "itemView");
            Button button2 = (Button) view3.findViewById(a.btn_positive);
            p.a((Object) button2, "itemView.btn_positive");
            p.a((Object) string, "positive");
            int length = charSequence.length();
            boolean z = true;
            int i = 0;
            button2.setVisibility(length == 0 ? 8 : 0);
            String string2 = etsyAssociativeArray.getString("negative");
            View view4 = this.itemView;
            p.a((Object) view4, "itemView");
            Button button3 = (Button) view4.findViewById(a.btn_negative);
            p.a((Object) button3, "itemView.btn_negative");
            CharSequence charSequence2 = string2;
            button3.setText(charSequence2);
            View view5 = this.itemView;
            p.a((Object) view5, "itemView");
            Button button4 = (Button) view5.findViewById(a.btn_negative);
            p.a((Object) button4, "itemView.btn_negative");
            p.a((Object) string2, "negative");
            if (charSequence2.length() != 0) {
                z = false;
            }
            if (z) {
                i = 8;
            }
            button4.setVisibility(i);
        }
    }
}
