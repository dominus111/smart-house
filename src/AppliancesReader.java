import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

// Reads, loads and parses the file
public class AppliancesReader {
	// Stores the filename
	String filename;
	// Instance variable of BufferReader
	BufferedReader reader;
	// Holds all the lines as elements of an ArrayList
	ArrayList<String> listWithLinesFromTheFile;
	boolean fileLoaded = false;
	boolean fileParsed = false;
	ArrayList<Appliance> allTheElectricAppliances;
	ArrayList<Appliance> allTheWaterAppliances;
	ArrayList<Appliance> allTheHybridAppliances;
	// Holds the total number of Appliances(chunks of 9 lines)
	int numOfAppliances;

	// An AppliancesReader constructor and instantiate the 2 ArrayLists
	public AppliancesReader(String filename) {
		this.filename = filename;
		allTheElectricAppliances = new ArrayList<>();
		allTheWaterAppliances = new ArrayList<>();
	}

	// Returns the number of appliances(chunks by 9 lines)
	// Needed to check it against the real number of appliances
	public int getNumOfAppliances() {
		return numOfAppliances;
	}

	// Get the ArrayList with the electric appliances.
	// Throw an exception, if the file is not in the right format
	public ArrayList<Appliance> getArrayListOfElectricAppliances() throws Exception {
		if (fileLoaded != true) {
			loadFile();
		}
		if (fileParsed != true) {
			parseFile();
		}
		return allTheElectricAppliances;
	}

	// Get the ArrayList with the water appliances.
	// Throw an exception, if the file is not in the right format
	public ArrayList<Appliance> getArrayListOfWaterAppliances() throws Exception {
		if (fileLoaded != true) {
			loadFile();
		}
		if (fileParsed != true) {
			parseFile();
		}
		return allTheWaterAppliances;
	}

	// Return the next line of the contents of the file
	public String getLine() {
		// Try to return the next line of the contents of the file
		try {
			return reader.readLine();
			// If you don't find such file catch IOException
		} catch (IOException e) {
			e.printStackTrace();
		}
		// and return null
		return null;
	}

	// Checks if the file is ready to be read
	public boolean fileIsReady() {
		try {
			// If you can find the file
			if (reader != null) {
				// return that the file is ready
				return reader.ready();
			}
			// else catch IOException
		} catch (IOException e) {
			e.printStackTrace();
		}
		// and return false
		return false;
	}

