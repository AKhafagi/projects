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

import edu.sdsu.cs.datastructures.LinkedList;
import org.junit.Before;
import org.junit.Test;

import java.util.Deque;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedListDequeTests {

  final String ARBITRARY_VALUE = "DarthBinks";
  final String INVALID_VALUE = "Kenseth";
  final String MANY_ITEM_MAX_STR = "9999";
  final int MANY_ITEMS = 10000;
  Deque<String> sut;
  final String VALID_VALUE = "Logano";
  final String ZERO_STR = "0";

  @Test
  public void addFirst_manyItems_endContentsAndSizeCorrect() {
    for (int count = 0; count < MANY_ITEMS; count++) {
      sut.addFirst(((Integer) count).toString());
    }
    assertThat("First item invalid.", sut.peekFirst(), is(MANY_ITEM_MAX_STR));
    assertThat("Last item invalid.", sut.peekLast(), is(ZERO_STR));
    assertThat("Size invalid.", sut.size(), is(MANY_ITEMS));
  }

  @Test
  public void addLast_manyItems_endContentsAndSizeCorrect() {
    for (int count = 0; count < MANY_ITEMS; count++) {
      sut.addLast(((Integer) count).toString());
    }
    assertThat("Last item invalid.", sut.peekLast(), is(MANY_ITEM_MAX_STR));
    assertThat("First item invalid.", sut.peekFirst(), is(ZERO_STR));
    assertThat("Size invalid.", sut.size(), is(MANY_ITEMS));
  }

  @Test(expected = NoSuchElementException.class)
  public void getFirst_afterDeletingEveryting_exception() {
    for (int count = 0; count < MANY_ITEMS; count++) {
      sut.offerLast("To Delete " + count);
    }
    for (int count = 0; count < MANY_ITEMS; count++) {
      sut.pollFirst();
    }
    sut.getFirst();
  }

  @Test(expected = NoSuchElementException.class)
  public void getFirst_emptyDeque_exception() {
    sut.getFirst();
  }

  @Test
  public void getFirstAndGetLast_afterAddingToCleared_correctValues() {
    for (int count = 0; count < MANY_ITEMS; count++) {
      sut.offerLast(INVALID_VALUE + count);
    }
    sut.clear();
    sut.offer(VALID_VALUE);
    assertThat("Invalid first value", sut.getFirst(), equalTo(VALID_VALUE));
    assertThat("Invalid last value", sut.getLast(), equalTo(VALID_VALUE));
  }

  @Test(expected = NoSuchElementException.class)
  public void getLast_afterDeletingEveryting_exception() {
    for (int count = 0; count < MANY_ITEMS; count++) {
      sut.offerFirst("To Delete " + count);
    }
    for (int count = 0; count < MANY_ITEMS; count++) {
      sut.pollLast();
    }
    sut.getFirst();
  }

  @Test(expected = NoSuchElementException.class)
  public void getLast_emptyDeque_exception() {
    sut.getLast();
  }

  @Test
  public void offer_manyItems_firstItemCorrect() {
    sut.offer(VALID_VALUE);
    for (int count = 0; count < MANY_ITEMS; count++) {
      sut.offer("Invalid " + count);
    }
    assertThat(sut.peek(), equalTo(VALID_VALUE));
  }

  @Test
  public void offerFirst_manyItems_finalItemCorrect() {
    sut.offerFirst(VALID_VALUE);
    for (int count = 0; count < MANY_ITEMS; count++) {
      sut.offerFirst("Invalid " + count);
    }
    sut.offerFirst("First");

    assertThat("Invalid last item", sut.peekLast(), equalTo(VALID_VALUE));
    assertThat("Invalid first item", sut.peekFirst(), equalTo("First"));
  }

  @Test
  public void peek_afterDeletingDownToOneItem_correctValue() {
    for (int count = 0; count < MANY_ITEMS; count++) {
      sut.addLast(((Integer) count).toString());
    }

    while (sut.size() > 1) {
      sut.removeFirst();
    }
    assertThat(sut.peekFirst(), is(MANY_ITEM_MAX_STR));
    assertThat(sut.peekLast(), is(MANY_ITEM_MAX_STR));
  }

  @Test
  public void peek_afterEmptyingAddSingleItem_correctValue() {
    for (int count = 0; count < MANY_ITEMS; count++) {
      sut.addLast(((Integer) count).toString());
    }
    for (int count = 0; count < MANY_ITEMS; count++) {
      sut.remove();
    }
    sut.addFirst(VALID_VALUE);

    assertThat("First incorrect", sut.peekFirst(), is(VALID_VALUE));
    assertThat("Last incorrect", sut.peekLast(), is(VALID_VALUE));
  }

  @Test
  public void peek_emptyDeque_nullReturn() {
    assertThat(sut.peekFirst(), equalTo(null));
    assertThat(sut.peekLast(), equalTo(null));
  }

  @Test
  public void peek_fewItems_correctValue() {
    sut.push(VALID_VALUE + "1");
    sut.push(VALID_VALUE + "2");
    sut.push(VALID_VALUE + "3");

    assertThat(sut.peek(), equalTo(VALID_VALUE + "3"));
  }

  @Test
  public void poll_singleItemInList_correct() {
    sut.add(VALID_VALUE);

    assertThat(sut.poll(), equalTo(VALID_VALUE));
    assertThat(sut.poll(), equalTo(null));
  }

  @Test
  public void pollFirst_singleItemInList_correct() {
    sut.add(VALID_VALUE);

    assertThat(sut.pollFirst(), equalTo(VALID_VALUE));
    assertThat(sut.pollFirst(), equalTo(null));
  }

  @Test
  public void pollLast_singleItemInList_correct() {
    sut.add(VALID_VALUE);

    assertThat(sut.pollLast(), equalTo(VALID_VALUE));
    assertThat(sut.pollLast(), equalTo(null));
  }

  @Test(expected = NoSuchElementException.class)
  public void pop_emptyDeque_exception() {
    sut.pop();
  }

  @Test
  public void push_manyItems_endValuesCorrect() {
    for (int count = 0; count < MANY_ITEMS; count++) {
      sut.push(VALID_VALUE + count);
    }

    assertThat("Incorrect last element", sut.peekLast(),
        equalTo(VALID_VALUE + 0));
    assertThat("Incorrect first element", sut.peekFirst(),
        equalTo(VALID_VALUE + (MANY_ITEMS - 1)));
  }

  @Test
  public void removeFirstOccurance_endsOfList_correctDuplicateRemoved() {
    sut.push(VALID_VALUE);
    sut.push(ARBITRARY_VALUE);
    sut.push(VALID_VALUE);

    sut.removeFirstOccurrence(new String(VALID_VALUE));
    assertThat("Invalid first item", sut.pollFirst(), equalTo(ARBITRARY_VALUE));
    assertThat("Invalid last item", sut.pollLast(), equalTo(VALID_VALUE));
  }

  @Test
  public void removeLastOccurance_endsOfList_correctDuplicateRemoved() {
    sut.push(VALID_VALUE);
    sut.push(ARBITRARY_VALUE);
    sut.push(VALID_VALUE);

    sut.removeLastOccurrence(VALID_VALUE);
    assertThat("Invalid first item", sut.pollFirst(), equalTo(VALID_VALUE));
    assertThat("Invalid last item", sut.pollLast(), equalTo(ARBITRARY_VALUE));
  }

  @Test
  public void removeFirstLastOccurance_itemNotInList_returnsFalse() {
    sut.push(VALID_VALUE);
    sut.push(VALID_VALUE);

    assertThat("removeFirst failed", sut.removeFirstOccurrence(INVALID_VALUE),
        equalTo(false));
    assertThat("removeLast failed", sut.removeLastOccurrence(INVALID_VALUE),
        equalTo(false));
  }

  @Test
  public void descendingIterator_manyItems_correct() {

    for (int count = 0; count < MANY_ITEMS; count++) {
      // TODO Students: Do you understand what is happening here? This type of
      // casting is testable. It has to do with primitives wrapped as objects.
      sut.offerFirst(((Integer) count).toString());
    }

    Iterator<String> it = sut.descendingIterator();
    int expectedCount = 0;
    while (it.hasNext()) {
      final String item = it.next();
      assertThat("Invalid item: " + item, item,
          equalTo(Integer.toString(expectedCount++)));
    }
    assertThat("Invalid size", expectedCount, equalTo(MANY_ITEMS));
  }

  @Before
  public void setUp() throws Exception {
    sut = new LinkedList<String>();
  }
}