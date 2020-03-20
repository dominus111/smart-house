import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;

// A simulation for a House
public class House {
	// Create an electric meter
	Meter electric;
	// Create a water meter
	Meter water;
	// To represent the Appliances in the House
	ArrayList<Appliance> appliances = new ArrayList<Appliance>();
	PrintStream originalStream;
	PrintStream newStream;
	File savedFile;
	ByteArrayOutputStream newConsole =  new ByteArrayOutputStream();

	// A constructor for the House taking electric meter and
	// water meter as parameters
	public House(Meter electric, Meter water) {
		this.electric = electric;
		this.water = water;
	}

	// An overloaded constructor for the House taking electric BatteryMeter and
	// water meter as parameters
	public House(BatteryMeter electric, Meter water) {
		this.electric = electric;
		this.water = water;
	}

	// Adds the specified Appliance to the House, and sets its meter to be the
	// House electric meter
	public void addElectricAppliance(Appliance electricAppliance) {
		// Add the appliance to the ArrayList
		appliances.add(electricAppliance);
		// Set the meter to the House electric meter
		electricAppliance.setMeter(electric);
	}

	// Adds the specified Appliance to the House, and sets its meter to be the
	// House water meter
	public void addWaterAppliance(Appliance waterAppliance) {
		// Add the appliance to the ArrayList
		appliances.add(waterAppliance);
		// Set the meter to the House electric meter
		waterAppliance.setMeter(water);
	}

	// Removes an Appliance from the House specified in the parameter
	public void removeAppliance(Appliance removedAppliance) {
		// Iterate over the appliances
		Iterator<Appliance> itAppliance = appliances.iterator();
		// If there are more take them
		while (itAppliance.hasNext()) {
			// Get the next one
			Appliance appliance = itAppliance.next();
			// If it equals to the one in the parameter, remove it
			if (appliance.equals(removedAppliance)) {
				itAppliance.remove();
			}
		}
	}

	// Return the number of appliances in the ArrayList
	public int numAppliances() {
		return appliances.size();
	}

	// Simulates one unit of time (an hour) passing in the house
	public double activate() {
		// Records the cost incurred from both water and electric appliances
		double sumOfCost;
		// Iterate through the appliances and call timePasses() on each
		for (Appliance appliance : appliances) {
			appliance.timePasses();
		}
		//System.out.println("\nA new hour:");
		// Sum the cost incurred from the electric appliance and
		// the cost incurred from the water appliances
		sumOfCost = electric.report() + water.report();
		System.out.println("\nA new hour:");
		return sumOfCost;
	}

	// Overloading the activate method, adding a loop for the hours repeated
	public void activate(int timeBeingActive) {
		// Records the total cost incurred for the simulation from both
		// electric and water appliances
		double simulationTotalCost = 0;
		// Call activate(), the number that the appliance is active for times
		for (int i = 0; i < timeBeingActive; i++) {
			try {
				// Make a small pause between each time activate() is called
				Thread.sleep(10);
				//System.out.println("\nA new hour:");
				simulationTotalCost += activate();
			} catch (InterruptedException e) {
			}
		}
		// Round the simulation total to 2 decimal points
		double roundSimulationTotalCost = Math.round(simulationTotalCost*100.0)/100.0;
		System.out.println("The total cost incurred from the simulation is: " + roundSimulationTotalCost);
	}

	// Extension
	// Overloading the activate method, adding a loop for the hours repeated
	// The output from the method can be save into a file
	public void activate(int timeBeingActive, String filename) {
		// create a file with the filename from the argument
		savedFile = new File(filename);
		// If a "save.txt" already exists, delete it
		if (savedFile.exists()) {
			savedFile.delete();
		}
		// Store original print stream
		originalStream = System.out;
		// Set new print stream, now anything written via
		// System.out will be stored in the byte array 'newConsole'
		newStream = new PrintStream(newConsole);
		System.setOut(newStream);
		// Records the total cost incurred for the simulation from both
		// Electric and water appliances
		double simulationTotalCost = 0;
		// Call activate(), the number that the appliance is active for times
		for (int i = 0; i < timeBeingActive; i++) {
			System.out.println("\nA new hour:");
			simulationTotalCost += activate();
		}
		// Round the simulation total to 2 decimal points
		double roundSimulationTotalCost = Math.round(simulationTotalCost*100.0)/100.0;
		System.out.println("The total cost incurred from the simulation is: " + roundSimulationTotalCost);
		// Flush data to new console
		newStream.flush();
		// Retrieve data as string from new console,
		// Now you can use it as you want
		String newStreamData = newConsole.toString();
		// Change stream to original, now anything written
		// Via System.out will go to console.
		System.setOut(originalStream);
		// Print new console data on original console
		System.out.println(newStreamData);
		try {
			// Create a PrintStream that will print output to a file
			PrintStream toFile = new PrintStream(new FileOutputStream(filename, true));
			// print the console output to the file
			toFile.print(newStreamData);
			// Close the stream
			toFile.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
	}
}