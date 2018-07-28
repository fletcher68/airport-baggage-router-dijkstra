package com.fletcher;

/**
 * Domain object to store departure
 * 
 * @author Kent Fletcher
 * @date 7/12/2018
 *
 */
public class Departure
{

	String flightId;
	String flightGate;
	String destination;
	String flightTime;

	public String getFlightId()
	{
		return flightId;
	}

	public void setFlightId(String flightId)
	{
		this.flightId = flightId;
	}

	public String getFlightGate()
	{
		return flightGate;
	}

	public void setFlightGate(String flightGate)
	{
		this.flightGate = flightGate;
	}

	public String getDestination()
	{
		return destination;
	}

	public void setDestination(String destination)
	{
		this.destination = destination;
	}

	public String getFlightTime()
	{
		return flightTime;
	}

	public void setFlightTime(String flightTime)
	{
		this.flightTime = flightTime;
	}
}
