package com.etsy.android.vespa;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.RecyclerView.RecycledViewPool;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.etsy.android.lib.a.i;
import com.etsy.android.lib.a.j;
import com.etsy.android.lib.logger.b;
import com.etsy.android.lib.logger.f;
import com.etsy.android.vespa.a.a;
import com.etsy.android.vespa.a.d;
import com.etsy.android.vespa.a.e;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: BaseViewHolderFactory */
public class c<K extends Context> {
    private ArrayList<c> a;
    @Deprecated
    protected LayoutInflater b;
    /* access modifiers changed from: protected */
    public K c;
    protected com.etsy.android.lib.core.img.c d;
    @NonNull
    protected SparseArrayCompat<b> e;
    @NonNull
    protected b f;
    @Nullable
    protected f g;
    @Nullable
    protected d h;
    private RecycledViewPool i;
    private RecycledViewPool j;

    public c(K k, @NonNull b bVar, @Nullable d dVar, @Nullable f fVar) {
        this.e = new SparseArrayCompat<>();
        this.a = new ArrayList<>();
        this.h = null;
        this.c = k;
        this.h = dVar;
        this.g = fVar;
        this.d = new com.etsy.android.lib.core.img.c();
        this.b = LayoutInflater.from(this.c);
        this.f = bVar;
        this.i = new RecycledViewPool();
        a();
    }

    public c(K k, @NonNull b bVar, @Nullable d dVar) {
        this(k, bVar, dVar, null);
    }

    public c(K k, @NonNull b bVar) {
        this(k, bVar, null);
    }

    /* access modifiers changed from: protected */
    public void a() {
        this.e.put(i.view_type_message_card, new e((FragmentActivity) this.c, this.f));
        if (this.g != null) {
            a aVar = new a((FragmentActivity) this.c, this.f, this.g, this.h);
            this.e.put(i.view_type_hero_banner, aVar);
            this.e.put(i.view_type_icon_banner, aVar);
            this.e.put(i.view_type_full_bleed_banner, aVar);
            this.e.put(i.view_type_section_link_footer, new d((FragmentActivity) this.c, this.f));
        }
    }

