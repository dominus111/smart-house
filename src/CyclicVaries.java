// Inherits from Appliance class
public class CyclicVaries extends Appliance {
	// Holds the value for how many units are consumed per hour
	private float unitsConsumed;
	// Holds the value for the time being active from 1 to 24 hours
	private int timeActive;
	// Used to apply an increment for the time passed
	private int clock;
	private float minUnitsConsumed;
	private float maxUnitsConsumed;

	// Constructor taking the name, the minimum and max units consumed, and
	// the active time of the appliance
	// Throws an exception if the active time is not in the 24 hours range
	public CyclicVaries(String name, float minUnitsConsumed, float maxUnitsConsumed, int timeActive) throws Exception {
		// inherits name from the Appliance class
		super(name);
		this.minUnitsConsumed = minUnitsConsumed;
		this.maxUnitsConsumed = maxUnitsConsumed;
		// making sure that the active time is between 1 to 24 hours
		if (timeActive >= 1 && timeActive <= 24) {
			this.timeActive = timeActive;
		} else {
			throw new Exception("The active time for " + name + " should be between 1 and 24 hours");
		}
		
		// check if the minimum and maximum units are right
		try {
			if (maxUnitsConsumed < minUnitsConsumed);
		} catch (Exception e) {
			System.out.println("The maximum units are less than the minimum");
			System.exit(0);
		}
	}

	// Calculates how many units an appliance has used in a time period
	public void timePasses() {
		// If timePasses is called on an appliance but no meter has been set
		// throw Exception
		if (this.meter == null) {
			throw new RuntimeException("You have not set a meter!");
		}
		// When the clock is less than 24 hours
		if (clock < 24) {
			// counting hours
			clock++;
			// Consume units for the active time
			if (clock <= timeActive) {
				// Generate a random number between minUnitsConsumed and maxUnitsConsumed
				unitsConsumed = random.nextFloat() * (maxUnitsConsumed - minUnitsConsumed) + minUnitsConsumed;
				// Right number of units are recorded by the meterReading
				tellMeterToConsumeUnits(unitsConsumed);
			}
		} else {
			// When it reaches the end of the day 24th hour, reset the clock
			clock = 0;
		}
	}
}