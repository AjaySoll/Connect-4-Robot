import lejos.hardware.motor.BaseRegulatedMotor;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.Color;

public class resetBot {

	public static void reset() {
		
		//initialise motor and sensor to the correct ports.
		BaseRegulatedMotor motor = new EV3LargeRegulatedMotor(MotorPort.A);
	    EV3ColorSensor colorSensor = new EV3ColorSensor(SensorPort.S1);

		motor.setSpeed(50);
	    //infinitely loop the motor moving backward until the red mark (1st column) is detected.
	    while (true) {
	        motor.backward();

	        
	        if (colorSensor.getColorID() == Color.RED) {
	        	
	            motor.stop();//stops the motor once the red mark is detected.
	            
	            break;// exits the loop
	            }
	    }
	    
	    //both motor and sensor are closed to avoid resource leaks
	    motor.close();
	    colorSensor.close();

	}

}
