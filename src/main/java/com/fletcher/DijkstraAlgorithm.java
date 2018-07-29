package com.fletcher;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The Dijkstra Algorithm class for determining the shortest path between two
 * nodes given a collection of Conveyor Systems. This could be refactored to
 * used generics.
 * 
 * @author Kent Fletcher
 * @date 7/15/2018
 */
public class DijkstraAlgorithm
{
	/**
	 * private internal map of visited nodes
	 */
	private Map<String, GraphMap> visitedMap = new HashMap<>();

	/**
	 * The primary public method for executing Dijkstra's algorithm for finding the
	 * shortest path between 2 nodes given a list of directed edges, in our case a
	 * list of conveyor system segments
	 * 
	 * @param start                String name of start node
	 * @param end                  String name of end node
	 * @param conveyorSystemRoutes List<ConveyorSystem> list of conveyor systems
	 * @return a string defining the shortest path of nodes and the total travel
	 *         time
	 */
	public String findShortestPath(String start, String end, List<ConveyorSystem> conveyorSystemRoutes)
	{
		GraphMap gm;
		if (visitedMap.containsKey(start))
		{
			gm = visitedMap.get(start);
		} else
		{
			gm = new GraphMap();
			gm.buildGraphMap(conveyorSystemRoutes);
			gm.computePaths(start);
			visitedMap.put(start, gm);
		}

		List<Node> shortestPath = gm.getShortestPath(end);
		return getPathString(shortestPath);
	}

	/**
	 * Return string representation of path give a list of nodes passed in as an
	 * argument
	 * 
	 * @param path List<Node> list of nodes
	 * @return String the path string
	 */
	private String getPathString(List<Node> path)
	{
		StringBuffer line = new StringBuffer();

		for (Node node : path)
		{
			line.append(node.getName()).append(" ");
		}
		line.append(": ").append(path.get(path.size() - 1).getTime());
		return line.toString();
	}

}
