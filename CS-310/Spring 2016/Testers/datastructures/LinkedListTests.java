/**
 * San Diego State University.<br>
 * CS 310: Data Structures<br>
 * Spring 2016<br>
 * 
 * @version 1.0
 */

package edu.sdsu.cs.datastructures;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class LinkedListTests {

  private static final int ARBITRARY_VALUE = 20;
  private static final int FIRST_ITEM = 0;
  private static final int TEST_SIZE = 100;

  private static final int VALID_VALUE = 22;

  Random dice;

  //you can safely comment this out if your version of JUnit does not support
   //the @Rule
  @Rule
 public Timeout globalTimeout = Timeout.builder()
      .withTimeout(200, TimeUnit.MILLISECONDS).build();
  LinkedList<Integer> sut;

  @Test
  public void add_oneHundredItems_sizeCorrect() {
    addSequentialSeriesToList(sut, 0, TEST_SIZE);
    assertThat(sut.size(), is(TEST_SIZE));
  }

  @Test
  public void add_oneHundredItemsSequentially_valuesCorrect() {
    addSequentialSeriesToList(sut, 0, TEST_SIZE);
    for (int counter = 0; counter < TEST_SIZE; counter++) {
      assertThat("Invalid value detected at[" + counter + "]", sut.get(counter),
          is(counter));
    }
  }

  @Test
  public void add_singleItem_getAndSizeCorrect() {
    sut.add(new Integer(VALID_VALUE));

    assertThat(sut.get(FIRST_ITEM), is(VALID_VALUE));
    assertThat(sut.size(), is(1));

    sut.clear();
    addSequentialSeriesToList(sut, 0, TEST_SIZE);
    

    ListIterator<Integer> it = sut.listIterator(0);
    	 while (it.hasNext()) {
    	      it.add(22);
    	      it.next();  
    	    }
    	 System.out.println(sut);
    
  }

  private void addSequentialSeriesToList(List<Integer> obj, int startInc,
      int stopExc) {
    for (int counter = startInc; counter < stopExc; counter++) {
      sut.add(new Integer(counter));
    }
  }

  @Test
  public void clearingSublist_allMiddleElements_onlyEndsRemain() {
    addSequentialSeriesToList(sut, 0, TEST_SIZE);
    List<Integer> subList = sut.subList(1, TEST_SIZE - 1);
    subList.clear();

    assertThat(sut.size(), is(2));
    assertThat(sut.get(0), is(0));
    assertThat(sut.get(1), is(TEST_SIZE - 1));
  }

  @Test
  public void defaultCnstr_initializedCorrectly() {
    assertThat(sut.size(), is(0));
    assertThat(sut.isEmpty(), is(true));
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void get_afterCnstr_IndexOutOfBoundsException() {
    sut.get(FIRST_ITEM);
  }

  @Test
  public void get_endsOfList_valuesCorrect() {
    addSequentialSeriesToList(sut, 0, TEST_SIZE);

    assertThat(sut.get(FIRST_ITEM), is(0));
    assertThat(sut.get(TEST_SIZE - 1), is((TEST_SIZE - 1)));
  }

  @Test(expected = NoSuchElementException.class)
  public void iterator_nextWhenNone_exception() {
    ListIterator<Integer> it = sut.listIterator();
    it.next();
  }

  @Test(expected = NoSuchElementException.class)
  public void iterator_previousWhenNone_exception() {
    ListIterator<Integer> it = sut.listIterator();
    it.previous();
  }

  @Test(expected = IllegalStateException.class)
  public void iterator_setAfterAdd_exception() {
    ListIterator<Integer> it = sut.listIterator();
    it.add(ARBITRARY_VALUE);
    it.set(ARBITRARY_VALUE);
  }

  @Test(expected = IllegalStateException.class)
  public void iterator_setAfterRemove_exception() {
    sut.add(ARBITRARY_VALUE * dice.nextInt());
    sut.add(ARBITRARY_VALUE * dice.nextInt());
    ListIterator<Integer> it = sut.listIterator();
    it.next();
    it.remove();
    it.set(ARBITRARY_VALUE);
  }

  @Test(expected = IllegalStateException.class)
  public void iterator_setBeforeNextOrPrevious_exception() {
    sut.add(ARBITRARY_VALUE);
    ListIterator<Integer> it = sut.listIterator();
    it.set(ARBITRARY_VALUE);
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void remove_afterCnstr_IndexOutOfBoundsException() {
    sut.remove(FIRST_ITEM);
  }

  @Test
  public void remove_singleItem_removed() {
    sut.add((Integer) VALID_VALUE);
    sut.remove((Integer) VALID_VALUE);
    assertThat(sut.contains((Integer) VALID_VALUE), is(false));
  }

  @Test
  public void retainAll_othersDiscarded() {
    addSequentialSeriesToList(sut, 0, TEST_SIZE);
    final int maxValueInFirstSet = TEST_SIZE >> 1;
    final Collection<Integer> discardSet = new ArrayList<Integer>(
        sut.subList(maxValueInFirstSet, sut.size()));
    sut.retainAll(sut.subList(0, maxValueInFirstSet));
    for (Integer invalidItem : discardSet) {
      assertThat(sut.contains(invalidItem), is(false));
    }
  }

  @Test
  public void retainAll_retained() {
    addSequentialSeriesToList(sut, 0, TEST_SIZE);
    final int maxValueInFirstSet = TEST_SIZE >> 1;
    final Collection<Integer> firstSet = sut.subList(0, maxValueInFirstSet);
    sut.retainAll(firstSet);
    assertThat(sut.containsAll(firstSet), is(true));
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void set_aboveRange_IndexOutOfBoundsException() {
    sut.set(1, ARBITRARY_VALUE);
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void set_afterCnstr_IndexOutOfBoundsException() {
    sut.set(FIRST_ITEM, ARBITRARY_VALUE);
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void set_belowRange_IndexOutOfBoundsException() {
    sut.set(-1, ARBITRARY_VALUE);
  }

  @Test
  public void set_changeValueInMiddleOfList_changed() {
    addSequentialSeriesToList(sut, 0, TEST_SIZE);
    final int middleIndex = TEST_SIZE >> 1;
    final Integer priorValue = sut.get(middleIndex);
    sut.set(middleIndex, priorValue * -1);
    assertThat(sut.get(middleIndex), is(priorValue * -1));
    assertThat(sut.contains(priorValue), is(false));
  }

  @Before
  public void setUp() throws Exception {
    sut = new LinkedList<Integer>();
    dice = new Random(310);
  }
}