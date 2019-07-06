package com.etsy.android.uikit.util;

import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnDrawListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.ViewTreeObserver.OnPreDrawListener;

/* compiled from: ViewTreeObserverHelper */
public class j {
    public static void a(ViewTreeObserver viewTreeObserver, OnGlobalLayoutListener onGlobalLayoutListener) {
        if (viewTreeObserver != null) {
            viewTreeObserver.addOnGlobalLayoutListener(onGlobalLayoutListener);
        }
    }

    public static void b(ViewTreeObserver viewTreeObserver, OnGlobalLayoutListener onGlobalLayoutListener) {
        if (viewTreeObserver != null && viewTreeObserver.isAlive()) {
            viewTreeObserver.removeOnGlobalLayoutListener(onGlobalLayoutListener);
        }
    }

    public static void a(ViewTreeObserver viewTreeObserver, OnPreDrawListener onPreDrawListener) {
        if (viewTreeObserver != null) {
            viewTreeObserver.addOnPreDrawListener(onPreDrawListener);
        }
    }

    public static void b(ViewTreeObserver viewTreeObserver, OnPreDrawListener onPreDrawListener) {
        if (viewTreeObserver != null) {
            viewTreeObserver.removeOnPreDrawListener(onPreDrawListener);
        }
    }

    public static void a(ViewTreeObserver viewTreeObserver, OnDrawListener onDrawListener) {
        if (viewTreeObserver != null) {
            viewTreeObserver.addOnDrawListener(onDrawListener);
        }
    }

    public static void b(ViewTreeObserver viewTreeObserver, OnDrawListener onDrawListener) {
        if (viewTreeObserver != null) {
            viewTreeObserver.removeOnDrawListener(onDrawListener);
        }
    }
}
