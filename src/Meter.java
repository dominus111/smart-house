// Manages the consumption and production of a particular utility
public class Meter {
	// The type of utility
	private String utilityName;
	// Cost of one unit of the specific utility
	private double unitCost;
	// The balance of units from the last meter reading
	private float meterReading;
	// The corresponding cost incurred to the home occupier
	double costIncurred;

	// Constructor taking utilityName, unitCost and meterReading as parameters
	public Meter(String utilityName, double unitCost, float meterReading) {
		this.utilityName = utilityName;
		this.unitCost = unitCost;
		this.meterReading = meterReading;
	}

	// Adjusts the meterReading appropriately
	public void consumeUnits(float unitsValue) {
		this.meterReading += unitsValue;
	}

	// Showing the amount of units of a utility and the respective cost
	public double report() {
		// Makes sure if the meterReading is a negative number to consider it as 0
		if (meterReading < 0) {
			meterReading = 0;
		}
		// Cost = number of units * one unit cost
		costIncurred = meterReading * unitCost;
		// Prints the name of the utility the balance of units consumed and the incurred cost
		System.out.println("Utility: " + utilityName + " " + meterReading + " " + costIncurred);
		// Reseting the meterReading to zero
		meterReading = 0;
		return costIncurred;
	}

	// Needed for to set this.meterReading to be equal to the one in the
	// BatteryMeter class. It is needed for the Meter's report method
	public void setMeterReading(float batteryMeterReading) {
		this.meterReading = batteryMeterReading;
	}
}