package Algorithms.lesson2.Multithreading;

// Mehrere Threads erstellen
//Создание нескольких потоков
public class MyThread4 implements Runnable {
    Thread thrd;

    MyThread4(String name) {
        thrd = new Thread(this, name);
    }

    public static MyThread4 createAndStart(String name) {
        MyThread4 myThread4 = new MyThread4(name);
        myThread4.thrd.start();
        return myThread4;
    }


    @Override
    public void run() {
        System.out.println(thrd.getName() + " -Start.");
        try {
            for (int count = 0; count < 10; count++) {
                Thread.sleep(400);
                System.out.println("B " + thrd.getName() + ", Schalter: " + count);
            }

        } catch (InterruptedException e) {
            System.out.println(thrd.getName() + " - unterbrochen");
        }
        System.out.println(thrd.getName() + " - Fertigstellung");

    }
}

class MoreThreads {
    public static void main(String[] args) {
        MyThread4 mt1 = MyThread4.createAndStart("Entstandener Thread #1");
        MyThread4 mt2 = MyThread4.createAndStart("Entstandener Thread #2");
        MyThread4 mt3 = MyThread4.createAndStart("Entstandener Thread #3");

        for (int i = 0; i < 50; i++) {
            System.out.println(".");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println("Unterbrochen Haupttread");
            }

        }
        System.out.println("Fertigstellung Haupttread");
    }
}

//Verwendung der isAlive()-Methode.
//Использование метода isAlive().
class MoreThreadsIsAlive {
    public static void main(String[] args) {
        MyThread4 mt1 = MyThread4.createAndStart("Entstandener Thread #1");
        MyThread4 mt2 = MyThread4.createAndStart("Entstandener Thread #2");
        MyThread4 mt3 = MyThread4.createAndStart("Entstandener Thread #3");

        do {
            System.out.println(".");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println("Unterbrochen Haupttread");
            }
        } while (mt1.thrd.isAlive() ||
                mt2.thrd.isAlive() ||
                mt3.thrd.isAlive());
        System.out.println("Fertigstellung Haupttread");
    }
}

//Использование метода join
//Verwendung der join -Methode.
class JoinThreads {
    public static void main(String[] args) {
        System.out.println("Den Hauptthread starten");// Зaпycк основного потока

        MyThread4 mt1 = MyThread4.createAndStart("Entstandener Thread #1");
        MyThread4 mt2 = MyThread4.createAndStart("Entstandener Thread #2");
        MyThread4 mt3 = MyThread4.createAndStart("Entstandener Thread #3");

        try {
            mt1.thrd.join();
            System.out.println("Entstandener Thread #1 - beigefügt");//присоединен
            mt2.thrd.join();
            System.out.println("Entstandener Thread #2 - beigefügt");
            mt3.thrd.join();
            System.out.println("Entstandener Thread #3 - beigefügt");

        } catch (InterruptedException e) {
            System.out.println("Unterbrochen Haupttread");
        }
        System.out.println("Fertigstellung Haupttread");

    }
}

// Демонстрация потоков с разными приоритетами
// Demonstration von Threads mit unterschiedlichen Prioritäten
class Priority implements Runnable {
    int count;
    Thread thrd;

    static boolean stop = false;
    static String currentName;

    Priority(String name) {
        thrd = new Thread(this, name);
        count = 0;
        currentName = name;
    }

    @Override
    public void run() {
        System.out.println(thrd.getName() + " -START.");
        do {
            count++;
            if (currentName.compareTo(thrd.getName()) != 0) {
                currentName = thrd.getName();
                System.out.println("B " + currentName);
            }
        } while (stop == false && count < 10000000);
        stop = true;
        System.out.println("\n" + thrd.getName() + " -unterbrochen");
    }
}

