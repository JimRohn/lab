package adplabs.Lab2;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import adplabs.lab2.Utilities.FileWalker;

public class Demo {

  public static boolean doNextFile(final FileWalker walker) {
    final File f1 = walker.nextFile();
    if ( f1 == null) {
      return false;
    } else {
      System.out.println( f1 + "\t" + Utilities.toHexString(Utilities.computeHash(f1)));
      return true;
    }
  }

  public static void main(final String[] args) throws NoSuchAlgorithmException, IOException {
    File target = new File(".");
    //final File target = new File("C:\\");
    
    final FileWalker walker = new FileWalker( target);

    boolean notLast = true;
    while(notLast) {
      notLast = doNextFile(walker);
    }
    System.out.println( "done");
            
  }

}
