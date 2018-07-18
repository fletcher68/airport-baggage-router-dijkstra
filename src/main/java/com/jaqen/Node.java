package com.jaqen;

import java.util.HashMap;
import java.util.Map;


/**
 * Domain object to store the node. All nodes stored in internal hashmap
 * 
 * @author Kent Fletcher
 * @date 7/12/2018
 *
 */
public class Node
{

	private static Map<String, Node> nodes = new HashMap<String, Node>();
	private static Integer nodeCounter = 0;
	private String name;

	private Integer nodeId;

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
}
