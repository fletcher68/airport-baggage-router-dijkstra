package com.fletcher;

/**
 * Custom Exception thrown iff node not present in GraphMap
 * 
 * @author Kent Fletcher
 * @date 7/15/2018
 */
public class GraphMapException extends RuntimeException
{

	/**
	 * Serial id
	 */
	private static final long serialVersionUID = 8081940240189417771L;

	/**
	 * Public constructor
	 * 
	 * @param message String displayed in this exception
	 */
	public GraphMapException(String message)
	{
		super(message);
	}
}
