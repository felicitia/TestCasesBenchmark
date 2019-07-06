package com.etsy.android.uikit.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.AppCompatSpinner;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import com.etsy.android.lib.a.i;
import com.etsy.android.lib.a.k;
import com.etsy.android.lib.a.p;
import java.util.ArrayList;

public class SpinnerList extends LinearLayout {
    private LayoutInflater mInflater;
    /* access modifiers changed from: private */
    public ArrayList<String> mOptions = new ArrayList<>();
    private String mPrompt;
    /* access modifiers changed from: private */
    public AppCompatSpinner mSpinner;

    private static class a extends BaseAdapter {
        private final ArrayList<String> a;
        private final LayoutInflater b;

        public a(Context context, ArrayList<String> arrayList) {
            this.b = LayoutInflater.from(context);
            this.a = arrayList;
        }

        public int getCount() {
            return this.a.size();
        }

        /* renamed from: a */
        public String getItem(int i) {
            return (String) this.a.get(i);
        }

        public long getItemId(int i) {
            return (long) ((String) this.a.get(i)).hashCode();
        }

        public View getDropDownView(int i, View view, ViewGroup viewGroup) {
            return a(i, view, viewGroup, k.spinner_dropdown_item);
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            return a(i, view, viewGroup, k.spinner_item_blue_large_no_padding);
        }

        private View a(int i, View view, ViewGroup viewGroup, @LayoutRes int i2) {
            if (view == null) {
                view = this.b.inflate(i2, viewGroup, false);
            }
            TextView textView = (TextView) view;
            textView.setText(String.valueOf(getItem(i)));
            if (i == 0) {
                textView.setTextAppearance(textView.getContext(), p.TextLightGrey_Large);
            } else {
                textView.setTextAppearance(textView.getContext(), p.TextBlue_Large);
            }
            return view;
        }
    }

    public SpinnerList(Context context) {
        super(context);
        init();
    }

    public SpinnerList(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public SpinnerList(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    @TargetApi(21)
    public SpinnerList(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        init();
    }

    private void init() {
        this.mInflater = LayoutInflater.from(getContext());
        setOrientation(1);
        setPrompt("");
    }

    public void setPrompt(String str) {
        this.mPrompt = str;
        if (!this.mOptions.isEmpty()) {
            this.mOptions.remove(0);
        }
        this.mOptions.add(0, this.mPrompt);
    }

    public void setOptions(ArrayList<String> arrayList) {
        removeAllViews();
        this.mOptions.clear();
        setPrompt(this.mPrompt);
        this.mOptions.addAll(arrayList);
        this.mInflater.inflate(k.spinner_with_line, this, true);
        this.mSpinner = (AppCompatSpinner) getChildAt(getChildCount() - 1);
        this.mSpinner.setAdapter((SpinnerAdapter) new a(getContext(), this.mOptions));
        this.mSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                if (i != 0) {
                    SpinnerList.this.addItemView(adapterView.getAdapter().getItem(i).toString());
                    SpinnerList.this.mSpinner.setSelection(0);
                }
            }
        });
    }

    public void setSelected(ArrayList<String> arrayList) {
        for (int i = 0; i < arrayList.size(); i++) {
            addItemView((String) arrayList.get(i));
        }
    }

    /* access modifiers changed from: private */
    public void addItemView(final String str) {
        final LinearLayout linearLayout = (LinearLayout) this.mInflater.inflate(k.textview_with_delete, this, false);
        ((TextView) linearLayout.findViewById(i.text)).setText(str);
        linearLayout.findViewById(i.delete_button).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                SpinnerList.this.mOptions.add(str);
                SpinnerList.this.removeView(linearLayout);
                ((a) SpinnerList.this.mSpinner.getAdapter()).notifyDataSetChanged();
                SpinnerList.this.mSpinner.setVisibility(0);
            }
        });
        this.mOptions.remove(str);
        ((a) this.mSpinner.getAdapter()).notifyDataSetChanged();
        addView(linearLayout, getChildCount() - 1);
        if (this.mOptions.size() <= 1) {
            this.mSpinner.setVisibility(8);
        }
    }

    public ArrayList<String> getSelected() {
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0; i < getChildCount() - 1; i++) {
            arrayList.add(((TextView) getChildAt(i).findViewById(i.text)).getText().toString());
        }
        return arrayList;
    }
}
