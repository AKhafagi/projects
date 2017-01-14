/**
 * SUBMIT THIS FILE. This file defines the object responsible for generating a
 * maze.
 * 
 * @version 1.0
 */

package edu.sdsu.cs.prog3;

import java.awt.Point;
import java.util.Deque;
/**
 * Remove the LinkedList java.util import for full credit! That is, use the one
 * you developed in assignment 2.
 */

import java.util.Random;

import edu.sdsu.cs.datastructures.LinkedList;

/**
 * Generates a maze represented by a two-dimensional array of individual room
 * objects.
 * 
 * @author Anas Khafagi
 *
 */
public class MazeBuilder {

	/**
	 * By providing the pseudo-random number generator with a seed number, we
	 * guarantee we get the same "random" numbers every time. Thus, one may
	 * perform testing without the added uncertainty of not knowing if the
	 * reason it didn't fail this time was because you fixed the problem, or
	 * because it just had a different maze.
	 */
	private static final Random dice = new Random(310);

	/**
	 * Used to track if we have processed a room yet during generation. The
	 * order is <code>visited[row][col]</code>.
	 */
	boolean[][] visited;

	/**
	 * A digital frontier.
	 */
	MazeRoom[][] theGrid;

	/**
	 * Creates and initializes a MazeBuilder object.
	 * 
	 * @param edgeLength
	 *            number of <code>MazeRoom</code> objects per side.
	 * @param borderWidth
	 *            space (in rooms) to render around the maze. This should stand
	 *            out as . . . odd. Why does something building a maze need to
	 *            know its border width, but not the colors to render the walls
	 *            or the amount of time to spend rendering each move? It seems
	 *            we should refactor this constructor to, instead, take some
	 *            kind of configuration object holding all these data.
	 */
	private Deque<MazeRoom> visitedRooms;

	public MazeBuilder(int edgeLength, int borderWidth) {
		visited = new boolean[edgeLength][edgeLength];
		theGrid = initializeRooms(edgeLength, borderWidth);
		visitedRooms = new LinkedList<MazeRoom>();
	}

	/**
	 * A recursive algorithm which produces a square maze. Because this is a
	 * recursive algorithm, one may request a maze with an edge length so long
	 * it causes a Stack Overflow, for the recursive calls require more and more
	 * stack space which, eventually, bumps into the heap.<br>
	 * 
	 * @return a two dimensional array of <code>MazeRoom</code> objects
	 */
	public MazeRoom[][] generate() {
		generate(theGrid[0][0]);
		return theGrid;
	}

	/**
	 * Performs the bulk of the maze generation. Given the parameter object
	 * start room, this method should initiate the next step in the maze
	 * production process.
	 * 
	 * <p>
	 * To generate the maze, one may use the following algorithm:
	 * </p>
	 * 
	 * <ol>
	 * 1. Mark the <code>start</code> room as visited indicating it no longer
	 * requires processing at this level.
	 * </ol>
	 * <ol>
	 * 2. While the starting maze room has unvisited neighbors:
	 * </ol>
	 * <li>Randomly select an unvisited neighbor</li>
	 * <li>Connect to selected neighbor by removing the walls between the two
	 * rooms</li>
	 * <li>Recursively call this method on the neighbor it just connected</li>
	 * 
	 * <p>
	 * This process results in a fully-connected maze. That is, there are no
	 * unreachable cells.
	 * </p>
	 * 
	 * @param start
	 *            The <code>MazeRoom</code> object from which to search for
	 *            unvisited neighbors.
	 */
	private void generate(MazeRoom start) {
		MazeRoom currentRoom;
		int row = start.getRow();
		int col = start.getCol();
		this.visited[row][col] = true;
		Deque<MazeRoom> unvisitedRooms = getUnvisitedNeighbors(start);
		if (!unvisitedRooms.isEmpty()) {
			currentRoom = randomNeighbor(unvisitedRooms);
			connectNeighbors(start, currentRoom);
			visitedRooms.push(currentRoom);
			generate(currentRoom);

		}

		if (!visitedRooms.isEmpty()) {
			currentRoom = visitedRooms.pop();
			generate(currentRoom);
		}

	}

	private static MazeRoom[][] initializeRooms(int size, int borderWidth) {
		MazeRoom[][] grid = new MazeRoom[size][size];
		for (int rowCounter = 0; rowCounter < size; rowCounter++) {
			for (int colCounter = 0; colCounter < size; colCounter++) {
				grid[rowCounter][colCounter] = new MazeRoom(new Point(colCounter, rowCounter), borderWidth);
			}
		}
		return grid;
	}

	/**
	 * Identifies the unvisited neighbors, if any, for the target room and
	 * returns them to the caller.
	 * 
	 * @param target
	 *            room from which to search for unvisited neighbors
	 * @return a Deque object containing all the unvisited rooms adjacent to the
	 *         target. If there are no unvisited neighbors, this method returns
	 *         an empty Deque. It never returns null.
	 */
	private Deque<MazeRoom> getUnvisitedNeighbors(MazeRoom target) {
		Deque<MazeRoom> unvisitedRooms = new LinkedList<MazeRoom>();
		int x = target.getCol();
		int y = target.getRow();

		if (x > 0 && !visited[y][x - 1]) {
			unvisitedRooms.add(theGrid[y][x - 1]);
		}

		if (x + 1 < theGrid.length && !visited[y][x + 1]) {
			unvisitedRooms.add(theGrid[y][x + 1]);
		}
		if (y > 0 && !visited[y - 1][x]) {
			unvisitedRooms.add(theGrid[y - 1][x]);
		}
		if (y + 1 < theGrid.length && !visited[y + 1][x]) {
			unvisitedRooms.add(theGrid[y + 1][x]);
		}
		return unvisitedRooms;
	}

	/**
	 * Breaks down the walls between two adjoining rooms by updating the actual
	 * MazeRoom objects themselves.
	 * 
	 * @param source
	 *            starting room
	 * @param neighbor
	 *            target with which to connect
	 */
	private void connectNeighbors(MazeRoom source, MazeRoom neighbor) {
		int sourceX = source.getCol();
		int sourceY = source.getRow();
		int neighborX = neighbor.getCol();
		int neighborY = neighbor.getRow();
		if (sourceX == neighborX && sourceY + 1 == neighborY) {
			source.removeWallNorth();
			neighbor.removeWallSouth();
		}
		if (sourceX == neighborX && sourceY - 1 == neighborY) {
			source.removeWallSouth();
			neighbor.removeWallNorth();

		}
		if (sourceY == neighborY && sourceX + 1 == neighborX) {
			source.removeWallEast();
			neighbor.removeWallWest();
		}
		if (sourceY == neighborY && sourceX - 1 == neighborX) {
			source.removeWallWest();
			neighbor.removeWallEast();
		}
	}

	private MazeRoom randomNeighbor(Deque<MazeRoom> unvisitedRooms) {
		LinkedList<MazeRoom> mazeRooms = (LinkedList<MazeRoom>) unvisitedRooms;
		MazeRoom randomRoom;
		Random rand = new Random();
		int random = rand.nextInt(unvisitedRooms.size());
		if (random == 0) {
			randomRoom = mazeRooms.remove(random);
		} else if (random == 1) {
			randomRoom = mazeRooms.remove(random);
		} else if (random == 2) {
			randomRoom = mazeRooms.remove(random);
		} else {
			randomRoom = mazeRooms.remove(random);
		}
		return randomRoom;
	}

}
