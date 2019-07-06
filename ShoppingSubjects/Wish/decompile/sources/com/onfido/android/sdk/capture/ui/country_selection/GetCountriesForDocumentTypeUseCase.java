package com.onfido.android.sdk.capture.ui.country_selection;

import android.content.Context;
import android.content.res.Resources;
import com.onfido.android.sdk.capture.DocumentType;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.ui.country_selection.DocumentCountryAvailability.Country;
import com.onfido.android.sdk.capture.utils.CountryCode;
import com.onfido.android.sdk.capture.utils.JSONResourceReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import org.json.JSONArray;
import org.json.JSONObject;

public class GetCountriesForDocumentTypeUseCase {
    private final Context a;

    public GetCountriesForDocumentTypeUseCase(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.a = context;
    }

    public List<Pair<CountryCode, Boolean>> build(DocumentType documentType) {
        Object obj;
        Intrinsics.checkParameterIsNotNull(documentType, "documentType");
        Resources resources = this.a.getResources();
        Intrinsics.checkExpressionValueIsNotNull(resources, "context.resources");
        JSONArray jSONArray = new JSONObject(new JSONResourceReader(resources, R.raw.onfido_doc_country_map).getJsonString()).getJSONArray("countries");
        DocumentCountryAvailability documentCountryAvailability = new DocumentCountryAvailability();
        Iterator it = new IntRange(0, jSONArray.length() - 1).iterator();
        while (it.hasNext()) {
            int nextInt = ((IntIterator) it).nextInt();
            List countries = documentCountryAvailability.getCountries();
            Country country = new Country();
            JSONObject jSONObject = jSONArray.getJSONObject(nextInt);
            JSONArray jSONArray2 = jSONObject.getJSONArray("types");
            String string = jSONObject.getString("code");
            Intrinsics.checkExpressionValueIsNotNull(string, "jsonObject.getString(\"code\")");
            country.setCode(string);
            Iterator it2 = new IntRange(0, jSONArray2.length() - 1).iterator();
            while (it2.hasNext()) {
                JSONObject jSONObject2 = jSONArray2.getJSONObject(((IntIterator) it2).nextInt());
                List types = country.getTypes();
                DocumentCountryAvailability.DocumentType documentType2 = new DocumentCountryAvailability.DocumentType();
                String string2 = jSONObject2.getString("name");
                Intrinsics.checkExpressionValueIsNotNull(string2, "document.getString(\"name\")");
                documentType2.setName(string2);
                types.add(documentType2);
            }
            countries.add(country);
        }
        Iterable list = ArraysKt.toList((Object[]) CountryCode.values());
        Collection arrayList = new ArrayList();
        for (Object next : list) {
            CountryCode countryCode = (CountryCode) next;
            Iterable<Country> countries2 = documentCountryAvailability.getCountries();
            Collection arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(countries2, 10));
            for (Country code : countries2) {
                arrayList2.add(code.getCode());
            }
            if (((List) arrayList2).contains(countryCode.getAlpha3())) {
                arrayList.add(next);
            }
        }
        Iterable<CountryCode> iterable = (List) arrayList;
        Collection arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (CountryCode countryCode2 : iterable) {
            Iterator it3 = documentCountryAvailability.getCountries().iterator();
            while (true) {
                if (!it3.hasNext()) {
                    obj = null;
                    break;
                }
                obj = it3.next();
                if (Intrinsics.areEqual(((Country) obj).getCode(), countryCode2.getAlpha3())) {
                    break;
                }
            }
            if (obj == null) {
                Intrinsics.throwNpe();
            }
            Iterable<DocumentCountryAvailability.DocumentType> types2 = ((Country) obj).getTypes();
            Collection arrayList4 = new ArrayList(CollectionsKt.collectionSizeOrDefault(types2, 10));
            for (DocumentCountryAvailability.DocumentType name : types2) {
                arrayList4.add(name.getName());
            }
            arrayList3.add(TuplesKt.to(countryCode2, Boolean.valueOf(((List) arrayList4).contains(documentType.getId()))));
        }
        Iterable sortedWith = CollectionsKt.sortedWith((List) arrayList3, new GetCountriesForDocumentTypeUseCase$build$$inlined$sortedBy$1());
        Collection arrayList5 = new ArrayList();
        for (Object next2 : sortedWith) {
            if (((Boolean) ((Pair) next2).getSecond()).booleanValue()) {
                arrayList5.add(next2);
            }
        }
        return (List) arrayList5;
    }
}
