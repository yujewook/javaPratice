package OOP;


class UserData {
    UserData(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }
    String name;
    String phone;
    UserData next;
}
