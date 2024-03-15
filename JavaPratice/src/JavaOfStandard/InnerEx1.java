package JavaOfStandard;

class InnerEx1 {
	class InstanceInner {
		int iv = 100;
		//static int cv = 100 ; //static 변수 선언 못함
		final static int CONST = 100;
	}
	
	static class StaticInner{
		int iv = 200;
		static int cv = 200;
	}
	
	void myMethod() {
		class LoaclInner{
			int iv = 300;
//			static int cv = 300;
			final static int CONST = 300;
			
		}
	}

	public static void main(String[] args) {
		System.out.println(InstanceInner.CONST);
		System.out.println(StaticInner.cv);

	}

}
