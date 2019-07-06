package com.qualtrics.qsiframework;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.FrameLayout;
import com.qualtrics.qsiframework.c.b;

@Deprecated
public class QSISupportFragment extends Fragment {
    private WebView _qsiView;

    public void init(WebView webView) {
        this._qsiView = webView;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        FrameLayout frameLayout = (FrameLayout) layoutInflater.inflate(b.qsi_fragment, viewGroup, false);
        if (this._qsiView.getParent() != null) {
            ((ViewGroup) this._qsiView.getParent()).removeAllViews();
        }
        frameLayout.addView(this._qsiView);
        return frameLayout;
    }
}
