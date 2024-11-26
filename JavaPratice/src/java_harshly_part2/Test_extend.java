package java_harshly_part2;
/*
 * 1. ����� ���� �̷� ������ ���� �ڵ� ���ظ� ���� �ڵ�
 * 
 */
class Test1 {
	protected int field;
	
	Test1(){
		
	}
	
	Test1(int param){
		this.field = param;
	}
	
	public void setData() {
		onSetData(this.field);
		System.out.print("�θ�" + this.field);
	}
	
	public Boolean onSetData(int param) {
		System.out.print("�θ� onSetData");
		return true;
	}
}

public class Test_extend extends Test1 {
	Test_extend(){
		
	}
	
	Test_extend(int param){
		super(param);
	}
	@Override
	public Boolean onSetData(int param) {
		System.out.print("�ڽ� onSetData");
		Boolean output = true;
		if(param < 0) {
			output = false;
		}else {
			super.field = 1;
		}
		System.out.print("�ڽ� onSetData output ::: "+output);
		return output;
	}
	public static void main(String[] args) {
		Test1 t1 = new Test_extend();
		t1.onSetData(-1);
	}

}
