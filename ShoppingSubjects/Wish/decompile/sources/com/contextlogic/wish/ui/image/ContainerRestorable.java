package com.contextlogic.wish.ui.image;

import android.view.View;
import android.view.ViewGroup;

public class ContainerRestorable {
    public static void restoreChildren(ViewGroup viewGroup) {
        if (viewGroup != null) {
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = viewGroup.getChildAt(i);
                if (childAt instanceof ImageRestorable) {
                    ((ImageRestorable) childAt).restoreImages();
                }
            }
        }
    }

    public static void releaseChildren(ViewGroup viewGroup) {
        if (viewGroup != null) {
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = viewGroup.getChildAt(i);
                if (childAt instanceof ImageRestorable) {
                    ((ImageRestorable) childAt).releaseImages();
                }
            }
        }
    }
}
