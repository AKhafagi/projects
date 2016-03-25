/**
 * San Diego State University.<br>
 * CS 310: Data Structures<br>
 * Spring 2016<br>
 * 
 * @version 1.0
 */

package edu.sdsu.cs.datastructures;

import org.junit.Before;

/**
 * Unit tests for a Vector based Deque implementation. 
 * 
 * @author Shawn Healey
 *
 */
public class SlowDequeTests extends LinkedListDequeTests{

  @Before
  public void setUp() throws Exception {
    sut = new SlowDeque<String>();
  }

}