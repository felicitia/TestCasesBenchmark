package com.etsy.etsyapi.api.shop.bespoke;

import com.etsy.auto.value.jackson.ModelParser;
import com.etsy.etsyapi.models.EtsyId;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.io.IOException;

/* renamed from: com.etsy.etsyapi.api.shop.bespoke.$AutoValue_SellerDashboardGetSpec reason: invalid class name */
abstract class C$AutoValue_SellerDashboardGetSpec extends C$$AutoValue_SellerDashboardGetSpec {
    C$AutoValue_SellerDashboardGetSpec(EtsyId etsyId, String str, String str2, Boolean bool) {
        super(etsyId, str, str2, bool);
    }

    public static SellerDashboardGetSpec read(JsonParser jsonParser) throws IOException {
        a aVar = new a();
        if (jsonParser.getCurrentToken() != JsonToken.START_OBJECT) {
            return null;
        }
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                char c = 65535;
                int hashCode = currentName.hashCode();
                if (hashCode != -1331952113) {
                    if (hashCode != -458248348) {
                        if (hashCode != 160665566) {
                            if (hashCode == 2067081988 && currentName.equals("shop_id")) {
                                c = 0;
                            }
                        } else if (currentName.equals("statsRange")) {
                            c = 1;
                        }
                    } else if (currentName.equals("statsChannel")) {
                        c = 2;
                    }
                } else if (currentName.equals("includeShopManager")) {
                    c = 3;
                }
                switch (c) {
                    case 0:
                        aVar.a((EtsyId) ModelParser.a(jsonParser, EtsyId.class));
                        break;
                    case 1:
                        aVar.a(ModelParser.a(jsonParser));
                        break;
                    case 2:
                        aVar.b(ModelParser.a(jsonParser));
                        break;
                    case 3:
                        aVar.a(Boolean.valueOf(jsonParser.getValueAsBoolean()));
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
