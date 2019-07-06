package com.contextlogic.wish.activity.exampleugc.exampleugcintro;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.dialog.popupanimation.PopupAnimationDialogFragment;

public class ExampleUgcIntroDialogFragment<A extends BaseActivity> extends PopupAnimationDialogFragment {
    private String[] mDescriptionParagraphs;
    private String mTitle;

    /* access modifiers changed from: protected */
    public int getLayout() {
        return R.layout.example_ugc_intro_popup_animation_holder;
    }

    /* access modifiers changed from: protected */
    public boolean shouldAnimateDown() {
        return false;
    }

    public static ExampleUgcIntroDialogFragment<BaseActivity> createPointsDialogFragment(String str, String[] strArr) {
        ExampleUgcIntroDialogFragment<BaseActivity> exampleUgcIntroDialogFragment = new ExampleUgcIntroDialogFragment<>();
        Bundle bundle = new Bundle();
        bundle.putString("ArgumentTitle", str);
        bundle.putStringArray("ArgumentDescription", strArr);
        exampleUgcIntroDialogFragment.setArguments(bundle);
        return exampleUgcIntroDialogFragment;
    }

    /* access modifiers changed from: protected */
    public void handleArguments(Bundle bundle) {
        this.mTitle = bundle.getString("ArgumentTitle");
        this.mDescriptionParagraphs = bundle.getStringArray("ArgumentDescription");
    }

    /* access modifiers changed from: protected */
    public View getPopupView() {
        return new ExampleUgcIntroPopupView(getContext(), this.mTitle, this.mDescriptionParagraphs);
    }

    /* access modifiers changed from: protected */
    public ViewGroup getPopupHolder(View view) {
        return (ViewGroup) view.findViewById(R.id.example_ugc_intro_popup_animation_holder);
    }

    /* access modifiers changed from: protected */
    public int getPopupHeight() {
        return getResources().getDimensionPixelOffset(R.dimen.example_ugc_intro_popup_dialog_height);
    }
}
