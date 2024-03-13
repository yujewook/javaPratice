package JavaOfStandard;

public class FighterTest {
	public static void main(String[]args) {
		Fighter f = new Fighter();
		if (f instanceof Unit) {
			System.out.println("f는 Unit의 자손");
		}
		if (f instanceof Fightable) {
			System.out.println("f는 Fightable의 interface 구현");
		}
		if (f instanceof Moveable) {
			System.out.println("f는 Moveable의 interface 구현");
		}
		if (f instanceof Attackable) {
			System.out.println("f는 Attackable의 interface 구현");
		}
		if (f instanceof Object) {
			System.out.println("f는 Attackable의 자손");
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
