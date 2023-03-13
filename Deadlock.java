package adp.lab2;

public class Deadlock {

  static class SomeClass {

    private final Object o1 = "o1";
    private final Object o2 = "o2";

    public void method1() {
      System.out.println( Thread.currentThread().getName() + " attempting to enter method 1");
      synchronized(o1) {
        randomPause();
        synchronized(o2) {
          System.out.println( Thread.currentThread().getName() + " inside method 1");
        }
      }
    }

    public void method2() {
      System.out.println( Thread.currentThread().getName() + " attempting to enter method 2");
      synchronized(o2) {
        randomPause();
        synchronized(o1) {
          System.out.println( Thread.currentThread().getName() + " inside method 2");
        }
      }
    }

    private void randomPause() {
      try {
        Thread.sleep((long) (Math.random() * 10));
      } catch (InterruptedException e) {
      }       
    }
    
  }

  public static class OneCaller implements Runnable {

    SomeClass someClass;
    
    public OneCaller(SomeClass someClass) {
      this.someClass = someClass;
    }
    
    @Override
    public void run() {
      for(int i = 0; i < 10; i++) {
        someClass.method1();
      }
    }    
  }
  
  public static class TwoCaller implements Runnable {

    SomeClass someClass;
    
    public TwoCaller(SomeClass someClass) {
      this.someClass = someClass;
    }
    
    @Override
    public void run() {
      for(int i = 0; i < 10; i++) {
        someClass.method2();
      }
    }
    
  }

  
  public static void main(String[] args) {
    SomeClass someClass = new SomeClass();
    
    Thread[] threads = new Thread[10];
    for (int i = 0; i < threads.length; i += 2) {
      threads[i] = new Thread(new OneCaller(someClass));
      threads[i + 1] = new Thread(new TwoCaller(someClass));
    }
    for (int i = 0; i < threads.length; i++) {
      threads[i].start();
    }
    
  }
  
}
