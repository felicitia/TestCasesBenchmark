package com.contextlogic.wish.activity.exampleugc;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import com.contextlogic.wish.R;
import com.contextlogic.wish.ui.text.ThemedTextView;

public class ExampleUgcItemsHeader extends LinearLayout {
    public ExampleUgcItemsHeader(Context context) {
        super(context);
        init();
    }

    private void init() {
        ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.example_ratings_header, this);
    }

    public void setup(String str, String str2) {
        ThemedTextView themedTextView = (ThemedTextView) findViewById(R.id.example_ratings_header_subtitle);
        ((ThemedTextView) findViewById(R.id.example_ratings_header_title)).setText(str);
        themedTextView.setText(str2);
    }
}
