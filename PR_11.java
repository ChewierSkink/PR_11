import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;


public class PR_11 {
    public static void begin(int horses_number) {
        Object ob = new Object();
        List<Thread> horses = new ArrayList<Thread>(horses_number);
        String number;
        for (int i = 1; i < horses_number + 1; i++) {
            Thread horse_i = new Thread(new HorseThread(ob, "Лошадь " + i));
            horses.add(horse_i);
        }
        for (int i = 0; i < horses_number; i++) {
            horses.get(i).start();
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите кол-во лошадей (n): ");
        int n = in.nextInt();
        begin(n);
    }
}


class HorseThread implements Runnable {
    private Object ob;
    private String name;

    public HorseThread(Object ob, String name) {
        this.ob = ob;
        this.name = name;
    }

    @Override
    public void run() {
        synchronized (ob) {
            String s = "";
            for (int i = 0; i < 100001; i++) {
                if (i == 100000) {
                    s = " финишировала!";
                    System.out.println(name + s);
                    try {
                        Thread.sleep(1000);
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    ob.notify();
                    try {
                        ob.wait(1000);
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}