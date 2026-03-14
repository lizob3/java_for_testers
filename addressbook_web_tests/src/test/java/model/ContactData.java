package model;

public record ContactData(
        String id,
        String firstName,
        String lastName,
        String address,
        String email,
        String email2,
        String email3,
        String workPhone,
        String photo,
        String mobilePhone,
        String homePhone
) {

    public ContactData() {
        this("", "", "", "", "", "", "", "", "", "", "");
    }

    public ContactData withId(String id) {
        return new ContactData(id, this.firstName, this.lastName, this.address, this.email, this.email2, this.email3, this.workPhone, this.photo, this.mobilePhone, this.homePhone);
    }

    public ContactData withFirstName(String firstName) {
        return new ContactData(this.id, firstName, this.lastName, this.address, this.email, this.email2, this.email3, this.workPhone, this.photo, this.mobilePhone, this.homePhone);
    }

    public ContactData withLastName(String lastName) {
        return new ContactData(this.id, this.firstName, lastName, this.address, this.email, this.email2, this.email3, this.workPhone, this.photo, this.mobilePhone, this.homePhone);
    }

    public ContactData withAddress(String address) {
        return new ContactData(this.id, this.firstName, this.lastName, address, this.email, this.email2, this.email3, this.workPhone, this.photo, this.mobilePhone, this.homePhone);
    }

    public ContactData withEmail(String email) {
        return new ContactData(this.id, this.firstName, this.lastName, this.address, email, this.email2, this.email3, this.workPhone, this.photo, this.mobilePhone, this.homePhone);
    }

    public ContactData withEmail2(String email2) {
        return new ContactData(this.id, this.firstName, this.lastName, this.address, this.email, email2, this.email3, this.workPhone, this.photo, this.mobilePhone, this.homePhone);
    }

    public ContactData withEmail3(String email3) {
        return new ContactData(this.id, this.firstName, this.lastName, this.address, this.email, this.email2, email3, this.workPhone, this.photo, this.mobilePhone, this.homePhone);
    }

    public ContactData withMobilePhone(String mobilePhone) {
        return new ContactData(this.id, this.firstName, this.lastName, this.address, this.email, this.email2, this.email3, this.workPhone, this.photo, mobilePhone, this.homePhone);
    }

    public ContactData withHomePhone(String homePhone) {
        return new ContactData(this.id, this.firstName, this.lastName, this.address, this.email, this.email2, this.email3, this.workPhone, this.photo, this.mobilePhone, homePhone);
    }

    public ContactData withWorkPhone(String workPhone) {
        return new ContactData(this.id, this.firstName, this.lastName, this.address, this.email, this.email2, this.email3, workPhone, this.photo, this.mobilePhone, this.homePhone);
    }

    public ContactData withPhoto(String photo) {
        return new ContactData(this.id, this.firstName, this.lastName, this.address, this.email, this.email2, this.email3, this.workPhone, photo, this.mobilePhone, this.homePhone);
    }
}