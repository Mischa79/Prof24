package Algorithms.lesson2.Multithreading;

//Создание потока путем реализации интерфейса Runnable
//Erstellen eines Threads durch Implementierung der Runnable-Schnittstelle
class MyThread implements Runnable {
    String thrdName;

    MyThread(String name) {
        thrdName = name;
    }

    @Override
    public void run() {
        System.out.println(thrdName + " - Start");//запуск
        try {
            for (int count = 0; count < 10; count++) {
                Thread.sleep(400);
                System.out.println("B " + thrdName + ", Schalter: " + count);//счетчик
            }
        } catch (InterruptedException exc) {
            System.out.println(thrdName + " - unterbrochen");//прерван
        }
        System.out.println(thrdName + " - Fertigstellung");//завершение
    }

}
class UseThreads {
    public static void main(String[] args) {
        System.out.println("Hauptthread starten");//запуск основного потока
        MyThread mt = new MyThread("Entstandener Bach(Thread) # 1");//Порожденный поток
        Thread newThrd = new Thread(mt);
        newThrd.start();

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
