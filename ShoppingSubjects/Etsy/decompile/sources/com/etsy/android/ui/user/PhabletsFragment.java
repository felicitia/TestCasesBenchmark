package com.etsy.android.ui.user;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.etsy.android.R;
import com.etsy.android.lib.core.img.c;
import com.etsy.android.lib.logger.AnalyticsLogAttribute;
import com.etsy.android.lib.logger.i;
import com.etsy.android.lib.logger.l;
import com.etsy.android.lib.models.BaseModelImage;
import com.etsy.android.lib.models.IFullImage;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.datatypes.EtsyDeepLinkId;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.ui.nav.e;
import com.etsy.android.uikit.BaseRecyclerViewListFragment;
import com.etsy.android.uikit.adapter.BaseRecyclerViewAdapter;
import com.etsy.android.uikit.view.FullImageView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kotlin.TypeCastException;
import kotlin.collections.o;
import kotlin.jvm.internal.p;

/* compiled from: PhabletsFragment.kt */
public final class PhabletsFragment extends BaseRecyclerViewListFragment<a> implements com.etsy.android.lib.core.b.a {
    private HashMap _$_findViewCache;
    public l log;

    /* compiled from: PhabletsFragment.kt */
    public static final class PhabletAdapter extends BaseRecyclerViewAdapter<a> {
        private final a mOnPhabletClickListener;

        /* compiled from: PhabletsFragment.kt */
        public interface a {
            void a(a aVar);
        }

        /* compiled from: PhabletsFragment.kt */
        private static final class b extends ViewHolder {
            private final FullImageView a;
            private final TextView b;
            private final TextView c;

            /* compiled from: PhabletsFragment.kt */
            static final class a implements OnClickListener {
                final /* synthetic */ a a;
                final /* synthetic */ a b;

                a(a aVar, a aVar2) {
                    this.a = aVar;
                    this.b = aVar2;
                }

                public final void onClick(View view) {
                    a aVar = this.a;
                    if (aVar != null) {
                        aVar.a(this.b);
                    }
                }
            }

            public b(View view) {
                p.b(view, "root");
                super(view);
                View findViewById = view.findViewById(R.id.phablet_avatar);
                if (findViewById == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.etsy.android.uikit.view.FullImageView");
                }
                this.a = (FullImageView) findViewById;
                View findViewById2 = view.findViewById(R.id.phablet_name);
                if (findViewById2 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type android.widget.TextView");
                }
                this.b = (TextView) findViewById2;
                View findViewById3 = view.findViewById(R.id.phablet_etsy_username);
                if (findViewById3 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type android.widget.TextView");
                }
                this.c = (TextView) findViewById3;
            }

            public final void a(a aVar, c cVar, a aVar2) {
                p.b(cVar, "imageBatch");
                TextView textView = this.b;
                if (aVar == null) {
                    p.a();
                }
                textView.setText(aVar.b());
                this.c.setText(aVar.c());
                this.a.setImageInfo(aVar.a(), cVar, 1);
                this.itemView.setOnClickListener(new a(aVar2, aVar));
            }
        }

        /* access modifiers changed from: protected */
        public int getListItemViewType(int i) {
            return 0;
        }

        public PhabletAdapter(FragmentActivity fragmentActivity, c cVar, a aVar) {
            p.b(fragmentActivity, ResponseConstants.CONTEXT);
            p.b(cVar, "imageBatch");
            p.b(aVar, "mOnPhabletClickListener");
            super(fragmentActivity, cVar);
            this.mOnPhabletClickListener = aVar;
        }

        /* access modifiers changed from: protected */
        public ViewHolder onCreateListItemViewHolder(ViewGroup viewGroup, int i) {
            p.b(viewGroup, ResponseConstants.PARENT);
            View inflate = this.mInflater.inflate(R.layout.list_item_phablet, viewGroup, false);
            p.a((Object) inflate, "mInflater.inflate(R.layo…m_phablet, parent, false)");
            return new b(inflate);
        }

        /* access modifiers changed from: protected */
        public void onBindListItemViewHolder(ViewHolder viewHolder, int i) {
            p.b(viewHolder, "holder");
            b bVar = (b) viewHolder;
            a aVar = (a) getItem(i);
            c cVar = this.mImageBatch;
            p.a((Object) cVar, "mImageBatch");
            bVar.a(aVar, cVar, this.mOnPhabletClickListener);
        }
    }

