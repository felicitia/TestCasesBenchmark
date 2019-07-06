package com.etsy.android.ui.convos.convoredesign;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.StringRes;
import android.support.v4.app.FrameMetricsAggregator;
import com.etsy.android.R;
import com.etsy.android.lib.models.Conversation3;
import com.etsy.android.lib.models.ConversationUser;
import com.etsy.android.lib.models.ConvoUser;
import com.etsy.android.lib.models.ImageInfo;
import com.etsy.android.lib.models.Listing;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.convo.ConversationThread.InteractionState;
import com.etsy.android.lib.models.convo.ConversationThread2;
import com.etsy.android.lib.models.convo.context.ConvoContext;
import com.etsy.android.lib.models.convo.context.CustomOrderContext;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.util.NetworkUtils;
import com.etsy.android.ui.convos.convoredesign.DraftMessage.Status;
import io.reactivex.disposables.Disposable;
import io.reactivex.q;
import io.reactivex.v;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.TypeCastException;
import kotlin.collections.o;
import kotlin.jvm.internal.Ref.IntRef;
import kotlin.jvm.internal.p;

/* compiled from: ConvoThreadPresenter.kt */
public final class af {
    public static final a a = new a(null);
    /* access modifiers changed from: private */
    public final io.reactivex.disposables.a b = new io.reactivex.disposables.a();
    private final c[] c;
    /* access modifiers changed from: private */
    public InteractionState d;
    /* access modifiers changed from: private */
    public DraftMessage e;
    /* access modifiers changed from: private */
    public ConversationThread2 f;
    /* access modifiers changed from: private */
    public ArrayList<DraftMessage> g;
    private Disposable h;
    private final b i;
    private final d j;
    private final e k;
    /* access modifiers changed from: private */
    public final c l;
    /* access modifiers changed from: private */
    public final ai m;
    /* access modifiers changed from: private */
    public final com.etsy.android.lib.f.a n;
    private final EtsyId o;
    private final ConversationUser p;
    /* access modifiers changed from: private */
    public final com.etsy.android.lib.util.b.a q;
    private final NetworkUtils r;
    private final com.etsy.android.lib.util.sharedprefs.b s;
    private final t t;

    /* compiled from: ConvoThreadPresenter.kt */
    public static final class a {
        private a() {
        }

        public /* synthetic */ a(o oVar) {
            this();
        }
    }

    /* compiled from: ConvoThreadPresenter.kt */
    public interface b {
        void a(int i, List<ImageInfo> list);
    }

    /* compiled from: ConvoThreadPresenter.kt */
    private static abstract class c {

        /* compiled from: ConvoThreadPresenter.kt */
        public static final class a extends c {
            private final File a;

            public a(File file) {
                p.b(file, ResponseConstants.FILE);
                super(null);
                this.a = file;
            }

            public final File a() {
                return this.a;
            }
        }

        /* compiled from: ConvoThreadPresenter.kt */
        public static final class b extends c {
            public b() {
                super(null);
            }
        }

        /* renamed from: com.etsy.android.ui.convos.convoredesign.af$c$c reason: collision with other inner class name */
        /* compiled from: ConvoThreadPresenter.kt */
        public static final class C0091c extends c {
            public C0091c() {
                super(null);
            }
        }

        private c() {
        }

        public /* synthetic */ c(o oVar) {
            this();
        }
    }

    /* compiled from: ConvoThreadPresenter.kt */
    public interface d {
        void a(int i, ak akVar);
    }

    /* compiled from: ConvoThreadPresenter.kt */
    public interface e {
        void a(String str);
    }

    /* compiled from: ConvoThreadPresenter.kt */
    public static final class f implements d {
        final /* synthetic */ af a;

        f(af afVar) {
            this.a = afVar;
        }

        public void a(int i, ak akVar) {
            if ((akVar != null ? akVar.e() : null) != null) {
                if (akVar.a().length() == 0) {
                    v a2 = this.a.l.a(akVar.e()).b(this.a.n.a()).a(this.a.n.b());
                    p.a((Object) a2, "repository.getCardDetail…(schedulers.mainThread())");
                    io.reactivex.rxkotlin.a.a(io.reactivex.rxkotlin.c.a(a2, (kotlin.jvm.a.b<? super Throwable, kotlin.h>) new ConvoThreadPresenter$linkCardBoundListener$1$onLinkCardBound$2<Object,kotlin.h>(this), new ConvoThreadPresenter$linkCardBoundListener$1$onLinkCardBound$1<>(this, akVar, i)), this.a.b);
                }
            }
        }
    }

