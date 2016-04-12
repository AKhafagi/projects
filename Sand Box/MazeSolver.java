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

	boolean[][] visited;

	public MazeSolver(Maze toSolve) {
		myMaze = toSolve;
		visited = new boolean[toSolve.size()][toSolve.size()];
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
		Deque<MazeRoom> solution = new LinkedList<MazeRoom>();
		int distance = 0;
		MazeRoom current = myMaze.getRoom(start.y, start.x);
		MazeRoom next;
		path.add(current);
		while(!path.isEmpty()){
			current = path.remove();
			visited[current.getRow()][current.getCol()] = true;
			if(current.posInMaze.equals(end)){
				break;
			}
			if (current.canMoveNorth() && current.getRow()+1 >myMaze.size()  ) {
				next = myMaze.getRoom(start.y+1, start.x);
				next.setDistanceToStart(distance+1);
				path.add(next);
			}
			 if (current.canMoveSouth() && current.getCol()>0) {
				next = myMaze.getRoom(start.y-1, start.x);
				next.setDistanceToStart(distance+1);
				path.add(next);
				
			}
			 if (current.canMoveEast()&& current.getCol()+1< myMaze.size()) {
				next = myMaze.getRoom(start.y, start.x+1 );
				next.setDistanceToStart(distance+1);
				path.add(next);
				
			}
			 if (current.canMoveWest()&& current.getRow()> 0) {
				next = myMaze.getRoom(start.y, start.x-1);
				next.setDistanceToStart(distance+1);
				path.add(next);
				
			}
		}
		solution.push(myMaze.getRoom(end.y, end.x));
		while(!solution.isEmpty()){
			current = solution.peekFirst();
			if(current.posInMaze.equals(start)){
				return solution;
			}
			
			next=getShortestDistance(current);
			solution.push(next);
					
		}
		return solution;
		
	}
	private MazeRoom getShortestDistance(MazeRoom start){
		Deque<MazeRoom> path = getNeighbors(start);
		int distance = start.getDistanceToStart();
		MazeRoom child;
		MazeRoom min = start;
		while(!path.isEmpty()){
			child = path.remove();
			if(child.getDistanceToStart() < distance){
				min = child;	
			}
		}
		return min;
	}
	private Deque<MazeRoom> getNeighbors(MazeRoom end){
		Deque<MazeRoom> path = new LinkedList<MazeRoom>();
		MazeRoom next;
		if (end.canMoveNorth() && end.getCol() > 0 ) {
			next = myMaze.getRoom(end.getRow(), end.getCol()-1);
			path.add(next);
		}
		 if (end.canMoveSouth() && end.getCol()+1< myMaze.size()) {
			next = myMaze.getRoom(end.getRow(), end.getCol()+1);
			path.add(next);
		}
		 if (end.canMoveEast()&& end.getRow()+1< myMaze.size()) {
			next = myMaze.getRoom(end.getRow()+1, end.getCol() );
			path.add(next);
		}
		 if (end.canMoveWest()&& end.getRow()> 0) {
			next = myMaze.getRoom(end.getRow()-1, end.getCol());
			path.add(next);
		}
		 return path;
	}
}
