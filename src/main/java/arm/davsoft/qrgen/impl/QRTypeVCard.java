package arm.davsoft.qrgen.impl;

import arm.davsoft.qrgen.api.QRType;

/**
 * @author David Shahbazyan
 * @since Mar 04, 2017
 */
public class QRTypeVCard implements QRType {
    private String name;
    private String company;
    private String title;
    private String phone;
    private String website;
    private String email;
    private String address;
    private String note;

    public QRTypeVCard setName(String name) {
        this.name = name;
        return this;
    }

    public QRTypeVCard setCompany(String company) {
        this.company = company;
        return this;
    }

    public QRTypeVCard setTitle(String title) {
        this.title = title;
        return this;
    }

    public QRTypeVCard setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public QRTypeVCard setWebsite(String website) {
        this.website = website;
        return this;
    }

    public QRTypeVCard setEmail(String email) {
        this.email = email;
        return this;
    }

    public QRTypeVCard setAddress(String address) {
        this.address = address;
        return this;
    }

    public QRTypeVCard setNote(String note) {
        this.note = note;
        return this;
    }

    @Override
    public String getData() {
        return new StringBuilder("BEGIN:VCARD")
                .append('\n').append("VERSION:3.0")
                .append('\n').append("N:").append(name)
                .append('\n').append("ORG:").append(company)
                .append('\n').append("TITLE:").append(title)
                .append('\n').append("TEL:").append(phone)
                .append('\n').append("URL:").append(website)
                .append('\n').append("EMAIL:").append(email)
                .append('\n').append("ADR:").append(address)
                .append('\n').append("NOTE:").append(note)
                .append('\n').append("END:VCARD").toString();
    }
}
