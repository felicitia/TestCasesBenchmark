package com.etsy.android.uikit.view;

import android.view.View;
import android.view.View.OnClickListener;
import java.util.ArrayList;
import java.util.List;

public class CompositeOnClickListener implements OnClickListener {
    private List<OnClickListener> registeredListeners = new ArrayList();

    public void add(OnClickListener onClickListener) {
        this.registeredListeners.add(onClickListener);
    }

    public void remove(OnClickListener onClickListener) {
        this.registeredListeners.remove(onClickListener);
    }

    public void onClick(View view) {
        for (OnClickListener onClick : this.registeredListeners) {
            onClick.onClick(view);
        }
    }
}
