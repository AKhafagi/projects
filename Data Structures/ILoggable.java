/**
 * San Diego State University.<br>
 * CS 310: Data Structures<br>
 * Spring 2016<br>
 * 
 * @version 1.0
 */


import java.util.Comparator;
import java.util.logging.Logger;

/**
 * Indicates class supports basic logging to a <code>java.util.logger</code>
 * compliant log.
 * <p>
 * The implementing class should specify this object's behavior for when the log
 * is not set and the trigger objects identifies a loggable event. It might
 * simply ignore the event or, perhaps, record to some default logger.
 * </p>
 * 
 * @author Shawn Healey
 *
 * @param <E>
 *          The Type of the objects the trigger compares
 */
public interface ILoggable<E> {

  /**
   * Attaches the parameter object <code>trigger</code> to the
   * <code>ILoggable</code> object. When the <code>Comparator</code> object
   * evaluates as equal to <code>value</code>, the <code>ILoggable</code> object
   * will begin writing events to the current logger. The meaning associated
   * with the other possible <code>Comparator</code> return values remains
   * undefined, and it is up to the implementing class to define their meaning
   * (if necessary).
   * <p>
   * Implementing classes may support multiple triggers for this object, so when
   * any one of which evaluate as true, then the object shall begin logging
   * events. Additionally, implementing classes shall record an INFO event in
   * their currently associated log indicating the addition of the trigger to
   * this object.
   * </p>
   * 
   * @param trigger
   *          when this object determines the current activity's value and the
   *          stored value evaluate as true, the <code>ILoggable</code> object
   *          begins writing events to the log.
   * @param value
   *          used as the left hand side parameter when this object calls the
   *          trigger object.
   * @return <code>true</code> if the provided trigger is now associated with
   *         the <code>ILoggable</code> object.<br>
   *         <code>false</code> if this object was unable to attach the provided
   *         trigger
   */
  boolean addTrigger(Comparator<E> trigger, E value);

  /**
   * Removes all currently attached triggers. The <code>ILoggable</code> object
   * will not log any triggerable events to the logger until it has at least one
   * trigger. Additionally, the <code>ILoggable</code> object shall record an
   * INFO event indicating it cleared the associated triggers.
   */
  void clearTriggers();

  /**
   * Associates the provided log with this object. When a trigger determines it
   * is appropriate to log an event, this is the object it uses. Additionally,
   * the <code>ILoggable</code> object shall record an INFO event in the new
   * log, assuming it is non-null, indicating it is now in use.
   * 
   * @param log
   *          The <code>java.util.logger</code> log to use when storing events
   *          or <code>null</code> to remove any association with a log.
   * @return the previous log in use or <code>null</code> if none.
   */
  Logger useLog(Logger log);
}