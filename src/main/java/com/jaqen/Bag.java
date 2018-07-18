package com.jaqen;

/**
 * Bag domain object
 * 
 * @author Kent Fletcher
 * @date 7/12/2018
 *
 */
public class Bag
{
	private String bagNumber;
	private String entryPoint;
	private String flightId;

	public String getBagNumber()
	{
		return bagNumber;
	}

	public void setBagNumber(String bagNumber)
	{
		this.bagNumber = bagNumber;
	}

	public String getEntryPoint()
	{
		return entryPoint;
	}

	public void setEntryPoint(String entryPoint)
	{
		this.entryPoint = entryPoint;
	}

	public String getFlightId()
	{
		return flightId;
	}

	public void setFlightId(String flightId)
	{
		this.flightId = flightId;
	}

}
