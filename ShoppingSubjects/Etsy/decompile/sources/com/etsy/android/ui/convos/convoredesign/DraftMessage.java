package com.etsy.android.ui.convos.convoredesign;

import com.etsy.android.R;
import com.etsy.android.lib.models.ConversationMessage2;
import com.etsy.android.lib.models.ImageInfo;
import com.etsy.android.ui.convos.convoredesign.aj.b;
import com.etsy.android.ui.convos.convoredesign.o.a;
import com.etsy.android.uikit.adapter.AbstractContextRecyclerViewAdapter;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.p;

/* compiled from: DraftMessage.kt */
public final class DraftMessage {
    private final List<ImageInfo> a;
    private long b;
    private String c;
    private String d;
    private final boolean e;
    private int f;
    private int g;
    private Status h;
    private long i;
    private List<? extends File> j;

    /* compiled from: DraftMessage.kt */
    public enum Status {
        IN_DRAFT(R.string.convo_status_draft),
        SENDING(R.string.convo_status_sending),
        FAILED(R.string.convo_status_failed);
        
        private int resId;

        protected Status(int i) {
            this.resId = i;
        }

        public final int getResId() {
            return this.resId;
        }

        /* access modifiers changed from: protected */
        public final void setResId(int i) {
            this.resId = i;
        }
    }

    public static /* bridge */ /* synthetic */ DraftMessage a(DraftMessage draftMessage, long j2, String str, String str2, boolean z, int i2, int i3, Status status, long j3, List list, int i4, Object obj) {
        DraftMessage draftMessage2 = draftMessage;
        int i5 = i4;
        return draftMessage2.a((i5 & 1) != 0 ? draftMessage2.b : j2, (i5 & 2) != 0 ? draftMessage2.c : str, (i5 & 4) != 0 ? draftMessage2.d : str2, (i5 & 8) != 0 ? draftMessage2.e : z, (i5 & 16) != 0 ? draftMessage2.f : i2, (i5 & 32) != 0 ? draftMessage2.g : i3, (i5 & 64) != 0 ? draftMessage2.h : status, (i5 & 128) != 0 ? draftMessage2.i : j3, (i5 & 256) != 0 ? draftMessage2.j : list);
    }

    public final DraftMessage a(long j2, String str, String str2, boolean z, int i2, int i3, Status status, long j3, List<? extends File> list) {
        String str3 = str;
        p.b(str3, "message");
        Status status2 = status;
        p.b(status2, "status");
        List<? extends File> list2 = list;
        p.b(list2, "imageAttachments");
        DraftMessage draftMessage = new DraftMessage(j2, str3, str2, z, i2, i3, status2, j3, list2);
        return draftMessage;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof DraftMessage) {
            DraftMessage draftMessage = (DraftMessage) obj;
            if ((this.b == draftMessage.b) && p.a((Object) this.c, (Object) draftMessage.c) && p.a((Object) this.d, (Object) draftMessage.d)) {
                if (this.e == draftMessage.e) {
                    if (this.f == draftMessage.f) {
                        if ((this.g == draftMessage.g) && p.a((Object) this.h, (Object) draftMessage.h)) {
                            return ((this.i > draftMessage.i ? 1 : (this.i == draftMessage.i ? 0 : -1)) == 0) && p.a((Object) this.j, (Object) draftMessage.j);
                        }
                    }
                }
            }
        }
    }

    public int hashCode() {
        long j2 = this.b;
        int i2 = ((int) (j2 ^ (j2 >>> 32))) * 31;
        String str = this.c;
        int i3 = 0;
        int hashCode = (i2 + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.d;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        boolean z = this.e;
        if (z) {
            z = true;
        }
        int i4 = (((((hashCode2 + (z ? 1 : 0)) * 31) + this.f) * 31) + this.g) * 31;
        Status status = this.h;
        int hashCode3 = (i4 + (status != null ? status.hashCode() : 0)) * 31;
        long j3 = this.i;
        int i5 = (hashCode3 + ((int) (j3 ^ (j3 >>> 32)))) * 31;
        List<? extends File> list = this.j;
        if (list != null) {
            i3 = list.hashCode();
        }
        return i5 + i3;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("DraftMessage(convoId=");
        sb.append(this.b);
        sb.append(", message=");
        sb.append(this.c);
        sb.append(", userName=");
        sb.append(this.d);
        sb.append(", hasAttachment=");
        sb.append(this.e);
        sb.append(", cursorStartPosition=");
        sb.append(this.f);
        sb.append(", cursorEndPosition=");
        sb.append(this.g);
        sb.append(", status=");
        sb.append(this.h);
        sb.append(", _creationDate=");
        sb.append(this.i);
        sb.append(", imageAttachments=");
        sb.append(this.j);
        sb.append(")");
        return sb.toString();
    }

    public DraftMessage(long j2, String str, String str2, boolean z, int i2, int i3, Status status, long j3, List<? extends File> list) {
        p.b(str, "message");
        p.b(status, "status");
        p.b(list, "imageAttachments");
        this.b = j2;
        this.c = str;
        this.d = str2;
        this.e = z;
        this.f = i2;
        this.g = i3;
        this.h = status;
        this.i = j3;
        this.j = list;
        this.a = new ArrayList(3);
    }

    public final void a(long j2) {
        this.b = j2;
    }

    public final long c() {
        return this.b;
    }

    public final void a(String str) {
        p.b(str, "<set-?>");
        this.c = str;
    }

    public final String d() {
        return this.c;
    }

    public final void b(String str) {
        this.d = str;
    }

    public /* synthetic */ DraftMessage(long j2, String str, String str2, boolean z, int i2, int i3, Status status, long j3, List list, int i4, o oVar) {
        int i5 = i4;
        this(j2, str, str2, z, (i5 & 16) != 0 ? 0 : i2, (i5 & 32) != 0 ? 0 : i3, (i5 & 64) != 0 ? Status.IN_DRAFT : status, (i5 & 128) != 0 ? 0 : j3, (i5 & 256) != 0 ? new ArrayList(3) : list);
    }

    public final void a(Status status) {
        p.b(status, "<set-?>");
        this.h = status;
    }

    public final void a(List<? extends File> list) {
        p.b(list, "<set-?>");
        this.j = list;
    }

    public final List<File> e() {
        return this.j;
    }

    public final boolean a() {
        return this.h == Status.IN_DRAFT;
    }

    public final DraftMessage a(int i2, int i3) {
        this.f = i2;
        this.g = i3;
        return this;
    }

    public final o b() {
        b bVar = new b(this.c);
        List<ImageInfo> list = this.a;
        ConversationMessage2 conversationMessage2 = r3;
        ConversationMessage2 conversationMessage22 = new ConversationMessage2(0, 0, 0, this.c, null, null, false, 0, null, AbstractContextRecyclerViewAdapter.VIEW_TYPE_HEADER, null);
        ak a2 = p.a(conversationMessage2, (ak) null);
        return new a(p.a(a2, (aj) bVar), list, a2);
    }
}
