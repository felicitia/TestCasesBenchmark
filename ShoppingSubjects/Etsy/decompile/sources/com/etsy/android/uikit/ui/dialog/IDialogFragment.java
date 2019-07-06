package com.etsy.android.uikit.ui.dialog;

import android.app.Dialog;
import android.support.v4.app.FragmentManager;
import android.view.View.OnClickListener;

public interface IDialogFragment {

    public enum WindowMode {
        STANDARD,
        WRAP,
        WRAP_ALL,
        SMALL,
        MEDIUM,
        LARGE
    }

    void dismiss();

    void dismissAllowingStateLoss();

    void dismissAllowingStateLoss(boolean z);

    FragmentManager getChildFragmentManager();

    Dialog getDialog();

    int getMinHeight(WindowMode windowMode);

    int getMinWidth(WindowMode windowMode);

    void hideHeader();

    boolean isDetached();

    void setDialogGravity(int i);

    void setOkButtonVisibility(int i);

    void setOkClickListener(OnClickListener onClickListener, boolean z);

    void setTitle(String str);

    void setWindowAnimation(int i);

    void setWindowBackgroundDim(float f);

    void setWindowMode(WindowMode windowMode);
}
