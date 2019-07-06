package com.etsy.android.ui.convos.convoredesign;

import android.support.v4.app.Fragment;
import com.etsy.android.R;
import com.etsy.android.lib.util.e;
import com.etsy.android.lib.util.e.C0081e;
import com.etsy.android.lib.util.e.a;
import com.etsy.android.lib.util.e.c;
import com.etsy.android.lib.util.e.d;
import kotlin.h;
import kotlin.jvm.a.b;
import kotlin.jvm.internal.Lambda;

/* compiled from: ConvoThreadImageHelper.kt */
final class ConvoThreadImageHelper$1 extends Lambda implements b<e, h> {
    final /* synthetic */ ae this$0;

    ConvoThreadImageHelper$1(ae aeVar) {
        this.this$0 = aeVar;
        super(1);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((e) obj);
        return h.a;
    }

    public final void invoke(e eVar) {
        if (eVar instanceof c) {
            this.this$0.a();
        } else if (eVar instanceof C0081e) {
            this.this$0.b.i();
        } else if (eVar instanceof e.b) {
            e.b bVar = (e.b) eVar;
            this.this$0.a(bVar.a(), bVar.b());
        } else if (eVar instanceof a) {
            this.this$0.b.a(((a) eVar).a());
        } else if (eVar instanceof d) {
            this.this$0.d.startImagePicker((Fragment) this.this$0.c, (int) R.string.choose_image);
        }
    }
}
