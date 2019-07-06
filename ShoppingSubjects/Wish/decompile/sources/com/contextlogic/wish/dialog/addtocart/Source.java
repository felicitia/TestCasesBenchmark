package com.contextlogic.wish.dialog.addtocart;

public enum Source {
    DEFAULT,
    FREE_GIFT,
    FREE_SAMPLE,
    GROUP_BUY_JOIN,
    GROUP_BUY_CREATE,
    DAILY_GIVEAWAY,
    POINTS_REDEMPTION,
    BRANDED;

    public static Source fromString(String str) {
        if (str == null) {
            return null;
        }
        String lowerCase = str.toLowerCase();
        char c = 65535;
        switch (lowerCase.hashCode()) {
            case -433836253:
                if (lowerCase.equals("free_gift")) {
                    c = 1;
                    break;
                }
                break;
            case 31558365:
                if (lowerCase.equals("free_sample")) {
                    c = 2;
                    break;
                }
                break;
            case 112232085:
                if (lowerCase.equals("group_buy_create")) {
                    c = 4;
                    break;
                }
                break;
            case 137728614:
                if (lowerCase.equals("branded")) {
                    c = 7;
                    break;
                }
                break;
            case 603673859:
                if (lowerCase.equals("group_buy_join")) {
                    c = 3;
                    break;
                }
                break;
            case 1153828615:
                if (lowerCase.equals("points_redemption")) {
                    c = 6;
                    break;
                }
                break;
            case 1541176549:
                if (lowerCase.equals("daily_giveaway")) {
                    c = 5;
                    break;
                }
                break;
            case 1544803905:
                if (lowerCase.equals("default")) {
                    c = 0;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return DEFAULT;
            case 1:
                return FREE_GIFT;
            case 2:
                return FREE_SAMPLE;
            case 3:
                return GROUP_BUY_JOIN;
            case 4:
                return GROUP_BUY_CREATE;
            case 5:
                return DAILY_GIVEAWAY;
            case 6:
                return POINTS_REDEMPTION;
            case 7:
                return BRANDED;
            default:
                return DEFAULT;
        }
    }
}
