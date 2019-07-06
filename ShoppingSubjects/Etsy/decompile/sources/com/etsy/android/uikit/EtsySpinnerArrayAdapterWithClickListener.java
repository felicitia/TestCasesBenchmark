package com.etsy.android.uikit;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import com.etsy.android.lib.a.i;
import com.etsy.android.lib.a.k;
import java.util.ArrayList;

public class EtsySpinnerArrayAdapterWithClickListener<T> extends ArrayAdapter<T> {
    OnItemSelectedListener mAdapterViewOnItemSelectedListener = new OnItemSelectedListener() {
        public void onNothingSelected(AdapterView<?> adapterView) {
        }

        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
            Object item = EtsySpinnerArrayAdapterWithClickListener.this.getItem(i);
            if (!(item == null || EtsySpinnerArrayAdapterWithClickListener.this.mOnItemClickListener == null || !EtsySpinnerArrayAdapterWithClickListener.this.mOnItemClickListener.b(item))) {
                EtsySpinnerArrayAdapterWithClickListener.this.mOnItemClickListener.a(item);
            }
        }
    };
    a mOnItemClickListener = null;

    public interface a<T> {
        void a(@NonNull T t);

        boolean b(@NonNull T t);
    }

    public EtsySpinnerArrayAdapterWithClickListener(Context context, Spinner spinner) {
        super(context, k.spinner_list_item, i.text);
        setDropDownViewResource(k.spinner_list_item_dropdown);
        spinner.setAdapter(this);
        spinner.setOnItemSelectedListener(getOnItemSelectedListener());
    }

    public EtsySpinnerArrayAdapterWithClickListener(Context context, @NonNull Spinner spinner, @NonNull ArrayList<T> arrayList) {
        super(context, k.spinner_list_item, i.text);
        setDropDownViewResource(k.spinner_list_item_dropdown);
        addAll(arrayList);
        spinner.setAdapter(this);
        spinner.setOnItemSelectedListener(getOnItemSelectedListener());
    }

    public void setOnItemClickListener(a<T> aVar) {
        this.mOnItemClickListener = aVar;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        View view2 = super.getView(i, view, viewGroup);
        setText(view2, getItem(i));
        return view2;
    }

    public View getDropDownView(int i, View view, ViewGroup viewGroup) {
        View dropDownView = super.getDropDownView(i, view, viewGroup);
        setText(dropDownView.findViewById(i.text), getItem(i));
        return dropDownView;
    }

    /* access modifiers changed from: 0000 */
    public void setText(View view, T t) {
        TextView textView = (TextView) view;
        if (textView != null) {
            textView.setText(getText(t));
        }
    }

    /* access modifiers changed from: protected */
    public String getText(T t) {
        return t.toString();
    }

    public OnItemSelectedListener getOnItemSelectedListener() {
        return this.mAdapterViewOnItemSelectedListener;
    }
}
