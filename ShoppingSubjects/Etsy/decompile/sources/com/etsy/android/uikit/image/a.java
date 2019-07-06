package com.etsy.android.uikit.image;

import android.graphics.Bitmap;
import io.reactivex.functions.Consumer;

final /* synthetic */ class a implements Consumer {
    private final CropImageFragment a;

    a(CropImageFragment cropImageFragment) {
        this.a = cropImageFragment;
    }

    public void accept(Object obj) {
        this.a.lambda$init$0$CropImageFragment((Bitmap) obj);
    }
}
