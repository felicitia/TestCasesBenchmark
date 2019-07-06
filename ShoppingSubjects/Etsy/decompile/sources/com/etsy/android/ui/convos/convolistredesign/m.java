package com.etsy.android.ui.convos.convolistredesign;

import com.etsy.android.R;
import com.etsy.android.lib.logger.l;
import com.etsy.android.lib.models.Conversation3;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.ui.convos.convolistredesign.c.b;
import com.etsy.android.ui.convos.convoredesign.t;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.a;
import io.reactivex.q;
import io.reactivex.rxkotlin.c;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import kotlin.TypeCastException;
import kotlin.collections.o;
import kotlin.jvm.internal.p;

/* compiled from: ConvosListPresenter.kt */
public final class m {
    private int a = -1;
    private final a b = new a();
    private n c;
    private Disposable d;
    private final b e;
    /* access modifiers changed from: private */
    public final o f;
    private final com.etsy.android.lib.f.a g;
    private final t h;
    /* access modifiers changed from: private */
    public final l i;

    public m(b bVar, o oVar, com.etsy.android.lib.f.a aVar, t tVar, l lVar) {
        p.b(bVar, "repository");
        p.b(oVar, "view");
        p.b(aVar, "schedulers");
        p.b(tVar, "convoNotificationRepo");
        p.b(lVar, "logCat");
        this.e = bVar;
        this.f = oVar;
        this.g = aVar;
        this.h = tVar;
        this.i = lVar;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001e, code lost:
        if (r2 != null) goto L_0x0028;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a() {
        /*
            r4 = this;
            com.etsy.android.ui.convos.convolistredesign.n r0 = r4.c
            r1 = 0
            if (r0 == 0) goto L_0x000a
            int r0 = r0.b()
            goto L_0x000b
        L_0x000a:
            r0 = r1
        L_0x000b:
            com.etsy.android.ui.convos.convolistredesign.n r2 = r4.c
            if (r2 == 0) goto L_0x0014
            int r2 = r2.c()
            goto L_0x0015
        L_0x0014:
            r2 = r1
        L_0x0015:
            int r0 = r0 + r2
            com.etsy.android.ui.convos.convolistredesign.n r2 = r4.c
            if (r2 == 0) goto L_0x0021
            com.etsy.android.ui.convos.convolistredesign.n r2 = r2.a()
            if (r2 == 0) goto L_0x0021
            goto L_0x0028
        L_0x0021:
            com.etsy.android.ui.convos.convolistredesign.n r2 = new com.etsy.android.ui.convos.convolistredesign.n
            r3 = 20
            r2.<init>(r1, r3)
        L_0x0028:
            int r1 = r4.a
            r3 = -1
            if (r1 == r3) goto L_0x0038
            int r1 = r4.a
            if (r1 <= r0) goto L_0x0032
            goto L_0x0038
        L_0x0032:
            com.etsy.android.ui.convos.convolistredesign.o r0 = r4.f
            r0.stopRefreshing()
            goto L_0x006b
        L_0x0038:
            com.etsy.android.ui.convos.convolistredesign.b r0 = r4.e
            io.reactivex.v r0 = r0.a(r2)
            com.etsy.android.lib.f.a r1 = r4.g
            io.reactivex.u r1 = r1.a()
            io.reactivex.v r0 = r0.b(r1)
            com.etsy.android.lib.f.a r1 = r4.g
            io.reactivex.u r1 = r1.b()
            io.reactivex.v r0 = r0.a(r1)
            java.lang.String r1 = "repository\n             …(schedulers.mainThread())"
            kotlin.jvm.internal.p.a(r0, r1)
            com.etsy.android.ui.convos.convolistredesign.ConvosListPresenter$loadConversations$1 r1 = new com.etsy.android.ui.convos.convolistredesign.ConvosListPresenter$loadConversations$1
            r1.<init>(r4)
            kotlin.jvm.a.b r1 = (kotlin.jvm.a.b) r1
            com.etsy.android.ui.convos.convolistredesign.ConvosListPresenter$loadConversations$2 r2 = com.etsy.android.ui.convos.convolistredesign.ConvosListPresenter$loadConversations$2.INSTANCE
            kotlin.jvm.a.b r2 = (kotlin.jvm.a.b) r2
            io.reactivex.disposables.Disposable r0 = io.reactivex.rxkotlin.c.a(r0, r2, r1)
            io.reactivex.disposables.a r1 = r4.b
            io.reactivex.rxkotlin.a.a(r0, r1)
        L_0x006b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.etsy.android.ui.convos.convolistredesign.m.a():void");
    }

    /* access modifiers changed from: private */
    public final void a(c cVar) {
        if (cVar instanceof b) {
            b bVar = (b) cVar;
            a(bVar.a(), bVar.b(), bVar.c());
        } else if (cVar instanceof c.a) {
            c.a aVar = (c.a) cVar;
            if (aVar.a().isEmpty()) {
                this.f.showErrorView();
            } else {
                a(aVar.a(), aVar.b(), aVar.c());
                this.f.showErrorSnackbar(R.string.convo_error);
            }
            this.f.stopRefreshing();
        }
    }

    private final void a(List<Conversation3> list, int i2, n nVar) {
        e eVar;
        boolean z = nVar.b() == 0 && list.isEmpty();
        this.a = i2;
        if (z || this.a == 0) {
            this.f.showEmptyView();
            return;
        }
        this.c = nVar;
        Conversation3 conversation3 = null;
        Iterable<Conversation3> iterable = list;
        Collection arrayList = new ArrayList(o.a(iterable, 10));
        for (Conversation3 conversation32 : iterable) {
            if (a(conversation32, conversation3)) {
                eVar = new e.b(conversation32, b(conversation32));
            } else {
                eVar = new e.a(conversation32);
            }
            arrayList.add(eVar);
            conversation3 = conversation32;
        }
        this.f.addItemsToAdapter((List) arrayList);
        this.f.showListView();
        this.f.stopRefreshing();
    }

    public final void a(EtsyId etsyId) {
        if (etsyId != null) {
            this.f.navigateToUserWebView(etsyId);
        }
    }

    public final void a(Conversation3 conversation3) {
        p.b(conversation3, ResponseConstants.CONVO);
        this.f.launchConversation(conversation3);
    }

    public final void b() {
        this.c = null;
    }

    public final boolean a(Conversation3 conversation3, Conversation3 conversation32) {
        p.b(conversation3, "thisConversation");
        return conversation32 == null || a(conversation3.getLastUpdateDateInMillis(), conversation32.getLastUpdateDateInMillis());
    }

    public final boolean a(long j, long j2) {
        Calendar instance = Calendar.getInstance(Locale.getDefault());
        p.a((Object) instance, "calendar");
        instance.setTimeInMillis(j);
        int i2 = instance.get(2);
        instance.setTimeInMillis(j2);
        return i2 != instance.get(2);
    }

    public final String b(Conversation3 conversation3) {
        p.b(conversation3, "conversation");
        Calendar instance = Calendar.getInstance(Locale.getDefault());
        p.a((Object) instance, "calendar");
        instance.setTimeInMillis(conversation3.getLastUpdateDateInMillis());
        StringBuilder sb = new StringBuilder();
        String displayName = instance.getDisplayName(2, 2, Locale.getDefault());
        p.a((Object) displayName, "calendar.getDisplayName(…ONG, Locale.getDefault())");
        Locale locale = Locale.getDefault();
        p.a((Object) locale, "Locale.getDefault()");
        if (displayName == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        String upperCase = displayName.toUpperCase(locale);
        p.a((Object) upperCase, "(this as java.lang.String).toUpperCase(locale)");
        sb.append(upperCase);
        int i2 = instance.get(1);
        instance.setTimeInMillis(System.currentTimeMillis());
        if (i2 != instance.get(1)) {
            sb.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
            sb.append(i2);
        }
        String sb2 = sb.toString();
        p.a((Object) sb2, "displayDate.toString()");
        return sb2;
    }

    public final void c() {
        q a2 = this.h.a().b(this.g.a()).a(this.g.b());
        p.a((Object) a2, "convoNotificationRepo.ge…(schedulers.mainThread())");
        this.d = c.a(a2, (kotlin.jvm.a.b) new ConvosListPresenter$startListeningForNotifications$2(this), (kotlin.jvm.a.a) null, new ConvosListPresenter$startListeningForNotifications$1(this), 2, (Object) null);
    }

    public final void d() {
        Disposable disposable = this.d;
        if (disposable != null) {
            disposable.dispose();
        }
    }
}
