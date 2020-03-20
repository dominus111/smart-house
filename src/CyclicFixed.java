// Inherits from Appliance class
public class CyclicFixed extends Appliance {
	// Stores the fixed amount of units that the appliance consumes
	private float fixedUnitsConsumed;
	// Stores how long an appliance is active each day
	private int timeActive;
	// Stores, which hour of the day is
	private int clock;

	// Constructor taking the name the units consumed and the active time of the appliance
	public CyclicFixed(String name, float fixedUnitsConsumed, int timeActive) throws Exception {
		// Inherits name from the Appliance class
		super(name);
		this.fixedUnitsConsumed = fixedUnitsConsumed;
		// Making sure that the active time is between 1 to 24 hours
		if (timeActive >= 1 && timeActive <= 24) {
			this.timeActive = timeActive;
		} else {
			throw new Exception("The active time for " + name + " should be between 1 and 24 hours");
		}
	}

	// Override timesPasses() from Appliance class
	// Records 1 increment of time = 1 hour
	public void timePasses() {
		// If timePasses is called on an appliance but no meter has been set
		// throw Exception
		if (this.meter == null) {
			throw new RuntimeException("You have not set a meter!");
		}
		// When the clock is less than 24 hours
		if (clock < 24) {
			// Counting hours
			clock++;
			// Consume units for the active time
			if (clock <= timeActive) {
				// Right number of units are recorded by the meterReading
				tellMeterToConsumeUnits(fixedUnitsConsumed);
			}
		} else {
			// When it reaches the end of the day 24th hour, reset the clock
			clock = 0;
		}
	}
}