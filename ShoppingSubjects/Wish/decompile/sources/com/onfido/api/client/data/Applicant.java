package com.onfido.api.client.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Applicant implements Serializable {
    @SerializedName("addresses")
    @Expose
    private List<Object> addresses;
    @SerializedName("country")
    @Expose
    private Locale country;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("dob")
    @Expose
    private Date dateOfBirth;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("href")
    @Expose
    private String href;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("id_numbers")
    @Expose
    private List<Object> idNumbers;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("middle_name")
    @Expose
    private String middleName;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("telephone")
    @Expose
    private String telephone;
    @SerializedName("title")
    @Expose
    private String title;

    public static final class Builder {
        /* access modifiers changed from: private */
        public List<Object> addresses;
        /* access modifiers changed from: private */
        public Locale country;
        /* access modifiers changed from: private */
        public String createdAt;
        /* access modifiers changed from: private */
        public Date dateOfBirth;
        /* access modifiers changed from: private */
        public String email;
        /* access modifiers changed from: private */
        public String firstName;
        /* access modifiers changed from: private */
        public String gender;
        /* access modifiers changed from: private */
        public String href;
        /* access modifiers changed from: private */
        public String id;
        /* access modifiers changed from: private */
        public List<Object> idNumbers;
        /* access modifiers changed from: private */
        public String lastName;
        /* access modifiers changed from: private */
        public String middleName;
        /* access modifiers changed from: private */
        public String mobile;
        /* access modifiers changed from: private */
        public String telephone;
        /* access modifiers changed from: private */
        public String title;

        private Builder() {
            this.idNumbers = new ArrayList();
            this.addresses = new ArrayList();
        }

        public Builder withId(String str) {
            this.id = str;
            return this;
        }

        public Applicant build() {
            return new Applicant(this);
        }
    }

    private Applicant(Builder builder) {
        this.idNumbers = new ArrayList();
        this.addresses = new ArrayList();
        setId(builder.id);
        setCreatedAt(builder.createdAt);
        setHref(builder.href);
        setTitle(builder.title);
        setFirstName(builder.firstName);
        setMiddleName(builder.middleName);
        setLastName(builder.lastName);
        setGender(builder.gender);
        setDateOfBirth(builder.dateOfBirth);
        setTelephone(builder.telephone);
        setCountry(builder.country);
        setMobile(builder.mobile);
        setEmail(builder.email);
        setIdNumbers(builder.idNumbers);
        setAddresses(builder.addresses);
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public void setCreatedAt(String str) {
        this.createdAt = str;
    }

    public void setHref(String str) {
        this.href = str;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public void setFirstName(String str) {
        this.firstName = str;
    }

    public void setMiddleName(String str) {
        this.middleName = str;
    }

    public void setLastName(String str) {
        this.lastName = str;
    }

    public void setGender(String str) {
        this.gender = str;
    }

    public void setDateOfBirth(Date date) {
        this.dateOfBirth = date;
    }

    public void setTelephone(String str) {
        this.telephone = str;
    }

    public void setCountry(Locale locale) {
        this.country = locale;
    }

    public void setMobile(String str) {
        this.mobile = str;
    }

    public void setEmail(String str) {
        this.email = str;
    }

    public void setIdNumbers(List<Object> list) {
        this.idNumbers = list;
    }

    public void setAddresses(List<Object> list) {
        this.addresses = list;
    }
}