    /* JADX WARNING: type inference failed for: r8v1, types: [com.etsy.android.vespa.viewholders.BaseViewHolder] */
    /* JADX WARNING: type inference failed for: r8v2, types: [com.etsy.android.vespa.viewholders.PlaceholderViewHolder] */
    /* JADX WARNING: type inference failed for: r1v11, types: [com.etsy.android.vespa.viewholders.ListSectionLinkFooterViewHolder] */
    /* JADX WARNING: type inference failed for: r8v9, types: [com.etsy.android.vespa.viewholders.HTMLTextViewHolder] */
    /* JADX WARNING: type inference failed for: r0v3, types: [com.etsy.android.vespa.viewholders.FullBleedBannerViewHolder] */
    /* JADX WARNING: type inference failed for: r0v4, types: [com.etsy.android.vespa.viewholders.IconBannerViewHolder] */
    /* JADX WARNING: type inference failed for: r0v5, types: [com.etsy.android.vespa.viewholders.HeroBannerViewHolder] */
    /* JADX WARNING: type inference failed for: r8v16, types: [com.etsy.android.vespa.viewholders.LoadingCardViewHolder] */
    /* JADX WARNING: type inference failed for: r0v6 */
    /* JADX WARNING: type inference failed for: r0v7, types: [com.etsy.android.vespa.viewholders.MessageCardViewHolder] */
    /* JADX WARNING: type inference failed for: r8v20, types: [com.etsy.android.vespa.viewholders.ListSectionHeaderViewHolder] */
    /* JADX WARNING: type inference failed for: r0v9 */
    /* JADX WARNING: type inference failed for: r0v10 */
    /* JADX WARNING: type inference failed for: r0v11 */
    /* JADX WARNING: type inference failed for: r0v12 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 10 */
    @android.support.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.etsy.android.vespa.viewholders.BaseViewHolder a(android.view.ViewGroup r7, int r8) {
        /*
            r6 = this;
            boolean r0 = r6.a(r7)
            java.util.ArrayList<com.etsy.android.vespa.c> r1 = r6.a
            java.util.Iterator r1 = r1.iterator()
        L_0x000a:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x001d
            java.lang.Object r2 = r1.next()
            com.etsy.android.vespa.c r2 = (com.etsy.android.vespa.c) r2
            com.etsy.android.vespa.viewholders.BaseViewHolder r2 = r2.a(r7, r8)
            if (r2 == 0) goto L_0x000a
            return r2
        L_0x001d:
            int r1 = com.etsy.android.lib.a.i.view_type_section_header
            if (r8 != r1) goto L_0x0028
            com.etsy.android.vespa.viewholders.ListSectionHeaderViewHolder r8 = new com.etsy.android.vespa.viewholders.ListSectionHeaderViewHolder
            r8.<init>(r7)
            goto L_0x00c5
        L_0x0028:
            int r1 = com.etsy.android.lib.a.i.view_type_message_card
            if (r8 != r1) goto L_0x003c
            com.etsy.android.vespa.viewholders.MessageCardViewHolder r0 = new com.etsy.android.vespa.viewholders.MessageCardViewHolder
            android.support.v4.util.SparseArrayCompat<com.etsy.android.vespa.b> r1 = r6.e
            java.lang.Object r8 = r1.get(r8)
            com.etsy.android.vespa.b r8 = (com.etsy.android.vespa.b) r8
            r0.<init>(r7, r8)
        L_0x0039:
            r8 = r0
            goto L_0x00c5
        L_0x003c:
            int r1 = com.etsy.android.lib.a.i.view_type_loading
            if (r8 != r1) goto L_0x0047
            com.etsy.android.vespa.viewholders.LoadingCardViewHolder r8 = new com.etsy.android.vespa.viewholders.LoadingCardViewHolder
            r8.<init>(r7)
            goto L_0x00c5
        L_0x0047:
            int r1 = com.etsy.android.lib.a.i.view_type_hero_banner
            if (r8 != r1) goto L_0x005b
            com.etsy.android.vespa.viewholders.HeroBannerViewHolder r0 = new com.etsy.android.vespa.viewholders.HeroBannerViewHolder
            android.support.v4.util.SparseArrayCompat<com.etsy.android.vespa.b> r1 = r6.e
            java.lang.Object r8 = r1.get(r8)
            com.etsy.android.vespa.a.a r8 = (com.etsy.android.vespa.a.a) r8
            com.etsy.android.lib.core.img.c r1 = r6.d
            r0.<init>(r7, r8, r1)
            goto L_0x0039
        L_0x005b:
            int r1 = com.etsy.android.lib.a.i.view_type_icon_banner
            if (r8 != r1) goto L_0x006f
            com.etsy.android.vespa.viewholders.IconBannerViewHolder r0 = new com.etsy.android.vespa.viewholders.IconBannerViewHolder
            android.support.v4.util.SparseArrayCompat<com.etsy.android.vespa.b> r1 = r6.e
            java.lang.Object r8 = r1.get(r8)
            com.etsy.android.vespa.a.a r8 = (com.etsy.android.vespa.a.a) r8
            com.etsy.android.lib.core.img.c r1 = r6.d
            r0.<init>(r7, r8, r1)
            goto L_0x0039
        L_0x006f:
            int r1 = com.etsy.android.lib.a.i.view_type_full_bleed_banner
            if (r8 != r1) goto L_0x0083
            com.etsy.android.vespa.viewholders.FullBleedBannerViewHolder r0 = new com.etsy.android.vespa.viewholders.FullBleedBannerViewHolder
            android.support.v4.util.SparseArrayCompat<com.etsy.android.vespa.b> r1 = r6.e
            java.lang.Object r8 = r1.get(r8)
            com.etsy.android.vespa.a.a r8 = (com.etsy.android.vespa.a.a) r8
            com.etsy.android.lib.logger.b r1 = r6.f
            r0.<init>(r7, r8, r1)
            goto L_0x0039
        L_0x0083:
            int r1 = com.etsy.android.lib.a.i.view_type_html_text
            if (r8 != r1) goto L_0x008d
            com.etsy.android.vespa.viewholders.HTMLTextViewHolder r8 = new com.etsy.android.vespa.viewholders.HTMLTextViewHolder
            r8.<init>(r7)
            goto L_0x00c5
        L_0x008d:
            int r1 = com.etsy.android.lib.a.i.view_type_horizontal_list_section
            if (r8 != r1) goto L_0x00ad
            android.support.v7.widget.RecyclerView$RecycledViewPool r8 = r6.j
            if (r8 != 0) goto L_0x009c
            android.support.v7.widget.RecyclerView$RecycledViewPool r8 = new android.support.v7.widget.RecyclerView$RecycledViewPool
            r8.<init>()
            r6.j = r8
        L_0x009c:
            com.etsy.android.vespa.viewholders.HorizontalListSectionViewHolder r8 = new com.etsy.android.vespa.viewholders.HorizontalListSectionViewHolder
            K r0 = r6.c
            r1 = r0
            android.support.v4.app.FragmentActivity r1 = (android.support.v4.app.FragmentActivity) r1
            com.etsy.android.lib.logger.b r3 = r6.f
            r4 = 1
            r0 = r8
            r2 = r7
            r5 = r6
            r0.<init>(r1, r2, r3, r4, r5)
            goto L_0x00c5
        L_0x00ad:
            int r1 = com.etsy.android.lib.a.i.view_type_section_link_footer
            if (r8 != r1) goto L_0x00c0
            com.etsy.android.vespa.viewholders.ListSectionLinkFooterViewHolder r1 = new com.etsy.android.vespa.viewholders.ListSectionLinkFooterViewHolder
            android.support.v4.util.SparseArrayCompat<com.etsy.android.vespa.b> r2 = r6.e
            java.lang.Object r8 = r2.get(r8)
            com.etsy.android.vespa.b r8 = (com.etsy.android.vespa.b) r8
            r1.<init>(r7, r8, r0)
            r8 = r1
            goto L_0x00c5
        L_0x00c0:
            com.etsy.android.vespa.viewholders.PlaceholderViewHolder r8 = new com.etsy.android.vespa.viewholders.PlaceholderViewHolder
            r8.<init>(r7)
        L_0x00c5:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.etsy.android.vespa.c.a(android.view.ViewGroup, int):com.etsy.android.vespa.viewholders.BaseViewHolder");
    }

    public int a(int i2, int i3) {
        int c2 = c();
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            int a2 = ((c) it.next()).a(i2, i3);
            if (a2 < c2) {
                return a2;
            }
        }
        return c2;
    }

    public final int c() {
        return this.c.getResources().getInteger(j.vespa_grid_layout_max_span);
    }

    public int a(k kVar) {
        return kVar.getViewType();
    }

    public void a(int i2, b bVar) {
        this.e.put(i2, bVar);
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            ((c) it.next()).a(i2, bVar);
        }
    }

    /* access modifiers changed from: protected */
    @Nullable
    public b b(int i2) {
        return (b) this.e.get(i2);
    }

    public void a(c cVar) {
        cVar.a(this.i);
        this.a.add(cVar);
    }

    public com.etsy.android.lib.core.img.c d() {
        return this.d;
    }

    /* access modifiers changed from: protected */
    public boolean a(@NonNull ViewGroup viewGroup) {
        if (viewGroup instanceof RecyclerView) {
            LayoutManager layoutManager = ((RecyclerView) viewGroup).getLayoutManager();
            if (layoutManager != null) {
                return layoutManager.canScrollHorizontally();
            }
        }
        return false;
    }

    public RecycledViewPool e() {
        return this.i;
    }

    public RecycledViewPool f() {
        return this.j;
    }

    public void a(RecycledViewPool recycledViewPool) {
        this.i = recycledViewPool;
    }

    public int a(int i2) {
        Iterator it = this.a.iterator();
        int i3 = 0;
        while (it.hasNext()) {
            i3 = ((c) it.next()).a(i2);
            if (i3 != 0) {
                break;
            }
        }
        if (i3 == 0) {
            f.f("Horizontal section height was zero. Did you override the getHorizontalSectionHeightForViewType method in your ViewHolderFactory?");
        }
        return i3;
    }
}