class PriorityDemo {
    public static void main(String[] args) {
        Priority mt1 = new Priority("Hohe Priorität"); // Bыcoкий приоритет
        Priority mt2 = new Priority("Niedrige Priorität"); // Низкий приоритет
        Priority mt3 = new Priority("Normale Priorität #1"); //  Обычный приоритет
        Priority mt4 = new Priority("Normale Priorität #2"); //  Обычный приоритет
        Priority mt5 = new Priority("Normale Priorität #3"); //  Обычный приоритет

        mt1.thrd.setPriority(Thread.NORM_PRIORITY + 2);
        mt2.thrd.setPriority(Thread.NORM_PRIORITY - 2);

        mt1.thrd.start();
        mt2.thrd.start();
        mt3.thrd.start();
        mt4.thrd.start();
        mt5.thrd.start();

        try {
            mt1.thrd.join();
            mt1.thrd.join();
            mt1.thrd.join();
            mt1.thrd.join();
            mt1.thrd.join();
        } catch (InterruptedException e) {
            System.out.println("Hauptthread unterbrochen");// Пpepвaн основной поток

        }
        System.out.println("\nDurchflusszähler mit hoher Priorität: " + mt1.count);// Cчeтчик потока с высоким приоритетом :
        System.out.println("\nDurchflusszähler mit Niedrige Priorität: " + mt2.count);
        System.out.println("\n1. Durchflusszähler mit Normal Priorität: " + mt3.count);//" Cчeтчик 1 - го потока с обычным
        System.out.println("\n2. Durchflusszähler mit Normal Priorität: " + mt4.count);
        System.out.println("\n3. Durchflusszähler mit Normal Priorität: " + mt5.count);

    }
}
//synchronized  для управления доступом
//zur Zugangskontrolle

class SumArray {

    private int sum;

    synchronized int sumArray(int[] nums) {
        sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            System.out.println("Aktueller Summenwert für " + Thread.currentThread().getName() + " Wille " + sum);//Teкyщee значение суммы для

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                System.out.println("Thread unterbrochen");
            }

        }
        return sum;
    }
}

class MyThread5 implements Runnable {
    Thread thrd;
    static SumArray sa = new SumArray();
    int[] a;
    int answer;

    MyThread5(String name, int[] nums) {
        thrd = new Thread(this, name);
        a = nums;
    }

    public static MyThread5 createAndStart(String name, int[] nums) {
        MyThread5 myThrd = new MyThread5(name, nums);
        myThrd.thrd.start();
        return myThrd;
    }

    @Override
    public void run() {
        System.out.println(thrd.getName() + "Start");
        answer = sa.sumArray(a);
        System.out.println("Summa für " + thrd.getName() + " wille " + answer);
        System.out.println(thrd.getName() + " -End");

    }
}

class Sync {
    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4, 5};
        MyThread5 mt1 = MyThread5.createAndStart("Entstandener Thread #1", a);
        MyThread5 mt2 = MyThread5.createAndStart("Entstandener Thread #2", a);

        try {
            mt1.thrd.join();
            mt2.thrd.join();
        } catch (InterruptedException e) {
            System.out.println("Hauptthread unterbrochen");
        }
    }
}

//Verwendung die wait() und notify()-Methodes.
//Использование методов wait() и notify()
class TickTock {
    String state;

    synchronized void tick(boolean running) {
        if (!running) {
            state = "ticked";
            notify();
            return;
        }
        System.out.println("Tick ");
        state = "ticked";
        notify();
        try {
            while (!state.equals("tocked"))
                wait();
        } catch (InterruptedException e) {
            System.out.println("Thread unterbrochen");
        }
    }

    synchronized void tock(boolean running) {
        if (!running) {
            state = "tocked";
            notify();
            return;
        }
        System.out.println("Tock ");
        state = "tocked";
        notify();
        try {
            while (!state.equals("ticked"))
                wait();
        } catch (InterruptedException e) {
            System.out.println("Thread unterbrochen");
        }
    }

}

class MyThread6 implements Runnable {
    Thread thrd;
    TickTock ttob;

    MyThread6(String name, TickTock tt) {
        thrd = new Thread(this, name);
        ttob = tt;
    }

    public static MyThread6 createAndStart(String name, TickTock tt) {
        MyThread6 myThrd6 = new MyThread6(name, tt);
        myThrd6.thrd.start();
        return myThrd6;
    }

    @Override
    public void run() {
        if (thrd.getName().compareTo("Tick") == 0) {
            for (int i = 0; i < 5; i++) ttob.tick(true);
            ttob.tick(false);

        } else {
            for (int i = 0; i < 5; i++) ttob.tock(true);
            ttob.tock(false);
        }
    }

}

class ThreadCom {
    public static void main(String[] args) {
        TickTock tt = new TickTock();
        MyThread6 mt1 = MyThread6.createAndStart("Tick", tt);
        MyThread6 mt2 = MyThread6.createAndStart("Tock", tt);

        try {
            mt1.thrd.join();
            mt2.thrd.join();
        } catch (InterruptedException e) {
            System.out.println("Hauptthread unterbrochen");
        }
    }
}