import java.util.Random;

// Classes: CyclicFixed, CyclicVaries, RandomFixed, RandomVaries use this class
public abstract class Appliance {
	// The name of the appliance
	protected String name;
	// To record the units of consumption and production of a utility
	Meter meter;
	Meter helpMeter;
	// Create a Random object, which is needed in timePasses()
	// for certain appliances
	Random random = new Random();

	// Constructor taking a name as parameters
	public Appliance(String name) {
		this.name = name;
	}

	// setting the meter to the one passed as a parameter
	public void setMeter(Meter meter) {
		this.meter = meter;
	}

	// Denotes one increment of time passing (1 increment of time = 1 hour)
	// Each concrete class that inherits from the abstract Appliance class
	// will have to provide code that determines how it
	// consumes or produces a utility when timePasess is called.
	public abstract void timePasses();

	// Make sure that the consumed units are added or subtracted from the meterReading
	// either in the Meter class or in the BatteryMeter class (depends on what is
	// being used)
	protected void tellMeterToConsumeUnits(float units) {
		this.meter.consumeUnits(units);
	}
}