/**
 * San Diego State University.<br> CS 310: Data Structures<br> Spring 2016<br>
 * 
 * <p> Driver file for the breadth-first-search programming assignment. Do not
 * submit this file. </p>
 * 
 * @version 1.0
 */

package edu.sdsu.cs.prog3;

import java.awt.Color;
import java.awt.Point;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Random;

/**
 * A simple driver class which, upon construction, generates and solves a series
 * of randomly generated mazes. This implementation must uses each
 * <b>student's</b> <code>LinkedList</code> (or <code>SlowDeque</code>)
 * implementation to fulfill the <code>Deque</code> requirements. Alternatively,
 * f one chooses to import <code>java.util.LinkedList</code> instead, perhaps
 * due to missing the previous assignment, the solution will receive a <i>point
 * penalty</i>, but I will accept the submission.
 * 
 * @version 1.0
 */
public class MazeDriver {

  /**
   * A coordinating thread responsible for controlling the creation and solution
   * of random mazes.
   */
  static class Coordinator implements Runnable {

    private final int mazeEdgeSize;
    /**
     * Periodically the program pauses to facilitate human processing, and this
     * establishes the delay in milliseconds.
     */
    private final int pauseMillis = 2000;

    /**
     * Create a new <code>Coordinator</code> object for mazes with the indicated
     * edge size.
     * 
     * @param size
     *          number of <code>MazeRoom</code> objects per maze size, so
     *          passing in 25 produces a 25x25 room maze.
     */
    public Coordinator(int size) {
      mazeEdgeSize = size;
    }

    private void displayStats(int[] wins, Deque<MazeRoom> lowLeftToUpRight,
        Deque<MazeRoom> lowRightToUpLeft, LinkedList<MazeRoom> common) {

      if (lowLeftToUpRight.size() < lowRightToUpLeft.size()) {
        wins[0]++;
      } else {
        wins[1]++;
      }

      System.out.println("Blue: " + wins[0] + " vs. Yellow: " + wins[1]);
      System.out.println("Length blue route: " + lowLeftToUpRight.size());
      System.out.println("Length yellow route: " + lowRightToUpLeft.size());
      System.out.println("Steps in common: " + common.size());
    }

