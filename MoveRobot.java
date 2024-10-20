import lejos.hardware.motor.BaseRegulatedMotor;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.Color;
import lejos.utility.Delay;

public class MoveRobot {
	public static void move(int column) {
	    BaseRegulatedMotor motor = new EV3LargeRegulatedMotor(MotorPort.A);
	    EV3ColorSensor colorSensor = new EV3ColorSensor(SensorPort.S1);

	    motor.setSpeed(50);

	 // Initialise count variable to keep track of green detections
	    int count = 0;

	    // Loop indefinitely
	    while (true) {
	        motor.forward();
	        Delay.msDelay(500);
	        
	        if (colorSensor.getColorID() == Color.GREEN) {
	            motor.stop();
	            count++; // Increment count when green is detected
	            

	            if (count >= column) {
	                break; // Exit the loop if count reaches column
	            }
	        }
	    }
	    
	    motor.close();
	    colorSensor.close();

	}
}