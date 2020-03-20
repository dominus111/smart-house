// Inherits from Appliance class
public class RandomFixed extends Appliance {
	// Holds the value for how many units are consumed per hour
	private float fixedUnitsConsumed;
	// Used to apply an increment for the time passed
	private int clock;
	// Stores out of what, the probability of being on
	private int probabilityOutOf;

	// Constructor taking the name the units consumed and out of what and
	// the probability of being on
	public RandomFixed(String name, float fixedUnitsConsumed, int probabilityOutOf) {
		// Inherits name from the Appliance class
		super(name);
		this.fixedUnitsConsumed = fixedUnitsConsumed;
		this.probabilityOutOf = probabilityOutOf;
	}

	// calculate how many units an appliance has used in a time period
	public void timePasses() {
		// If timePasses is called on an appliance but no meter has been set
		// throw Exception
		if (this.meter == null) {
			throw new RuntimeException("You have not set a meter!");
		}
		// Generate a random number between 0 and probabilityOutOf - 1
		int randomNumber = random.nextInt(probabilityOutOf);
		// wWen the clock is less than 24 hours
		if (clock < 24) {
			// Counting hours
			clock++;
			// The chance of being 0 is 1/probabilityOutOf
			if (randomNumber == 0) {
				// Right number of units are recorded by the meterReading
				tellMeterToConsumeUnits(fixedUnitsConsumed);
			} else {
				System.out.println("Today the " + name + " wasn't used!");
			}
		} else {
			// When it reaches the end of the day 24th hour, reset the clock
			clock = 0;
		}
	}
}
