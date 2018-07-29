package com.fletcher;

/**
 * Custom Exception thrown for invalid input
 * 
 * @author Kent Fletcher
 * @date 7/15/2018
 */
public class InvalidInputException extends Exception
{

	/**
	 * serial id
	 */
	private static final long serialVersionUID = 8711755235601383320L;

	public InvalidInputException(String msg)
	{
		super(msg);
	}
}
