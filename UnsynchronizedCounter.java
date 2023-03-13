package adp.lab2;

class UnsynchronizedCounter implements Counter {
  private int c = 0;
  public void increment() {
    this.c++;
  }
  public void decrement() {
    this.c--;
  }
  public int value() {
    return this.c;
  }
}
