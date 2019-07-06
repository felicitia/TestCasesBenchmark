package com.contextlogic.wish.util;

import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.ui.grid.StaggeredGridView;
import com.contextlogic.wish.ui.image.AutoReleasableImageView;
import com.contextlogic.wish.ui.image.NetworkImageView;
import com.contextlogic.wish.ui.listview.HorizontalListView;

public class ViewUtil {
    public static void releaseReleasableImageViews(View view) {
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                releaseReleasableImageViews(viewGroup.getChildAt(i));
            }
        } else if (view instanceof AutoReleasableImageView) {
            ((AutoReleasableImageView) view).releaseImages();
        }
    }

    public static void restoreReleasableImageViews(View view) {
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                restoreReleasableImageViews(viewGroup.getChildAt(i));
            }
        } else if (view instanceof AutoReleasableImageView) {
            ((AutoReleasableImageView) view).restoreImages();
        }
    }

    public static void unbindDrawables(View view) {
        if (view.getBackground() != null) {
            view.getBackground().setCallback(null);
        }
        WishApplication.getInstance().getRefWatcher().watch(view);
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                unbindDrawables(viewGroup.getChildAt(i));
            }
            try {
                viewGroup.removeAllViews();
            } catch (Exception unused) {
            }
        } else if (view instanceof NetworkImageView) {
            NetworkImageView networkImageView = (NetworkImageView) view;
            networkImageView.clearPlaceholder();
            networkImageView.releaseImages();
        } else if (view instanceof ImageView) {
            ImageView imageView = (ImageView) view;
            if (imageView.getDrawable() != null) {
                imageView.getDrawable().setCallback(null);
            }
            imageView.setImageDrawable(null);
        }
        if (view instanceof StaggeredGridView) {
            ((StaggeredGridView) view).teardown();
        } else if (view instanceof HorizontalListView) {
            ((HorizontalListView) view).teardown();
        }
    }

    public static String extractEditTextValue(EditText editText) {
        if (editText == null) {
            return null;
        }
        String trim = editText.getText() == null ? "" : editText.getText().toString().trim();
        if (trim.equals("")) {
            trim = null;
        }
        return trim;
    }

    public static void setDefaultScreenPadding(View view) {
        view.setPadding(view.getContext().getResources().getDimensionPixelSize(R.dimen.screen_padding), view.getContext().getResources().getDimensionPixelSize(R.dimen.screen_padding), view.getContext().getResources().getDimensionPixelSize(R.dimen.screen_padding), view.getContext().getResources().getDimensionPixelSize(R.dimen.screen_padding));
    }

    public static <T extends View> T findViewByClassReference(View view, Class<T> cls) {
        if (cls.isInstance(view)) {
            return (View) cls.cast(view);
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                T findViewByClassReference = findViewByClassReference(viewGroup.getChildAt(i), cls);
                if (findViewByClassReference != null) {
                    return findViewByClassReference;
                }
            }
        }
        return null;
    }
}
