package java_harshly;

import java.util.Scanner;

/* 1. Shape �⺻Ŭ���� ���� �ϰ� render()�޼ҵ� ����
 * 2. �Ļ�Ŭ������ Trianlge�� Rectangle����
 * 3. main ���� ����� �Է��� �ް� �Է¿� ���� �� ��ü�� ������ �� Shape.render()ȣ���Ѵ�.
 * -> Shape.render() ȣ��� ������ �ν��Ͻ��� rander�Լ� ȣ��
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
		System.out.print(param+"�� �ﰢ���� �簢���� �ƴմϴ�.");
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
		System.out.print(param+"�� �ﰢ���Դϴ�.");
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
		System.out.print(param+"�� �簢���Դϴ�.");
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
			System.out.println(shape.getPoint()+" ������ ��");
		}
		 
	}

}
