package com.stripe.model;

public class Card extends ExternalAccount {
    String addressCity;
    String addressCountry;
    String addressLine1;
    String addressLine2;
    String addressState;
    String addressZip;
    String country;
    Integer expMonth;
    Integer expYear;
    String fingerprint;
    String last4;
    String name;
    String type;

    public Integer getExpMonth() {
        return this.expMonth;
    }

    public Integer getExpYear() {
        return this.expYear;
    }

    public String getLast4() {
        return this.last4;
    }

    public String getCountry() {
        return this.country;
    }

    public String getType() {
        return this.type;
    }

    public String getName() {
        return this.name;
    }

    public String getAddressLine1() {
        return this.addressLine1;
    }

    public String getAddressLine2() {
        return this.addressLine2;
    }

    public String getAddressCity() {
        return this.addressCity;
    }

    public String getAddressZip() {
        return this.addressZip;
    }

    public String getAddressState() {
        return this.addressState;
    }

    public String getAddressCountry() {
        return this.addressCountry;
    }

    public String getFingerprint() {
        return this.fingerprint;
    }
}