    private void pauseBriefly() {
      try {
        Thread.sleep(pauseMillis);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

    /**
     * As a runnable object, the outside world will call this method when it
     * wishes it to perform work. In this example, it occurs when the
     * <code>main</code> method creates and starts the coordinator thread.
     */
    @Override
    public void run() {

      int[] score = { 0, 0 };

      while (true) {
        Maze sut = new Maze(mazeEdgeSize);
        SimpleDraw.clear(clrBackground);
        sut.draw();
        pauseBriefly();

        // Generate the maze solutions
        Deque<MazeRoom> lowLeftToUpRight = new MazeSolver(sut).solve(
            new Point(0, 0), new Point(mazeEdgeSize - 1, mazeEdgeSize - 1));
        Deque<MazeRoom> lowRightToUpLeft = new MazeSolver(sut).solve(
            new Point(0, mazeEdgeSize - 1), new Point(mazeEdgeSize - 1, 0));
        // Determine how much of the maze they shared
        LinkedList<MazeRoom> common = new LinkedList<MazeRoom>(
            lowLeftToUpRight);
        common.retainAll(lowRightToUpLeft);

        displayStats(score, lowLeftToUpRight, lowRightToUpLeft, common);

        // launch the threads responsible for running the maze
        runCornerPaths(sut, lowLeftToUpRight, lowRightToUpLeft);
        runCommonPath(sut, common);
        pauseBriefly();
      }
    }

    private void runCommonPath(IRenderable sut, LinkedList<MazeRoom> common) {
      Thread th3 = new Thread(new RunMaze(sut, common, Color.red));
      th3.start();
      try {
        th3.join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

    private void runCornerPaths(IRenderable sut,
        Deque<MazeRoom> lowLeftToUpRight, Deque<MazeRoom> lowRightToUpLeft) {
      Thread th1 = new Thread(new RunMaze(sut, lowLeftToUpRight, clrPlayer1));
      Thread th2 = new Thread(new RunMaze(sut, lowRightToUpLeft, clrPlayer2));

      th1.start();
      th2.start();

      try {
        th1.join();
        th2.join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * A simple thread responsible for displaying a route in a maze.
   */
  private static class RunMaze implements Runnable {

    final Color myColor;
    final IRenderable myMaze;
    final Deque<MazeRoom> myRoute;

    /**
     * Establishes the values to use when running the route.
     * 
     * @param maze
     *          The <code>Maze</code> type object to render
     * @param route
     *          A series of <code>MazeRoom</code> objects the caller wishes to
     *          highlight in the order they appear in the list
     * @param color
     *          color to use when rendering the maze highlights
     */
    public RunMaze(IRenderable maze, Deque<MazeRoom> route, Color color) {
      myRoute = route;
      myMaze = maze;
      myColor = color;
    }

    /**
     * periodically highlights MazeRoom objects within the maze and updates the
     * maze's clock (and a bad place to do it). In reality, this thread should
     * simply update the maze's highlights and then sleep. The coordinator
     * object should then wait for all its threads to put themselves to sleep
     * after highlighting the path, and then call the draw and update method
     * once for all of them. When the update completes, it should notify the
     * sleeping threads, and they then update the maze again for the next turn.
     */
    @Override
    public void run() {
      for (MazeRoom rm : myRoute) {
        rm.setRenderHighlight(true, myColor);
        myMaze.draw();
        myMaze.update();
      }
    }
  }

  /**
   * Color everything reverts to when cleared.
   */
  static final Color clrBackground = new Color(15, 20, 30);

  /**
   * Color of the lingering trail dots.
   */
  static final Color clrBreadcrumbs = new Color(255, 230, 75);

  /**
   * Primary color used when highlighting the first player's route.
   */
  static final Color clrPlayer1 = new Color(110, 195, 225);

  /**
   * Primary color used for the second player's route.
   */
  static final Color clrPlayer2 = new Color(255, 230, 75);

  /**
   * A common random number generator to use. Provide a seed number for
   * consistent development testing, and when confident in your solution, remove
   * the seed and see how it performs with a different set of pseudo-random
   * numbers.
   */
  static final Random dice = new Random(310);

  /**
   * Establishes the pixel resolution for each edge of the display window.
   * Adjust this so it looks best on your machine.
   */
  static final int displayResolution = 800;

  /**
   * Number of <code>MazeRoom</code> objects per maze side.
   */
  static final int edgeSize = 40;

  /**
   * Establishes the highlighted route's "head" length during display.
   */
  static final int numStepsToHighlight =3 ;

  /**
   * A simple exercise of the <code>MazeBuilder</code> and
   * <code>MazeSolver</code> classes. This program displays and solves an
   * infinite series of randomly generated mazes. The generation and solution
   * process relies heavily on the <code>java.util.Deque</code> interface.
   * 
   * @param args
   *          Although one may provide arguments, this program does not use them
   * @throws InterruptedException
   *           when one of the runner threads throws an exception.
   */
  public static void main(String[] args) throws InterruptedException {

    SimpleDraw.setCanvasSize(displayResolution, displayResolution);
    MazeDriver maze = new MazeDriver(edgeSize);
    maze.start();
  }

  public MazeDriver() {
    this(edgeSize);
  }

  /**
   * specifies the size of maze in <code>MazeRoom</code> objects.
   * 
   * @param size
   *          number of rooms on each edge, so the maze has size^2 rooms total
   */
  public MazeDriver(int size) {
    initDraw(size + (2 * Maze.widthBorderInRooms));
    // new Maze(size);
  }

  private void initDraw(int size) {
    SimpleDraw.setXscale(0, size);
    SimpleDraw.setYscale(0, size);
    SimpleDraw.clear(clrBackground);
    SimpleDraw.setPenColor(clrPlayer1);
  }

  /**
   * Creates and starts a new coordinator thread. The Coordinator object, in
   * turn, handles generating, displaying, and creating the maze solving
   * threads.
   */
  public void start() {
    Thread th = new Thread(new Coordinator(edgeSize));
    th.start();
  }

}