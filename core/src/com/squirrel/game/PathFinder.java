/**
 * This class creates a graph out of a tiled map and allows you to find
 * the shortest distance between two tiles (nodes in the graph).
 * 
 * @author Jacob Rust
 */

package com.squirrel.game;

import java.util.ArrayList;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class PathFinder {

	private int height;
	private int width;
	private Node[][] nodeGraph;
	private ArrayList<Node> closedSet;
	private ArrayList<Node> openSet;
	
	/**
	 * Constructs a new PathFinder object. Creates a graph from 
	 * the tiles found in the specified layer of a map. Tiles that 
	 * cannot be traveled through should have a property titled
	 * "blocked".
	 * @param mapLayer Layer of a map that contains the tiles
	 * @param layerNumber The layer that specifies which tiles can
	 * be traveled across by giving tiles the property "blocked".
	 */
	public PathFinder(TiledMapTileLayer mapLayer) {
		height = mapLayer.getHeight();
		width = mapLayer.getWidth();
		createNodes(mapLayer);
		closedSet = new ArrayList<Node>();
		openSet = new ArrayList<Node>();
		
//		//Log for testing
//		Gdx.app.log("height: ", ((Integer) mapLayer.getHeight()).toString());
//		Gdx.app.log("width: ", ((Integer) mapLayer.getWidth()).toString());
	}
	
	/**
	 * Updates the map the path finder uses. This is to improve memory
	 * when a map is constantly updated, eliminating the need to create
	 * more path finder objects
	 * @param mapLayer tiled map layer to find path on
	 */
	public void updateMap(TiledMapTileLayer mapLayer) {
		reset();
		height = mapLayer.getHeight();
		width = mapLayer.getWidth();
		createNodes(mapLayer);
	}
	
	/**
	 * Creates the graph and fills it with nodes corresponding
	 * to the given map layer.
	 * @param layerMap map layer that will be translated into the graph
	 */
	private void createNodes(TiledMapTileLayer layerMap) {
		nodeGraph = new Node[width][height];
		
		//Fill graph with nodes corresponding to tiles
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				//Create a node at the tile's position and check if it is blocked
				boolean blocked = false;
				Cell cell = layerMap.getCell(x, y);
				
				//Check if this cell can be traveled across
				if (cell != null) {
					blocked = cell.getTile().getProperties().containsKey("blocked");
				}
				
				Node newNode = new Node(new Vector2(x, y), blocked);
				
				//If node is not blocked, put it in the graph
				if (!newNode.isBlocked) {
					nodeGraph[x][y] = newNode;
				}
			}
		}
	}
	
	/**
	 * Finds the distance between two vectors
	 * @param a The first vector
	 * @param b The second vector
	 * @return The distance between a and b
	 */
	private float dist(Vector2 a, Vector2 b) {
		return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
	}
	
	/**
	 * Restores everything in the graph to its 
	 * properties from construction allowing a new
	 * path to be searched for.
	 */
	private void reset() {
		openSet.clear();
		closedSet.clear();
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				Node node = nodeGraph[x][y];
				if (node != null) {
					node.distFromStart = 0;
					node.distFromGoal = 0;
					node.cameFrom = null;
				}
			}
		}
	}
	
	/**
	 * Finds the node with the best "f" value where
	 * f = g + h. g is the amount of nodes from the specific
	 * node to the start point. h is the heuristic function, 
	 * distance(a, b), giving the distance between the specific
	 * node and the end point.
	 * @return The node with the best estimate for "f"
	 */
	private Node findBestEstimateNode() {
		float bestEstimate;
		
		//Start with the first node in the openSet being the best
		Node best = openSet.get(0);
		if (best == null) {
			return null;
		} else {
			bestEstimate = best.getEstimate();
		}
		
		//Check the rest of the nodes in the openSet for a better value
		for (Node n : openSet) {
			if (n.getEstimate() < bestEstimate) {
				bestEstimate = n.getEstimate();
				best = n;
			}
		}
		return best;
	}
	
	/**
	 * Generates the neighbors of a specific node and returns
	 * them in an array list.
	 * @param node Node to find neighbors of
	 * @return ArrayList of node's neighbor nodes
	 */
	private ArrayList<Node> generateNeighbors(Node node) {
		ArrayList<Node> neighbors = new ArrayList<Node>();

		////Make neighbors in each of the four possible directions
		Vector2[] possibleNeighbors = new Vector2[4];
		possibleNeighbors[0] = new Vector2(node.position.x-1, node.position.y);
		possibleNeighbors[1] = new Vector2(node.position.x+1, node.position.y);
		possibleNeighbors[2] = new Vector2(node.position.x, node.position.y-1);
		possibleNeighbors[3] = new Vector2(node.position.x, node.position.y+1);
		
		/*
		 * Switch the above possible neighbors with this to allow diagonal movement.
		 */
//		//Make neighbors in each of the eight possible directions
//		Vector2[] possibleNeighbors = new Vector2[8];
//		possibleNeighbors[0] = new Vector2(node.position.x-1, node.position.y);
//		possibleNeighbors[1] = new Vector2(node.position.x+1, node.position.y);
//		possibleNeighbors[2] = new Vector2(node.position.x, node.position.y-1);
//		possibleNeighbors[3] = new Vector2(node.position.x, node.position.y+1);
//		possibleNeighbors[4] = new Vector2(node.position.x-1, node.position.y-1);
//		possibleNeighbors[5] = new Vector2(node.position.x-1, node.position.y+1);
//		possibleNeighbors[6] = new Vector2(node.position.x+1, node.position.y-1);
//		possibleNeighbors[7] = new Vector2(node.position.x+1, node.position.y+1);
	
		//Add the valid neighbors to the node
		for (int i = 0; i < possibleNeighbors.length; i++) {
			
			//Don't add it if the neighbor is invalid (off the map)
			if (possibleNeighbors[i].x < 0 || possibleNeighbors[i].x > width - 1 || 
					possibleNeighbors[i].y < 0 || possibleNeighbors[i].y > height - 1) {
				continue;
			}
			
			Node neighbor = nodeGraph[(int) possibleNeighbors[i].x][(int) possibleNeighbors[i].y];
			
			//Add the neighbor if it is not null or blocked 
			if (neighbor != null && !neighbor.isBlocked) {
				neighbors.add(neighbor); 
			}
			
		}
		return neighbors;
	}
	
	/**
	 * Checks if an array list contains a node with the 
	 * specified vector as its position;
	 * @param list List to check
	 * @param position Position checking for
	 * @return true if the list contains the position, else false
	 */
	private boolean listContainsPosition(ArrayList<Node> list, Vector2 position) {
		boolean contains = false;
		for (Node n : list) {
			if (n.position == position) {
				contains = true;
			}
		}
		return contains;
	}
	
	/**
	 * Gets the node from a list that has a position of the 
	 * specified vector.
	 * @param list List to check
	 * @param position Position to get
	 * @return The node with the position if found, else null
	 */
	public Node getNodeWithPosition(ArrayList<Node> list, Vector2 position) {
		Node target = null;
		for (Node n : list) {
			if (n.position == position) {
				target = n;
			}
		}
		return target;
	}
	
	/**
	 * Retraces the path found and stores it as an array of 
	 * vectors in the coordinate system of the map.   
	 * PRECONDITION: Path has been found
	 * @param start Node the path started on
	 * @param end Node the path ended on
	 * @return Array of vectors representing the path
	 */
	private Array<Vector2> retracePath(Node start, Node end) {
		Node successor = end.cameFrom;
		
		//Path in the graph's coordinates
		Array<Vector2> finalNodePath = new Array<Vector2>();
		
		//Path in the map's coordinates
		Array<Vector2> finalPath = new Array<Vector2>();
	
		//Retrace the node path
		while(successor != start) {
			finalNodePath.add(successor.position);
			successor = successor.cameFrom;
		}
		
		Vector2 temp;
		
		//Change back to screen coordinates
		for (int i = 0; i < finalNodePath.size; i++) {
			temp = new Vector2();
			temp.x = finalNodePath.get(i).x * 
					ScreenInfo.TILE_SIZE;
			temp.y = finalNodePath.get(i).y * 
					ScreenInfo.TILE_SIZE;
			finalPath.add(temp);
		}
		
		//log for testing
//		for (Vector2 v : finalNodePath) {
//			Gdx.app.log("NodePath: ", (v).toString());
//		}
//		
//		for (Vector2 v : finalPath) {
//			Gdx.app.log("Path: ", (v).toString());
//		}
		
		//Reset for future path finds
		reset();
		return finalPath;
	}
	
	/**
	 * Finds the shortest path between two tiles in a TiledMap using
	 * the A* graph search algorithm. 
	 * @param startPosition starting tile
	 * @param goalPosition end tile
	 * @return The shortest path as an array of 2d vectors, or null if 
	 * no path exists.
	 */
	public Array<Vector2> findShortestPath(Vector2 startPosition, Vector2 goalPosition) {
		closedSet.clear();
		openSet.clear();
		
		//Log for testing
//		Gdx.app.log("Start pos x: ", ((Float) startPosition.x).toString());
//		Gdx.app.log("Start pos y: ", ((Float) startPosition.y).toString());
//		Gdx.app.log("Goal pos x: ", ((Float) goalPosition.x).toString());
//		Gdx.app.log("Goal pos y: ", ((Float) goalPosition.y).toString());
		
		Node start = nodeGraph[(int) startPosition.x][(int) startPosition.y];
		Node goal = nodeGraph[(int) goalPosition.x][(int) goalPosition.y];
		
		//Return null if goal is unreachable
		if (goal == null || goal.position == null) {
			return null;
		}
		
		openSet.add(start);
		
		while (openSet.size() > 0) {
			
			//Find best "f"
			Node current = findBestEstimateNode();
			
			//If there was no best estimate found, stop searching
			if (current == null) {
				break;
			}
			
			openSet.remove(current);
			
			ArrayList<Node> neighbors = generateNeighbors(current);

			for (Node neighbor : neighbors) {
				
				if (neighbor == goal) {
					neighbor.cameFrom = current;
					return retracePath(start, goal);
				}
				
				//Only check neighbors that can be traveled over
				if (neighbor == null || neighbor.isBlocked) {
					continue;
				}
				
				//The distance between neighbors is always just 1
				neighbor.distFromStart = current.distFromStart + 1;
				
				//The heuristic function is just geometric distance between 
				neighbor.distFromGoal = dist(neighbor.position, goal.position);
				
				Node sameOpenNode = null;
				Node sameClosedNode = null;
				
				//Check if node with same position in open list
				if (listContainsPosition(openSet, neighbor.position)) {
					sameOpenNode = getNodeWithPosition(openSet, neighbor.position);
				}
				
				//Check if node with same position in closed list
				if (listContainsPosition(closedSet, neighbor.position)) {
					sameClosedNode = getNodeWithPosition(closedSet, neighbor.position);
				}
				
				//If there is already a node in the open or closed set with a better estimate, skip
				//this neighbor.
				if ((sameOpenNode != null && sameOpenNode.getEstimate() <= neighbor.getEstimate()) ||
						(sameClosedNode != null && sameClosedNode.getEstimate() <= neighbor.getEstimate()))	{
					continue;
				} else {
					neighbor.cameFrom = current;
					openSet.add(neighbor);
				}
			}
			//Put in closed set so we know we already checked this node
			closedSet.add(current);
		}
		//If it failed return null
		return null;
	}
	
	
	/*
	 * Represents a node in a graph for the A* Search Algorithm
	 */
	public class Node {
		private Vector2 position;
		private boolean isBlocked;
		private float distFromStart;
		private float distFromGoal;
		private Node cameFrom;
		
		public Node(Vector2 position, boolean isBlocked) {
			this.position = position;
			this.isBlocked = isBlocked;
			distFromStart = 0;
			distFromGoal = 0;
			cameFrom = null;
		}
		
		/*
		 * The estimate function for the algorithm:
		 * f = g + h 
		 */
		public float getEstimate() {
			return distFromStart + distFromGoal;
		}
		
		public String toString() {
			String out = "";
			out += "\nPosition: "+position;
			out += "\nBlocked?: "+isBlocked;
			out += "\ncameFrom: "+cameFrom;
			return out;
		}
	}
}
