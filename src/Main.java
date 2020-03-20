public class Main {
	public static void main(String[] args) {
		System.out.println("======HOUSE SIMULATION======");
		
		// Create an electric BatteryMeter with certain characteristics
		BatteryMeter electricity = new BatteryMeter("electric", 0.013, 0);
		// Create a water meter with certain characteristics
		Meter water = new Meter("water", 0.002, 0);
		// Create a house taking the electric and water meter as parameters
		House house = new House(electricity, water);

		try {
			if (args.length > 0) { 
				// Assign the first argument to be the name of the file from which the programme reads
				String file = args[0];
				// Create an AppliancesReader that takes that file as an argument
				AppliancesReader applianceReader = new AppliancesReader(file);

				// Try to get all the electric appliances and put them in the house
				try {
					for (Appliance appliance : applianceReader.getArrayListOfElectricAppliances()) {
						house.addElectricAppliance(appliance);
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
				// Try to get all the water appliances and put them in the house
				try {
					for (Appliance appliance : applianceReader.getArrayListOfWaterAppliances()) { 
						house.addWaterAppliance(appliance); 
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
				// Try to see if the format is right, checks the appliances created 
				// with the once that were expected to be created
				try {
					if (house.numAppliances() == applianceReader.getNumOfAppliances());
				} catch (Exception e) {
					System.out.println("Sorry, your file has invalid format. After every new appliance being 8 lines and empty line should be added.");
					System.exit(0);
				}
				
				// Activate the house using 1, 2 or 3 arguments on the command line
				try {
					// Uses 3 arguments in command line
					house.activate(Integer.parseInt(args[1]), args[2]);
				} catch(Exception e) {
					try {
						// Uses 2 arguments in command line
						house.activate(Integer.parseInt(args[1]));
					} catch(Exception ex1) {
						// Uses 1 argument in command line
						house.activate(168);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Sorry, your have not set a proper argument! Try again");
		}
	}
}