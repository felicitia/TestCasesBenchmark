package com.etsy.android.uikit.view;

import android.content.Context;
import android.support.v7.widget.AppCompatSpinner;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ZeroSpinner extends AppCompatSpinner {
    private SpinnerAdapter mAdapter;
    int mPromptViewResource = 17367048;
    boolean requestingTopView;

    protected class a implements InvocationHandler {
        protected SpinnerAdapter a;
        protected Method b;

        protected a(SpinnerAdapter spinnerAdapter) {
            this.a = spinnerAdapter;
            try {
                this.b = SpinnerAdapter.class.getMethod("getView", new Class[]{Integer.TYPE, View.class, ViewGroup.class});
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
            try {
                if (!method.equals(this.b) || (objArr[0].intValue() >= 0 && (!ZeroSpinner.this.requestingTopView || objArr[0].intValue() != 0))) {
                    return method.invoke(this.a, objArr);
                }
                return a(-1, objArr[1], objArr[2]);
            } catch (InvocationTargetException e) {
                throw e.getTargetException();
            } catch (Exception e2) {
                throw new RuntimeException(e2);
            }
        }

        /* access modifiers changed from: protected */
        public View a(int i, View view, ViewGroup viewGroup) throws IllegalAccessException {
            if (i >= 0) {
                return this.a.getView(i, view, viewGroup);
            }
            TextView textView = (TextView) ((LayoutInflater) ZeroSpinner.this.getContext().getSystemService("layout_inflater")).inflate(ZeroSpinner.this.mPromptViewResource, viewGroup, false);
            textView.setText(ZeroSpinner.this.getPrompt());
            return textView;
        }
    }

    public ZeroSpinner(Context context) {
        super(context);
    }

    public ZeroSpinner(Context context, int i) {
        super(context, i);
    }

    public ZeroSpinner(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ZeroSpinner(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public SpinnerAdapter getAdapter() {
        return this.mAdapter;
    }

    public void setAdapter(SpinnerAdapter spinnerAdapter) {
        if (spinnerAdapter == null) {
            super.setAdapter((SpinnerAdapter) null);
            return;
        }
        super.setAdapter(newProxy(spinnerAdapter));
        try {
            Method declaredMethod = AdapterView.class.getDeclaredMethod("setNextSelectedPositionInt", new Class[]{Integer.TYPE});
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(this, new Object[]{Integer.valueOf(-1)});
            Method declaredMethod2 = AdapterView.class.getDeclaredMethod("setSelectedPositionInt", new Class[]{Integer.TYPE});
            declaredMethod2.setAccessible(true);
            declaredMethod2.invoke(this, new Object[]{Integer.valueOf(-1)});
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /* access modifiers changed from: protected */
    public SpinnerAdapter newProxy(SpinnerAdapter spinnerAdapter) {
        this.mAdapter = spinnerAdapter;
        return (SpinnerAdapter) Proxy.newProxyInstance(spinnerAdapter.getClass().getClassLoader(), new Class[]{SpinnerAdapter.class}, new a(spinnerAdapter));
    }

    public void setPromptTextViewResource(int i) {
        this.mPromptViewResource = i;
    }

    public int getSelectedItemPosition() {
        if (super.getSelectedItemPosition() == -1) {
            this.requestingTopView = true;
            return 0;
        }
        this.requestingTopView = false;
        return super.getSelectedItemPosition();
    }

    public boolean hasSelection() {
        return super.getSelectedItemPosition() > -1;
    }
}
