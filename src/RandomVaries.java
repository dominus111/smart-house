//inherits from Appliance class
public class RandomVaries extends Appliance {
	// holds the value for how many units consumes per hour
	private float unitsConsumed;
	// used to apply an increment for the time passed
	private int clock;
	private float minUnitsConsumed;
	private float maxUnitsConsumed;
	// Stores out of what, the probability of being on
	private int probabilityOutOf;

	// Constructor taking the name, the minimum and max units consumed, and
	// out of what, the probability of being on.
	// Throws an exception if the active time is not in the 24 hours range
	public RandomVaries(String name, float minUnitsConsumed, float maxUnitsConsumed, int probabilityOutOf) throws Exception {
		// Inherits name from the Appliance class
		super(name);
		this.minUnitsConsumed = minUnitsConsumed;
		this.maxUnitsConsumed = maxUnitsConsumed;
		this.probabilityOutOf = probabilityOutOf;
		
		// check if the minimum and maximum units are right
		try {
			if (maxUnitsConsumed < minUnitsConsumed);
		} catch (Exception e) {
			System.out.println("The maximum units are less than the minimum");
			System.exit(0);
		}
	}

	// Calculate how many units an appliance has used in a time period
	public void timePasses() {
		// If timePasses is called on an appliance but no meter has been set
		// throw Exception
		if (this.meter == null) {
			throw new RuntimeException("You have not set a meter!");
		}
		// Generate a random number between 0 and probabilityOutOf - 1
		int randomNumber = random.nextInt(probabilityOutOf);
		// When the clock is less than 24 hours
		if (clock < 24) {
			// Counting hours
			clock++;
			// The chance of being 0 is 1/probabilityOutOf
			if (randomNumber == 0) {
				// Generate a random number between minUnitsConsumed and maxUnitsConsumed
				unitsConsumed = random.nextFloat() * (maxUnitsConsumed - minUnitsConsumed) + minUnitsConsumed;
				// Right number of units are recorded by the meterReading
				tellMeterToConsumeUnits(unitsConsumed);
			} else {
				System.out.println("Today the " + name + " wasn't used!");
			}
		} else {
			// When it reaches the end of the day 24th hour, reset the clock
			clock = 0;
		}
	}
}