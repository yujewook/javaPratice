package OOP;

public class Main {
    public static void main(String[] args) {
    	MyList db = new MyList(new MyNode());
    	UserInterface ui = new UserInterface(db);
    	ui.run();
    }
}
