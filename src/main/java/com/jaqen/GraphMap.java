package com.jaqen;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NavigableSet;
import java.util.TreeSet;

public class GraphMap
{
	private Map<String, Node> graphMap;

	public GraphMap()
	{

	}
	
	public void buildGraphMap(List<ConveyorSystem> conveyorSystemRoutes)
	{
		graphMap = new HashMap<>(conveyorSystemRoutes.size());

		for (ConveyorSystem e : conveyorSystemRoutes)
		{
			String start = e.getNode1().getName();
			String end = e.getNode2().getName();
			if (!graphMap.containsKey(start))
				graphMap.put(start, Node.getNode(start));
			if (!graphMap.containsKey(end))
				graphMap.put(end, Node.getNode(end));
		}

		for (ConveyorSystem e : conveyorSystemRoutes)
		{
			String start = e.getNode1().getName();
			String end = e.getNode2().getName();
			graphMap.get(start).getNeighbors().put(graphMap.get(end), e.getTravelTime());
		}

		for (ConveyorSystem e : conveyorSystemRoutes)
		{
			String start = e.getNode1().getName();
			String end = e.getNode2().getName();
			Node n = graphMap.get(end);
			Node s = graphMap.get(start);
			s.getNeighbors().put(n, e.getTravelTime());
		}
	}

	/**
	 * Run algorithm using a specified source node. Every time when the
	 * starting node get changed, this algorithm should get run.
	 * 
	 * @param startName
	 *          the starting or source node for the path
	 */
	public void computePaths(String startName)
	{
		if (!graphMap.containsKey(startName))
		{
			throw new GraphMapException(
					"This GraphMap does not contain the starting Node named:" + startName);
		}

		Node start = graphMap.get(startName);
		NavigableSet<Node> queue = new TreeSet<>();

		for (Node n : graphMap.values())
		{
			n.setPreviousNode(n == start ? start : null);
			n.setTime(n == start ? 0 : Integer.MAX_VALUE);
			queue.add(n);
		}

		traverseNodes(queue);
	}

	/**
	 * Get the shortest path as a list of nodes for a specific destination Node
	 * with name as endName
	 * 
	 * @param endName
	 *          the destination node name
	 * @return the shortest path as a List<Node>
	 */

	public List<Node> getShortestPath(String endName)
	{
		if (!graphMap.containsKey(endName))
		{
			throw new GraphMapException("Graph doesn't contain end node : " + endName);
		}

		return graphMap.get(endName).getShortestPathTo();
	}

	private void traverseNodes(final NavigableSet<Node> q)
	{
		while (!q.isEmpty())
		{
			Node n = q.pollFirst(); 
			if (n.getTime() == Integer.MAX_VALUE)
				break; 

			for (Map.Entry<Node, Integer> a : n.getNeighbors().entrySet())
			{
				Node neighbor = a.getKey();

				final int alternateTime = n.getTime() + a.getValue();
				if (alternateTime < neighbor.getTime())
				{ 
					/*
					 * Then a shorter path to neighbor found
					 */
					q.remove(neighbor);
					neighbor.setTime(alternateTime);
					neighbor.setPreviousNode(n);
					q.add(neighbor);
				}
			}
		}
	}
}
