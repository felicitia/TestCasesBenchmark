package com.onfido.android.sdk.capture.utils;

import java.util.HashMap;
import kotlin.jvm.internal.Intrinsics;

public enum CountryCode {
    AD("Andorra", "AND", 16, null, 8, null),
    AE("United Arab Emirates", "ARE", 784, "الإمارات العربية المتحدة"),
    AF("Afghanistan", "AFG", 4, null, 8, null),
    AG("Antigua and Barbuda", "ATG", 28, null, 8, null),
    AI("Anguilla", "AIA", 660, null, 8, null),
    AL("Albania", "ALB", 8, "Shqipëria"),
    AM("Armenia", "ARM", 51, "Հայաստան"),
    AN("Netherlands Antilles", "ANT", 530, null, 8, null),
    AO("Angola", "AGO", 24, null, 8, null),
    AQ("Antarctica", "ATA", 10, null, 8, null),
    AR("Argentina", "ARG", 32, null, 8, null),
    AS("American Samoa", "ASM", 16, null, 8, null),
    AT("Austria", "AUT", 40, "Österreich"),
    AU("Australia", "AUS", 36, null, 8, null),
    AW("Aruba", "ABW", 533, null, 8, null),
    AX("Åland Islands", "ALA", 248, null, 8, null),
    AZ("Azerbaijan", "AZE", 31, "Azərbaycan"),
    BA("Bosnia and Herzegovina", "BIH", 70, "Bosna i Hercegovina"),
    BB("Barbados", "BRB", 52, null, 8, null),
    BD("Bangladesh", "BGD", 50, "বাংলাদেশ"),
    BE("Belgium", "BEL", 56, "België"),
    BF("Burkina Faso", "BFA", 854, null, 8, null),
    BG("Bulgaria", "BGR", 100, "България"),
    BH("Bahrain", "BHR", 48, null, 8, null),
    BI("Burundi", "BDI", 108, null, 8, null),
    BJ("Benin", "BEN", 204, null, 8, null),
    BL("Saint Barthélemy", "BLM", 652, null, 8, null),
    BM("Bermuda", "BMU", 60, null, 8, null),
    BN("Brunei Darussalam", "BRN", 96, null, 8, null),
    BO("Plurinational State of Bolivia", "BOL", 68, null, 8, null),
    BQ("Bonaire, Sint Eustatius and Saba", "BES", 535, null, 8, null),
    BR("Brazil", "BRA", 76, "Brasil"),
    BS("Bahamas", "BHS", 44, null, 8, null),
    BT("Bhutan", "BTN", 64, null, 8, null),
    BV("Bouvet Island", "BVT", 74, null, 8, null),
    BW("Botswana", "BWA", 72, null, 8, null),
    BY("Belarus", "BLR", 112, "Беларусь"),
    BZ("Belize", "BLZ", 84, null, 8, null),
    CA("Canada", "CAN", 124, null, 8, null),
    CC("Cocos Islands", "CCK", 166, null, 8, null),
    CD("The Democratic Republic of the Congo", "COD", 180, null, 8, null),
    CF("Central African Republic", "CAF", 140, null, 8, null),
    CG("Congo", "COG", 178, null, 8, null),
    CH("Switzerland", "CHE", 756, "Schweiz"),
    CI("Côte d'Ivoire", "CIV", 384, null, 8, null),
    CK("Cook Islands", "COK", 184, null, 8, null),
    CL("Chile", "CHL", 152, null, 8, null),
    CM("Cameroon", "CMR", 120, null, 8, null),
    CN("China", "CHN", 156, null, 8, null),
    CO("Colombia", "COL", 170, null, 8, null),
    CR("Costa Rica", "CRI", 188, null, 8, null),
    CU("Cuba", "CUB", 192, null, 8, null),
    CV("Cape Verde", "CPV", 132, null, 8, null),
    CW("Curaçao", "CUW", 531, null, 8, null),
    CX("Christmas Island", "CXR", 162, null, 8, null),
    CY("Cyprus", "CYP", 196, "Κύπρος"),
    CZ("Czech Republic", "CZE", 203, "Česko"),
    DE("Germany", "DEU", 276, "Deutschland"),
    DJ("Djibouti", "DJI", 262, null, 8, null),
    DK("Denmark", "DNK", 208, "Danmark"),
    DM("Dominica", "DMA", 212, null, 8, null),
    DO("Dominican Republic", "DOM", 214, "República Dominicana"),
    DZ("Algeria", "DZA", 12, "الجزائر"),
    EC("Ecuador", "ECU", 218, null, 8, null),
    EE("Estonia", "EST", 233, "Eesti"),
    EG("Egypt", "EGY", 818, "مصر"),
    EH("Western Sahara", "ESH", 732, null, 8, null),
    ER("Eritrea", "ERI", 232, null, 8, null),
    ES("Spain", "ESP", 724, "España"),
    ET("Ethiopia", "ETH", 231, "ኢትዮጵያ"),
    FI("Finland", "FIN", 246, "Suomi"),
    FJ("Fiji", "FJI", 242, null, 8, null),
    FK("Falkland Islands", "FLK", 238, null, 8, null),
    FM("Federated States of Micronesia", "FSM", 583, null, 8, null),
    FO("Faroe Islands", "FRO", 234, null, 8, null),
    FR("France", "FRA", 250, null, 8, null),
    GA("Gabon", "GAB", 266, null, 8, null),
    GB("United Kingdom", "GBR", 826, null, 8, null),
    GD("Grenada", "GRD", 308, null, 8, null),
    GE("Georgia", "GEO", 268, "საქართველო"),
    GF("French Guiana", "GUF", 254, null, 8, null),
    GG("Guemsey", "GGY", 831, null, 8, null),
    GH("Ghana", "GHA", 288, null, 8, null),
    GI("Gibraltar", "GIB", 292, null, 8, null),
    GL("Greenland", "GRL", 304, null, 8, null),
    GM("Gambia", "GMB", 270, null, 8, null),
    GN("Guinea", "GIN", 324, null, 8, null),
    GP("Guadeloupe", "GLP", 312, null, 8, null),
    GQ("Equatorial Guinea", "GNQ", 226, null, 8, null),
    GR("Greece", "GRC", 300, "Ελλάδα"),
    GS("South Georgia and the South Sandwich Islands", "SGS", 239, null, 8, null),
    GT("Guatemala", "GTM", 320, null, 8, null),
    GU("Guam", "GUM", 316, null, 8, null),
    GW("Guinea-Bissau", "GNB", 624, null, 8, null),
    GY("Guyana", "GUY", 328, null, 8, null),
    HK("Hong Kong", "HKG", 344, null, 8, null),
    HM("Heard Island and McDonald Islands", "HMD", 334, null, 8, null),
    HN("Honduras", "HND", 340, null, 8, null),
    HR("Croatia", "HRV", 191, "Hrvatska"),
    HT("Haiti", "HTI", 332, null, 8, null),
    HU("Hungary", "HUN", 348, "Magyarország"),
    ID("Indonesia", "IDN", 360, null, 8, null),
    IE("Ireland", "IRL", 372, "Éire"),
    IL("Israel", "ISR", 376, "מדינת ישראל"),
    IM("Isle of Man", "IMN", 833, null, 8, null),
    IN("India", "IND", 356, "भारत"),
    IO("British Indian Ocean Territory", "IOT", 86, null, 8, null),
    IQ("Iraq", "IRQ", 368, null, 8, null),
    IR("Islamic Republic of Iran", "IRN", 364, null, 8, null),
    IS("Iceland", "ISL", 352, "Ísland"),
    IT("Italy", "ITA", 380, "Italia"),
    JE("Jersey", "JEY", 832, null, 8, null),
    JM("Jamaica", "JAM", 388, null, 8, null),
    JO("Jordan", "JOR", 400, "الأردن"),
    JP("Japan", "JPN", 392, "日本"),
    KE("Kenya", "KEN", 404, null, 8, null),
    KG("Kyrgyzstan", "KGZ", 417, null, 8, null),
    KH("Cambodia", "KHM", 116, null, 8, null),
    KI("Kiribati", "KIR", 296, null, 8, null),
    KM("Comoros", "COM", 174, null, 8, null),
    KN("Saint Kitts and Nevis", "KNA", 659, null, 8, null),
    KP("Democratic People's Republic of Korea", "PRK", 408, null, 8, null),
    KR("Republic of Korea", "KOR", 410, "한국"),
    KW("Kuwait", "KWT", 414, null, 8, null),
    KY("Cayman Islands", "CYM", 136, null, 8, null),
    KZ("Kazakhstan", "KAZ", 398, null, 8, null),
    LA("Lao People's Democratic Republic", "LAO", 418, null, 8, null),
    LB("Lebanon", "LBN", 422, null, 8, null),
    LC("Saint Lucia", "LCA", 662, null, 8, null),
    LI("Liechtenstein", "LIE", 438, null, 8, null),
    LK("Sri Lanka", "LKA", 144, null, 8, null),
    LR("Liberia", "LBR", 430, null, 8, null),
    LS("Lesotho", "LSO", 426, null, 8, null),
    LT("Lithuania", "LTU", 440, "Lietuva"),
    LU("Luxembourg", "LUX", 442, "Luxemburg"),
    LV("Latvia", "LVA", 428, "Latvija"),
    LY("Libya", "LBY", 434, null, 8, null),
    MA("Morocco", "MAR", 504, "المغرب"),
    MC("Monaco", "MCO", 492, null, 8, null),
    MD("Republic of Moldova", "MDA", 498, null, 8, null),
    ME("Montenegro", "MNE", 499, "Crna Gora"),
    MF("Saint Martin", "MAF", 663, null, 8, null),
    MG("Madagascar", "MDG", 450, null, 8, null),
    MH("Marshall Islands", "MHL", 584, null, 8, null),
    MK("The former Yugoslav Republic of Macedonia", "MKD", 807, "Македонија"),
    ML("Mali", "MLI", 466, null, 8, null),
    MM("Myanmar", "MMR", 104, null, 8, null),
    MN("Mongolia", "MNG", 496, "Монгол улс"),
    MO("Macao", "MAC", 446, null, 8, null),
    MP("Northern Mariana Islands", "MNP", 580, null, 8, null),
    MQ("Martinique", "MTQ", 474, null, 8, null),
    MR("Mauritania", "MRT", 478, null, 8, null),
    MS("Montserrat", "MSR", 500, null, 8, null),
    MT("Malta", "MLT", 470, null, 8, null),
    MU("Mauritius", "MUS", 480, null, 8, null),
    MV("Maldives", "MDV", 462, null, 8, null),
    MW("Malawi", "MWI", 454, null, 8, null),
    MX("Mexico", "MEX", 484, "México"),
    MY("Malaysia", "MYS", 458, null, 8, null),
    MZ("Mozambique", "MOZ", 508, null, 8, null),
    NA("Namibia", "NAM", 516, null, 8, null),
    NC("New Caledonia", "NCL", 540, null, 8, null),
    NE("Niger", "NER", 562, null, 8, null),
    NF("Norfolk Island", "NFK", 574, null, 8, null),
    NG("Nigeria", "NGA", 566, null, 8, null),
    NI("Nicaragua", "NIC", 558, null, 8, null),
    NL("Netherlands", "NLD", 528, "Nederland"),
    NO("Norway", "NOR", 578, "Norge"),
    NP("Nepal", "NPL", 524, null, 8, null),
    NR("Nauru", "NRU", 520, null, 8, null),
    NU("Niue", "NIU", 570, null, 8, null),
    NZ("New Zealand", "NZL", 554, "Aotearoa"),
    OM("Oman", "OMN", 512, "عمان"),
    PA("Panama", "PAN", 591, null, 8, null),
    PE("Peru", "PER", 604, "Perú"),
    PF("French Polynesia", "PYF", 258, null, 8, null),
    PG("Papua New Guinea", "PNG", 598, null, 8, null),
    PH("Philippines", "PHL", 608, "Pilipinas"),
    PK("Pakistan", "PAK", 586, "پاکستان"),
    PL("Poland", "POL", 616, "Polska"),
    PM("Saint Pierre and Miquelon", "SPM", 666, null, 8, null),
    PN("Pitcairn", "PCN", 612, null, 8, null),
    PR("Puerto Rico", "PRI", 630, null, 8, null),
    PS("Occupied Palestinian Territory", "PSE", 275, null, 8, null),
    PT("Portugal", "PRT", 620, null, 8, null),
    PW("Palau", "PLW", 585, null, 8, null),
    PY("Paraguay", "PRY", 600, "Paraguái"),
    QA("Qatar", "QAT", 634, "قطر"),
    RE("Réunion", "REU", 638, null, 8, null),
    RO("Romania", "ROU", 642, "România"),
    RS("Serbia", "SRB", 688, "Србија"),
    RU("Russian Federation", "RUS", 643, "Россия"),
    RW("Rwanda", "RWA", 646, null, 8, null),
    SA("Saudi Arabia", "SAU", 682, "السعودية"),
    SB("Solomon Islands", "SLB", 90, null, 8, null),
    SC("Seychelles", "SYC", 690, null, 8, null),
    SD("Sudan", "SDN", 729, null, 8, null),
    SE("Sweden", "SWE", 752, "Sverige"),
    SG("Singapore", "SGP", 702, "新加坡"),
    SH("Saint Helena, Ascension and Tristan da Cunha", "SHN", 654, null, 8, null),
    SI("Slovenia", "SVN", 705, "Slovenija"),
    SJ("Svalbard and Jan Mayen", "SJM", 744, null, 8, null),
    SK("Slovakia", "SVK", 703, "Slovensko"),
    SL("Sierra Leone", "SLE", 694, null, 8, null),
    SM("San Marino", "SMR", 674, null, 8, null),
    SN("Senegal", "SEN", 686, null, 8, null),
    SO("Somalia", "SOM", 706, null, 8, null),
    SR("Suriname", "SUR", 740, null, 8, null),
    SS("South Sudan", "SSD", 728, null, 8, null),
    ST("Sao Tome and Principe", "STP", 678, null, 8, null),
    SV("El Salvador", "SLV", 222, null, 8, null),
    SX("Sint Maarten", "SXM", 534, null, 8, null),
    SY("Syrian Arab Republic", "SYR", 760, null, 8, null),
    SZ("Swaziland", "SWZ", 748, null, 8, null),
    TC("Turks and Caicos Islands", "TCA", 796, null, 8, null),
    TD("Chad", "TCD", 148, null, 8, null),
    TF("French Southern Territories", "ATF", 260, null, 8, null),
    TG("Togo", "TGO", 768, null, 8, null),
    TH("Thailand", "THA", 764, "ประเทศไทย"),
    TJ("Tajikistan", "TJK", 762, null, 8, null),
    TK("Tokelau", "TKL", 772, null, 8, null),
    TL("Timor-Leste", "TLS", 626, null, 8, null),
    TM("Turkmenistan", "TKM", 795, null, 8, null),
    TN("Tunisia", "TUN", 788, "تونس"),
    TO("Tonga", "TON", 776, null, 8, null),
    TR("Turkey", "TUR", 792, "Türkiye"),
    TT("Trinidad and Tobago", "TTO", 780, null, 8, null),
    TV("Tuvalu", "TUV", 798, null, 8, null),
    TW("Taiwan, Province of China", "TWN", 158, null, 8, null),
    TZ("United Republic of Tanzania", "TZA", 834, null, 8, null),
    UA("Ukraine", "UKR", 804, "Україна"),
    UG("Uganda", "UGA", 800, null, 8, null),
    UM("United States Minor Outlying Islands", "UMI", 581, null, 8, null),
    US("United States", "USA", 840, null, 8, null),
    UY("Uruguay", "URY", 858, null, 8, null),
    UZ("Uzbekistan", "UZB", 860, null, 8, null),
    VA("Holy See", "VAT", 336, null, 8, null),
    VC("Saint Vincent and the Grenadines", "VCT", 670, null, 8, null),
    VE("Bolivarian Republic of Venezuela", "VEN", 862, null, 8, null),
    VG("British Virgin Islands", "VGB", 92, null, 8, null),
    VI("Virgin Islands, U.S.", "VIR", 850, null, 8, null),
    VN("Viet Nam", "VNM", 704, "Việt Nam"),
    VU("Vanuatu", "VUT", 548, null, 8, null),
    WF("Wallis and Futuna", "WLF", 876, null, 8, null),
    WS("Samoa", "WSM", 882, null, 8, null),
    YE("Yemen", "YEM", 887, null, 8, null),
    YT("Mayotte", "MYT", 175, null, 8, null),
    ZA("South Africa", "ZAF", 710, "Suid-Afrika"),
    ZM("Zambia", "ZMB", 894, null, 8, null),
    ZW("Zimbabwe", "ZWE", 716, null, 8, null);
    
