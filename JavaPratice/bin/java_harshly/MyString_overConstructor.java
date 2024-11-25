package test;
/*
 * deepCopy�� �̿� 
 * 1. ���� �����ڸ� ����ؼ� deepCopy ���� �غ���
 * 2. MyString_overConstructor( MyString_overConstructor rhs ) �����ڸ� �����
 * 3. deepCopy �޼ҵ� �����ؼ� deepCopy �ǰ� �ϱ�~
 */
public class MyString_overConstructor {
	private byte[] string;
	//�⺻������
	public MyString_overConstructor() {
		string = null;
	}
	//������overloading
	public MyString_overConstructor(String param) {
		this();//�⺻������ ȣ��
		this.setString(param);
	}
	//������overloading
	public MyString_overConstructor(int param) {
		this.setString(String.valueOf(param));
	}
	//������overloading
	public MyString_overConstructor(MyString_overConstructor rhs) {
		this();
		deepCopy(rhs);
	}
	
	public void deepCopy(MyString_overConstructor rhs) {
		this.string = rhs.string;
	}
	
	public String getString() {
		//nullpointerException ����
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
		System.out.println("deepCopy ���� str \t" + str.getString()); //world
		System.out.println("deepCopy ���� str1 \t"+str1.getString()); //hello
	}

}
