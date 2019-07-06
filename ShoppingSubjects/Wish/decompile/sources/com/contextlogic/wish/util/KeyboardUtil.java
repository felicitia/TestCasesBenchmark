package com.contextlogic.wish.util;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import com.contextlogic.wish.application.WishApplication;

public class KeyboardUtil {
    public static void requestKeyboardFocus(View view) {
        view.requestFocus();
        InputMethodManager inputMethodManager = (InputMethodManager) WishApplication.getInstance().getSystemService("input_method");
        if (inputMethodManager != null) {
            inputMethodManager.showSoftInput(view, 1);
        }
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager) WishApplication.getInstance().getSystemService("input_method");
        if (inputMethodManager != null && activity != null) {
            try {
                inputMethodManager.hideSoftInputFromWindow(activity.getWindow().getDecorView().getRootView().getWindowToken(), 0);
            } catch (Throwable unused) {
            }
        }
    }

    public static void hideKeyboard(Fragment fragment) {
        if (fragment != null) {
            hideKeyboard(fragment.getView());
        }
    }

    public static void hideKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) WishApplication.getInstance().getSystemService("input_method");
        if (inputMethodManager != null && view != null) {
            try {
                inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
            } catch (Throwable unused) {
            }
        }
    }
}
