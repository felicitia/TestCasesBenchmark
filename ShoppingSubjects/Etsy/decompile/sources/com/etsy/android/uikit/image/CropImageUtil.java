package com.etsy.android.uikit.image;

import android.support.annotation.LayoutRes;
import android.support.annotation.StringRes;
import com.etsy.android.lib.a.k;
import com.etsy.android.lib.a.o;
import com.google.android.gms.common.ConnectionResult;
import java.io.Serializable;
import org.parceler.Parcel;

public class CropImageUtil {

    @Parcel
    public static class Options implements Serializable {
        private static final float DEFAULT_ASPECT_RATIO = 1.0f;
        private static final int DEFAULT_MAX_HEIGHT = 1500;
        private static final int DEFAULT_MAX_WIDTH = 1500;
        private static final int DEFAULT_MIN_HEIGHT = 640;
        private static final int DEFAULT_MIN_WIDTH = 640;
        private static final long serialVersionUID = 5830211587947078537L;
        float mAspectRatioX = 1.0f;
        float mAspectRatioY = 1.0f;
        @StringRes
        int mHelpTextId = o.crop_photo_help_text;
        @LayoutRes
        int mLayoutId = k.fragment_cropimage;
        int mMaxHeight = ConnectionResult.DRIVE_EXTERNAL_STORAGE_REQUIRED;
        int mMaxWidth = ConnectionResult.DRIVE_EXTERNAL_STORAGE_REQUIRED;
        int mMinHeight = 640;
        int mMinWidth = 640;
        boolean mUseOvalViewport = false;

        public Options setLayoutId(@LayoutRes int i) {
            this.mLayoutId = i;
            return this;
        }

        @LayoutRes
        public int getLayoutId() {
            return this.mLayoutId;
        }

        public Options setHelpTextId(@StringRes int i) {
            this.mHelpTextId = i;
            return this;
        }

        @StringRes
        public int getHelpTextId() {
            return this.mHelpTextId;
        }

        public Options setMaxHeight(int i) {
            if (i <= 0) {
                throw new IllegalArgumentException("Maximum height must be greater than 0.");
            }
            this.mMaxHeight = i;
            return this;
        }

        public int getMaxHeight() {
            return this.mMaxHeight;
        }

        public Options setMaxWidth(int i) {
            if (i <= 0) {
                throw new IllegalArgumentException("Maximum width must be greater than 0.");
            }
            this.mMaxWidth = i;
            return this;
        }

        public int getMaxWidth() {
            return this.mMaxWidth;
        }

        public Options setMinHeight(int i) {
            if (i <= 0) {
                throw new IllegalArgumentException("Minimum height must be greater than 0.");
            }
            this.mMinHeight = i;
            return this;
        }

        public int getMinHeight() {
            return this.mMinHeight;
        }

        public Options setMinWidth(int i) {
            if (i <= 0) {
                throw new IllegalArgumentException("Minimum width must be greater than 0.");
            }
            this.mMinWidth = i;
            return this;
        }

        public int getMinWidth() {
            return this.mMinWidth;
        }

        public Options setAspectRatio(float f, float f2) {
            if (f <= 0.0f || f2 <= 0.0f) {
                throw new IllegalArgumentException("Invalid aspect ration: must be greater than 0");
            }
            this.mAspectRatioX = f;
            this.mAspectRatioY = f2;
            return this;
        }

        public float getAspectRatioX() {
            return this.mAspectRatioX;
        }

        public float getAspectRatioY() {
            return this.mAspectRatioY;
        }

        public boolean useOvalViewport() {
            return this.mUseOvalViewport;
        }

        public Options setUseOvalViewport(boolean z) {
            this.mUseOvalViewport = z;
            return this;
        }
    }
}
