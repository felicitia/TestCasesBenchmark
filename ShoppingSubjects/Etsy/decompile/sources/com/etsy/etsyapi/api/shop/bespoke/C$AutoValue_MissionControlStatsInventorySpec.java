package com.etsy.etsyapi.api.shop.bespoke;

import com.etsy.android.lib.models.cardviewelement.stats.Filter;
import com.etsy.auto.value.jackson.ModelParser;
import com.etsy.etsyapi.models.EtsyId;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.io.IOException;
import org.apache.commons.lang3.CharUtils;

/* renamed from: com.etsy.etsyapi.api.shop.bespoke.$AutoValue_MissionControlStatsInventorySpec reason: invalid class name */
abstract class C$AutoValue_MissionControlStatsInventorySpec extends C$$AutoValue_MissionControlStatsInventorySpec {
    C$AutoValue_MissionControlStatsInventorySpec(EtsyId etsyId, Integer num, String str, String str2, Integer num2, String str3, String str4, String str5, Integer num3, Boolean bool, Boolean bool2, Integer num4, Integer num5, String str6, String str7, String str8, String str9, String str10, String str11, String str12) {
        super(etsyId, num, str, str2, num2, str3, str4, str5, num3, bool, bool2, num4, num5, str6, str7, str8, str9, str10, str11, str12);
    }

    public static MissionControlStatsInventorySpec read(JsonParser jsonParser) throws IOException {
        a aVar = new a();
        if (jsonParser.getCurrentToken() != JsonToken.START_OBJECT) {
            return null;
        }
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                char c = 65535;
                switch (currentName.hashCode()) {
                    case -1981272548:
                        if (currentName.equals("prod_shop_id")) {
                            c = 8;
                            break;
                        }
                        break;
                    case -1573629589:
                        if (currentName.equals("start_date")) {
                            c = 1;
                            break;
                        }
                        break;
                    case -1326197564:
                        if (currentName.equals("domain")) {
                            c = 17;
                            break;
                        }
                        break;
                    case -722568161:
                        if (currentName.equals("referrer")) {
                            c = 16;
                            break;
                        }
                        break;
                    case -563615928:
                        if (currentName.equals("listings_filter")) {
                            c = 19;
                            break;
                        }
                        break;
                    case -531577596:
                        if (currentName.equals("end_date_str")) {
                            c = 5;
                            break;
                        }
                        break;
                    case -474705571:
                        if (currentName.equals("start_date_str")) {
                            c = 2;
                            break;
                        }
                        break;
                    case 622159148:
                        if (currentName.equals("inv_sort")) {
                            c = CharUtils.CR;
                            break;
                        }
                        break;
                    case 738950403:
                        if (currentName.equals(Filter.FILTER_FIELD_NAME_CHANNEL)) {
                            c = 7;
                            break;
                        }
                        break;
                    case 771303585:
                        if (currentName.equals("inv_offset")) {
                            c = 12;
                            break;
                        }
                        break;
                    case 873238892:
                        if (currentName.equals(Filter.FILTER_FIELD_NAME_DATE_RANGE)) {
                            c = 3;
                            break;
                        }
                        break;
                    case 1535116401:
                        if (currentName.equals("end_date_str_inclusive")) {
                            c = 6;
                            break;
                        }
                        break;
                    case 1725067410:
                        if (currentName.equals("end_date")) {
                            c = 4;
                            break;
                        }
                        break;
                    case 1726427163:
                        if (currentName.equals("include_yoy_visits")) {
                            c = 10;
                            break;
                        }
                        break;
                    case 1826554577:
                        if (currentName.equals("internal_page")) {
                            c = 18;
                            break;
                        }
                        break;
                    case 1959390282:
                        if (currentName.equals("inv_sort_by")) {
                            c = 14;
                            break;
                        }
                        break;
                    case 2020677531:
                        if (currentName.equals("prod_dataset_override")) {
                            c = 9;
                            break;
                        }
                        break;
                    case 2067081988:
                        if (currentName.equals("shop_id")) {
                            c = 0;
                            break;
                        }
                        break;
                    case 2100415981:
                        if (currentName.equals("inv_limit")) {
                            c = 11;
                            break;
                        }
                        break;
                    case 2130707039:
                        if (currentName.equals("referrer_group")) {
                            c = 15;
                            break;
                        }
                        break;
                }
                switch (c) {
                    case 0:
                        aVar.a((EtsyId) ModelParser.a(jsonParser, EtsyId.class));
                        break;
                    case 1:
                        aVar.a(Integer.valueOf(jsonParser.getValueAsInt()));
                        break;
                    case 2:
                        aVar.a(ModelParser.a(jsonParser));
                        break;
                    case 3:
                        aVar.b(ModelParser.a(jsonParser));
                        break;
                    case 4:
                        aVar.b(Integer.valueOf(jsonParser.getValueAsInt()));
                        break;
                    case 5:
                        aVar.c(ModelParser.a(jsonParser));
                        break;
                    case 6:
                        aVar.d(ModelParser.a(jsonParser));
                        break;
                    case 7:
                        aVar.e(ModelParser.a(jsonParser));
                        break;
                    case 8:
                        aVar.c(Integer.valueOf(jsonParser.getValueAsInt()));
                        break;
                    case 9:
                        aVar.a(Boolean.valueOf(jsonParser.getValueAsBoolean()));
                        break;
                    case 10:
                        aVar.b(Boolean.valueOf(jsonParser.getValueAsBoolean()));
                        break;
                    case 11:
                        aVar.d(Integer.valueOf(jsonParser.getValueAsInt()));
                        break;
                    case 12:
                        aVar.e(Integer.valueOf(jsonParser.getValueAsInt()));
                        break;
                    case 13:
                        aVar.f(ModelParser.a(jsonParser));
                        break;
                    case 14:
                        aVar.g(ModelParser.a(jsonParser));
                        break;
                    case 15:
                        aVar.h(ModelParser.a(jsonParser));
                        break;
                    case 16:
                        aVar.i(ModelParser.a(jsonParser));
                        break;
                    case 17:
                        aVar.j(ModelParser.a(jsonParser));
                        break;
                    case 18:
                        aVar.k(ModelParser.a(jsonParser));
                        break;
                    case 19:
                        aVar.l(ModelParser.a(jsonParser));
                        break;
                    default:
                        jsonParser.skipChildren();
                        break;
                }
            }
        }
        return aVar.a();
    }
}
