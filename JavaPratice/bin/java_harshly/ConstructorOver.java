package test;
class UserData {
	private String name;
	private int age;
	UserData(){
		this.name = "";
		this.age = 0;
		System.out.println("기본생성자 " +this.name+" " +this.age);
	}
	UserData(String name, int age){
		this();// 기본생성자 호출
		System.out.println("ovreLoading 생성자");
		this.name = name;
		this.age = age;
		System.out.println(this.name+" " +this.age);
	}	
}


public class ConstructorOver {
	public static void main(String[] args) {
		UserData ud = new UserData();
		UserData ud2 = new UserData("에이스장시원" , 27);
	}

}
