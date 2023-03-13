package adp.lab2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListDemoV1 {
  static class ShowLast implements Runnable {
    private final List<String> list;
    public ShowLast( final List<String> list) {
      this.list = list;
    }
    public void run() {
      while( this.list.size() > 0) {
        int lastItem = this.list.size() - 1;
        Thread.yield();
        System.out.println( "Last item is " + this.list.get( lastItem)); 
        try {
          Thread.sleep( (long)(Math.random() * 100));
        } catch( final InterruptedException e) {}
      }
    }
  }
  static class Remover implements Runnable {
    private final List<String> list;
    public Remover( final List<String> list) {
      this.list = list;
    }
    public void run() {
      while( this.list.size() > 0) {
        try {
          Thread.sleep( (long)(Math.random() * 100));
        } catch( final InterruptedException e) {}
        System.out.println( "Removing " + this.list.remove( this.list.size() - 1));
      }
    }
  }


  public static void main( final String[] args) throws InterruptedException {
    final List<String> list = new ArrayList<String>( Arrays.asList( new String[] { "apple", "orange", "lemon", "banana", "pineapple", "lime", "pear", "plum", "peach", "raspberry", "strawberry", "gooseberry", "melon", "kiwi" }));
    final Thread[] threads = new Thread[4];
    // create the threads
    for( int i = 0; i <= threads.length / 2; i += 2) {
      threads[i] = new Thread( new Remover(list));
      threads[i + 1] = new Thread( new ShowLast(list));
    }
    // set all the threads running concurrently
    for( int i = 0; i < threads.length; i++) {
      threads[i].start();
    }
    // wait for all the threads to finish
    for( int i = 0; i < threads.length; i++) {
      threads[i].join();
    }
  }
}

