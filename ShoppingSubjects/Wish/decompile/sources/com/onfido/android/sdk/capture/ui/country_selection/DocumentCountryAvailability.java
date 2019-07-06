package com.onfido.android.sdk.capture.ui.country_selection;

import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

public final class DocumentCountryAvailability {
    private List<Country> a = new ArrayList();

    public static final class Country {
        private String a = "";
        private List<DocumentType> b = new ArrayList();

        public final String getCode() {
            return this.a;
        }

        public final List<DocumentType> getTypes() {
            return this.b;
        }

        public final void setCode(String str) {
            Intrinsics.checkParameterIsNotNull(str, "<set-?>");
            this.a = str;
        }
    }

    public static final class DocumentType {
        private String a = "";
        private List<String> b = new ArrayList();
        private List<Object> c = new ArrayList();

        public final String getName() {
            return this.a;
        }

        public final void setName(String str) {
            Intrinsics.checkParameterIsNotNull(str, "<set-?>");
            this.a = str;
        }
    }

    public final List<Country> getCountries() {
        return this.a;
    }
}
