package com.contextlogic.wish.dialog.prompt;

import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.dialog.BaseDialogFragment;
import com.contextlogic.wish.util.ValueUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

@Deprecated
public class PromptDialogFragment<A extends BaseActivity> extends BaseDialogFragment<A> {
    public static PromptDialogFragment<BaseActivity> createErrorDialog(String str) {
        if (str == null) {
            str = WishApplication.getInstance().getString(R.string.general_error);
        }
        return createOkDialog(WishApplication.getInstance().getString(R.string.oops), str);
    }

    public static PromptDialogFragment<BaseActivity> createOkDialog(String str, String str2) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(PromptDialogChoice.createOkChoice());
        return createDialog(str, str2, arrayList);
    }

    public static PromptDialogFragment<BaseActivity> createDialog(String str, String str2, ArrayList<PromptDialogChoice> arrayList) {
        PromptDialogFragment<BaseActivity> promptDialogFragment = new PromptDialogFragment<>();
        Bundle bundle = new Bundle();
        if (str != null) {
            bundle.putString("ArgumentTitle", str);
        }
        if (str2 != null) {
            bundle.putString("ArgumentMessage", str2);
        }
        bundle.putParcelableArrayList("ArgumentChoices", arrayList);
        promptDialogFragment.setArguments(bundle);
        return promptDialogFragment;
    }

    public View getContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.prompt_dialog_fragment, viewGroup, false);
        String string = getArguments().getString("ArgumentTitle");
        String string2 = getArguments().getString("ArgumentMessage");
        ArrayList parcelableArrayList = getArguments().getParcelableArrayList("ArgumentChoices");
        if (string != null) {
            initializeTitle(inflate, string, string2, parcelableArrayList);
        }
        if (string2 != null) {
            initializeMessage(inflate, string2, parcelableArrayList);
        }
        if (parcelableArrayList != null && parcelableArrayList.size() > 0) {
            initializeChoices(inflate, layoutInflater, parcelableArrayList);
        }
        return inflate;
    }

    private void initializeTitle(View view, String str, String str2, ArrayList<PromptDialogChoice> arrayList) {
        TextView textView = (TextView) view.findViewById(R.id.prompt_dialog_fragment_title);
        textView.setVisibility(0);
        textView.setText(str);
        if (str2 != null) {
            textView.setPadding(textView.getPaddingLeft(), textView.getPaddingTop(), textView.getPaddingRight(), (int) ValueUtil.convertDpToPx(16.0f));
        } else if (arrayList != null && arrayList.size() > 0) {
            textView.setPadding(textView.getPaddingLeft(), textView.getPaddingTop(), textView.getPaddingRight(), (int) ValueUtil.convertDpToPx(30.0f));
        }
    }

    private void initializeMessage(View view, String str, ArrayList<PromptDialogChoice> arrayList) {
        TextView textView = (TextView) view.findViewById(R.id.prompt_dialog_fragment_message);
        textView.setVisibility(0);
        textView.setText(str);
        if (arrayList != null && arrayList.size() > 0) {
            textView.setPadding(textView.getPaddingLeft(), textView.getPaddingTop(), textView.getPaddingRight(), (int) ValueUtil.convertDpToPx(30.0f));
        }
    }

    private void initializeChoices(View view, LayoutInflater layoutInflater, ArrayList<PromptDialogChoice> arrayList) {
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.prompt_dialog_fragment_choices);
        linearLayout.setVisibility(0);
        int dialogWidth = getDialogWidth();
        int convertDpToPx = (int) (ValueUtil.convertDpToPx(16.0f) * 2.0f);
        TextView textView = (TextView) layoutInflater.inflate(R.layout.prompt_dialog_choice, linearLayout, false);
        Paint paint = new Paint();
        paint.setTextSize(textView.getTextSize());
        paint.setTypeface(textView.getTypeface());
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            PromptDialogChoice promptDialogChoice = (PromptDialogChoice) it.next();
            float f = (float) convertDpToPx;
            float convertDpToPx2 = (float) ((int) (ValueUtil.convertDpToPx(8.0f) * 2.0f));
            StringBuilder sb = new StringBuilder();
            sb.append(promptDialogChoice.getText());
            sb.append(getResources().getDimensionPixelSize(R.dimen.triple_screen_padding));
            convertDpToPx = (int) (f + convertDpToPx2 + paint.measureText(sb.toString()));
        }
        final boolean z = ((double) convertDpToPx) > ((double) dialogWidth) * 0.7d;
        if (z) {
            linearLayout.setOrientation(1);
        } else {
            linearLayout.setOrientation(0);
        }
        Collections.sort(arrayList, new Comparator<PromptDialogChoice>() {
            public int compare(PromptDialogChoice promptDialogChoice, PromptDialogChoice promptDialogChoice2) {
                int i = 1;
                if (promptDialogChoice.isPositive() && !promptDialogChoice2.isPositive()) {
                    if (z) {
                        i = -1;
                    }
                    return i;
                } else if (promptDialogChoice.isPositive() || !promptDialogChoice2.isPositive()) {
                    return 0;
                } else {
                    if (!z) {
                        i = -1;
                    }
                    return i;
                }
            }
        });
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            final PromptDialogChoice promptDialogChoice2 = (PromptDialogChoice) it2.next();
            TextView textView2 = (TextView) layoutInflater.inflate(R.layout.prompt_dialog_choice, linearLayout, false);
            if (!z) {
                textView2.setPadding(WishApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.triple_screen_padding), textView2.getPaddingTop(), textView2.getPaddingRight(), textView2.getPaddingBottom());
            }
            textView2.setText(promptDialogChoice2.getText().toUpperCase());
            if (promptDialogChoice2.isPositive()) {
                textView2.setTextColor(WishApplication.getInstance().getResources().getColorStateList(R.color.text_selector_main_primary));
            } else {
                textView2.setTextColor(WishApplication.getInstance().getResources().getColorStateList(R.color.text_selector_text_primary));
            }
            linearLayout.addView(textView2);
            textView2.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    PromptDialogFragment.this.makeSelection(promptDialogChoice2.getChoiceId());
                }
            });
        }
    }
}
