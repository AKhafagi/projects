/**
 * San Diego State University.<br>
 * CS 310: Data Structures<br>
 * Spring 2016<br>
 * 
 * @version 1.0
 */

package edu.sdsu.cs.prog1;

import edu.sdsu.cs.datastructures.LoggedVector;

import java.io.IOException;
import java.util.Comparator;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LogTest {

  private class MatchesCaseInsensitive implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
      return o1.toUpperCase().equals(o2.toUpperCase()) ? 0 : -1;
    }
  }

  final Logger myLogger = Logger
      .getLogger("edu.sdsu.cs.datastructures.LoggedVector");

  static FileHandler fileTxt;
  static SimpleFormatter formatterTxt;

  LoggedVector<String> stringVector = new LoggedVector<String>(2, 16);

  private LogTest() throws SecurityException, IOException {
    initLogger();
    stringVector.useLog(myLogger);

    myLogger.info("Running with Anonymous comparator . . . ");
    stringVector.addTrigger(new Comparator<String>() {
      @Override
      public int compare(String arg0, String arg1) {
        return (arg0.equals(arg1) ? 0 : 1);
      }
    }, "day");

    runTests(stringVector);
    stringVector.clearTriggers();

    myLogger.info("Running with MatchesCaseInsensitive comparator . . . ");
    stringVector.addTrigger(new MatchesCaseInsensitive(), "Day");
    runTests(stringVector);
    
    System.out.println("complete");
  }

  void runTests(LoggedVector<String> sut) {
    sut.add("groundhog");
    sut.set(0, "day");
    sut.add("Groundhog");
    sut.add("day");
    sut.add("Day");
    sut.add("daY");
    sut.add("GroundHog");
    sut.remove("day");
  }

  void disableConsoleLogger() {
    Logger rootLogger = Logger.getLogger("");
    Handler[] handlers = rootLogger.getHandlers();
    if (handlers[0] instanceof ConsoleHandler) {
      rootLogger.removeHandler(handlers[0]);
    }
  }

  void initLogger() throws SecurityException, IOException {

    disableConsoleLogger();

    fileTxt = new FileHandler("Logging.xml");

    myLogger.setLevel(Level.INFO);
    myLogger.addHandler(fileTxt);
  }

  /**
   * Quickly creates a new instance of this class.
   * 
   * @param args
   *          unused
   */
  public static void main(String[] args) {
    try {
      new LogTest();
    } catch (SecurityException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
