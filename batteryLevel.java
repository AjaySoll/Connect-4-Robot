import lejos.hardware.Battery;
import lejos.robotics.subsumption.Behavior;


public class batteryLevel implements Behavior{

	private boolean suppressed = false;
	
	@Override
	public boolean takeControl() {
		double batteryLevel = Battery.getVoltage();
        double batteryPercentage = calculateBatteryPercentage(batteryLevel);

        // Take control only when battery level is below 20%
        return batteryPercentage < 20;
	}

	@Override
	public void action() {
        while (!suppressed) {
            double batteryLevel = Battery.getVoltage();
            double batteryPercentage = calculateBatteryPercentage(batteryLevel);

            if (batteryPercentage < 20) {
                System.out.println("Warning: Battery level is below 20%!");
            } else {
                System.out.println("Battery level is at " + batteryPercentage + "%");
            }

            // Delay to avoid continuous looping without pause
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                // Handle interrupted exception if needed
                e.printStackTrace();
            }
            
        }
    }

	@Override
	public void suppress() {
		
		suppressed = true;
		
	}
	
	private static double calculateBatteryPercentage(double voltage) {
        // Assuming battery voltage ranges from 6V to 9V
        double minVoltage = 6.0;
        double maxVoltage = 9.0;

        // Calculate battery percentage based on voltage
        double batteryPercentage = ((voltage - minVoltage) / (maxVoltage - minVoltage)) * 100;

        return batteryPercentage;
    }

}
