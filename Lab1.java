

public class Lab1 {


    public static class  MyRunnable implements Runnable{

        int interval ;
        public MyRunnable (int interval){
            this.interval = interval;
        }

        public void run(){

            System.out.println(Thread.currentThread().getName());

            for  (int i=0; i<10;i++){
                System.out.println(Thread.currentThread().getName()+ " count = "+ i);

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

        Thread t0= new Thread(new MyRunnable(500));
        t0.start();

        Thread t1= new Thread(new MyRunnable(1000));
        t1.start();

        Thread t2= new Thread(new MyRunnable(2000));
        t2.start();

        Thread t3= new Thread(new MyRunnable(3000));
        t3.start();


    }
}
