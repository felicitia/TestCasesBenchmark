package com.onfido.android.sdk.capture.ui.country_selection;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.SpannableString;
import android.text.style.TextAppearanceSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TextView.BufferType;
import com.onfido.android.sdk.capture.DocumentType;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.utils.CountryCode;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.NoWhenBranchMatchedException;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.StringsKt;

public final class CountrySelectionAdapter extends Adapter<ViewHolder> {
    private final LayoutInflater a = LayoutInflater.from(this.f);
    private DocumentType b;
    private String c = "";
    private List<BaseAdapterItem> d = new ArrayList();
    private List<BaseAdapterItem> e = new ArrayList();
    private final Context f;
    /* access modifiers changed from: private */
    public final CountrySelectionListener g;

    public static final class CountrySelectionHeaderViewHolder extends ViewHolder {
        public CountrySelectionHeaderViewHolder(View view) {
            Intrinsics.checkParameterIsNotNull(view, "itemView");
            super(view);
        }
    }

    public static final class CountrySelectionSeparatorViewHolder extends ViewHolder {
        public CountrySelectionSeparatorViewHolder(View view) {
            Intrinsics.checkParameterIsNotNull(view, "itemView");
            super(view);
        }
    }

    public static final class CountrySelectionViewHolder extends ViewHolder {
        public CountrySelectionViewHolder(View view) {
            Intrinsics.checkParameterIsNotNull(view, "itemView");
            super(view);
        }
    }

    public static final class NoCountriesAvailableViewHolder extends ViewHolder {
        public NoCountriesAvailableViewHolder(View view) {
            Intrinsics.checkParameterIsNotNull(view, "itemView");
            super(view);
        }
    }

    static final class a implements OnClickListener {
        final /* synthetic */ CountrySelectionAdapter a;
        final /* synthetic */ CountryCode b;

        a(CountrySelectionAdapter countrySelectionAdapter, CountryCode countryCode) {
            this.a = countrySelectionAdapter;
            this.b = countryCode;
        }

        public final void onClick(View view) {
            this.a.g.onCountrySelected(this.b);
        }
    }

    static final class b extends Lambda implements Function1<BaseAdapterItem, Integer> {
        final /* synthetic */ String a;

        b(String str) {
            this.a = str;
            super(1);
        }

        public final int a(BaseAdapterItem baseAdapterItem) {
            Intrinsics.checkParameterIsNotNull(baseAdapterItem, "it");
            return StringsKt.indexOf((CharSequence) ((CountryAvailability) baseAdapterItem).getCountryCode().getDisplayName(), this.a, 0, true);
        }

        public /* synthetic */ Object invoke(Object obj) {
            return Integer.valueOf(a((BaseAdapterItem) obj));
        }
    }

    static final class c extends Lambda implements Function1<BaseAdapterItem, String> {
        public static final c a = new c();

        c() {
            super(1);
        }

        /* renamed from: a */
        public final String invoke(BaseAdapterItem baseAdapterItem) {
            Intrinsics.checkParameterIsNotNull(baseAdapterItem, "it");
            return ((CountryAvailability) baseAdapterItem).getCountryCode().getDisplayName();
        }
    }

    static final class d implements OnClickListener {
        final /* synthetic */ CountrySelectionAdapter a;

        d(CountrySelectionAdapter countrySelectionAdapter) {
            this.a = countrySelectionAdapter;
        }

        public final void onClick(View view) {
            this.a.g.onAlternativeDocumentSelected();
        }
    }

