package com.jaqen;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NavigableSet;
import java.util.TreeSet;

public class DijkstraGraphMap
{
	// mapping of vertex names to Vertex objects, built from a set of Edges
	private final Map<String, Node> graphMap;

	public DijkstraGraphMap(List<ConveyorSystem> directedEdges)
	{

		graphMap = new HashMap<>(directedEdges.size());

		// Populated all the vertices from the edges
		for (ConveyorSystem e : directedEdges)
		{
			if (!graphMap.containsKey(e.getNode1().getName()))
				graphMap.put(e.getNode1().getName(), Node.getNode(e.getNode1().getName()));
			if (!graphMap.containsKey(e.getNode2().getName()))
				graphMap.put(e.getNode2().getName(), Node.getNode(e.getNode2().getName()));
		}

		// Set all the neighbours
		for (ConveyorSystem e : directedEdges)
		{
			graphMap.get(e.getNode1().getName()).getNeighbours().put(graphMap.get(e.getNode2().getName()), e.getTravelTime());
		}
		// Set all the neighbours
		for (ConveyorSystem e : directedEdges)
		{
			Node n = graphMap.get(e.getNode2().getName());
			Node s = graphMap.get(e.getNode1().getName());
			s.getNeighbours().put(n, e.getTravelTime());
		}
	}

	/**
	 * Runs dijkstra algorithm using a specified source vertex. Every time when the
	 * starting vertex get changed, this algorithm should get run.
	 * 
	 * @param startName
	 *          the starting or source Vertex for the path
	 */
	public void dijkstra(String startName)
	{
		if (!graphMap.containsKey(startName))
		{
			throw new DijkstraGraphMapException(
					"This DijkstraGraphMap does not contain the starting Vertex named:" + startName);
		}
		final Node source = graphMap.get(startName);
		NavigableSet<Node> queue = new TreeSet<>();

		// populate vertices to the queue
		for (Node v : graphMap.values())
		{
			v.setPreviousNode(v == source ? source : null);
			v.setTime(v == source ? 0 : Integer.MAX_VALUE);
			queue.add(v);
		}

		dijkstra(queue);
	}

	/**
	 * Get the shortest path as a list of Vertex for a specific destination Vertex
	 * with name as endName
	 * 
	 * @param endName
	 *          the destination vertex name
	 * @return the shortest path as a List<Vertex>
	 */

	public List<Node> getShortestPath(String endName)
	{
		if (!graphMap.containsKey(endName))
		{
			throw new DijkstraGraphMapException("Graph doesn't contain end vertex : " + endName);
		}

		return graphMap.get(endName).getShortestPathTo();
	}

	// Implementation of dijkstra's algorithm using a binary heap.
	private void dijkstra(final NavigableSet<Node> que)
	{
		Node source, neighbour;
		while (!que.isEmpty())
		{

			source = que.pollFirst(); // vertex with shortest distance (first iteration will return source)
			if (source.getTime() == Integer.MAX_VALUE)
				break; // ignore u (and any other remaining vertices) since they are unreachable

			// look at distances to each neighbour
			for (Map.Entry<Node, Integer> a : source.getNeighbours().entrySet())
			{
				neighbour = a.getKey();

				final int alternateTime = source.getTime() + a.getValue();
				if (alternateTime < neighbour.getTime())
				{ // shorter path to neighbour found
					que.remove(neighbour);
					neighbour.setTime(alternateTime);
					neighbour.setPreviousNode(source);
					que.add(neighbour);
				}
			}
		}
	}
}
