package test;
class UserData {
	private String name;
	private int age;
	UserData(){
		this.name = "";
		this.age = 0;
		System.out.println("�⺻������ " +this.name+" " +this.age);
	}
	UserData(String name, int age){
		this();// �⺻������ ȣ��
		System.out.println("ovreLoading ������");
		this.name = name;
		this.age = age;
		System.out.println(this.name+" " +this.age);
	}	
}


public class ConstructorOver {
	public static void main(String[] args) {
		UserData ud = new UserData();
		UserData ud2 = new UserData("���̽���ÿ�" , 27);
	}

}
