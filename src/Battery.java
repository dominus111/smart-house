// Has the ability to store utility
public class Battery {
	// The capacity of the battery
	private int batteryCapacity;
	// How much is battery(in battery units)
	private float batteryPower;
	// The power drawn from the battery
	private float powerDrawn;
	// The power drawn from a single appliance
	private float powerDrawnThisTime;

	// Constructor taking the battery's capacity as a parameter
	public Battery(int batteryCapacity) {
		this.batteryCapacity = batteryCapacity;
	}

	// Charge the battery if the production is more than the consumption
	public void chargeBattery(float units) {
		// If the production excess is less then the battery's capacity,
		// set the batteryPower to the excess
		if ((batteryPower - units) <= batteryCapacity) {
			batteryPower -= units;
		// If the production excess is more then the battery's capacity,
		// set the batteryPower to the capacity
		} else if ((batteryPower - units) > batteryCapacity) {
			batteryPower = batteryCapacity;
		}
	}

	// Discharge the battery if the consumption is more than the production
	public void dischargeBattery(float units) {
		// If the consumption minus the batteryPower is less than 0,
		// utilise all of the batteryPower
		if ((units - batteryPower) < 0) {
			// Add the consumption to the powerDrawn from the battery
			powerDrawn += units;
			// Will be subtracted from the meterReading
			powerDrawnThisTime = units;
			// Subtract the consumption from the batteryPower
			batteryPower -= units;
		// If the consumption minus the batteryPower is less than 0,
		// adjust the powerDrawn and the batteryPower appropriately
		} else if ((units - batteryPower) >= 0) {
			powerDrawn += batteryPower;
			// Will be subtracted from the meterReading
			powerDrawnThisTime = batteryPower;
			batteryPower = 0;
		}
	}

	// Used to adjust the meterReading accordingly
	public float getPowerDrawn() {
		return powerDrawn;
	}
	
	// Used to adjust the meterReading accordingly
	public float getPowerDrawnThisTime() {
		return powerDrawnThisTime;
	}

	// Used later for the report()
	public float getBatteryPower() {
		return batteryPower;
	}

	// Set the powerDrawn to the wanted amount
	public void setPowerDrawn(int newCharge) {
		this.powerDrawn = newCharge;
	}
}