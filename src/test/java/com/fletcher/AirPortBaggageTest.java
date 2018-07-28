package com.fletcher;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.runners.MethodSorters;
/**
 * 
 * AirPortBaggage test class. Test coverage is mainly on the Graph object since
 * this class is the backbone of the application. Additional test coverage is
 * limited due to time constraints.
 * 
 * @author Kent Fletcher
 * @date 7/12/2018
 *
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class AirPortBaggageTest
{
	

	/**
	 * This method is an attempt to test happy path the application beyond the
	 * Graph object.  All input is valid. The AirPortBaggage class is the main entry point and reads
	 * stdin. The contents of the input file is stored as a string, and written to a
	 * ByteArrayInputStream to mock stdin.  The stdout is written to ByteArrayOutputStream
	 * Asserts Expected result compare with stdout 
	 *  
	 */
	@Test
	void testXInput() 
	{
		String input = "# Section: Conveyor System\n" + 
				"Concourse_A_Ticketing A5 5\n" + 
				"A5 BaggageClaim 5\n" + 
				"A5 A10 4\n" + 
				"A5 A1 6\n" + 
				"A1 A2 1\n" + 
				"A2 A3 1\n" + 
				"A3 A4 1\n" +
				"A10 A9 1\n" + 
				"A9 A8 1\n" + 
				"A8 A7 1\n" + 
				"A7 A6 1\n" + 
				"# Section: Departures\n" + 
				"UA10 A1 MIA 08:00\n" + 
				"UA11 A1 LAX 09:00\n" + 
				"UA12 A1 JFK 09:45\n" + 
				"UA13 A2 JFK 08:30\n" + 
				"UA14 A2 JFK 09:45\n" + 
				"UA15 A2 JFK 10:00\n" + 
				"UA16 A3 JFK 09:00\n" + 
				"UA17 A4 MHT 09:15\n" + 
				"UA18 A5 LAX 10:15\n" + 
				"# Section: Bags\n" + 
				"0001 Concourse_A_Ticketing UA12\n" + 
				"0002 A5 UA17\n" + 
				"0003 A2 UA10\n" + 
				"0004 A8 UA18\n" + 
				"0005 A7 ARRIVAL\n" +
				"\n";

		String expected = "0001 Concourse_A_Ticketing A5 A1 : 11\n" + 
				"0002 A5 A1 A2 A3 A4 : 9\n" + 
				"0003 A2 A1 : 1\n" + 
				"0004 A8 A9 A10 A5 : 6\n" + 
				"0005 A7 A8 A9 A10 A5 BaggageClaim : 12\n";
		
		System.setIn(new ByteArrayInputStream(input.getBytes()));

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(baos);
		System.setOut(ps);

		try
		{
			String[] args =
			{ "", "" };
			AirPortBaggage.main(args);
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		} 
		catch (java.util.NoSuchElementException e)
		{
			// e.printStackTrace();
			System.out.println("ERROR");
		}

		String output = baos.toString();

		if (!expected.equals(output))
		{
			System.out.println("ERROR, stdout does not match expected result");
		}

		assert expected.equals(output);
	}
	
}