    /* compiled from: PhabletsFragment.kt */
    public static final class a implements i, Comparable<a> {
        private final IFullImage a;
        private final String b;
        private final String c;

        public a(String str, final String str2, String str3) {
            p.b(str, ResponseConstants.NAME);
            p.b(str2, "imageUrl");
            p.b(str3, ResponseConstants.USERNAME);
            this.b = str;
            this.c = str3;
            this.a = new IFullImage() {
                public int getFullHeight() {
                    return -1;
                }

                public int getFullWidth() {
                    return -1;
                }

                public int getImageColor() {
                    return BaseModelImage.DEFAULT_LOADING_COLOR;
                }

                public String get4to3ImageUrlForPixelWidth(int i) {
                    return str2;
                }

                public String getFullHeightImageUrlForPixelWidth(int i) {
                    return str2;
                }
            };
        }

        public final String b() {
            return this.b;
        }

        public final String c() {
            return this.c;
        }

        public final IFullImage a() {
            return this.a;
        }

        public boolean equals(Object obj) {
            return obj != null && (obj instanceof a) && p.a((Object) this.b, (Object) ((a) obj).b);
        }

        public int hashCode() {
            return this.b.hashCode();
        }

        /* renamed from: a */
        public int compareTo(a aVar) {
            p.b(aVar, ResponseConstants.OTHER);
            return this.b.compareTo(aVar.b);
        }

        public HashMap<AnalyticsLogAttribute, Object> getTrackingParameters() {
            HashMap<AnalyticsLogAttribute, Object> hashMap = new HashMap<>(1);
            hashMap.put(AnalyticsLogAttribute.TARGET_USERNAME, this.c);
            return hashMap;
        }
    }

    /* compiled from: PhabletsFragment.kt */
    public static final class b implements a {
        final /* synthetic */ PhabletsFragment a;

        b(PhabletsFragment phabletsFragment) {
            this.a = phabletsFragment;
        }

        public void a(a aVar) {
            EtsyDeepLinkId etsyDeepLinkId = new EtsyDeepLinkId();
            if (aVar == null) {
                p.a();
            }
            etsyDeepLinkId.setName(aVar.c());
            e.a(this.a.getActivity()).a().c((EtsyId) etsyDeepLinkId);
            if (p.a((Object) "qsavit", (Object) aVar.c())) {
                Toast.makeText(this.a.getActivity(), "Hi, future Phablet!", 0).show();
            }
        }
    }

    public void _$_clearFindViewByIdCache() {
        if (this._$_findViewCache != null) {
            this._$_findViewCache.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view == null) {
            View view2 = getView();
            if (view2 == null) {
                return null;
            }
            view = view2.findViewById(i);
            this._$_findViewCache.put(Integer.valueOf(i), view);
        }
        return view;
    }

    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    public final l getLog() {
        l lVar = this.log;
        if (lVar == null) {
            p.b("log");
        }
        return lVar;
    }

    public final void setLog(l lVar) {
        p.b(lVar, "<set-?>");
        this.log = lVar;
    }

    /* access modifiers changed from: protected */
    public LayoutManager createLayoutManager() {
        Context context = getContext();
        Context context2 = getContext();
        p.a((Object) context2, ResponseConstants.CONTEXT);
        return new GridLayoutManager(context, context2.getResources().getInteger(R.integer.phablet_cards_grid_columns));
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        FragmentActivity activity = getActivity();
        p.a((Object) activity, "activity");
        c imageBatch = getImageBatch();
        p.a((Object) imageBatch, "imageBatch");
        this.mAdapter = new PhabletAdapter(activity, imageBatch, new b(this));
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        l lVar = this.log;
        if (lVar == null) {
            p.b("log");
        }
        lVar.c("onActivityCreated Called");
        int dimensionPixelOffset = getResources().getDimensionPixelOffset(R.dimen.fixed_large);
        this.mRecyclerView.setPadding(dimensionPixelOffset, dimensionPixelOffset, dimensionPixelOffset, dimensionPixelOffset);
        loadContent();
    }

    /* access modifiers changed from: protected */
    public void onLoadContent() {
        onLoadSuccess(createPhabletList(), 0);
    }

