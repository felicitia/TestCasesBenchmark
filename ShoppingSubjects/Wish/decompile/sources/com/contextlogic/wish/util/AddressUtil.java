package com.contextlogic.wish.util;

import android.content.Context;
import android.text.TextUtils;
import com.contextlogic.wish.R;
import com.contextlogic.wish.api.datacenter.ConfigDataCenter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map.Entry;

public class AddressUtil {

    public static class Country {
        private String mCountryCode;
        private String mCountryName;

        public Country(String str, String str2) {
            this.mCountryName = str;
            this.mCountryCode = str2;
        }

        public String getName() {
            return this.mCountryName;
        }

        public String getCode() {
            return this.mCountryCode;
        }

        public boolean equals(Object obj) {
            boolean z = true;
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Country country = (Country) obj;
            if (!this.mCountryName.equals(country.mCountryName) || !this.mCountryCode.equals(country.mCountryCode)) {
                z = false;
            }
            return z;
        }

        public int hashCode() {
            return (this.mCountryName.hashCode() * 31) + this.mCountryCode.hashCode();
        }
    }

    public static String getJoinedAddressLine(Context context, String str, String str2) {
        ArrayList arrayList = new ArrayList();
        if (!TextUtils.isEmpty(str)) {
            arrayList.add(str);
        }
        if (!TextUtils.isEmpty(str2)) {
            arrayList.add(str2);
        }
        return StringUtil.join(arrayList, context.getString(R.string.address_format_separator));
    }

    public static HashMap<String, ArrayList<String>> getStateMapping() {
        return ConfigDataCenter.getInstance().getShippingLocations();
    }

    public static HashMap<String, String> getCountries() {
        return ConfigDataCenter.getInstance().getAllCountries();
    }

    public static String getCountryName(String str) {
        return (String) getCountries().get(str.toUpperCase());
    }

    public static ArrayList<String> getStates(String str) {
        return (ArrayList) getStateMapping().get(str.toUpperCase());
    }

    public static String getSubdivisionNameForCountry(String str) {
        return (String) ConfigDataCenter.getInstance().getCountrySubdivisionNames().get(str.toUpperCase());
    }

    public static ArrayList<Country> getSortedCountries() {
        ArrayList<Country> arrayList = new ArrayList<>();
        for (Entry entry : getCountries().entrySet()) {
            arrayList.add(new Country((String) entry.getValue(), (String) entry.getKey()));
        }
        Collections.sort(arrayList, new Comparator<Country>() {
            public int compare(Country country, Country country2) {
                return country.getName().compareTo(country2.getName());
            }
        });
        return arrayList;
    }
}
