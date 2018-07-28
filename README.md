<h1>Airport Baggage Router</h1>

Denver International Airport has decided to give an automated baggage system 
another shot. The hardware and tracking systems from the previous attempt are 
still in place, they just need a system to route the baggage. The system will 
route baggage checked, connecting, and terminating in Denver. You have been 
asked to implement a system that will route bags to their flights or the 
proper baggage claim. The input describes the airport conveyor system, the 
departing flights, and the bags to be routed. The output is the optimal 
routing to get bags to their destinations. Bags with a flight id of “ARRIVAL” are terminating in Denver are routed to Baggage Claim.

Below is the project structure.  It contains all source code, 
build files, and a gradle binary for building the code.  

The code uses a Graph object and uses recursion to find all valid routes
from start to end node.  Only the optimized route is returned, i.e., the
minimum number of node hops from start node to end node.  Time is not 
considered when computing the optimized route.  It is assumed that fewer hops
means a shorter travel time.

<pre>
.
├── README.md
├── airport-baggage.in
├── build.gradle
├── gradle
│   └── wrapper
│       ├── gradle-wrapper.jar
│       └── gradle-wrapper.properties
├── gradle.properties
├── gradlew
├── gradlew.bat
├── settings.gradle
└── src
    ├── main
    │   └── java
    │       └── com
    │           └── fletcher
    │               ├── AirPortBaggage.java
    │               ├── AirPortBaggageRouter.java
    │               ├── Bag.java
    │               ├── ConveyorSystem.java
    │               ├── Departure.java
    │               ├── DijkstraAlgorithm.java
    │               ├── GraphMap.java
    │               ├── GraphMapException.java
    │               ├── InvalidInputException.java
    │               ├── Node.java
    │               └── RouteBuilder.java
    └── test
        └── java
            └── com
                └── fletcher
                    └── AirPortBaggageTest.java
</pre>

<h3>To clone this repo</h3>
<pre>
git clone https://github.com/many-faced-g0d/airport-baggage-router.git
</pre>

Java 1.8 is required to run this program.  

Below is the gradle version, java version, etc.

$ gradle -v

<pre>
------------------------------------------------------------
Gradle 4.8.1
------------------------------------------------------------

Build time:   2018-06-21 07:53:06 UTC
Revision:     0abdea078047b12df42e7750ccba34d69b516a22

Groovy:       2.4.12
Ant:          Apache Ant(TM) version 1.9.11 compiled on March 23 2018
JVM:          1.8.0_171 (Oracle Corporation 25.171-b11)
OS:           Linux 4.10.0-38-generic amd64
</pre>

$ java -version
<pre>
java version "1.8.0_171"
Java(TM) SE Runtime Environment (build 1.8.0_171-b11)
Java HotSpot(TM) 64-Bit Server VM (build 25.171-b11, mixed mode)
</pre>

To build the code and run unit test execute following command 
(gradle wrapper -> gradlew) on command line if you don't have gradle 
already installed.

>  ./gradlew clean build test

or if you already have gradle installed

>   gradle clean build test
   
Produces the following output...
<pre>
> Task :clean UP-TO-DATE
> Task :compileJava
> Task :processResources NO-SOURCE
> Task :classes
> Task :jar
> Task :assemble
> Task :compileTestJava
> Task :processTestResources NO-SOURCE
> Task :testClasses

> Task :test


com.fletcher.AirPortBaggageTest > testYObjectGraph2() STANDARD_OUT
    Following are all different paths from 2 to 3
    2 0 1 3 2 0 3 2 1 3 

com.fletcher.AirPortBaggageTest > testZObjectGraph() STANDARD_OUT
    BEGIN UNIT TEST
    Following are all different paths from 10 to 6
    10 9 8 7 4 6 

> Task :check
> Task :build

BUILD SUCCESSFUL in 1s
5 actionable tasks: 4 executed, 1 up-to-date
</pre>

To run code, execute the following command on the command line.

> java -jar ./build/libs/airport-baggage-router-1.0.jar < airport-baggage.in 

Produces the following stdout:

<pre>
0001 Concourse_A_Ticketing A5 A1 : 11
0002 A5 A1 A2 A3 A4 : 9
0003 A2 A1 : 1
0004 A8 A9 A10 A5 : 6
0005 A7 A8 A9 A10 A5 BaggageClaim : 12
</pre>

The contents of input file: airport-baggage.in.

> cat airport-baggage.in

<pre>
# Section: Conveyor System
Concourse_A_Ticketing A5 5
A5 BaggageClaim 5
A5 A10 4
A5 A1 6
A1 A2 1
A2 A3 1
A3 A4 1
A10 A9 1
A9 A8 1
A8 A7 1
A7 A6 1
# Section: Departures
UA10 A1 MIA 08:00
UA11 A1 LAX 09:00
UA12 A1 JFK 09:45
UA13 A2 JFK 08:30
UA14 A2 JFK 09:45
UA15 A2 JFK 10:00
UA16 A3 JFK 09:00
UA17 A4 MHT 09:15
UA18 A5 LAX 10:15
# Section: Bags
0001 Concourse_A_Ticketing UA12
0002 A5 UA17
0003 A2 UA10
0004 A8 UA18
0005 A7 ARRIVAL
</pre>

The program has limited input validation.  Currently only supports that each
section of input contains the appropriate number of fields. If the number of 
fields for a given line of input is not sufficient, an InvalidInputException is
thrown internally, and an error message is written to stdout telling the user
which line of input is invalid.

Below is described the input fields.  As mentioned, input validation is limited. 
For example, it is assumed for section 1 input that the travel time is in 
minutes defined as a whole number integer.  So, a decimal notation in these
fields will cause a NumberFormatException at runtime with no further explanation.
Due to time constraints only one aspect of input validation was considered 
to demonstrate how the program would handle errors.     

Input: The input consists of several sections. The beginning of each section 
is marked by a line starting: “# Section:” 

Section 1: A weighted bi-directional graph describing the conveyor system.
Format: <Node 1> <Node 2> <travel_time (Integers Only)>

Section 2: Departure list Format:
<flight_id> <flight_gate> <destination> <flight_time>

Section 3: Bag list Format:
<bag_number> <entry_point> <flight_id>



If input is valid, an output file is written, "airport-baggage.out".  Again
limited input validation occurs in the program.  

 > cat airport-baggage.out
<pre>
0001 Concourse_A_Ticketing A5 A1 : 11
0002 A5 A1 A2 A3 A4 : 9
0003 A2 A1 : 1
0004 A8 A9 A10 A5 : 6
0005 A7 A8 A9 A10 A5 BaggageClaim : 12
</pre>

The output : The optimized route for each bag.  The program only takes 
into consideration the shortest number of hops from the starting node to 
the ending node.  Time is current not considered when determining the optimized
route.  However, the total travel time is computed for each optimal route
and written to the output file.

<Bag_Number> <point_1> <point_2> [<point_3>, …] : <total_travel_time>
