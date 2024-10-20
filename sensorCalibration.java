import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.BaseRegulatedMotor;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.Color;

public class sensorCalibration implements Behavior {
    private boolean suppressed = false;
    private static boolean calibrated = false;
    private static final int targetCount = 7;
    private int colorCount = 0;

    @Override
    public boolean takeControl() {
        // Take control if not yet calibrated
        return !calibrated;
    }

    @Override
    public void action() {
        suppressed = false;
        BaseRegulatedMotor motor = new EV3LargeRegulatedMotor(MotorPort.A);
        EV3ColorSensor sensor = new EV3ColorSensor(SensorPort.S1);

        motor.setSpeed(50);

        // Calibration loop
        while (!suppressed && !calibrated && colorCount < targetCount) {
            motor.forward();
            // Check for red or green colour
            if (sensor.getColorID() == Color.RED || sensor.getColorID() == Color.GREEN) {
                motor.stop();
                colorCount++;
                LCD.clear(); // Clear LCD before displaying messages
                LCD.drawString("Calibrating... " + colorCount + "/" + targetCount, 0, 0);
                Delay.msDelay(1000); // Delay for stability
                LCD.clear();

            }
        }

        // Check if calibration is complete
        if (colorCount >= targetCount) {
            calibrated = true;
            LCD.clear();
            LCD.drawString("Calibration complete.", 0, 0);
            Delay.msDelay(2000);
            motor.close();
            sensor.close();
            resetBot.reset();
        }
        

        // Close motor and sensor
        motor.close();
        sensor.close();
    }

    @Override
    public void suppress() {
        suppressed = true;
    }

    // Method to check if calibration is done
    public static boolean isCalibrated() {
        return calibrated;
    }
}