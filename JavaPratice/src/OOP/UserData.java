package OOP;


class UserData extends MyNode {
    UserData(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }
    String name;
    String phone;
    
	@Override
	String getKey() {
		// TODO Auto-generated method stub
		return name;
	}
}
