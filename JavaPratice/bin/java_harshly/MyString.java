package test;
/*
 * ������ overLoading�� ���� �ǽ�
 * 1. string ���� byte ������ ����ȯ
 * 2. int ���� byte ������ ����ȯ
 * 
 */
public class MyString {
	private byte[] string;
	//�⺻������
	public MyString() {
		string = null;
	}
	//������overloading
	public MyString(String param) {
		this();//�⺻������ ȣ��
		this.setString(param);
	}
	//������overloading
	public MyString(int param) {
		this.setString(String.valueOf(param));
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
		MyString str = new MyString();
		str.setString("hi");
		System.out.println(str.getString());
		MyString str1 = new MyString("test");
		System.out.println(str1.getString());
		MyString str2 = new MyString(512);
		System.out.println(str2.getString());
	}

}
