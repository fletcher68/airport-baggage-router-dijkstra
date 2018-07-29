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

	Node start;
	Node end;
	Integer travelTime;

	public Node getStart()
	{
		return start;
	}

	public void setStart(Node n)
	{
		this.start = n;
	}

	public Node getEnd()
	{
		return end;
	}

	public void setEnd(Node n)
	{
		this.end = n;
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
		return "[" + start + "][" + end + "][" + travelTime + "]";
	}
}