	//  If the file is ready load it to an ArrayList
	public boolean loadFile() {
		// Instantiate an ArralyList that will hold all the lines from the loadedFile
		listWithLinesFromTheFile = new ArrayList<String>();
		// An auxiliary string
		String line;

		// Try reading from a file with the filename
		try {
			reader = new BufferedReader(new FileReader(filename));
			// If you don't find the file, catch an FileNotFoundException
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		// If the file is ready
		if (fileIsReady()) {
			// read it line by line
			while ((line = getLine()) != null) {
				// add each line to the ArrayList
				listWithLinesFromTheFile.add(line);
			}
		}
		return this.fileLoaded = true;
	}

	// Parse the file, throw an exception if the file is not in the right format
	public void parseFile() throws Exception {
		// Initialise variables that will hold the needed text or
		// values for the correct Appliance
		String name = "";
		String subclass = "";
		String meter = "";
		float minUnitsConsumed = 0f;
		float maxUnitsConsumed = 0f;
		float fixedUnits = 0f;
		int probability = 0;
		int timeActive = 0;

		// Holds the position, on which line we are in the ArrayList
		String lineNow;

		// Needed to test if the file format is right
		float numOfAppliancesInFloat = ((float) this.listWithLinesFromTheFile.size()) / 9;
		// Holds the total number of Appliances(chunks of 9 lines)
		numOfAppliances = (int) numOfAppliancesInFloat;

		// Checks to see if the file format is right
		try {
			if (numOfAppliancesInFloat == numOfAppliances);
		} catch (Exception e) {
			System.out.println("Sorry, your file has invalid format. After every new appliance being 8 lines and empty line should be added." + e);
			System.exit(0);
		}

		// Loop through every Appliance[block(being 9 lines)]
		// and when you get the needed information
		// create one with the appropriate characteristics
		for (int chunk = 1; chunk <= numOfAppliances; chunk++) {
			// Loop through each line of each Appliance(being 9 lines),
			// which is holding the necessary description(information) and
			// load this information to the local variables,
			// which will be used after it exits the loop to create a new Appliance
			// of a certain type. Than enter in another loop an repeat
			for (int currentLine = 1; currentLine < 9; currentLine++) {
				// Get the current line
				lineNow = this.listWithLinesFromTheFile.get(((chunk - 1) * 9) + currentLine - 1);

				// Split the line onto ": "
				String[] line = lineNow.split(": ");

				// Take the first part before the ": " and compare it with different text or
				// values and if it matches, process the second element after the ": " if needed,
				// and assign it to the appropriate local variable
				if (line[0].equals("name")) {
					name = line[1];
				}
				if (line[0].equals("subclass")) {
					subclass = line[1];
				}
				if (line[0].equals("meter")) {
					meter = line[1];
				}
				if (line[0].equals("Min units consumed")) {
					try {
						minUnitsConsumed = Float.parseFloat(line[1]);
					} catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
						minUnitsConsumed = 0f;
					}
				}
				if (line[0].equals("Max units consumed")) {
					try {
						maxUnitsConsumed = Float.parseFloat(line[1]);
					} catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
						maxUnitsConsumed = 0f;
					}
				}
				if (line[0].equals("Fixed units consumed")) {
					try {
						fixedUnits = Float.parseFloat(line[1]);
					} catch (NumberFormatException | ArrayIndexOutOfBoundsException  e) {
						fixedUnits = 0f;
					}
				}
				if (line[0].equals("Probability switched on")) {
					try {
						probability = Integer.parseInt(line[1].split(" ")[2]);
					} catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
						probability = 0;
					}
				}
				if (line[0].equals("Cycle length")) {
					try {
						timeActive = Integer.parseInt(line[1].split("/")[0]);
					} catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
						timeActive = 0;
					}
				}
			}		
			// Create the appropriate Appliance
			try {
				if (subclass.equals("CyclicFixed")) {
					if (meter.equals("electric")) {
						this.allTheElectricAppliances.add(new CyclicFixed(name, fixedUnits, timeActive));
					} else {
						this.allTheWaterAppliances.add(new CyclicFixed(name, fixedUnits, timeActive));
					}
				}
				if (subclass.equals("CyclicVaries")) {
					if (meter.equals("electric")) {
						this.allTheElectricAppliances.add(new CyclicVaries(name, minUnitsConsumed, maxUnitsConsumed, timeActive));
					} else {
						this.allTheWaterAppliances.add(new CyclicVaries(name, minUnitsConsumed, maxUnitsConsumed, timeActive));
					}
				}
				if (subclass.equals("RandomFixed")) {
					if (meter.equals("electric")) {
						this.allTheElectricAppliances.add(new RandomFixed(name, fixedUnits, probability));
					} else {
						this.allTheWaterAppliances.add(new RandomFixed(name, fixedUnits, probability));
					}
				}
				if (subclass.equals("RandomVaries")) {
					if (meter.equals("electric")) {
						this.allTheElectricAppliances.add(new RandomVaries(name, minUnitsConsumed, maxUnitsConsumed, probability));
					} else {
						this.allTheWaterAppliances.add(new RandomVaries(name, minUnitsConsumed, maxUnitsConsumed, probability));
					}
				}
			} catch (Exception e) {
				System.out.println("Sorry, your file has invalid format. After every new appliance being 8 lines and empty line should be added.");
				e.printStackTrace();
			}
		}
		this.fileParsed = true;
	}
}