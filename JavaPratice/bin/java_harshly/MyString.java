package test;
/*
 * 생성자 overLoading을 통한 실습
 * 1. string 형을 byte 형으로 형변환
 * 2. int 형을 byte 형으로 형변환
 * 
 */
public class MyString {
	private byte[] string;
	//기본생성자
	public MyString() {
		string = null;
	}
	//생성자overloading
	public MyString(String param) {
		this();//기본생성자 호출
		this.setString(param);
	}
	//생성자overloading
	public MyString(int param) {
		this.setString(String.valueOf(param));
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
		MyString str = new MyString();
		str.setString("hi");
		System.out.println(str.getString());
		MyString str1 = new MyString("test");
		System.out.println(str1.getString());
		MyString str2 = new MyString(512);
		System.out.println(str2.getString());
	}

}
