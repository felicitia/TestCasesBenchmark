package com.etsy.android.uikit.ui.core;

import android.os.Bundle;
import android.text.Spannable;
import android.text.method.LinkMovementMethod;
import android.text.style.URLSpan;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.etsy.android.lib.a.i;
import com.etsy.android.lib.a.k;
import com.etsy.android.lib.logger.f;

public class TextDialogFragment extends TrackingBaseDialogFragment {
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setHasOptionsMenu(true);
        if (!getArguments().containsKey("text")) {
            f.a(new RuntimeException("No text to show in dialog"));
            goBack();
        }
    }

    public View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(k.fragment_text, viewGroup, true);
        TextView textView = (TextView) inflate.findViewById(i.text);
        CharSequence charSequence = getArguments().getCharSequence("text");
        if ((charSequence instanceof Spannable) && ((URLSpan[]) ((Spannable) charSequence).getSpans(0, charSequence.length(), URLSpan.class)).length != 0) {
            textView.setMovementMethod(LinkMovementMethod.getInstance());
        }
        textView.setText(charSequence);
        return inflate;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        goBack();
        return true;
    }
}
