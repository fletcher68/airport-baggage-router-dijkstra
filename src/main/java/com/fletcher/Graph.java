package com.fletcher;

import java.util.ArrayList;
import java.util.List;

/**
 * Graph class stores collection of nodes and provides method to define which
 * nodes connect and provides a method to compute the route between nodes
 * 
 * @author Kent Fletcher
 * @date 7/12/2018
 *
 */
public class Graph
{

	/**
	 * the total number of nodes defined in this graph
	 */
	private int numNodes;

	/**
	 * adjacent list array contains collection of all nodes in the graph and their
	 * corresponding adjacent nodes
	 */
	private ArrayList<Integer>[] adjList;

	/**
	 * public constructor for Graph object
	 * 
	 * @param numNodes
	 *          int the total number of nodes defined in the node graph
	 */
	public Graph(int numNodes)
	{
		this.numNodes = numNodes;
		initAdjList();
	}

	/**
	 * Method to initialize the adjacent node list
	 */
	@SuppressWarnings("unchecked")
	private void initAdjList()
	{
		adjList = new ArrayList[numNodes];

		for (int i = 0; i < numNodes; i++)
		{
			adjList[i] = new ArrayList<>();
		}
	}

	/**
	 * Method to define route between two nodes
	 * 
	 * @param node
	 *          int
	 * @param adjNode
	 *          int
	 */
	public void addRoute(int node, int adjNode)
	{
		adjList[node].add(adjNode);
	}

	/**
	 * Compute the path between a start and end node given a collection of nodes and
	 * node routes
	 * 
	 * @param start
	 *          int node start
	 * @param end
	 *          int node end
	 * @return List<Integer> returns route as list of integers
	 */
	public List<Integer> computePath(int start, int end, AirPortBaggageRouter abr)
	{

		RouteBuilder rb = RouteBuilder.getInstance();
		rb.initRoute();

		boolean[] isVisited = new boolean[numNodes];
		ArrayList<Integer> pathList = new ArrayList<>();

		rb.addRouteNode(start);
		pathList.add(start);

		computePath(start, end, isVisited, pathList);

		return RouteBuilder.getInstance().getOptimalRoute(abr);
	}
	
	/**
	 * Compute the path between a start and end node given a collection of nodes and
	 * node routes
	 * 
	 * @param start
	 *          int node start
	 * @param end
	 *          int node end
	 * @return List<Integer> returns route as list of integers
	 */
	public List<Integer> computePath(int start, int end)
	{

		RouteBuilder rb = RouteBuilder.getInstance();
		rb.initRoute();

		boolean[] isVisited = new boolean[numNodes];
		ArrayList<Integer> pathList = new ArrayList<>();

		rb.addRouteNode(start);
		pathList.add(start);

		computePath(start, end, isVisited, pathList);

		return RouteBuilder.getInstance().getOptimalRoute(null);
	}

	/**
	 * Private method computes path between 2 nodes given a collection of node
	 * routes. Uses recursion to determine path between nodes in the graph
	 * 
	 * @param start
	 *          int starting node the path is to be computed from
	 * @param end
	 *          int ending node the path is to be computed from
	 * @param isVisited
	 *          boolean[] array of boolean flags denoting if a node has been visited
	 *          or not
	 * @param localPathList
	 *          List<Integer> array list of node routes that is computed.
	 */
	private void computePath(Integer start, Integer end, boolean[] isVisited, List<Integer> localPathList)
	{

		/*
		 * mark current node visited to true
		 */
		isVisited[start] = true;

		/*
		 * if start = end, stash this route in a singleton. This is the computed route
		 * after this method is called recursively
		 */
		if (start.equals(end))
		{
			RouteBuilder.getInstance().addPossibleRoute(localPathList);
		}

		/*
		 * Iterate through all nodes and their adjacent nodes recursively
		 */
		for (Integer i : adjList[start])
		{
			if (!isVisited[i])
			{

				/*
				 * Store current node in path
				 */
				RouteBuilder.getInstance().addRouteNode(i);
				localPathList.add(i);

				/*
				 * Recursive call
				 */
				computePath(i, end, isVisited, localPathList);

				/*
				 * Remove current node from path
				 */
				RouteBuilder.getInstance().removeRouteNode(i);
				localPathList.remove(i);
			}
		}

		/*
		 * mark node isVisited false
		 */
		isVisited[start] = false;
	}

}
