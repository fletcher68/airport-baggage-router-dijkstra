package com.fletcher;

/**
 * Domain object to store conveyor system
 * 
 * @author Kent Fletcher
 * @date 7/12/2018
 *
 */
public class ConveyorSystem
{

	Node node1;
	Node node2;
	Integer travelTime;

	public Node getNode1()
	{
		return node1;
	}

	public void setNode1(Node n)
	{
		this.node1 = n;
	}

	public Node getNode2()
	{
		return node2;
	}

	public void setNode2(Node n)
	{
		this.node2 = n;
	}

	public Integer getTravelTime()
	{
		return travelTime;
	}

	public void setTravelTime(Integer travelTime)
	{
		this.travelTime = travelTime;
	}

	public String toString()
	{
		return "[" + node1 + "][" + node2 + "][" + travelTime + "]";
	}
}
