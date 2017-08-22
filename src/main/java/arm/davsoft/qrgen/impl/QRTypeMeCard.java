package arm.davsoft.qrgen.impl;

import arm.davsoft.qrgen.api.QRType;

/**
 * @author David Shahbazyan
 * @since Mar 04, 2017
 */
public class QRTypeMeCard implements QRType {
    private String name;
    private String company;
    private String phone;
    private String website;
    private String email;
    private String address;
    private String note;

    public QRTypeMeCard setName(String name) {
        this.name = name;
        return this;
    }

    public QRTypeMeCard setCompany(String company) {
        this.company = company;
        return this;
    }

    public QRTypeMeCard setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public QRTypeMeCard setWebsite(String website) {
        this.website = website;
        return this;
    }

    public QRTypeMeCard setEmail(String email) {
        this.email = email;
        return this;
    }

    public QRTypeMeCard setAddress(String address) {
        this.address = address;
        return this;
    }

    public QRTypeMeCard setNote(String note) {
        this.note = note;
        return this;
    }

    @Override
    public String getData() {
        return String.format("MECARD:N:%s;ORG:%s;TEL:%s;URL:%s;EMAIL:%s;ADR:%s;NOTE:%s;;", name, company, phone, website, email, address, note);
    }
}
