package java_harshly_part2;
/*
 * 1. 상속을 통한 미래 시점에 대한 코드 이해를 위한 코드
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
		System.out.print("부모" + this.field);
	}
	
	public Boolean onSetData(int param) {
		System.out.print("부모 onSetData");
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
		System.out.print("자식 onSetData");
		Boolean output = true;
		if(param < 0) {
			output = false;
		}else {
			super.field = 1;
		}
		System.out.print("자식 onSetData output ::: "+output);
		return output;
	}
	public static void main(String[] args) {
		Test1 t1 = new Test_extend();
		t1.onSetData(-1);
	}

}
