// Battery Meter is a subclass of Meter and is responsible to record the utility changes, while considering a battery
public class BatteryMeter extends Meter {
	Battery battery;
	// To record the total power drawn from the mains
	private float powerDrawnFromMain;
	// Records the balance of units
	private float meterReading;

	// A constructor, taking the a name cost and meterReading as parameters and
	// Creating a battery
	public BatteryMeter(String utilityName, double unitCost, float meterReading) {
		super(utilityName, unitCost, meterReading);
		// New battery with capacity of 50
		battery = new Battery(30);
	}

	// Override the consumeUnits, so as it can use the Battery class upon
	// consumption and production
	public void consumeUnits(float unitsConsumed) {
		// Add the unitsConsumed to the meterReading
		this.meterReading += unitsConsumed;
		// If the production is more than the consumption store the excess in the battery
		if (meterReading <= 0) {
			battery.chargeBattery(meterReading);
			powerDrawnFromMain = 0;
			// Set the meterReading to 0
			meterReading = 0;
			// If the consumption is more than the production use the battery to compensate the imbalance
		} else if (meterReading > 0) {
			battery.dischargeBattery(meterReading);
			// Set the total power drawn from the mains to be equal to the meterReading
			// minus the power given from the battery
			powerDrawnFromMain = meterReading - battery.getPowerDrawnThisTime();
			// adjust the meterReading accordingly
			meterReading -= battery.getPowerDrawnThisTime();
		}
	}

	// Showing the amount of units of a utility and the respective cost
	public double report() {
		System.out.println("The battery gave: " + battery.getPowerDrawn() + " utility units");
		System.out.println("The total units used from mains are: " + powerDrawnFromMain);
		System.out.println("The battery power(units) are: " + battery.getBatteryPower());
		// Set the meterReading minus the power given to it by the battery
		this.meterReading -= battery.getPowerDrawn();
		// Reset the the powerDrawn
		battery.setPowerDrawn(0);
		// Reset the the powerDrawnFromMain
		powerDrawnFromMain = 0;
		// Set the meterReading in the Meter class to the meterReading in the BatteryMeter class
		setMeterReading(meterReading);
		// Set the BatteryMeter meterReading to 0
		this.meterReading = 0f;
		// Return the report from the Meter class (utilise it)
		return super.report();
	}
}
