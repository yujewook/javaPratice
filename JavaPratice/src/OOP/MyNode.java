package OOP;

public abstract class MyNode {
	protected MyNode next;
	public MyNode getNext() {
		return next;
	}
	abstract String getKey();

}
