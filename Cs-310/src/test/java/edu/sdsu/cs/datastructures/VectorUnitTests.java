/**
 * San Diego State University.<br>
 * CS 310: Data Structures<br>
 * Spring 2016<br>
 * 
 * @version 1.0
 */

package edu.sdsu.cs.datastructures;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.util.concurrent.TimeUnit;

/**
 * Example tests for the Vector homework assignment. These will verify your
 * solution meets the core requirements, so passing all of these should provide
 * you with confidence in your software. Be advised, Unit testing methodology
 * continues to evolve. These tests use the method championed by Roy Osherove --
 * One Assertion Per Test Method (OAPTM).
 * <p>
 * In this approach, developers craft tests such that each one contains a single
 * assertion statement. Thus, if the test fails, you know <i>precisely</i> which
 * test failed to meet expectations. Proponents argue this helps keep developers
 * from breaking the atomic nature of the tests. That is, a test should test one
 * and only one thing, so by using a single assertion, you guarantee you cannot
 * test more than one thing.
 * </p>
 * <p>
 * Tomek Kaczanowski argues, however, this approach seems draconian (not just
 * the bad guys from DragonLance). Folk in this camp also believe a unit test
 * should only test a single idea or concept, but that one may need multiple
 * assertions to verify its correct operation. Rather than creating multiple
 * unit tests to verify each aspect of construction (e.g., isEmpty=true, size is
 * 0, isFull=false), one might simplify this by creating a single unit test
 * called <code>const_verifyInitCorrectly</code> which tests these
 * characteristics in one go. Although the developer may not know precisely
 * which component failed by examining the test name, a quick examination of the
 * failure will reveal precisely which test failed.
 * </p>
 * <p>
 * From an educational perspective, I believe OAPTM serves us better here. Play
 * with these tests, and see which method you prefer. The key is to start
 * exploring how we test software so you can begin creating your own testers and
 * drivers on your own.
 * </p>
 * 
 * @author Shawn Healey
 *
 */
public class VectorUnitTests {

  private static final int DefaultSeriesSize = 256;

  private static final int DeleteItem = 20;
  private static final int ValidItem = 22;

  @Rule
  public Timeout globalTimeout = Timeout.builder()
      .withTimeout(200, TimeUnit.MILLISECONDS).build();

  Vector<Integer> sut;

  @Test
  public void add_multipleItems_getMiddleCorrect() {
    populateWithSeries(sut, DefaultSeriesSize);
    assertThat(sut.get(DefaultSeriesSize >> 1),
        is(equalTo(DefaultSeriesSize >> 1)));
  }

  @Test
  public void add_oneItem_correctSize() {
    sut.add(new Integer(ValidItem));
    assertThat(sut.size(), is(1));
  }

  @Test
  public void add_afterClear_getCorrect() {
    populateWithSeries(sut, DefaultSeriesSize);
    sut.clear();
    sut.add(ValidItem);
    assertThat(sut.get(0), is(equalTo(ValidItem)));
  }

  @Test
  public void addIndexValue_multipleItems_getMiddleCorrect() {
    populateWithSeries(sut, DefaultSeriesSize);
    sut.add(0, new Integer(ValidItem));
    assertThat(sut.size(), is(DefaultSeriesSize + 1));
  }

  @Test
  public void addIndexValue_oneItem_correctSize() {
    sut.add(0, new Integer(ValidItem));
    assertThat(sut.size(), is(1));
  }

  @Test
  public void clear_afterConstruction_sizeCorrect() {
    sut.clear();
    assertThat(sut.size(), is(0));
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void clear_getAfterClear_indexOutOfBoundsException() {
    populateWithSeries(sut, DefaultSeriesSize);
    sut.clear();
    sut.get(0);
  }

  @Test
  public void get_firstElement_correctValue() {
    sut.add(ValidItem);
    for (int i = ValidItem + 1; i < DefaultSeriesSize; i++) {
      sut.add(i);
    }
    assertThat(sut.get(0), is(equalTo(ValidItem)));
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void get_indexAboveRange_Exception() {
    sut.get(sut.size() + 1);
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void get_indexBelowRange_Exception() {
    sut.get(-1);
  }

  @Test
  public void isEmpty_afterConstruction_true() {
    assertThat(sut.isEmpty(), is(true));
  }

  @Test
  public void isEmpty_afterRemovingOnlyItem_true() {
    sut.add(ValidItem);
    sut.remove(0);
    assertThat(sut.isEmpty(), is(true));
  }

  @Test
  public void isEmpty_oneItem_false() {
    sut.add(ValidItem);
    assertThat(sut.isEmpty(), is(false));
  }

  private void populateWithSeries(Vector<Integer> vec, int count) {
    for (int i = 0; i < count; i++) {
      sut.add(i);
    }
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void remove_aboveIndexRange_Exception() {
    sut.add(ValidItem);
    sut.remove(1);
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void remove_afterConstruction_Exception() {
    sut.remove(0);
  }

  @Test
  public void remove_firstItem_secondNowFirst() {
    sut.add(DeleteItem);
    sut.add(ValidItem);
    sut.remove(0);
    assertThat(sut.get(0), is(equalTo(ValidItem)));
  }

  @Test
  public void remove_firstItem_sizeCorrect() {
    populateWithSeries(sut, DefaultSeriesSize);
    sut.remove(0);
    assertThat(sut.size(), is(equalTo(DefaultSeriesSize - 1)));
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void remove_belowIndexRange_Exception() {
    sut.add(ValidItem);
    sut.remove(-1);
  }

  @Test
  public void remove_allInList_finalReturnValueCorrect() {
    populateWithSeries(sut, DefaultSeriesSize);
    for (int i = 0; i < DefaultSeriesSize - 1; i++) {
      sut.remove(0);
    }
    assertThat(sut.remove(0), is(equalTo(DefaultSeriesSize - 1)));
  }

  @Test
  public void remove_firstInList_correctReturnValue() {
    populateWithSeries(sut, DefaultSeriesSize);
    assertThat(sut.remove(0), is(equalTo(new Integer(0))));
  }

  @Test
  public void remove_middleOfList_correctReturnValue() {
    populateWithSeries(sut, DefaultSeriesSize);
    assertThat(sut.remove(DefaultSeriesSize / 2),
        is(equalTo(new Integer(DefaultSeriesSize / 2))));
  }

  @Test
  public void remove_lastInList_correctReturnValue() {
    populateWithSeries(sut, DefaultSeriesSize);
    assertThat(sut.remove(DefaultSeriesSize - 1),
        is(equalTo(new Integer(DefaultSeriesSize - 1))));
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void set_indexAboveRange_Exception() {
    sut.set(sut.size(), new Integer(ValidItem));
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void set_indexBelowRange_Exception() {
    sut.set(-2, new Integer(ValidItem));
  }

  @Test
  public void set_middleIndex_correctSize() {
    populateWithSeries(sut, 3);
    sut.set(1, ValidItem);
    assertThat(sut.size(), is(3));
  }

  @Test
  public void set_middleIndex_correctValue() {
    populateWithSeries(sut, 3);
    sut.set(1, ValidItem);
    assertThat(sut.get(1), is(equalTo(ValidItem)));
  }

  /**
   * Note: this method executes before *every* unit test, so each one starts
   * with a new, fresh vector.
   */
  @Before
  public void setup() {
    sut = new Vector<Integer>();
  }

  @Test
  public void size_afterConstruction_zero() {
    assertThat(sut.size(), is(0));
  }

}