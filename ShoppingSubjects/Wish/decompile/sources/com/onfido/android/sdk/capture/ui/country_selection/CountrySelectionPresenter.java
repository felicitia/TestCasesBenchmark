package com.onfido.android.sdk.capture.ui.country_selection;

import com.onfido.android.sdk.capture.DocumentType;
import com.onfido.android.sdk.capture.analytics.AnalyticsInteractor;
import com.onfido.android.sdk.capture.utils.CountryCode;
import com.onfido.android.sdk.capture.utils.ReactiveExtensionsKt;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Pair;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SpreadBuilder;
import kotlin.reflect.KProperty;

public final class CountrySelectionPresenter {
    static final /* synthetic */ KProperty[] a = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(CountrySelectionPresenter.class), "subscriptions", "getSubscriptions()Lio/reactivex/disposables/CompositeDisposable;"))};
    private View b;
    private final Lazy c = LazyKt.lazy(d.a);
    private final GetCountriesForDocumentTypeUseCase d;
    private final AnalyticsInteractor e;
    private final GetCurrentCountryCodeUseCase f;

    public interface View {
        void enterLoadingState();

        void setCountries(List<? extends BaseAdapterItem> list);
    }

    static final class a<T, R> implements Function<T, R> {
        final /* synthetic */ List a;

        a(List list) {
            this.a = list;
        }

        /* renamed from: a */
        public final CountryAvailability apply(String str) {
            Object obj;
            Iterator it = this.a.iterator();
            while (true) {
                if (!it.hasNext()) {
                    obj = null;
                    break;
                }
                obj = it.next();
                if (Intrinsics.areEqual(((CountryAvailability) obj).getCountryCode().getAlpha2(), str)) {
                    break;
                }
            }
            CountryAvailability countryAvailability = (CountryAvailability) obj;
            if (countryAvailability != null) {
                return countryAvailability;
            }
            throw new UnsupportedSuggestedCountryException();
        }
    }

    static final class b<T> implements Consumer<CountryAvailability> {
        final /* synthetic */ CountrySelectionPresenter a;
        final /* synthetic */ List b;

        b(CountrySelectionPresenter countrySelectionPresenter, List list) {
            this.a = countrySelectionPresenter;
            this.b = list;
        }

        /* renamed from: a */
        public final void accept(CountryAvailability countryAvailability) {
            View access$getView$p = CountrySelectionPresenter.access$getView$p(this.a);
            SpreadBuilder spreadBuilder = new SpreadBuilder(5);
            spreadBuilder.add(new CountrySelectionSeparator(CountrySelectionSeparatorType.SUGGESTED_COUNTRY));
            spreadBuilder.add(countryAvailability);
            spreadBuilder.add(new CountrySelectionSeparator(CountrySelectionSeparatorType.SEPARATOR));
            spreadBuilder.add(new CountrySelectionSeparator(CountrySelectionSeparatorType.ALL_COUNTRIES));
            Iterable iterable = this.b;
            Collection arrayList = new ArrayList();
            for (Object next : iterable) {
                if (!Intrinsics.areEqual(((CountryAvailability) next).getCountryCode(), countryAvailability.getCountryCode())) {
                    arrayList.add(next);
                }
            }
            Object[] array = ((List) arrayList).toArray(new CountryAvailability[0]);
            if (array == null) {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
            }
            spreadBuilder.addSpread(array);
            access$getView$p.setCountries(CollectionsKt.listOf((Object[]) (BaseAdapterItem[]) spreadBuilder.toArray(new BaseAdapterItem[spreadBuilder.size()])));
        }
    }

    static final class c<T> implements Consumer<Throwable> {
        final /* synthetic */ CountrySelectionPresenter a;
        final /* synthetic */ List b;

        c(CountrySelectionPresenter countrySelectionPresenter, List list) {
            this.a = countrySelectionPresenter;
            this.b = list;
        }

        /* renamed from: a */
        public final void accept(Throwable th) {
            CountrySelectionPresenter.access$getView$p(this.a).setCountries(this.b);
        }
    }

    static final class d extends Lambda implements Function0<CompositeDisposable> {
        public static final d a = new d();

        d() {
            super(0);
        }

        /* renamed from: a */
        public final CompositeDisposable invoke() {
            return new CompositeDisposable();
        }
    }

    public CountrySelectionPresenter(GetCountriesForDocumentTypeUseCase getCountriesForDocumentTypeUseCase, AnalyticsInteractor analyticsInteractor, GetCurrentCountryCodeUseCase getCurrentCountryCodeUseCase) {
        Intrinsics.checkParameterIsNotNull(getCountriesForDocumentTypeUseCase, "getCountriesForDocumentTypeUseCase");
        Intrinsics.checkParameterIsNotNull(analyticsInteractor, "analyticsInteractor");
        Intrinsics.checkParameterIsNotNull(getCurrentCountryCodeUseCase, "getCurrentCountryCodeUseCase");
        this.d = getCountriesForDocumentTypeUseCase;
        this.e = analyticsInteractor;
        this.f = getCurrentCountryCodeUseCase;
    }

    private final CompositeDisposable a() {
        Lazy lazy = this.c;
        KProperty kProperty = a[0];
        return (CompositeDisposable) lazy.getValue();
    }

    public static final /* synthetic */ View access$getView$p(CountrySelectionPresenter countrySelectionPresenter) {
        View view = countrySelectionPresenter.b;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
        }
        return view;
    }

    public final void attachView(View view) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        this.b = view;
    }

    public final void getCountrySuggestion(DocumentType documentType) {
        Intrinsics.checkParameterIsNotNull(documentType, "documentType");
        View view = this.b;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
        }
        view.enterLoadingState();
        Iterable<Pair> build = this.d.build(documentType);
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(build, 10));
        for (Pair pair : build) {
            arrayList.add(new CountryAvailability((CountryCode) pair.getFirst(), ((Boolean) pair.getSecond()).booleanValue()));
        }
        List list = (List) arrayList;
        a().add(ReactiveExtensionsKt.subscribeAndObserve$default(this.f.build().map(new a(list)), null, null, 3, null).subscribe(new b(this, list), new c(this, list)));
    }

    public final void trackCountrySelection(boolean z) {
        this.e.trackCountrySelection(z);
    }
}
