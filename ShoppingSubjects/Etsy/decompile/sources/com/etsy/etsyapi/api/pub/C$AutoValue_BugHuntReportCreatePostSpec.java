package com.etsy.etsyapi.api.pub;

import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.auto.value.jackson.ModelParser;
import com.etsy.etsyapi.models.EtsyId;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.io.File;
import java.io.IOException;

/* renamed from: com.etsy.etsyapi.api.pub.$AutoValue_BugHuntReportCreatePostSpec reason: invalid class name */
abstract class C$AutoValue_BugHuntReportCreatePostSpec extends C$$AutoValue_BugHuntReportCreatePostSpec {
    C$AutoValue_BugHuntReportCreatePostSpec(String str, String str2, String str3, String str4, String str5, EtsyId etsyId, File file, File file2, File file3) {
        super(str, str2, str3, str4, str5, etsyId, file, file2, file3);
    }

    public static BugHuntReportCreatePostSpec read(JsonParser jsonParser) throws IOException {
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
                    case -1542869117:
                        if (currentName.equals("device_type")) {
                            c = 4;
                            break;
                        }
                        break;
                    case -208516626:
                        if (currentName.equals(ResponseConstants.ETSY_VERSION)) {
                            c = 3;
                            break;
                        }
                        break;
                    case -147132913:
                        if (currentName.equals("user_id")) {
                            c = 5;
                            break;
                        }
                        break;
                    case 954925063:
                        if (currentName.equals("message")) {
                            c = 0;
                            break;
                        }
                        break;
                    case 1813514508:
                        if (currentName.equals(ResponseConstants.PLATFORM_VERSION)) {
                            c = 2;
                            break;
                        }
                        break;
                    case 1874684019:
                        if (currentName.equals(ResponseConstants.PLATFORM)) {
                            c = 1;
                            break;
                        }
                        break;
                    case 1911932060:
                        if (currentName.equals("image01")) {
                            c = 6;
                            break;
                        }
                        break;
                    case 1911932061:
                        if (currentName.equals("image02")) {
                            c = 7;
                            break;
                        }
                        break;
                    case 1911932062:
                        if (currentName.equals("image03")) {
                            c = 8;
                            break;
                        }
                        break;
                }
                switch (c) {
                    case 0:
                        aVar.a(ModelParser.a(jsonParser));
                        break;
                    case 1:
                        aVar.b(ModelParser.a(jsonParser));
                        break;
                    case 2:
                        aVar.c(ModelParser.a(jsonParser));
                        break;
                    case 3:
                        aVar.d(ModelParser.a(jsonParser));
                        break;
                    case 4:
                        aVar.e(ModelParser.a(jsonParser));
                        break;
                    case 5:
                        aVar.a((EtsyId) ModelParser.a(jsonParser, EtsyId.class));
                        break;
                    case 6:
                        aVar.a((File) ModelParser.a(jsonParser, File.class));
                        break;
                    case 7:
                        aVar.b((File) ModelParser.a(jsonParser, File.class));
                        break;
                    case 8:
                        aVar.c((File) ModelParser.a(jsonParser, File.class));
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
