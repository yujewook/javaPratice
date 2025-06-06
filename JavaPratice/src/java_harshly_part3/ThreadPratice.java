package java_harshly_part3;

class MyThread implements Runnable {
    @Override
    public void run() {
        System.out.println("스레드 실행");
        method1();
        System.out.println("스레드 종료");
    }

    public static void method1() {
        System.out.println("스레드 실행 method1");
    }
}

public class ThreadPratice {
    public static void main(String args[]) {
        System.out.println("main run");
        Runnable myThread = new MyThread();
        Thread tr = new Thread(myThread);
        tr.start();
        try {
            Thread.sleep(500); // Thread.sleep은 static 메서드이므로 클래스명으로 호출해야 합니다.
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("main exit");
    }
}
