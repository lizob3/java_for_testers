package model;

public record ContactData(String firstName, String lastName, String address, String email, String mobilePhone) {

    public ContactData() {
        this("", "", "", "", "");
    }
}
