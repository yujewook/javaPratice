package java_harshly;

import java.util.Scanner;

/* 1. Shape 기본클래스 정의 하고 render()메소드 구현
 * 2. 파생클래스로 Trianlge과 Rectangle정의
 * 3. main 에서 사용자 입력을 받고 입력에 따라 두 객체를 생성한 후 Shape.render()호출한다.
 * -> Shape.render() 호출시 적절한 인스턴스의 rander함수 호출
 */
class Shape {
	private int point;
	
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	
	Shape(){
		
	}
	Shape(int param){
		this.point = param;
	}
	void render(int param) {
		this.point = param;
		System.out.print(param+"은 삼각형도 사각형도 아닙니다.");
	}
}
class Trianlge extends Shape {
	Trianlge(){
		
	}
	Trianlge(int param){
		super(param);
	}
	@Override
	void render(int param) {
		for(int i = 0 ; i < 3 ; i++ ) {
			for(int j = 0 ; j <= i ; j++ ) {
				System.out.print("*");
			}
			System.out.println();
		}
		System.out.print(param+"은 삼각형입니다.");
	}
}
class Rectangle extends Shape {
	Rectangle(){
		
	}
	Rectangle(int param){
		super(param);
	}
	@Override
	void render(int param) {
		for(int i = 0 ; i < 3 ; i++ ) {
			for(int j = 0 ; j < 4 ; j++ ) {
				System.out.print("*");
			}
			System.out.println();
		}
		System.out.print(param+"은 사각형입니다.");
	}
}


public class ExtendPratice {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int input = sc.nextInt();
		Shape shape = new Shape(input);
		if(input == 3) {
			shape = new Trianlge(input);
		} else if(input == 4) {
			shape = new Rectangle(input);
		} 
		shape.render(input);
		if(null != shape ) {
			System.out.println(shape.getPoint()+" 갯수의 점");
		}
		 
	}

}
