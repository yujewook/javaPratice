package test;
/*
 * deepCopy를 이용 
 * 1. 복사 생성자를 사용해서 deepCopy 구현 해보기
 * 2. MyString_overConstructor( MyString_overConstructor rhs ) 생성자를 만들고
 * 3. deepCopy 메소드 생성해서 deepCopy 되게 하기~
 */
public class MyString_overConstructor {
	private byte[] string;
	//기본생성자
	public MyString_overConstructor() {
		string = null;
	}
	//생성자overloading
	public MyString_overConstructor(String param) {
		this();//기본생성자 호출
		this.setString(param);
	}
	//생성자overloading
	public MyString_overConstructor(int param) {
		this.setString(String.valueOf(param));
	}
	//생성자overloading
	public MyString_overConstructor(MyString_overConstructor rhs) {
		this();
		deepCopy(rhs);
	}
	
	public void deepCopy(MyString_overConstructor rhs) {
		this.string = rhs.string;
	}
	
	public String getString() {
		//nullpointerException 방지
		if(this.string == null || string.length == 0 ) {
			return "null";
		}
		return new String(this.string);
	}
	
	public void setString(String param) {
		this.string = param.getBytes(); 
	}
	
	
	public static void main(String[] args) {
		MyString_overConstructor str = new MyString_overConstructor();
		str.setString("hello");
		System.out.println(str.getString()); //hello
		MyString_overConstructor str1 = new MyString_overConstructor(str);
		str.setString("world");
		System.out.println("deepCopy 이후 str \t" + str.getString()); //world
		System.out.println("deepCopy 이후 str1 \t"+str1.getString()); //hello
	}

}
