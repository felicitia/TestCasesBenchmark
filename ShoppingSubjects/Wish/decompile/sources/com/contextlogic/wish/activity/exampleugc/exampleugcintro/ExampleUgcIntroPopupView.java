package com.contextlogic.wish.activity.exampleugc.exampleugcintro;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import com.contextlogic.wish.R;
import com.contextlogic.wish.ui.text.ThemedTextView;

public class ExampleUgcIntroPopupView extends LinearLayout {
    public ExampleUgcIntroPopupView(Context context, String str, String[] strArr) {
        super(context);
        ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.layout.example_ugc_intro_popup_view, this);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.ugc_intro_popup_view_description);
        ((ThemedTextView) findViewById(R.id.ugc_intro_popup_view_title)).setText(str);
        for (String str2 : strArr) {
            ThemedTextView themedTextView = new ThemedTextView(context);
            themedTextView.setText(str2);
            themedTextView.setTextSize(0, (float) context.getResources().getDimensionPixelSize(R.dimen.text_size_body));
            themedTextView.setTextColor(context.getResources().getColor(R.color.text_primary));
            linearLayout.addView(themedTextView);
        }
    }
}
