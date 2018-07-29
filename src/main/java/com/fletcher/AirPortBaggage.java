package com.fletcher;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Main class The system will route baggage checked, connecting, and 
 * terminating in Denver per the exercise. Will route bags to their 
 * flights or the proper baggage claim
 * 
 * @author Kent Fletcher
 * @date 7/12/2018
 *
 */
public class AirPortBaggage
{

	/**
	 * stdin reader
	 */
	private static final Scanner scanner = new Scanner(System.in);

	/**
	 * Main entry point. To execute on command line > java -jar
	 * airport-baggage-router-1.0.jar < airport-baggage.in
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException
	{

		AirPortBaggageRouter abr = new AirPortBaggageRouter();

		scanner.nextLine();

		try
		{
			readConveyorSystemData(abr);

			readDepartureData(abr);

			readBaggageData(abr);

			scanner.close();			
			
			outputBaggageRouteData(abr);

		}
		catch (InvalidInputException e)
		{
			System.out.println(e.getMessage());
		}
	}


	/**
	 * Read conveyor system data
	 * 
	 * @param abr
	 *          AirPortBaggageRouter
	 */
	public static void readConveyorSystemData(AirPortBaggageRouter abr) throws InvalidInputException
	{
		/*
		 * Read Section 1 data - Conveyer System <node1> <node2> <travel-time)
		 */
		boolean readSection = true;
		while (readSection)
		{

			String line = scanner.nextLine();
			if (line.indexOf("Section") >= 0)
			{
				readSection = false;
				continue;
			}
			String[] routeLegs = line.split(" ");

			if (routeLegs.length != 3)
				throw new InvalidInputException("ERROR: Number of input columns on conveyor system input section should be 3 columns on line => " + line);

			ConveyorSystem cs1 = new ConveyorSystem();
			Node p1 = Node.getNode(routeLegs[0]);
			Node p2 = Node.getNode(routeLegs[1]);
			Integer travelTime = Integer.parseInt(routeLegs[2]);

			cs1.setStart(p1);
			cs1.setEnd(p2);
			cs1.setTravelTime(travelTime);

			abr.addConveyorSystem(cs1);

			ConveyorSystem cs2 = new ConveyorSystem();
			cs2.setStart(p2);
			cs2.setEnd(p1);
			cs2.setTravelTime(travelTime);

			abr.addConveyorSystem(cs2);

		}

	}

	/**
	 * Read departure data
	 * 
	 * @param abr
	 *          AirPortBaggageRouter
	 */
	public static void readDepartureData(AirPortBaggageRouter abr) throws InvalidInputException
	{
		/* read section 2 departure data */
		boolean readSection = true;
		while (readSection)
		{

			String line = scanner.nextLine();
			if (line.indexOf("Section") >= 0)
			{
				readSection = false;
				continue;
			}
			String[] departures = line.split(" ");

			if (departures.length != 4)
				throw new InvalidInputException("ERROR: Number of input columns on departure input section should be 4 columns on line => " + line);

			String flightId = departures[0];
			String flightGate = departures[1];
			String destination = departures[2];
			String flightTime = departures[3];

			Departure departure = new Departure();
			departure.setFlightId(flightId);
			departure.setFlightGate(flightGate);
			departure.setDestination(destination);
			departure.setFlightTime(flightTime);

			abr.addDeparture(departure);

		}

	}

	/**
	 * Read baggage input
	 * 
	 * @param abr
	 *          AirPortBaggageRouter
	 */
	public static void readBaggageData(AirPortBaggageRouter abr) throws InvalidInputException
	{
		/* read section 3 baggage data */
		boolean readSection = true;
		while (readSection)
		{

			String line = scanner.nextLine();
			if (line.indexOf("Section") >= 0 || line.trim().isEmpty())
			{
				readSection = false;
				continue;
			}
			String[] bagdata = line.split(" ");

			if (bagdata.length != 3)
				throw new InvalidInputException("ERROR: Number of input columns on bag input section should be 3 columns on line => " + line);

			String bagNumber = bagdata[0];
			String entryPoint = bagdata[1];
			String flightId = bagdata[2];

			Bag bag = new Bag();
			bag.setBagNumber(bagNumber);
			bag.setEntryPoint(entryPoint);
			bag.setFlightId(flightId);
			abr.addBag(bag);
		}
	}

	/**
	 * Write baggage route output to stdout and to output file "airport-baggage.out"
	 * 
	 * @param abr
	 *          AirPortBaggageRouter
	 * @param g
	 *          Graph
	 * @throws IOException
	 */
	public static void outputBaggageRouteData(AirPortBaggageRouter abr) throws IOException
	{
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("airport-baggage.out"));

		DijkstraAlgorithm dijkstraAlgorithm = new DijkstraAlgorithm();
		
		for (Bag bag : abr.getBags())
		{
			Departure departure = abr.getDeparture(bag);

			String start = null;
			String end = null;
			if (bag.getFlightId().equals("ARRIVAL"))
			{
				start = bag.getEntryPoint();
				end = "BaggageClaim";
			}
			else
			{
				start = bag.getEntryPoint();
				end = departure.getFlightGate();
			}


			String path = dijkstraAlgorithm.findShortestPath(start, end, abr.getConveyorSystems());
			
			System.out.print(bag.getBagNumber() + " ");
			bufferedWriter.write(bag.getBagNumber() + " ");

			System.out.print(path);
			bufferedWriter.write(path);

			System.out.println("");
			bufferedWriter.newLine();
		}

		bufferedWriter.close();

	}

}
