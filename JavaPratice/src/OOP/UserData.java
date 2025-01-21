package OOP;


public class UserData extends MyNode{
    UserData(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }
    String name;
    String phone;
    UserData next;
	@Override
	String getKey() {
		// TODO Auto-generated method stub
		return null;
	}
}
