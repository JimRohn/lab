

public class Lab1_part2 {


    public static class MyRunnable implements Runnable {

        int interval;
        Thread toWaitFor;

        public MyRunnable(int interval, Thread toWaitFor) {
            this.interval = interval;
            this.toWaitFor = toWaitFor;
        }

        public void run() {

            System.out.println(Thread.currentThread().getName());

            try {
                if (toWaitFor !=null) {
                    toWaitFor.join();
                }

            } catch (InterruptedException x) {
            }


            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + " count = " + i);

                try {
                    Thread.sleep(interval);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }

    public static void main(String[] args) {

        System.out.println(Thread.currentThread().getName());

        Thread t0 = new Thread(new MyRunnable(500, null));
        t0.start();

        Thread t1 = new Thread(new MyRunnable(1000,t0));
        t1.start();

        Thread t2 = new Thread(new MyRunnable(2000, null));
        t2.start();

        Thread t3 = new Thread(new MyRunnable(3000,t2));
        t3.start();


    }
}