    private final List<a> createPhabletList() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new a("Cameron K.", "https://img1.etsystatic.com/035/0/39000148/ist_fullxfull.624779421_j378g7vt.jpg", "cnketcham"));
        arrayList.add(new a("Mike V.", "https://img0.etsystatic.com/039/0/38791293/ist_fullxfull.547795516_ci55nx19.jpg", "optimike"));
        arrayList.add(new a("Scott B.", "https://img0.etsystatic.com/038/0/10660248/ist_fullxfull.624655666_sove0r5d.jpg", "sbirksted"));
        arrayList.add(new a("Jamie H.", "https://img0.etsystatic.com/043/0/21997824/ist_fullxfull.592917636_stt3xsar.jpg", "jamiehuson"));
        arrayList.add(new a("Quinn S.", "https://img0.etsystatic.com/027/0/46418940/ist_fullxfull.630494644_qk5i8182.jpg", "qsavit"));
        arrayList.add(new a("Hannah M.", "https://img0.etsystatic.com/023/0/27836589/ist_fullxfull.527611068_cvoll88j.jpg", "hannahmitt"));
        arrayList.add(new a("Chris J.", "https://img0.etsystatic.com/060/0/19835214/ist_fullxfull.708055870_o2tdncny.jpg", "cojohn"));
        arrayList.add(new a("Nick K.", "https://img1.etsystatic.com/067/0/68267642/ist_fullxfull.802120701_3hqjd57b.jpg", "nkonecny"));
        arrayList.add(new a("Nishan S.", "https://img0.etsystatic.com/031/0/44591123/ist_fullxfull.566005860_arje7aoj.jpg", "xerox357"));
        arrayList.add(new a("Richard L.", "https://img0.etsystatic.com/101/0/49065648/ist_fullxfull.908762560_kkis1t0f.jpg", "rwliang"));
        arrayList.add(new a("Adrian P.", "https://img0.etsystatic.com/133/0/75072899/ist_fullxfull.858289984_bwxbifph.jpg", "parsonsadrian"));
        arrayList.add(new a("Katrina W.", "https://img1.etsystatic.com/127/0/85034308/ist_fullxfull.1018773653_qjhyt2tz.jpg", "akwalser"));
        arrayList.add(new a("Amelia R.", "https://img0.etsystatic.com/120/0/6252399/ist_fullxfull.1023393558_dc1637ui.jpg", "brunerson"));
        arrayList.add(new a("Phoebe F.", "https://img0.etsystatic.com/103/0/14066997/ist_fullxfull.1019607684_5fuceyeu.jpg", "pf211"));
        arrayList.add(new a("Patrick C.", "https://img1.etsystatic.com/150/0/96246648/ist_fullxfull.1120479699_hvdhxr9m.jpg", "cousinspatrick"));
        arrayList.add(new a("Augusto P.", "https://img1.etsystatic.com/217/0/136539152/ist_fullxfull.1449414313_k28vqqii.jpg", "apedroza"));
        arrayList.add(new a("Mike B.", "https://img1.etsystatic.com/197/0/12206405/ist_fullxfull.1468325393_pgl35el6.jpg", "mikeburns"));
        arrayList.add(new a("Alex S.", "https://img0.etsystatic.com/207/0/5238284/ist_fullxfull.1421060852_gywgfjo3.jpg", "asullivan"));
        arrayList.add(new a("Neha H.", "https://img1.etsystatic.com/189/0/14500566/ist_fullxfull.1515340897_226rvl72.jpg", "nheera"));
        arrayList.add(new a("Kate K.", "https://img.etsystatic.com/ist/e658c2/1522304371/ist_fullxfull.1522304371_ldfniz1a.jpg", "mparib48"));
        arrayList.add(new a("Jayesh K.", "https://img.etsystatic.com/ist/4ae6fc/1522351368/ist_fullxfull.1522351368_j2bzi1uq.jpg", "c1ldrdiw"));
        arrayList.add(new a("Andrew C.", "https://atlas.etsycorp.com/images/avatars/noface.png", "achoi"));
        arrayList.add(new a("Filip M.", "https://img.etsystatic.com/ist/c36aef/1583433460/ist_fullxfull.1583433460_rk6rsb47.jpg?version=0", "filipmaj"));
        arrayList.add(new a("John K.", "https://img.etsystatic.com/ist/5dec7a/1636815925/ist_fullxfull.1636815925_5m3weima.jpg?version=0", "jkalmi"));
        List<a> list = arrayList;
        o.c(list);
        return list;
    }
}
