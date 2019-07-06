package com.etsy.android.uikit.a;

import android.animation.Animator;
import android.content.Context;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/* compiled from: AnimationManager */
public final class a {
    final Reference<Context> a;
    final List<Animator> b = new ArrayList();
    final List<b> c = new ArrayList();

    public a(Context context) {
        this.a = new WeakReference(context);
    }

    public void a() {
        for (Animator animator : this.b) {
            animator.removeAllListeners();
            if (animator.isRunning()) {
                animator.cancel();
            }
        }
        this.b.clear();
        for (b d : this.c) {
            d.d();
        }
        this.c.clear();
    }

    public Animator a(Animator animator) {
        this.b.add(animator);
        return animator;
    }
}
