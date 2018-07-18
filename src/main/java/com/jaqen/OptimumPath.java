package com.jaqen;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OptimumPath
{
	Map<String, GraphMap> visitedMap = new HashMap<>();

	public String findShortestPath(String start, String end, List<ConveyorSystem> conveyorSystemRoutes)
	{
		GraphMap gm;
		if (visitedMap.containsKey(start))
		{
			gm = visitedMap.get(start);
		}
		else
		{
			gm = new GraphMap();
			gm.buildGraphMap(conveyorSystemRoutes);
			gm.computePaths(start);
			visitedMap.put(start, gm);
		}

		List<Node> shortestPath = gm.getShortestPath(end);
		return getPathString(shortestPath);
	}

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
