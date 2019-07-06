package com.etsy.android.uikit.text;

import android.text.Layout;
import android.text.Spanned;
import android.text.style.URLSpan;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.TextView;

public class ClickableSpanTouchListener implements OnTouchListener {
    public boolean onTouch(View view, MotionEvent motionEvent) {
        TextView textView = (TextView) view;
        CharSequence text = textView.getText();
        boolean z = false;
        if (text instanceof Spanned) {
            Spanned spanned = (Spanned) text;
            if (motionEvent.getAction() == 1) {
                int length = spanned.length();
                int x = (int) motionEvent.getX();
                int totalPaddingLeft = (x - textView.getTotalPaddingLeft()) + textView.getScrollX();
                int y = (((int) motionEvent.getY()) - textView.getTotalPaddingTop()) + textView.getScrollY();
                Layout layout = textView.getLayout();
                URLSpan[] uRLSpanArr = (URLSpan[]) spanned.getSpans(layout.getOffsetForHorizontal(layout.getLineForVertical(y), (float) totalPaddingLeft), length, URLSpan.class);
                if (uRLSpanArr.length > 0) {
                    uRLSpanArr[0].onClick(textView);
                }
                if (uRLSpanArr.length > 0) {
                    z = true;
                }
                return z;
            }
        }
        return false;
    }
}
