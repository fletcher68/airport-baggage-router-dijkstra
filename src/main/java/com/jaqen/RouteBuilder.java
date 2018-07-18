package com.jaqen;

import java.util.ArrayList;
import java.util.List;

/**
 * Singleton to store computed route as it is being built and discovered. Called
 * whenever the computed route needs to be accessed anywhere in the program
 * 
 * @author Kent Fletcher
 * @date 7/12/2018
 *
 */
public class RouteBuilder
{

	/**
	 * The singleton instance
	 */
	private static RouteBuilder instance = null;

	/**
	 * Current route. It is dynamically updated during recursion
	 */
	private List<Integer> route;
	
	/**
	 * Once the recursive method has computed route, a route is stored 
	 */
	private List<List<Integer>> possibleRoutes;
	
	/**
	 * Private constructor enforcing that a singleton getInstance() method is called
	 * instead.
	 */
	private RouteBuilder()
	{

	}

	/**
	 * static initializer block
	 */
	static
	{
		try
		{
			instance = new RouteBuilder();
		}
		catch (Exception e)
		{
			throw new RuntimeException("Exception occured createing RouteBuilder singleton");
		}
	}

	/**
	 * get singleton instance
	 * 
	 * @return
	 */
	static RouteBuilder getInstance()
	{
		if (instance == null)
		{
			instance = new RouteBuilder();
		}
		return instance;
	}

	/**
	 * Initialize route when being used again for next route computation
	 */
	public void initRoute()
	{

		route = new ArrayList<Integer>();

		possibleRoutes = new ArrayList<List<Integer>>();

	}

	/**
	 * Method to add node to route
	 * 
	 * @param i
	 *          Integer
	 */
	public void addRouteNode(Integer i)
	{
		route.add(i);
	}

	/**
	 * Method to remove node from route
	 * 
	 * @param i
	 *          Integer
	 */
	public void removeRouteNode(Integer i)
	{
		route.remove(i);
	}

	/**
	 * Get current route
	 * 
	 * @return List<Integer>
	 */
	public List<Integer> getRoute()
	{
		return route;
	}

	/**
	 * Get the optimum route. This is current computed route based on the fewest
	 * number of hops. This optimum route doesn't take into consideration the travel
	 * time between nodes
	 * 
	 * @return List<Integer>
	 */

	public List<Integer> getOptimalRoute()
	{
		int min = 999;
		int ctr = 0;
		int index = 0;
		for (List<Integer> l : possibleRoutes)
		{
			if (min > l.size())
			{
				min = l.size();
				index = ctr;
			}
			ctr++;
		}
		return possibleRoutes.get(index);
	}


	/**
	 * Add possible route when it is computed. Called by Graph object when the graph
	 * route is computed
	 * 
	 * @param stashRoute
	 *          List<Integer>
	 */
	public void addPossibleRoute(List<Integer> r)
	{
		List<Integer> route = new ArrayList<Integer>();
		route.addAll(r);
		this.possibleRoutes.add(route);
	}

}
