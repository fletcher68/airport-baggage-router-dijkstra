package com.fletcher;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Domain object to store the node. All nodes stored in internal hashmap
 * 
 * @author Kent Fletcher
 * @date 7/12/2018
 *
 */
public class Node implements Comparable<Node>
{

	private static Map<String, Node> nodes = new HashMap<String, Node>();
	private static Integer nodeCounter = 0;
	private String name;
	private Node previousNode = null;
	private Integer nodeId;
	private final Map<Node, Integer> neighbors = new HashMap<>();
	private Integer time = Integer.MAX_VALUE;

	/**
	 * Return node given the node name. If multiple calls to this method are called
	 * with the same name, the same node instance is returned
	 * 
	 * @param name
	 *          String
	 * @return Node
	 */
	public static Node getNode(String name)
	{
		Node n = nodes.get(name);
		if (n == null)
		{
			n = new Node(name);
			n.setNodeId(nodeCounter); // assign unique integer to node
			nodeCounter++;
			nodes.put(name, n);
		}
		return n;
	}

	/**
	 * Return the node instance given its unique integer identifier
	 * 
	 * @param id
	 *          Integer
	 * @return Node
	 */
	public static Node getNodeById(Integer id)
	{
		Node rtnNode = null;
		for (Node n : nodes.values())
		{
			if (n.getNodeId().equals(id))
			{
				rtnNode = n;
				break;
			}
		}
		return rtnNode;
	}

	private Node()
	{

	}

	/**
	 * Private constructor
	 * 
	 * @param name
	 */
	private Node(String name)
	{
		this.name = name;

	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String toString()
	{
		return name;
	}

	public Integer getNodeId()
	{
		return nodeId;
	}

	public void setNodeId(Integer nodeId)
	{
		this.nodeId = nodeId;
	}

	public static Integer getNodeCount()
	{
		return nodes.size();
	}

	public Map<Node, Integer> getNeighbors()
	{
		return neighbors;
	}

	public int getTime()
	{
		return time;
	}

	public void setTime(int time)
	{
		this.time = time;
	}

	public Node getPreviousNode()
	{
		return previousNode;
	}

	public void setPreviousNode(Node previousNode)
	{
		this.previousNode = previousNode;
	}

	public List<Node> getShortestPathTo()
	{
		List<Node> path = new ArrayList<Node>();
		path.add(this);
		Node node = this.getPreviousNode();
		while (node != null && !path.contains(node))
		{
			path.add(node);
			node = node.getPreviousNode();
		}

		Collections.reverse(path);
		return path;
	}

	@Override
	public int compareTo(Node o)
	{
		if (time == o.time)
			return name.compareTo(o.name);

		return Integer.compare(time, o.time);
	}

}
