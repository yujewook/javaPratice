package JavaOfStandard;

public class PolyArgumentTest {
	public static void main(String[] args) {
		Buyer b = new Buyer();
		
		b.buy(new Tv());
		b.buy(new Computer());

		System.out.println("���糲����  "+ b.money +" �Դϴ�");
		System.out.println("���纸�ʽ�  "+ b.bonusPoint +" �Դϴ�");
	}

}

class Product{
	int price;
	int bonusPoint;
	
	Product(int price){
		this.price = price;
		this.bonusPoint = (int) (price/10.0);
	}
}

class Tv extends Product{
	Tv(){
		super(100);
	}
	public String toString() {return "Tv" ; }
}

class Computer extends Product{
	Computer(){
		super(200);
	}
	public String toString() {return "Computer" ; }
}

class Buyer{
	int money = 1000;
	int bonusPoint = 0;
	
	//�Ű������� ������
	void buy(Product p) {
		if( money < p.price) {
			System.out.println("�ܾ׺���");
			return;
		}
		
		money -= p.price;
		bonusPoint += p.bonusPoint;
		System.out.println(p+"��/�� ���ԿϷ�");
	}
	
}

