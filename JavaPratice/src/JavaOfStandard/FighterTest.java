package JavaOfStandard;

public class FighterTest {
	public static void main(String[]args) {
		Fighter f = new Fighter();
		if (f instanceof Unit) {
			System.out.println("f�� Unit�� �ڼ�");
		}
		if (f instanceof Fightable) {
			System.out.println("f�� Fightable�� interface ����");
		}
		if (f instanceof Moveable) {
			System.out.println("f�� Moveable�� interface ����");
		}
		if (f instanceof Attackable) {
			System.out.println("f�� Attackable�� interface ����");
		}
		if (f instanceof Object) {
			System.out.println("f�� Attackable�� �ڼ�");
		}
	}
}

class Fighter extends Unit implements Fightable {
	public void move(int x , int y) {
		this.x = 10;
		this.y = 5;
	};
	public void attact(Unit u) {
		u.currentHp = 0;
	};
}

class Unit {
	int currentHp;
	int x ;
	int y ;
}

interface Fightable extends Moveable, Attackable{}
interface Moveable{void move(int x, int y);}
interface Attackable{void attact(Unit u);}
