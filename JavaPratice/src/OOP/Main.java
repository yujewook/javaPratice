package OOP;

public class Main {
    public static void main(String[] args) {
        MyList db = new MyList();
        UserInterface ui = new UserInterface(db);
        ui.run();
    }
}
