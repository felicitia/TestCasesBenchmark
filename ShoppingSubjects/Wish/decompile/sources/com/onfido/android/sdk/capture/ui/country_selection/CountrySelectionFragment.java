package com.onfido.android.sdk.capture.ui.country_selection;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.SearchView.OnQueryTextListener;
import android.support.v7.widget.SearchView.SearchAutoComplete;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import com.onfido.android.sdk.capture.DocumentType;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.common.SdkController;
import com.onfido.android.sdk.capture.ui.BaseFragment;
import com.onfido.android.sdk.capture.ui.NextActionListener;
import com.onfido.android.sdk.capture.ui.country_selection.CountrySelectionPresenter.View;
import com.onfido.android.sdk.capture.ui.widget.RecyclerView;
import com.onfido.android.sdk.capture.utils.BottomSheetExtensionsKt;
import com.onfido.android.sdk.capture.utils.CountryCode;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.TypeCastException;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;

public final class CountrySelectionFragment extends BaseFragment implements OnQueryTextListener, CountrySelectionListener, View {
    public static final Companion Companion = new Companion(null);
    static final /* synthetic */ KProperty[] a = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(CountrySelectionFragment.class), "handler", "getHandler()Landroid/os/Handler;"))};
    /* access modifiers changed from: private */
    public NextActionListener b;
    private CountrySelectionAdapter c;
    private DocumentType d;
    private BottomSheetBehavior<RelativeLayout> e;
    private final Lazy f = LazyKt.lazy(a.a);
    private HashMap g;
    public CountrySelectionPresenter presenter;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final CountrySelectionFragment createInstance(DocumentType documentType) {
            Intrinsics.checkParameterIsNotNull(documentType, "documentType");
            CountrySelectionFragment countrySelectionFragment = new CountrySelectionFragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable("DOCUMENT_TYPE", documentType);
            countrySelectionFragment.setArguments(bundle);
            return countrySelectionFragment;
        }
    }

    static final class a extends Lambda implements Function0<Handler> {
        public static final a a = new a();

        a() {
            super(0);
        }

        /* renamed from: a */
        public final Handler invoke() {
            return new Handler();
        }
    }

    static final class b implements OnClickListener {
        final /* synthetic */ android.view.View a;
        final /* synthetic */ CountrySelectionFragment b;

        b(android.view.View view, CountrySelectionFragment countrySelectionFragment) {
            this.b = countrySelectionFragment;
            this.a = view;
        }

        public final void onClick(android.view.View view) {
            BottomSheetExtensionsKt.toggle(CountrySelectionFragment.access$getBottomSheetBehavior$p(this.b));
        }
    }

    static final class c implements OnClickListener {
        final /* synthetic */ android.view.View a;
        final /* synthetic */ CountrySelectionFragment b;

        c(android.view.View view, CountrySelectionFragment countrySelectionFragment) {
            this.b = countrySelectionFragment;
            this.a = view;
        }

        public final void onClick(android.view.View view) {
            NextActionListener access$getNextActionListener$p = this.b.b;
            if (access$getNextActionListener$p != null) {
                access$getNextActionListener$p.onAlternateDocumentSelected();
            }
        }
    }

    static final class d implements OnClickListener {
        final /* synthetic */ android.view.View a;
        final /* synthetic */ CountrySelectionFragment b;

        d(android.view.View view, CountrySelectionFragment countrySelectionFragment) {
            this.b = countrySelectionFragment;
            this.a = view;
        }

        public final void onClick(android.view.View view) {
            BottomSheetExtensionsKt.collapse(CountrySelectionFragment.access$getBottomSheetBehavior$p(this.b));
        }
    }

    static final class e implements Runnable {
        final /* synthetic */ CountrySelectionFragment a;
        final /* synthetic */ List b;

        e(CountrySelectionFragment countrySelectionFragment, List list) {
            this.a = countrySelectionFragment;
            this.b = list;
        }

        public final void run() {
            CountrySelectionFragment.access$getCountriesAdapter$p(this.a).setCountries(this.b);
            RecyclerView recyclerView = (RecyclerView) this.a._$_findCachedViewById(R.id.countriesList);
            if (recyclerView != null) {
                recyclerView.exitLoadingState();
            }
        }
    }

    /* access modifiers changed from: private */
    public final Handler a() {
        Lazy lazy = this.f;
        KProperty kProperty = a[0];
        return (Handler) lazy.getValue();
    }

    public static final /* synthetic */ BottomSheetBehavior access$getBottomSheetBehavior$p(CountrySelectionFragment countrySelectionFragment) {
        BottomSheetBehavior<RelativeLayout> bottomSheetBehavior = countrySelectionFragment.e;
        if (bottomSheetBehavior == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bottomSheetBehavior");
        }
        return bottomSheetBehavior;
    }

    public static final /* synthetic */ CountrySelectionAdapter access$getCountriesAdapter$p(CountrySelectionFragment countrySelectionFragment) {
        CountrySelectionAdapter countrySelectionAdapter = countrySelectionFragment.c;
        if (countrySelectionAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("countriesAdapter");
        }
        return countrySelectionAdapter;
    }

    public static final /* synthetic */ DocumentType access$getDocumentType$p(CountrySelectionFragment countrySelectionFragment) {
        DocumentType documentType = countrySelectionFragment.d;
        if (documentType == null) {
            Intrinsics.throwUninitializedPropertyAccessException("documentType");
        }
        return documentType;
    }

    public void _$_clearFindViewByIdCache() {
        if (this.g != null) {
            this.g.clear();
        }
    }

    public android.view.View _$_findCachedViewById(int i) {
        if (this.g == null) {
            this.g = new HashMap();
        }
        android.view.View view = (android.view.View) this.g.get(Integer.valueOf(i));
        if (view == null) {
            android.view.View view2 = getView();
            if (view2 == null) {
                return null;
            }
            view = view2.findViewById(i);
            this.g.put(Integer.valueOf(i), view);
        }
        return view;
    }

    public void enterLoadingState() {
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(R.id.countriesList);
        if (recyclerView != null) {
            recyclerView.enterLoadingState();
        }
    }

    public final CountrySelectionPresenter getPresenter() {
        CountrySelectionPresenter countrySelectionPresenter = this.presenter;
        if (countrySelectionPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        return countrySelectionPresenter;
    }

    public void onAlternativeDocumentSelected() {
        NextActionListener nextActionListener = this.b;
        if (nextActionListener != null) {
            nextActionListener.onAlternateDocumentSelected();
        }
    }

    public void onAttach(Activity activity) {
        Intrinsics.checkParameterIsNotNull(activity, "activity");
        super.onAttach(activity);
        try {
            this.b = (NextActionListener) activity;
        } catch (ClassCastException unused) {
            String simpleName = activity.getClass().getSimpleName();
            StringBuilder sb = new StringBuilder();
            sb.append("Activity ");
            sb.append(simpleName);
            sb.append(" must implement NextActionListener interface.");
            throw new IllegalStateException(sb.toString());
        }
    }

    public void onCountrySelected(CountryCode countryCode) {
        Intrinsics.checkParameterIsNotNull(countryCode, "countryCode");
        NextActionListener nextActionListener = this.b;
        if (nextActionListener != null) {
            DocumentType documentType = this.d;
            if (documentType == null) {
                Intrinsics.throwUninitializedPropertyAccessException("documentType");
            }
            nextActionListener.onCountrySelected(documentType, countryCode);
        }
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        Intrinsics.checkParameterIsNotNull(menu, "menu");
        Intrinsics.checkParameterIsNotNull(menuInflater, "inflater");
        super.onCreateOptionsMenu(menu, menuInflater);
        Context context = getContext();
        if (context != null) {
            menuInflater.inflate(R.menu.onfido_country_selection, menu);
            MenuItem findItem = menu.findItem(R.id.action_search);
            android.view.View actionView = MenuItemCompat.getActionView(findItem);
            if (actionView == null) {
                throw new TypeCastException("null cannot be cast to non-null type android.support.v7.widget.SearchView");
            }
            SearchView searchView = (SearchView) actionView;
            searchView.setMaxWidth(Integer.MAX_VALUE);
            Drawable icon = findItem.getIcon();
            android.view.View findViewById = searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
            Intrinsics.checkExpressionValueIsNotNull(findViewById, "searchView.findViewById(…pat.R.id.search_src_text)");
            ((SearchAutoComplete) findViewById).setHintTextColor(ContextCompat.getColor(context, R.color.onfidoTextColorSecondary));
            Drawable wrap = DrawableCompat.wrap(icon);
            icon.mutate();
            DrawableCompat.setTint(wrap, ContextCompat.getColor(context, R.color.onfidoTextColorPrimary));
            findItem.setIcon(wrap);
            MenuItemCompat.setOnActionExpandListener(findItem, new CountrySelectionFragment$onCreateOptionsMenu$$inlined$let$lambda$1(searchView, this, menuInflater, menu));
        }
    }

    public android.view.View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(layoutInflater, "inflater");
        android.view.View inflate = layoutInflater.inflate(R.layout.onfido_fragment_country_selection, viewGroup, false);
        Bundle arguments = getArguments();
        if (arguments != null) {
            Serializable serializable = arguments.getSerializable("DOCUMENT_TYPE");
            if (serializable == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.onfido.android.sdk.capture.DocumentType");
            }
            this.d = (DocumentType) serializable;
            SdkController.getSdkComponent$default(SdkController.Companion.getInstance(), null, 1, null).inject(this);
            Context context = inflate.getContext();
            Intrinsics.checkExpressionValueIsNotNull(context, "context");
            this.c = new CountrySelectionAdapter(context, this);
            RecyclerView recyclerView = (RecyclerView) inflate.findViewById(R.id.countriesList);
            if (recyclerView != null) {
                CountrySelectionAdapter countrySelectionAdapter = this.c;
                if (countrySelectionAdapter == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("countriesAdapter");
                }
                DocumentType documentType = this.d;
                if (documentType == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("documentType");
                }
                countrySelectionAdapter.setDocumentType(documentType);
                recyclerView.setAdapter(countrySelectionAdapter);
            }
            BottomSheetBehavior<RelativeLayout> from = BottomSheetBehavior.from((RelativeLayout) inflate.findViewById(R.id.bottomSheet));
            from.setPeekHeight(inflate.getContext().getResources().getDimensionPixelSize(R.dimen.onfido_country_selection_bs_peek_height));
            BottomSheetExtensionsKt.collapse(from);
            Intrinsics.checkExpressionValueIsNotNull(from, "BottomSheetBehavior.from…lapse()\n                }");
            this.e = from;
            ((RelativeLayout) inflate.findViewById(R.id.bottomSheet)).setOnClickListener(new b(inflate, this));
            ((Button) inflate.findViewById(R.id.alternativeDocument)).setOnClickListener(new c(inflate, this));
            ((Button) inflate.findViewById(R.id.dismiss)).setOnClickListener(new d(inflate, this));
            CountrySelectionPresenter countrySelectionPresenter = this.presenter;
            if (countrySelectionPresenter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("presenter");
            }
            countrySelectionPresenter.attachView(this);
        }
        return inflate;
    }

    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    public void onDetach() {
        super.onDetach();
        this.b = null;
        a().removeCallbacksAndMessages(null);
    }

    public boolean onQueryTextChange(String str) {
        Intrinsics.checkParameterIsNotNull(str, "newText");
        CountrySelectionAdapter countrySelectionAdapter = this.c;
        if (countrySelectionAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("countriesAdapter");
        }
        countrySelectionAdapter.setSearchTerm(str);
        CountrySelectionAdapter countrySelectionAdapter2 = this.c;
        if (countrySelectionAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("countriesAdapter");
        }
        countrySelectionAdapter2.filterBy(str);
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(R.id.countriesList);
        if (recyclerView != null) {
            recyclerView.scrollToPosition(0);
        }
        return true;
    }

    public boolean onQueryTextSubmit(String str) {
        Intrinsics.checkParameterIsNotNull(str, "query");
        return false;
    }

    public void onStart() {
        super.onStart();
        CountrySelectionPresenter countrySelectionPresenter = this.presenter;
        if (countrySelectionPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        countrySelectionPresenter.trackCountrySelection(true);
    }

    public void onStop() {
        super.onStop();
        CountrySelectionPresenter countrySelectionPresenter = this.presenter;
        if (countrySelectionPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        countrySelectionPresenter.trackCountrySelection(false);
    }

    public void onViewCreated(android.view.View view, Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        super.onViewCreated(view, bundle);
        setHasOptionsMenu(true);
        CountrySelectionPresenter countrySelectionPresenter = this.presenter;
        if (countrySelectionPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        DocumentType documentType = this.d;
        if (documentType == null) {
            Intrinsics.throwUninitializedPropertyAccessException("documentType");
        }
        countrySelectionPresenter.getCountrySuggestion(documentType);
    }

    public void setCountries(List<? extends BaseAdapterItem> list) {
        Intrinsics.checkParameterIsNotNull(list, "countries");
        a().postDelayed(new e(this, list), 250);
    }
}
