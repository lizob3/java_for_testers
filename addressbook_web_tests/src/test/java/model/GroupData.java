package model;

public record GroupData(String id, String name, String footer, String header) {

    public GroupData() {
        this("", "", "", "");
    }

    public GroupData withId(String id) {
        return new GroupData(id, this.name, this.footer, this.header);
    }

    public GroupData withName(String name) {
        return new GroupData(this.id, name, this.footer, this.header);
    }

    public GroupData withHeader(String header) {
        return new GroupData(this.id, this.name, this.footer, header);
    }

    public GroupData withFooter(String footer) {
        return new GroupData(this.id, this.name, footer, this.header);
    }
}
