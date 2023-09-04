package Algorithms.lesson2.Multithreading;

public class MyThread2 implements Runnable {
    Thread thrd;

    MyThread2(String name) {
        thrd = new Thread(this, name);
    }

    public static MyThread2 createAndStart(String name) {
        MyThread2 myThread2 = new MyThread2(name);
        myThread2.thrd.start();
        return myThread2;
    }

    @Override
    public void run() {
        System.out.println(thrd.getName() + " - Start.");
        try {
            for (int count = 0; count < 10; count++) {
                Thread.sleep(400);
                System.out.println("B " + thrd.getName() + ", Schalter: " + count);//счетчик
            }
        } catch (InterruptedException exc) {
            System.out.println(thrd.getName() + " - unterbrochen");//прерван
        }
        System.out.println(thrd.getName() + " - Fertigstellung");//завершение
    }

}
class ThreadVariations{
    public static void main(String[] args) {
        System.out.println("Hauptthread starten");

        MyThread2 mt = MyThread2.createAndStart("Entstandener Bach(Thread) # 1");

        for (int i = 0; i < 50; i++){
            System.out.println(".");
            try {
                Thread.sleep(100);
            }
            catch (InterruptedException exc){
                System.out.println("Unterbrochen Haupttread");
            }
        }
        System.out.println("Fertigstellung Haupttread");
    }
}


