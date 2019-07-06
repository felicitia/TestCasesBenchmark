package com.etsy.android.ui.shophome;

/* compiled from: SectionedShopHomeFragment_MembersInjector */
public final class a implements dagger.a<SectionedShopHomeFragment> {
    static final /* synthetic */ boolean a = true;
    private final javax.a.a<com.etsy.android.lib.util.b.a> b;

    public a(javax.a.a<com.etsy.android.lib.util.b.a> aVar) {
        if (a || aVar != null) {
            this.b = aVar;
            return;
        }
        throw new AssertionError();
    }

    public static dagger.a<SectionedShopHomeFragment> a(javax.a.a<com.etsy.android.lib.util.b.a> aVar) {
        return new a(aVar);
    }

    /* renamed from: a */
    public void injectMembers(SectionedShopHomeFragment sectionedShopHomeFragment) {
        if (sectionedShopHomeFragment == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        sectionedShopHomeFragment.fileSupport = (com.etsy.android.lib.util.b.a) this.b.b();
    }
}