    public static final Companion Companion = null;
    /* access modifiers changed from: private */
    public static final HashMap<String, CountryCode> f = null;
    /* access modifiers changed from: private */
    public static final HashMap<Integer, CountryCode> g = null;
    private final String b;
    private final String c;
    private final int d;
    private final String e;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* access modifiers changed from: private */
        public final HashMap<String, CountryCode> a() {
            return CountryCode.f;
        }

        /* access modifiers changed from: private */
        public final HashMap<Integer, CountryCode> b() {
            return CountryCode.g;
        }
    }

    static {
        int i;
        CountryCode[] values;
        Companion = new Companion(null);
        f = new HashMap<>();
        g = new HashMap<>();
        for (CountryCode countryCode : values()) {
            Companion.a().put(countryCode.c, countryCode);
            Companion.b().put(Integer.valueOf(countryCode.d), countryCode);
        }
    }

    protected CountryCode(String str, String str2, int i, String str3) {
        Intrinsics.checkParameterIsNotNull(str, "englishName");
        Intrinsics.checkParameterIsNotNull(str2, "alpha3");
        Intrinsics.checkParameterIsNotNull(str3, "nativeName");
        this.b = str;
        this.c = str2;
        this.d = i;
        this.e = str3;
    }

    public final String getAlpha2() {
        return name();
    }

    public final String getAlpha3() {
        return this.c;
    }

    public final String getDisplayName() {
        if (!(!Intrinsics.areEqual(this.b, this.e))) {
            return this.b;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("");
        sb.append(this.b);
        sb.append(" | ");
        sb.append(this.e);
        return sb.toString();
    }

    public final String getNativeName() {
        return this.e;
    }
}