    public CountrySelectionAdapter(Context context, CountrySelectionListener countrySelectionListener) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(countrySelectionListener, "countrySelectionListener");
        this.f = context;
        this.g = countrySelectionListener;
    }

    private final void a(CountryAvailability countryAvailability, View view) {
        CountryCode countryCode = countryAvailability.getCountryCode();
        TextView textView = (TextView) view.findViewById(R.id.countryName);
        int indexOf = StringsKt.indexOf((CharSequence) countryCode.getDisplayName(), this.c, 0, true);
        SpannableString spannableString = new SpannableString(countryCode.getDisplayName());
        spannableString.setSpan(new TextAppearanceSpan(textView.getContext(), R.style.OnfidoTextStyle_Bold), indexOf, this.c.length() + indexOf, 17);
        textView.setTextColor(ContextCompat.getColor(textView.getContext(), R.color.onfido_medium_500));
        textView.setText(spannableString, BufferType.SPANNABLE);
        view.setOnClickListener(new a(this, countryCode));
    }

    private final void a(List<? extends BaseAdapterItem> list) {
        for (int lastIndex = CollectionsKt.getLastIndex(this.d); lastIndex >= 0; lastIndex--) {
            if (this.d.get(lastIndex) instanceof CountryAvailability) {
                Object obj = this.d.get(lastIndex);
                if (obj == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.onfido.android.sdk.capture.ui.country_selection.CountryAvailability");
                } else if (list.contains((CountryAvailability) obj)) {
                }
            }
            this.d.remove(lastIndex);
            notifyItemRemoved(lastIndex);
        }
        Iterable<BaseAdapterItem> iterable = list;
        int i = 0;
        for (BaseAdapterItem baseAdapterItem : iterable) {
            int i2 = i + 1;
            if (!this.d.contains(baseAdapterItem)) {
                this.d.add(i, baseAdapterItem);
                notifyItemInserted(i);
            }
            i = i2;
        }
        int i3 = 0;
        for (BaseAdapterItem baseAdapterItem2 : iterable) {
            int i4 = i3 + 1;
            int indexOf = this.d.indexOf(baseAdapterItem2);
            if (indexOf < 0 || indexOf == i3) {
                notifyItemChanged(indexOf);
            } else {
                this.d.remove(indexOf);
                this.d.add(i3, baseAdapterItem2);
                notifyItemMoved(indexOf, i3);
                notifyItemChanged(i3);
            }
            i3 = i4;
        }
        if (this.d.isEmpty()) {
            this.d.add(new CountrySelectionEmptyState());
            notifyItemInserted(0);
        }
    }

    public final void filterBy(String str) {
        Intrinsics.checkParameterIsNotNull(str, "searchTerm");
        Iterable iterable = this.e;
        Collection arrayList = new ArrayList();
        Iterator it = iterable.iterator();
        while (true) {
            boolean z = false;
            if (it.hasNext()) {
                Object next = it.next();
                BaseAdapterItem baseAdapterItem = (BaseAdapterItem) next;
                if ((baseAdapterItem instanceof CountryAvailability) && StringsKt.contains(((CountryAvailability) baseAdapterItem).getCountryCode().getDisplayName(), str, true)) {
                    z = true;
                }
                if (z) {
                    arrayList.add(next);
                }
            } else {
                a(CollectionsKt.sortedWith((List) arrayList, ComparisonsKt.compareBy(new b(str), c.a)));
                return;
            }
        }
    }

    public int getItemCount() {
        return this.d.size();
    }

    public int getItemViewType(int i) {
        if (!(this.d.get(i) instanceof CountrySelectionSeparator)) {
            return this.d.get(i) instanceof CountrySelectionEmptyState ? 3 : 0;
        }
        Object obj = this.d.get(i);
        if (obj == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.onfido.android.sdk.capture.ui.country_selection.CountrySelectionSeparator");
        }
        switch (((CountrySelectionSeparator) obj).getType()) {
            case SUGGESTED_COUNTRY:
            case ALL_COUNTRIES:
                return 1;
            case SEPARATOR:
                return 2;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Context context;
        TextView textView;
        int i2;
        Intrinsics.checkParameterIsNotNull(viewHolder, "holder");
        BaseAdapterItem baseAdapterItem = (BaseAdapterItem) this.d.get(i);
        if (baseAdapterItem instanceof CountryAvailability) {
            CountryAvailability countryAvailability = (CountryAvailability) baseAdapterItem;
            View view = viewHolder.itemView;
            Intrinsics.checkExpressionValueIsNotNull(view, "holder.itemView");
            a(countryAvailability, view);
        } else if (baseAdapterItem instanceof CountrySelectionSeparator) {
            switch (((CountrySelectionSeparator) baseAdapterItem).getType()) {
                case SUGGESTED_COUNTRY:
                    textView = (TextView) viewHolder.itemView.findViewById(R.id.headerText);
                    context = this.f;
                    i2 = R.string.onfido_suggested_country;
                    break;
                case ALL_COUNTRIES:
                    textView = (TextView) viewHolder.itemView.findViewById(R.id.headerText);
                    context = this.f;
                    i2 = R.string.onfido_all_countries;
                    break;
                default:
                    return;
            }
            textView.setText(context.getString(i2));
        } else {
            if (baseAdapterItem instanceof CountrySelectionEmptyState) {
                ((Button) viewHolder.itemView.findViewById(R.id.selectAnotherCountry)).setOnClickListener(new d(this));
            }
        }
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        ViewHolder countrySelectionHeaderViewHolder;
        Intrinsics.checkParameterIsNotNull(viewGroup, "parent");
        switch (i) {
            case 1:
                View inflate = this.a.inflate(R.layout.onfido_elem_country_selection_header, viewGroup, false);
                Intrinsics.checkExpressionValueIsNotNull(inflate, "inflater.inflate(R.layou…on_header, parent, false)");
                countrySelectionHeaderViewHolder = new CountrySelectionHeaderViewHolder(inflate);
                break;
            case 2:
                View inflate2 = this.a.inflate(R.layout.onfido_separator, viewGroup, false);
                Intrinsics.checkExpressionValueIsNotNull(inflate2, "inflater.inflate(R.layou…separator, parent, false)");
                countrySelectionHeaderViewHolder = new CountrySelectionSeparatorViewHolder(inflate2);
                break;
            case 3:
                View inflate3 = this.a.inflate(R.layout.onfido_country_search_empty_state, viewGroup, false);
                Intrinsics.checkExpressionValueIsNotNull(inflate3, "inflater.inflate(R.layou…pty_state, parent, false)");
                countrySelectionHeaderViewHolder = new NoCountriesAvailableViewHolder(inflate3);
                break;
            default:
                View inflate4 = this.a.inflate(R.layout.onfido_elem_country_selection, viewGroup, false);
                Intrinsics.checkExpressionValueIsNotNull(inflate4, "inflater.inflate(R.layou…selection, parent, false)");
                countrySelectionHeaderViewHolder = new CountrySelectionViewHolder(inflate4);
                break;
        }
        return countrySelectionHeaderViewHolder;
    }

    public final void removeItems() {
        this.d.clear();
        this.e.clear();
        notifyDataSetChanged();
    }

    public final void setCountries(List<? extends BaseAdapterItem> list) {
        Intrinsics.checkParameterIsNotNull(list, "countries");
        Collection collection = list;
        this.d = CollectionsKt.toMutableList(collection);
        this.e = CollectionsKt.toMutableList(collection);
        notifyItemRangeInserted(0, list.size());
    }

    public final void setDocumentType(DocumentType documentType) {
        Intrinsics.checkParameterIsNotNull(documentType, "documentType");
        this.b = documentType;
    }

    public final void setSearchTerm(String str) {
        Intrinsics.checkParameterIsNotNull(str, "term");
        this.c = str;
    }
}
