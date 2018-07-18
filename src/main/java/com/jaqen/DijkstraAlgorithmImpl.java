package com.jaqen;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

 
public class DijkstraAlgorithmImpl
{
	Map<String, DijkstraGraphMap> visitedMap = new ConcurrentHashMap<>(); 

	public String findShortestPath(String start, String end, List<ConveyorSystem> edges)
	{
		DijkstraGraphMap dgm;
		if (visitedMap.containsKey(start))
		{
			dgm = visitedMap.get(start);
		}
		else
		{
			dgm = new DijkstraGraphMap(edges);
			dgm.dijkstra(start);
			visitedMap.put(start, dgm);
		}

		List<Node> shortestPath = dgm.getShortestPath(end);
		return generatePathLine(shortestPath);
	}

	private String generatePathLine(List<Node> path)
	{
		StringBuffer line = new StringBuffer();

		for (Node vertex : path)
		{
			line.append(vertex.getName()).append(" ");
		}
		line.append(": ").append(path.get(path.size() - 1).getTime());
		return line.toString();
	}

}
