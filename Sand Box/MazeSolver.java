/**
 * SUBMIT THIS FILE.
 * 
 * @author your name here
 */

package edu.sdsu.cs.prog3;

import java.awt.Point;
import java.util.Collection;
import java.util.Deque;
import java.util.Queue;

import edu.sdsu.cs.datastructures.LinkedList;

/**
 * The MazeSolver class implements a breadth-first search algorithm to produce a
 * Deque of moves from the origin to the destination.
 * 
 * @author Your name here.
 * @account Your bitbucket user name
 *
 */
public class MazeSolver {

	final Maze myMaze;

	private int[][] visited;

	public MazeSolver(Maze toSolve) {
		myMaze = toSolve;
		visited = new int[toSolve.size()][toSolve.size()];
		visited = intializeArray(visited);
	}
	private int[][] intializeArray(int[][] array){
		for(int i =0; i <array.length;i++){
			for(int j =0; j< array.length;j++){
				array[i][j] =-1;
			}
		}
		return array;
	}

	/**
	 * STUDENTS MUST CODE: Given the starting and ending points within the maze,
	 * this method performs a breadth-first search and returns one shortest path
	 * between them. The strategy should be:
	 * 
	 * <li>Mark all rooms unvisited</li>
	 * <li>Enqueue the starting room and record the current distance as 0</li>
	 * <li><b>While</b> the queue of rooms has items:</li>
	 * <li>Dequeue a room and mark it as visited</li>
	 * <li>Mark each accessible, adjoining room as distance (current)+1 and
	 * place it in the queue</li>
	 * <p>
	 * This process will determine the number of steps one must take to return
	 * to the starting point from any room in the maze. With these data, you
	 * will then work backward to build a stack of moves. To find the shortest
	 * path, one only need to push the destination room on a stack, and then
	 * work backwards from there by selecting the connected room at each step
	 * with the shortest distance back to the origin. As you find the next step
	 * to take, push it on the stack and continue. When you reach the origin,
	 * your stack will contain the list of rooms one must visit, in sequential
	 * order, to exit the maze.
	 * </p>
	 * 
	 * @param start
	 *            The starting position in the maze. The .y component refers to
	 *            the row and comes first when referring to the grid[y][x].
	 * @param end
	 *            The destination point in the maze.
	 * @return A Deque of the rooms one must visit to reach the exit.
	 */
	public Deque<MazeRoom> solve(Point start, Point end) {
		Deque<MazeRoom> path = new LinkedList<MazeRoom>();
		MazeRoom origin = myMaze.getRoom(start.y, start.x);
		MazeRoom current;
		MazeRoom next;
		int curRow;
		int curCol;
		path.add(origin);
		int distance = 0;
		visited[start.y][start.x] = 0;
		while(!path.isEmpty()){
			current = path.remove();
			curRow = current.getRow();
			curCol = current.getCol();
			distance = visited[curRow][curCol];
			if (curCol>0 && (visited[curRow][curCol-1] == -1 && current.canMoveWest()) ) {
				visited[curRow][curCol-1] = distance+1;
				next =myMaze.getRoom(curRow,curCol - 1);
				path.add(next);
			}
			 if(curRow > 0 && visited[curRow-1][curCol] == -1 && current.canMoveSouth()){
				 visited[curRow-1][curCol] = distance+1;
				 next = myMaze.getRoom(curRow-1,curCol);
					path.add(next);
			}
			if (curCol+1 <myMaze.size() && (visited[curRow][curCol+1] == -1) && current.canMoveEast() ) {
				visited[curRow][curCol+1] = distance+1;
				next = myMaze.getRoom(curRow,curCol + 1);
				path.add(next);
			}
			if (curRow+1 <myMaze.size() && visited[curRow+1][curCol] == -1 && current.canMoveNorth()) {
				visited[curRow+1][curCol] = distance+1;
				next = myMaze.getRoom(curRow+1,curCol);
				path.add(next);
				}
			 
		}
		current = myMaze.getRoom(end.y, end.x);
		path.push(current);
		while(!current.equals(origin)){
			current = path.peek();
			next=getShortestDistance(current);
			path.push(next);
		}
		return path;
	}
	private MazeRoom getShortestDistance(MazeRoom start){
		int row = start.getRow();
		int col = start.getCol();
		int distance = visited[row][col];
		MazeRoom next;
		if (row+1 < myMaze.size() && visited[row+1][col] == distance-1 ) {
			next = myMaze.getRoom(row+1,col);
			return next;
		}
		if (row > 0 && visited[row-1][col] == distance-1 ) {
			next = myMaze.getRoom(row-1,col);
			return next;
		}
		if (col+1 < myMaze.size() && visited[row][col+1] == distance-1 ) {
			next = myMaze.getRoom(row,col+1);
			return next;
		}
		if (col > 0  && visited[row][col-1] == distance-1 ) {
			next = myMaze.getRoom(row,col-1);
			return next;
		}
		return start;
	}
	
}
