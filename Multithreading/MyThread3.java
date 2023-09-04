package Algorithms.lesson2.Multithreading;

//Erweiterung der Thread-Klasse.
//Расширение класса Thread.
public class MyThread3 extends Thread {
    MyThread3(String name) {
        super(name);
    }

    @Override
    public void run() {
        System.out.println(getName() + " -Start.");
        for (int count = 0; count < 10; count++) {
            try {
                Thread.sleep(400);
                System.out.println("B " + getName() + ",Schalter:" + count);
            } catch (InterruptedException e) {
                System.out.println(getName() + " - unterbrochen");
            }
            System.out.println(getName() + " - Fertigstellung");

        }
    }
}
    class ExtendThread{
        public static void main(String[] args) {
            System.out.println("Hauptthread starten");

            MyThread3 mt = new MyThread3("Entstandener Bach(Thread) # 1");

            mt.start();
            for (int i = 0; i < 50; i++) {
                System.out.print(".");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    System.out.println("Unterbrochen Haupttread");
                }

            }
            System.out.println("Fertigstellung Haupttread");
        }
    }