    /* compiled from: ConvoThreadPresenter.kt */
    public static final class g implements e {
        final /* synthetic */ af a;

        g(af afVar) {
            this.a = afVar;
        }

        public void a(String str) {
            p.b(str, "url");
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            intent.setData(Uri.parse(str));
            this.a.m.getContext().startActivity(intent);
        }
    }

    /* compiled from: ConvoThreadPresenter.kt */
    public static final class h implements b {
        final /* synthetic */ af a;

        h(af afVar) {
            this.a = afVar;
        }

        public void a(int i, List<ImageInfo> list) {
            p.b(list, "images");
            String urlFullxFull = ((ImageInfo) list.get(i)).getUrlFullxFull();
            if (this.a.q.a(urlFullxFull)) {
                this.a.m.openDetailedImagesView(i, list);
            } else {
                this.a.m.openNonImageAttachmentView(urlFullxFull);
            }
        }
    }

    public af(c cVar, ai aiVar, com.etsy.android.lib.f.a aVar, EtsyId etsyId, ConversationUser conversationUser, com.etsy.android.lib.util.b.a aVar2, NetworkUtils networkUtils, com.etsy.android.lib.util.sharedprefs.b bVar, t tVar) {
        c cVar2 = cVar;
        ai aiVar2 = aiVar;
        com.etsy.android.lib.f.a aVar3 = aVar;
        EtsyId etsyId2 = etsyId;
        ConversationUser conversationUser2 = conversationUser;
        com.etsy.android.lib.util.b.a aVar4 = aVar2;
        NetworkUtils networkUtils2 = networkUtils;
        com.etsy.android.lib.util.sharedprefs.b bVar2 = bVar;
        t tVar2 = tVar;
        p.b(cVar2, "repository");
        p.b(aiVar2, "view");
        p.b(aVar3, "schedulers");
        p.b(etsyId2, "convoId");
        p.b(conversationUser2, "currentUser");
        p.b(aVar4, "fileSupport");
        p.b(networkUtils2, "networkUtils");
        p.b(bVar2, "sharedPreferencesProvider");
        p.b(tVar2, "convoNotificationRepo");
        this.l = cVar2;
        this.m = aiVar2;
        this.n = aVar3;
        this.o = etsyId2;
        this.p = conversationUser2;
        this.q = aVar4;
        this.r = networkUtils2;
        this.s = bVar2;
        this.t = tVar2;
        c[] cVarArr = new c[3];
        int length = cVarArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            cVarArr[i2] = new C0091c();
        }
        this.c = cVarArr;
        this.d = new InteractionState();
        DraftMessage draftMessage = new DraftMessage(this.o.getIdAsLong(), "", this.p.getUsername(), false, 0, 0, null, 0, null, 496, null);
        this.e = draftMessage;
        this.g = new ArrayList<>();
        this.i = new h(this);
        this.j = new f(this);
        this.k = new g(this);
    }

    public final b a() {
        return this.i;
    }

    public final d b() {
        return this.j;
    }

    public final e c() {
        return this.k;
    }

    @StringRes
    public final int d() {
        ConversationThread2 conversationThread2 = this.f;
        return (conversationThread2 == null || !conversationThread2.isRead()) ? R.string.convo_mark_as_read : R.string.convo_mark_as_unread;
    }

    public final boolean e() {
        return this.f != null;
    }

    public final void f() {
        this.b.a();
    }

    public final void g() {
        if (this.r.b() && (!this.g.isEmpty())) {
            a(this.g);
        }
        io.reactivex.g a2 = this.l.a(this.o.getIdAsLong()).b(this.n.a()).a(this.n.b());
        p.a((Object) a2, "repository\n             …(schedulers.mainThread())");
        io.reactivex.rxkotlin.a.a(io.reactivex.rxkotlin.c.a(a2, (kotlin.jvm.a.b) new ConvoThreadPresenter$loadContent$2(this), (kotlin.jvm.a.a) null, new ConvoThreadPresenter$loadContent$1(this), 2, (Object) null), this.b);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x004d, code lost:
        if (r0 != null) goto L_0x0052;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(com.etsy.android.lib.models.convo.ConversationThread2 r4) {
        /*
            r3 = this;
            java.lang.String r0 = "thread"
            kotlin.jvm.internal.p.b(r4, r0)
            r3.f = r4
            com.etsy.android.lib.models.Conversation3 r0 = r4.getConversation()
            com.etsy.android.lib.models.ConvoUser r0 = r0.getOtherUser()
            com.etsy.android.lib.models.convo.ConversationThread2 r1 = r3.f
            if (r1 == 0) goto L_0x001d
            boolean r1 = r1.isRead()
            if (r1 != 0) goto L_0x001d
            r1 = 0
            r3.a(r1)
        L_0x001d:
            com.etsy.android.lib.models.ConversationUser r1 = r3.p
            com.etsy.android.lib.models.datatypes.EtsyId r1 = r1.getUserId()
            java.lang.String r2 = "currentUser.userId"
            kotlin.jvm.internal.p.a(r1, r2)
            long r1 = r1.getIdAsLong()
            java.util.List r1 = com.etsy.android.ui.convos.convoredesign.p.a(r4, r1)
            com.etsy.android.ui.convos.convoredesign.ai r2 = r3.m
            r2.addItemsToAdapter(r1)
            com.etsy.android.ui.convos.convoredesign.ai r1 = r3.m
            r1.showListView()
            com.etsy.android.ui.convos.convoredesign.ai r1 = r3.m
            r1.stopRefreshing()
            com.etsy.android.ui.convos.convoredesign.ai r1 = r3.m
            if (r0 == 0) goto L_0x0050
            com.etsy.android.ui.convos.convoredesign.ai r2 = r3.m
            android.content.Context r2 = r2.getContext()
            java.lang.String r0 = r0.getFormattedDisplayName(r2)
            if (r0 == 0) goto L_0x0050
            goto L_0x0052
        L_0x0050:
            java.lang.String r0 = ""
        L_0x0052:
            r1.setTitle(r0)
            com.etsy.android.lib.models.Conversation3 r4 = r4.getConversation()
            r3.a(r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.etsy.android.ui.convos.convoredesign.af.a(com.etsy.android.lib.models.convo.ConversationThread2):void");
    }

    public final void a(Conversation3 conversation3) {
        p.b(conversation3, ResponseConstants.CONVO);
        ConvoContext conversationContext = conversation3.getConversationContext();
        if (!(conversationContext instanceof CustomOrderContext)) {
            this.m.showTitleBar(conversation3.getTitle());
            return;
        }
        CustomOrderContext customOrderContext = (CustomOrderContext) conversationContext;
        if (customOrderContext.getShowStatusBar()) {
            a(customOrderContext);
        } else {
            this.m.hideTitleBar();
        }
    }

    private final void a(CustomOrderContext customOrderContext) {
        switch (ag.a[customOrderContext.getActionType().ordinal()]) {
            case 1:
                this.m.showGreenCircleTitleBar(customOrderContext.getFormattedStatus(), customOrderContext.getFormattedButtonTitle());
                return;
            case 2:
                if (customOrderContext.isDraft()) {
                    this.m.showYellowCircleTitleBar(customOrderContext.getFormattedStatus(), customOrderContext.getFormattedButtonTitle());
                    return;
                } else {
                    this.m.showGreenCircleTitleBar(customOrderContext.getFormattedStatus(), customOrderContext.getFormattedButtonTitle());
                    return;
                }
            case 3:
                if (customOrderContext.isDraft()) {
                    this.m.showYellowCircleTitleBar(customOrderContext.getFormattedStatus(), customOrderContext.getFormattedButtonTitle());
                    return;
                } else {
                    this.m.showGreenCircleTitleBar(customOrderContext.getFormattedStatus(), customOrderContext.getFormattedButtonTitle());
                    return;
                }
            case 4:
                this.m.showCheckmarkTitleBar(customOrderContext.getFormattedStatus(), customOrderContext.getFormattedButtonTitle());
                return;
            case 5:
                this.m.hideTitleBar();
                return;
            case 6:
                this.m.hideTitleBar();
                return;
            default:
                return;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0014  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void h() {
        /*
            r3 = this;
            com.etsy.android.lib.models.convo.ConversationThread2 r0 = r3.f
            if (r0 == 0) goto L_0x000f
            com.etsy.android.lib.models.Conversation3 r0 = r0.getConversation()
            if (r0 == 0) goto L_0x000f
            com.etsy.android.lib.models.convo.context.ConvoContext r0 = r0.getConversationContext()
            goto L_0x0010
        L_0x000f:
            r0 = 0
        L_0x0010:
            boolean r1 = r0 instanceof com.etsy.android.lib.models.convo.context.CustomOrderContext
            if (r1 == 0) goto L_0x0074
            com.etsy.android.lib.models.convo.context.CustomOrderContext r0 = (com.etsy.android.lib.models.convo.context.CustomOrderContext) r0
            com.etsy.android.lib.models.convo.context.CustomOrderContext$Action r1 = r0.getActionType()
            int[] r2 = com.etsy.android.ui.convos.convoredesign.ag.b
            int r1 = r1.ordinal()
            r1 = r2[r1]
            switch(r1) {
                case 1: goto L_0x0036;
                case 2: goto L_0x0030;
                case 3: goto L_0x0026;
                default: goto L_0x0025;
            }
        L_0x0025:
            goto L_0x0074
        L_0x0026:
            com.etsy.android.ui.convos.convoredesign.ai r1 = r3.m
            com.etsy.android.lib.models.datatypes.EtsyId r0 = r0.getReceiptEtsyId()
            r1.navToReceipt(r0)
            goto L_0x0074
        L_0x0030:
            com.etsy.android.ui.convos.convoredesign.ai r0 = r3.m
            r0.navToCart()
            goto L_0x0074
        L_0x0036:
            java.lang.Long r0 = r0.getReservedListingID()
            java.lang.String r0 = java.lang.String.valueOf(r0)
            com.etsy.android.ui.convos.convoredesign.c r1 = r3.l
            io.reactivex.a r0 = r1.b(r0)
            com.etsy.android.lib.f.a r1 = r3.n
            io.reactivex.u r1 = r1.a()
            io.reactivex.a r0 = r0.b(r1)
            com.etsy.android.lib.f.a r1 = r3.n
            io.reactivex.u r1 = r1.b()
            io.reactivex.a r0 = r0.a(r1)
            java.lang.String r1 = "repository.addCustomOrde…(schedulers.mainThread())"
            kotlin.jvm.internal.p.a(r0, r1)
            com.etsy.android.ui.convos.convoredesign.ConvoThreadPresenter$onTitleBarButtonClick$1 r1 = new com.etsy.android.ui.convos.convoredesign.ConvoThreadPresenter$onTitleBarButtonClick$1
            r1.<init>(r3)
            kotlin.jvm.a.a r1 = (kotlin.jvm.a.a) r1
            com.etsy.android.ui.convos.convoredesign.ConvoThreadPresenter$onTitleBarButtonClick$2 r2 = new com.etsy.android.ui.convos.convoredesign.ConvoThreadPresenter$onTitleBarButtonClick$2
            r2.<init>(r3)
            kotlin.jvm.a.b r2 = (kotlin.jvm.a.b) r2
            io.reactivex.disposables.Disposable r0 = io.reactivex.rxkotlin.c.a(r0, r2, r1)
            io.reactivex.disposables.a r1 = r3.b
            io.reactivex.rxkotlin.a.a(r0, r1)
        L_0x0074:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.etsy.android.ui.convos.convoredesign.af.h():void");
    }

    public final void a(boolean z) {
        al alVar;
        ConversationThread2 conversationThread2 = this.f;
        if (conversationThread2 != null) {
            Conversation3 conversation = conversationThread2.getConversation();
            if (conversation != null) {
                boolean read = conversation.getRead();
                if (read) {
                    alVar = new com.etsy.android.ui.convos.convoredesign.al.d(this.o);
                } else {
                    alVar = new com.etsy.android.ui.convos.convoredesign.al.a(this.o);
                }
                io.reactivex.a a2 = this.l.a(alVar).b(this.n.a()).a(this.n.b());
                p.a((Object) a2, "repository.update(spec)\n…(schedulers.mainThread())");
                io.reactivex.rxkotlin.a.a(io.reactivex.rxkotlin.c.a(a2, (kotlin.jvm.a.b<? super Throwable, kotlin.h>) new ConvoThreadPresenter$toggleReadState$$inlined$let$lambda$2<Object,kotlin.h>(this, z), new ConvoThreadPresenter$toggleReadState$$inlined$let$lambda$1<>(read, this, z)), this.b);
            }
        }
    }

    public final void a(Bitmap bitmap, File file) {
        p.b(bitmap, "bitmap");
        p.b(file, ResponseConstants.FILE);
        c[] cVarArr = this.c;
        int i2 = 0;
        int length = cVarArr.length;
        while (true) {
            if (i2 >= length) {
                i2 = -1;
                break;
            } else if (cVarArr[i2] instanceof b) {
                break;
            } else {
                i2++;
            }
        }
        this.c[i2] = new a(file);
        this.m.addImageAttachment(bitmap, i2);
        p();
        q();
    }

    public final void i() {
        c[] cVarArr = this.c;
        int i2 = 0;
        int length = cVarArr.length;
        while (true) {
            if (i2 >= length) {
                i2 = -1;
                break;
            } else if (cVarArr[i2] instanceof C0091c) {
                break;
            } else {
                i2++;
            }
        }
        this.c[i2] = new b();
        this.m.showImageLoadingIndicator(i2);
        p();
    }

    public final void a(int i2) {
        c cVar = this.c[i2];
        this.c[i2] = new C0091c();
        if (cVar instanceof a) {
            ((a) cVar).a().delete();
        }
        this.m.removeImageAttachment(i2);
        p();
        q();
    }

    public final void j() {
        for (int i2 = 0; i2 < 3; i2++) {
            c cVar = this.c[i2];
            this.c[i2] = new C0091c();
            if (cVar instanceof a) {
                ((a) cVar).a().delete();
            }
            this.m.removeImageAttachment(i2);
        }
        p();
        q();
        this.e.a(r());
    }

    public final void a(File file) {
        c[] cVarArr = this.c;
        int i2 = 0;
        int length = cVarArr.length;
        while (true) {
            if (i2 >= length) {
                i2 = -1;
                break;
            } else if (cVarArr[i2] instanceof b) {
                break;
            } else {
                i2++;
            }
        }
        this.c[i2] = new C0091c();
        this.m.removeImageLoadingIndicator(i2);
        if (file != null) {
            file.delete();
        }
        q();
    }

    private final void p() {
        boolean z = false;
        int i2 = 0;
        for (c cVar : this.c) {
            if (cVar instanceof C0091c) {
                i2++;
            }
        }
        if (i2 > 0) {
            z = true;
        }
        this.m.updateImageAttachmentButton(z);
    }

    /* access modifiers changed from: private */
    public final void q() {
        boolean z = false;
        if (((this.e.d().length() > 0) || (!r().isEmpty())) && !this.d.isSending()) {
            z = true;
        }
        this.m.enableSend(z);
    }

    public final void k() {
        this.m.showLoadingDialog(R.string.marking_spam_convo);
        io.reactivex.a a2 = this.l.a((al) new com.etsy.android.ui.convos.convoredesign.al.b(this.o)).b(this.n.a()).a(this.n.b());
        p.a((Object) a2, "repository.update(TagSpe…(schedulers.mainThread())");
        io.reactivex.rxkotlin.a.a(io.reactivex.rxkotlin.c.a(a2, (kotlin.jvm.a.b<? super Throwable, kotlin.h>) new ConvoThreadPresenter$markAsSpam$2<Object,kotlin.h>(this), new ConvoThreadPresenter$markAsSpam$1<>(this)), this.b);
    }

    public final void l() {
        this.m.showLoadingDialog(R.string.deleting_convo);
        io.reactivex.a a2 = this.l.a((al) new com.etsy.android.ui.convos.convoredesign.al.c(this.o)).b(this.n.a()).a(this.n.b());
        p.a((Object) a2, "repository.update(TagSpe…(schedulers.mainThread())");
        io.reactivex.rxkotlin.a.a(io.reactivex.rxkotlin.c.a(a2, (kotlin.jvm.a.b<? super Throwable, kotlin.h>) new ConvoThreadPresenter$markAsTrash$2<Object,kotlin.h>(this), new ConvoThreadPresenter$markAsTrash$1<>(this)), this.b);
    }

    public final void a(String str, int i2, int i3) {
        p.b(str, "message");
        DraftMessage draftMessage = this.e;
        this.e.a(str);
        draftMessage.a(i2, i3);
        q();
    }

    public final void a(ArrayList<DraftMessage> arrayList) {
        p.b(arrayList, "unsentList");
        this.d.setSending(true);
        IntRef intRef = new IntRef();
        intRef.element = arrayList.size();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            DraftMessage draftMessage = (DraftMessage) it.next();
            draftMessage.a(Status.SENDING);
            c cVar = this.l;
            p.a((Object) draftMessage, Listing.DRAFT_STATE);
            io.reactivex.a a2 = cVar.a(draftMessage).b(this.n.a()).a(this.n.b());
            p.a((Object) a2, "repository\n             …(schedulers.mainThread())");
            io.reactivex.rxkotlin.a.a(io.reactivex.rxkotlin.c.a(a2, (kotlin.jvm.a.b<? super Throwable, kotlin.h>) new ConvoThreadPresenter$sendUnsentDrafts$2<Object,kotlin.h>(this, intRef), new ConvoThreadPresenter$sendUnsentDrafts$1<>(this, arrayList, draftMessage, intRef)), this.b);
        }
    }

    public final void m() {
        ConversationThread2 conversationThread2 = this.f;
        if (conversationThread2 != null && !this.d.isSending()) {
            if (this.e.a()) {
                this.e.a(this.o.getIdAsLong());
                DraftMessage draftMessage = this.e;
                ConvoUser otherUser = conversationThread2.getConversation().getOtherUser();
                draftMessage.b(otherUser != null ? otherUser.getUsername() : null);
                draftMessage.a(r());
            }
            if (!r().isEmpty()) {
                this.m.showLoadingDialog(R.string.uploading_images);
            }
            this.e.a(Status.SENDING);
            this.d.setSending(true);
            if (this.r.b()) {
                a(this.g);
                io.reactivex.a a2 = this.l.a(this.e).b(this.n.a()).a(this.n.b());
                p.a((Object) a2, "repository\n             …(schedulers.mainThread())");
                io.reactivex.rxkotlin.a.a(io.reactivex.rxkotlin.c.a(a2, null, new ConvoThreadPresenter$sendMessage$2(this), 1, null), this.b);
            } else {
                this.g.add(DraftMessage.a(this.e, 0, null, null, false, 0, 0, null, 0, null, FrameMetricsAggregator.EVERY_DURATION, null));
                this.d.setSending(false);
                this.e.a(Status.IN_DRAFT);
                q();
                this.m.addDraftToAdapter(this.e.b());
                this.m.clearMessageInput();
            }
            q();
        }
    }

    private final List<File> r() {
        c[] cVarArr = this.c;
        Collection arrayList = new ArrayList();
        for (c cVar : cVarArr) {
            if (cVar instanceof a) {
                arrayList.add(cVar);
            }
        }
        Iterable<c> iterable = (List) arrayList;
        Collection arrayList2 = new ArrayList(o.a(iterable, 10));
        for (c cVar2 : iterable) {
            if (cVar2 == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.etsy.android.ui.convos.convoredesign.ConvoThreadPresenter.ImageLoadingState.Loaded");
            }
            arrayList2.add(((a) cVar2).a());
        }
        return (List) arrayList2;
    }

    public final void n() {
        q a2 = this.t.a().b(this.n.a()).a(this.n.b());
        p.a((Object) a2, "convoNotificationRepo.ge…(schedulers.mainThread())");
        this.h = io.reactivex.rxkotlin.c.a(a2, (kotlin.jvm.a.b) ConvoThreadPresenter$startListeningForNotifications$2.INSTANCE, (kotlin.jvm.a.a) null, new ConvoThreadPresenter$startListeningForNotifications$1(this), 2, (Object) null);
    }

    public final void o() {
        Disposable disposable = this.h;
        if (disposable != null) {
            disposable.dispose();
        }
    }
}
