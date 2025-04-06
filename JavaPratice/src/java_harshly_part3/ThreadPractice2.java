package java_harshly_part3;

class MyThread2 implements Runnable {
    @Override
    public void run() {
        System.out.println("스레드 실행");
        int count = 0;
        while (!ThreadPractice2.exitFlag) {
            count++;
        }
        System.out.println("스레드 종료 후 count: " + count);
    }

    public static void method1() {
        System.out.println("스레드 실행 method1");
    }
}

public class ThreadPractice2 {
    static volatile boolean exitFlag = false;

    public static void main(String args[]) {
        System.out.println("main run");
        Runnable myThread = new MyThread2();
        Thread tr = new Thread(myThread);
        tr.start();

        try {
            Thread.sleep(100); // Thread.sleep은 static 메서드이므로 클래스명으로 호출해야 함
            exitFlag = true;
            Thread.sleep(300);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("main exit");
    }
}
